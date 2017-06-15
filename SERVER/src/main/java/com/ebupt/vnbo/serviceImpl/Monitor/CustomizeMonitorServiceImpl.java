package com.ebupt.vnbo.serviceImpl.Monitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.classes.monitor.CustomizeMonitor;
import com.ebupt.vnbo.dao.CustomizeMonitor.CustomizeMonitorDao;
import com.ebupt.vnbo.daoImpl.CustomizeMonitor.CustomizeMonitorDaoImpl;
import com.ebupt.vnbo.request.Request;
import com.ebupt.vnbo.request.monitor.CustomizeMonRequest;
import com.ebupt.vnbo.service.Monitor.CustomizeMonitorService;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 
* 类名: CustomizeMonitorServiceImpl.java <br/>
* 包名 : com.ebupt.vnbo.serviceImpl.Monitor <br/>
* 详细描述: TODO(处理自定义策略请求的服务实现类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月8日 <br/>
* 发布版本： V1.0  <br/>
 */
public class CustomizeMonitorServiceImpl implements CustomizeMonitorService {

	@Override
	public JSONObject resolve(Request request) {
		// TODO Auto-generated method stub
		CustomizeMonRequest customizeMonRequest=(CustomizeMonRequest) request;
		OperationType operationType=customizeMonRequest.getOperationType();
		
		if(operationType==OperationType.ADD)
			return addMon(customizeMonRequest.getCustomizeMonitor());
		if(operationType==OperationType.REMOVE)
			return removeMon(customizeMonRequest.getCustomizeMonitor());
		if(operationType==OperationType.QUERRY)
			return querryMons(customizeMonRequest.getCustomizeMonitors());
		if(operationType==OperationType.QUERRYALL)
			return querryAllMon();
		JSONObject result=new JSONObject();
		result.put("Status", 1);
		result.put("description", "error operationType");
		return result;
		
	}
	
	

/**
 * 
* 方法名：addMon</br>
* 详述：TODO（添加一条自定义监控策略）</br>
* 开发人员：xujun</br>
* 创建时间：2017年6月8日  </br>
* @param customizeMonitor
* @return
* @throws
 */
	@SuppressWarnings("finally")
	private JSONObject addMon(CustomizeMonitor customizeMonitor){
		JSONObject result=new JSONObject();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
		try {
			if(!customizeMonitorDao.contains(customizeMonitor)){
			customizeMonitor.send(null);
			customizeMonitorDao.Insert(customizeMonitor);
			result.put("Status", 0);
			result.put("description", "success to creat customize monitor  "+customizeMonitor.getId());
			}
			else {
				result.put("Status", -1);
				result.put("description", "this customize monitor  "+customizeMonitor.getId()+"has been added before");			
			}
		} catch (ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "failed to creat customize monitor  "+customizeMonitor.getId());
		}
		finally{
			return result;
		}
	
	}
	/**
	 * 
	* 方法名：removeMon</br>
	* 详述：TODO（简单方法可一句话概述）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param monTag
	* @return
	* @throws
	 */
	@SuppressWarnings("finally")
	private JSONObject removeMon(CustomizeMonitor customizeMonitor){
		JSONObject result=new JSONObject();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
		try {
			customizeMonitor.remove(null);
			customizeMonitorDao.delete(customizeMonitor);
			result.put("Status", 0);
			result.put("description", "success to creat customize monitor  "+customizeMonitor.getId());
		} catch (ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", 1);
			result.put("description", "failed to creat customize monitor  "+customizeMonitor.getId());
		}
		finally {
			return result;
		}
		
		
	}
	/**
	 * 
	* 方法名：querryMon</br>
	* 详述：TODO（查询指定的自定义监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitor
	* @return
	* @throws
	 */
	@SuppressWarnings("finally")
	private JSONObject querryMon(CustomizeMonitor customizeMonitor){
		JSONObject result=new JSONObject();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
	    try {
			customizeMonitor=customizeMonitorDao.querry(customizeMonitor);
			result.put("Status", 0);
			result.put("description", "success to querry customize monitor  "+customizeMonitor.getId());
			result.put("CustomizeMonitor", JSON.toJSON(customizeMonitor));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", 1);
			result.put("description", "failed to querry customize monitor  "+customizeMonitor.getId());
		}
	    finally {
			return result;
		}
		
	}
	/**
	 * 
	* 方法名：querryAllMon</br>
	* 详述：TODO（查询所有的自定义监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @return
	* @throws
	 */
	@SuppressWarnings("finally")
	private JSONObject querryAllMon(){
		JSONObject result=new JSONObject();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
		try {
			List<CustomizeMonitor> customizeMonitors=customizeMonitorDao.querryAll();
			result.put("Status", 0);
			result.put("description", "success to querry customize monitor  ");
			result.put("CustomizeMonitors", JSON.toJSON(customizeMonitors));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", 1);
			result.put("description", "failed to querry customize monitor  ");
		}
		finally {
			return result;
		}
	}
	/**
	 * 
	* 方法名：querryMons</br>
	* 详述：TODO（一次性查询多条指定监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitors
	* @return
	* @throws
	 */
	@SuppressWarnings("finally")
	private JSONObject querryMons(List<CustomizeMonitor> customizeMonitors){
		JSONObject result=new JSONObject();
		List<CustomizeMonitor> customizeMonitorsResult=new ArrayList<>();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
		try {
		for(CustomizeMonitor customizeMonitor:customizeMonitors){
			customizeMonitor=customizeMonitorDao.querry(customizeMonitor);		
			customizeMonitorsResult.add(customizeMonitor);		
		   }
		result.put("Status", 0);
		result.put("description", "success to querry customize monitor  ");
		result.put("CustomizeMonitors", JSON.toJSON(customizeMonitorsResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", 1);
			result.put("description", "failed to querry customize monitor  ");
		}
		finally{
			return result;
		}
		
	}
	

		
	

}
