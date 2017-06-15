package com.ebupt.vnbo.classes.flow.flow_static;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Flow_Statistic.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.flow_static <br/>
* 详细描述: TODO(用于表示流表中的统计信息的实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Flow_Statistic {
	@JSONField(serialize=false)
	private String flowid;
	public String getFlowid() {
		return flowid;
	}
	public Flow_Statistic setFlowid(String flowid) {
		this.flowid = flowid;
		return this;
	}
	@JSONField(name="packet-count")
	private long packet_count;
	@JSONField(name="byte-count")
	private long byte_count;
	private Duration duration;
	public long getPacket_count() {
		return packet_count;
	}
	public void setPacket_count(long packet_count) {
		this.packet_count = packet_count;
	}
	public long getByte_count() {
		return byte_count;
	}
	public void setByte_count(long byte_count) {
		this.byte_count = byte_count;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (byte_count ^ (byte_count >>> 32));
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((flowid == null) ? 0 : flowid.hashCode());
		result = prime * result + (int) (packet_count ^ (packet_count >>> 32));
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
		Flow_Statistic other = (Flow_Statistic) obj;
		if (byte_count != other.byte_count)
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (flowid == null) {
			if (other.flowid != null)
				return false;
		} else if (!flowid.equals(other.flowid))
			return false;
		if (packet_count != other.packet_count)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Flow_Statistic [flowid=" + flowid + ", packet_count=" + packet_count + ", byte_count=" + byte_count
				+ ", duration=" + duration + "]";
	}

}

