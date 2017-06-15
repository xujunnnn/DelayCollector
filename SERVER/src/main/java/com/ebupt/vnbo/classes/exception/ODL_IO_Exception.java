package com.ebupt.vnbo.classes.exception;
/**
 * 
* 类名: ODL_IO_Exception.java <br/>
* 包名 : com.ebupt.vnbo.classes.exception <br/>
* 详细描述: TODO(用于表示配置信息失败的异常类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class ODL_IO_Exception extends Exception{
	private static final long serialVersionUID = 1L;
	public ODL_IO_Exception (){}
	public ODL_IO_Exception(String info){
		super(info);
	}

}
