package com.ebupt.vnbo.classes.topology;

import java.util.HashSet;

public class Switch {
	 private String node_id;
	 private HashSet<Termination_point> ports=new HashSet<Termination_point>();
	public HashSet<Termination_point> getPorts() {
		return ports;
	}
	public Switch setPorts(HashSet<Termination_point> ports) {
		this.ports = ports;
		return this;
	}
	public String getNode_id() {
		return node_id;
	}
	public Switch setNode_id(String node_id) {
		this.node_id = node_id;
		return this;
	}
	 

}
