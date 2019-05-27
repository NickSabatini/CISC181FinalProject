package app.controller;

import app.StudentCalc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private Label lblTotalPayemnts;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AddPayment;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		System.out.println("Loan Amount: " + dLoanAmount);
		
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		System.out.println("Loan Interest Rate: " + dInterestRate);
		
		int iNbrOfYears = Integer.parseInt(NbrOfYears.getText());
		System.out.println("Loan Length in Years: " + iNbrOfYears);
		
		LocalDate localDate = PaymentStartDate.getValue();
		System.out.println("Loan Start Date: " +localDate);
		
		double dAddPayment = Double.parseDouble(AddPayment.getText());
		System.out.println("Extra Payment is: " + dAddPayment);
		
		lblTotalPayemnts.setText("123");
		
		Loan studentLoan = new Loan(dLoanAmount, dInterestRate, iNbrOfYears, "Not Ready Yet", 0.00);
		
		Payment first = new Payment(studentLoan);
		
	}	
}
