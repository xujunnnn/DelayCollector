package com.ebupt.vnbo.classes.flow.match;


import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Match.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.match <br/>
* 详细描述: TODO(流表中match的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Match {

	@JSONField(name="in-port")
	private String in_port;
	@JSONField(name="in-phy-port")
	private String in_phy_port;
	@JSONField(name="ipv4-source")
	private String ipv4_source;
	@JSONField(name="ipv4-destination")
	private String ipv4_destination;
	@JSONField(name="tcp-source-port")
	private String tcp_source_port;
	@JSONField(name="tcp-destination-port")
	private String tcp_destination_port;
	@JSONField(name="ethernet-match")
	private Ethernet_Match ethernet_Match;
	@JSONField(name="icmpv4-match")
	private Icmpv4_Match icmpv4_Match;
	@JSONField(name="ip-match")
	private Ip_Match ip_Match;
	@JSONField(name="protocol-match-fields")
	private Protocol_Match_Fields protocol_Match_Fields;
	@JSONField(name="tcp-flag-match")
	private Tcp_Flag_Match tcp_Flag_Match;
	@JSONField(name="vlan-match")
	private Vlan_Match vlan_Match;
	@JSONField(name="udp-source-port")
	private String udp_source_port;
	@JSONField(name="udp-destination-port")
	private String udp_destination_port;
	public Ethernet_Match getEthernet_Match() {
		return ethernet_Match;
	}
	public void setEthernet_Match(Ethernet_Match ethernet_Match) {
		this.ethernet_Match = ethernet_Match;
	}
	public Icmpv4_Match getIcmpv4_Match() {
		return icmpv4_Match;
	}
	public void setIcmpv4_Match(Icmpv4_Match icmpv4_Match) {
		this.icmpv4_Match = icmpv4_Match;
	}
	public Ip_Match getIp_Match() {
		return ip_Match;
	}
	public void setIp_Match(Ip_Match ip_Match) {
		this.ip_Match = ip_Match;
	}
	public Protocol_Match_Fields getProtocol_Match_Fields() {
		return protocol_Match_Fields;
	}
	public void setProtocol_Match_Fields(Protocol_Match_Fields protocol_Match_Fields) {
		this.protocol_Match_Fields = protocol_Match_Fields;
	}
	public Tcp_Flag_Match getTcp_Flag_Match() {
		return tcp_Flag_Match;
	}
	public void setTcp_Flag_Match(Tcp_Flag_Match tcp_Flag_Match) {
		this.tcp_Flag_Match = tcp_Flag_Match;
	}
	public Vlan_Match getVlan_Match() {
		return vlan_Match;
	}
	public void setVlan_Match(Vlan_Match vlan_Match) {
		this.vlan_Match = vlan_Match;
	}
	public String getIn_port() {
		return in_port;
	}
	public Match setIn_port(String in_port) {
		this.in_port = in_port;
		return this;
	}
	public String getIn_phy_port() {
		return in_phy_port;
	}
	public Match setIn_phy_port(String in_phy_port) {
		this.in_phy_port = in_phy_port;
		return this;
	}
	public String getIpv4_source() {
		return ipv4_source;
	}
	public Match setIpv4_source(String ipv4_source) {
		this.ipv4_source = ipv4_source;
		return this;
	}
	public String getIpv4_destination() {
		return ipv4_destination;
	}
	public Match setIpv4_destination(String ipv4_destination) {
		this.ipv4_destination = ipv4_destination;
		return this;
	}
	public String getTcp_source_port() {
		return tcp_source_port;
	}
	public Match setTcp_source_port(String tcp_source_port) {
		this.tcp_source_port = tcp_source_port;
		return this;
	}
	public String getTcp_destination_port() {
		return tcp_destination_port;
	}
	public Match setTcp_destination_port(String tcp_destination_port) {
		this.tcp_destination_port = tcp_destination_port;
		return this;
	}
	public String getUdp_source_port() {
		return udp_source_port;
	}
	public Match setUdp_source_port(String udp_source_port) {
		this.udp_source_port = udp_source_port;
		return this;
	}
	public String getUdp_destination_port() {
		return udp_destination_port;
	}
	public Match setUdp_destination_port(String udp_destination_port) {
		this.udp_destination_port = udp_destination_port;
		return this;
	}
	/**
	 * 
	* 方法名：Set_Mac_Match</br>
	* 详述：TODO（简单方法可一句话概述）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月12日  </br>
	* @param src 二层源mac
	* @param dest 二层目的mac
	* @param etherType 以太网类型
	* @return match
	* @throws
	 */
	public Match Set_Mac_Match(String src,String dest,String etherType){
	    Ethernet_Match ethernet_Match=new Ethernet_Match(src,dest,etherType);
	    this.setEthernet_Match(ethernet_Match);
		return this;
		
	}
	/**
	 * 
	 * @param icmpv4_type
	 * @param icmpv4_code
	 * @return
	 */
	public Match Set_Icmpv4_Match(String icmpv4_type,String icmpv4_code){
		return this;
	}
	/**
	 * Set Vlan match 
	 * @param vlanpcp the pcp 
	 * @param vlanid the specified vlanid to match
	 * @return Match this match
	 */
	public Match Set_Vlan_Match(String vlanpcp,String vlanid){
		Vlan_Match vlan_Match=new Vlan_Match(vlanpcp, vlanid);
		this.setVlan_Match(vlan_Match);
		return this;
	}
	/**
	 *
	 * @param protocol
	 * @param dscp
	 * @param ecn
	 * @param proto
	 * @return Match
	 */
    public Match Set_Ip_Match(String protocol,String dscp,String ecn,String proto){
    	Ip_Match ip_Match=new Ip_Match(protocol, dscp, ecn, proto);
    	this.ip_Match=ip_Match;
    	return this;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ethernet_Match == null) ? 0 : ethernet_Match.hashCode());
		result = prime * result + ((icmpv4_Match == null) ? 0 : icmpv4_Match.hashCode());
		result = prime * result + ((in_phy_port == null) ? 0 : in_phy_port.hashCode());
		result = prime * result + ((in_port == null) ? 0 : in_port.hashCode());
		result = prime * result + ((ip_Match == null) ? 0 : ip_Match.hashCode());
		result = prime * result + ((ipv4_destination == null) ? 0 : ipv4_destination.hashCode());
		result = prime * result + ((ipv4_source == null) ? 0 : ipv4_source.hashCode());
		result = prime * result + ((protocol_Match_Fields == null) ? 0 : protocol_Match_Fields.hashCode());
		result = prime * result + ((tcp_Flag_Match == null) ? 0 : tcp_Flag_Match.hashCode());
		result = prime * result + ((tcp_destination_port == null) ? 0 : tcp_destination_port.hashCode());
		result = prime * result + ((tcp_source_port == null) ? 0 : tcp_source_port.hashCode());
		result = prime * result + ((udp_destination_port == null) ? 0 : udp_destination_port.hashCode());
		result = prime * result + ((udp_source_port == null) ? 0 : udp_source_port.hashCode());
		result = prime * result + ((vlan_Match == null) ? 0 : vlan_Match.hashCode());
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
		Match other = (Match) obj;
		if (ethernet_Match == null) {
			if (other.ethernet_Match != null)
				return false;
		} else if (!ethernet_Match.equals(other.ethernet_Match))
			return false;
		if (icmpv4_Match == null) {
			if (other.icmpv4_Match != null)
				return false;
		} else if (!icmpv4_Match.equals(other.icmpv4_Match))
			return false;
		if (in_phy_port == null) {
			if (other.in_phy_port != null)
				return false;
		} else if (!in_phy_port.equals(other.in_phy_port))
			return false;
		if (in_port == null) {
			if (other.in_port != null)
				return false;
		} else if (!in_port.equals(other.in_port))
			return false;
		if (ip_Match == null) {
			if (other.ip_Match != null)
				return false;
		} else if (!ip_Match.equals(other.ip_Match))
			return false;
		if (ipv4_destination == null) {
			if (other.ipv4_destination != null)
				return false;
		} else if (!ipv4_destination.equals(other.ipv4_destination))
			return false;
		if (ipv4_source == null) {
			if (other.ipv4_source != null)
				return false;
		} else if (!ipv4_source.equals(other.ipv4_source))
			return false;
		if (protocol_Match_Fields == null) {
			if (other.protocol_Match_Fields != null)
				return false;
		} else if (!protocol_Match_Fields.equals(other.protocol_Match_Fields))
			return false;
		if (tcp_Flag_Match == null) {
			if (other.tcp_Flag_Match != null)
				return false;
		} else if (!tcp_Flag_Match.equals(other.tcp_Flag_Match))
			return false;
		if (tcp_destination_port == null) {
			if (other.tcp_destination_port != null)
				return false;
		} else if (!tcp_destination_port.equals(other.tcp_destination_port))
			return false;
		if (tcp_source_port == null) {
			if (other.tcp_source_port != null)
				return false;
		} else if (!tcp_source_port.equals(other.tcp_source_port))
			return false;
		if (udp_destination_port == null) {
			if (other.udp_destination_port != null)
				return false;
		} else if (!udp_destination_port.equals(other.udp_destination_port))
			return false;
		if (udp_source_port == null) {
			if (other.udp_source_port != null)
				return false;
		} else if (!udp_source_port.equals(other.udp_source_port))
			return false;
		if (vlan_Match == null) {
			if (other.vlan_Match != null)
				return false;
		} else if (!vlan_Match.equals(other.vlan_Match))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Match [in_port=" + in_port + ", in_phy_port=" + in_phy_port + ", ipv4_source=" + ipv4_source
				+ ", ipv4_destination=" + ipv4_destination + ", tcp_source_port=" + tcp_source_port
				+ ", tcp_destination_port=" + tcp_destination_port + ", ethernet_Match=" + ethernet_Match
				+ ", icmpv4_Match=" + icmpv4_Match + ", ip_Match=" + ip_Match + ", protocol_Match_Fields="
				+ protocol_Match_Fields + ", tcp_Flag_Match=" + tcp_Flag_Match + ", vlan_Match=" + vlan_Match
				+ ", udp_source_port=" + udp_source_port + ", udp_destination_port=" + udp_destination_port + "]";
	}
    


}
