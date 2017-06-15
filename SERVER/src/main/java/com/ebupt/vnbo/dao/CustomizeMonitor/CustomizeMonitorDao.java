package com.ebupt.vnbo.dao.CustomizeMonitor;
import java.io.IOException;

import com.ebupt.vnbo.classes.monitor.CustomizeMonitor;
/**
 * 
* 类名: CustomizeMonitorDao.java <br/>
* 包名 : com.ebupt.vnbo.dao.CustomizeMonitor <br/>
* 详细描述: TODO(自定义监控器数据持久层接口) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月8日 <br/>
* 发布版本： V1.0  <br/>
 */
public interface CustomizeMonitorDao  {
	/**
	 * 
	* 方法名：Insert</br>
	* 详述：TODO（插入一条信息）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void Insert(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* 方法名：delete</br>
	* 详述：TODO（删除指定监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void delete(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* 方法名：update</br>
	* 详述：TODO（在数据库中删除指定自定义监控器）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitor
	* @throws
	 */
	public void update(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* 方法名：querry</br>
	* 详述：TODO（查询指定自定义监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @param customizeMonitor
	* @return
	* @throws
	 */
	public CustomizeMonitor querry(CustomizeMonitor customizeMonitor) throws IOException;
	/**
	 * 
	* 方法名：querryAll</br>
	* 详述：TODO（查询所有监控项）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月8日  </br>
	* @return
	* @throws
	 */
	public java.util.List<CustomizeMonitor> querryAll() throws IOException;
	/**
	 * 
	* 方法名：contains</br>
	* 详述：TODO（查询是否已经包含此条信息）</br>
	* 开发人员：xujun</br>
	* 创建时间：2017年6月9日  </br>
	* @param customizeMonitor
	* @return
	* @throws IOException
	* @throws
	 */
	public boolean contains(CustomizeMonitor customizeMonitor) throws IOException;

}
