package com.ebupt.vnbo.classes.monitor;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;
/**
 * 
* 类名: Latency_list.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(Latency_list实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Latency_list implements Operational{
	@JSONField(name="latency-list")
	private ArrayList<Latency> latency_list=new ArrayList<>();

	public ArrayList<Latency> getLatency_list() {
		return latency_list;
	}

	public void setLatency_list(ArrayList<Latency> latency_list) {
		this.latency_list = latency_list;
	}
	

	@Override
	public String toString() {
		return "Latency_list [latency_list=" + latency_list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latency_list == null) ? 0 : latency_list.hashCode());
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
		Latency_list other = (Latency_list) obj;
		if (latency_list == null) {
			if (other.latency_list != null)
				return false;
		} else if (!latency_list.equals(other.latency_list))
			return false;
		return true;
	}

	@Override
	public Latency_list read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		String url="http://"+VtnOperationUrl+"/topology-lldp-discovery-impl:getGlobalLatency";
		String[] result=HttpUtil.Post_request(url);
		String responsecode=result[0];
		String responsebody=result[1];
		if(!"201".equals(responsecode) && !"200".equals(responsecode))
			throw new OperationalException("Latency read fail");
		JSONObject resultjson=JSONObject.parseObject(responsebody);
		Latency_list latency_list=JSONObject.parseObject(resultjson.getJSONObject("output").toJSONString(), Latency_list.class);
		return latency_list;
	}

}
