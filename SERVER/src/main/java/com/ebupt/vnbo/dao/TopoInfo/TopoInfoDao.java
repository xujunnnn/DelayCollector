package com.ebupt.vnbo.dao.TopoInfo;

import java.io.IOException;
import java.util.List;
import com.ebupt.vnbo.classes.topology.TopoInfo;

public interface TopoInfoDao {
	public void insert(TopoInfo topoInfo) throws IOException;
	public void delete(TopoInfo topoInfo) throws IOException;
	public TopoInfo querry(TopoInfo topoInfo) throws IOException;
	public List<TopoInfo> querryAll() throws IOException;
	public void update(TopoInfo topoInfo) throws IOException;

}
