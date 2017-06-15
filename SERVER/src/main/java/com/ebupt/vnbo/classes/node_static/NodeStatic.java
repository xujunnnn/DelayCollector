package com.ebupt.vnbo.classes.node_static;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.util.HttpUtil;
/**
 * 
* 类名: NodeStatic.java <br/>
* 包名 : com.ebupt.vnbo.classes.node_static <br/>
* 详细描述: TODO(交换机节点信息) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月11日 <br/>
* 发布版本： V1.0  <br/>
 */
public class NodeStatic implements Operational{
	private String id;
	@JSONField(name="node-connector")
	private ArrayList<Node_connector> node_connector;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Node_connector> getNode_connector() {
		return node_connector;
	}
	public void setNode_connector(ArrayList<Node_connector> node_connector) {
		this.node_connector = node_connector;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((node_connector == null) ? 0 : node_connector.hashCode());
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
		NodeStatic other = (NodeStatic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (node_connector == null) {
			if (other.node_connector != null)
				return false;
		} else if (!node_connector.equals(other.node_connector))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NodeStatic [id=" + id + ", node_connector=" + node_connector + "]";
	}
	@Override
	public Operational read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/opendaylight-inventory:nodes/node/"+node;
		String response[]=HttpUtil.Get_request(url);
		String responsebody=response[1];
		String responsecode=response[0];
		if(!"201".equals(responsecode) && !"200".equals(responsecode) )
			throw new OperationalException("NodeInfo read failed "+this.getId());
		JSONObject json=JSONObject.parseObject(responsebody);
		JSONObject nodejson=json.getJSONArray("node").getJSONObject(0);
		NodeStatic nodeStatic=JSON.toJavaObject(nodejson, NodeStatic.class);
		return nodeStatic;
	}
	

}
