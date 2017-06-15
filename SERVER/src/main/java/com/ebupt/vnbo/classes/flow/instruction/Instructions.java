package com.ebupt.vnbo.classes.flow.instruction;

import java.util.ArrayList;
public class Instructions {
	/**
	 * 
	 * ���췽����: 
	 * ����: (������һ�仰�����������������)
	 * ������Ա��xujun
	 * ����ʱ�䣺
	 * ˵����������
	 */
	public Instructions(){}
	
	private ArrayList<Instruction> instruction=new ArrayList<Instruction>();

	public ArrayList<Instruction> getInstruction() {
		return instruction;
	}

	public void setInstruction(ArrayList<Instruction> instruction) {
		this.instruction = instruction;
	}
	public Instructions(ArrayList<Instruction> instructions){
		this.instruction=instructions;
	}
	public Instructions addInstruction(Instruction instruction){
		this.instruction.add(Integer.parseInt(instruction.getOrder()), instruction);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instruction == null) ? 0 : instruction.hashCode());
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
		Instructions other = (Instructions) obj;
		if (instruction == null) {
			if (other.instruction != null)
				return false;
		} else if (!instruction.equals(other.instruction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instructions [instruction=" + instruction + "]";
	}


	
	
}
