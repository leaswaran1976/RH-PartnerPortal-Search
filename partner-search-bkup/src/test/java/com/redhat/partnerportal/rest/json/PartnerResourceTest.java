package com.redhat.partnerportal.rest.json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PartnerResourceTest {

	@Test
	public void testHelloEndpoint() {
		given().when().get("/partners").then().statusCode(200).body(containsString("Accenture"));
	}

}