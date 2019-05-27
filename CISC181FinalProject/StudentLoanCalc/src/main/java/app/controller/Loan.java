package app.controller;
import org.apache.poi.ss.formula.functions.FinanceLib;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Loan {
	
	double loanAmt;
	double iRate;
	int nTerms;
	String startDate;
	double ePmt;


	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public double getiRate() {
		return iRate;
	}

	public void setiRate(double iRate) {
		this.iRate = iRate;
	}

	public int getnTerms() {
		return nTerms;
	}

	public void setnTerms(int nTerms) {
		this.nTerms = nTerms;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public double getePmt() {
		return ePmt;
	}

	public void setePmt(double ePmt) {
		this.ePmt = ePmt;
	}

	public Loan(double loanAmt, double iRate, int nTerms, String startDate, double ePmt){
		
		this.loanAmt = loanAmt;
		this.iRate = iRate / 1200;
		this.nTerms = nTerms * 12;
		this.startDate = startDate;
		this.ePmt = ePmt;
	}
	
	public static void printLoan(Loan loan) {
		System.out.println(loan.getLoanAmt());
		System.out.println(loan.getiRate());
		System.out.println(loan.getnTerms());
		System.out.println(loan.getStartDate());
		System.out.println(loan.getePmt());
	}
	
	public static void main(String[] args) {
		Loan studentLoan = new Loan(10000, 3, 10, "January", 100);
		printLoan(studentLoan);
	}
	
}