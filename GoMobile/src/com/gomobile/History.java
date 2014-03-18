package com.gomobile;

import java.util.ArrayList;

public class History extends ArrayList<Component>{
	
	private int listSize = 10;

	public History(int listSize){
		super();
	}
	
	public History() {
		super();
	}
	
	@Override
	public boolean add(Component object) {
		if(this.size() == listSize){
			this.remove(listSize-1);
		}
		return super.add(object);
	}
	
}
