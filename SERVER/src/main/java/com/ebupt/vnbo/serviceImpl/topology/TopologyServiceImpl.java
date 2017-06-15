package com.ebupt.vnbo.serviceImpl.topology;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.topology.Host;
import com.ebupt.vnbo.classes.topology.HostTracker;
import com.ebupt.vnbo.classes.topology.Link;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.classes.topology.Termination_point;
import com.ebupt.vnbo.classes.topology.Topology;
import com.ebupt.vnbo.classes.vtopo.VGroup;
import com.ebupt.vnbo.classes.vtopo.VTopo;
import com.ebupt.vnbo.request.Request;
import com.ebupt.vnbo.request.topology.TopologyRequest;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.util.TopoUtil;

public class TopologyServiceImpl implements TopologyService {
	private static Topology topology=new Topology();
	//鐢ㄤ簬缁存姢鍙互杩涜鍒涘缓铏氭嫙缃戠粶鐨勪富鏈�
	public JSONObject resolve(Request request) {
		// TODO Auto-generated method stub
		TopologyRequest topologyRequest=(TopologyRequest) request;
		JSONObject result=new JSONObject();
		if(topologyRequest.getOperationType()==OperationType.QUERRYALL){
			if(topologyRequest.getTag()==Tag.HOST){
				return querryHost();
			}
			if(topologyRequest.getTag()==Tag.NODE){
				return querrySwitch();
			}
			if(topologyRequest.getTag()==Tag.ABLEHOST){
				return querryAbleHost();
			}
		
			result.put("status", -1);
			result.put("description", "error tag");		
			return result;
		}

		
		else if(topologyRequest.getOperationType()==OperationType.DISCOVER){
			return discoverHost(topologyRequest);
		}
		else {
			result.put("status", -1);
			result.put("description", "error OperationType");		
			return result;
		}
		
	}
	/**
	 * @throws OperationalException 
	 * 鏌ヨ宸茬粡澶勪簬铏氭嫙缃戠粶姝ｇ殑涓绘満
	 */
	private HashSet<Host> getBusyHost() throws OperationalException{
		HashSet<Host> busyHosts=new HashSet<>();
		VTopo vTopo=new VTopo();
		HashSet<VTopo> vTopos=vTopo.readAll();
		for(VTopo vtopo:vTopos){
			for(VGroup vGroup:vtopo.getvGroups()){
				for(String hostname:vGroup.getHost_names()){
					Host host=get_host_from_name(hostname);
					busyHosts.add(host);
				}
			}
		}
		return busyHosts;
	}
	
	/**
	 * 鏌ヨ鍙敤涓绘満
	 * @return
	 */
	private JSONObject querryAbleHost(){
		JSONObject result=new JSONObject();
		
		try {
			HashSet<Host> busyHosts=getBusyHost();
			HashSet<Host> hosts=get_hosts();
			Iterator<Host> iterator=hosts.iterator();
			while(iterator.hasNext()){
				if(busyHosts.contains(iterator.next()))
					iterator.remove();
			}
			result.put("Status", 0);
			result.put("description", "querrySuccess");
			result.put("Hosts", JSON.toJSON(hosts));
			return result;
		} catch (OperationalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result.put("Status", -1);
			result.put("description", "querry failed");
			return result;
		}

	}
	
/**
 * 鍙戠幇涓绘満
 * @param topologyRequest
 * @return
 */
	@SuppressWarnings("finally")
	private JSONObject discoverHost(TopologyRequest topologyRequest){
		HostTracker hostTracker=new HostTracker();
		JSONObject result=new JSONObject();
		hostTracker.setSrcIp(topologyRequest.getIdleIp());
		HashSet<String> iplist=new HashSet<>();
		try {
			iplist.addAll(TopoUtil.getIpInRange(topologyRequest.getStartIp(), topologyRequest.getEndIp()));
			hostTracker.setIp_list(iplist);
			hostTracker.send(null);
			result.put("Status", 0);
			result.put("description", "discover Success");
		} catch (UnknownHostException | ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "discover failed");
		}
		finally {
			return result;
		}

	}
	
	/**
	 * 鏌ヨ涓绘満
	 * @return
	 */
	@SuppressWarnings("finally")
	private JSONObject querryHost(){
		JSONObject result=new JSONObject();
		try {
			HashSet<Host> hosts=get_hosts();
			result.put("Status", 0);
			result.put("description", "querrySuccess");
			result.put("Hosts", JSON.toJSON(hosts));
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "querry failed");
		}
		finally{
			return result;
		}
	}
	/**
	 * 鏌ヨ浜ゆ崲鏈�
	 * @return
	 */
	@SuppressWarnings("finally")
	private JSONObject querrySwitch(){
		JSONObject result=new JSONObject();
		try {
			HashSet<Node> switchs=get_switch();
			result.put("Status", 0);
			result.put("description", "querrySuccess");
			result.put("Nodes", JSON.toJSON(switchs));
		} catch (OperationalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "querry failed");
		}
		finally{
			return result;
		}
	}
	


	public HashSet<Node> get_access_node() throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Node> Accessnodes=new HashSet<Node>();
		HashSet<String> AccessStringnodes=new HashSet<String>();
		HashSet<Node> nodes=this.getNodes();
		for(Node node:nodes){
			if(node.getNode_id().startsWith("host")){
				String node_connector=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
				String []nodeinfo=node_connector.split(":");
				String nodeid=nodeinfo[0]+":"+nodeinfo[1];
				
				
				if(!AccessStringnodes.contains(nodeid)){
					
					AccessStringnodes.add(nodeid);
					
				}
			}
			
		}
		for(String nodeid:AccessStringnodes){
			Node node2=new Node();
			node2.setNode_id(nodeid);
			Accessnodes.add(node2);
			
		}
		return Accessnodes;
	}

	public HashSet<Link> get_inner_link() throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Link> inner_links=new HashSet<Link>();
		for(Link link:this.getLinks()){
				if((link.getSource().getSource_node().startsWith("openflow")) && (link.getDestination().getDest_node().startsWith("openflow"))){
				inner_links.add(link);
			}
		}
		return inner_links;
	}

	public HashSet<Node> get_switch() throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Node> switches=new HashSet<Node>();
		for(Node node:this.getNodes()){
			if(node.getNode_id().startsWith("openflow")){
				switches.add(node);
			}
		}
		return switches;
	}

	public HashSet<Termination_point> get_ports_to_host() throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Termination_point>  ports_to_host=new HashSet<Termination_point>();
		HashSet<Host> hosts=this.get_hosts();
		for(Host host:hosts){
			Termination_point termination_point=new Termination_point();
			termination_point.setTp_id(host.getAccess_port());
			ports_to_host.add(termination_point);
		}
		return ports_to_host;
	}

	public HashSet<Termination_point> get_ports_to_host(Node thenode) throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Termination_point>  ports_to_host=new HashSet<Termination_point>();
		HashSet<Host> hosts=this.get_hosts(thenode);
		for(Host host:hosts){
			Termination_point termination_point=new Termination_point();
			termination_point.setTp_id(host.getAccess_port());
			ports_to_host.add(termination_point);
		}
		return ports_to_host;
	}

	public HashSet<Host> get_hosts() throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Host> hosts=new HashSet<Host>();
    	for(Node node:this.getNodes()){
    		if(node.getNode_id().startsWith("host")){
    			Host host=new Host();
    			String port=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
    			String []portinfo=port.split(":");
    			String nodename=portinfo[0]+":"+portinfo[1];
    			host.setMac(node.getHost_tracker_service_addresses().get(0).getMac())
    				.setIp(node.getHost_tracker_service_addresses().get(0).getIp())
    				.setAccess_node(nodename)
    				.setAccess_port(port)
    				.setHost_name(node.getNode_id());
    			hosts.add(host);
    		}
    	}
    	return hosts;
	}

	public HashSet<Host> get_hosts(Node thenode) throws OperationalException {
		// TODO Auto-generated method stub
		HashSet<Host> hosts=new HashSet<Host>();
    	for(Node node:this.getNodes()){
    		if(node.getNode_id().startsWith("host")){
    			Host host=new Host();
    			String port=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
    			String []portinfo=port.split(":");
    			String nodename=portinfo[0]+":"+portinfo[1];
    			host.setMac(node.getHost_tracker_service_addresses().get(0).getMac())
    				.setIp(node.getHost_tracker_service_addresses().get(0).getIp())
    				.setAccess_node(nodename)
    				.setAccess_port(port)
    				.setHost_name(node.getNode_id());
    			if(thenode.getNode_id().equals(nodename))
    			hosts.add(host);
    		}
    	}
    	return hosts;
	}

	public Host get_host_from_name(String hostA) throws OperationalException {
		// TODO Auto-generated method stub
		Host host=new Host();
    	for(Host h:this.get_hosts()){
    		if(h.getHost_name().equals(hostA)){
    		host=h;
    			break;	    		
    		}
    	}
    	return host;
	}

	public Host get_host_from_ip(String ip) throws OperationalException {
		// TODO Auto-generated method stub
		Host host=new Host();
    	for(Host h:this.get_hosts()){
    		if(h.getIp().equals(ip)){
    		host=h;
    			break;	    		
    		}
    	}
    	return host;
	}

	public HashSet<Node> getNodes() throws OperationalException {
		// TODO Auto-generated method stub
		topology=topology.read(null);
		return topology.getNodes();
	}

	public HashSet<Link> getLinks() throws OperationalException {
		// TODO Auto-generated method stub
		topology=topology.read(null);
		return topology.getLinks();
	}

}
