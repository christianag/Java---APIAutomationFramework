package jsons;

import tests.Base;
import tests.Organizations;
import tests.Templates;

public class DiplomasBody extends Base {

    public static String JSON = "{\n" +
            "  \"organization_id\": \" " + ORG_ID + " \",\n" +
            "  \"template_id\": \" " + TEMPLATE_ID + " \",\n" +
            "  \"diplomas\": [\n" +
            "    {\n" +
            "      \"recipient_email\": \"test_email@test.com\",\n" +
            "      \"recipient_name\": \"API diploma test\",\n" +
            "      \"language_code\": \"en-US\",\n" +
            "      \"issue_date\": \"2022-09-01\",\n" +
            "      \"expire_date\": \"2023-08-10\",\n" +
            "      \"access_level\": \"two factor authentication\",\n" +
            "      \"diploma_fields\": {\n" +
            "        \"270\": \"API dtu_d_degree_en\",\n" +
            "        \"271\": \"API dtu_table_grades_en\",\n" +
            "        \"272\": \"dtu_d_sum_belastning\"\n" +
            "      },\n" +
            "      \"personal_identifiers\": {\n" +
            "        \"1\": \"1234567890\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
