package com.enupt.vnbo.servlet.Vtopo;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.vtopo.VGroup;
import com.ebupt.vnbo.classes.vtopo.VLink;
import com.ebupt.vnbo.classes.vtopo.VTopo;
import com.ebupt.vnbo.request.vtopo.VtopoRequest;
import com.squareup.moshi.Json;

public class VtopoTest {
	public static void main(String []args){
		VTopo vTopo=new VTopo();
		VtopoRequest vtopoRequest=new VtopoRequest();
		vtopoRequest.setOperationType(OperationType.QUERRYALL);
		System.out.println(JSON.toJSONString(vtopoRequest));
		VTopo vTopo2=new VTopo().setVtopo_name("v1");
		ArrayList<VTopo> vTopos=new ArrayList<>();
		vTopos.add(new VTopo().setVtopo_name("v1"));
		vtopoRequest.setOperationType(OperationType.QUERRY);
		vtopoRequest.setvTopos(vTopos);
		System.out.println(JSON.toJSONString(vtopoRequest));
		VtopoRequest vtopoRequest2=new VtopoRequest();
		VTopo vTopo3=new VTopo();
		VGroup vGroup=new VGroup().addHost("host:00:00:00:00:00:03").addHost("host:00:00:00:00:00:02").setGroup_id("g1");
		vtopoRequest2.setOperationType(OperationType.ADD);
		VGroup vGroup2=new VGroup().addHost("host:00:00:00:00:00:05").addHost("host:00:00:00:00:00:01").setGroup_id("g2");
		VLink vLink=new VLink();
		vLink.setGroupA("g1").setGroupB("g2");
		vTopo3.addGroup(vGroup).addGroup(vGroup2).addLink(vLink).setVtopo_name("v3");
		vtopoRequest2.setvTopo(vTopo3);
		System.out.println(JSON.toJSONString(vtopoRequest2));
		
		
	}

}
