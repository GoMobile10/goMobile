package com.gomobile;

import java.util.ArrayList;

import com.gomobile.navigation.Navigation;
import com.gomobile.scanner.model.Component;

public class ScannerController {
		
	private Component componentInUse;
	private int listSize = 10;
	private ArrayList<Component> history = new ArrayList<Component>();
	
	public void setComponentInUse(Component component){
		if(history.size() == listSize){
			history.remove(9);
		}
		history.add(componentInUse);
		this.componentInUse = component;
	}
	
	public Component getComponentInUse(){
		return componentInUse;
	}
	
	
	//singleton
	private static ScannerController instance = null;
	
	private ScannerController(){}
	
	public static synchronized ScannerController getInstance(){
		if (instance == null){
			instance = new ScannerController();
		}
		return instance;
	}
	//singleton end
}
