package com.ebupt.vnbo.classes.monitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.node_static.NodeStatic;
import com.ebupt.vnbo.classes.node_static.Node_connector;
import com.ebupt.vnbo.classes.node_static.Queue;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.daoImpl.TopoInfo.TopoInfoDaoImpl;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.ebupt.vnbo.util.InfluxDBUtil;

/**
 * 
* 类名: QueueMonitorTask.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(队列监控多线程实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月19日 <br/>
* 发布版本： V1.0  <br/>
 */
public class QueueMonitorTask implements Runnable{
	private HashSet<Node> nodes;
	private long interval=4000;
	private static final String measurement="queue_load";
	private Map<MonTag, NetStatic> netMonitorMap=new HashMap<>();
	private volatile boolean isactive;
	
	public HashSet<Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public QueueMonitorTask setIsactive(boolean isactive) {
		this.isactive = isactive;
		return this;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isactive=true){
			long timestamp=System.currentTimeMillis();
			for(Node node:nodes){
				NodeStatic nodeStatic=new NodeStatic();
				nodeStatic.setId(node.getNode_id());
				try {
					nodeStatic=(NodeStatic)nodeStatic.read(node.getNode_id());
					for(Node_connector node_connector:nodeStatic.getNode_connector()){
						if(node_connector.getQueues()!=null){
						for(Queue queue:node_connector.getQueues()){
							MonTag monTag=new MonTag().setNode(node.getNode_id()).setInport(node_connector.getId());
							monTag.setQueueid(queue.getQueue_id());
							NetStatic netStatic=new NetStatic();
							long nowbyte=queue.getQueue_Static().getTransmitted_bytes();
							long nowpkt=queue.getQueue_Static().getTransmitted_packets();
							long nowtime=queue.getQueue_Static().getDuration().getSecond();
							netStatic.setBytecount(nowbyte);
							netStatic.setBytespeed(nowpkt);
							netStatic.setTimestamp(nowtime);
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
				} catch (ODL_IO_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			InfluxDBUtil.put(timestamp,measurement,netMonitorMap);
			System.out.println("queue  Monitoring "+" Thread "+Thread.currentThread().getName());
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
		QueueMonitorTask queueMonitorTask=new QueueMonitorTask();
		try {
			queueMonitorTask.setNodes(topologyService.get_access_node());
			queueMonitorTask.setIsactive(true);
			Thread thread=new Thread(queueMonitorTask);
			thread.start();
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
