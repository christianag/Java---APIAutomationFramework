package jsons;

import tests.Base;

public class TemplatesBody extends Base {

    public static String JSON = "{\n" +
            "  \"extra_name\": \"ChrisTest_API\",\n" +
            "  \"organization_id\": \" "+ ORG_ID + " \",\n" +
            "  \"default_language\": \"en-US\",\n" +
            "  \"name\": {\n" +
            "    \"da-DK\": \"ChrisTest_API\",\n" +
            "    \"en-US\": \"ChrisTest_API\"\n" +
            "  },\n" +
            "  \"type\": \"Diploma\",\n" +
            "  \"external_link\": \"http://example.com\",\n" +
            "  \"badge_id\": \"48\",\n" +
            "  \"email_template_id\": 9,\n" +
            "  \"custom_design_id\": \"133\",\n" +
            "  \"regular_design_id\": \"155\",\n" +
            "  \"description\": {\n" +
            "    \"da-DK\": \"Dansk Description\",\n" +
            "    \"en-US\": \"English Description\"\n" +
            "  },\n" +
            "  \"blockcerts\": 0,\n" +
            "  \"template_fields\": {\n" +
            "    \"1575\": \"API DTU Pilot Transcript English\"\n" +
            "  },\n" +
            "  \"personal_identifier\": 1,\n" +
            "  \"presentation_view\": \"1\"\n" +
            "}";
}