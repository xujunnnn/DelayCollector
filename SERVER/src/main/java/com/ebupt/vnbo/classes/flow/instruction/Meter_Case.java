package com.ebupt.vnbo.classes.flow.instruction;
import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* ����: Meter_Case.java <br/>
* ���� : com.ebupt.vnbo.classes.flow.instruction <br/>
* ��ϸ����: TODO(���ڱ�ʾmeter_case ��ʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��12�� <br/>
* �����汾�� V1.0  <br/>
 */
public class Meter_Case{
	@JSONField(name="meter-id")
	private String meter_id;

	public String getMeter_id() {
		return meter_id;
	}
	@JSONField(name="meter-id")
	public Meter_Case setMeter(String meter_id) {
		this.meter_id = meter_id;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meter_id == null) ? 0 : meter_id.hashCode());
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
		Meter_Case other = (Meter_Case) obj;
		if (meter_id == null) {
			if (other.meter_id != null)
				return false;
		} else if (!meter_id.equals(other.meter_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Meter_Case [meter_id=" + meter_id + "]";
	}

	
}
	