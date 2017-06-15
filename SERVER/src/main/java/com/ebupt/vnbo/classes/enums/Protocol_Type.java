package com.ebupt.vnbo.classes.enums;
/**
 * 
* 类名: Protocol_Type.java <br/>
* 包名 : com.ebupt.vnbo.classes.enums <br/>
* 详细描述: TODO(用于表示网络协议的枚举类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public enum Protocol_Type {
    TCP(6),ICMP(1),UDP(17),UNKNOW(0);
	private int value=-1;
    private Protocol_Type(int value) {
	// TODO Auto-generated constructor stub
    	this.value=value;
    }
    public int value(){
    	return this.value;
    }
    public static Protocol_Type Valueof(int value){
    	switch (value) {
		case 1:
			return ICMP;
		case 6:
			return TCP;
		case 17:
			return UDP;
		default:
			return UNKNOW;
			
		}
    }
    
}
