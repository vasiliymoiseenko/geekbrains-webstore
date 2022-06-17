package ru.geekbrains.webstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void securityAccessAllowedTest() throws Exception {
    mockMvc.perform(get("/api/v1/products"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").isArray());
  }

  @Test
  public void securityAccessDeniedTest() throws Exception {
    mockMvc.perform(get("/api/v1/orders"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "Bob", roles = "ADMIN")
  public void securityCheckUserTest() throws Exception {
    mockMvc.perform(get("/api/v1/orders"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
