package com.ebupt.vnbo.classes.table;

import java.util.HashSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.flow.FlowEntry;
import com.ebupt.vnbo.util.HttpUtil;
/**
 * 
* 类名: TableRead.java <br/>
* 包名 : com.ebupt.vnbo.classes.table <br/>
* 详细描述: TODO(TableRead实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class TableRead implements Operational{
	@JSONField(name="id")
	private String tableid;
	private HashSet<FlowEntry> flow;
	@JSONField(name="opendaylight-flow-table-statistics:flow-table-statistics")
	private TableStatic tableStatic;
	
	public String getTableid() {
		return tableid;
	}


	public void setTableid(String tableid) {
		this.tableid = tableid;
	}


	public HashSet<FlowEntry> getFlow() {
		return flow;
	}


	public void setFlow(HashSet<FlowEntry> flow) {
		this.flow = flow;
	}


	public TableStatic getTableStatic() {
		return tableStatic;
	}


	public void setTableStatic(TableStatic tableStatic) {
		this.tableStatic = tableStatic;
	}
	
	@Override
	public String toString() {
		return "TableRead [tableid=" + tableid + ", flow=" + flow + ", tableStatic=" + tableStatic + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flow == null) ? 0 : flow.hashCode());
		result = prime * result + ((tableStatic == null) ? 0 : tableStatic.hashCode());
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
		TableRead other = (TableRead) obj;
		if (flow == null) {
			if (other.flow != null)
				return false;
		} else if (!flow.equals(other.flow))
			return false;
		if (tableStatic == null) {
			if (other.tableStatic != null)
				return false;
		} else if (!tableStatic.equals(other.tableStatic))
			return false;
		if (tableid == null) {
			if (other.tableid != null)
				return false;
		} else if (!tableid.equals(other.tableid))
			return false;
		return true;
	}


	@Override
	public TableRead read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		String url="http://"+OperationalUrl+"/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+tableid;
		String s=HttpUtil.Get_request(url)[1];
	    JSONObject flowtable=JSONObject.parseObject(s);
//		System.out.println(s);
		JSONArray jsonArray=flowtable.getJSONArray("flow-node-inventory:table");
		JSONObject tablejson=jsonArray.getJSONObject(0);
		TableRead tableRead=JSON.toJavaObject(tablejson, TableRead.class);
		return tableRead;
	}
	public static void main(String []args){
		TableRead tableRead=new TableRead();
		tableRead.setTableid("0");
		try {
			tableRead.read("openflow:1");
		} catch (ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
