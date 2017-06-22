package com.ebupt.vnbo.request.vtopo;

import java.util.ArrayList;

import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.vtopo.VTopo;
import com.ebupt.vnbo.request.Request;
/**
 * 
* 类名: VtopoRequest.java <br/>
* 包名 : com.ebupt.vnbo.request.vtopo <br/>
* 详细描述: TODO(虚拟拓扑Vtopo请求实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月16日 <br/>
* 发布版本： V1.0  <br/>
 */
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((vTopo == null) ? 0 : vTopo.hashCode());
		result = prime * result + ((vTopos == null) ? 0 : vTopos.hashCode());
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
		VtopoRequest other = (VtopoRequest) obj;
		if (operationType != other.operationType)
			return false;
		if (vTopo == null) {
			if (other.vTopo != null)
				return false;
		} else if (!vTopo.equals(other.vTopo))
			return false;
		if (vTopos == null) {
			if (other.vTopos != null)
				return false;
		} else if (!vTopos.equals(other.vTopos))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VtopoRequest [vTopos=" + vTopos + ", vTopo=" + vTopo + ", operationType=" + operationType + "]";
	}
	

}
