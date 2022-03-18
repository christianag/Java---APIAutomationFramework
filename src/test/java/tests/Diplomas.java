package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsons.BundleBody;
import jsons.DiplomasBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class Diplomas extends Base {

    public static String diploma_id;

    @Test (description = "POST /diplomas")
    public void postDiploma() {
        Response response =
            given().log().all()
                    .header("Authorization", Token.ACCESS_TOKEN)
                    .header("Accept", ContentType.JSON)
                    .when().and()
                    .body(DiplomasBody.JSON)
                    .post("/diplomas/" + diploma_id)
                    .then().log().all().extract().response();

        diploma_id = (String) response.jsonPath().get("issued.data[0].diploma.id");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((int) response.jsonPath().get("countIssued"), 1);
        Assert.assertEquals((String) response.jsonPath().get("issued.data[0].diploma.recipient_name"), "API diploma test");
    }

    @Test(description = "GET /diplomas/{diploma_id}", dependsOnMethods = {"postDiploma"})
    public void getDiplomaID() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/diplomas/" + diploma_id)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((String) response.jsonPath().get("diploma.recipient_name"), "API diploma test");
        Assert.assertEquals((String) response.jsonPath().get("template.id"), TEMPLATE_ID);
    }

    @Test (description = "GET /organizations/{organizatiom_id}/diplomas",dependsOnMethods = {"getDiplomaID"})
    public void getOrgDiplomas() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/organizations/" + ORG_ID + "/diplomas")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].diploma.recipient_name"), "API diploma test");
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].template.id"), TEMPLATE_ID);
        ArrayList list = response.jsonPath().get("diplomas");
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals((String) response.jsonPath().get("diplomas[" + i + "].organization.id"), ORG_ID);
        }
    }

    @Test(description = "GET /templates/{template_id}/diplomas", dependsOnMethods = {"getOrgDiplomas"})
    public void getTemplateDiplomas() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/templates/" + TEMPLATE_ID + "/diplomas")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].diploma.recipient_name"), "API diploma test");
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].organization.id"), ORG_ID);
        ArrayList list = response.jsonPath().get("diplomas");
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals((String) response.jsonPath().get("diplomas[" + i + "].template.id"), TEMPLATE_ID);
        }
    }

    @Test(description = "POST /diplomas/{diploma_id}/revoke", dependsOnMethods = {"getTemplateDiplomas"})
    public void getRevokeDiploma() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .get("/diplomas/" + diploma_id + "/revoke")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((String) response.jsonPath().get("success"), "Diploma successfully revoked");
    }

    @Test (description = "POST /bundles")
    public void postBundle() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Accept", ContentType.JSON)
                        .when().and()
                        .body(BundleBody.JSON)
                        .post("/bundles")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].diploma.recipient_name"), "API Bundle Test");
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].template.id"), BUNDLE_ID);
        Assert.assertEquals((String) response.jsonPath().get("diplomas[0].organization.id"), ORG_ID);
    }

}


