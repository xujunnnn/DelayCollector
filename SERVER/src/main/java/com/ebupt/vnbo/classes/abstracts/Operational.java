package com.ebupt.vnbo.classes.abstracts;

import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.BaseUtil;
/**
 * 
* ����: Operational.java <br/>
* ���� : com.ebupt.vnbo.classes.abstracts <br/>
* ��ϸ����: TODO(������Ϣ��ѯ�ӿ�) <br/>
* ������Ա�� xujun   <br/>
* �������ڣ�2017��6��12�� <br/>
* �����汾�� V1.0  <br/>
 */
public interface Operational {
	public static final String OperationalUrl=BaseUtil.getODL_IP()+"/restconf/operational";
	public static final String VtnOperationUrl=BaseUtil.getODL_IP()+"/restconf/operations";
	public abstract Operational read(String node) throws ODL_IO_Exception;

}
