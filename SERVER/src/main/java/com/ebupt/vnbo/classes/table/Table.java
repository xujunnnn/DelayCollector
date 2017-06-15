package com.ebupt.vnbo.classes.table;

import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.HttpUtil;
/**
 * 
* 类名: Table.java <br/>
* 包名 : com.ebupt.vnbo.classes.table <br/>
* 详细描述: TODO(Flow table 实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Table implements Config {

	private String tableid;
	private String node;
	public String getNode() {
		return node;
	}

	public Table setNode(String node) {
		this.node = node;
		return this;
	}

	public String getTableid() {
		return tableid;
	}

	public Table setTableid(String tableid) {
		this.tableid = tableid;
		return this;
	}
	
	
	

	@Override
	public String toString() {
		return "Table [tableid=" + tableid + ", node=" + node + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		result = prime * result + ((tableid == null) ? 0 : tableid.hashCode());
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
		Table other = (Table) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		if (tableid == null) {
			if (other.tableid != null)
				return false;
		} else if (!tableid.equals(other.tableid))
			return false;
		return true;
	}

	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		String url="http://"+ConfigUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+tableid;
		String s[]=HttpUtil.Delete_request(url);
	   String responsecode=s[0];
	   if(!"200".equals(responsecode) && !"201".equals(responsecode) && !"404".equals(responsecode))
		   throw new ConfigException("Table "+tableid+" delete fail");
	}

}
