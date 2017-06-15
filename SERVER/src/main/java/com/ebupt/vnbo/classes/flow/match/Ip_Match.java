package com.ebupt.vnbo.classes.flow.match;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* ����: Ip_Match.java <br/>
* ���� : com.ebupt.vnbo.classes.flow.match <br/>
* ��ϸ����: TODO(Ip_Matchʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��12�� <br/>
* �����汾�� V1.0  <br/>
 */
public class Ip_Match {
	@JSONField(name="ip-protocol")
private String ip_protocol;
	@JSONField(name="ip-dscp")
private String ip_dscp;
	@JSONField(name="ip-ecn")
private String ip_ecn;
	@JSONField(name="ip-proto")
private String ip_proto;


public String getIp_protocol() {
		return ip_protocol;
	}

	public void setIp_protocol(String ip_protocol) {
		this.ip_protocol = ip_protocol;
	}

	public String getIp_dscp() {
		return ip_dscp;
	}

	public void setIp_dscp(String ip_dscp) {
		this.ip_dscp = ip_dscp;
	}

	public String getIp_ecn() {
		return ip_ecn;
	}

	public void setIp_ecn(String ip_ecn) {
		this.ip_ecn = ip_ecn;
	}

	public String getIp_proto() {
		return ip_proto;
	}

	public void setIp_proto(String ip_proto) {
		this.ip_proto = ip_proto;
	}

public Ip_Match(){}

/**
 * 
 * ���췽����: 
 * ����: ( Ip_Match����)
 * ������Ա��xujun
 * ����ʱ�䣺
 * @protocol��Э��
 * @dscp:dscp
 * @ecn:ecm
 * @proto:proto
 */
public Ip_Match(String protocol,String dscp,String ecn,String proto){
	if(protocol!=null)
		this.ip_protocol=protocol;
	
	if(proto!=null)
		this.ip_proto=proto;
	if(dscp!=null)
		this.ip_dscp=dscp;
	if(ecn!=null)
		this.ip_ecn=ecn;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ip_dscp == null) ? 0 : ip_dscp.hashCode());
	result = prime * result + ((ip_ecn == null) ? 0 : ip_ecn.hashCode());
	result = prime * result + ((ip_proto == null) ? 0 : ip_proto.hashCode());
	result = prime * result + ((ip_protocol == null) ? 0 : ip_protocol.hashCode());
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
	Ip_Match other = (Ip_Match) obj;
	if (ip_dscp == null) {
		if (other.ip_dscp != null)
			return false;
	} else if (!ip_dscp.equals(other.ip_dscp))
		return false;
	if (ip_ecn == null) {
		if (other.ip_ecn != null)
			return false;
	} else if (!ip_ecn.equals(other.ip_ecn))
		return false;
	if (ip_proto == null) {
		if (other.ip_proto != null)
			return false;
	} else if (!ip_proto.equals(other.ip_proto))
		return false;
	if (ip_protocol == null) {
		if (other.ip_protocol != null)
			return false;
	} else if (!ip_protocol.equals(other.ip_protocol))
		return false;
	return true;
}

@Override
public String toString() {
	return "Ip_Match [ip_protocol=" + ip_protocol + ", ip_dscp=" + ip_dscp + ", ip_ecn=" + ip_ecn + ", ip_proto="
			+ ip_proto + "]";
}


}
