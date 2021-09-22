package com.bridgelabz.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;


public class CabInvoiceGeneratorTest {
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance,time);
		Assert.assertEquals(25, fare, 0.0);
	}
}
