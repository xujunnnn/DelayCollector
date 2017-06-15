package com.ebupt.vnbo.classes.flow.instruction;
import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Instruction.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.instruction <br/>
* 详细描述: TODO(用于表示流表中的Instruction实体类
* ) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Instruction {
	private String order;
	@JSONField(name="apply-actions")
	private Apply_Actions apply_Actions;
	@JSONField(name="go-to-table")
	private Go_To_Table go_To_Table;
	@JSONField(name="meter")
	private Meter_Case meter_Case;
	public Meter_Case getMeter_Case() {
		return meter_Case;
	}
	public void setMeter_Case(Meter_Case meter_Case) {
		this.meter_Case = meter_Case;
	}
	public String getOrder() {
		return order;
	}
	public Instruction setOrder(String order) {
		this.order = order;
		return this;
	}

	public Go_To_Table getGo_To_Table() {
		return go_To_Table;
	}
	public Instruction setGo_To_Table(Go_To_Table go_To_Table) {
		this.go_To_Table = go_To_Table;
		return this;
	}

	public Instruction Set_Go_To_Table_Id(String table_id){
		Go_To_Table go_To_Table=new Go_To_Table();
		go_To_Table.setTable_id(table_id);
	    this.setGo_To_Table(go_To_Table);
		return this;
	}
	public Instruction Set_Meter(String meter){
		Meter_Case meter_case=new Meter_Case();
		meter_case.setMeter(meter);
		this.setMeter_Case(meter_case);
		return this;
	}

	public Apply_Actions getApply_Actions() {
		return apply_Actions;
	}
	public Instruction setApply_Actions(Apply_Actions apply_Actions) {
		this.apply_Actions = apply_Actions;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apply_Actions == null) ? 0 : apply_Actions.hashCode());
		result = prime * result + ((go_To_Table == null) ? 0 : go_To_Table.hashCode());
		result = prime * result + ((meter_Case == null) ? 0 : meter_Case.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		Instruction other = (Instruction) obj;
		if (apply_Actions == null) {
			if (other.apply_Actions != null)
				return false;
		} else if (!apply_Actions.equals(other.apply_Actions))
			return false;
		if (go_To_Table == null) {
			if (other.go_To_Table != null)
				return false;
		} else if (!go_To_Table.equals(other.go_To_Table))
			return false;
		if (meter_Case == null) {
			if (other.meter_Case != null)
				return false;
		} else if (!meter_Case.equals(other.meter_Case))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Instruction [order=" + order + ", apply_Actions=" + apply_Actions + ", go_To_Table=" + go_To_Table
				+ ", meter_Case=" + meter_Case + "]";
	}

}
	

