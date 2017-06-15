package com.enupt.vnbo.servlet.topology;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.topology.TopologyRequest;

public class TopoTest {
	public static void main(String []args){
		TopologyRequest topologyRequest=new TopologyRequest();
		topologyRequest.setOperationType(OperationType.QUERRY);
		topologyRequest.setTag(Tag.ABLEHOST);
		System.out.println(JSON.toJSONString(topologyRequest));
		
		TopologyRequest topologyRequest2=new TopologyRequest();
		topologyRequest2.setOperationType(OperationType.DISCOVER);
		topologyRequest2.setIdleIp("10.0.0.0");
		topologyRequest2.setStartIp("10.0.0.1");
		topologyRequest2.setEndIp("10.0.0.20");
		System.out.println(JSON.toJSONString(topologyRequest2));
	}

}
