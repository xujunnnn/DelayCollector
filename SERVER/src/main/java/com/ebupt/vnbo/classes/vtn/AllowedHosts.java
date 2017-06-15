package com.ebupt.vnbo.classes.vtn;

import java.util.HashSet;

import com.alibaba.fastjson.annotation.JSONField;

public class AllowedHosts{
	@JSONField(name="vlan-host-desc-list")
	private HashSet<Hostmc> vlan_host_desc_list=new HashSet<Hostmc>();

	public HashSet<Hostmc> getVlan_host_desc_list() {
		return vlan_host_desc_list;
	}

	public void setVlan_host_desc_list(HashSet<Hostmc> vlan_host_desc_list) {
		this.vlan_host_desc_list = vlan_host_desc_list;
	}


}