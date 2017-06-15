package com.ebupt.vnbo.classes.vtn;

import com.alibaba.fastjson.annotation.JSONField;

public class Vtenant_config {
	@JSONField(name="idle-timeout")
	private String idle_timeout;
	@JSONField(name="hard-timeout")
	private String hard_timeout;
	public String getIdle_timeout() {
		return idle_timeout;
	}
	public Vtenant_config setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
		return this;
	}
	public String getHard_timeout() {
		return hard_timeout;
	}
	public Vtenant_config setHard_timeout(String hard_timeout) {
		this.hard_timeout = hard_timeout;
		return this;
	}
	

}
