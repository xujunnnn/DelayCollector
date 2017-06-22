package com.ebupt.vnbo.classes.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.table.TableRead;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.ebupt.vnbo.util.InfluxDBUtil;
/**
 * 
* 类名: ProtocolMonTask.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(监控协议流量信息的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class ProtocolMonTask implements Runnable {
	private static Logger logger=LoggerFactory.getLogger(ProtocolMonTask.class);
	private static final String PROTOCOLTABLE="3";
	private static final long interval=4000;
	private static final String measurement="protocol_load";
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
	public ProtocolMonTask setIsactive(boolean isactive) {
		this.isactive = isactive;
		return this;
	}
	public HashSet<Node> getNodes() {
		return nodes;
	}
	public ProtocolMonTask setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
		return this;
	}

	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	
	@Override
	public String toString() {
		return "ProtocolMonTask [netMonitorMap=" + netMonitorMap + ", isactive=" + isactive + ", nodes=" + nodes
				+ ", tableid=" + tableid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isactive ? 1231 : 1237);
		result = prime * result + ((netMonitorMap == null) ? 0 : netMonitorMap.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		result = prime * result + ((tableid == null) ? 0 : tableid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProtocolMonTask other = (ProtocolMonTask) obj;
		if (isactive != other.isactive)
			return false;
		if (netMonitorMap == null) {
			if (other.netMonitorMap != null)
				return false;
		} else if (!netMonitorMap.equals(other.netMonitorMap))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		if (tableid == null) {
			if (other.tableid != null)
				return false;
		} else if (!tableid.equals(other.tableid))
			return false;
		return true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isactive){
			long timestamp=System.currentTimeMillis();	
			for(Node node:nodes){
				TableRead tableRead=new TableRead();		
				tableRead.setTableid(PROTOCOLTABLE);
				try {
					tableRead=tableRead.read(node.getNode_id());
					
				} catch (ODL_IO_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(tableRead.getFlow()!=null){
					HashSet<FlowEntry> flowEntries=tableRead.getFlow();

				for(FlowEntry flowEntry:flowEntries){
					MonTag monTag=new MonTag();
					monTag.setNode(node.getNode_id());
					String inport;
					if((inport=flowEntry.getMatch().getIn_port())!=null){
						monTag.setInport(inport);
						monTag.setFlowid(flowEntry.getId());
					}
					else{
						continue;
					}
					if(flowEntry.getMatch().getIp_Match()!=null && flowEntry.getMatch().getIp_Match().getIp_protocol()!=null){
						String ipProtocol=flowEntry.getMatch().getIp_Match().getIp_protocol();
						Protocol_Type protocol_Type=Protocol_Type.Valueof(Integer.parseInt(ipProtocol));
						monTag.setProtocol_Type(protocol_Type);
					}
					else {
						monTag.setProtocol_Type(Protocol_Type.UNKNOW);
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
			InfluxDBUtil.put(timestamp,measurement, netMonitorMap);
			System.out.println("protocol_load  Monitoring "+" Thread "+Thread.currentThread().getName());
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("ProtocolMonitorTask running error, error details {} ",e.getMessage());
				e.printStackTrace();
			}
		
		}
		
	}
	public static void main(String []args){
		Thread thread;
		try {
			thread = new Thread(new ProtocolMonTask().setIsactive(true).setNodes(new TopologyServiceImpl().get_access_node()));
			thread.start();
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
