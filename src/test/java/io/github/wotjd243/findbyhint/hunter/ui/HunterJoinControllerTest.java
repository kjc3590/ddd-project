package io.github.wotjd243.findbyhint.hunter.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HunterJoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 헌터가입() throws Exception {

        MockHttpServletRequestBuilder hunterJoin = post("/hunterJoin")
                .param("hunterId", "testId")
                .param("hunterPw", "testPw")
                .param("hunterName", "testName")
                .param("hunterPicturePath", "testPath")
                .param("hunterPictureName", "test.png")
                .param("hunterPoint", "0")
                .param("hunterBullet", "0");

        this.mockMvc.perform(hunterJoin)
                .andDo(print());
    }

    @Test
    public void 헌터체크() throws Exception {

        MockHttpServletRequestBuilder hunterCheck = get("/hunterCheck")
                .param("hunterId", "testId");

        this.mockMvc.perform(hunterCheck)
                .andDo(print());
    }

}