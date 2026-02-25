package app.planner.jsontestdata;

public class ServiceTestData {

    public static String invalidNewServiceData = """
            {
              "ownerId": "550e8400-e29b-41d4-a716-446655440000",
              "serviceTypeId": 1024,
              "startPrice": 50.00,
              "endPrice": 250.00,
              "properties": {
                "includesDisposal": true,
                "equipmentType": "industrial",
                "warrantyMonths": 12
              },
              "latitude": 43.0035,
              "longitude": 17.5501,
              "email": "contact@example.com",
              "phoneNumber": "+385 20 123 4567",
              "streetAddress": "Ulica kralja Tomislava 1, 20355 Opuzen"
            }

                        """;

    public static String validNewServiceData = """
            {
              "ownerId": "550e8400-e29b-41d4-a716-446655440000",
              "serviceTypeId": 1024,
              "title": "Professional Landscaping Service",
              "startPrice": 50.00,
              "endPrice": 250.00,
              "properties": {
                "includesDisposal": true,
                "equipmentType": "industrial",
                "warrantyMonths": 12
              },
              "latitude": 43.0035,
              "longitude": 17.5501,
              "email": "contact@example.com",
              "phoneNumber": "+385 20 123 4567",
              "streetAddress": "Ulica kralja Tomislava 1, 20355 Opuzen"
            }

                        """;

}
