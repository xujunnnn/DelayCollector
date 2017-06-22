package com.ebupt.vnbo.classes.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.flow.match.Ethernet_destination;
import com.ebupt.vnbo.classes.flow.match.Ethernet_source;
import com.ebupt.vnbo.classes.table.TableRead;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.util.InfluxDBUtil;
/**
 * 
* 类名: CustomizeMonitorTask.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(自定义监控项监控进程) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月9日 <br/>
* 发布版本： V1.0  <br/>
 */
public class CustomizeMonitorTask implements Runnable{
	private static Logger logger=LoggerFactory.getLogger(CustomizeMonitor.class);
	private static final String CUSTOMIZETABLE="1";
	private static final long interval=4000;
	private static final String measurement="customize_load";
	private Map<MonTag, NetStatic> netMonitorMap=new HashMap<>();
	private volatile boolean isactive;
	private HashSet<Node> nodes=new HashSet<Node>();
	private String tableid;
	public Map<MonTag, NetStatic> getNetMonitorMap() {
		return netMonitorMap;
	}
	public void setNetMonitorMap(Map<MonTag, NetStatic> netMonitorMap) {
		this.netMonitorMap = netMonitorMap;
	}
	public boolean isIsactive() {
		return isactive;
	}

	public HashSet<Node> getNodes() {
		return nodes;
	}

	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isactive){
			long timestamp=System.currentTimeMillis();	
			
			for(Node node:nodes){
				TableRead tableRead=new TableRead();		
				tableRead.setTableid(CUSTOMIZETABLE);
				try {
					tableRead=tableRead.read(node.getNode_id());
				} catch (ODL_IO_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HashSet<FlowEntry> flowEntries=tableRead.getFlow();
				if(flowEntries!=null){
				for(FlowEntry flowEntry:flowEntries){
					MonTag monTag=new MonTag();
					monTag.setNode(node.getNode_id());
					if("BaseFlow_0".equals(flowEntry.getId()) || flowEntry.getId().startsWith("#UF$TABLE"))
						continue;
					
					if(flowEntry.getMatch()!=null && flowEntry.getMatch().getEthernet_Match()!=null&&flowEntry.getMatch().getEthernet_Match().getEthernet_source()!=null){
						Ethernet_source source=flowEntry.getMatch().getEthernet_Match().getEthernet_source();
						monTag.setSrcmac(source.getAddress());
					}
					if(flowEntry.getMatch()!=null && flowEntry.getMatch().getEthernet_Match()!=null && flowEntry.getMatch().getEthernet_Match().getEthernet_destination()!=null){
						Ethernet_destination destination=flowEntry.getMatch().getEthernet_Match().getEthernet_destination();
						monTag.setDestmac(destination.getAddress());
					}
					if(flowEntry.getMatch().getIp_Match()!=null && flowEntry.getMatch().getIp_Match().getIp_protocol()!=null){
						String ipProtocol=flowEntry.getMatch().getIp_Match().getIp_protocol();
						Protocol_Type protocol_Type=Protocol_Type.Valueof(Integer.parseInt(ipProtocol));
						monTag.setProtocol_Type(protocol_Type);
					}
					
					if(flowEntry.getMatch().getUdp_source_port()!=null){
						monTag.setUdp_srcport(flowEntry.getMatch().getUdp_source_port());
						}
					if(flowEntry.getMatch().getUdp_destination_port()!=null){
						monTag.setUdp_destport(flowEntry.getMatch().getUdp_destination_port());
					}
					if(flowEntry.getMatch().getTcp_source_port()!=null){
						monTag.setTcp_srcport(flowEntry.getMatch().getTcp_source_port());
					}
					if(flowEntry.getMatch().getTcp_destination_port()!=null){
						monTag.setTcp_destport(flowEntry.getMatch().getTcp_destination_port());						
					}			
					long nowtime=flowEntry.getFlow_Statistic().getDuration().getSecond();
					long nowbyte=flowEntry.getFlow_Statistic().getByte_count();
					long nowpkt=flowEntry.getFlow_Statistic().getPacket_count();
					NetStatic netStatic=new NetStatic();
					netStatic.setBytecount(nowbyte).setPacketcount(nowpkt).setTimestamp(nowtime);
					if(netMonitorMap.get(monTag)!=null){					
						long oldbyte=netMonitorMap.get(monTag).getBytecount();
						long oldpkt=netMonitorMap.get(monTag).getPacketcount();
						long oldtime=netMonitorMap.get(monTag).getTimestamp();
						if(nowbyte < oldbyte){
							nowbyte=oldbyte+nowbyte;
						}
						long bytespeed=0;
						long pktspeed=0;
						if((nowtime-oldtime)!=0){
							 bytespeed=(nowbyte-oldbyte)/(nowtime-oldtime);
 						     pktspeed=(nowpkt-oldpkt)/(nowtime-oldtime);
						}
 						netStatic.setPacketspeed(pktspeed).setBytespeed(bytespeed);				
					}
					else {
						netStatic.setPacketspeed(0).setBytespeed(0);
					}
					this.netMonitorMap.put(monTag, netStatic);
				}
					
			}
			}
			InfluxDBUtil.put(timestamp,measurement,netMonitorMap);
			System.out.println("customize  Monitoring "+" Thread "+Thread.currentThread().getName());
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("CustomizeMonitorTask running error, error details {} ",e.getMessage());
				e.printStackTrace();
			}
		
		}
	}
	public CustomizeMonitorTask setIsactive(boolean isactive) {
		this.isactive = isactive;
		return this;
	}
	public CustomizeMonitorTask setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
		return this;
	}
}
