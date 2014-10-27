package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Party;
import fma.ua.dp.levelup.models.Voice;
import fma.ua.dp.levelup.services.IVoiceService;
import fma.ua.dp.levelup.services.VoiceService;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Admin on 05.10.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:exit-poll/src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class VoteControllerTest {

    @InjectMocks
    private VoteController vc;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    IVoiceService vs;

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//        vs = wac.getBean(VoiceService.class);
        vs.addNewVoice(new Voice("ksimeo@gmail.com", 1));
        vs.addNewVoice(new Voice("maksym.fedorenko@gmail.com", 2));
//        vs.addNewVoice(new Voice("ximeo@mail.ru", 3));
    }

    @Test
    public void testCheckChoice() throws Exception {
        MvcResult res = this.mockMvc.perform(post("/vote").param("voter_id", "ksimeo@gmail.com"))
                .andExpect(status().isOk())
                .andReturn();
        boolean data = Boolean.parseBoolean(res.getResponse().getContentAsString());
        assertEquals(data, true);

        MvcResult res1 = this.mockMvc.perform(post("/vote").param("voter_id", "office@mdc-design.com"))
                .andExpect(status().isOk())
                .andReturn();
        boolean data1 = Boolean.parseBoolean(res1.getResponse().getContentAsString());
        assertEquals(data1, false);
    }

    @Test
    public void testDoChoice() throws Exception {
        Voice voice1 = new Voice("ximeo@mail.ru", 3);
        ObjectMapper om = new ObjectMapper();
        String data = om.writeValueAsString(voice1);
        MvcResult res = this.mockMvc.perform(post("/voter_page").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("/election_results")).andReturn();
    }

    @Test
    public void testShowResults() throws Exception {
        MvcResult res = this.mockMvc.perform(get("/election_results"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().
                constructCollectionType(List.class, Voice.class);
        List<Voice> voicesList = om.readValue(data, type);
        assertEquals(false, voicesList.isEmpty());
    }

    @After
    public void completion() throws Exception {
        vs.clearAll();
    }
}