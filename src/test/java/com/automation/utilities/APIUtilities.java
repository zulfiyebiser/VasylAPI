package com.automation.utilities;

import com.automation.pojos.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtilities {
    /**
     * This method can POST new spartan
     * @param spartan POJO
     * @return response object
     */
    public Response postSpartan(Spartan spartan){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.uri");
        Response response = given().
                contentType(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans");
        return response;
    }

    /**
     * This method can POST new spartan
     * @param spartan as map
     * @return response object
     */
    public Response postSpartan(Map<String, ?> spartan){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.uri");
        Response response = given().
                contentType(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans");
        return response;
    }


    /**
     * This method can POST new spartan
     * @param filePath to the Spartan external JSON file
     * @return response object
     */
    public Response postSpartan(String filePath){
        File file = new File(filePath);
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.uri");
        Response response = given().
                contentType(ContentType.JSON).
                body(file).
                when().
                post("/spartans");
        return response;
    }
}
