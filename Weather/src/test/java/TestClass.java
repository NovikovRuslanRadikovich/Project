import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rus.controllers.MainController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ruslan on 18.10.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
public class TestClass {



    @Autowired
    MainController mainController;


    private HttpServletRequest request;


    private MockMvc mockMvc;


    @Before
    public void start(){
        request = new MockHttpServletRequest();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
    }

    @Test
    public void sanity1() throws Exception {
        Assert.assertEquals("main",mainController.findCity("Kazan",request));
    }

    @Test
    public void sanity2() throws Exception{
        this.mockMvc.perform(get("/")).andExpect(redirectedUrl("/home"));
    }

    @Test
    public void sanity3() throws Exception{
        this.mockMvc.perform(post("/findCity")).andExpect(content().string(""));
    }

    @Test
    public void sanity4() throws Exception{
        this.mockMvc.perform(get("/home")).andExpect(status().isNotFound());
    }

    @Test
    public void sanity5() throws Exception{
        this.mockMvc.perform(post("/findCity")).andExpect(request().attribute("test",(Object) null));
    }

}
