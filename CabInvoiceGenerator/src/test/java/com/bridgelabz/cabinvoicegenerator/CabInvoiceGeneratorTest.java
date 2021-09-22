package com.bridgelabz.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.cabinvoicegenerator.Ride.RideType;


public class CabInvoiceGeneratorTest {
	CabInvoiceGenerator invoiceGenerator = null;
	@Before
	public void setUp() throws Exception {
		invoiceGenerator = new CabInvoiceGenerator();
	}
	
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance,time,RideType.NORMAL_RIDE);
		Assert.assertEquals(25, fare, 0.0);
	}
	
	@Test
	public void givenLessDistanceAndTime_ShouldReturnTotalFare() {
		double distance = 0.1;
		int time = 1;
		double fare = invoiceGenerator.calculateFare(distance,time,RideType.NORMAL_RIDE);
		Assert.assertEquals(5, fare, 0.0);
	}
	
	@Test
	public void givenMultipleRides_ShouldReturnTotalFare() {
		Ride[] rides = { new Ride(2.0,5,RideType.NORMAL_RIDE),
						 new Ride(0.1,1,RideType.NORMAL_RIDE),
		};
		double fare = invoiceGenerator.calculateFare(rides,RideType.NORMAL_RIDE);
		Assert.assertEquals(30, fare, 0.0);
	}
	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(2.0,5,RideType.NORMAL_RIDE),
						 new Ride(0.1,1,RideType.NORMAL_RIDE),
		};
		InvoiceSummary summary = invoiceGenerator.generateSummary(rides,RideType.NORMAL_RIDE);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
		Assert.assertEquals(expectedInvoiceSummary,summary);
	}
	@Test
	public void givenUserId_ShouldReturnInvoiceSummary() {
		String userId = "C1";
		invoiceGenerator.addUserRide(userId,new Ride(2.0, 5,RideType.NORMAL_RIDE));
		invoiceGenerator.addUserRide(userId,new Ride(0.1, 1,RideType.NORMAL_RIDE));
		InvoiceSummary summary = invoiceGenerator.generateSummaryByUserId(userId,RideType.NORMAL_RIDE);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}
	
	@Test
	public void givenDistanceAndTime_ForPremimumRide_ShouldReturnTotalFare() {
		double distance = 6.0;
		int time = 10;
		double fare = invoiceGenerator.calculateFare(distance,time,RideType.PREMIUM_RIDE);
		Assert.assertEquals(140, fare, 0.0);
	}
	
	@Test
	public void givenLessDistanceAndTime_ForPremimumRide_ShouldReturnTotalFare() {
		double distance = 0.7;
		int time = 2;
		double fare = invoiceGenerator.calculateFare(distance,time,RideType.PREMIUM_RIDE);
		Assert.assertEquals(20, fare, 0.0);
	}
	
	@Test
	public void givenMultipleRides_ForPremimumRide_ShouldReturnTotalFare() {
		Ride[] rides = { new Ride(6.0,10,RideType.PREMIUM_RIDE),
						 new Ride(0.7,2,RideType.PREMIUM_RIDE),
		};
		double fare = invoiceGenerator.calculateFare(rides,RideType.PREMIUM_RIDE);
		Assert.assertEquals(160, fare, 0.0);
	}
	@Test
	public void givenMultipleRides_ForPremimumRide_ShouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(6.0,10,RideType.PREMIUM_RIDE),
						 new Ride(0.7,2,RideType.PREMIUM_RIDE),
		};
		InvoiceSummary summary = invoiceGenerator.generateSummary(rides,RideType.PREMIUM_RIDE);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,160.0);
		Assert.assertEquals(expectedInvoiceSummary,summary);
	}
	@Test
	public void givenUserId_ForPremimumRide_ShouldReturnInvoiceSummary() {
		String userId = "C2";
		invoiceGenerator.addUserRide(userId,new Ride(6.0, 10,RideType.PREMIUM_RIDE));
		invoiceGenerator.addUserRide(userId,new Ride(0.7, 2,RideType.PREMIUM_RIDE));
		InvoiceSummary summary = invoiceGenerator.generateSummaryByUserId(userId,RideType.PREMIUM_RIDE);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,160.0);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}
}
