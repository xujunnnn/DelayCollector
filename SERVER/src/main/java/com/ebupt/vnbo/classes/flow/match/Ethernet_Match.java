package com.ebupt.vnbo.classes.flow.match;

import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;

public class Ethernet_Match {
	@JSONField(name="ethernet-source")
	private Ethernet_source ethernet_source;
	@JSONField(name="ethernet-destination")
	private Ethernet_destination ethernet_destination;
	@JSONField(name="ethernet-type")
	private Ethernet_type ethernet_type;
	public Ethernet_source getEthernet_source() {
		return ethernet_source;
	}
	public void setEthernet_source(Ethernet_source ethernet_source) {
		this.ethernet_source = ethernet_source;
	}
	public Ethernet_destination getEthernet_destination() {
		return ethernet_destination;
	}
	public void setEthernet_destination(Ethernet_destination ethernet_destination) {
		this.ethernet_destination = ethernet_destination;
	}
	public Ethernet_type getEthernet_type() {
		return ethernet_type;
	}
	public void setEthernet_type(Ethernet_type ethernet_type) {
		this.ethernet_type = ethernet_type;
	}
	public Ethernet_Match(){}
	/**
	 * 
	 * 构造方法名: 
	 * 描述: (直接赋值构造Ethernet_Match)
	 * 开发人员：xujun
	 * 创建时间：
	 * 说明参数含义
	 */
	public Ethernet_Match (String src,String dest,String ethetType){
		
		
		if(src!=null){
			Ethernet_source ethernet_source=new Ethernet_source();
			ethernet_source.setAddress(src);
			this.setEthernet_source(ethernet_source);
		}
		if(dest!=null){
			Ethernet_destination ethernet_destination=new Ethernet_destination();
			ethernet_destination.setAddress(dest);
			this.setEthernet_destination(ethernet_destination);
		}
		//set the default ethernettype
		Ethernet_type ethernet_type=new Ethernet_type();
		ethernet_type.setType(ethetType);
		
		
		this.setEthernet_type(ethernet_type);
		
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(ethernet_source,ethernet_destination,ethernet_type);
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		Ethernet_Match other=(Ethernet_Match) obj;
			return Objects.equals(this.ethernet_source, other.getEthernet_source()) && Objects.equals(this.ethernet_destination, other.getEthernet_destination())
					&& Objects.equals(this.ethernet_type, other.ethernet_type);
		
	}                                                                            

}

