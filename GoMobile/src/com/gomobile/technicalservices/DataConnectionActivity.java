package com.gomobile.technicalservices;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.gomobile.R;
import com.gomobile.data.controller.BikeDataController;
import com.gomobile.data.controller.EmployeesDataController;
import com.gomobile.model.Employee;
import com.gomobile.model.RepairOrder;

/**
 * Misused for testing data management functionality on an android device.
 * @author Anton
 *
 */
public class DataConnectionActivity extends Activity {

    private final String ODATA_SERVICE_URL = "http://vm20.hcc.uni-magdeburg.de:8000/sap/opu/odata/sap/Z_MAT_SEARCH_EAN/SearchMaterial(EAN='4011200296908')";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_data_connection);
	
	TextView dataInfoView = (TextView) findViewById(R.id.queryResultTextView);
	String text = "";
	
	BikeDataController dataController = new BikeDataController();
	
	EmployeesDataController edc = new EmployeesDataController();
	for(Employee employer : edc.getAllEmployees()){
		text = text +  "\n" + employer.getFirstName() + " " + employer.getLastName();
	}
	
	dataInfoView.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.data_connection, menu);
	
	return true;
    }
    
    private Map<String, Node> getMapFromQueryResult(String idPath){
	Map<String, Node> map = new HashMap<String, Node>();
	
	try 
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(idPath);

	    XPathFactory xPathFactory = XPathFactory.newInstance();
	    XPath xpath = xPathFactory.newXPath();
	    XPathExpression expr = xpath.compile(idPath);
	    NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	    
	    if (list.getLength() == 0)
	    {
	    	Log.i("Database connection:", "ERROR: no ids (" + idPath +") found!");
	    }
	    
            for (int i = 0; i < list.getLength(); i++)
            {
          	   	map.put(list.item(i).getNodeValue(), list.item(i)); //changed to get the attribute value as ID
            }

	}
	catch (Exception e)
	{
	   Log.e("Database connection error:", e.toString());
	   e.printStackTrace();
	} 
	
	return map;

    }
}
