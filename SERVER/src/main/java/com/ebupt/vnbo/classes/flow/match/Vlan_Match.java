package com.ebupt.vnbo.classes.flow.match;

import java.util.Objects;
import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* ����: Vlan_Match.java <br/>
* ���� : com.ebupt.vnbo.classes.flow.match <br/>
* ��ϸ����: TODO(Vlan_Match��ʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��12�� <br/>
* �����汾�� V1.0  <br/>
 */
public class Vlan_Match {
	@JSONField(name="vlan-pcp")
	private String vlan_pcp;
	@JSONField(name="vlan-id")
	private Vlan_id vlan_id;

	public String getVlan_pcp() {
		return vlan_pcp;
	}

	public void setVlan_pcp(String vlan_pcp) {
		this.vlan_pcp = vlan_pcp;
	}

	public Vlan_id getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(Vlan_id vlan_id) {
		this.vlan_id = vlan_id;
	}
	
	public Vlan_Match(){}
	
	/**
	 * 
	 * ���췽����: 
	 * ����: (ֱ����vlanpcp��vlanid����Vlan_Match)
	 * ������Ա��xujun
	 * ����ʱ�䣺
	 * ˵����������
	 */
	public Vlan_Match(String vlanpcp,String vlanid){
		if(vlanid!=null)
		{
			Vlan_id vlan_id=new Vlan_id(vlanid);
			this.vlan_id=vlan_id;
		}
		if(vlanpcp!=null){
			this.vlan_pcp=vlanpcp;
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(vlan_id,vlan_pcp);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==this) 
			return true;
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		Vlan_Match other=(Vlan_Match) obj;
			return Objects.equals(this.vlan_pcp,other.getVlan_pcp()) && Objects.equals(this.vlan_id,other.getVlan_id());
	}
}



//class vlanid
class Vlan_id{
	private String vlan_id_present="true";
	private String vlan_id;
	
	public Vlan_id(){}
	
	/**
	 * 
	 * @param vlanid
	 */
	public Vlan_id(String vlanid){
		this.vlan_id=vlanid;
	}
	public String getVlan_id_present() {
		return vlan_id_present;
	}
	public void setVlan_id_present(String vlan_id_present) {
		this.vlan_id_present = vlan_id_present;
	}
	public String getVlan_id() {
		return vlan_id;
	}
	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		
		return Objects.hash(vlan_id,vlan_id_present);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==this) 
			return true;
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		Vlan_id other=(Vlan_id) obj;
			return Objects.equals(this.vlan_id, other.getVlan_id()) && Objects.equals(this.vlan_id_present,other.getVlan_id_present());
	}
	
}