package com.ebupt.vnbo.classes.topology;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

public class Node {
	
	@JSONField(name="node-id")
	private String node_id;
	@JSONField(name="host-tracker-service:attachment-points")
    private ArrayList<Host_tracker_service_attachment_points> host_tracker_service_attachment_points=new ArrayList<Host_tracker_service_attachment_points>();
	@JSONField(name="termination-point")
	private ArrayList<Termination_point> termination_points=new ArrayList<Termination_point>();
	@JSONField(name="host-tracker-service:addresses")
	private ArrayList<Host_tracker_service_address> host_tracker_service_addresses=new ArrayList<Host_tracker_service_address>();
	public String getNode_id() {
		return node_id;
	}
	public ArrayList<Termination_point> getTermination_points() {
		return termination_points;
	}
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	public void setTermination_points(ArrayList<Termination_point> termination_points) {
		this.termination_points = termination_points;
	}
	public ArrayList<Host_tracker_service_attachment_points> getHost_tracker_service_attachment_points() {
		return host_tracker_service_attachment_points;
	}
	public void setHost_tracker_service_attachment_points(ArrayList<Host_tracker_service_attachment_points> host_tracker_service_attachment_points) {
		this.host_tracker_service_attachment_points = host_tracker_service_attachment_points;
	}
	public ArrayList<Host_tracker_service_address> getHost_tracker_service_addresses() {
		return host_tracker_service_addresses;
	}
	public void setHost_tracker_service_addresses(ArrayList<Host_tracker_service_address> host_tracker_service_addresses) {
		this.host_tracker_service_addresses = host_tracker_service_addresses;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node_id == null) ? 0 : node_id.hashCode());
		result = prime * result + ((termination_points == null) ? 0 : termination_points.hashCode());
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
		Node other = (Node) obj;
		if (node_id == null) {
			if (other.node_id != null)
				return false;
		} else if (!node_id.equals(other.node_id))
			return false;
		if (termination_points == null) {
			if (other.termination_points != null)
				return false;
		} else if (!termination_points.equals(other.termination_points))
			return false;
		return true;
	}
}
