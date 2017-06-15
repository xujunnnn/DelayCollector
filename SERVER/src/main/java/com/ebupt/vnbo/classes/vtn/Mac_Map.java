package com.ebupt.vnbo.classes.vtn;

import java.util.HashSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;

public class Mac_Map implements Config,Operational{	
	@JSONField(name="tenant-name")
	private String tenant_name;
	@JSONField(name="bridge-name")
	private String bridge_name;
	@JSONField(name="allowed-hosts")
	private HashSet<String> allowed_hosts;
	@JSONField(name="denied-hosts")
	private HashSet<String> denyed_Hosts;
	@JSONField(name="mac-map-config")
	private Mac_Map_Config mac_Map_Config;
	private OperationType operation;
	
	public Mac_Map_Config getMac_Map_Config() {
		return mac_Map_Config;
	}
	public void setMac_Map_Config(Mac_Map_Config mac_Map_Config) {
		this.mac_Map_Config = mac_Map_Config;
	}
	public HashSet<String> getAllowed_hosts() {
		return allowed_hosts;
	}
	public Mac_Map setAllowed_hosts(HashSet<String> allowed_hosts) {
		this.allowed_hosts = allowed_hosts;
		return this;
	}
	public HashSet<String> getDenyed_Hosts() {
		return denyed_Hosts;
	}
	public Mac_Map setDenyed_Hosts(HashSet<String> denyed_Hosts) {
		this.denyed_Hosts = denyed_Hosts;
		return this;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public Mac_Map setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public String getBridge_name() {
		return bridge_name;
	}
	public Mac_Map setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
		return this;
	}
	public OperationType getOperation() {
		return operation;
	}
	public Mac_Map setOperation(OperationType operation) {
		this.operation = operation;
		return this;
	}
	/**
	public void send(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url2="http://"+ConfigUrl+"/vtn-mac-map:set-mac-map";
		JSONObject jsonObject2=new JSONObject();
		jsonObject2.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		System.out.println(url2+jsonObject2);
		String responsecode=HttpUtil.Post_request(url2,jsonObject2)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new ConfigException("vbridge"+this.getBridge_name()+"mac_map created failed");
		
		
		
	}
	/**
	public void remove(String node) throws Mac_MapFailException {
		// TODO Auto-generated method stub
		
	}
	/**
	public Mac_Map read(String node) throws Mac_MapFailException{
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/operational/vtn:vtns/vtn/"+tenant_name+"/vbridge/"+bridge_name+"/mac-map";
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
				throw new Mac_MapFailException("failed to read the mac_map VTn/Vbridge "+tenant_name+"/"+bridge_name);
		JSONObject resultjson=JSON.parseObject(jsondata).getJSONObject("mac-map");
		Mac_Map mac_Map=JSON.parseObject(resultjson.toJSONString(), Mac_Map.class);
		return mac_Map;
		
	}
	**/
	public Mac_Map read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/vtn:vtns/vtn/"+tenant_name+"/vbridge/"+bridge_name+"/mac-map";
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
				throw new OperationalException("failed to read the mac_map VTn/Vbridge "+tenant_name+"/"+bridge_name);
		JSONObject resultjson=JSON.parseObject(jsondata).getJSONObject("mac-map");
		Mac_Map mac_Map=JSON.parseObject(resultjson.toJSONString(), Mac_Map.class);
		return mac_Map;
	}
	public void send(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url2="http://"+VtnConfigUrl+"/vtn-mac-map:set-mac-map";
		JSONObject jsonObject2=new JSONObject();
		jsonObject2.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		System.out.println(url2+jsonObject2);
		String responsecode=HttpUtil.Post_request(url2,jsonObject2)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new ConfigException("vbridge"+this.getBridge_name()+"mac_map created failed");
		
	}
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		
	}
}