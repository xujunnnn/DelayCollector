package com.ebupt.vnbo.classes.vtopo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.UpDate_Mode;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.vtn.Hostmc;
import com.ebupt.vnbo.classes.vtn.VbridgeRead;
import com.ebupt.vnbo.classes.vtn.Vtn;
import com.ebupt.vnbo.classes.vtn.VtnRead;
import com.ebupt.vnbo.util.HttpUtil;
import com.ebupt.vnbo.util.VTopoUtil;

/**
 * 
 * @author xu
 *
 */
public class VTopo implements Config,Operational{
	
	private HashSet<VGroup> vGroups=new HashSet<VGroup>();
	private HashSet<VLink> vLinks=new HashSet<VLink>();
	private String Vtopo_name;
	public HashSet<VGroup> getvGroups() {
		return vGroups;
	}
	public VTopo setvGroups(HashSet<VGroup> vGroups) {
		this.vGroups = vGroups;
		return this;
	}
	public HashSet<VLink> getvLinks() {
		return vLinks;
	}
	public VTopo setvLinks(HashSet<VLink> vLinks) {
		this.vLinks = vLinks;
		return this;
	}
	public String getVtopo_name() {
		return Vtopo_name;
	}
	public VTopo setVtopo_name(String vtopo_name) {
		Vtopo_name = vtopo_name;
		return this;
	}
	
	public VTopo addGroup(VGroup vGroup){
		this.vGroups.add(vGroup);
		return this;
	}
	
	public VTopo addLink(VLink vLink){
		this.vLinks.add(vLink);
		return this;
	}

	
	
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(vGroups,vLinks,Vtopo_name);
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
		VTopo other=(VTopo) obj;
			return Objects.deepEquals(this.vGroups, other.getvGroups())  &&
							Objects.deepEquals(this.vLinks, other.getvLinks()) && 
							Objects.equals(this.Vtopo_name, other.getVtopo_name());
	}
	/**
	@Override
	public void send(String node) throws VtopoException ,VtnException, VbridgeException, Mac_MapFailException, TopoReadFailException {
		// TODO Auto-generated method stub
		VTopoUtil.initVlan(Vtopo_name);
		Vtn vtn=new Vtn();
		vtn.setTenant_name(Vtopo_name)
		   .setHard_timeout("10000").setIdle_timeout("1000")
		   .setOperation(OperationType.SET)
		   .setUpdate_mode(UpDate_Mode.CREATE)
		   .send(null);
		for(VGroup vGroup:vGroups){
			vGroup.setVtoponame(Vtopo_name).send(null);
		}
		for(VLink vLink:vLinks){
			vLink.setVtoponame(Vtopo_name).send(null);
		}
	}
	@Override
	public void remove(String node) throws EntrySetException {
		// TODO Auto-generated method stub
		Vtn vtn=new Vtn();
		vtn.setTenant_name(Vtopo_name);
		vtn.remove(null);
		
	}
	@Override
	public VTopo read(String node) throws EntrySetException {
		// TODO Auto-generated method stub
		VtnRead vtnRead=new VtnRead().setTenant_name(Vtopo_name);
		vtnRead=vtnRead.read(null);
		VTopo vTopo=this.Adapter(vtnRead);
		return vTopo;
	}
	/**
	 * 读取所有vtopos
	 * @return
	 * @throws OperationalException 
	 * @throws VtopoException
	 * @throws VbridgeException
	 * @throws VtnException
	 */
	public HashSet<VTopo> readAll() throws OperationalException {
		HashSet<VTopo> vTopos=new HashSet<>();
		String url="http://"+OperationalUrl+"/vtn:vtns";
		String response[]=HttpUtil.Get_request(url);
		String responsecode=response[0];
		if(!"200".equals(responsecode)  &&  !"201".equals(responsecode))
				throw new OperationalException("failed to read the VTopo");
		String responsebody=response[1];
		JSONObject resultjson=JSONObject.parseObject(responsebody);
		resultjson=resultjson.getJSONObject("vtns");
		JSONArray resultArray=resultjson.getJSONArray("vtn");
		if(resultArray!=null){
		ArrayList<VtnRead> vtnReads=(ArrayList<VtnRead>) JSON.parseArray(resultArray.toJSONString(), VtnRead.class);
		for(VtnRead vtnRead:vtnReads){
			VTopo vTopo=Adapter(vtnRead);
			vTopos.add(vTopo);
		}
		}
		return vTopos;
	}

	/**
	 * 将读取的vtn映射成vtopo
	 * @param vtnRead
	 * @return
	 * @throws VtopoException
	 * @throws VbridgeException
	 */
	private VTopo Adapter(VtnRead vtnRead) {
		VTopo vTopo=new VTopo().setVtopo_name(vtnRead.getTenant_name());
		for(VbridgeRead vbridgeRead:vtnRead.getVbridgeReads()){
			if(!vbridgeRead.getBridge_name().contains("_")){
			VGroup vGroup=new VGroup().setGroup_id(vbridgeRead.getBridge_name()).setVtoponame(vtnRead.getTenant_name());
			HashSet<String> host_names=new HashSet<>();
			if(vbridgeRead.getMac_Map()!=null){
			for(Hostmc hostmc:vbridgeRead.getMac_Map().getMac_Map_Config().getAllowedHosts().getVlan_host_desc_list()){
					host_names.add(hostmc.getHostName());
			}
			}
			vGroup.setHost_names(host_names);
			vTopo.addGroup(vGroup);
			}
			else{
				VLink vLink=new VLink().setLink_id(vbridgeRead.getBridge_name()).setVtoponame(vtnRead.getTenant_name());
				String linkname=vbridgeRead.getBridge_name();
				String groupA=linkname.split("_")[0];
				String groupB=linkname.split("_")[1];
				int vlanid=Integer.parseInt(linkname.split("_")[2]);
				vLink.setGroupA(groupA).setGroupB(groupB).setVlanid(vlanid);
				vTopo.addLink(vLink);
			}
		}
			return vTopo;

	}

	@Override
	public VTopo read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		VtnRead vtnRead=new VtnRead().setTenant_name(Vtopo_name);
		vtnRead=vtnRead.read(null);
		VTopo vTopo=this.Adapter(vtnRead);
		return vTopo;
	}
	
	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		VTopoUtil.initVlan(Vtopo_name);
		Vtn vtn=new Vtn();
		vtn.setTenant_name(Vtopo_name)
		   .setHard_timeout(VTN_FLOW_HARD).setIdle_timeout(VTN_FLOW_IDLE)
		   .setOperation(OperationType.SET)
		   .setUpdate_mode(UpDate_Mode.CREATE)
		   .send(null);
		for(VGroup vGroup:vGroups){
			vGroup.setVtoponame(Vtopo_name).send(null);
		}
		for(VLink vLink:vLinks){
			vLink.setVtoponame(Vtopo_name).send(null);
		}
		
	}
	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		Vtn vtn=new Vtn();
		vtn.setTenant_name(Vtopo_name);
		vtn.remove(null);
		
	}
	

}
