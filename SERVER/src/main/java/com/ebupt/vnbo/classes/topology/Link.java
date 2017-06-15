package com.ebupt.vnbo.classes.topology;

import com.alibaba.fastjson.annotation.JSONField;

public class Link {
	@JSONField(name="link-id")
	private String link_id;
	private Destination destination;
	private Source source;
	
	
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}

}
