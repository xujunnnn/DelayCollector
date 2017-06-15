package com.ebupt.vnbo.service.topology;

import java.util.HashSet;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.topology.Host;
import com.ebupt.vnbo.classes.topology.Link;
import com.ebupt.vnbo.classes.topology.Node;
import com.ebupt.vnbo.classes.topology.Termination_point;
import com.ebupt.vnbo.service.WebService;

public interface TopologyService extends WebService{

	
	
	/**
	 * 获取接入交换机节点
	 * @return
	 * @throws TopoReadFailException
	 */
	public  HashSet<Node> get_access_node() throws OperationalException;
	/**
	 * 获取内部链路
	 * @return
	 * @throws TopoReadFailException
	 */
	public  HashSet<Link> get_inner_link() throws OperationalException;
	/**
	 * 获取所有交换机
	 * @return
	 * @throws TopoReadFailException
	 */
	public  HashSet<Node> get_switch() throws OperationalException;
	/**
	 * 获取所有连接主机的端口
	 * @return
	 * @throws TopoReadFailException
	 */
	public  HashSet<Termination_point> get_ports_to_host() throws OperationalException;
	/**
	 * 获取指定交换机上链接主机的端口
	 * @param thenode
	 * @return
	 * @throws TopoReadFailException
	 */
	public  HashSet<Termination_point> get_ports_to_host(Node thenode) throws OperationalException;
	/**
	 * 获取所有主机
	 * @return
	 * @throws TopoReadFailException
	 */
	 public HashSet<Host> get_hosts() throws OperationalException;
	 /**
	  * 获取指定节点上的主机
	  * @param thenode
	  * @return
	  * @throws TopoReadFailException
	  */
	 public HashSet<Host> get_hosts(Node thenode) throws OperationalException;
	 /**
	  * 通过主机名获取主机
	  * @param hostA
	  * @return
	  * @throws TopoReadFailException
	  */
	 public Host get_host_from_name(String hostA) throws OperationalException;
	 /**
	  * 通过ip地址获取主机
	  * @param ip
	  * @return
	  * @throws TopoReadFailException
	  */
	 public Host get_host_from_ip(String ip) throws OperationalException;
	 /**
	  * 获取所有结点
	  * @return
	  * @throws TopoReadFailException
	  */
	 public HashSet<Node> getNodes() throws OperationalException;
	 /**
	  * 获取所有链路
	  * @return
	  * @throws TopoReadFailException
	  */
	 public HashSet<Link> getLinks() throws OperationalException;
}
