package org.choongang.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=dev")
public class FileUploadTest {

    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file;

    @BeforeEach
    void init() {
        file = new MockMultipartFile("file", "test.png", "image/png", "abc".getBytes());
    }

    @Test
    @DisplayName("[통합테스트] 파일 업로드 API")
    void uploadTest() throws Exception {
        mockMvc.perform(multipart("/api/v1/file")
                .file(file)
                .param("gid", "testgid")
                .param("location", "testLocation"))
                .andDo(print());


    }
}
