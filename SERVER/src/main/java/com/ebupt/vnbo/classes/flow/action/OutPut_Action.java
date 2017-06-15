package com.ebupt.vnbo.classes.flow.action;

import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: OutPut_Action.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.action <br/>
* 详细描述: TODO(用于表示输出到某端口的实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class OutPut_Action{
	@JSONField(name="output-node-connector")
	private String output_node_connector;
	@JSONField(name="max-length")
	private String max_length;
	public String getOutput_node_connector() {
		return output_node_connector;
	}
	public void setOutput_node_connector(String output_node_connector) {
		this.output_node_connector = output_node_connector;
	}
	public String getMax_length() {
		return max_length;
	}
	public void setMax_length(String max_length) {
		this.max_length = max_length;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(output_node_connector,max_length);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj)
			return true;
		if(obj==null)
			return false;
	    if(this.getClass()!=obj.getClass())
	    	return false;
	    OutPut_Action other=(OutPut_Action) obj;
	    	return Objects.equals(this.output_node_connector, other.output_node_connector) &&
	    			Objects.equals(this.max_length, other.getMax_length());
	}
	
}