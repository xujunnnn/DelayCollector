/*
 * Copyright © 2017 org.bupt.xu and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl.flow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataBroker.DataChangeScope;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataChangeEvent;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.openflowplugin.api.OFConstants;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Uri;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.OutputActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.output.action._case.OutputActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.TableKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.Flow;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.AddFlowInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.AddFlowOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.FlowTableRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowCookie;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowModFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.OutputPortValues;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.InstructionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Match;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.ApplyActionsCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.Instruction;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.InstructionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.EtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetTypeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.EthernetMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.IpMatch;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.IpMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ethernet.rev140528.KnownEtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.KnownIpProtocols;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

public class InitialFlowWriter implements DataChangeListener{
	private static final Logger LOG = LoggerFactory.getLogger(InitialFlowWriter.class);
	private final ExecutorService initialFlowExecutor = Executors.newCachedThreadPool();
	private final SalFlowService salFlowService;
	private final String FLOW_ID_PREFIX = "Delay-";
	private short flowTableId;
	private int flowPriority;
	private int flowIdleTimeout;
	private int flowHardTimeout;
	private int vlanid;
	private AtomicLong flowIdInc = new AtomicLong();
	private AtomicLong flowCookieInc = new AtomicLong(0x5a00000000000000L);
	public InitialFlowWriter(SalFlowService salFlowService) {
		// TODO Auto-generated constructor stub
		this.salFlowService=salFlowService;
	}
	 public void setFlowTableId(short flowTableId) {
	        this.flowTableId = flowTableId;
	    }

	    public void setFlowPriority(int flowPriority) {
	        this.flowPriority = flowPriority;
	    }

	    public void setFlowIdleTimeout(int flowIdleTimeout) {
	        this.flowIdleTimeout = flowIdleTimeout;
	    }

	    public void setFlowHardTimeout(int flowHardTimeout) {
	        this.flowHardTimeout = flowHardTimeout;
	    }
	    public void setVlanId(int vlanid){
	    	this.vlanid=vlanid;
	    }
	    public ListenerRegistration<DataChangeListener> registerAsDataChangeListener(DataBroker dataBroker) {
	        InstanceIdentifier<Node> nodeInstanceIdentifier = InstanceIdentifier.builder(Nodes.class).child(Node.class).build();
	        return dataBroker.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL, nodeInstanceIdentifier, this, DataChangeScope.BASE);
	    }
	@Override
	public void onDataChanged(AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> change) {
		// TODO Auto-generated method stub
		Map<InstanceIdentifier<?>, DataObject> createdData=change.getCreatedData();
		if(createdData!=null || !createdData.isEmpty()){
			Set<InstanceIdentifier<?>> nodeIds=createdData.keySet();
			initialFlowExecutor.submit(new InitialFlowWriterProcessor(nodeIds));
		}
		
	}
	private class InitialFlowWriterProcessor implements Runnable{
		Set<InstanceIdentifier<?>> nodeIds = null;
		public InitialFlowWriterProcessor(Set<InstanceIdentifier<?>> nodeIds) {
			// TODO Auto-generated constructor stub
			this.nodeIds=nodeIds;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(nodeIds==null)
				return;
			for(InstanceIdentifier<?> nodeId:nodeIds){
				if(Node.class.isAssignableFrom(nodeId.getTargetType())){
					InstanceIdentifier<Node> invNodeId=(InstanceIdentifier<Node>) nodeId;
					if(invNodeId.firstKeyOf(Node.class,NodeKey.class).getId().getValue().contains("openflow:")){
						addInitialFlows(invNodeId);
					}
				}
			}
			
		}
		
		public void addInitialFlows(InstanceIdentifier<Node> nodeId){
			 LOG.debug("adding initial flows for node {} ", nodeId);
			 InstanceIdentifier<Table> tableId=getTableInstanceId(nodeId);
			 InstanceIdentifier<Flow> flowId=getFlowInstanceId(tableId);
			 writeFlowToController(nodeId, tableId, flowId, createDelayToControllerFlow(flowTableId, flowPriority));
	         LOG.debug("Added initial flows for node {} ", nodeId);
		}
		 private InstanceIdentifier<Table> getTableInstanceId(InstanceIdentifier<Node> nodeId) {
	            // get flow table key
	            TableKey flowTableKey = new TableKey(flowTableId);

	            return nodeId.builder()
	                    .augmentation(FlowCapableNode.class)
	                    .child(Table.class, flowTableKey)
	                    .build();
	    }
		 
		 private InstanceIdentifier<Flow> getFlowInstanceId(InstanceIdentifier<Table> tableId) {
	            // generate unique flow key
	            FlowId flowId = new FlowId(FLOW_ID_PREFIX+String.valueOf(flowIdInc.getAndIncrement()));
	            FlowKey flowKey = new FlowKey(flowId);
	            return tableId.child(Flow.class, flowKey);
	        }
		 private Flow createDelayToControllerFlow(short tableid,int priority){
			 FlowBuilder delayFlow=new FlowBuilder().setTableId(tableid).setFlowName("delaytocntrl");
			 delayFlow.setId(new FlowId(Long.toString(delayFlow.hashCode())));
			 EthernetMatchBuilder ethernetMatchBuilder = new EthernetMatchBuilder()
	                    .setEthernetType(new EthernetTypeBuilder()
	                            .setType(new EtherType(Long.valueOf(KnownEtherType.Ipv4.getIntValue()))).build());
			 IpMatch ipMatch=new IpMatchBuilder().setIpProtocol((short)KnownIpProtocols.Experimentation1.getIntValue()).build();
			// VlanMatchBuilder vlanMatchBuilder=new VlanMatchBuilder().setVlanId(new VlanIdBuilder().setVlanIdPresent(true).setVlanId(new VlanId(vlanid)).build());
			 Match match=new MatchBuilder().setEthernetMatch(ethernetMatchBuilder.build())
					 //.setVlanMatch(vlanMatchBuilder.build())
					 .setIpMatch(ipMatch)
					 .build();
			 List<Action> actions=new ArrayList<Action>();
			 actions.add(getSendToControllerAction());
			// Create an Apply Action
	            ApplyActions applyActions = new ApplyActionsBuilder() //
	                    .setAction(ImmutableList.copyOf(actions)) //
	                    .build();

	            // Wrap our Apply Action in an Instruction
	            Instruction applyActionsInstruction = new InstructionBuilder() //
	                    .setOrder(0)
	                    .setInstruction(new ApplyActionsCaseBuilder()//
	                            .setApplyActions(applyActions) //
	                            .build()) //
	                    .build();
	            delayFlow.setMatch(match)
	            		 .setInstructions(new InstructionsBuilder().setInstruction(ImmutableList.of(applyActionsInstruction)).build())
	            		 .setPriority(priority)
	            		 .setBufferId(OFConstants.OFP_NO_BUFFER)
	            		 .setHardTimeout(flowHardTimeout)
	            		 .setIdleTimeout(flowIdleTimeout)
	            		 .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
	            		 .setFlags(new FlowModFlags(false, false, false, false, false));

	            // Put our Instruction in a list of Instructions
	            return delayFlow.build();
		 }
		 
		 private Action getSendToControllerAction(){
			 Action sendToController=new ActionBuilder()
					 .setOrder(0)
					 .setKey(new ActionKey(0))
					 .setAction(new OutputActionCaseBuilder().setOutputAction(new OutputActionBuilder()
					 .setMaxLength(0xffff).setOutputNodeConnector(new Uri(OutputPortValues.CONTROLLER.toString())).build(
							 
							 )).build()).build();
			 return sendToController;
			 
		 }
		 private Future<RpcResult<AddFlowOutput>> writeFlowToController(InstanceIdentifier<Node> nodeInstanceId,InstanceIdentifier<Table> tableInsatnceId,
				 InstanceIdentifier<Flow> flowPath,Flow flow){
			 LOG.trace("Adding flow to node {}",nodeInstanceId.firstKeyOf(Node.class, NodeKey.class).getId().getValue());
			 final AddFlowInputBuilder builder=new AddFlowInputBuilder(flow);
			 builder.setNode(new NodeRef(nodeInstanceId));
			 builder.setFlowRef(new FlowRef(flowPath));
			 builder.setFlowTable(new FlowTableRef(tableInsatnceId));
			 builder.setTransactionUri(new Uri(flow.getId().getValue()));
			 return salFlowService.addFlow(builder.build());
			 
		 }
		
	}

}
