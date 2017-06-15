package com.ebupt.vnbo.dao.CustomizeMonitor;
import java.io.IOException;

import com.ebupt.vnbo.classes.monitor.CustomizeMonitor;
/**
 * 
* ����: CustomizeMonitorDao.java <br/>
* ���� : com.ebupt.vnbo.dao.CustomizeMonitor <br/>
* ��ϸ����: TODO(�Զ����������ݳ־ò�ӿ�) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��8�� <br/>
* �����汾�� V1.0  <br/>
 */
public interface CustomizeMonitorDao  {
	/**
	 * 
	* ��������Insert</br>
	* ������TODO������һ����Ϣ��</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void Insert(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* ��������delete</br>
	* ������TODO��ɾ��ָ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void delete(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* ��������update</br>
	* ������TODO�������ݿ���ɾ��ָ���Զ���������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void update(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* ��������querry</br>
	* ������TODO����ѯָ���Զ������</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param customizeMonitor
	* @return
	* @throws
	 */
	public CustomizeMonitor querry(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* ��������querryAll</br>
	* ������TODO����ѯ���м���</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @return
	* @throws
	 */
	public java.util.List<CustomizeMonitor> querryAll() throws IOException;
	/**
	 * 
	* ��������contains</br>
	* ������TODO����ѯ�Ƿ��Ѿ�����������Ϣ��</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��9��  </br>
	* @param customizeMonitor
	* @return
	* @throws IOException
	* @throws
	 */
	public boolean contains(CustomizeMonitor customizeMonitor) throws IOException;

}
