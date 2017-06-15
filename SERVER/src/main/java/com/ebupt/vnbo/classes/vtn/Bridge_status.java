package com.ebupt.vnbo.classes.vtn;

import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.enums.State;

public class Bridge_status {
	private State state;
	public State getState() {
		return state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path_faults == null) ? 0 : path_faults.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Bridge_status other = (Bridge_status) obj;
		if (path_faults == null) {
			if (other.path_faults != null)
				return false;
		} else if (!path_faults.equals(other.path_faults))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getPath_faults() {
		return path_faults;
	}
	public void setPath_faults(String path_faults) {
		this.path_faults = path_faults;
	}
	@JSONField(name="path-faults")
	private String path_faults;
}
