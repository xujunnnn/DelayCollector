package com.ebupt.vnbo.classes.meter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.meter.band_header.Meter_Band_Header;
import com.ebupt.vnbo.classes.meter.band_header.Meter_Band_Headers;
import com.ebupt.vnbo.util.HttpUtil;
/**
 * 
* 类名: MeterEntry.java <br/>
* 包名 : com.ebupt.vnbo.classes.meter <br/>
* 详细描述: TODO(MeterEntry实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class MeterEntry implements Config,Operational {
	@JSONField(name="meter-id")
	private String meter_id;
	@JSONField(name="meter-name")
	private String meter_name;
	@JSONField(name="container-name")
	private String container_name;
	private String flags="meter-kbps";
	@JSONField(name="meter-band-headers")
	private Meter_Band_Headers meter_band_headers;
	public String getMeter_id() {
		return meter_id;
	}
	public MeterEntry setMeter_id(String meter_id) {
		this.meter_id = meter_id;
		return this;
	}
	public String getMeter_name() {
		return meter_name;
	}
	public MeterEntry setMeter_name(String meter_name) {
		this.meter_name = meter_name;
		return this;
	}
	public String getContainer_name() {
		return container_name;
	}
	public MeterEntry setContainer_name(String container_name) {
		this.container_name = container_name;
		return this;
	}
	public String getFlags() {
		return flags;
	}
	public MeterEntry setFlags(String flags) {
		this.flags = flags;
		return this;
	}
	public Meter_Band_Headers getMeter_band_headers() {
		return meter_band_headers;
	}
	public MeterEntry setMeter_band_headers(Meter_Band_Headers meter_band_headers) {
		this.meter_band_headers = meter_band_headers;
		return this;
	}
	/**
	 * set drop-rate for the meter
	 * @param droprate
	 * @return
	 */
	public MeterEntry Set_drop_rate(String droprate){
		Meter_Band_Header meter_Band_Header=new Meter_Band_Header().setBand_burst_size("100").setBand_id(meter_id).setBand_rate("100").setDrop_burst_size("100").setDrop_rate(droprate);
		meter_Band_Header.Set_Type("ofpmbt-drop");
		Meter_Band_Headers meter_Band_Headers=new Meter_Band_Headers();
		meter_Band_Headers.addMeter_Band_Header(meter_Band_Header);
		this.meter_band_headers=meter_Band_Headers;
		return this;
	}
	
	

	public Operational read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:meter/"+meter_id;
		String responsecode=HttpUtil.Delete_request(url)[0];
		if(!"201".equals(responsecode) && !"200".equals(responsecode) )
			throw new OperationalException("meter "+this.getMeter_id()+" read failed");
		return null;

	}
	public void remove(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:meter/"+meter_id;
		String responsecode=HttpUtil.Delete_request(url)[0];
		if(!"201".equals(responsecode) && !"200".equals(responsecode) && !"404".equals(responsecode))
			throw new ConfigException("meter "+this.getMeter_id()+" delete failed");
		
	}
	public void send(String node) throws ConfigException {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:meter/"+meter_id;
	    JSONArray jsonArray=new JSONArray();
	    jsonArray.add(JSONObject.parse(JSON.toJSONString(this)));
	    JSONObject jsonObject=new JSONObject();   
	    jsonObject.put("flow-node-inventory:meter", jsonArray);
		String response[]=HttpUtil.Put_request(url, jsonObject);
		String responsecode=response[0];
		String responsebody=response[1];
		if(!"200".equals(responsecode) && !"201".equals(responsecode))
			throw new ConfigException("meter "+this.getMeter_id()+" sended to "+node+"fail"+"  error information "+ responsebody);
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((container_name == null) ? 0 : container_name.hashCode());
		result = prime * result + ((flags == null) ? 0 : flags.hashCode());
		result = prime * result + ((meter_band_headers == null) ? 0 : meter_band_headers.hashCode());
		result = prime * result + ((meter_id == null) ? 0 : meter_id.hashCode());
		result = prime * result + ((meter_name == null) ? 0 : meter_name.hashCode());
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
		MeterEntry other = (MeterEntry) obj;
		if (container_name == null) {
			if (other.container_name != null)
				return false;
		} else if (!container_name.equals(other.container_name))
			return false;
		if (flags == null) {
			if (other.flags != null)
				return false;
		} else if (!flags.equals(other.flags))
			return false;
		if (meter_band_headers == null) {
			if (other.meter_band_headers != null)
				return false;
		} else if (!meter_band_headers.equals(other.meter_band_headers))
			return false;
		if (meter_id == null) {
			if (other.meter_id != null)
				return false;
		} else if (!meter_id.equals(other.meter_id))
			return false;
		if (meter_name == null) {
			if (other.meter_name != null)
				return false;
		} else if (!meter_name.equals(other.meter_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MeterEntry [meter_id=" + meter_id + ", meter_name=" + meter_name + ", container_name=" + container_name
				+ ", flags=" + flags + ", meter_band_headers=" + meter_band_headers + "]";
	}
	


}
