package com.ebupt.vnbo.classes.vtn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.enums.Map_Type;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.UpDate_Mode;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.util.HttpUtil;

/**
 * 
 * @author xu
 *
 */
public class Vbridge implements Config {
	@JSONField(serialize=false)
	private Map_Type map_type;
	@JSONField(name="update-mode")
	private UpDate_Mode update_mode;
	private OperationType operation;
	private String description;
	@JSONField(name="age-interval")
	private String age_interval;
	@JSONField(name="tenant-name")
	private String tenant_name;
	@JSONField(name="bridge-name")
	private String bridge_name;
	@JSONField(serialize=false)
	private Mac_Map mac_Map;
	
	
	public Map_Type getMap_type() {
		return map_type;
	}
	public Vbridge setMap_type(Map_Type map_type) {
		this.map_type = map_type;
	return this;
	}
	public Mac_Map getMac_Map() {
		return mac_Map;
	}
	public Vbridge setMac_Map(Mac_Map mac_Map) {
		this.mac_Map = mac_Map;
		return this;
	}
	public UpDate_Mode getUpdate_mode() {
		return update_mode;
	}
	public Vbridge setUpdate_mode(UpDate_Mode update_mode) {
		this.update_mode = update_mode;
		return this;
	}
	public OperationType getOperation() {
		return operation;
	}
	public Vbridge setOperation(OperationType operation) {
		this.operation = operation;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Vbridge setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getAge_interval() {
		return age_interval;
	}
	public Vbridge setAge_interval(String age_interval) {
		this.age_interval = age_interval;
		return this;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public Vbridge setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public String getBridge_name() {
		return bridge_name;
	}
	public Vbridge setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
		return this;
	}
	/**
	 * 设置MacMap参数
	 * @param allowed_Hosts
	 * @param denyed_Hosts
	 * @return
	 */
	public Vbridge Set_Mac_Map(Allowed_Hosts allowed_Hosts,Denyed_Hosts denyed_Hosts){
		this.map_type=Map_Type.mac_map;
		this.mac_Map=new Mac_Map();
		mac_Map.setBridge_name(this.bridge_name);
		mac_Map.setTenant_name(this.tenant_name);
		if(allowed_Hosts!=null)
		{
			this.mac_Map.setAllowed_hosts(allowed_Hosts.getAllowed_hosts());
		}
		if(denyed_Hosts!=null){
			this.mac_Map.setDenyed_Hosts(denyed_Hosts.getDenied_hosts());
		}
		return this;
		
	}
/**
	@Override
	public void send(String node) throws VbridgeException, Mac_MapFailException {
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/operations/vtn-vbridge:update-vbridge";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		String responsecode=HttpUtil.Post_request(url,jsonObject)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new VbridgeException("vbridge"+this.getBridge_name()+"created failed");
		if(this.map_type==Map_Type.mac_map){
				this.mac_Map.send(null);
		}
		
		
	}
	@Override
	public void remove(String node) throws VbridgeException {
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/config/vtn:vtns/vtn/"+this.getTenant_name()+"/vbridge/"+this.getBridge_name();
		String responsecode=HttpUtil.Delete_request(url)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode) && !"404".equals(responsecode))
				throw new VbridgeException("vbridge delete fail"+this.getBridge_name());
		
	}
	@Override
	public Vbridge read(String node) throws VbridgeException {
		// TODO Auto-generated method stub
		String url="http://"+ODL_IP+"/restconf/operational/vtn:vtns/vtn/"+tenant_name+"/vbridge/"+bridge_name;
		String []result=HttpUtil.Get_request(url);
		String jsondata=result[1];
		String responsecode=result[0];
		if(!"200".equals(responsecode) && ! "201".equals(responsecode))
			throw new VbridgeException("vbridge"+bridge_name+"read failed");
		JSONObject vbridgejson=JSONObject.parseObject(jsondata).getJSONArray("vbridge").getJSONObject(0);
		Vbridge vbridge=JSONObject.parseObject(vbridgejson.toJSONString(), Vbridge.class);
		return vbridge;
	}
**/
	public void send(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+VtnConfigUrl+"/vtn-vbridge:update-vbridge";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		String responsecode=HttpUtil.Post_request(url,jsonObject)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new ConfigException("vbridge"+this.getBridge_name()+"created failed");
		if(this.map_type==Map_Type.mac_map){
				this.mac_Map.send(null);
		}
		
	}
	public void remove(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/vtn:vtns/vtn/"+this.getTenant_name()+"/vbridge/"+this.getBridge_name();
		String responsecode=HttpUtil.Delete_request(url)[0];
		if(!"200".equals(responsecode) && !"201".equals(responsecode) && !"404".equals(responsecode))
				throw new ConfigException("vbridge delete fail"+this.getBridge_name());
		
	}
}

