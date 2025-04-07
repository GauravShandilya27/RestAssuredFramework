package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//this class ois created for getting url from Properties file
//it created for perform CRUD operation
//it will be different class for different module like this is userendpoints .....same package if we want to create different model for pet it will be petendpoints.java

public class UserEndpoints2 {

	//method created for getting URL's from properties file
	static ResourceBundle getURL(){
		
		ResourceBundle rb=ResourceBundle.getBundle("routes");
		return rb;
	}
	
	
	public static Response createUser(User payload){
		
		String post_url=getURL().getString("post_url");
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(post_url);
		
		return response;
	}
	
public static Response readUser(String userName){
		
	String get_url=getURL().getString("get_url");
	
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(get_url);
		
		return response;
	}
public static Response updateUser(String userName,User payload){
	String put_url=getURL().getString("update_url");
	
	Response response=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", userName)
	.body(payload)
	
	.when()
	.put(put_url);
	
	return response;
}
public static Response deleteUser(String userName){
	String delete_url=getURL().getString("delete_urls");
	
	Response response=given()
	.pathParam("username", userName)
	
	.when()
	.delete(delete_url);
	
	return response;
}


}
