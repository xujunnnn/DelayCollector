package com.ebupt.vnbo.classes.vtn;
import com.alibaba.fastjson.annotation.JSONField;
public class Mac_Map_Config {
	@JSONField(name="allowed-hosts")
	private AllowedHosts allowedHosts;

	public AllowedHosts getAllowedHosts() {
		return allowedHosts;
	}

	public void setAllowedHosts(AllowedHosts allowedHosts) {
		this.allowedHosts = allowedHosts;
	}

}


