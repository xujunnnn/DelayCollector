package com.ebupt.vnbo.classes.exception;
/**
 * 
* 类名: ConfigException.java <br/>
* 包名 : com.ebupt.vnbo.classes.exception <br/>
* 详细描述: TODO(用于表示配置信息发送失败的异常类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class ConfigException extends ODL_IO_Exception{
	private static final long serialVersionUID = 1L;
	public ConfigException(){};
	public ConfigException(String info){
		super(info);
	}

}
