/*
 * Copyright (c) 2016 Inocybe and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.opendaylight.l2switch.arphandler.core.PacketDispatcher;
import org.opendaylight.l2switch.arphandler.inventory.InventoryReader;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.delaycollector.config.rev140528.DelaycollectorConfig;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.KnownIpProtocols;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.TransmitPacketInputBuilder;

import delaycollector.impl.util.IPv4;

import org.opendaylight.controller.liblldp.ConstructionException;
import org.opendaylight.controller.liblldp.EtherTypes;
import org.opendaylight.controller.liblldp.Ethernet;
import org.opendaylight.controller.liblldp.EthernetAddress;
import org.opendaylight.controller.liblldp.PacketException;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
public class Delay_Sender_Task implements Runnable{
	private final DelaycollectorConfig delaycollectorConfig;
	private final PacketProcessingService packetProcessingService;
	private final DataBroker dataservice;
	public Delay_Sender_Task(DelaycollectorConfig delayCollectorConfig,PacketProcessingService packetProcessingService,DataBroker dataSerivice){
		this.delaycollectorConfig=delayCollectorConfig;
		this.packetProcessingService=packetProcessingService;
		this.dataservice=dataSerivice;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		PacketDispatcher packetDispatcher=new PacketDispatcher();
		packetDispatcher.setPacketProcessingService(packetProcessingService);
		InventoryReader inventoryReader=new InventoryReader(dataservice);
		inventoryReader.setRefreshData(true);
		inventoryReader.readInventory(true);
		
		while(delaycollectorConfig.isIsActive()){
			//generate a ipv4 packet
			IPv4 iPv4=new IPv4();
			iPv4.setTtl((byte)1).setProtocol((byte)KnownIpProtocols.Experimentation1.getIntValue());
			try {
			iPv4.setSourceAddress(InetAddress.getByName("0.0.0.1")).setDestinationAddress(InetAddress.getByName("0.0.0.2"));
			//generate a ethernet packet
			Ethernet ethernet=new Ethernet();
			EthernetAddress srcMac=new EthernetAddress(new byte[]{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xee});
			EthernetAddress destMac=new EthernetAddress(new byte[]{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xef});
			ethernet.setSourceMACAddress(srcMac.getValue()).setDestinationMACAddress(destMac.getValue());
			ethernet.setEtherType(EtherTypes.IPv4.shortValue());
			ethernet.setPayload(iPv4);
			TransmitPacketInputBuilder transmitPacketInputBuilde=new TransmitPacketInputBuilder();		
			packetDispatcher.setInventoryReader(inventoryReader);
			HashMap<String, NodeConnectorRef> nodeConnectorMap=inventoryReader.getControllerSwitchConnectors();
			for(String nodeid:nodeConnectorMap.keySet()){		
				packetDispatcher.floodPacket(nodeid, ethernet.serialize(), nodeConnectorMap.get(nodeid), null,true);    
			}
			} catch (ConstructionException | UnknownHostException | PacketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(delaycollectorConfig.getQuerryDelay()*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
