package com.ebupt.vnbo.classes.monitor;
/**
 * 
* 类名: Latency.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(Latency实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Latency {
	private String srcnode;
	private String destnode;
	private long latency;
	
	public String getSrcnode() {
		return srcnode;
	}
	public void setSrcnode(String srcnode) {
		this.srcnode = srcnode;
	}
	public String getDestnode() {
		return destnode;
	}
	public void setDestnode(String destnode) {
		this.destnode = destnode;
	}
	public long getLatency() {
		return latency;
	}
	public void setLatency(long latency) {
		this.latency = latency;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destnode == null) ? 0 : destnode.hashCode());
		result = prime * result + (int) (latency ^ (latency >>> 32));
		result = prime * result + ((srcnode == null) ? 0 : srcnode.hashCode());
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
		Latency other = (Latency) obj;
		if (destnode == null) {
			if (other.destnode != null)
				return false;
		} else if (!destnode.equals(other.destnode))
			return false;
		if (latency != other.latency)
			return false;
		if (srcnode == null) {
			if (other.srcnode != null)
				return false;
		} else if (!srcnode.equals(other.srcnode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Latency [srcnode=" + srcnode + ", destnode=" + destnode + ", latency=" + latency + "]";
	}
	
	


}
