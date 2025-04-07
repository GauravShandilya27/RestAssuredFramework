package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;

import api.payload.User;
import io.restassured.response.Response;


//This filr is created for using data from UserEndpoints22
public class UserTests2 {
	
	
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
		public void testPostUser() throws InterruptedException{
			
			logger.info("******************Creating the user****************");
			Response response=UserEndpoints2.createUser(userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("****************** User Created****************");
			Thread.sleep(10000);
	}
		@Test(priority=2)
		public void testGetUserbyName() throws InterruptedException {
			
			logger.info("******************retrieving the user****************");
			Response response=UserEndpoints2.readUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******************User retrieved****************");
			
			Thread.sleep(10000);
		}
		@Test(priority=3)
		public void testUpdateUser() throws InterruptedException{
			logger.info("******************updating the user****************");
			//update data using payload
			userPayload.setUsername(fk.name().username());
			userPayload.setFirstName(fk.name().firstName());
			userPayload.setLastName(fk.name().lastName());
			
			Thread.sleep(5000);
			
			Response response=UserEndpoints2.updateUser(this.userPayload.getUsername(),userPayload);
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
		
			logger.info("******************user updated****************");
			//checking data after updation
			
			Thread.sleep(5000);
			logger.info("******************Checking User is updated or not****************");
			Response responseAfterUpdate=UserEndpoints2.readUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			responseAfterUpdate.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			logger.info("******************Checked for updated****************");
	}
		
		@Test(priority=4)
		public void testDeleteUserbyName(){
			logger.info("******************deleting the user****************");
			Response response=UserEndpoints2.deleteUser(this.userPayload.getUsername());
			System.out.println("Username is -------------------->"+this.userPayload.getUsername());
			
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("******************user died****************");
		}	
}
