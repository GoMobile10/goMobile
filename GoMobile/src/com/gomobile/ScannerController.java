package com.gomobile;

import com.gomobile.model.Material;

/**
 * This is a controller class to...
 * @author Tim Endlich
 *
 */

public class ScannerController {
	
	private History history = new History();

	public Material getMaterialInUse(){
		return history.getMaterialInUse();
	}
	
	public boolean setMaterialInUse(Material comp){
		history.add(comp);
		if(history.isSameType(comp)){
			return true;
		}else{
	        return false;
		}
	}
	
	public Material getMaterialBefore() {
		return history.getMaterialBefore();
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
