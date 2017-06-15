package com.ebupt.vnbo.classes.vtn;

public class Mac_Vlan {
	public String getMac() {
		return mac;
	}
	public Mac_Vlan setMac(String mac) {
		this.mac = mac;
		return this;
	}
	public String getVlan() {
		return vlan;
	}
	public Mac_Vlan setVlan(String vlan) {
		this.vlan = vlan;
		return this;
	}
	/**
	 * 
	 */
	public Mac_Vlan(){}
	/**
	 * constructor
	 * @param mac
	 * @param vlan
	 */
	public Mac_Vlan(String mac,String vlan){
		this.mac=mac;
		this.vlan=vlan;		
	}
	@Override
	public String toString(){
		return this.mac+"@"+this.vlan;
	}
	private String mac;
	private String vlan;

}
