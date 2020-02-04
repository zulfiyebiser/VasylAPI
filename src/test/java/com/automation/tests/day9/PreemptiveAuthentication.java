package com.automation.tests.day9;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PreemptiveAuthentication {
    @BeforeAll
    public static void setup() {
        //https will not work, because of SSL certificate issues
        //this website doesn't have it
        baseURI = "http://practice.cybertekschool.com";
    }

    @Test
    @DisplayName("Normal basic authentication")
    public void test1(){
        given().
                auth().basic("admin", "admin").
                when().
                get("/basic_auth").prettyPeek().
                then().assertThat().statusCode(200);
    }
}
