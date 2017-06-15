package com.ebupt.vnbo.classes.abstracts;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.BaseUtil;
/**
 * 
* 类名: Config.java <br/>
* 包名 : com.ebupt.vnbo.classes.abstracts <br/>
* 详细描述: TODO(配置信息接口) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月8日 <br/>
* 发布版本： V1.0  <br/>
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
	* 方法名：send</br>
	* 详述：TODO（发送一条配置信息）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param node
	* @throws ODL_IO_Exception
	* @throws
	 */
	public  void send(String node) throws ODL_IO_Exception;
	/**
	 * 
	* 方法名：remove</br>
	* 详述：TODO（删除一条配置信息）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param node
	* @throws ODL_IO_Exception
	* @throws
	 */
	public  void remove(String node) throws ODL_IO_Exception;
}
