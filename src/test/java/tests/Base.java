package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class Base {

    public static String ORG_NAME = "Organization Name";
    public static String ORG_ID = "123";
    public static String BASE_URL = "http://test.com/api/v1";
    public static String TEMPLATE_ID = "123";
    public static String BUNDLE_ID = "123";

    @BeforeMethod
    void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

}
