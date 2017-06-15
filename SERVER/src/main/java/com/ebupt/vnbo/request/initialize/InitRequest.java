package com.ebupt.vnbo.request.initialize;

import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.Request;

public class InitRequest implements Request {
	private OperationType operationType;
	private Tag tag;
	
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
	
}
