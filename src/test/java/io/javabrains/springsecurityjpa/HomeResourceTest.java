package io.javabrains.springsecurityjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeResource.class)
public class HomeResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void canGetIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void cannotGetUserWhenUnauthenticated() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void canGetUserAsRegularUser() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void cannotGetAdminAsRegularUser() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void canGetAdminAsAdminUser() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().isOk());
    }
}
