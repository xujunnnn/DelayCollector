package com.ebupt.vnbo.serviceImpl.Monitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import sun.util.logging.resources.logging;

/**
 * 
* ����: CustomizeMonitorServiceImpl.java <br/>
* ���� : com.ebupt.vnbo.serviceImpl.Monitor <br/>
* ��ϸ����: TODO(�����Զ����������ķ���ʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��8�� <br/>
* �����汾�� V1.0  <br/>
 */
public class CustomizeMonitorServiceImpl implements CustomizeMonitorService {
	private static Logger logger=LoggerFactory.getLogger(CustomizeMonitorServiceImpl.class);
	@Override
	public JSONObject resolve(Request request) {
		// TODO Auto-generated method stub
		CustomizeMonRequest customizeMonRequest=(CustomizeMonRequest) request;
		OperationType operationType=customizeMonRequest.getOperationType();
		logger.info("receive CustomizeMonRequest {}",customizeMonRequest.toString());
		
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
* ��������addMon</br>
* ������TODO�����һ���Զ����ز��ԣ�</br>
* ������Ա��xujun</br>
* ����ʱ�䣺2017��6��8��  </br>
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
			logger.info("success to add CustomizeMonitor {}",customizeMonitor.getId());
			result.put("Status", 0);
			result.put("description", "success to creat customize monitor  "+customizeMonitor.getId());
			}
			else {
				logger.error("fail to add CustomizeMonitor {}",customizeMonitor.getId());
				result.put("Status", -1);
				result.put("description", "this customize monitor  "+customizeMonitor.getId()+"has been added before");			
			}
		} catch (ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to add CustomizeMonitor {} error deatils {}",customizeMonitor.getId(),e.getMessage());
			result.put("Status", -1);
			result.put("description", "failed to creat customize monitor  "+customizeMonitor.getId());
		}
		finally{
			return result;
		}
	
	}
	/**
	 * 
	* ��������removeMon</br>
	* ������TODO��ɾ��ָ�����Զ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
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
			logger.info("success to remove CustomizeMonitor {}",customizeMonitor.getId());
			result.put("Status", 0);
			result.put("description", "success to creat customize monitor  "+customizeMonitor.getId());
		} catch (ODL_IO_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to add CustomizeMonitor {}, error datail {}",customizeMonitor.getId(),e.getMessage());
			result.put("Status", 1);
			result.put("description", "failed to creat customize monitor  "+customizeMonitor.getId());
		}
		finally {
			return result;
		}
		
		
	}
	/**
	 * 
	* ��������querryMon</br>
	* ������TODO����ѯָ�����Զ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
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
			logger.info("success to querry CustomizeMonitor {}",customizeMonitor.getId());
			result.put("Status", 0);
			result.put("description", "success to querry customize monitor  "+customizeMonitor.getId());
			result.put("CustomizeMonitor", JSON.toJSON(customizeMonitor));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to querry CustomizeMonitor {}, error details",customizeMonitor.getId(),e.getMessage());
			result.put("Status", 1);
			result.put("description", "failed to querry customize monitor  "+customizeMonitor.getId());
		}
	    finally {
			return result;
		}
		
	}
	/**
	 * 
	* ��������querryAllMon</br>
	* ������TODO����ѯ���е��Զ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @return
	* @throws
	 */
	@SuppressWarnings("finally")
	private JSONObject querryAllMon(){
		JSONObject result=new JSONObject();
		CustomizeMonitorDao customizeMonitorDao=new CustomizeMonitorDaoImpl();
		try {
			List<CustomizeMonitor> customizeMonitors=customizeMonitorDao.querryAll();
			logger.info("success to querry CustomizeMonitor ");
			result.put("Status", 0);
			result.put("description", "success to querry customize monitor  ");
			result.put("CustomizeMonitors", JSON.toJSON(customizeMonitors));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("fail to querry CustomizeMonitor ");
			result.put("Status", 1);
			result.put("description", "failed to querry customize monitor  ");
		}
		finally {
			return result;
		}
	}
	/**
	 * 
	* ��������querryMons</br>
	* ������TODO��һ���Բ�ѯ����ָ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
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
