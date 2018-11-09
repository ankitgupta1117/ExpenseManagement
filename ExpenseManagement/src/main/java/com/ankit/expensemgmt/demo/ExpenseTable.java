package com.ankit.expensemgmt.demo;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Expense-entries")
public class ExpenseTable {
	@DynamoDBHashKey
	private Date expenseDate;
	
	@DynamoDBAttribute
	private String name;
	
	@DynamoDBAttribute
	private float amount;
	
	
	public Date getExpenseDate() {
		return expenseDate;
	}

	public String getName() {
		return name;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	
}
