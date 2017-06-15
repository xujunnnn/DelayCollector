package com.ebupt.vnbo.classes.abstracts;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.BaseUtil;
/**
 * 
* ����: Config.java <br/>
* ���� : com.ebupt.vnbo.classes.abstracts <br/>
* ��ϸ����: TODO(������Ϣ�ӿ�) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��8�� <br/>
* �����汾�� V1.0  <br/>
 */
public interface  Config {
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
	public static final String ConfigUrl=BaseUtil.getODL_IP()+"/restconf/config";
	public static final String VtnConfigUrl=BaseUtil.getODL_IP()+"/restconf/operations";
	/**
	 * 
	* ��������send</br>
	* ������TODO������һ��������Ϣ��</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param node
	* @throws ODL_IO_Exception
	* @throws
	 */
	public  void send(String node) throws ODL_IO_Exception;
	/**
	 * 
	* ��������remove</br>
	* ������TODO��ɾ��һ��������Ϣ��</br>
	* ������Ա��xujun</br>
	* ����ʱ�䣺2017��6��8��  </br>
	* @param node
	* @throws ODL_IO_Exception
	* @throws
	 */
	public  void remove(String node) throws ODL_IO_Exception;
}
