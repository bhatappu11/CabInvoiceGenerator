package com.bridgelabz.cabinvoicegenerator;

public class Ride {

	public double distance;
	public int time;
	public RideType rideType;
	public enum RideType {
		PREMIUM_RIDE,NORMAL_RIDE
	}
	public Ride(double distance, int time,RideType type) {
		this.distance = distance;
		this.time = time;
		this.rideType = type;
	}

}
