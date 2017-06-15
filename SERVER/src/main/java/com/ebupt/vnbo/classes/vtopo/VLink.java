package com.ebupt.vnbo.classes.vtopo;


import java.util.Objects;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.UpDate_Mode;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.vtn.Allowed_Hosts;
import com.ebupt.vnbo.classes.vtn.Hostmc;
import com.ebupt.vnbo.classes.vtn.Mac_Map;
import com.ebupt.vnbo.classes.vtn.Vbridge;
import com.ebupt.vnbo.classes.vtn.VbridgeRead;
import com.ebupt.vnbo.util.VTopoUtil;


/**
 * 
 * @author xu
 *
 */
public class VLink implements Config,Operational{
	private String link_id;
	private String groupA;
	private String groupB;
	@JSONField(deserialize=true)
	private int vlanid;
	
	public int getVlanid() {
		return vlanid;
	}
	public VLink setVlanid(int vlanid) {
		this.vlanid = vlanid;
		return this;
	}
	private String vtoponame;
	public String getLink_id() {
		return link_id;
	}
	public String getGroupA() {
		return groupA;
	}
	public VLink setGroupA(String groupA) {
		this.groupA = groupA;
		return this;
	}
	public String getGroupB() {
		return groupB;
	}
	public VLink setGroupB(String groupB) {
		this.groupB = groupB;
		return this;
	}
	public VLink setLink_id(String link_id) {
		this.link_id = link_id;
		return this;
	}	
	public String getVtoponame() {
		return vtoponame;
	}
	public VLink setVtoponame(String vtoponame) {
		this.vtoponame = vtoponame;
		return this;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(link_id,groupA,groupB,vtoponame);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if(this.getClass()!=obj.getClass())
			return false;
		VLink other=(VLink) obj;
			return Objects.equals(this.link_id,other.getLink_id()) &&
					        Objects.equals(this.groupA, other.getGroupA()) &&
					        Objects.equals(this.groupB, other.getGroupB()) &&
					        Objects.equals(this.vtoponame,other.getVtoponame());
	}
	/**
	@throws ODL_IO_Exception 
	 * @Override
	public void send(String node) throws Mac_MapFailException, VbridgeException  {
		// TODO Auto-generated method stub
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		//读取vgropu中的主机列表
		Mac_Map mac_Map=new Mac_Map();
		mac_Map.setTenant_name(vtoponame).setBridge_name(groupA);
		mac_Map=mac_Map.read(null);
		 this.vlanid=VTopoUtil.get_Vlan(vtoponame);
		for(Hostmc host:mac_Map.getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		//读取vgropu中的主机列表
		mac_Map.setTenant_name(vtoponame).setBridge_name(groupB);
		mac_Map=mac_Map.read(null);
		for(Hostmc host:mac_Map.getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		//linkid改为groupname+vlanid
		link_id=link_id+"_"+vlanid;
		Vbridge vbridge=new Vbridge();
		vbridge.setTenant_name(vtoponame)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .setBridge_name(link_id)
			   .Set_Mac_Map(allowed_Hosts,null)
			   .send(null);
	}
	@Override
	public void remove(String node) throws VbridgeException  {
		// TODO Auto-generated method stub
		Vbridge vbridge=new Vbridge();
		//linkid改为groupname+vlanid
		link_id=link_id+"_"+vlanid;
		vbridge.setBridge_name(link_id).setTenant_name(vtoponame).remove(null);
		
	}
	@Override
	public VLink read(String node) throws VbridgeException  {
		// TODO Auto-generated method stub
		VbridgeRead vbridgeRead=new VbridgeRead().setTenant_name(vtoponame).setBridge_name(link_id);
		vbridgeRead=vbridgeRead.read(null);
		VLink vLink=new VLink().setLink_id(vbridgeRead.getBridge_name());
		String linkname=vbridgeRead.getBridge_name();
		String groupA=linkname.split("_")[0];
		String groupB=linkname.split("_")[1];
		int vlanid=Integer.parseInt(linkname.split("_")[2]);
		vLink.setGroupA(groupA).setGroupB(groupB).setVlanid(vlanid);
		return vLink;
	}
	**/
	public static void main(String []args) throws ODL_IO_Exception {
		VLink vLink=new VLink().setLink_id("g1_g2_1").setVtoponame("v1");
		vLink=vLink.read(null);
		
	}
	@Override
	public VLink read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		VbridgeRead vbridgeRead=new VbridgeRead().setTenant_name(vtoponame).setBridge_name(link_id);
		vbridgeRead=vbridgeRead.read(null);
		VLink vLink=new VLink().setLink_id(vbridgeRead.getBridge_name());
		String linkname=vbridgeRead.getBridge_name();
		String groupA=linkname.split("_")[0];
		String groupB=linkname.split("_")[1];
		int vlanid=Integer.parseInt(linkname.split("_")[2]);
		vLink.setGroupA(groupA).setGroupB(groupB).setVlanid(vlanid);
		return vLink;
	}
	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		//读取vgropu中的主机列表
		Mac_Map mac_Map=new Mac_Map();
		mac_Map.setTenant_name(vtoponame).setBridge_name(groupA);
		mac_Map=mac_Map.read(null);
		 this.vlanid=VTopoUtil.get_Vlan(vtoponame);
		for(Hostmc host:mac_Map.getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		//读取vgropu中的主机列表
		mac_Map.setTenant_name(vtoponame).setBridge_name(groupB);
		mac_Map=mac_Map.read(null);
		for(Hostmc host:mac_Map.getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
			allowed_Hosts.addHost(host.getHost().split("@")[0]+"@"+vlanid);
		}
		//linkid改为groupname+vlanid
		link_id=link_id+"_"+vlanid;
		Vbridge vbridge=new Vbridge();
		vbridge.setTenant_name(vtoponame)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .setBridge_name(link_id)
			   .Set_Mac_Map(allowed_Hosts,null)
			   .send(null);
		
	}
	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		Vbridge vbridge=new Vbridge();
		//linkid改为groupname+vlanid
		link_id=link_id+"_"+vlanid;
		vbridge.setBridge_name(link_id).setTenant_name(vtoponame).remove(null);
	}
}
