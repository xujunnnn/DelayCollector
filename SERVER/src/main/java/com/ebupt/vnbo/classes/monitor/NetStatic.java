package com.ebupt.vnbo.classes.monitor;

/**
 * 
* 类名: NetStatic.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(NetStatic网络监控数据的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */

public class NetStatic implements Cloneable{

	private long timestamp;
	private long packetcount;
	private long bytecount;
	private long packetspeed;
	private long bytespeed;
	public long getPacketcount() {
		return packetcount;
	}
	public NetStatic setPacketcount(long packetcount) {
		this.packetcount = packetcount;
		return this;
	}
	public long getBytecount() {
		return bytecount;
	}
	public NetStatic setBytecount(long bytecount) {
		this.bytecount = bytecount;
		return this;
	}
	public long getPacketspeed() {
		return packetspeed;
	}
	public NetStatic setPacketspeed(long packetspeed) {
		this.packetspeed = packetspeed;
		return this;
	}
	public long getBytespeed() {
		return bytespeed;
	}
	public NetStatic setBytespeed(long bytespeed) {
		this.bytespeed = bytespeed;
		return this;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public NetStatic setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bytecount ^ (bytecount >>> 32));
		result = prime * result + (int) (bytespeed ^ (bytespeed >>> 32));
		result = prime * result + (int) (packetcount ^ (packetcount >>> 32));
		result = prime * result + (int) (packetspeed ^ (packetspeed >>> 32));
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
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
		NetStatic other = (NetStatic) obj;
		if (bytecount != other.bytecount)
			return false;
		if (bytespeed != other.bytespeed)
			return false;
		if (packetcount != other.packetcount)
			return false;
		if (packetspeed != other.packetspeed)
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException  {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "NetStatic [timestamp=" + timestamp + ", packetcount=" + packetcount + ", bytecount=" + bytecount
				+ ", packetspeed=" + packetspeed + ", bytespeed=" + bytespeed + "]";
	}

}
