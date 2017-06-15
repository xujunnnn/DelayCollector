package com.ebupt.vnbo.request.vtopo;

import java.util.ArrayList;

import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.vtopo.VTopo;
import com.ebupt.vnbo.request.Request;

public class VtopoRequest implements Request {
	private ArrayList<VTopo> vTopos;
	private VTopo vTopo;
	private OperationType operationType;
	public ArrayList<VTopo> getvTopos() {
		return vTopos;
	}
	public void setvTopos(ArrayList<VTopo> vTopos) {
		this.vTopos = vTopos;
	}
	public VTopo getvTopo() {
		return vTopo;
	}
	public void setvTopo(VTopo vTopo) {
		this.vTopo = vTopo;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

}
