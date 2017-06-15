package com.enupt.vnbo.servlet.qos;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.qos.QosEntry;
import com.ebupt.vnbo.request.qos.QosRequest;

public class QosRequestTest {
	public static void main(String []args){
		QosRequest qosRequest=new QosRequest();
		qosRequest.setOperationType(OperationType.ADD);
		qosRequest.setQosEntry(new QosEntry().setQos_id("74")
				.setDrop_rate("400")
				.setIp_Protocol(Protocol_Type.ICMP)
				);
		System.out.println(JSON.toJSONString(qosRequest));
		ArrayList<QosEntry> qosEntries=new ArrayList<>();
		qosEntries.add(new QosEntry().setQos_id("26"));
		qosEntries.add(new QosEntry().setQos_id("27"));
		qosRequest.setOperationType(OperationType.QUERRY);
		qosRequest.setQosEntries(qosEntries);
		System.out.println(JSON.toJSONString(qosRequest));
		qosRequest.setOperationType(OperationType.REMOVE);
		System.out.println(JSON.toJSONString(qosRequest));
		QosRequest qosRequest2=new QosRequest();
		qosRequest2.setOperationType(OperationType.ADD);
		QosEntry qosEntry=new QosEntry().setQos_id("qos1")
				.setSrchost("10.0.0.3/32")
				.setDesthost("10.0.0.4/32")
				.setIp_Protocol(Protocol_Type.UDP)
				.setUdp_srcPort("100")
				.setUdp_destPort("12")
				.setTcp_destPort("100")
				.setTcp_srcPort("30")
				.setDrop_rate("122")
				.setQueue_id("1");
		qosRequest2.setQosEntry(qosEntry);
		System.out.println(JSON.toJSON(qosRequest2));
				
	}

}
