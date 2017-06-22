package com.ebupt.vnbo.classes.flow.instruction;

import java.util.ArrayList;
import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.flow.action.Action;
/**
 * 
* 类名: Apply_Actions.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.instruction <br/>
* 详细描述: TODO(用于表示流表中的Apply_Actions) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Apply_Actions{
	@JSONField(name="action")
	private ArrayList<Action> actions=new ArrayList<Action>();
	public ArrayList<Action> getActions() {
		return actions;
	}
    public Apply_Actions(){}
	public void setAction(ArrayList<Action> action) {
		this.actions = action;
	}
	public Apply_Actions addAction(Action action){
		this.actions.add(action);
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
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
		Apply_Actions other = (Apply_Actions) obj;
		if (actions == null) {
			if (other.actions != null)
				return false;
		} else if (!actions.equals(other.actions))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Apply_Actions [actions=" + actions + "]";
	}

	


	
}