package com.ebupt.vnbo.serviceImpl.qos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.exception.ConfigException;
import com.ebupt.vnbo.classes.exception.OperationalException;
import com.ebupt.vnbo.classes.qos.QosEntry;
import com.ebupt.vnbo.dao.qos.QosEntryDao;
import com.ebupt.vnbo.daoImpl.qos.QosEntryDaoImpl;
import com.ebupt.vnbo.request.Request;
import com.ebupt.vnbo.request.qos.QosRequest;
import com.ebupt.vnbo.service.qos.QosService;


public class QosServiceImpl implements QosService{

	public JSONObject resolve(Request request) {
		// TODO Auto-generated method stub
		QosRequest qosRequest=(QosRequest) request;
		if(qosRequest.getOperationType()==OperationType.ADD){
			QosEntry qosEntry=qosRequest.getQosEntry();
			qosEntry.setHash(qosEntry.hashCode());
			return addQos(qosEntry);
		}
		if(qosRequest.getOperationType()==OperationType.REMOVE){
			return deleteQos(qosRequest.getQosEntry());
		}
		if(qosRequest.getOperationType()==OperationType.QUERRYALL){
			return querryQos();
		}
		if(qosRequest.getOperationType()==OperationType.QUERRY){
			return QuerryQos(qosRequest.getQosEntries());
		}
		JSONObject result=new JSONObject();
		result.put("Status", -1);
		result.put("description", "error OperationType");
		return result;	
	}
	/**
	 * 娣诲姞qos
	 * @param qos
	 * @return
	 */
	private JSONObject addQos(QosEntry qos) {
		JSONObject result=new JSONObject();
		QosEntryDao qosEntryDao=new QosEntryDaoImpl();
		//test if the qospolicy is in the table
		String contain=null;
		try {
			contain = qosEntryDao.contains(qos);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result.put("Status", -1);
			result.put("description", "failed to check the QosPolicy  qosid= "+qos.getQos_id());
					
		}
		if(contain==null){
		// apply the qos
		try {
			qos.send(null);
		} catch ( ConfigException | OperationalException e) {
			// TODO Auto-generated catch block
			result.put("Status", -1);
			result.put("description", "failed to creat QosPolicy  qosid= "+qos.getQos_id());
			e.printStackTrace();	
			return result;
		}
		//insert the qos	
		try {
			qosEntryDao.insert(qos);
			result.put("Status", 0);
			result.put("description", "QosEntry created success qosid= "+qos.getQos_id());
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result.put("Status", -1);
			result.put("description", "successed to creat QosPolicy but failed to save  qosid= "+qos.getQos_id());
			e.printStackTrace();
			return result;
		
		}
		
	  }
		else{
			result.put("Status", -1);
			result.put("description", "QosPolicy "+qos.getQos_id()+" collide with "+contain);
			return result;
		
		}
	}
	/**
	 * 鍒犻櫎qos
	 * @param qosEntry
	 * @return
	 */
	private JSONObject deleteQos(QosEntry qosEntry){
		JSONObject result=new JSONObject();
		QosEntryDao qosEntryDao=new QosEntryDaoImpl();
		try {
			qosEntry.remove(null);
			qosEntryDao.delete(qosEntry);
			result.put("Status", 0);
			result.put("description", "delete success "+qosEntry.getQos_id());
			return result;		
		} catch (IOException |ConfigException|OperationalException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("Status", -1);
			result.put("description", "failed to delete qospolicy");
			return result;
		}
		
		
	}
	/**
	 * 鏌ヨ鎸囧畾鐨剄os
	 * @param qosEntry
	 * @return
	 */
	private JSONObject QuerryQos(List<QosEntry> qosEntries){
		List<QosEntry> qEntries=new ArrayList<>();
		QosEntryDao qosEntryDao=new QosEntryDaoImpl();
		JSONObject result=new JSONObject();
		for(QosEntry q:qosEntries){
			try {
				QosEntry qosEntry=qosEntryDao.querry(q);
				qEntries.add(qosEntry);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("Status", -1);
				result.put("description", "failed to delete qospolicy");
				return result;
			}			
		}
		result.put("Status", 0);
		result.put("description", "querry success ");
		result.put("QosEntries", JSON.toJSON(qEntries));
		return result;
		
	}

	@SuppressWarnings("finally")
	private JSONObject querryQos(){
		QosEntryDao qosEntryDao=new QosEntryDaoImpl();
		JSONObject result=new JSONObject();
		try {
			List<QosEntry> qosEntries=qosEntryDao.querryAll();
			result.put("Status", 0);
			result.put("description", "querry success");
			result.put("QosEntrys", JSON.toJSON(qosEntries));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result.put("status", -1);
			result.put("description", "failed to querry");
			e.printStackTrace();

		}
	finally {
		return result;
	}
	}

}
