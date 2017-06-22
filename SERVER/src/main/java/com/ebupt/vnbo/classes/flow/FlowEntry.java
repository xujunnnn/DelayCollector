package com.ebupt.vnbo.classes.flow;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * 
* 类名: FlowEntry.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow <br/>
* 详细描述: TODO(FlowEntry流表项的实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月16日 <br/>
* 发布版本： V1.0  <br/>
 */
public class FlowEntry implements Config,Operational {
	private static Logger logger=LoggerFactory.getLogger(FlowEntry.class);
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
	
	
	
	

	@Override
	public String toString() {
		return "FlowEntry [flow_name=" + flow_name + ", priority=" + priority + ", idle_timeout=" + idle_timeout
				+ ", hard_timeout=" + hard_timeout + ", flow_Statistic=" + flow_Statistic + ", table_id=" + table_id
				+ ", cookie=" + cookie + ", id=" + id + ", instructions=" + instructions + ", match=" + match
				+ ", tableid=" + tableid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cookie == null) ? 0 : cookie.hashCode());
		result = prime * result + ((flow_Statistic == null) ? 0 : flow_Statistic.hashCode());
		result = prime * result + ((flow_name == null) ? 0 : flow_name.hashCode());
		result = prime * result + ((hard_timeout == null) ? 0 : hard_timeout.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idle_timeout == null) ? 0 : idle_timeout.hashCode());
		result = prime * result + ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((table_id == null) ? 0 : table_id.hashCode());
		result = prime * result + ((tableid == null) ? 0 : tableid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlowEntry other = (FlowEntry) obj;
		if (cookie == null) {
			if (other.cookie != null)
				return false;
		} else if (!cookie.equals(other.cookie))
			return false;
		if (flow_Statistic == null) {
			if (other.flow_Statistic != null)
				return false;
		} else if (!flow_Statistic.equals(other.flow_Statistic))
			return false;
		if (flow_name == null) {
			if (other.flow_name != null)
				return false;
		} else if (!flow_name.equals(other.flow_name))
			return false;
		if (hard_timeout == null) {
			if (other.hard_timeout != null)
				return false;
		} else if (!hard_timeout.equals(other.hard_timeout))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idle_timeout == null) {
			if (other.idle_timeout != null)
				return false;
		} else if (!idle_timeout.equals(other.idle_timeout))
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (table_id == null) {
			if (other.table_id != null)
				return false;
		} else if (!table_id.equals(other.table_id))
			return false;
		if (tableid == null) {
			if (other.tableid != null)
				return false;
		} else if (!tableid.equals(other.tableid))
			return false;
		return true;
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
		String responsebody=result[1];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode)){
			logger.error("FlowEntry {} send error, error details {} ",this.getId(),responsebody);
			throw new ConfigException("flow"+this.getId()+"sended to"+node+"failed");	
		}
	
		
	}
	public void remove(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
		String response[]=HttpUtil.Delete_request(url);
		String responsecode=response[0];
		String responsebody=response[1];
		if(!"200".equals(responsecode) && !"201".equals(responsecode) && !"404".equals(responsecode)){
			logger.error("FlowEntry {} remove error, error details {} ",this.getId(),responsebody);
			throw new ConfigException("flow "+this.getId()+" delete from "+node+" failed");		
		}
	}
	public FlowEntry read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		  String url="http://"+OperationalUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
		  String []result=HttpUtil.Get_request(url);
		  String code=result[0];
		  String responsebody=result[1];
		  if("404".equals(code)){
			  logger.error("FlowEntry {} send error, error details {} ",this.getId(),responsebody);
			  throw new OperationalException("can not read the flow ");
		  }		  
		  JSONObject jsonObject=JSONObject.parseObject(responsebody); 
		  FlowEntry flowEntry = JSON.parseObject(jsonObject.getJSONArray("flow-node-inventory:flow").getJSONObject(0).toJSONString(), FlowEntry.class);
		  return flowEntry;
		
	}



}
