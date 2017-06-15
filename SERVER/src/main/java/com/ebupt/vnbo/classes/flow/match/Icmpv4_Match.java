package com.ebupt.vnbo.classes.flow.match;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Icmpv4_Match.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.match <br/>
* 详细描述: TODO(Icmpv4_Match实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Icmpv4_Match {
	@JSONField(name="icmpv4-type")
	private String icmpv4_type;
	@JSONField(name="icmpv4-code")
	private String icmpv4_code;
	public String getIcmpv4_type() {
	return icmpv4_type;
}
	public void setIcmpv4_type(String icmpv4_type) {
	this.icmpv4_type = icmpv4_type;
}
	public String getIcmpv4_code() {
	return icmpv4_code;
}
	public void setIcmpv4_code(String icmpv4_code) {
	this.icmpv4_code = icmpv4_code;
}
	public Icmpv4_Match(String icmpv4_type,String icmpv4_code){
		if (icmpv4_code!=null)
		this.icmpv4_code=icmpv4_code;
		if(icmpv4_type!=null)
		this.icmpv4_type=icmpv4_type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((icmpv4_code == null) ? 0 : icmpv4_code.hashCode());
		result = prime * result + ((icmpv4_type == null) ? 0 : icmpv4_type.hashCode());
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
		Icmpv4_Match other = (Icmpv4_Match) obj;
		if (icmpv4_code == null) {
			if (other.icmpv4_code != null)
				return false;
		} else if (!icmpv4_code.equals(other.icmpv4_code))
			return false;
		if (icmpv4_type == null) {
			if (other.icmpv4_type != null)
				return false;
		} else if (!icmpv4_type.equals(other.icmpv4_type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Icmpv4_Match [icmpv4_type=" + icmpv4_type + ", icmpv4_code=" + icmpv4_code + "]";
	}


}
