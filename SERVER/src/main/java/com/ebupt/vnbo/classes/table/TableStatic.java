package com.ebupt.vnbo.classes.table;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: TableStatic.java <br/>
* 包名 : com.ebupt.vnbo.classes.table <br/>
* 详细描述: TODO(TableStatic实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class TableStatic {
	@JSONField(name="packets-matched")
	private String packets_matched;
	@JSONField(name="packets-looked-up")
	private String packets_looked_up;
	@JSONField(name="active-flows")
	private String active_flows;
	public String getPackets_matched() {
		return packets_matched;
	}
	public void setPackets_matched(String packets_matched) {
		this.packets_matched = packets_matched;
	}
	public String getPackets_looked_up() {
		return packets_looked_up;
	}
	public void setPackets_looked_up(String packets_looked_up) {
		this.packets_looked_up = packets_looked_up;
	}
	public String getActive_flows() {
		return active_flows;
	}
	public void setActive_flows(String active_flows) {
		this.active_flows = active_flows;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active_flows == null) ? 0 : active_flows.hashCode());
		result = prime * result + ((packets_looked_up == null) ? 0 : packets_looked_up.hashCode());
		result = prime * result + ((packets_matched == null) ? 0 : packets_matched.hashCode());
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
		TableStatic other = (TableStatic) obj;
		if (active_flows == null) {
			if (other.active_flows != null)
				return false;
		} else if (!active_flows.equals(other.active_flows))
			return false;
		if (packets_looked_up == null) {
			if (other.packets_looked_up != null)
				return false;
		} else if (!packets_looked_up.equals(other.packets_looked_up))
			return false;
		if (packets_matched == null) {
			if (other.packets_matched != null)
				return false;
		} else if (!packets_matched.equals(other.packets_matched))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TableStatic [packets_matched=" + packets_matched + ", packets_looked_up=" + packets_looked_up
				+ ", active_flows=" + active_flows + "]";
	}
	

}
