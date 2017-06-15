package com.ebupt.vnbo.classes.topology;

import java.util.HashSet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.HttpUtil;
public class HostTracker implements Config{
	@JSONField(name="SrcIp")
	private String SrcIp;
	@JSONField(name="ip-list")
	private HashSet<String> ip_list=new HashSet<>();
	@JSONField(name="SrcIp")
	public String getSrcIp() {
		return SrcIp;
	}
	@JSONField(name="SrcIp")
	public HostTracker setSrcIp(String SrcIp) {
		this.SrcIp = SrcIp;
		return this;
	}
	public HashSet<String> getIp_list() {
		return ip_list;
	}
	public HostTracker setIp_list(HashSet<String> ip_list) {
		this.ip_list = ip_list;
		return this;
	}
	

	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		String url="http://"+VtnConfigUrl+"/arp-handler-impl:ActiveArp";
		JSONObject jsondata=new JSONObject();
		jsondata.put("input", JSONObject.toJSON(this));
		String []result=HttpUtil.Post_request(url, jsondata);
		String responsecode=result[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode)){
			throw new ConfigException("failed to send ArpPacket");
		}
		
	}
	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
