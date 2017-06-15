package com.ebupt.vnbo.classes.topology;

import com.alibaba.fastjson.annotation.JSONField;

public class Host_tracker_service_address {
	private String id;
	private String mac;
	@JSONField(name="last-seen")
	private String last_seen;
	private String ip;
	@JSONField(name="first-seen")
	private String first_seen;
	
	
	public String getId() {
		return id;
	}
	public String getMac() {
		return mac;
	}
	public String getLast_seen() {
		return last_seen;
	}
	public String getIp() {
		return ip;
	}
	public String getFirst_seen() {
		return first_seen;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setLast_seen(String last_seen) {
		this.last_seen = last_seen;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setFirst_seen(String first_seen) {
		this.first_seen = first_seen;
	}

}
