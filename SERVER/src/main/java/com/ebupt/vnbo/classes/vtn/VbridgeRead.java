package com.ebupt.vnbo.classes.vtn;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;

public class VbridgeRead implements Operational{
	@JSONField(name="name")
	private String bridge_name;
	@JSONField(name="bridge-status")
	private Bridge_status bridge_status;
	@JSONField(name="mac-map")
	private Mac_Map mac_Map;
	@JSONField(deserialize=true)
	private String tenant_name;
	public String getBridge_name() {
		return bridge_name;
	}
	public VbridgeRead setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
		return this;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public VbridgeRead setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public Bridge_status getBridge_status() {
		return bridge_status;
	}
	public VbridgeRead setBridge_status(Bridge_status bridge_status) {
		this.bridge_status = bridge_status;
		return this;
	}
	public Mac_Map getMac_Map() {
		return mac_Map;
	}
	public VbridgeRead setMac_Map(Mac_Map mac_Map) {
		this.mac_Map = mac_Map;
		return this;
	}
/**
	@Override
	public VbridgeRead read(String node) throws VbridgeException {
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/operational/vtn:vtns/vtn/"+tenant_name+"/vbridge/"+bridge_name;
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new VbridgeException("vbridge"+bridge_name+"read failed");
		JSONObject vbridgejson=JSONObject.parseObject(jsondata).getJSONArray("vbridge").getJSONObject(0);
		VbridgeRead vbridgeRead=JSONObject.parseObject(vbridgejson.toJSONString(), VbridgeRead.class);
		vbridgeRead.setTenant_name(tenant_name).setBridge_name(bridge_name);
		return vbridgeRead;
	}
	**/
	public VbridgeRead read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/vtn:vtns/vtn/"+tenant_name+"/vbridge/"+bridge_name;
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new OperationalException("vbridge"+bridge_name+"read failed");
		JSONObject vbridgejson=JSONObject.parseObject(jsondata).getJSONArray("vbridge").getJSONObject(0);
		VbridgeRead vbridgeRead=JSONObject.parseObject(vbridgejson.toJSONString(), VbridgeRead.class);
		vbridgeRead.setTenant_name(tenant_name).setBridge_name(bridge_name);
		return vbridgeRead;
	}
}
