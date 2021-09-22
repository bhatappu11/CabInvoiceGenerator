package com.bridgelabz.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.cabinvoicegenerator.Ride.RideType;

public class CabInvoiceGenerator {
	
	private static final double MINIMUM_COST_PER_KM = 10;
	private static final int COST_PER_TIME = 1;
	private static final double MINIMUM_FARE = 5;
	
	private static final double PREMIUM_MINIMUM_COST_PER_KM = 20;
	private static final int PREMIUM_COST_PER_TIME = 2;
	private static final double PREMIUM_MINIMUM_FARE = 20;
	
	RideRepository rideRepo = new RideRepository();

	public double calculateFare(double distance, int time, RideType type) {
		if(type.equals(RideType.NORMAL_RIDE)) {
			double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
			return Math.max(totalFare, MINIMUM_FARE);	
		}
		else {
			double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KM + time * PREMIUM_COST_PER_TIME;
			return Math.max(totalFare, PREMIUM_MINIMUM_FARE);	
		}
	}
	public double calculateFare(Ride[] rides,RideType type) {
		double totalFare = 0;
		for(Ride ride:rides) {
			totalFare += this.calculateFare(ride.distance,ride.time,type);
		}
		return totalFare;
		
	}

	public InvoiceSummary generateSummary(Ride[] rides,RideType type) {
		double totalFare = 0;
		for(Ride ride:rides) {
			totalFare += this.calculateFare(ride.distance,ride.time,type);
		}
		return new InvoiceSummary(rides.length, totalFare);
		
	}
	public void addUserRide(String userId, Ride ride) {
		rideRepo.addUsersRideDetails(userId,ride);
		
	}
	public InvoiceSummary generateSummaryByUserId(String userId,RideType type) {
		ArrayList<Ride> rides = rideRepo.getRidesList(userId);
		Ride[] ridesArray = new Ride[rides.size()];
		ridesArray = rides.toArray(ridesArray);
		return this.generateSummary(ridesArray,type);
	}

}
