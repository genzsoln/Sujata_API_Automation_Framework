package api.test;

import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.asserts.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import java.util.logging.Logger;

public class UserTests2 
{
	Faker faker;
	User userPayload;
	public Logger logger; // this variable we have to use in setupdata method
	
	@BeforeClass //this will execution before starting execution of all the methods
	public void setup()
	{
		faker=new Faker();
		userPayload=new User(); //what ever data we create using faker same data we have to pass to POJO class
		
		userPayload.setId(faker.idNumber().hashCode());   //generating id
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Once you pass all the information to POJO class i.e "Userjava" this class is having some data
		// then this userPayload data we have to pass to testRequest i.e post,put,delete,get
	
		//Log
		logger=LogManager.getLogger(this.getClass()); //return the logger
		
		logger.debug("Debugging---------------------------------");
	}
	
	//For Create User
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************** Create User *************");
		
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("************** User Is Created *************");

	}
	
	//Get User
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("************** Reading User Info *************");

		Response response=UserEndPoints2.readUser(this.userPayload.getUsername()); //extract username // we refer payload by refering this keyword
		//this will gives user name of your payload
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("************** User Info is displayed *************");
	}
	
	//update data Using payload
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("************** Updating User *************");

		//update user requires 2 para username & payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload); //extract username
		//response.then().log().all();
		response.then().log().body().statusCode(200); //HI assertion comes with restasuured
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("************** User is Updated *************");

		//Checking data after update
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPayload.getUsername()); //extract username
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
	}
	
	//Delete
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("************** Deleting User *************");

		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername()); //extract username	
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("************** USer Deleted *************");

	}
}

//1st we need to set up data using pojo
//by using faker we create the data (we are generating our own id)
//for postuser we are calling create user method by passing userpayload