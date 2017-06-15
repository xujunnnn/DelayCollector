package com.ebupt.vnbo.classes.vtopo;

import java.util.HashSet;
import java.util.Objects;
import com.alibaba.fastjson.JSONObject;

import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.UpDate_Mode;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.vtn.Allowed_Hosts;
import com.ebupt.vnbo.classes.vtn.Hostmc;
import com.ebupt.vnbo.classes.vtn.Vbridge;
import com.ebupt.vnbo.classes.vtn.VbridgeRead;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
public class VGroup implements Config,Operational{
	private HashSet<String> host_names=new HashSet<String>();
	private String group_id;
	private String vtoponame;
	public VGroup(){}
	
	public String getVtoponame() {
		return vtoponame;
	}

	public VGroup setVtoponame(String vtoponame) {
		this.vtoponame = vtoponame;
		return this;
	}

	/**
	 * constructor
	 * @param host_names
	 */
	public VGroup(HashSet<String> host_names){
		this.host_names=host_names;
	}
	/**
	 * add a host to this group
	 * @param host_name
	 * @return
	 */
	public VGroup addHost(String host_name){
		this.host_names.add(host_name);
		return this;
	}
	public VGroup addGroup(VGroup group){
		this.host_names.addAll(group.getHost_names());
		return this;
	}
	
	public HashSet<String> getHost_names() {
		return host_names;
	}

	public VGroup setHost_names(HashSet<String> host_names) {
		this.host_names = host_names;
		return this;
	}

	public String getGroup_id() {
		return group_id;
	}

	public VGroup setGroup_id(String group_id) {
		this.group_id = group_id;
		return this;
	}

	public static void main(String args[]){
		VGroup vGroup=new VGroup().setGroup_id("g1");
		vGroup.addHost("host1")
			  .addHost("host2")
			  .addHost("host3");
		JSONObject json=(JSONObject)JSONObject.toJSON(vGroup);
		System.out.println(json.toJSONString());
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.host_names,this.group_id);
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
		VGroup other=(VGroup) obj;
		return Objects.equals(this.host_names, other.getHost_names()) && Objects.equals(this.group_id, other.getGroup_id());
	}
/**
	@Override
	public void send(String node) throws VbridgeException, Mac_MapFailException, TopoReadFailException
		{
			// TODO Auto-generated method stub
		TopoService topoService=new TopoServiceImpl();
		Vbridge vbridge=new Vbridge();
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		for(String name:host_names){
			allowed_Hosts.addHost(topoService.get_host_from_name(name).getMac()+"@"+"0");
			}
		vbridge.setTenant_name(vtoponame)
			   .setBridge_name(group_id)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .Set_Mac_Map(allowed_Hosts, null)
			   .send(null);				
	}

	@Override
	public void remove(String node) throws VtopoException, VbridgeException {
		// TODO Auto-generated method stub
		Vbridge vbridge=new Vbridge();
		vbridge.setBridge_name(group_id)
					  .setTenant_name(vtoponame)
					  .remove(null);
	}

	@Override
	public VGroup read(String node) throws VtopoException, VbridgeException {
		// TODO Auto-generated method stub
		VbridgeRead vbridgeRead=new VbridgeRead().setTenant_name(vtoponame).setBridge_name(group_id);
		vbridgeRead=vbridgeRead.read(null);
		VGroup vGroup=new VGroup().setGroup_id(vbridgeRead.getBridge_name());
		HashSet<String> host_names=new HashSet<>();
		if(vbridgeRead.getMac_Map()!=null){
		for(Hostmc hostmc:vbridgeRead.getMac_Map().getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
				host_names.add(hostmc.getHostName());
		}
		}
		vGroup.setHost_names(host_names);
		return vGroup;
		
	}
	**/

	@Override
	public Operational read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		VbridgeRead vbridgeRead=new VbridgeRead().setTenant_name(vtoponame).setBridge_name(group_id);
		vbridgeRead=vbridgeRead.read(null);
		VGroup vGroup=new VGroup().setGroup_id(vbridgeRead.getBridge_name());
		HashSet<String> host_names=new HashSet<>();
		if(vbridgeRead.getMac_Map()!=null){
		for(Hostmc hostmc:vbridgeRead.getMac_Map().getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
				host_names.add(hostmc.getHostName());
		}
		}
		vGroup.setHost_names(host_names);
		return vGroup;
	}

	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		TopologyService topoService=new TopologyServiceImpl();
		Vbridge vbridge=new Vbridge();
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		for(String name:host_names){
			allowed_Hosts.addHost(topoService.get_host_from_name(name).getMac()+"@"+"0");
			}
		vbridge.setTenant_name(vtoponame)
			   .setBridge_name(group_id)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .Set_Mac_Map(allowed_Hosts, null)
			   .send(null);				
		
	}

	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		Vbridge vbridge=new Vbridge();
		vbridge.setBridge_name(group_id)
					  .setTenant_name(vtoponame)
					  .remove(null);
		
	}
}
