package com.ebupt.vnbo.serviceImpl.vtopo;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.State;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.topology.Host;
import com.ebupt.vnbo.classes.topology.HostStatus;
import com.ebupt.vnbo.classes.vtopo.VGroup;
import com.ebupt.vnbo.classes.vtopo.VLink;
import com.ebupt.vnbo.classes.vtopo.VTopo;
import com.ebupt.vnbo.request.Request;
import com.ebupt.vnbo.request.vtopo.VtopoRequest;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.service.vtopo.VtopoService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;

public class VtopoServiceImpl implements VtopoService {

	public JSONObject resolve(Request request) {
		// TODO Auto-generated method stub
		VtopoRequest vtopoRequest=(VtopoRequest) request;
		JSONObject result=new JSONObject();
		if(vtopoRequest.getOperationType()==OperationType.ADD){
			return add(vtopoRequest.getvTopo());
		}
		if(vtopoRequest.getOperationType()==OperationType.REMOVE){
			return remove(vtopoRequest.getvTopo());
		}
		if(vtopoRequest.getOperationType()==OperationType.QUERRYALL){
			return querryAll(new VTopo());
		}
		if(vtopoRequest.getOperationType()==OperationType.QUERRY){
			return querry(vtopoRequest.getvTopos());
		}
		result.put("status", -1);
		result.put("description", "error OperationType");		
		return result;
		
	}
	
	
	/**
	 * 添加vtopo
	 * @param vTopo
	 * @return
	 */
		private JSONObject add(VTopo vTopo){
			TopologyService topologyService=new TopologyServiceImpl();
			JSONObject result=new JSONObject();
			
			try {
				vTopo.send(null);
				result.put("Status", 0);
				result.put("description", "success to creat vtopo "+vTopo.getVtopo_name());		
			} catch (ConfigException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("Status", -1);
				result.put("description", "faied to creat vtopo "+vTopo.getVtopo_name());
			}
			finally {
				return result;
			}
		}
		/**
		 * 删除vtopo
		 * @param vTopo
		 * @return
		 */
		private JSONObject remove(VTopo vTopo){
			JSONObject result=new JSONObject();
			TopologyService topologyService=new TopologyServiceImpl();
			try {			
				vTopo.remove(null);
				result.put("Status", 0);
				result.put("description", "success to remove vtopo "+vTopo.getVtopo_name());
			} catch (ConfigException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("Status", -1);
				result.put("description", "faied to delete vtopo "+vTopo.getVtopo_name());
			}
			finally {
				return result;
			}
		}
		/**
		 * 查询指定的vtopo
		 * @param vTopo
		 * @return
		 */
		private JSONObject querry(List<VTopo> vTopos){
			JSONObject result=new JSONObject();
			ArrayList<VTopo> vList=new ArrayList<>();
			for(VTopo v:vTopos){
				try {
					v=v.read(null);
					vList.add(v);			
				} catch (ODL_IO_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.put("Status", -1);
					result.put("description", "read failed");
					return result;
				}				
			}
			result.put("Status", 0);
			result.put("description", "read success");
			result.put("VTopos", JSON.toJSON(vList));
			return result;
		
		}
		@SuppressWarnings("finally")
		private JSONObject querryAll(VTopo vTopo){
			JSONObject result=new JSONObject();
		try {
			HashSet<VTopo> vTopos=vTopo.readAll();
			result.put("Status", 0);
			result.put("description", "read success");
			result.put("VTopos", JSON.toJSON(vTopos));
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "read failed");
		}
		finally {
			return result;
		}
		}
		public static void main(String []args){
			VtopoServiceImpl vtopoServiceImpl=new VtopoServiceImpl();
			VtopoRequest vtopoRequest=new VtopoRequest();
			vtopoRequest.setOperationType(OperationType.ADD);
			TopologyService topoService=new TopologyServiceImpl();
			ArrayList<Host> hosts;
			try {
				hosts = new ArrayList<Host>(topoService.get_hosts());
				VTopo vTopo=new VTopo().setVtopo_name("v1");
				VGroup vGroup=new VGroup().addHost(hosts.get(0).getHost_name()).addHost(hosts.get(1).getHost_name()).setGroup_id("g1").setVtoponame(vTopo.getVtopo_name());
				VGroup vGroup2=new VGroup().addHost(hosts.get(2).getHost_name()).addHost(hosts.get(3).getHost_name()).setGroup_id("g2").setVtoponame(vTopo.getVtopo_name());
				VGroup vGroup3=new VGroup().addHost(hosts.get(4).getHost_name()).addHost(hosts.get(5).getHost_name()).setGroup_id("g3").setVtoponame(vTopo.getVtopo_name());
				VLink vLink=new VLink();
				vLink.setGroupA(vGroup.getGroup_id()).setGroupB(vGroup2.getGroup_id()).setLink_id(vLink.getGroupA()+"_"+vLink.getGroupB()).setVtoponame(vTopo.getVtopo_name());
				VLink vLink2=new VLink();
				vLink2.setGroupA(vGroup.getGroup_id()).setGroupB(vGroup3.getGroup_id()).setLink_id(vLink2.getGroupA()+"_"+vLink.getGroupB()).setVtoponame(vTopo.getVtopo_name());
				vTopo.addGroup(vGroup).addGroup(vGroup2).addGroup(vGroup3).addLink(vLink).addLink(vLink2);
				JSONObject jsonObject=(JSONObject)JSONObject.toJSON(vTopo);
				System.out.println(jsonObject);
				vtopoRequest.setvTopo(vTopo);
			 //	vtopoServiceImpl.resolve(vtopoRequest);
				System.out.println(JSON.toJSONString(vtopoRequest));
			} catch (OperationalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}

}
