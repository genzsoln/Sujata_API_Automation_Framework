package api.test;

import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.asserts.*;
//import org.apache.log4j.Logger;
import java.util.logging.Logger;

public class UserTests 
{
	Faker faker;
	User userPayload;
	
	@BeforeClass //this will execution before starting execution of all the methods
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User(); //what ever data we create using faker same data we have to pass to POJO class
		
		public Logger logger;
		
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
	}
	
	//For Create User
	@Test(priority=1)
	public void testPostUser()
	{
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	//Get User
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response=UserEndPoints.readUser(this.userPayload.getUsername()); //extract username // we refer payload by refering this keyword
		//this will gives user name of your payload
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	//update data Using payload
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		//update user requires 2 para username & payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload); //extract username
		//response.then().log().all();
		response.then().log().body().statusCode(200); //HI assertion comes with restasuured
		Assert.assertEquals(response.getStatusCode(), 200);
	
		//Checking data after update
		Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername()); //extract username
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
	}
	
	//Delete
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername()); //extract username	
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}

//1st we need to set up data using pojo
//by using faker we create the data (we are generating our own id)
//for postuser we are calling create user method by passing userpayload