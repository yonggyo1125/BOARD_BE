package org.choongang.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.board.controllers.RequestBoardConfig;
import org.choongang.board.repositories.BoardRepository;
import org.choongang.board.service.BoardConfigSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=test")
public class SaveTest {

    @Autowired
    private BoardConfigSaveService saveService;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    private RequestBoardConfig form;

    @BeforeEach
    void init() {
        form = new RequestBoardConfig();
        form.setBid("notice");
        form.setBName("공지사항");
    }
    
    @Test
    @DisplayName("게시판 설정 저장 테스트")
    void saveServiceTest() {
        saveService.save(form);

        String bid = form.getBid();
        assertTrue(repository.existsById(bid));
    }

    @Test
    @WithMockUser
    @DisplayName("[통합테스트] 게시판 설정 저장 테스트")
    void saveTest() throws Exception {
        //form.setBid(null);
        //form.setBName(null);
        String params = om.writeValueAsString(form);

        mockMvc.perform(post("/api/v1/board/config")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
                .andDo(print())
                .andExpect(status().isCreated());

        assertTrue(repository.existsById(form.getBid()));
    }
}
