package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Voice;
import fma.ua.dp.levelup.services.IVoiceService;
import fma.ua.dp.levelup.services.VoiceService;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static junit.framework.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by Admin on 05.10.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class VoteControllerTest {

    @InjectMocks
    private VoteController vc;

    @Autowired
    private WebApplicationContext wac;

//    @Autowired
    IVoiceService vs;

    private MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(vc).build();
        vs = wac.getBean(VoiceService.class);
        vs.addNewVoice(new Voice(1, 1));
        vs.addNewVoice(new Voice(2, 2));
        vs.addNewVoice(new Voice(3, 1));
        vs.addNewVoice(new Voice(5, 3));
    }

    @Test
    public void testCheckChoice() throws Exception {
        this.mockMvc.perform(get("/vote").param("voter_id", "1L"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        this.mockMvc.perform(get("/vote").param("vote_id", "4L"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testDoChoice() throws Exception {
        this.mockMvc.perform(get("/voter_page").param("voter_id", "4L").param("party_id", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("elections_results"));
        Voice voice = vs.getById(4);
        assertEquals(voice.getId(), 4L);
        assertEquals(voice.getPartyId(), 3);
    }

    @Test
    public void testShowResults() throws Exception {
        this.mockMvc.perform(get("/election_results"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        MvcResult res = this.mockMvc.perform(get("/election_results")).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        HashMap map = om.readValue(data, HashMap.class);
        Object result1 = map.get(1);
        Object result2 = map.get(2);
        Object result3 = map.get(3);
        assertEquals(result1, 2L);
        assertEquals(result2, 1L);
        assertEquals(result3, 2L);
    }

    @After
    public void completion() throws Exception {
        vs.clearAll();
    }
}

