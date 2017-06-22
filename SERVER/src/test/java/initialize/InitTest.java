package initialize;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.config.InitServiceConfig;
import com.ebupt.vnbo.classes.enums.OperationType;
import com.ebupt.vnbo.classes.enums.Tag;
import com.ebupt.vnbo.request.initialize.InitRequest;
import com.ebupt.vnbo.service.initialize.InitService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = InitServiceConfig.class)
public class InitTest {
private InitService initService;
@Test
public void InitTest(){
    initService.initBaseFlow();
}

}
