package com.ankit.expensemgmt.demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class ClientDB {

	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
            // we can use any region here
            new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();
	private static DynamoDBMapper mapper  = new DynamoDBMapper(client);
	
	public static void main(String[] args) {
	    System.out.println("Listing Tables");
	    client.listTables().getTableNames().forEach(System.out::println);
	    
	    if(client.listTables().getTableNames().size() ==0) {
	    	System.out.println("No table present.. Creating one..");
	    	createTable(ExpenseTable.class);
	    }
	   saveExpense();
		
//		ExpenseTable e = mapper.load(ExpenseTable.class,Date.from(Instant.from(LocalDate.of(2018, 11, 3).atStartOfDay(ZoneId.systemDefault()))));
//		System.out.println(e);
		
	}
	private static void createTable(Class c) {
		CreateTableRequest createRequest = mapper.generateCreateTableRequest(c);
		createRequest.setProvisionedThroughput(new ProvisionedThroughput(5l, 5l));;
	    client.createTable(createRequest);
	}
	
	private static void saveExpense() {
		ExpenseTable expense = new ExpenseTable();
		expense.setExpenseDate(Date.from(Instant.from(LocalDate.of(2018, 11, 3).atStartOfDay(ZoneId.systemDefault()))));
		expense.setName("Test Expense");
		expense.setName("Test Expense");
		expense.setAmount(99f);
		mapper.save(expense);
	}
}
