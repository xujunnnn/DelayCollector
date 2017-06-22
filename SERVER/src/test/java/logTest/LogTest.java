package logTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.glass.ui.TouchInputSupport;

public class LogTest {
	private static Logger logger=LoggerFactory.getLogger(LogTest.class);
	public static void main(String []args){
		logger.info("info");
		logger.error("error");
		System.out.println(System.getProperties().getProperty("user.home"));
		String logdir=System.getProperties().getProperty("user.home");
		System.out.println(logdir);
		System.out.println(System.getProperty("user.dir"));
		
	}

}
