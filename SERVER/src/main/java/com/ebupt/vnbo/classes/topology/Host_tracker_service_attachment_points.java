package com.ebupt.vnbo.classes.topology;

import com.alibaba.fastjson.annotation.JSONField;

public class Host_tracker_service_attachment_points {
	@JSONField(name="tp-id")
	private String tp_id;
	private boolean active;
	@JSONField(name="corresponding-tp")
	private String corresponding_tp;
	public String getTp_id() {
		return tp_id;
	}
	public boolean isActive() {
		return active;
	}
	public String getCorresponding_tp() {
		return corresponding_tp;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setCorresponding_tp(String corresponding_tp) {
		this.corresponding_tp = corresponding_tp;
	}
	

}
