package com.bridgelabz.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RideRepository {
	
	HashMap<String, ArrayList<Ride>>  ridesList; 

	public RideRepository() {
		ridesList = new HashMap<String, ArrayList<Ride>>();
	}

	public void addUsersRideDetails(String userId, Ride ride) {
		if(!ridesList.containsKey(userId))
			ridesList.put(userId, new ArrayList<Ride>());
		ridesList.get(userId).add(ride);		
		
	}

	public ArrayList<Ride> getRidesList(String userId) {
		if(ridesList.containsKey(userId)) 
			return ridesList.get(userId);
		else {
			System.err.println("User does not exist");
			return null;
		}
	}

}
