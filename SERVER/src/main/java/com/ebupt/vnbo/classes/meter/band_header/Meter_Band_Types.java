package com.ebupt.vnbo.classes.meter.band_header;

/**
 * 
* 类名: Meter_Band_Types.java <br/>
* 包名 : com.ebupt.vnbo.classes.meter.band_header <br/>
* 详细描述: TODO(Meter_Band_Types实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */

public class Meter_Band_Types{
	private String flags;

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flags == null) ? 0 : flags.hashCode());
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
		Meter_Band_Types other = (Meter_Band_Types) obj;
		if (flags == null) {
			if (other.flags != null)
				return false;
		} else if (!flags.equals(other.flags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meter_Band_Types [flags=" + flags + "]";
	}
	
	
	
}
