package com.ebupt.vnbo.classes.exception;
/**
 * 
* 类名: OperationalException.java <br/>
* 包名 : com.ebupt.vnbo.classes.exception <br/>
* 详细描述: TODO(用于表示读取配置信息失败的异常类 ) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class OperationalException extends ODL_IO_Exception{
	private static final long serialVersionUID = 1L;

	public OperationalException(){}
	public OperationalException(String info){
		super(info);
	}

}
