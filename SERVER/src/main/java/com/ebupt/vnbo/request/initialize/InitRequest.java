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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InitRequest other = (InitRequest) obj;
		if (operationType != other.operationType)
			return false;
		if (tag != other.tag)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InitRequest [operationType=" + operationType + ", tag=" + tag + "]";
	}
	
	
}
