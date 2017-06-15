package com.ebupt.vnbo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VTopoUtil {
	private static Map<String, Integer> vlan_idMap=new ConcurrentHashMap<String, Integer>();
 /**
  * assign vlanid for different vbridges
  * @param vtopo
  * @return
  */
	public static int get_Vlan(String vtopo){
		int vlan=vlan_idMap.get(vtopo);
		vlan_idMap.put(vtopo, vlan+1);
		return vlan;
	}
	
	
	public static void initVlan(String vtopo){
		vlan_idMap.put(vtopo, 1);
	}

	
}
