package com.ebupt.vnbo.classes.topology;

import com.alibaba.fastjson.annotation.JSONField;

public class Destination {
	@JSONField(name="dest-node")
	private String dest_node;
	@JSONField(name="dest-tp")
	private String dest_tp;
	public String getDest_node() {
		return dest_node;
	}
	public void setDest_node(String dest_node) {
		this.dest_node = dest_node;
	}
	public String getDest_tp() {
		return dest_tp;
	}
	public void setDest_tp(String dest_tp) {
		this.dest_tp = dest_tp;
	}

}
