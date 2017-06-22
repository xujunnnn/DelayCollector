package com.ebupt.vnbo.serviceImpl.Initialize;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.flow.instruction.Instruction;
import com.ebupt.vnbo.classes.flow.instruction.Instructions;
import com.ebupt.vnbo.classes.flow.match.Match;
import com.ebupt.vnbo.classes.monitor.CustomizeMonitorTask;
import com.ebupt.vnbo.classes.monitor.IpMonitorTask;
import com.ebupt.vnbo.classes.monitor.LatencyMonitorTask;
import com.ebupt.vnbo.classes.monitor.ProtocolMonTask;
import com.ebupt.vnbo.classes.monitor.QueueMonitorTask;
import com.ebupt.vnbo.classes.table.Table;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.classes.topology.Termination_point;
import com.ebupt.vnbo.request.Request;
import com.ebupt.vnbo.request.initialize.InitRequest;
import com.ebupt.vnbo.service.initialize.InitService;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.ebupt.vnbo.util.FlowUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitServiceImpl implements InitService{
	private static Thread PROTOCOL_MON_THREAD;
	private static Thread IP_MON_THREAD;
	private static Thread LATENCY_MON_THREAD;
	private static Thread CUSTOMIZE_MON_THREAD;
	private static Thread QUEUE_MON_THREAD;
	private static volatile boolean isMonFlowInstalled=false;
	private static Logger logger=LoggerFactory.getLogger(InitServiceImpl.class);
	
	@Override
	public JSONObject resolve(Request request) {
		
		// TODO Auto-generated method stub
		JSONObject result=new JSONObject();
		InitRequest initRequest=(InitRequest) request;
		logger.info("receive InitRequest {}",initRequest.toString());
		if(initRequest.getOperationType()==OperationType.INIT){
			if(initRequest.getTag()==Tag.BASE)
				return initBaseFlow();
			if(initRequest.getTag()==Tag.MONITOR)
				return initMonitor();
			if(initRequest.getTag()==Tag.START)
				return startMonitor();
			result.put("status", -1);
			result.put("description", "error tag");		
			return result;
		}
		result.put("status", -1);
		result.put("description", "error operationType ");		
		return result;
		
	}



	@SuppressWarnings("finally")
	@Override
	public JSONObject initBaseFlow()  {
		// TODO Auto-generated method stub
		
		JSONObject result=new JSONObject();
		TopologyService topologyService=new TopologyServiceImpl();
		try {
			for(Node node:topologyService.get_switch()){
			FlowEntry flow=new FlowEntry();
			Match match=new Match();
			Instructions instructions=new Instructions();
			Instruction instruction=new Instruction().Set_Go_To_Table_Id(CUSTOMIZETABLE).setOrder("0");
			instructions.addInstruction(instruction);
			/*
			 * send flow gototable 1
			 */
			flow.setId("0")
				.setFlow_name("initflow"+flow.getId())
				.setCookie(String.valueOf(flow.getId().hashCode()))
				.setHard_timeout("0")
				.setIdle_timeout("0")
				.setPriority(LOWPRIORITY)
				.setTable_id(QOSFLOWTABLE)
				.setMatch(match)
				.setInstructions(instructions)
				.send(node.getNode_id());
			Instructions instructions2=new Instructions();
			Instruction instruction2=new Instruction().Set_Go_To_Table_Id(MONFLOWTABLE).setOrder("0");
			instructions2.addInstruction(instruction2);
			/*
			 * send flow gototable 5
			 */
			flow.setId("BaseFlow_"+"0")
				.setFlow_name("initflow"+flow.getId())
				.setCookie(String.valueOf(flow.getId().hashCode()))
				.setInstructions(instructions2)
				.setTable_id(CUSTOMIZETABLE)
				.setHard_timeout("0")
				.setIdle_timeout("0")
				.setPriority(LOWPRIORITY)
				.setMatch(match)
				.send(node.getNode_id());
			Instructions instructions3=new Instructions();
			Instruction instruction3=new Instruction().Set_Go_To_Table_Id(VTNFLOWTABLE).setOrder("0");
			instructions3.addInstruction(instruction3);
			flow.setId("BaseFlow_"+"0")
			.setFlow_name("initflow"+flow.getId())
			.setCookie(String.valueOf(Math.abs(flow.getId().hashCode())))
			.setInstructions(instructions3)
			.setTable_id(MONFLOWTABLE)
			.setHard_timeout("0")
			.setIdle_timeout("0")
			.setPriority(LOWPRIORITY)
			.setMatch(match)
			.send(node.getNode_id());
			}
			
		
			FlowUtil.initMap();
			logger.info("succsee to init baseFlow");
			result.put("Status", 0);
			result.put("description", "success to init baseFlow");
			
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to init base flow, error detail {}",e.getMessage());
			result.put("Status", -1);
			result.put("description", "init error, can not read the openflow topology");
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to init base flow, error detail {}",e.getMessage());
			result.put("Status", -1);
			result.put("description", "init error, failed to send the initflows");
		}
		finally{
			return result;
		}
		
	}
	
	@SuppressWarnings("finally")
	@Override
	public JSONObject initMonitor(){
		// TODO Auto-generated method stub
		
		JSONObject result=new JSONObject();
		HashSet<Protocol_Type> protocol_Types=new HashSet<>();
		protocol_Types.add(Protocol_Type.ICMP);
		protocol_Types.add(Protocol_Type.TCP);
		protocol_Types.add(Protocol_Type.UDP);
		protocol_Types.add(Protocol_Type.UNKNOW);
		TopologyService topologyService=new TopologyServiceImpl();
		try {
			if(isMonFlowInstalled==false){
				for(Node node:topologyService.get_switch()){
					Table table=new Table().setTableid(MONFLOWTABLE);
					table.remove(node.getNode_id());
				}
			for(Node node:topologyService.get_switch()){
			Table table=new Table().setTableid(MONFLOWTABLE);
			table.remove(node.getNode_id());
			Instructions instructions=new Instructions();
			Instruction instruction=new Instruction().Set_Go_To_Table_Id(VTNFLOWTABLE).setOrder("0");
			instructions.addInstruction(instruction);
			//鑾峰彇涓庝富鏈虹浉杩炴帴鐨勭鍙�
			HashSet<Termination_point> termination_points=topologyService.get_ports_to_host(node);
			for(Termination_point t:termination_points){
				System.out.println(t.getTp_id());
				for(Protocol_Type p:protocol_Types){
					FlowEntry flow2=new FlowEntry();
					Match match2=new Match();
					if(!(Protocol_Type.UNKNOW==p)){
					 match2.Set_Ip_Match(String.valueOf(p.value()), null, null,null)
									.setIn_port(t.getTp_id())
									.Set_Mac_Match(null, null, "2048");
					 flow2.setPriority(HIGHPRIORITY);
					}
					else{
					 match2.setIn_port(t.getTp_id());	
					 flow2.setPriority(MIDPRIORITY);
					}
					flow2.setId("MonitorFlow"+Long.toString(FlowUtil.getFlowId(node)))
						 .setFlow_name("MonitorFlow"+flow2.getId())
						 .setCookie(String.valueOf(Math.abs(flow2.getId().hashCode())))
						 .setIdle_timeout("0")
						 .setHard_timeout("0")
						 .setTable_id(MONFLOWTABLE)
						 .setInstructions(instructions)
						 .setMatch(match2)
						 .send(node.getNode_id());
					System.out.println(JSON.toJSON(flow2));
					
				}
			}
 }			
			}
			initBaseFlow();
			ProtocolMonTask protocolMonTask=new ProtocolMonTask().setIsactive(true);
			IpMonitorTask ipMonitorTask=new IpMonitorTask().setIsactive(true);
			LatencyMonitorTask latencyMonitorTask=new LatencyMonitorTask().setIsactive(true);
			CustomizeMonitorTask customizeMonitorTask=new CustomizeMonitorTask().setIsactive(true);
			QueueMonitorTask queueMonitorTask=new QueueMonitorTask().setIsactive(true);
			protocolMonTask.setNodes(topologyService.get_access_node());
			ipMonitorTask.setNodes(topologyService.get_access_node());
			customizeMonitorTask.setNodes(topologyService.get_access_node());
			queueMonitorTask.setNodes(topologyService.get_access_node());
			PROTOCOL_MON_THREAD=new Thread(protocolMonTask);
			PROTOCOL_MON_THREAD.start();
			IP_MON_THREAD=new Thread(ipMonitorTask);
			IP_MON_THREAD.start();
			LATENCY_MON_THREAD=new Thread(latencyMonitorTask);
			LATENCY_MON_THREAD.start();
			CUSTOMIZE_MON_THREAD=new Thread(customizeMonitorTask);
			CUSTOMIZE_MON_THREAD.start();
			QUEUE_MON_THREAD=new Thread(queueMonitorTask);
			QUEUE_MON_THREAD.start();	
			isMonFlowInstalled=true;
			logger.info("success to init Monitor flow");
			result.put("Status", 0);
			result.put("description", "success to init MonitorFlow");
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to init monitor flow, error detail {}",e.getMessage());
			result.put("Status", -1);
			result.put("description", "init error, can not read the openflow topology");
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to init monitor flow, error detail {}",e.getMessage());
			result.put("Status", -1);
			result.put("description", "init error, failed to send the Monflows");
		}
		finally{
			return result;
		}
	}
	
	public JSONObject startMonitor(){
		JSONObject result=new JSONObject();
		TopologyService topologyService=new TopologyServiceImpl();
		/**寮�鍚洃鎺ц繘绋�*/
		ProtocolMonTask protocolMonTask=new ProtocolMonTask().setIsactive(true);
		IpMonitorTask ipMonitorTask=new IpMonitorTask().setIsactive(true);
		LatencyMonitorTask latencyMonitorTask=new LatencyMonitorTask().setIsactive(true);
		try {
			protocolMonTask.setNodes(topologyService.get_access_node());
			ipMonitorTask.setNodes(topologyService.get_access_node());
			PROTOCOL_MON_THREAD=new Thread(protocolMonTask);
			PROTOCOL_MON_THREAD.start();
			IP_MON_THREAD=new Thread(ipMonitorTask);
			IP_MON_THREAD.start();
			LATENCY_MON_THREAD=new Thread(latencyMonitorTask);
			LATENCY_MON_THREAD.start();
			result.put("Status", 0);
			result.put("description", "success to start  Monitor");
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "failed to start monitor");
		}
		finally {
			return result;
		}

	}
	
	public static void main(String []args){
		InitServiceImpl impl=new InitServiceImpl();
		impl.initBaseFlow();
		impl.initMonitor();
	}



}
