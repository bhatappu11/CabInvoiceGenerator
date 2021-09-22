package com.bridgelabz.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.List;

public class CabInvoiceGenerator {

	private static final double MINIMUM_COST_PER_KM = 10;
	private static final int COST_PER_TIME = 1;
	private static final double MINIMUM_FARE = 5;
	RideRepository rideRepo = new RideRepository();

	public double calculateFare(double distance, int time) {
		double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
		return Math.max(totalFare, MINIMUM_FARE);			
	}
	public double calculateFare(Ride[] rides) {
		double totalFare = 0;
		for(Ride ride:rides) {
			totalFare += this.calculateFare(ride.distance,ride.time);
		}
		return totalFare;
		
	}

	public InvoiceSummary generateSummary(Ride[] rides) {
		double totalFare = 0;
		for(Ride ride:rides) {
			totalFare += this.calculateFare(ride.distance,ride.time);
		}
		return new InvoiceSummary(rides.length, totalFare);
		
	}
	public void addUserRide(String userId, Ride ride) {
		rideRepo.addUsersRideDetails(userId,ride);
		
	}
	public InvoiceSummary generateSummaryByUserId(String userId) {
		ArrayList<Ride> rides = rideRepo.getRidesList(userId);
		Ride[] ridesArray = new Ride[rides.size()];
		ridesArray = rides.toArray(ridesArray);
		return this.generateSummary(ridesArray);
	}

}
