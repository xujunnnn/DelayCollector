package com.ebupt.vnbo.daoImpl.TopoInfo;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.ebupt.vnbo.classes.topology.TopoInfo;
import com.ebupt.vnbo.dao.TopoInfo.TopoInfoDao;
import com.ebupt.vnbo.util.DBUtil;

public class TopoInfoDaoImpl implements TopoInfoDao{

	@Override
	public void insert(TopoInfo topoInfo) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();		
		session.insert("TopoInfo.insertTopoInfo",topoInfo);
		session.commit();
		
	}

	@Override
	public void delete(TopoInfo topoInfo) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();
		session.delete("TopoInfo.deleteTopoInfo",topoInfo);
		session.commit();
	}

	@Override
	public TopoInfo querry(TopoInfo topoInfo) throws IOException {
		// TODO Auto-generated method stub
		TopoInfo Topoinfo;
		SqlSession session=null;
		session=DBUtil.getSqlSession();
		Topoinfo=session.selectOne("TopoInfo.querryTopoInfoWithName",topoInfo);
		return Topoinfo;
	}

	@Override
	public List<TopoInfo> querryAll() throws IOException {
		// TODO Auto-generated method stub
		List<TopoInfo> topoInfos;
		SqlSession session=null;
		session=DBUtil.getSqlSession();
		topoInfos=session.selectList("TopoInfo.querryTopoInfo");
		return topoInfos;
	}

	@Override
	public void update(TopoInfo topoInfo) throws IOException {
		// TODO Auto-generated method stub
		SqlSession session=null;
		session=DBUtil.getSqlSession();
		session=DBUtil.getSqlSession();
		session.update("TopoInfo.updateTopoInfo", topoInfo);
		session.commit();		
	}
	public static void main(String []args){
		TopoInfo topoInfo=new TopoInfo();
		String s="变电站1";
			// TODO Auto-generated catch block

		topoInfo.setName("openflow:3").setDescription(s);
		TopoInfoDaoImpl topoInfoDaoImpl=new TopoInfoDaoImpl();
		try {
			topoInfoDaoImpl.insert(topoInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<TopoInfo> topoInfos;
			topoInfos=topoInfoDaoImpl.querryAll();
			System.out.println(JSON.toJSON(topoInfos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			topoInfoDaoImpl.delete(topoInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<TopoInfo> topoInfos;
			topoInfos=topoInfoDaoImpl.querryAll();
			System.out.println(JSON.toJSON(topoInfos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			topoInfo.setName("openflow:1");
			topoInfo.setDescription("山东省的");
			topoInfoDaoImpl.update(topoInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<TopoInfo> topoInfos;
			topoInfos=topoInfoDaoImpl.querryAll();
			System.out.println(JSON.toJSON(topoInfos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			TopoInfo topoInfo2;
			topoInfo2=topoInfoDaoImpl.querry(topoInfo);
			System.out.println(JSON.toJSON(topoInfo2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
