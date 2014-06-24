package com.gomobile.repair;

import java.util.ArrayList;
import java.util.List;





import com.gomobile.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ToDoListAdapter extends ArrayAdapter<String>{
	
	private Context context;
	private int layoutResourceId;
	private List<String> repairToDoListData;
	private String toDoItem;
	
	
	public ToDoListAdapter(Context context,int layoutResourceId, List<String> repairToDoListData){
		
		super(context, layoutResourceId, repairToDoListData);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.repairToDoListData = repairToDoListData;
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}
		// Find the ToDo to work with
		toDoItem = repairToDoListData.get(position);

		// Fill the view items with values
		TextView textToDo = (TextView) convertView.findViewById(R.id.toDoItemId);
		textToDo.setText(toDoItem);
		
		
				
		
		return convertView;
		
	}
}
