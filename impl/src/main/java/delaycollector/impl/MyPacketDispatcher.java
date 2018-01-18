/*
 * Copyright (c) 2016 Inocybe and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl;

import java.util.List;
import org.opendaylight.l2switch.arphandler.core.PacketDispatcher;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delaycollector.impl.topo.AsyncInventory;

public class MyPacketDispatcher extends PacketDispatcher{
	 private final static Logger LOG = LoggerFactory.getLogger(PacketDispatcher.class);
	 private AsyncInventory inventoryReader;
	public AsyncInventory getInventoryReader() {
		return inventoryReader;
	}
	public void setInventoryReader(AsyncInventory inventoryReader) {
		this.inventoryReader = inventoryReader;
	}
	/**
     * Floods the packet.
     *
     * @param payload
     *            The payload to be sent.
     * @param origIngress
     *            The NodeConnector where the payload came from.
     */
    public void floodPacket(String nodeId, byte[] payload, NodeConnectorRef origIngress,
            NodeConnectorRef controllerNodeConnector,boolean all) {

        List<NodeConnectorRef> nodeConnectors = inventoryReader.readSwitchNodeConnectors().get(nodeId);

        if (nodeConnectors == null) {
            refreshInventoryReader();
            nodeConnectors = inventoryReader.readSwitchNodeConnectors().get(nodeId);
            if (nodeConnectors == null) {
                LOG.info("Cannot flood packets, as inventory doesn't have any node connectors for node {}", nodeId);
                return;
            }
        }
        for (NodeConnectorRef ncRef : nodeConnectors) {
            String ncId = ncRef.getValue().firstIdentifierOf(NodeConnector.class)
                    .firstKeyOf(NodeConnector.class, NodeConnectorKey.class).getId().getValue();
            // Don't flood on discarding node connectors & origIngress
            if (!ncId.equals(origIngress.getValue().firstIdentifierOf(NodeConnector.class)
                    .firstKeyOf(NodeConnector.class, NodeConnectorKey.class).getId().getValue())) {
                sendPacketOut(payload, origIngress, ncRef);
            }
        }
    }
    private void refreshInventoryReader() {
        inventoryReader.setRefreshData(true);
        inventoryReader.readInventory();
    }

}
