package com.ebupt.vnbo.classes.node_static;

import com.alibaba.fastjson.annotation.JSONField;

public class Queue {
	@JSONField(name="queue-id")
	private String queue_id;
	@JSONField(name="opendaylight-queue-statistics:flow-capable-node-connector-queue-statistics")
	private Queue_Static queue_Static;
	public String getQueue_id() {
		return queue_id;
	}
	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}
	public Queue_Static getQueue_Static() {
		return queue_Static;
	}
	public void setQueue_Static(Queue_Static queue_Static) {
		this.queue_Static = queue_Static;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((queue_Static == null) ? 0 : queue_Static.hashCode());
		result = prime * result + ((queue_id == null) ? 0 : queue_id.hashCode());
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
		Queue other = (Queue) obj;
		if (queue_Static == null) {
			if (other.queue_Static != null)
				return false;
		} else if (!queue_Static.equals(other.queue_Static))
			return false;
		if (queue_id == null) {
			if (other.queue_id != null)
				return false;
		} else if (!queue_id.equals(other.queue_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Queue [queue_id=" + queue_id + ", queue_Static=" + queue_Static + "]";
	}
	
}
