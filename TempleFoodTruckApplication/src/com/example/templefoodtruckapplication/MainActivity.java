package com.example.templefoodtruckapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements LocationListener{

	private GoogleMap googleMap;
	private int numOfTrucks = 10;
	LocationManager locationManager;
	String locationProvider;
	Marker posMarker;
	Location userLocation;
	LatLng truckLocation;
	LatLng[] truckLocations = new LatLng[numOfTrucks];
	double[] truckDistanceFromUser = new double[numOfTrucks];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button findNearestTrucks = (Button)findViewById(R.id.btnFindNearestTrucks);
		
		findNearestTrucks.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//findDistanceFromUserToTruck();
			}

			private void findDistanceFromUserToTruck() {
				
				
			}
		});
		
		new getAllTrucksTask().execute(new ApiConnector());

		getMap();
		UserCurrentLocation();	
	}
	
	// sets up the map
	private void getMap() {
		FragmentManager fm = getSupportFragmentManager();
		SupportMapFragment fragment = (SupportMapFragment) fm
				.findFragmentById(R.id.map);
		googleMap = fragment.getMap();
	}

	// provide current user location
		private void UserCurrentLocation() { 
	  
			googleMap.setMyLocationEnabled(true);
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			locationProvider = LocationManager.GPS_PROVIDER;
			userLocation = locationManager.getLastKnownLocation(locationProvider);

			googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			double latitude = userLocation.getLatitude();
			double longitude = userLocation.getLongitude();

			LatLng latLng = new LatLng(latitude, longitude);
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
			this.posMarker = googleMap.addMarker(new MarkerOptions().position(
					new LatLng(latitude, longitude)).title("You are here!"));
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
			this.posMarker.remove();
		}  

		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			locationManager.removeUpdates(this);
		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			locationManager.requestLocationUpdates(locationProvider, 500, 1, this);     
		}

		// gets all the trucks from ApiConnector class which returns a JSONArray
		class getAllTrucksTask extends AsyncTask<ApiConnector, Long, JSONArray> {

			@Override
			protected JSONArray doInBackground(ApiConnector... params) {
				return params[0].getAllTrucks();
			}

			protected void onPostExecute(JSONArray jsonArray) {
				setTrucksOnMap(jsonArray);
			}
		}  

		// displays all the trucks on the map
		private void setTrucksOnMap(JSONArray jsonArray) {
			String s = "";
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = null;

				try {
					json = jsonArray.getJSONObject(i);

					String truck_name = json.getString("Truck_Name");
					double truckLat = Double.parseDouble(json.getString("Lat"));
					double truckLng = Double.parseDouble(json.getString("Lng"));
					truckLocation = new LatLng(truckLat, truckLng);

					truckLocations[i] = truckLocation;
					Log.e("truckDistanceFromUser: ", truckLocations[i].toString());
					
					this.posMarker = googleMap.addMarker(new MarkerOptions()
							.position(truckLocation).title(truck_name));
					// s = s+
					// "name: " + truck_name + "\n" +
					// "lat: " + lat + "\n"
					// + "lng: " + lng + "\n";
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	   
		private void EnterDistancesIntoDatabase(){
//			try{
//				HttpClient httpClient = new DefaultHttpClient();
//				
//			}
//			catch()
//			{
//				
//			}
		}
		


		// gives updated user location as he/she moves to a new position
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			this.posMarker.remove();
			String provider = location.getProvider();
			float accuracy = location.getAccuracy();
			long time = location.getTime();
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			
			Log.e("provider: ", provider);
			Log.e("lat: ", "" + latitude);
			Log.e("lng: ", "" + longitude);
			Log.e("accuracy: ", "" + accuracy);
			Log.e("time: ", "" + time);
			
			this.posMarker = googleMap.addMarker(new MarkerOptions().position(
					new LatLng(latitude, longitude)).title("You are here!"));
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}
		
}
