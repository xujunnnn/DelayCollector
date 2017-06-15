package com.ebupt.vnbo.util;

import java.util.HashMap;
import java.util.Map;

import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;

public class FlowUtil {
	private static Map<Node, Long> flowIdMap=new HashMap<>();
	public static void initMap() throws OperationalException{
		TopologyService topologyService=new TopologyServiceImpl();
		for(Node node:topologyService.get_switch()){
			flowIdMap.put(node, (long) 1);
		}
	}
	public static long getFlowId(Node node){
		long id=flowIdMap.get(node);
		long newid=id+1;
		flowIdMap.put(node,newid);
		return id;
	}

}
