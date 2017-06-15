package com.ebupt.vnbo.classes.flow.instruction;

import java.util.Objects;
/**
 * 
* 类名: Go_To_Table.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.instruction <br/>
* 详细描述: TODO(用于表示流表中的发送到下一个table实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Go_To_Table{
	private String table_id;

	public String getTable_id() {
		return table_id;
	}

	public Go_To_Table setTable_id(String table_id) {
		this.table_id = table_id;
		return this;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(table_id);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj)
			return true;
		if(obj==null)
			return false;
	    if(this.getClass()!=obj.getClass())
	    	return false;
	    Go_To_Table other=(Go_To_Table) obj;
	    	return Objects.equals(this.table_id, other.getTable_id());
	}
	
	
}