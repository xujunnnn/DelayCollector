/*
 * Copyright (c) 2016 Inocybe and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl.topo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataBroker;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataChangeEvent;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.l2switch.arphandler.inventory.InventoryReader;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2switch.loopremover.rev140714.StpStatusAwareNodeConnector;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class AsyncInventory extends InventoryReader{
	private static final Logger LOG = LoggerFactory.getLogger(InventoryReader.class);
    private DataBroker dataService;
    // Key: SwitchId, Value: NodeConnectorRef that corresponds to NC between
    // controller & switch
    private Map<String, NodeConnectorRef> controllerSwitchConnectors;
    // Key: SwitchId, Value: List of node connectors on this switch
    private Map<String, List<NodeConnectorRef>> switchNodeConnectors;
    private List<ListenerRegistration<DataChangeListener>> listenerRegistrationList = new ArrayList<>();

    public void setRefreshData(boolean refreshData) {
        this.refreshData = refreshData;
    }

    private boolean refreshData = false;
    private long refreshDataDelay = 20L;
    private boolean refreshDataScheduled = false;
    private final ScheduledExecutorService nodeConnectorDataChangeEventProcessor = Executors.newScheduledThreadPool(1);
	public AsyncInventory(DataBroker dataService) {
		super(dataService);
		this.dataService=dataService;
		controllerSwitchConnectors=new ConcurrentHashMap<>();
		switchNodeConnectors=new ConcurrentHashMap<>();
		// TODO Auto-generated constructor stub
	}


	
	public Map<String, NodeConnectorRef> readControllerSwitchConnectors() {
		return controllerSwitchConnectors;
	}



	public void setControllerSwitchConnectors(Map<String, NodeConnectorRef> controllerSwitchConnectors) {
		this.controllerSwitchConnectors = controllerSwitchConnectors;
	}



	public Map<String, List<NodeConnectorRef>> readSwitchNodeConnectors() {
		return switchNodeConnectors;
	}



	public void setSwitchNodeConnectors(Map<String, List<NodeConnectorRef>> switchNodeConnectors) {
		this.switchNodeConnectors = switchNodeConnectors;
	}
	 private void registerAsDataChangeListener(){
		    InstanceIdentifier<NodeConnector> nodeConnector = InstanceIdentifier.builder(Nodes.class)
		            .child(Node.class)
		            .child(NodeConnector.class)
		            .build();

		    this.listenerRegistrationList.add(dataService.registerDataChangeListener(
		            LogicalDatastoreType.OPERATIONAL,
		            nodeConnector,
		            this, AsyncDataBroker.DataChangeScope.BASE));


		    InstanceIdentifier<StpStatusAwareNodeConnector> stpStatusAwareNodeConnector
		            = InstanceIdentifier.builder(Nodes.class)
		            .child(Node.class)
		            .child(NodeConnector.class)
		            .augmentation(StpStatusAwareNodeConnector.class)
		            .build();
		    this.listenerRegistrationList.add(dataService.registerDataChangeListener(
		            LogicalDatastoreType.OPERATIONAL,
		            stpStatusAwareNodeConnector,
		            this, AsyncDataBroker.DataChangeScope.BASE));

		  }
	  @Override
	  public void onDataChanged(AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> asyncDataChangeEvent){
	    if (asyncDataChangeEvent == null) {
	      LOG.info("In onDataChanged: No processing done as change even is null.");
	      return;
	    }

	    if(!refreshDataScheduled) {
	      synchronized(this) {
	        if(!refreshDataScheduled) {
	          nodeConnectorDataChangeEventProcessor.schedule(new NodeConnectorDataChangeEventProcessor(),refreshDataDelay, TimeUnit.MILLISECONDS);
	          refreshDataScheduled = true;
	        }
	      }
	    }

	  }

	  private class NodeConnectorDataChangeEventProcessor implements Runnable {

		    @Override
		    public void run() {
		      controllerSwitchConnectors.clear();
		      switchNodeConnectors.clear();
		      refreshDataScheduled = false;
		      setRefreshData(true);
		      readInventory();
		    }

		  }



	   public void readInventory() {
	        // Only run once for now
	        if (!refreshData) {
	            return;
	        }
	        synchronized (this) {
	            if (!refreshData)
	                return;
	            // Read Inventory
	            InstanceIdentifier.InstanceIdentifierBuilder<Nodes> nodesInsIdBuilder = InstanceIdentifier
	                    .<Nodes>builder(Nodes.class);
	            Nodes nodes = null;
	            ReadOnlyTransaction readOnlyTransaction = dataService.newReadOnlyTransaction();

	            try {
	                Optional<Nodes> dataObjectOptional = null;
	                dataObjectOptional = readOnlyTransaction
	                        .read(LogicalDatastoreType.OPERATIONAL, nodesInsIdBuilder.build()).get();
	                if (dataObjectOptional.isPresent())
	                    nodes = (Nodes) dataObjectOptional.get();
	            } catch (InterruptedException e) {
	                LOG.error("Failed to read nodes from Operation data store.");
	                readOnlyTransaction.close();
	                throw new RuntimeException("Failed to read nodes from Operation data store.", e);
	            } catch (ExecutionException e) {
	                LOG.error("Failed to read nodes from Operation data store.");
	                readOnlyTransaction.close();
	                throw new RuntimeException("Failed to read nodes from Operation data store.", e);
	            }

	            if (nodes != null) {
	                // Get NodeConnectors for each node
	                for (Node node : nodes.getNode()) {
	                    ArrayList<NodeConnectorRef> nodeConnectorRefs = new ArrayList<NodeConnectorRef>();
	                    List<NodeConnector> nodeConnectors = node.getNodeConnector();
	                    if (nodeConnectors != null) {
	                        for (NodeConnector nodeConnector : nodeConnectors) {
	                    
	                            if (nodeConnector.getKey().toString().contains("LOCAL")) {
	                                continue;
	                            }
	                            NodeConnectorRef ncRef = new NodeConnectorRef(InstanceIdentifier.<Nodes>builder(Nodes.class)
	                                    .<Node, NodeKey>child(Node.class, node.getKey())
	                                    .<NodeConnector, NodeConnectorKey>child(NodeConnector.class, nodeConnector.getKey())
	                                    .build());
	                            nodeConnectorRefs.add(ncRef);
	                        }
	                    }

	                    switchNodeConnectors.put(node.getId().getValue(), nodeConnectorRefs);
	                    NodeConnectorRef ncRef = new NodeConnectorRef(InstanceIdentifier.<Nodes>builder(Nodes.class)
	                            .<Node, NodeKey>child(Node.class, node.getKey())
	                            .<NodeConnector, NodeConnectorKey>child(NodeConnector.class,
	                                    new NodeConnectorKey(new NodeConnectorId(node.getId().getValue() + ":LOCAL")))
	                            .build());
	                    LOG.debug("Local port for node {} is {}", node.getKey(), ncRef);
	                    controllerSwitchConnectors.put(node.getId().getValue(), ncRef);
	                }
	            }
	            readOnlyTransaction.close();
	            refreshData = false;

	            if(0 == listenerRegistrationList.size()){
	              registerAsDataChangeListener();
	            }
	        }
	    }
	

}
