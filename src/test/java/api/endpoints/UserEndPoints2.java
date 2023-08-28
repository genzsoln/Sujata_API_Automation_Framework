package api.endpoints;

//this class is create to perform CRUD operation on the User API's.
import api.payload.User;  //for User payload
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;

public class UserEndPoints2 
{
	//get the URL from the properties file
	static ResourceBundle getURL()
	{
		//method created for getting url from properties file
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load the properties file
		return routes;
	}
	
	//create User
	public static Response createUser(User payload) //payload is nothing but request body, here we need some payload, also we need to import package for payload
	{
		//POST Method
		
		String post_url=getURL().getString("post_url"); // put the post url which is under routes.properties file
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);	//here we refering the URL from Route class
		
		return response;
	}

	//Read User
	public static Response readUser(String userName) //payload is not required here bcz we are not passing data
	{
		String get_url=getURL().getString("get_url"); // put the get url which is under routes.properties file

		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.get(get_url);	//here we refering the URL from Route class
		
		return response;
	}
	
	//Update User
	public static Response updateUser(String userName, User payload) //payload is nothing but request body, here we need some payload
	{
		String update_url=getURL().getString("update_url"); // put the post url which is under routes.properties file

		//here we need two parameter which user you want to update
		// another is payload
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(update_url);	//here we refering the URL from Route class
		
		return response;
	}
	
	//Delete User
	public static Response deleteUser(String userName) //payload is not required here bcz we are not passing data
	{
		String delete_url=getURL().getString("delete_url"); // put the post url which is under routes.properties file

		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.delete(delete_url);	//here we refering the URL from Route class
		
		return response;
	}
}
