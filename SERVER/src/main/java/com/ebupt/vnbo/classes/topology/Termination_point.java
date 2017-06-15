package com.ebupt.vnbo.classes.topology;

import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;

public class Termination_point {
	@JSONField(name="tp-id")
	private String tp_id;

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

	@Override
	public boolean equals(Object obj){
		if(obj==this) 
			return true;
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		
		Termination_point other=(Termination_point) obj;
		return Objects.equals(other.tp_id, this.tp_id);
	
		}
	@Override
	public int hashCode(){
		return Objects.hash(this.tp_id);
	}
	

}
