package com.study.oas.member.api;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.study.oas.config.JsonConverter;
import com.study.oas.config.SpringBootH2Test;
import com.study.oas.member.domain.Member;
import com.study.oas.member.infrastructure.MariaDBMemberRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.jdbc.Sql;

import static com.epages.restdocs.apispec.WebTestClientRestDocumentationWrapper.document;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@Sql(scripts = {"/sql/member/save-member.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/member/remove-member.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MemberControllerTest extends SpringBootH2Test {
    @Autowired
    private MariaDBMemberRepository memberRepository;

    @Test
    public void 회원_정보_조회_통합_테스트() throws Exception {
        String responseJson = JsonConverter.fromJson(JsonConverter.MOCK_DATA_RESOURCE_PATH + "/member/member-find-response.json");

        webTestClient.get()
                .uri("/api/member/{seqMember}", "2")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(responseJson)
                .consumeWith(document("find-member",
                        ResourceSnippetParameters.builder()
                                .tag("member")
                                .summary("회원 정보 조회 API")
                                .description("회원 정보 조회하는 API입니다."),
                        pathParameters(
                                parameterWithName("seqMember").description("회원 고유번호")
                        ),
                        responseFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원이름").optional(),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호")
                        )
                ));

    }

    @Test
    public void 회원_정보_저장_통합_테스트() throws Exception {
        String requestJson = JsonConverter.fromJson(JsonConverter.MOCK_DATA_RESOURCE_PATH + "/member/member-save-request.json");

        webTestClient.post()
                .uri("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .consumeWith(document("save-member",
                        ResourceSnippetParameters.builder()
                                .tag("member")
                                .summary("회원 정보 저장 API")
                                .description("회원 정보 저장하는 API입니다."),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원이름").optional(),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호").optional()
                        )
                ));

        Member member = memberRepository.findMember(5L);

        assertEquals(member.getEmail(), "test05@gmail.com");
    }

    @Test
    public void 회원_정보_삭제_통합_테스트() {
        webTestClient.delete()
                .uri("/api/member/{seqMember}", 3)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("remove-member",
                        ResourceSnippetParameters.builder()
                                .tag("member")
                                .summary("회원 정보 삭제 API")
                                .description("회원 정보 삭제하는 API입니다.")
                ));

        Member member = memberRepository.findMember(3L);

        assertNull(member, "Member 값은 null이어야 합니다.");
    }

    @Test
    public void 회원_정보_수정_통합_테스트() throws Exception {
        String requestJson = JsonConverter.fromJson(JsonConverter.MOCK_DATA_RESOURCE_PATH + "/member/member-modify-request.json");

        webTestClient.put()
                .uri("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("modify-member",
                        ResourceSnippetParameters.builder()
                                .tag("member")
                                .summary("회원 정보 수정 API")
                                .description("회원 정보 수정하는 API입니다."),
                        requestFields(
                                fieldWithPath("seqMember").type(JsonFieldType.NUMBER).description("회원 고유번호"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원이름").optional(),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호").optional()
                )));

        Member member = memberRepository.findMember(4L);

        assertEquals(member.getEmail(), "test04@naver.com");
    }
}
