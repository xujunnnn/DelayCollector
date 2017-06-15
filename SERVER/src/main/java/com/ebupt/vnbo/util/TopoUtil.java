package com.ebupt.vnbo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.HashSet;
public class TopoUtil {
	private static final String BROADCAST="255.255.255.255";
	  /**
	   * 将byte[]转换为int
	   * @param bytes
	   * @return
	   */
		public static int BytesToInt(byte[] bytes){
			return ByteBuffer.wrap(bytes).getInt();
		}
		/**
		 * 将int 转化为Byte[]
		 * @param i
		 * @return
		 */
		public static byte[] IntToBytes(int i){
			byte[] bytes=new byte[4];
			 bytes[3]=(byte)(i & 0xff);
			 bytes[2]=(byte)((i>>8) & 0xff);
			 bytes[1]=(byte)((i>>16) & 0xff);
			 bytes[0]=(byte)((i>>24) & 0xff);
			 return bytes;
		}
		/**
		 * 获取指定范围的iP
		 *
		 * @param starts
		 * @param ends
		 * @return
		 * @throws UnknownHostException
		 */
		public static HashSet<String> getIpInRange(String starts,String ends) throws UnknownHostException{
				HashSet< String> ipList=new HashSet<>();		
				InetAddress start=InetAddress.getByName(starts);
				InetAddress end=InetAddress.getByName(ends);
				int srcAdd=BytesToInt(start.getAddress());
				int destAdd=BytesToInt(end.getAddress());
				for(int i=srcAdd;i<=destAdd;i++){
				InetAddress inetAddress=InetAddress.getByAddress(IntToBytes(i));
				ipList.add(inetAddress.getHostAddress());			
				}
				return ipList;
		}
		public static String getBroadCastIp(String cidr) throws UnknownHostException{
			String []info=cidr.split("/");
			InetAddress ip=InetAddress.getByName(info[0]);
			InetAddress broad=InetAddress.getByName(BROADCAST);
			int mask=BytesToInt(broad.getAddress());
			int ipint=BytesToInt(ip.getAddress());
			InetAddress broadIp=InetAddress.getByAddress(IntToBytes(ipint | (mask>>>Integer.parseInt(info[1]))));
			return broadIp.getHostAddress();
			
			
		}
		public static void main(String []args){
			try {
				TopoUtil.getBroadCastIp("10.0.0.1/8");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
