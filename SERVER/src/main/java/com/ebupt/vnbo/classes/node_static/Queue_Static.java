package com.ebupt.vnbo.classes.node_static;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.flow.flow_static.Duration;
/**
 * 
* 类名: Queue_Static.java <br/>
* 包名 : com.ebupt.vnbo.classes.node_static <br/>
* 详细描述: TODO(队列统计信息实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月19日 <br/>
* 发布版本： V1.0  <br/>
 */

public class Queue_Static {
	@JSONField(name="transmission-errors")
	private long transmission_errors;
	@JSONField(name="transmitted-bytes")
	private long transmitted_bytes;
	@JSONField(name="transmitted-packets")
	private long  transmitted_packets;
	private Duration duration;
	public long getTransmission_errors() {
		return transmission_errors;
	}
	public void setTransmission_errors(long transmission_errors) {
		this.transmission_errors = transmission_errors;
	}
	public long getTransmitted_bytes() {
		return transmitted_bytes;
	}
	public void setTransmitted_bytes(long transmitted_bytes) {
		this.transmitted_bytes = transmitted_bytes;
	}
	public long getTransmitted_packets() {
		return transmitted_packets;
	}
	public void setTransmitted_packets(long transmitted_packets) {
		this.transmitted_packets = transmitted_packets;
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
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + (int) (transmission_errors ^ (transmission_errors >>> 32));
		result = prime * result + (int) (transmitted_bytes ^ (transmitted_bytes >>> 32));
		result = prime * result + (int) (transmitted_packets ^ (transmitted_packets >>> 32));
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
		Queue_Static other = (Queue_Static) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (transmission_errors != other.transmission_errors)
			return false;
		if (transmitted_bytes != other.transmitted_bytes)
			return false;
		if (transmitted_packets != other.transmitted_packets)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Queue_Static [transmission_errors=" + transmission_errors + ", transmitted_bytes=" + transmitted_bytes
				+ ", transmitted_packets=" + transmitted_packets + ", duration=" + duration + "]";
	}
	
}
