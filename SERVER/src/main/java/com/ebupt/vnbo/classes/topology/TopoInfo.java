package com.ebupt.vnbo.classes.topology;

import com.ebupt.vnbo.classes.abstracts.Config;
import com.ebupt.vnbo.classes.abstracts.Operational;
import com.ebupt.vnbo.classes.exception.ODL_IO_Exception;

public class TopoInfo  implements Config,Operational{
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public TopoInfo setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public TopoInfo setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public Operational read(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String node) throws ODL_IO_Exception {
		// TODO Auto-generated method stub
		
	}

}
