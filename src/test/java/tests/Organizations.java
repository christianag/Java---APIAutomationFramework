package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Organizations extends Base{

    @Test (description = "GET /organizations")
    void getOrgs() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ArrayList list = response.jsonPath().get("organizations");
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals((int) response.jsonPath().get("pagination.total"), 1);
        Assert.assertEquals((int) response.jsonPath().get("pagination.per_page"), 50);
        Assert.assertEquals(response.jsonPath().getString("organizations.name[0].en-US"), ORG_NAME);
    }

    @Test (description = "GET /organizations/{org_id}")
    void getOrgId() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations/" + ORG_ID)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("id"), ORG_ID);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), ORG_NAME);
    }

    @Test (description = "GET /template-badges")
    void getTemplateBadges() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations/" + ORG_ID + "/template-badges")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.jsonPath().getString("template_badges.name[0]"), ORG_NAME);
    }

    @Test (description = "GET /template-designs")
    void getTemplateDesigns() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations/" + ORG_ID + "/template-designs")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((int) response.jsonPath().get("pagination.per_page"), 50);
        Assert.assertEquals((int) response.jsonPath().get("pagination.current_page"), 1);
    }

    @Test (description = "GET /template-fields")
    void getTemplateFields() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations/" + ORG_ID + "/template-fields")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ArrayList list = response.jsonPath().get("template_fields");
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals((int) response.jsonPath().get("pagination.per_page"), 50);
        Assert.assertEquals((int) response.jsonPath().get("pagination.current_page"), 1);
    }

    @Test (description = "GET /diploma-fields")
    void getDiplomaFields() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .contentType(ContentType.JSON)
                        .get("/organizations/" + ORG_ID + "/diploma-fields")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ArrayList list = response.jsonPath().get("diploma_fields");
        Assert.assertTrue(list.size() > 0);
        Assert.assertEquals((int) response.jsonPath().get("pagination.per_page"), 50);
        Assert.assertEquals((int) response.jsonPath().get("pagination.current_page"), 1);
    }

}
