package app.planner.endpoint.servicetype;

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
public class ServiceTypeTest extends BaseIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void shouldReturnBadRequestForInvalidData() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTestClient
                .get()
                .uri("api/service-types/all")
                .exchange()
                .expectStatus().isOk();
    }

    

}
