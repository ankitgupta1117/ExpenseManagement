package com.ankit.expensemgmt.demo;

import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.cli.ParseException;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.exceptions.DynamoDBLocalServiceException;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;

public class Mainclass {
	
	public static void main1(String[] args) {
		AmazonDynamoDB db  = AmazonDynamoDBClientBuilder.standard().build();
		
		DynamoDBMapper mapper  = new DynamoDBMapper(db);
		ExpenseTable expense = new ExpenseTable();
		expense.setAmount(100f);
		expense.setExpenseDate(new Date(LocalDate.now().toEpochDay()));
		expense.setName("Test Expense");
//		mapper.generateCreateTableRequest(ExpenseTable.class);
		ExpenseTable e = mapper.load(ExpenseTable.class, new Date(LocalDate.now().toEpochDay()));
		e.setName("Test Expense");
		e.setAmount(100f);
		mapper.save(e);
		
	}

	
	public static void main(String[] args1) throws DynamoDBLocalServiceException, ParseException, Exception {
//		AmazonDynamoDB db = DynamoDBEmbedded.create().amazonDynamoDB();
//		ListTablesResult result = db.listTables();
//		System.out.println("Number of tables: "+result.getTableNames().size());
		System.setProperty("aws.accessKeyId", "x");
	    System.setProperty("aws.secretKey", "x");
	    System.setProperty("java.library.path", "C:\\Users\\ankgupta1\\.m2\\repository\\com\\amazonaws\\DynamoDBLocal\\1.11.119\\DynamoDBLocal-1.11.119.jar");
	    System.setProperty("sqlite4java.library.path", "native-libs");
		String [] args = {/*"-dbPath" , "C:\\Dynamo"*/"-inMemory"};
		ServerRunner.createServerFromCommandLineArgs(args).start();
		
		
	}
	
}
