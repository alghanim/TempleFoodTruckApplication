package com.example.templefoodtruckapplication;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ApiConnector {

	public JSONArray getAllTrucks()
	{
		String url = "http://cis-linux2.temple.edu/~tuf15070/foodtruck.php";
		
		HttpEntity httpEntity = null;
		JSONArray jsonArray = null;
		
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);						
			HttpResponse httpResponse = client.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			
    	} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(httpEntity != null){
			try {
				String entityResponse = EntityUtils.toString(httpEntity);
				Log.e("Entity Response: ", entityResponse);
				jsonArray = new JSONArray(entityResponse);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return jsonArray;	
	}
}

