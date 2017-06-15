package com.ebupt.vnbo.classes.qos;

import java.util.HashSet;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.classes.flow.action.Action;
import com.ebupt.vnbo.classes.flow.instruction.Apply_Actions;
import com.ebupt.vnbo.classes.flow.instruction.Instruction;
import com.ebupt.vnbo.classes.flow.instruction.Instructions;
import com.ebupt.vnbo.classes.flow.match.Match;
import com.ebupt.vnbo.classes.meter.MeterEntry;
import com.ebupt.vnbo.classes.topology.Host;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.service.topology.TopologyService;
import com.ebupt.vnbo.serviceImpl.topology.TopologyServiceImpl;
import com.sun.glass.ui.TouchInputSupport;
/**
 * 
* 类名: QosEntry.java <br/>
* 包名 : com.ebupt.vnbo.classes.qos <br/>
* 详细描述: TODO(qos配置实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class QosEntry implements Config,Operational {
	private String qos_id;
	private String drop_rate;
	private String queue_id;
	private String Srchost;
	private String Desthost;
	private Protocol_Type ip_Protocol;
	private String Tcp_srcPort;
	private String Tcp_destPort;
	private String Udp_srcPort;
	private String Udp_destPort;
	private int hash;
	public String getQos_id() {
		return qos_id;
	}
	public QosEntry setQos_id(String qos_id) {
		this.qos_id = qos_id;
		return this;
	}
	public String getDrop_rate() {
		return drop_rate;
	}
	public QosEntry setDrop_rate(String drop_rate) {
		this.drop_rate = drop_rate;
		return this;
	}
	public String getQueue_id() {
		return queue_id;
	}
	public QosEntry setQueue_id(String queue_id) {
		this.queue_id = queue_id;
		return this;
	}
	public String getSrchost() {
		return Srchost;
	}
	public QosEntry setSrchost(String srchost) {
		Srchost = srchost;
		return this;
	}
	public String getDesthost() {
		return Desthost;
	}
	public QosEntry setDesthost(String desthost) {
		Desthost = desthost;
		return this;
	}
	public Protocol_Type getIp_Protocol() {
		return ip_Protocol;
	}
	public QosEntry setIp_Protocol(Protocol_Type ip_Protocol) {
		this.ip_Protocol = ip_Protocol;
		return this;
	}
	public String getTcp_srcPort() {
		return Tcp_srcPort;
	}
	public QosEntry setTcp_srcPort(String tcp_srcPort) {
		Tcp_srcPort = tcp_srcPort;
		return this;
	}
	public String getTcp_destPort() {
		return Tcp_destPort;
	}
	public QosEntry setTcp_destPort(String tcp_destPort) {
		Tcp_destPort = tcp_destPort;
		return this;
	}
	public String getUdp_srcPort() {
		return Udp_srcPort;
	}
	public QosEntry setUdp_srcPort(String udp_srcPort) {
		Udp_srcPort = udp_srcPort;
		return this;
	}
	public String getUdp_destPort() {
		return Udp_destPort;
	}
	public QosEntry setUdp_destPort(String udp_destPort) {
		Udp_destPort = udp_destPort;
		return this;
	}
	public int getHash() {
		return hash;
	}
	public QosEntry setHash(int hash) {
		this.hash = hash;
		return this;
	}

	


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Desthost == null) ? 0 : Desthost.hashCode());
		result = prime * result + ((Srchost == null) ? 0 : Srchost.hashCode());
		result = prime * result + ((Tcp_destPort == null) ? 0 : Tcp_destPort.hashCode());
		result = prime * result + ((Tcp_srcPort == null) ? 0 : Tcp_srcPort.hashCode());
		result = prime * result + ((Udp_destPort == null) ? 0 : Udp_destPort.hashCode());
		result = prime * result + ((Udp_srcPort == null) ? 0 : Udp_srcPort.hashCode());
		result = prime * result + ((ip_Protocol == null) ? 0 : ip_Protocol.hashCode());
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
		QosEntry other = (QosEntry) obj;
		if (Desthost == null) {
			if (other.Desthost != null)
				return false;
		} else if (!Desthost.equals(other.Desthost))
			return false;
		if (Srchost == null) {
			if (other.Srchost != null)
				return false;
		} else if (!Srchost.equals(other.Srchost))
			return false;
		if (Tcp_destPort == null) {
			if (other.Tcp_destPort != null)
				return false;
		} else if (!Tcp_destPort.equals(other.Tcp_destPort))
			return false;
		if (Tcp_srcPort == null) {
			if (other.Tcp_srcPort != null)
				return false;
		} else if (!Tcp_srcPort.equals(other.Tcp_srcPort))
			return false;
		if (Udp_destPort == null) {
			if (other.Udp_destPort != null)
				return false;
		} else if (!Udp_destPort.equals(other.Udp_destPort))
			return false;
		if (Udp_srcPort == null) {
			if (other.Udp_srcPort != null)
				return false;
		} else if (!Udp_srcPort.equals(other.Udp_srcPort))
			return false;
		if (ip_Protocol != other.ip_Protocol)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QosEntry [qos_id=" + qos_id + ", drop_rate=" + drop_rate + ", queue_id=" + queue_id + ", Srchost="
				+ Srchost + ", Desthost=" + Desthost + ", ip_Protocol=" + ip_Protocol + ", Tcp_srcPort=" + Tcp_srcPort
				+ ", Tcp_destPort=" + Tcp_destPort + ", Udp_srcPort=" + Udp_srcPort + ", Udp_destPort=" + Udp_destPort
				+ ", hash=" + hash + "]";
	}
	/**
	 * 
	* 方法名：getNodes</br>
	* 详述：TODO（获取需要发送该配置的交换机节点）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月12日  </br>
	* @return
	* @throws OperationalException
	* @throws
	 */
	@JSONField(deserialize=true)
	private HashSet<String> getNodes() throws OperationalException{
		
		TopologyService topoService=new TopologyServiceImpl();
		HashSet<String> nodes=new HashSet<String>();
		if(this.Srchost!=null && this.Desthost!=null){
			Host Srchost=topoService.get_host_from_ip(this.Srchost.split("/")[0]);
			Host Desthost=topoService.get_host_from_ip(this.Desthost.split("/")[0]);
		    nodes.add(Srchost.getAccess_node());
		    nodes.add(Desthost.getAccess_node());
		}
		else if(this.Srchost==null && this.Desthost!=null){
			Host Desthost=topoService.get_host_from_ip(this.Desthost.split("/")[0]);
			nodes.add(Desthost.getAccess_node());
		}
		else if(this.Srchost!=null && this.Desthost==null){
			Host Srchost=topoService.get_host_from_ip(this.Srchost.split("/")[0]);
			nodes.add(Srchost.getAccess_node());
		}
		else{
			for(Node n:topoService.get_access_node()){
				nodes.add(n.getNode_id());
			}
		}
		return nodes;
		
	}
	
	
	public Operational read(String node) throws OperationalException {
		// TODO Auto-generated method stub
		return null;
	}

	public void send(String Node) throws ConfigException, OperationalException{
		// TODO Auto-generated method stub
		Match match=new Match();
		if(this.Srchost!=null){
			match.setIpv4_source(this.Srchost);
		}
		if(this.Desthost!=null){
			match.setIpv4_destination(this.Desthost);
		}
			match.Set_Mac_Match( null, null, "2048");
		if(this.ip_Protocol!=null){
			match.Set_Ip_Match(String.valueOf(ip_Protocol.value()), null, null, null);
		}
		if(this.Udp_srcPort!=null){
			match.setUdp_source_port(this.Udp_srcPort);
		}
		if(this.Udp_destPort!=null){
			match.setUdp_destination_port(this.Udp_destPort);
		}
		if(this.Tcp_srcPort!=null){
			match.setTcp_source_port(this.Tcp_srcPort);
		}
		if(this.Tcp_destPort!=null){
			match.setTcp_destination_port(this.Tcp_destPort);
		}
		for(String node:this.getNodes()){
			FlowEntry flow=new FlowEntry();
			Instructions instructions=new Instructions();
			int Inorder=0;
			//Qos涓寘鍚檺閫熶俊鎭�
			if(drop_rate!=null){
				MeterEntry meter=new MeterEntry();
				meter.setMeter_id(String.valueOf(Math.abs(qos_id.hashCode())))
				.setMeter_name(this.getQos_id())
				.Set_drop_rate(drop_rate)
				.setContainer_name(meter.getMeter_name())
				.send(node);
				Instruction instruction2=new Instruction();
				instruction2.Set_Meter(meter.getMeter_id()).setOrder(String.valueOf(Inorder++));
				instructions.addInstruction(instruction2);
			}
			if(queue_id!=null){
				Instruction queueinstruction=new Instruction();
				Apply_Actions actions=new Apply_Actions();
				Action action=new Action().Set_Queue_Id(queue_id);
				action.setOrder("0");
				actions.addAction(action);
				queueinstruction.setApply_Actions(actions).setOrder(String.valueOf(Inorder++));
				instructions.addInstruction(queueinstruction);
			}
			//go_to_table 3 instruction
			Instruction instruction=new Instruction();	
			instruction.Set_Go_To_Table_Id(MONFLOWTABLE).setOrder(String.valueOf(Inorder++));
			instructions.addInstruction(instruction);		
				flow.setId(this.qos_id)
					.setFlow_name("qosflow"+this.qos_id)
					.setCookie(String.valueOf(Math.abs(this.getQos_id().hashCode())))
					.setTable_id(QOSFLOWTABLE)
					.setPriority(MIDPRIORITY)
					.setHard_timeout(HARD_TIME_OUT)
					.setIdle_timeout(IDLE_TIME_OUT)
					.setMatch(match)
					.setInstructions(instructions)
					.send(node);
			}	
		
	}

	public void remove(String Node) throws ConfigException, OperationalException {
		// TODO Auto-generated method stub
		for(String node:this.getNodes()){
			MeterEntry meter=new MeterEntry();
			meter.setMeter_id(String.valueOf(Math.abs(qos_id.hashCode()))).remove(node);
			FlowEntry flow=new FlowEntry();
				flow.setId(this.qos_id).setTable_id(QOSFLOWTABLE).remove(node);;		
			}	
		
	}


}
