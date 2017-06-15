package com.ebupt.vnbo.classes.flow.match;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Ethernet_destination.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.match <br/>
* 详细描述: TODO(Ethernet_destination的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Ethernet_destination {
	private String address;
	@JSONField(name="mask")
	private String Mask;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMask() {
		return Mask;
	}
	public void setMask(String mask) {
		Mask = mask;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mask == null) ? 0 : Mask.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Ethernet_destination other = (Ethernet_destination) obj;
		if (Mask == null) {
			if (other.Mask != null)
				return false;
		} else if (!Mask.equals(other.Mask))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ethernet_destination [address=" + address + ", Mask=" + Mask + "]";
	}

}
