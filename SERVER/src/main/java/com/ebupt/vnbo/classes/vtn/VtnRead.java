package com.ebupt.vnbo.classes.vtn;

import java.util.HashSet;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;


public class VtnRead implements Operational{
	@JSONField(name="name")
	private String tenant_name;
	@JSONField(name="vtenant-config")
	private Vtenant_config vtenant_config;
	@JSONField(name="vbridge")	
	private HashSet<VbridgeRead> vbridgeReads=new HashSet<>();
	public Vtenant_config getVtenant_config() {
		return vtenant_config;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public VtnRead setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public VtnRead setVtenant_config(Vtenant_config vtenant_config) {
		this.vtenant_config = vtenant_config;
		return this;
	}
	public HashSet<VbridgeRead> getVbridgeReads() {
		return vbridgeReads;
	}
	public VtnRead setVbridgeReads(HashSet<VbridgeRead> vbridgeReads) {
		this.vbridgeReads = vbridgeReads;
		return this;
	}
	/**
	@Override
	public void send(String node) throws  {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(String node) throws EntrySetException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public VtnRead read(String node) throws VtnException   {
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/operational/vtn:vtns/vtn/"+tenant_name;
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new VtnException("vtn"+tenant_name+"read failed");
		JSONObject jsonObject=JSONObject.parseObject(jsondata);
		JSONObject vtnJsonObject=jsonObject.getJSONArray("vtn").getJSONObject(0);	
		VtnRead vtnRead=JSONObject.parseObject(vtnJsonObject.toJSONString(), VtnRead.class);
		return vtnRead;
	}
**/
	@Override
	public VtnRead read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/vtn:vtns/vtn/"+tenant_name;
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new OperationalException("vtn"+tenant_name+"read failed");
		JSONObject jsonObject=JSONObject.parseObject(jsondata);
		JSONObject vtnJsonObject=jsonObject.getJSONArray("vtn").getJSONObject(0);	
		VtnRead vtnRead=JSONObject.parseObject(vtnJsonObject.toJSONString(), VtnRead.class);
		return vtnRead;
	}
}
