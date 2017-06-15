package com.ebupt.vnbo.classes.abstracts;

import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.BaseUtil;
/**
 * 
* 类名: Operational.java <br/>
* 包名 : com.ebupt.vnbo.classes.abstracts <br/>
* 详细描述: TODO(配置信息查询接口) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public interface Operational {
	public static final String OperationalUrl=BaseUtil.getODL_IP()+"/restconf/operational";
	public static final String VtnOperationUrl=BaseUtil.getODL_IP()+"/restconf/operations";
	public abstract Operational read(String node) throws ODL_IO_Exception;

}
