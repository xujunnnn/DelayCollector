package com.ebupt.vnbo.classes.monitor;

import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;
import com.ebupt.vnbo.util.InfluxDBUtil;
/**
 * 
* 类名: LatencyMonitorTask.java <br/>
* 包名 : com.ebupt.vnbo.classes.monitor <br/>
* 详细描述: TODO(时延监控多线程类) <br/>
* 开发人员： xujun   <br/>
* 开发日期：2017年6月12日 <br/>
* 发布版本： V1.0  <br/>
 */
public class LatencyMonitorTask implements Runnable{
	private static final long interval=4000;
	private Latency_list latency_list=new Latency_list();
	private static final String LATENCYMEASUREMENT="latency_load";
	private static boolean isactive;
	
	public static boolean isIsactive() {
		return isactive;
	}

	public LatencyMonitorTask setIsactive(boolean isactive) {
		LatencyMonitorTask.isactive = isactive;
		return this;
	}
	
	
	@Override
	public String toString() {
		return "LatencyMonitorTask [latency_list=" + latency_list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latency_list == null) ? 0 : latency_list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatencyMonitorTask other = (LatencyMonitorTask) obj;
		if (latency_list == null) {
			if (other.latency_list != null)
				return false;
		} else if (!latency_list.equals(other.latency_list))
			return false;
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long timestamp=System.currentTimeMillis();	
		while(isactive){
			try {
				latency_list=latency_list.read(null);
			} catch (ODL_IO_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InfluxDBUtil.putLatency(timestamp, LATENCYMEASUREMENT, latency_list);
			timestamp+=3000;
			System.out.println("latency monitoring");
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String []args){
		LatencyMonitorTask latencyMonitorTask=new LatencyMonitorTask();
		latencyMonitorTask.setIsactive(true);
		Thread thread=new Thread(latencyMonitorTask);
		thread.start();
	}

}
