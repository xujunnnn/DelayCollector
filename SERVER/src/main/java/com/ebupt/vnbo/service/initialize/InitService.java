package com.ebupt.vnbo.service.initialize;

import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.service.WebService;

public interface InitService  extends WebService{

	/**
	 * 初始化基本的通信流表
	 * @return
	 */
	public JSONObject initBaseFlow();
	/**
	 * 初始化监控的流表
	 * @return
	 */
	public JSONObject initMonitor() ;

}
