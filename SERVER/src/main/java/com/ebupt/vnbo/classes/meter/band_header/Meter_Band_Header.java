package com.ebupt.vnbo.classes.meter.band_header;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
* 类名: Meter_Band_Header.java <br/>
* 包名 : com.ebupt.vnbo.classes.meter.band_header <br/>
* 详细描述: TODO(Meter_Band_Header的实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class Meter_Band_Header {
	@JSONField(name="band-id")
	private String band_id;
	@JSONField(name="band-rate")
	private String band_rate;
	@JSONField(name="band-burst-size")
	private String band_burst_size;
	@JSONField(name="drop-rate")
	private String drop_rate;
	@JSONField(name="drop-burst-size")
	private String drop_burst_size;
	@JSONField(name="dscp-remark-burst-size")
	private String dscp_remark_burst_size;
	@JSONField(name="dscp-remark-rate")
	private String dscp_remark_rate;
	@JSONField(name="meter-band-types")
	private Meter_Band_Types meter_Band_Types;
	public String getDscp_remark_burst_size() {
		return dscp_remark_burst_size;
	}
	public Meter_Band_Header setDscp_remark_burst_size(String dscp_remark_burst_size) {
		this.dscp_remark_burst_size = dscp_remark_burst_size;
		return this;
	}
	public String getDscp_remark_rate() {
		return dscp_remark_rate;
	}
	public Meter_Band_Header setDscp_remark_rate(String dscp_remark_rate) {
		this.dscp_remark_rate = dscp_remark_rate;
		return this;
	}
	public String getPrec_level() {
		return prec_level;
	}
	public Meter_Band_Header setPrec_level(String prec_level) {
		this.prec_level = prec_level;
		return this;
	}
	private String prec_level;
	public String getBand_id() {
		return band_id;
	}
	public Meter_Band_Header setBand_id(String band_id) {
		this.band_id = band_id;
		return this;
	}
	public String getBand_rate() {
		return band_rate;
		
	}
	public Meter_Band_Header setBand_rate(String band_rate) {
		this.band_rate = band_rate;
		return this;
	}
	public String getBand_burst_size() {
		return band_burst_size;
		
	}
	public Meter_Band_Header setBand_burst_size(String band_burst_size) {
		this.band_burst_size = band_burst_size;
		return this;
	}
	public String getDrop_rate() {
		return drop_rate;
		
	}
	public Meter_Band_Header setDrop_rate(String drop_rate) {
		this.drop_rate = drop_rate;
		return this;
	}
	public String getDrop_burst_size() {
		return drop_burst_size;
	}
	public Meter_Band_Header setDrop_burst_size(String drop_burst_size) {
		this.drop_burst_size = drop_burst_size;
		return this;
	}
	public Meter_Band_Types getMeter_Band_Types() {
		return meter_Band_Types;
	}
	public Meter_Band_Header setMeter_Band_Types(Meter_Band_Types meter_Band_Types) {
		this.meter_Band_Types = meter_Band_Types;
        return this;
	}
	public Meter_Band_Header Set_Type(String type){
		Meter_Band_Types meter_Band_Types=new Meter_Band_Types();
		meter_Band_Types.setFlags(type);
		this.meter_Band_Types=meter_Band_Types;getClass();
		return this; 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((band_burst_size == null) ? 0 : band_burst_size.hashCode());
		result = prime * result + ((band_id == null) ? 0 : band_id.hashCode());
		result = prime * result + ((band_rate == null) ? 0 : band_rate.hashCode());
		result = prime * result + ((drop_burst_size == null) ? 0 : drop_burst_size.hashCode());
		result = prime * result + ((drop_rate == null) ? 0 : drop_rate.hashCode());
		result = prime * result + ((dscp_remark_burst_size == null) ? 0 : dscp_remark_burst_size.hashCode());
		result = prime * result + ((dscp_remark_rate == null) ? 0 : dscp_remark_rate.hashCode());
		result = prime * result + ((meter_Band_Types == null) ? 0 : meter_Band_Types.hashCode());
		result = prime * result + ((prec_level == null) ? 0 : prec_level.hashCode());
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
		Meter_Band_Header other = (Meter_Band_Header) obj;
		if (band_burst_size == null) {
			if (other.band_burst_size != null)
				return false;
		} else if (!band_burst_size.equals(other.band_burst_size))
			return false;
		if (band_id == null) {
			if (other.band_id != null)
				return false;
		} else if (!band_id.equals(other.band_id))
			return false;
		if (band_rate == null) {
			if (other.band_rate != null)
				return false;
		} else if (!band_rate.equals(other.band_rate))
			return false;
		if (drop_burst_size == null) {
			if (other.drop_burst_size != null)
				return false;
		} else if (!drop_burst_size.equals(other.drop_burst_size))
			return false;
		if (drop_rate == null) {
			if (other.drop_rate != null)
				return false;
		} else if (!drop_rate.equals(other.drop_rate))
			return false;
		if (dscp_remark_burst_size == null) {
			if (other.dscp_remark_burst_size != null)
				return false;
		} else if (!dscp_remark_burst_size.equals(other.dscp_remark_burst_size))
			return false;
		if (dscp_remark_rate == null) {
			if (other.dscp_remark_rate != null)
				return false;
		} else if (!dscp_remark_rate.equals(other.dscp_remark_rate))
			return false;
		if (meter_Band_Types == null) {
			if (other.meter_Band_Types != null)
				return false;
		} else if (!meter_Band_Types.equals(other.meter_Band_Types))
			return false;
		if (prec_level == null) {
			if (other.prec_level != null)
				return false;
		} else if (!prec_level.equals(other.prec_level))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Meter_Band_Header [band_id=" + band_id + ", band_rate=" + band_rate + ", band_burst_size="
				+ band_burst_size + ", drop_rate=" + drop_rate + ", drop_burst_size=" + drop_burst_size
				+ ", dscp_remark_burst_size=" + dscp_remark_burst_size + ", dscp_remark_rate=" + dscp_remark_rate
				+ ", meter_Band_Types=" + meter_Band_Types + ", prec_level=" + prec_level + "]";
	}
	
	
	

}

