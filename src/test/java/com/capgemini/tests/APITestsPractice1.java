package com.capgemini.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITestsPractice1 {
    Response resp;
    JsonPath respData;

    @Test
    public void testSingleUserAvatar() {
        resp = RestAssured.get("https://reqres.in/api/users/2").then().extract().response();
        System.out.println(resp.asPrettyString());
        respData = resp.jsonPath();
        Assert.assertEquals(respData.getString("data.avatar"), "https://reqres.in/img/faces/2-image.jpg");
    }

    @Test
    public void testListUsersEmail() {
        resp = RestAssured.get("https://reqres.in/api/users?page=2").then().extract().response();
        respData = resp.jsonPath();
        String email = respData.getString("data[1].email");
        String[] actualDomain = email.split("@");
        Assert.assertEquals(actualDomain[1], "reqres.in");
    }

    @Test
    public void testSingleUserNotFound() {
        resp = RestAssured.get("https://reqres.in/api/users/13").then().extract().response();
        System.out.println(resp.asPrettyString());
        Assert.assertEquals(resp.statusCode(), 404);
    }

}
