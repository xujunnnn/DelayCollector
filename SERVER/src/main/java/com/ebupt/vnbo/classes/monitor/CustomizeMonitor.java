package com.ebupt.vnbo.classes.monitor;

import java.util.HashSet;

import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.flow.instruction.Instruction;
import com.ebupt.vnbo.classes.flow.instruction.Instructions;
import com.ebupt.vnbo.classes.flow.match.Ethernet_Match;
import com.ebupt.vnbo.classes.flow.match.Ethernet_destination;
import com.ebupt.vnbo.classes.flow.match.Ethernet_source;
import com.ebupt.vnbo.classes.flow.match.Ethernet_type;
import com.ebupt.vnbo.classes.flow.match.Match;
import com.ebupt.vnbo.classes.topology.Host;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
/**
 * 
* 类名: CustomizeMonitor.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(用一句话描述该文件做什么) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月8日 <br/>
* 发布版本： V1.0  <br/>
 */
public class CustomizeMonitor implements Config {
	private String id;
	private Protocol_Type protocol_Type;
	private String srchost;
	private String desthost;
	private String inPort;
	private String udp_srcPort;
	private String udp_destPort;
	private String tcp_srcPort;
	private String tcp_destPort;
	

	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public Protocol_Type getProtocol_Type() {
		return protocol_Type;
	}




	public void setProtocol_Type(Protocol_Type protocol_Type) {
		this.protocol_Type = protocol_Type;
	}




	public String getSrchost() {
		return srchost;
	}




	public void setSrchost(String srchost) {
		this.srchost = srchost;
	}




	public String getDesthost() {
		return desthost;
	}




	public void setDesthost(String desthost) {
		this.desthost = desthost;
	}


	public String getInPort() {
		return inPort;
	}




	public void setInPort(String inPort) {
		this.inPort = inPort;
	}




	public String getUdp_srcPort() {
		return udp_srcPort;
	}




	public void setUdp_srcPort(String udp_srcPort) {
		this.udp_srcPort = udp_srcPort;
	}




	public String getUdp_destPort() {
		return udp_destPort;
	}




	public void setUdp_destPort(String udp_destPort) {
		this.udp_destPort = udp_destPort;
	}




	public String getTcp_srcPort() {
		return tcp_srcPort;
	}




	public void setTcp_srcPort(String tcp_srcPort) {
		this.tcp_srcPort = tcp_srcPort;
	}




	public String getTcp_destPort() {
		return tcp_destPort;
	}




	public void setTcp_destPort(String tcp_destPort) {
		this.tcp_destPort = tcp_destPort;
	}




	/**
	 * 
	* 方法名：getNodes</br>
	* 详述：TODO（获取需要发送流表的节点）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonRequest
	* @return
	* @throws OperationalException
	* @throws
	 */
		@JSONField(deserialize=true)
		private HashSet<String> getNodes() throws OperationalException{
			
			TopologyService topoService=new TopologyServiceImpl();
			HashSet<String> nodes=new HashSet<String>();
			if(this.srchost!=null){
				Host src=topoService.get_host_from_name(srchost);
				nodes.add(src.getAccess_node());
			}
			else if(this.desthost!=null){
					Host dest=topoService.get_host_from_name(desthost);
					nodes.add(dest.getAccess_node());
				}
			if(nodes.isEmpty()){
				for(Node n:topoService.get_access_node()){
					nodes.add(n.getNode_id());
				}	
			}
			
			return nodes;	
		}
		



	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		TopologyService topologyService=new TopologyServiceImpl();
		FlowEntry flowEntry=new FlowEntry();
		Match match=new Match();
		Ethernet_Match ethernet_Match=new Ethernet_Match();
		if(this.srchost!=null){
			Ethernet_source ethernet_source=new Ethernet_source();
			ethernet_source.setAddress(topologyService.get_host_from_name(srchost).getMac());
			ethernet_Match.setEthernet_source(ethernet_source);
			match.setIn_port(topologyService.get_host_from_name(this.srchost).getAccess_port());	
		}
		if(this.desthost!=null){
			Ethernet_destination ethernet_destination=new Ethernet_destination();
			ethernet_destination.setAddress(topologyService.get_host_from_name(desthost).getMac());
			ethernet_Match.setEthernet_destination(ethernet_destination);
		}
		Ethernet_type ethernet_type=new Ethernet_type();
		ethernet_type.setType("2048");
		ethernet_Match.setEthernet_type(ethernet_type);
		match.setEthernet_Match(ethernet_Match);
		if(this.protocol_Type!=null){
			String protocol=Integer.toString(this.protocol_Type.value());
			match.Set_Ip_Match(protocol, null, null, null);
		}
		if(this.tcp_srcPort!=null){
			match.setTcp_source_port(this.tcp_srcPort);
		}
		if(this.tcp_destPort!=null){
			match.setTcp_source_port(this.tcp_destPort);
		}
		if(this.udp_srcPort!=null){
			match.setUdp_source_port(this.udp_srcPort);
		}
		if(this.udp_destPort!=null){
			match.setUdp_destination_port(this.udp_destPort);
		}
		Instructions instructions=new Instructions();
		Instruction instruction=new Instruction();
		instruction.Set_Go_To_Table_Id(MONFLOWTABLE).setOrder("0");
		instructions.addInstruction(instruction);
		HashSet<String> nodes;
			nodes = getNodes();
			for(String n:nodes){
				flowEntry.setId(id)
						 .setFlow_name("CustomizeMonFlow "+flowEntry.getId())
						 .setMatch(match)
						 .setInstructions(instructions)
						 .setHard_timeout(HARD_TIME_OUT)
						 .setIdle_timeout(IDLE_TIME_OUT)
						 .setPriority(MIDPRIORITY)
						 .setCookie(flowEntry.getId())
						 .setTable_id(CUSTOMIZETABLE)
						 .send(n);	
			}
		
	}
	
	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		HashSet<String> nodes=getNodes();
		for(String n:nodes){
			FlowEntry flowEntry=new FlowEntry().setId(id);
			flowEntry.remove(n);
		}
		
		
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desthost == null) ? 0 : desthost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inPort == null) ? 0 : inPort.hashCode());
		result = prime * result + ((protocol_Type == null) ? 0 : protocol_Type.hashCode());
		result = prime * result + ((srchost == null) ? 0 : srchost.hashCode());
		result = prime * result + ((tcp_destPort == null) ? 0 : tcp_destPort.hashCode());
		result = prime * result + ((tcp_srcPort == null) ? 0 : tcp_srcPort.hashCode());
		result = prime * result + ((udp_destPort == null) ? 0 : udp_destPort.hashCode());
		result = prime * result + ((udp_srcPort == null) ? 0 : udp_srcPort.hashCode());
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
		CustomizeMonitor other = (CustomizeMonitor) obj;
		if (desthost == null) {
			if (other.desthost != null)
				return false;
		} else if (!desthost.equals(other.desthost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inPort == null) {
			if (other.inPort != null)
				return false;
		} else if (!inPort.equals(other.inPort))
			return false;
		if (protocol_Type != other.protocol_Type)
			return false;
		if (srchost == null) {
			if (other.srchost != null)
				return false;
		} else if (!srchost.equals(other.srchost))
			return false;
		if (tcp_destPort == null) {
			if (other.tcp_destPort != null)
				return false;
		} else if (!tcp_destPort.equals(other.tcp_destPort))
			return false;
		if (tcp_srcPort == null) {
			if (other.tcp_srcPort != null)
				return false;
		} else if (!tcp_srcPort.equals(other.tcp_srcPort))
			return false;
		if (udp_destPort == null) {
			if (other.udp_destPort != null)
				return false;
		} else if (!udp_destPort.equals(other.udp_destPort))
			return false;
		if (udp_srcPort == null) {
			if (other.udp_srcPort != null)
				return false;
		} else if (!udp_srcPort.equals(other.udp_srcPort))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "CustomizeMonitor [id=" + id + ", protocol_Type=" + protocol_Type + ", srchost=" + srchost
				+ ", desthost=" + desthost + ", inPort=" + inPort + ", udp_srcPort=" + udp_srcPort + ", udp_destPort="
				+ udp_destPort + ", tcp_srcPort=" + tcp_srcPort + ", tcp_destPort=" + tcp_destPort + "]";
	}





	
	


	

	
}
