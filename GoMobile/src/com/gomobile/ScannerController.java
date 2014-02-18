package com.gomobile;

import com.gomobile.scanner.model.Component;

public class ScannerController {
		
	private Component componentInUse;
	
	public void setComponentInUse(Component component){
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
