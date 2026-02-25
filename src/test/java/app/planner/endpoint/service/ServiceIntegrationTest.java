package app.planner.endpoint.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import app.planner.base.BaseIntegrationTest;
import app.planner.config.TestSecurityConfig;
import app.planner.jsontestdata.ServiceTestData;

@Import(TestSecurityConfig.class)
public class ServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
        void shouldReturnBadRequestForInvalidData() {
                var invalidData = ServiceTestData.invalidNewServiceData;
                var headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<Void> createResponse = restTemplate.exchange(
                                "/api/services",
                                HttpMethod.POST,
                                new HttpEntity<>(invalidData, headers),
                                Void.class);

                assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void shouldReturnCreatedOnValidSpotData() {
                var validData = ServiceTestData.validNewServiceData;
                var headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<Void> createResponse = restTemplate.exchange(
                                "/api/services",
                                HttpMethod.POST,
                                new HttpEntity<>(validData, headers),
                                Void.class);

                assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }

}
