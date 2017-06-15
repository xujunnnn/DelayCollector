package com.ebupt.vnbo.classes.vtn;

import java.util.HashSet;

public class Denyed_Hosts {
	private HashSet<String> denied_hosts=new HashSet<String>();
    public Denyed_Hosts(){}
    public Denyed_Hosts(HashSet<String> denied_hosts){
    	this.denied_hosts=denied_hosts;
    }
    public Denyed_Hosts addHost(String host){
    	this.denied_hosts.add(host);
    	return this;
    }
	public HashSet<String> getDenied_hosts() {
		return denied_hosts;
	}
    
	public Denyed_Hosts setDenied_hosts(HashSet< String> denied_hosts) {
		this.denied_hosts = denied_hosts;
		return this;
	}

}
