package com.ebupt.vnbo.request.qos;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.qos.QosEntry;
import com.ebupt.vnbo.request.Request;

public class QosRequest implements Request {
	private ArrayList<QosEntry> qosEntries;
	private OperationType operationType;
	private QosEntry qosEntry;
	public ArrayList<QosEntry> getQosEntries() {
		return qosEntries;
	}
	public void setQosEntries(ArrayList<QosEntry> qosEntries) {
		this.qosEntries = qosEntries;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public QosEntry getQosEntry() {
		return qosEntry;
	}
	public void setQosEntry(QosEntry qosEntry) {
		this.qosEntry = qosEntry;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((qosEntries == null) ? 0 : qosEntries.hashCode());
		result = prime * result + ((qosEntry == null) ? 0 : qosEntry.hashCode());
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
		QosRequest other = (QosRequest) obj;
		if (operationType != other.operationType)
			return false;
		if (qosEntries == null) {
			if (other.qosEntries != null)
				return false;
		} else if (!qosEntries.equals(other.qosEntries))
			return false;
		if (qosEntry == null) {
			if (other.qosEntry != null)
				return false;
		} else if (!qosEntry.equals(other.qosEntry))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "QosRequest [qosEntries=" + qosEntries + ", operationType=" + operationType + ", qosEntry=" + qosEntry
				+ "]";
	}
	public static void main(String []args){
		QosRequest qosRequest=new QosRequest();
		QosEntry qosEntry=new QosEntry();
		qosEntry.setQos_id("33").setUdp_destPort("200").setQueue_id("1");
		qosRequest.setOperationType(OperationType.ADD);
		qosRequest.setQosEntry(qosEntry);
		System.out.println(JSON.toJSONString(qosRequest));
		
	}
}
