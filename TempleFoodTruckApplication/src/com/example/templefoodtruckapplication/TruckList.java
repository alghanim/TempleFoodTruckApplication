package com.example.templefoodtruckapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.*;

public class TruckList extends Activity {

	TextView truckList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_truck_list);
		
		truckList = (TextView) findViewById(R.id.truckList);
		String s = "";
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		
		String SortedTrucks = b.getString("SortedTrucks");
		

		
		truckList.setText(SortedTrucks);
		
	}
}

