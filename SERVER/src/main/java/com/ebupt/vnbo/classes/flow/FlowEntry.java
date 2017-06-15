package com.ebupt.vnbo.classes.flow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.flow_static.Flow_Statistic;
import com.ebupt.vnbo.classes.flow.instruction.Instructions;
import com.ebupt.vnbo.classes.flow.match.Match;
import com.ebupt.vnbo.util.HttpUtil;

public class FlowEntry implements Config,Operational {
	
	@JSONField(name="flow-name")
	private String flow_name;
	private String  priority;
	@JSONField(name="idle-timeout")
	private String  idle_timeout;
	@JSONField(name="hard-timeout")
	private String  hard_timeout;
	@JSONField(name="opendaylight-flow-statistics:flow-statistics")
	private Flow_Statistic flow_Statistic;
	private String table_id;
	private String cookie;
	private String id;
	private Instructions instructions;
    private Match match;
	private String tableid;
    public Flow_Statistic getFlow_Statistic() {
		return flow_Statistic;
	}
	public void setFlow_Statistic(Flow_Statistic flow_Statistic) {
		this.flow_Statistic = flow_Statistic;
	}
	public String getFlow_name() {
		return flow_name;
	}
	public FlowEntry setFlow_name(String flow_name) {
		this.flow_name = flow_name;
		return this;
	}
	public String getPriority() {
		return priority;
	}
	public FlowEntry setPriority(String priority) {
		this.priority = priority;
		return this;
	}
	public String getIdle_timeout() {
		return idle_timeout;
	}
	public FlowEntry setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
		return this;
	}
	public String getHard_timeout() {
		return hard_timeout;
	}
	public FlowEntry setHard_timeout(String hard_timeout) {
		this.hard_timeout = hard_timeout;
		return this;
	}
	public String getTable_id() {
		return table_id;
	}
	public FlowEntry setTable_id(String table_id) {
		this.table_id = table_id;
		return this;
	}
	public String getId() {
		return id;
	}
	public FlowEntry setId(String id) {
		this.id = id;
		return this;
	}
	public Instructions getInstructions() {
		return instructions;
	}
	public FlowEntry setInstructions(Instructions instructions) {
		this.instructions = instructions;
		return this;
	}
	public Match getMatch() {
		return match;
	}
	public FlowEntry setMatch(Match match) {
		this.match = match;
		return this;
	}
	public String getCookie() {
		return cookie;
	}
	public FlowEntry setCookie(String cookie) {
		this.cookie = cookie;
		return this;
	}

	public void send(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
       	JSONArray jsonArray=new JSONArray();
		jsonArray.add(JSON.parseObject(JSON.toJSONString(this)));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("flow", jsonArray);
		String []result=HttpUtil.Put_request(url, jsonObject);
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new ConfigException("flow"+this.getId()+"sended to"+node+"failed");			
	
		
	}
	public void remove(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
		String responsecode=HttpUtil.Delete_request(url)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode) && !"404".equals(responsecode))
			throw new ConfigException("flow "+this.getId()+" delete from "+node+" failed");		
		
	}
	public FlowEntry read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		  String url="http://"+OperationalUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
		  String []result=HttpUtil.Get_request(url);
		  String code=result[0];
		  if("404".equals(code))
			  throw new OperationalException("can not read the flow ");
		  String s=result[1];
		  JSONObject jsonObject=JSONObject.parseObject(s); 
		  FlowEntry flowEntry = JSON.parseObject(jsonObject.getJSONArray("flow-node-inventory:flow").getJSONObject(0).toJSONString(), FlowEntry.class);
		  return flowEntry;
		
	}
	@Override
	public String toString() {
		return "FlowEntry [flow_name=" + flow_name + ", priority=" + priority + ", idle_timeout=" + idle_timeout
				+ ", hard_timeout=" + hard_timeout + ", flow_Statistic=" + flow_Statistic + ", table_id=" + table_id
				+ ", cookie=" + cookie + ", id=" + id + ", instructions=" + instructions + ", match=" + match
				+ ", tableid=" + tableid + "]";
	}



}
