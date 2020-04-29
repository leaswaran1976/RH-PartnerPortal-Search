package com.redhat.partnerportal.rest.json;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class RefreshEntityResourceTest {
	@Test
	public void testRefreshEndpoint() {
		given().when().get("/refresh").then().statusCode(200);
	}
}
