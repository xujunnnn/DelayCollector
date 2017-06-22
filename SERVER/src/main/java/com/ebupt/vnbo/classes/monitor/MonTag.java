package com.ebupt.vnbo.classes.monitor;


import com.ebupt.vnbo.classes.enums.Protocol_Type;
/**
 * 
* 类名: MonTag.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(MonTag监控标签的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class MonTag {
	private String flowid;
	private String node;
	private Protocol_Type protocol_Type;
	private String srcmac;
	private String destmac;
	private String inport;
	private String srcip;
	private String destip;
	private String udp_srcport;
	private String udp_destport;
	private String tcp_srcport;
	private String tcp_destport;
	private String queueid;
	public String getFlowid() {
		return flowid;
	}
	public MonTag setFlowid(String flowid) {
		this.flowid = flowid;
		return this;
	}
	public String getNode() {
		return node;
	}
	public MonTag setNode(String node) {
		this.node = node;
		return this;
	}
	public Protocol_Type getProtocol_Type() {
		return protocol_Type;
	}
	public MonTag setProtocol_Type(Protocol_Type protocol_Type) {
		this.protocol_Type = protocol_Type;
		return this;
	}
	public String getSrcmac() {
		return srcmac;
	}
	public MonTag setSrcmac(String srcmac) {
		this.srcmac = srcmac;
		return this;
	}
	public String getDestmac() {
		return destmac;
	}
	public MonTag setDestmac(String destmac) {
		this.destmac = destmac;
		return this;
	}
	public String getInport() {
		return inport;
	}
	public MonTag setInport(String inport) {
		this.inport = inport;
		return this;
	}
	public String getSrcip() {
		return srcip;
	}
	public MonTag setSrcip(String srcip) {
		this.srcip = srcip;
		return this;
	}
	public String getDestip() {
		return destip;
	}
	public MonTag setDestip(String destip) {
		this.destip = destip;
		return this;
	}
	public String getUdp_srcport() {
		return udp_srcport;
	}
	public MonTag setUdp_srcport(String udp_srcport) {
		this.udp_srcport = udp_srcport;
		return this;
	}
	public String getUdp_destport() {
		return udp_destport;
	}
	public MonTag setUdp_destport(String udp_destport) {
		this.udp_destport = udp_destport;
		return this;
	}
	public String getTcp_srcport() {
		return tcp_srcport;
	}
	public MonTag setTcp_srcport(String tcp_srcport) {
		this.tcp_srcport = tcp_srcport;
		return this;
	}
	public String getTcp_destport() {
		return tcp_destport;
	}
	public MonTag setTcp_destport(String tcp_destport) {
		this.tcp_destport = tcp_destport;
		return this;
	}
	public String getQueueid() {
		return queueid;
	}
	public MonTag setQueueid(String queueid) {
		this.queueid = queueid;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destip == null) ? 0 : destip.hashCode());
		result = prime * result + ((destmac == null) ? 0 : destmac.hashCode());
		result = prime * result + ((flowid == null) ? 0 : flowid.hashCode());
		result = prime * result + ((inport == null) ? 0 : inport.hashCode());
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		result = prime * result + ((protocol_Type == null) ? 0 : protocol_Type.hashCode());
		result = prime * result + ((queueid == null) ? 0 : queueid.hashCode());
		result = prime * result + ((srcip == null) ? 0 : srcip.hashCode());
		result = prime * result + ((srcmac == null) ? 0 : srcmac.hashCode());
		result = prime * result + ((tcp_destport == null) ? 0 : tcp_destport.hashCode());
		result = prime * result + ((tcp_srcport == null) ? 0 : tcp_srcport.hashCode());
		result = prime * result + ((udp_destport == null) ? 0 : udp_destport.hashCode());
		result = prime * result + ((udp_srcport == null) ? 0 : udp_srcport.hashCode());
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
		MonTag other = (MonTag) obj;
		if (destip == null) {
			if (other.destip != null)
				return false;
		} else if (!destip.equals(other.destip))
			return false;
		if (destmac == null) {
			if (other.destmac != null)
				return false;
		} else if (!destmac.equals(other.destmac))
			return false;
		if (flowid == null) {
			if (other.flowid != null)
				return false;
		} else if (!flowid.equals(other.flowid))
			return false;
		if (inport == null) {
			if (other.inport != null)
				return false;
		} else if (!inport.equals(other.inport))
			return false;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		if (protocol_Type != other.protocol_Type)
			return false;
		if (queueid == null) {
			if (other.queueid != null)
				return false;
		} else if (!queueid.equals(other.queueid))
			return false;
		if (srcip == null) {
			if (other.srcip != null)
				return false;
		} else if (!srcip.equals(other.srcip))
			return false;
		if (srcmac == null) {
			if (other.srcmac != null)
				return false;
		} else if (!srcmac.equals(other.srcmac))
			return false;
		if (tcp_destport == null) {
			if (other.tcp_destport != null)
				return false;
		} else if (!tcp_destport.equals(other.tcp_destport))
			return false;
		if (tcp_srcport == null) {
			if (other.tcp_srcport != null)
				return false;
		} else if (!tcp_srcport.equals(other.tcp_srcport))
			return false;
		if (udp_destport == null) {
			if (other.udp_destport != null)
				return false;
		} else if (!udp_destport.equals(other.udp_destport))
			return false;
		if (udp_srcport == null) {
			if (other.udp_srcport != null)
				return false;
		} else if (!udp_srcport.equals(other.udp_srcport))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MonTag [flowid=" + flowid + ", node=" + node + ", protocol_Type=" + protocol_Type + ", srcmac=" + srcmac
				+ ", destmac=" + destmac + ", inport=" + inport + ", srcip=" + srcip + ", destip=" + destip
				+ ", udp_srcport=" + udp_srcport + ", udp_destport=" + udp_destport + ", tcp_srcport=" + tcp_srcport
				+ ", tcp_destport=" + tcp_destport + ", queueid=" + queueid + "]";
	}
}