package com.ebupt.vnbo.classes.vtn;

import java.util.HashSet;
import java.util.Objects;
import com.alibaba.fastjson.annotation.JSONField;

public class Mac_Mapped_Host {
	@JSONField(name="ip-addresses")
	private HashSet<String> ip_addresses;
	private String node;
	@JSONField(name="mac-address")
	private String mac_address;
	@JSONField(name="port-name")
	private String port_name;
	@JSONField(name="vlan-id")
	private String vlan_id;
	@JSONField(name="port-id")
	private String port_id;
	public HashSet<String> getIp_addresses() {
		return ip_addresses;
	}
	public Mac_Mapped_Host setIp_addresses(HashSet<String> ip_addresses) {
		this.ip_addresses = ip_addresses;
		return this;
	}
	public String getNode() {
		return node;
	}
	public Mac_Mapped_Host setNode(String node) {
		this.node = node;
		return this;
	}
	public String getMac_address() {
		return mac_address;
	}
	public Mac_Mapped_Host setMac_address(String mac_address) {
		this.mac_address = mac_address;
		return this;
	}
	public String getPort_name() {
		return port_name;
	}
	public Mac_Mapped_Host setPort_name(String port_name) {
		this.port_name = port_name;
		return this;
	}
	public String getVlan_id() {
		return vlan_id;
	}
	public Mac_Mapped_Host setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
		return this;
	}
	public String getPort_id() {
		return port_id;
	}
	public Mac_Mapped_Host setPort_id(String port_id) {
		this.port_id = port_id;
		return this;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.ip_addresses,this.node,this.mac_address,this.port_id,this.port_name,this.vlan_id);
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
		Mac_Mapped_Host other=(Mac_Mapped_Host) obj;
		return Objects.equals(this.mac_address, other.getMac_address()) && Objects.equals(this.vlan_id, other.vlan_id)
				&& Objects.equals(this.port_id, other.port_id) && Objects.equals(this.port_name, other.getPort_name())
				&& Objects.equals(this.ip_addresses, other.getIp_addresses());
	}

	
	
}
