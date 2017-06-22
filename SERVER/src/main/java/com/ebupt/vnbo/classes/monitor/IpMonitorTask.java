package com.ebupt.vnbo.classes.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.flow.match.Ethernet_destination;
import com.ebupt.vnbo.classes.flow.match.Ethernet_source;
import com.ebupt.vnbo.classes.table.TableRead;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.ebupt.vnbo.util.InfluxDBUtil;
/**
 * 
* 类名: IpMonitorTask.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: (Ip监控多线程实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class IpMonitorTask implements Runnable{
	private static Logger logger=LoggerFactory.getLogger(IpMonitorTask.class);
	private static final String IPTABLE="5";
	private static final String IPMEASUREMENT="ip_load";
	private volatile boolean isactive;
	//private NetMonitorMap netMonitorMap=new NetMonitorMap();
	private HashSet<Node> nodes=new HashSet<Node>();
	//每4秒进行一次轮询
	private static final long interval=4000;
	private String tableid;
	public boolean isIsactive() {
		return isactive;
	}
	public IpMonitorTask setIsactive(boolean isactive) {
		this.isactive = isactive;
		return this;
	}
	public HashSet<Node> getNodes() {
		return nodes;
	}
	public IpMonitorTask setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
		return this;
	}
	public String getTableid() {
		return tableid;
	}
	public IpMonitorTask setTableid(String tableid) {
		this.tableid = tableid;
		return this;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		Map<MonTag, NetStatic> netMonitorMap=new HashMap<>();
		while(isactive){
			long timestamp=System.currentTimeMillis();		
		for(Node node:nodes){
			TableRead tableRead=new TableRead();
			tableRead.setTableid(IPTABLE);
			try {
				for(FlowEntry flowEntry:tableRead.read(node.getNode_id()).getFlow()){
					MonTag monTag=new MonTag();
					if(flowEntry.getMatch()!=null && flowEntry.getMatch().getEthernet_Match()!=null && flowEntry.getMatch().getIn_port()!=null){
						String in_port=flowEntry.getMatch().getIn_port();
						
						monTag.setInport(in_port);
					}
					else{
						continue;
					}
					if(flowEntry.getMatch()!=null && flowEntry.getMatch().getEthernet_Match()!=null&&flowEntry.getMatch().getEthernet_Match().getEthernet_source()!=null){
						Ethernet_source source=flowEntry.getMatch().getEthernet_Match().getEthernet_source();
						monTag.setSrcmac(source.getAddress());
					}
					if(flowEntry.getMatch()!=null && flowEntry.getMatch().getEthernet_Match()!=null && flowEntry.getMatch().getEthernet_Match().getEthernet_destination()!=null){
						Ethernet_destination destination=flowEntry.getMatch().getEthernet_Match().getEthernet_destination();
						monTag.setDestmac(destination.getAddress());
					}		
					NetStatic netStatic=new NetStatic();
					long nowbyte=flowEntry.getFlow_Statistic().getByte_count();
					long nowpkt=flowEntry.getFlow_Statistic().getPacket_count();
					long nowtime=flowEntry.getFlow_Statistic().getDuration().getSecond();	
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

					netMonitorMap.put(monTag, netStatic);	
					}
			} catch (NumberFormatException | ODL_IO_Exception e) {
				// TODO Auto-generated catch block
				logger.error("IpMonitorTask running error, error details {} ",e.getMessage());
				e.printStackTrace();
			}		
		  }
		InfluxDBUtil.put(timestamp,IPMEASUREMENT,netMonitorMap);
		System.out.println("Ip_load><><><>><><><><>>< Monitoring"+"  thread= "+Thread.currentThread().getName());
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
		}
	
	public static void main(String []args){
		Thread thread;
		try {
			thread = new Thread(new IpMonitorTask().setIsactive(true).setNodes(new TopologyServiceImpl().get_access_node()));
			thread.start();
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

		
		

		


