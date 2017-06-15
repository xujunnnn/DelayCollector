package com.ebupt.vnbo.service;

import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.request.Request;

/**
 * 
* ����: WebService.java <br/>
* ���� : com.ebupt.vnbo.service <br/>
* ��ϸ����: TODO(web�����ܽӿ�) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��8�� <br/>
* �����汾�� V1.0  <br/>
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
