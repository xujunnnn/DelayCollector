package com.ebupt.vnbo.dao.qos;

import java.io.IOException;
import java.util.List;

import com.ebupt.vnbo.classes.qos.QosEntry;



public interface QosEntryDao {
	/**
	 * 鍚戞暟鎹簱涓彃鍏os
	 * @param qosEntry
	 * @throws IOException
	 */
	public void insert(QosEntry qosEntry) throws IOException;
	/**
	 * 浠庢暟鎹簱涓垹闄os
	 * @param qosEntry
	 * @throws IOException
	 */
	public void delete(QosEntry qosEntry) throws IOException;
	/**
	 * 浠庢暟鎹簱涓煡璇㈡寚瀹氱殑qos
	 * @param qosEntry
	 * @throws IOException
	 */
	public QosEntry querry(QosEntry qosEntry) throws IOException;
	/**
	 * 妫�鏌ユ暟鎹簱涓槸鍚﹀凡缁忓瓨鍦ㄨqos
	 * @param qosEntry
	 * @return
	 * @throws IOException
	 */
	public String contains(QosEntry qosEntry) throws IOException;
	/**
	 * 鏌ヨ鏁版嵁搴撲腑鐨勬墍鏈塹os
	 * @return
	 * @throws IOException
	 */
	public List<QosEntry> querryAll() throws IOException;
	

}
