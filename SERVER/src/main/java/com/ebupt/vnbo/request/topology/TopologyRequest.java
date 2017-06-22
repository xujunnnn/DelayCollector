package com.ebupt.vnbo.request.topology;

import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.Request;
/**
 * 
* ����: TopologyRequest.java <br/>
* ���� : com.ebupt.vnbo.request.topology <br/>
* ��ϸ����: TODO(������������ʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��16�� <br/>
* �����汾�� V1.0  <br/>
 */
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endIp == null) ? 0 : endIp.hashCode());
		result = prime * result + ((idleIp == null) ? 0 : idleIp.hashCode());
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((startIp == null) ? 0 : startIp.hashCode());
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
		TopologyRequest other = (TopologyRequest) obj;
		if (endIp == null) {
			if (other.endIp != null)
				return false;
		} else if (!endIp.equals(other.endIp))
			return false;
		if (idleIp == null) {
			if (other.idleIp != null)
				return false;
		} else if (!idleIp.equals(other.idleIp))
			return false;
		if (operationType != other.operationType)
			return false;
		if (startIp == null) {
			if (other.startIp != null)
				return false;
		} else if (!startIp.equals(other.startIp))
			return false;
		if (tag != other.tag)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TopologyRequest [operationType=" + operationType + ", tag=" + tag + ", startIp=" + startIp + ", endIp="
				+ endIp + ", idleIp=" + idleIp + "]";
	}
	

}
