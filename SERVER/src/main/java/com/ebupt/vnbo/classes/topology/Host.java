package com.ebupt.vnbo.classes.topology;

import java.util.Objects;


/**
 * class which presents a host
 * @author xu
 *
 */
public class Host {
	private String host_name;
	private String ip;
	private String mac;
	private String access_node;
	private String access_port;
	public String getIp() {
		return ip;
	}
	public Host setIp(String ip) {
		this.ip = ip;
		return this;
	}
	public String getMac() {
		return mac;
	}
	public Host setMac(String mac) {
		this.mac = mac;
		return this;
	}
	public String getAccess_node() {
		return access_node;
	}
	public Host setAccess_node(String access_node) {
		this.access_node = access_node;
		return this;
	}
	public String getAccess_port() {
		return access_port;
	}
	public Host setAccess_port(String access_port) {
		this.access_port = access_port;
		return this;
	}
@Override
	public boolean equals(Object obj){
		if(obj==this) 
			return true;
		if(obj==null)
			return false;
		if(obj.getClass()!=this.getClass())
			return false;
		
		Host other=(Host) obj;
		return Objects.equals(this.ip, other.ip) && Objects.equals(other.mac,this.mac ) && Objects.equals(other.access_node, this.access_node)
				&& Objects.equals(this.access_port, other.access_port);
		
	}
@Override
	public int hashCode(){
	return Objects.hash(this.ip,this.mac,this.access_node,this.access_port);
	}


public String getHost_name() {
	return host_name;
}
public Host setHost_name(String host_name) {
	this.host_name = host_name;
	return this;
}
	

}
