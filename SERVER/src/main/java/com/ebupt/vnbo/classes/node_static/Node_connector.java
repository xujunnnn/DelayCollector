package com.ebupt.vnbo.classes.node_static;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;
public class Node_connector {
	private String id;
	@JSONField(name="flow-node-inventory-configuration")
	private String flow_node_inventory_configuration;
	@JSONField(name="flow-node-inventory-hardware-address")
    private String flow_node_inventory_hardware_address;
	@JSONField(name="flow-node-inventory-current-speed")
    private String flow_node_inventory_current_speed;
	@JSONField(name="flow-node-inventory-maximum-speed")
    private String flow_node_inventory_maximum_speed;
	@JSONField(name="opendaylight-port-statistics:flow-capable-node-connector-statistics")
	private Node_Connector_Static node_Connector_Static;
	@JSONField(name="flow-node-inventory:queue")
	private ArrayList<Queue> queues;
	
	public ArrayList<Queue> getQueues() {
		return queues;
	}
	public void setQueues(ArrayList<Queue> queues) {
		this.queues = queues;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlow_node_inventory_configuration() {
		return flow_node_inventory_configuration;
	}
	public void setFlow_node_inventory_configuration(String flow_node_inventory_configuration) {
		this.flow_node_inventory_configuration = flow_node_inventory_configuration;
	}
	public String getFlow_node_inventory_hardware_address() {
		return flow_node_inventory_hardware_address;
	}
	public void setFlow_node_inventory_hardware_address(String flow_node_inventory_hardware_address) {
		this.flow_node_inventory_hardware_address = flow_node_inventory_hardware_address;
	}
	public String getFlow_node_inventory_current_speed() {
		return flow_node_inventory_current_speed;
	}
	public void setFlow_node_inventory_current_speed(String flow_node_inventory_current_speed) {
		this.flow_node_inventory_current_speed = flow_node_inventory_current_speed;
	}
	public String getFlow_node_inventory_maximum_speed() {
		return flow_node_inventory_maximum_speed;
	}
	public void setFlow_node_inventory_maximum_speed(String flow_node_inventory_maximum_speed) {
		this.flow_node_inventory_maximum_speed = flow_node_inventory_maximum_speed;
	}
	public Node_Connector_Static getNode_Connector_Static() {
		return node_Connector_Static;
	}
	public void setNode_Connector_Static(Node_Connector_Static node_Connector_Static) {
		this.node_Connector_Static = node_Connector_Static;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flow_node_inventory_configuration == null) ? 0 : flow_node_inventory_configuration.hashCode());
		result = prime * result
				+ ((flow_node_inventory_current_speed == null) ? 0 : flow_node_inventory_current_speed.hashCode());
		result = prime * result + ((flow_node_inventory_hardware_address == null) ? 0
				: flow_node_inventory_hardware_address.hashCode());
		result = prime * result
				+ ((flow_node_inventory_maximum_speed == null) ? 0 : flow_node_inventory_maximum_speed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((node_Connector_Static == null) ? 0 : node_Connector_Static.hashCode());
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
		Node_connector other = (Node_connector) obj;
		if (flow_node_inventory_configuration == null) {
			if (other.flow_node_inventory_configuration != null)
				return false;
		} else if (!flow_node_inventory_configuration.equals(other.flow_node_inventory_configuration))
			return false;
		if (flow_node_inventory_current_speed == null) {
			if (other.flow_node_inventory_current_speed != null)
				return false;
		} else if (!flow_node_inventory_current_speed.equals(other.flow_node_inventory_current_speed))
			return false;
		if (flow_node_inventory_hardware_address == null) {
			if (other.flow_node_inventory_hardware_address != null)
				return false;
		} else if (!flow_node_inventory_hardware_address.equals(other.flow_node_inventory_hardware_address))
			return false;
		if (flow_node_inventory_maximum_speed == null) {
			if (other.flow_node_inventory_maximum_speed != null)
				return false;
		} else if (!flow_node_inventory_maximum_speed.equals(other.flow_node_inventory_maximum_speed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (node_Connector_Static == null) {
			if (other.node_Connector_Static != null)
				return false;
		} else if (!node_Connector_Static.equals(other.node_Connector_Static))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Node_connector [id=" + id + ", flow_node_inventory_configuration=" + flow_node_inventory_configuration
				+ ", flow_node_inventory_hardware_address=" + flow_node_inventory_hardware_address
				+ ", flow_node_inventory_current_speed=" + flow_node_inventory_current_speed
				+ ", flow_node_inventory_maximum_speed=" + flow_node_inventory_maximum_speed
				+ ", node_Connector_Static=" + node_Connector_Static + "]";
	}
	
  
	
	



  
	

}
