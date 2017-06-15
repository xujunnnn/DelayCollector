package com.ebupt.vnbo.classes.node_static;

import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.flow.flow_static.Duration;
public class Node_Connector_Static {
	@JSONField(name="receive-frame-error")
	private long receive_frame_error;
	@JSONField(name="receive-over-run-error")
	private long receive_over_run_error;
	@JSONField(name="receive-crc-error")
	private long receive_crc_error;
	@JSONField(name="receive-drops")
	private long receive_drops;
	@JSONField(name="transmit-drops")
	private long transmit_drops;
	@JSONField(name="transmit-errors")
	private long transmit_errors;
	@JSONField(name="receive-errors")
	private long receive_errors;
	@JSONField(name="collision-count")
	private long collision_count;
	private Bytes bytes;
	private Duration duration;
	private Packets packets;
	public long getReceive_frame_error() {
		return receive_frame_error;
	}
	public void setReceive_frame_error(long receive_frame_error) {
		this.receive_frame_error = receive_frame_error;
	}
	public long getReceive_over_run_error() {
		return receive_over_run_error;
	}
	public void setReceive_over_run_error(long receive_over_run_error) {
		this.receive_over_run_error = receive_over_run_error;
	}
	public long getReceive_crc_error() {
		return receive_crc_error;
	}
	public void setReceive_crc_error(long receive_crc_error) {
		this.receive_crc_error = receive_crc_error;
	}
	public long getReceive_drops() {
		return receive_drops;
	}
	public void setReceive_drops(long receive_drops) {
		this.receive_drops = receive_drops;
	}
	public long getTransmit_drops() {
		return transmit_drops;
	}
	public void setTransmit_drops(long transmit_drops) {
		this.transmit_drops = transmit_drops;
	}
	public long getTransmit_errors() {
		return transmit_errors;
	}
	public void setTransmit_errors(long transmit_errors) {
		this.transmit_errors = transmit_errors;
	}
	public long getReceive_errors() {
		return receive_errors;
	}
	public void setReceive_errors(long receive_errors) {
		this.receive_errors = receive_errors;
	}
	public long getCollision_count() {
		return collision_count;
	}
	public void setCollision_count(long collision_count) {
		this.collision_count = collision_count;
	}
	public Bytes getBytes() {
		return bytes;
	}
	public void setBytes(Bytes bytes) {
		this.bytes = bytes;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Packets getPackets() {
		return packets;
	}
	public void setPackets(Packets packets) {
		this.packets = packets;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bytes == null) ? 0 : bytes.hashCode());
		result = prime * result + (int) (collision_count ^ (collision_count >>> 32));
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((packets == null) ? 0 : packets.hashCode());
		result = prime * result + (int) (receive_crc_error ^ (receive_crc_error >>> 32));
		result = prime * result + (int) (receive_drops ^ (receive_drops >>> 32));
		result = prime * result + (int) (receive_errors ^ (receive_errors >>> 32));
		result = prime * result + (int) (receive_frame_error ^ (receive_frame_error >>> 32));
		result = prime * result + (int) (receive_over_run_error ^ (receive_over_run_error >>> 32));
		result = prime * result + (int) (transmit_drops ^ (transmit_drops >>> 32));
		result = prime * result + (int) (transmit_errors ^ (transmit_errors >>> 32));
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
		Node_Connector_Static other = (Node_Connector_Static) obj;
		if (bytes == null) {
			if (other.bytes != null)
				return false;
		} else if (!bytes.equals(other.bytes))
			return false;
		if (collision_count != other.collision_count)
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (packets == null) {
			if (other.packets != null)
				return false;
		} else if (!packets.equals(other.packets))
			return false;
		if (receive_crc_error != other.receive_crc_error)
			return false;
		if (receive_drops != other.receive_drops)
			return false;
		if (receive_errors != other.receive_errors)
			return false;
		if (receive_frame_error != other.receive_frame_error)
			return false;
		if (receive_over_run_error != other.receive_over_run_error)
			return false;
		if (transmit_drops != other.transmit_drops)
			return false;
		if (transmit_errors != other.transmit_errors)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Node_Connector_Static [receive_frame_error=" + receive_frame_error + ", receive_over_run_error="
				+ receive_over_run_error + ", receive_crc_error=" + receive_crc_error + ", receive_drops="
				+ receive_drops + ", transmit_drops=" + transmit_drops + ", transmit_errors=" + transmit_errors
				+ ", receive_errors=" + receive_errors + ", collision_count=" + collision_count + ", bytes=" + bytes
				+ ", duration=" + duration + ", packets=" + packets + "]";
	}

	

}
