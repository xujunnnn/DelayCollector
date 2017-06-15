package com.ebupt.vnbo.classes.topology;
import com.alibaba.fastjson.annotation.JSONField;
public class Source {
	@JSONField(name="source-tp")
	private String source_tp;
	@JSONField(name="source-node")
	private String source_node;
	public String getSource_tp() {
		return source_tp;
	}
	public String getSource_node() {
		return source_node;
	}
	public void setSource_tp(String source_tp) {
		this.source_tp = source_tp;
	}
	public void setSource_node(String source_node) {
		this.source_node = source_node;
	}

}
