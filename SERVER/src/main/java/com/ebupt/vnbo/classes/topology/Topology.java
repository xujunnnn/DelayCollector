package com.ebupt.vnbo.classes.topology;

import java.util.HashSet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;



/**
 * 
 * @author xu
 *
 */
public class Topology implements Operational {
	@JSONField(name="topology-id")
	private String topology_id;
	@JSONField(name="node")
	public HashSet<Node> nodes=new HashSet<Node>();
	@JSONField(name="link")
	public HashSet<Link> links=new HashSet<Link>();
	public String getTopology_id() {
	
		return topology_id;
	}
	public void setTopology_id(String topology_id) {
		this.topology_id = topology_id;
	}
	public HashSet<Node> getNodes() throws OperationalException {
		return nodes;
	}
	public void setNodes(HashSet<Node> nodes) {
		
		this.nodes = nodes;
	}
	public HashSet<Link> getLinks() throws OperationalException {
		return links;
	}
	public void setLinks(HashSet<Link> links) {
		this.links = links;
	}

	public Topology read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"//network-topology:network-topology";
		String []result=HttpUtil.Get_request(url);
		String responsecode=result[0];
		String json=result[1];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new OperationalException("failed to read the topologu from the controller");
		JSONObject jsonObject=JSONObject.parseObject(json);
		JSONObject topojson=jsonObject.getJSONObject("network-topology").getJSONArray("topology").getJSONObject(0);
		Topology topology=JSONObject.parseObject(topojson.toJSONString(), Topology.class);
		return topology;
		
	}	

}
