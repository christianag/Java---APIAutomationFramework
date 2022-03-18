package jsons;

import tests.Base;

public class BundleBody extends Base {

    public static String JSON = "{\n" +
            "  \"organization_id\": \" " + ORG_ID + " \",\n" +
            "  \"bundle_id\": \" " + BUNDLE_ID + " \",\n" +
            "  \"language_code\": \"en-US\",\n" +
            "  \"diplomas\": [\n" +
            "    {\n" +
            "      \"recipient_email\": \"test_email@test.com\",\n" +
            "      \"recipient_name\": \"API Bundle Test\",\n" +
            "      \"language_code\": \"en-US\",\n" +
            "      \"issue_date\": \"2022-03-10\",\n" +
            "      \"expire_date\": \"2023-03-10\",\n" +
            "      \"access_level\": \"2FA\",\n" +
            "      \"diploma_fields\": {\n" +
            "        \"29\": \"API Internship Organization 2\",\n" +
            "        \"35\": \"API Remark\"\n" +
            "      },\n" +
            "      \"personal_identifiers\": {\n" +
            "        \"1\": \"1234567890\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
