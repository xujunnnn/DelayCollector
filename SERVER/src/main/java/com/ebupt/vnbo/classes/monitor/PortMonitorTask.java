package com.ebupt.vnbo.classes.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.node_static.NodeStatic;
import com.ebupt.vnbo.classes.node_static.Node_Connector_Static;
import com.ebupt.vnbo.classes.node_static.Node_connector;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.ebupt.vnbo.util.InfluxDBUtil;
/**
 * 
* ����: PortMonitorTask.java <br/>
* ���� : com.ebupt.vnbo.classes.monitor <br/>
* ��ϸ����: TODO(���ڼ�ض˿���Ϣ�Ķ��߳�����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��11�� <br/>
* �����汾�� V1.0  <br/>
 */
public class PortMonitorTask implements Runnable {
	//ÿ4s����һ����ѯ
	private static final long interval=4000;
	private static String PORTMEASUREMENT="port_load";
	private Map<MonTag, NetStatic> netMonitorMap=new HashMap<>();
	private volatile boolean isactive;
	private HashSet<Node> nodes=new HashSet<Node>();
	public Map<MonTag, NetStatic> getNetMonitorMap() {
		return netMonitorMap;
	}

	public void setNetMonitorMap(Map<MonTag, NetStatic> netMonitorMap) {
		this.netMonitorMap = netMonitorMap;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public HashSet<Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
	}

	public static long getInterval() {
		return interval;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isactive){
			long timestamp=System.currentTimeMillis();
			for(Node node:nodes){
				NodeStatic nodeStatic=new NodeStatic();
				nodeStatic.setId(node.getNode_id());
				try {
					NodeStatic nodeStatic2=(NodeStatic)nodeStatic.read(nodeStatic.getId());
					for(Node_connector node_connector:nodeStatic2.getNode_connector()){
						MonTag monTag=new MonTag().setInport(node_connector.getId());
						Node_Connector_Static node_Connector_Static=node_connector.getNode_Connector_Static();
						long nowbyte=node_Connector_Static.getBytes().getReceived();
						long nowpacket=node_Connector_Static.getPackets().getReceived();
						long nowtime=node_Connector_Static.getDuration().getSecond();
						NetStatic netStatic=new NetStatic().setBytecount(nowbyte)
														    .setPacketcount(nowpacket)
														    .setTimestamp(nowtime);
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
								if(monTag.getInport().equals("openflow:1:4"))
									System.out.println("now");
								 bytespeed=(nowbyte-oldbyte)/(nowtime-oldtime);				 
							     pktspeed=(nowpacket-oldpkt)/(nowtime-oldtime);
							}
							netStatic.setPacketspeed(pktspeed).setBytespeed(bytespeed);				
						}
						netMonitorMap.put(monTag, netStatic);
						
					
					}
				} catch (ODL_IO_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//д��influx���ݿ�
			InfluxDBUtil.put(timestamp, PORTMEASUREMENT,netMonitorMap);
			//System.out.println("Port<><><><><><>Monitoring");
			//��ʱ
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String []args){
		TopologyService topologyService=new TopologyServiceImpl();
		PortMonitorTask portMonitorTask=new PortMonitorTask();
		portMonitorTask.setIsactive(true);
		try {
			portMonitorTask.setNodes(topologyService.get_access_node());
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread thread=new Thread(portMonitorTask);
		thread.start();
	}

}
