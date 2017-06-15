package com.ebupt.vnbo.classes.flow.flow_static;
/**
 * 
* 类名: Duration.java <br/>
* 包名 : com.ebupt.vnbo.classes.flow.flow_static <br/>
* 详细描述: TODO(用于表示流表中的时间信息的实体类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Duration {
	private long nanosecond;
	private long second;
	public long getNanosecond() {
		return nanosecond;
	}
	public void setNanosecond(long nanosecond) {
		this.nanosecond = nanosecond;
	}
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (nanosecond ^ (nanosecond >>> 32));
		result = prime * result + (int) (second ^ (second >>> 32));
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
		Duration other = (Duration) obj;
		if (nanosecond != other.nanosecond)
			return false;
		if (second != other.second)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Duration [nanosecond=" + nanosecond + ", second=" + second + "]";
	}

}
