package pkgUT;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.*;
import org.junit.Test;

import app.controller.Loan;
import app.controller.Payment;

public class TestPMT {

	@Test
	public void test() {
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));
		
		double PMTExpected = 1162.95;
		
		assertEquals(PMTExpected, PMT, 0.01);
		
		Loan testLoan = new Loan(p, r*1200, 20 , "Start date", 0);
		
		Payment testPayment = new Payment(testLoan);
		
		assertEquals(testPayment.getaPmt(), PMTExpected, 0.01);
		assertEquals(testPayment.getOldBalance(), p , 0.01);

		
	}

}

 









