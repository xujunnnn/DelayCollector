package com.ebupt.vnbo.service;

import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.request.Request;

/**
 * 
* 类名: WebService.java <br/>
* 包名 : com.ebupt.vnbo.service <br/>
* 详细描述: TODO(web服务功能接口) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月8日 <br/>
* 发布版本： V1.0  <br/>
 */
public interface WebService {
	public String CUSTOMIZETABLE="1";
	public String QOSFLOWTABLE="0";
	public String VTNFLOWTABLE="5";
	public String MONFLOWTABLE="3";
	public String LOWPRIORITY="200";
	public String MIDPRIORITY="210";
	public String HIGHPRIORITY="220";
	public String IDLE_TIME_OUT="0";
	public String HARD_TIME_OUT="0";
	public String VTN_FLOW_IDLE="3600";
	public String VTN_FLOW_HARD="0";
	public JSONObject resolve(Request request);
	
}
