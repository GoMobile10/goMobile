package com.gomobile;

import java.util.ArrayList;

import com.gomobile.scanner.model.Component;

@SuppressWarnings("serial")
public class History extends ArrayList<Component>{

	private int listSize = 10;

	public History(int length){
		super();
		listSize = length;
	}
	
	public History() {
		super();
	}

	@Override
	public boolean add(Component object) {
		if(this.size() == listSize){
			this.remove(9);
		}
		return super.add(object);
	}
		
	public boolean isSameType(Component comp){
		try {
			return getComponentBefore().isSameType(comp);			
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Component getComponentBefore(){
			return this.get(1);
	}
	
	public Component getComponentInUse(){
		return this.get(0);
	}
	
}
