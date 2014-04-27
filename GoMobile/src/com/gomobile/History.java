package com.gomobile;

import java.util.ArrayList;

import com.gomobile.model.Component;
import com.gomobile.model.Material;

@SuppressWarnings("serial")
public class History extends ArrayList<Material>{

	private int listSize = 10;

	public History(int length){
		super();
		listSize = length;
	}
	
	public History() {
		super();
	}

	@Override
	public boolean add(Material object) {
		if(this.size() == listSize){
			this.remove(9);
		}
		return super.add(object);
	}
		
	public boolean isSameType(Material comp){
		try {
			//TODO 
			return true;
//			return getComponentBefore().isSameType(comp);			
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Material getMaterialBefore(){
			return this.get(1);
	}
	
	public Material getMaterialInUse(){
		return this.get(0);
	}
	
}
