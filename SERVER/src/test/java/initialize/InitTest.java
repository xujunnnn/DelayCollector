package initialize;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.initialize.InitRequest;

public class InitTest {
	public static void main(String []args){
	InitRequest initRequest=new InitRequest();
	initRequest.setOperationType(OperationType.INIT);
	initRequest.setTag(Tag.MONITOR);
	System.out.println(JSON.toJSON(initRequest));
	}

}
