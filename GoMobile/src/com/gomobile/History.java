package com.gomobile;

import java.util.ArrayList;

import com.gomobile.model.Bike;
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

	public boolean add(Material object) {
		if(this.size() == listSize){
			this.remove(9);
		}
		super.add(0,object);
		//TODO
		return true;
	}
		
	public boolean isSameType(Material mat){
		try {
			
			Material matBefore = this.getMaterialBefore();	
			if(matBefore instanceof Bike && mat instanceof Bike){
				if(((Bike)matBefore).getCategory().equals(((Bike)mat).getCategory())){
					return true;
				}
			}
			return false;
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
