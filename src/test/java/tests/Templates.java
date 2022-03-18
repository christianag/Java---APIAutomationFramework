package tests;

import io.restassured.response.Response;
import jsons.TemplatesBody;
import jsons.TemplatesEdit;
import jsons.TemplatesMultilang;
import jsons.TemplatesMultilangEdit;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class Templates extends Base {

    public static String template_id;
    public static String multilang_template_id;

    @Test (description = "GET /templates")
    void getTemplates() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when()
                        .get("/templates")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ArrayList list = response.jsonPath().get("templates");
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals((String) response.jsonPath().get("templates[" + i + "].organization_id"), ORG_ID);
        }
    }

    @Test (description = "POST /templates")
    void postTemplates() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when()
                        .and()
                        .body(TemplatesBody.JSON)
                        .post("/templates")
                        .then().log().all().extract().response();

        template_id = (String) response.jsonPath().get("id");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), "ChrisTest_API");
    }

    @Test (description = "POST /templates/{template ID}", dependsOnMethods = {"postTemplates"})
    void postTemplatesEdit() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when().and()
                        .body(TemplatesEdit.JSON)
                        .post("/templates/" + template_id)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("organization_id"), ORG_ID);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), "ChrisTest_API_edited");
    }

    @Test (description = "GET /templates/{template ID}", dependsOnMethods = {"postTemplatesEdit"})
    void getTemplateID() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when()
                        .get("/templates/" + template_id)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("organization_id"), ORG_ID);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), "ChrisTest_API_edited");
    }

    @Test (description = "GET /organizations/{organization_id}/templates")
    void getOrgTemplates() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when()
                        .get("/organizations/" + ORG_ID + "/templates")
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("templates.organization_id[2]"), ORG_ID);
        Assert.assertEquals((String) response.jsonPath().get("templates.organization_id[10]"), ORG_ID);
    }

    @Test (description = "POST /templates-multilang")
    void postMultilangTemplate() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when().and()
                        .body(TemplatesMultilang.JSON)
                        .post("/templates-multilang")
                        .then().log().all().extract().response();

        multilang_template_id = (String) response.jsonPath().get("id");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), "ChrisTest_API_multilang");
    }

    @Test (description = "POST /templates-multilang/{template_id}", dependsOnMethods = {"postMultilangTemplate"})
    void postMultilangTemplateEdit() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when().and()
                        .body(TemplatesMultilangEdit.JSON)
                        .post("/templates-multilang/" + multilang_template_id)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((String) response.jsonPath().get("name.en-US"), "ChrisTest_API_multilang_edited");
    }

    @Test (description = "POST /templates-status/{template_id}", dependsOnMethods = {"postMultilangTemplateEdit"})
    void getTemplateStatus() {
        Response response =
                given().log().all()
                        .header("Authorization", Token.ACCESS_TOKEN)
                        .header("Content-type", "application/json")
                        .when()
                        .get("/template-status/" + multilang_template_id)
                        .then().log().all().extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals((int) response.jsonPath().get("total_issued"), 0);
    }

}
