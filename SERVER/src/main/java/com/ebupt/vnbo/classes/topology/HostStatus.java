package com.ebupt.vnbo.classes.topology;

import com.ebupt.vnbo.classes.enums.State;

public class HostStatus {
	private State state;
	private String vgroup;
	private String vTopo;
	public State getState() {
		return state;
	}
	public HostStatus setState(State state) {
		this.state = state;
		return this;
	}
	public String getVgroup() {
		return vgroup;
	}
	public HostStatus setVgroup(String vgroup) {
		this.vgroup = vgroup;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((vgroup == null) ? 0 : vgroup.hashCode());
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
		HostStatus other = (HostStatus) obj;
		if (state != other.state)
			return false;
		if (vgroup == null) {
			if (other.vgroup != null)
				return false;
		} else if (!vgroup.equals(other.vgroup))
			return false;
		return true;
	}
	public String getvTopo() {
		return vTopo;
	}
	public HostStatus setvTopo(String vTopo) {
		this.vTopo = vTopo;
		return this;
	}
	

}
