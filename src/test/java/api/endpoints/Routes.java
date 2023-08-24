package api.endpoints;

/*
Swagger URL -- https://petstore.swagger.io

if your working on pet module or store module then replace user than that name

create_user_POST -- https://petstore.swagger.io/v2/user
Get_user_Get -- https://petstore.swagger.io/v2/user/{username}
update_user_put -- https://petstore.swagger.io/v2/user/{username}
delete_user_delete -- https://petstore.swagger.io/v2/user/{username}
 */

// this class contains only the required URL.

public class Routes 
{
	public static String base_url="https://petstore.swagger.io/v2/user"; //base point/url
	//public static String base_url="//https://petstore3.swagger.io/"; //base point/url
	//public static String base_url="https://petstore.swagger.io"; //base point/url

	//Routes user module
//	public static String post_url=base_url+"/user";
//	public static String get_url=base_url+"/user/{username}";
//	public static String update_url=base_url+"/user/{username}";
//	public static String delete_url=base_url+"/user/{username}";


	public static String post_url=base_url+"/#/user/createUser";
	public static String get_url=base_url+"/#/user/getUserByName";
	public static String update_url=base_url+"/#/user/updateUser";
	public static String delete_url=base_url+"/#/user/deleteUser";
}
