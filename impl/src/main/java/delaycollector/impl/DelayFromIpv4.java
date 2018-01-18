/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package delaycollector.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.opendaylight.controller.liblldp.PacketException;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.IpVersion;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.basepacket.rev140528.packet.chain.grp.PacketChain;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.basepacket.rev140528.packet.chain.grp.packet.chain.packet.RawPacket;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.delaycollector.config.rev140528.DelaycollectorConfig;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ethernet.rev140528.ethernet.packet.received.packet.chain.packet.EthernetPacket;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.Ipv4PacketListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.Ipv4PacketReceived;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.KnownIpProtocols;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ipv4.rev140528.ipv4.packet.received.packet.chain.packet.Ipv4Packet;

import delaycollector.impl.util.Ipv4Option;

public class DelayFromIpv4 implements Ipv4PacketListener {
	private DelaycollectorConfig config;
	private Map<String, Long> delayMap;
	public  DelayFromIpv4(DelaycollectorConfig config,Map<String, Long> delayMap) {
		// TODO Auto-generated constructor stub
		this.config=config;
		this.delayMap=delayMap;
	//	if(config.getInfluxdbAddress()!=null)
	//	influxDB=InfluxDBFactory.connect(config.getInfluxdbAddress());
	}
	@Override
	public void onIpv4PacketReceived(Ipv4PacketReceived packetReceived) {
		// TODO Auto-generated method stub
		if (packetReceived == null || packetReceived.getPacketChain() == null) {
            return;
        }

        RawPacket rawPacket = null;
        EthernetPacket ethernetPacket = null;
        Ipv4Packet ipv4Packet = null;
        for (PacketChain packetChain : packetReceived.getPacketChain()) {
            if (packetChain.getPacket() instanceof RawPacket) {
                rawPacket = (RawPacket) packetChain.getPacket();
            } else if (packetChain.getPacket() instanceof EthernetPacket) {
                ethernetPacket = (EthernetPacket) packetChain.getPacket();
            } else if (packetChain.getPacket() instanceof Ipv4Packet) {
                ipv4Packet = (Ipv4Packet) packetChain.getPacket();
            }
        }
        if (rawPacket == null || ethernetPacket == null || ipv4Packet == null) {
            return;
        }
        if(ipv4Packet.getProtocol()!=KnownIpProtocols.Experimentation1){
        	return;
        }
        Ipv4Option ipv4Option=new Ipv4Option();
        try {
               ipv4Option=(Ipv4Option)ipv4Option.deserialize(ipv4Packet.getIpv4Options(),0,0);
               String ncId = rawPacket.getIngress().getValue().firstIdentifierOf(NodeConnector.class).firstKeyOf(NodeConnector.class, NodeConnectorKey.class).getId().getValue();
        
        	long delay=cacluateDelay(ipv4Option);
        	delayMap.put(ncId, delay);
       // 	Save(System.currentTimeMillis(),ncId,delay);
        //	System.out.println(ncId+"<><><><><><>"+delay);
        } catch (PacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public long cacluateDelay(Ipv4Option ipv4Option){
		long time1=ipv4Option.getFsecond();
		long time2=ipv4Option.getFNanosecond();
		long time3=ipv4Option.getSsecond();
		long time4=ipv4Option.getSNanosecond();
		long delay=(time3-time1)*1000000000+(time4-time2);
		return delay;
	}

	
	
	

}
