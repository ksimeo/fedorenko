package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Party;
import fma.ua.dp.levelup.services.IPartyService;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Admin on 28.09.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:exit-poll/src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class PartyControllerTest {

    @InjectMocks
    private PartyController pc;

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private IPartyService ps;

    private Party party;

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        party = ps.addParty(new Party("Labor", "It is a centre-left political party in the United Kingdom. It grew out of" +
                " the trade union movement and socialist political parties of the nineteenth century and has been " +
                "described as a broad church; the party contains a diversity of ideological trends from strongly " +
                "socialist, to more moderately social democratic."));
        ps.addParty(new Party("Conservators", "It is colloquially referred to as the Tory Party or the Tories, " +
                "is a centre-right political party in the United Kingdom that states that it espouses the philosophies" +
                " of conservatism and British unionism. After merging with the Liberal Unionist. Party in 1912, " +
                "it changed its name to the Conservative and Unionist Party, although that name is rarely used."));

    }

    @Test
    public void testGetAllParties() throws Exception {
        MvcResult res = this.mockMvc.perform(get("/bulletin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().
                constructCollectionType(List.class, Party.class);
        List<Party> resList = om.readValue(data, type);
        assertEquals(false, resList.isEmpty());
    }

    @Test
    public void testAddNewParty() throws Exception {
        Party party1 = new Party("Liberal", "It is a United Kingdom political party formed in 1989 by" +
                " a group of individuals within the original Liberal Party that opposed its merger with" +
                " the Social Democratic Party to form the Liberal Democrats.");
        ObjectMapper om = new ObjectMapper();
        String data = om.writeValueAsString(party1);
        this.mockMvc.perform(post("/add_new_party").content(data).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("/bulletin"));
    }

    @Test
    public void testGetParty() throws Exception {
        MvcResult res = this.mockMvc.perform(get("/get_party_by_id").param("id", String.valueOf(party.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        String data = res.getResponse().getContentAsString();
        ObjectMapper om = new ObjectMapper();
        Party p = om.readValue(data, Party.class);
        Assert.assertEquals(p, party);
    }

    @Test
    public void testClearAll() throws Exception {
        mockMvc.perform(delete("/clear_all"))
                .andExpect(status().isOk()).andExpect(view().name("/bulletin"));
    }

    @After
    public void completion() {
        ps.dropAll();
    }
}