package api.endpoints;

//this class is create to perform CRUD operation on the User API's.
import api.payload.User;  //for User payload
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//import org.testng.annotations.Test;
//import static org.hamcrest.Matchers.*;
//import java.util.HashMap;
//import static io.restassured.matcher.RestAssuredMatchers.*;

public class UserEndPoints 
{
	//create User
	public static Response createUser(User payload) //payload is nothing but request body, here we need some payload, also we need to import package for payload
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);	//here we refering the URL from Route class
		
		return response;
	}

	//Read User
	public static Response readUser(String userName) //payload is not required here bcz we are not passing data
	{
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.get(Routes.get_url);	//here we refering the URL from Route class
		
		return response;
	}
	
	//Update User
	public static Response updateUser(String userName, User payload) //payload is nothing but request body, here we need some payload
	{
		//here we need two parameter which user you want to update
		// another is payload
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(Routes.update_url);	//here we refering the URL from Route class
		
		return response;
	}
	
	//Delete User
	public static Response deleteUser(String userName) //payload is not required here bcz we are not passing data
	{
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.delete(Routes.delete_url);	//here we refering the URL from Route class
		
		return response;
	}
}
