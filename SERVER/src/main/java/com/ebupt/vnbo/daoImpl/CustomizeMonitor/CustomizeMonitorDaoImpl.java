package com.ebupt.vnbo.daoImpl.CustomizeMonitor;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Protocol_Type;
import com.ebupt.vnbo.classes.monitor.CustomizeMonitor;
import com.ebupt.vnbo.dao.CustomizeMonitor.CustomizeMonitorDao;
import com.ebupt.vnbo.request.monitor.CustomizeMonRequest;
import com.ebupt.vnbo.util.DBUtil;
/**
 * 
* ����: CustomizeMonitorDaoImpl.java <br/>
* ���� : com.ebupt.vnbo.daoImpl.CustomizeMonitor <br/>
* ��ϸ����: TODO(�Զ�����������ݳ־ò�ʵ����) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��8�� <br/>
* �����汾�� V1.0  <br/>
 */

public class CustomizeMonitorDaoImpl implements CustomizeMonitorDao {

	@Override
	public void Insert(CustomizeMonitor customizeMonitor) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		session.insert("CustomizeMon.insertCustomizeMon",customizeMonitor);
		session.commit();		
	}

	@Override
	public void delete(CustomizeMonitor customizeMonitor) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		session.insert("CustomizeMon.deleteCustomizeMon",customizeMonitor);
		session.commit();		
		
	}

	@Override
	public void update(CustomizeMonitor customizeMonitor) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		session.insert("CustomizeMon.updateCustmizeMon",customizeMonitor);
		session.commit();
	}

	@Override
	public CustomizeMonitor querry(CustomizeMonitor customizeMonitor) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		CustomizeMonitor customizeMonitor2=session.selectOne("CustomizeMon.querryCustomizeMon",customizeMonitor);
		session.commit();
		return customizeMonitor2;
	}

	@Override
	public List<CustomizeMonitor> querryAll() throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		List<CustomizeMonitor> customizeMonitors=session.selectList("CustomizeMon.querryAllCustmoizeMon");
		session.commit();
		return customizeMonitors;
	}
	
	public static void main(String []args){
		CustomizeMonitorDaoImpl customizeMonitorDaoImpl=new CustomizeMonitorDaoImpl();
		CustomizeMonitor customizeMonitor=new CustomizeMonitor();
		customizeMonitor.setId("1");
		customizeMonitor.setSrchost("h1");
		customizeMonitor.setDesthost("h2");
		customizeMonitor.setProtocol_Type(Protocol_Type.TCP);
		customizeMonitor.setTcp_srcPort("100");
		CustomizeMonRequest customizeMonRequest=new CustomizeMonRequest();
		customizeMonRequest.setOperationType(OperationType.ADD);
		customizeMonRequest.setCustomizeMonitor(customizeMonitor);
		System.out.println(JSON.toJSONString(customizeMonRequest));
		try {
			customizeMonitorDaoImpl.Insert(customizeMonitor);
			System.out.println(JSON.toJSONString(customizeMonitorDaoImpl.querryAll()));
			//customizeMonitorDaoImpl.delete(customizeMonitor);
			System.out.println(JSON.toJSONString(customizeMonitorDaoImpl.querryAll()));
			System.out.println(customizeMonitorDaoImpl.contains(customizeMonitor));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean contains(CustomizeMonitor customizeMonitor) throws IOException {
		// TODO Auto-generated method stub
		CustomizeMonitor customizeMonitor2=querry(customizeMonitor);
		if(customizeMonitor2==null)
			return false;
		else
			return true;
	}
	  
	
	

	
}
