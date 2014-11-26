package com.example.templefoodtruckapplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

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

		getMap();
	}
	
	// sets up the map
	private void getMap() {
		FragmentManager fm = getSupportFragmentManager();
		SupportMapFragment fragment = (SupportMapFragment) fm
				.findFragmentById(R.id.map);
		googleMap = fragment.getMap();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
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
