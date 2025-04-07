package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	
	Faker fk;
	User userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setUp() {
		
		fk= new Faker();
		userPayload=new User();
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password(5, 10));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
		
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}
		@Test(priority=1)
		public void testPostUser(){
			
			logger.info("******************Creating the user****************");
			Response response=UserEndpoints.createUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("****************** User Created****************");
	}
		@Test(priority=2)
		public void testGetUserbyName() throws InterruptedException {
			Thread.sleep(5000);
			logger.info("******************retrieving the user****************");
			Response response=UserEndpoints.readUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******************User retrieved****************");
		}
		@Test(priority=3)
		public void testUpdateUser() throws InterruptedException{
			logger.info("******************updating the user****************");
			//update data using payload
			userPayload.setUsername(fk.name().username());
			userPayload.setFirstName(fk.name().firstName());
			userPayload.setLastName(fk.name().lastName());
			
			Thread.sleep(5000);
			
			Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		
			logger.info("******************user updated****************");
			//checking data after updation
			
			Thread.sleep(5000);
			logger.info("******************Checking User is updated or not****************");
			Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			responseAfterUpdate.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			logger.info("******************Checked for updated****************");
	}
		
		@Test(priority=4)
		public void testDeleteUserbyName(){
			logger.info("******************deleting the user****************");
			Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******************user died****************");
		}	
}
