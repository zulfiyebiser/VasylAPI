package com.automation.tests.day4;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestsDay4 {
    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("ords.uri");
    }

    /**
     * Warmup!
     * Given accept type is JSON
     * When users sends a GET request to "/employees"
     * Then status code is 200
     * And Content type is application/json
     * And response time is less than 3 seconds
     */
    @Test
    @DisplayName("Verify that response time is less than 3 seconds")
    public void test1() {
        given().
                accept(ContentType.JSON).
                when().
                get("/employees").
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                time(lessThan(3L), TimeUnit.SECONDS).
                log().all(true);//payload=body, and our body has JSON format

        //.all(true) < it's like prettyPrint(), prettyPick(), but they just print body
        //log().all(true) <-- print into console all info about response: header, body, status code, schema(http), schema version (1.1)
    }

    /*{{baseUrl}}/countries?q={"country_id":"US"}

    Given accept type is JSON
    And parameters: q = {"country_id":"US"}
    When users sends a GET request to "/countries"
    Then status code is 200
    And Content type is application/json
    And country_name from payload is "United States of America"

 */
    @Test
    @DisplayName("Verify that country_name from payload is \"United States of America\"")
    public void test2() {
        given().
                accept(ContentType.JSON).
                queryParam("q", "{\"country_id\":\"US\"}").
        when().
                get("/countries").
        then().
                assertThat().
                    contentType(ContentType.JSON).
                    statusCode(200).
                    body("items[0].country_name", is("United States of America")).
                    log().all(true);
    }

    @Test
    @DisplayName("Get all links and print them")
    public void test3() {
        Response response = given().
                            accept(ContentType.JSON).
                            queryParam("q", "{\"country_id\":\"US\"}").
                            when().
                            get("/countries");

        JsonPath jsonPath = response.jsonPath();



    }

}

