package com.ebupt.vnbo.classes.flow.action;

import java.util.Objects;
import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Action.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.action <br/>
* 详细描述: TODO(用于表示流表Action的实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Action {
	private String order;
	@JSONField(name="output-action")
	private OutPut_Action outPut_Action;
	@JSONField(name="set-queue-action")
	private Set_Queue_Action set_Queue_Action;
	public OutPut_Action getOutPut_Action() {
		return outPut_Action;
	}

	public void setOutPut_Action(OutPut_Action outPut_Action) {
		this.outPut_Action = outPut_Action;
	}

	public Set_Queue_Action getSet_Queue_Action() {
		return set_Queue_Action;
	}

	public void setSet_Queue_Action(Set_Queue_Action set_Queue_Action) {
		this.set_Queue_Action = set_Queue_Action;
	}
    

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	public Action Set_Out_Put_Connector(String node_connector){
		OutPut_Action outPut_Action=new OutPut_Action();
		outPut_Action.setMax_length("65535");
		outPut_Action.setOutput_node_connector(node_connector);
		this.outPut_Action=outPut_Action;
		return this;
	}
	public Action Set_Queue_Id(String queue_id){
		Set_Queue_Action set_Queue_Action=new Set_Queue_Action();
		set_Queue_Action.setQueue(queue_id);
		this.set_Queue_Action=set_Queue_Action;
		return this;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(order,outPut_Action,set_Queue_Action);
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
	    Action other=(Action) obj;
	    	return Objects.equals(this.order, other.getOrder()) &&
	    			Objects.equals(this.outPut_Action, other.getOutPut_Action()) &&
	    			Objects.equals(this.set_Queue_Action, other.getSet_Queue_Action());
	}
	
	
	
	
}






