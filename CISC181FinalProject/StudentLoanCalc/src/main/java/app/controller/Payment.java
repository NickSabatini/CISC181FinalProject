package app.controller;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class Payment {

	int ID;
	String dueDate;
	double aPmt;
	double ePmt;
	double iPmt;
	double pPmt;
	double oldBalance;
	double newBalance;
	double iRate;
	
	
	public double getiRate() {
		return iRate;
	}
	public void setiRate(double iRate) {
		this.iRate = iRate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getaPmt() {
		return aPmt;
	}
	public void setaPmt(double aPmt) {
		this.aPmt = aPmt;
	}
	public double getePmt() {
		return ePmt;
	}
	public void setePmt(double ePmt) {
		this.ePmt = ePmt;
	}
	public double getiPmt() {
		return iPmt;
	}
	public void setiPmt(double iPmt) {
		this.iPmt = iPmt;
	}
	public double getpPmt() {
		return pPmt;
	}
	public void setpPmt(double pPmt) {
		this.pPmt = pPmt;
	}
	public double getOldBalance() {
		return oldBalance;
	}
	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	
	//to create the first payment, a loan will be passed into the constructor
	public Payment(Loan loan) {
		
		this.ID = 1;
		this.dueDate = loan.getStartDate();
		this.aPmt = (loan.getLoanAmt() * loan.getiRate() * Math.pow((1 + loan.getiRate()), loan.getnTerms())) 
				/ (Math.pow(1+ loan.getiRate(), loan.getnTerms()) -1);
		this.ePmt = loan.getePmt();
		this.iPmt = loan.getLoanAmt() * loan.getiRate();
		this.pPmt = this.aPmt - this.iPmt + this.ePmt;
		this.oldBalance = loan.getLoanAmt();
		this.newBalance = this.oldBalance - this.pPmt;
		this.iRate = loan.getiRate();
		
	}
	
	//this creates all middle payments
	public Payment(Payment prior) {
		
		this.ID = prior.getID() + 1;
		this.dueDate = prior.getDueDate();
		this.aPmt = prior.getaPmt();
		this.ePmt = prior.getePmt();
		this.iRate = prior.getiRate();
		this.oldBalance = prior.getNewBalance();
		this.iPmt = this.oldBalance * this.iRate;
		this.pPmt = this.aPmt + this.ePmt - this.iPmt;
		this.newBalance = this.oldBalance - this.pPmt;
	}
	
	//to create payments using a loan
	public static LinkedList<Payment> createPayments(Loan loan) {
		
		Payment firstPayment = new Payment(loan);
		LinkedList<Payment> allPayments = new LinkedList<Payment>();
		allPayments.add(firstPayment);
		
		Payment p = firstPayment;
		
		while (allPayments.size() < loan.getnTerms() && allPayments.getLast().getNewBalance() >
			firstPayment.getaPmt() + firstPayment.getePmt()) {
			
			Payment c = new Payment(p);
			allPayments.add(c);
			p = c;
		}
		
		Payment veryLast = new Payment(allPayments.getLast());
		double remBalance = allPayments.getLast().getNewBalance();
		
		if (remBalance > allPayments.getLast().getaPmt()) {
			veryLast.setePmt(remBalance - allPayments.getLast().getaPmt());
		}
		else {
			veryLast.setePmt(0);
			veryLast.setiPmt(veryLast.getOldBalance() * veryLast.getiRate());
			veryLast.setpPmt(veryLast.getOldBalance());
			veryLast.setaPmt(veryLast.getpPmt() + veryLast.getiPmt());
		}
		veryLast.setNewBalance(0);
		
		allPayments.add(veryLast);
		return allPayments;
	}
	
	
	
	public static void printPayment(Payment pmt) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		System.out.println("Payment ID: " +pmt.getID());
		System.out.println("Payment Date: " +pmt.getDueDate());
		System.out.println("Interest Rate: " +formatter.format((pmt.getiRate() * 100)) + "%");
		System.out.println("Scheduled Payment: " + formatter.format(pmt.getaPmt()));//this is the anuity without the extra
		System.out.println("Extra Payment: " +formatter.format(pmt.getePmt()));//this is the extra amount
		System.out.println("Interest Payment: " +formatter.format(pmt.getiPmt()));//part of total that covers the term interest
		System.out.println("Principle Payment: " +formatter.format(pmt.getpPmt()));//part of total that is toward the principle
		System.out.println("Old Balance: " +formatter.format(pmt.getOldBalance()));
		System.out.println("New Balance: " +formatter.format(pmt.getNewBalance()) + "\n\n");
	}
	
	public static void main(String[] args) {
		//please enter any numbers you'd like into the loan constructor
		// (Loan amount, interest rate, years, date, extra payment)
		Loan studentLoan = new Loan(50000, 4, 20, "January", 1000);
		LinkedList<Payment> printThese = createPayments(studentLoan);
		
		for (Payment i : printThese) {
			printPayment(i);
		}
		
		System.out.println("You are going to have to make " + printThese.getLast().getID() +
				" payments until your loan is paid off, better save up!");
	}
	
}
