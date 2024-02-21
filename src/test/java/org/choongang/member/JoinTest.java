package org.choongang.member;

import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.repositories.MemberRepository;
import org.choongang.member.service.MemberSaveService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=test")
public class JoinTest {

    @Autowired
    private MemberSaveService saveService;

    @Autowired
    private MemberRepository repository;

    @Test
    @DisplayName("회원가입 기능 테스트")
    void saveServiceTest() {
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setName("사용자01");
        form.setPassword("_aA123456");

        saveService.join(form);

        Member member = repository.findByEmail(form.getEmail()).orElse(null);
        System.out.println(member);
    }
}
