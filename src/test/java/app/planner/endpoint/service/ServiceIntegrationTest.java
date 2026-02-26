package app.planner.endpoint.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import app.planner.base.BaseIntegrationTest;
import app.planner.config.TestSecurityConfig;
import app.planner.jsontestdata.ServiceTestData;

@Import(TestSecurityConfig.class)
public class ServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldReturnBadRequestForInvalidData() {
        var invalidData = ServiceTestData.invalidNewServiceData;
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTestClient
                .post()
                .uri("api/services")
                .contentType(MediaType.APPLICATION_JSON)
                .body(invalidData)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnCreatedOnValidSpotData() {
        var validData = ServiceTestData.validNewServiceData;

        restTestClient
                .post()
                .uri("api/services")
                .contentType(MediaType.APPLICATION_JSON)
                .body(validData)
                .exchange()
                .expectStatus().isCreated();
    }

}
