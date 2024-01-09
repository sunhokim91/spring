package com.study.oas.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(
        properties = { "spring.profiles.active=h2" },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class SpringBootH2Test {
    protected WebTestClient webTestClient;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        webTestClient = MockMvcWebTestClient.bindToApplicationContext(context)  // 서블릿 컨테이너 바인딩
                .configureClient()                                              // 설정 추가
                .filter(documentationConfiguration(restDocumentation))          // epages 문서 설정을 추가
                .build();
    }
}
