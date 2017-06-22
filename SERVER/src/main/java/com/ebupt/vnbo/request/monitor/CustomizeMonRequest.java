package com.ebupt.vnbo.request.monitor;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.monitor.CustomizeMonitor;
import com.ebupt.vnbo.request.Request;
/**
 * 
* 类名: CustomizeMonRequest.java <br/>
* 包名 : com.ebupt.vnbo.request.monitor <br/>
* 详细描述: TODO() <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月16日 <br/>
* 发布版本： V1.0  <br/>
 */
public class CustomizeMonRequest implements Request 
{	
	private List<CustomizeMonitor> customizeMonitors;
	private CustomizeMonitor customizeMonitor;
	private OperationType operationType;
	
	public CustomizeMonitor getCustomizeMonitor() {
		return customizeMonitor;
	}
	public void setCustomizeMonitor(CustomizeMonitor customizeMonitor) {
		this.customizeMonitor = customizeMonitor;
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
		result = prime * result + ((customizeMonitor == null) ? 0 : customizeMonitor.hashCode());
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
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
		CustomizeMonRequest other = (CustomizeMonRequest) obj;
		if (customizeMonitor == null) {
			if (other.customizeMonitor != null)
				return false;
		} else if (!customizeMonitor.equals(other.customizeMonitor))
			return false;
		if (operationType != other.operationType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CustomizeMonRequest [customizeMonitor=" + customizeMonitor + ", operationType=" + operationType + "]";
	}
	public List<CustomizeMonitor> getCustomizeMonitors() {
		return customizeMonitors;
	}
	public void setCustomizeMonitors(List<CustomizeMonitor> customizeMonitors) {
		this.customizeMonitors = customizeMonitors;
	}
	
	public static void main(String []args){
		CustomizeMonRequest customizeMonRequest=new CustomizeMonRequest();
		
		
		customizeMonRequest.setOperationType(OperationType.ADD);
		CustomizeMonitor customizeMonitor=new CustomizeMonitor();
		customizeMonitor.setId("33");
		customizeMonitor.setSrchost("host:8a:54:d4:7e:bf:7c");
		customizeMonitor.setDesthost("host:32:c5:49:2a:03:ed");
		customizeMonitor.setProtocol_Type(Protocol_Type.UDP);
		customizeMonitor.setUdp_destPort("200");
		customizeMonRequest.setCustomizeMonitor(customizeMonitor);
		System.out.println(JSON.toJSON(customizeMonRequest));
	}
	

}
