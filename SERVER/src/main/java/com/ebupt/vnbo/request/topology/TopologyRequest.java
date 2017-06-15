package com.ebupt.vnbo.request.topology;

import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.Request;

public class TopologyRequest implements Request {
	private OperationType operationType;
	private Tag tag;
	private String startIp;
	private String endIp;
	private String idleIp;
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getIdleIp() {
		return idleIp;
	}
	public void setIdleIp(String idleIp) {
		this.idleIp = idleIp;
	}

}
