package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Voice;
import fma.ua.dp.levelup.services.VoiceService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 05.10.2014.
 */
@Controller
public class VoteController {

    @Autowired
    VoiceService voiceService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean isMadeChoise(@RequestParam("voter_id") String voterId) {

        return voiceService.isDoneChoice(voterId);
    }

    @RequestMapping(value = "/voter_page", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
    @ResponseBody
    public boolean doChoice(@RequestBody String jsonData) {
        try {
            ObjectMapper om = new ObjectMapper();
            Voice newVoice = om.readValue(jsonData, Voice.class);
            voiceService.addNewVoice(newVoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/election_results", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List showResults() {
        return voiceService.getAllVoices();
    }

    @RequestMapping(value = "/voices_count", method = RequestMethod.GET)
    public long voicesCount() {
        return 0;
    }
}