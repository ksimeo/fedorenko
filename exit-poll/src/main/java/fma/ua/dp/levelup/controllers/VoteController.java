package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Party;
import fma.ua.dp.levelup.models.Voice;
import fma.ua.dp.levelup.services.PartyService;
import fma.ua.dp.levelup.services.VoiceService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Admin on 05.10.2014.
 */
@Controller
public class VoteController {

    @Autowired
    VoiceService voiceService;

    @Autowired
    PartyService partyService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public boolean isMadeChoise(@RequestParam("voter_id") String voterId) {

        return voiceService.isDoneChoice(voterId);
    }

    @RequestMapping(value = "/voter_page", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
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
    public Map showResults() {

        List<Voice> voices = voiceService.getAllVoices();
        List<Party> parties = partyService.getAllParties();
        Iterator<Voice> iter = voices.iterator();
        Map<String, Long> voicesCount = new HashMap<>();
        Voice tmp;
        while (iter.hasNext()) {
            tmp = iter.next();
            int curPartyId = tmp.getPartyId();
            Party currentParty = parties.get(curPartyId);
            String curPartyName = currentParty.getPartyName();
            if (voicesCount.containsKey(curPartyName)) {
                Long currCount = voicesCount.get(curPartyName);
                voicesCount.put(curPartyName, currCount + 1L);
            } else {
                voicesCount.put(curPartyName, 1L);
            }
        }
        Iterator<Entry<String, Long>> it = voicesCount.entrySet().iterator();
        Entry<String, Long> temp;
        Map<Integer, String> partyRating = new TreeMap();
        while (iter.hasNext()) {
            temp = it.next();
            Long total = voiceService.getTotalCount();
            int part = (int) ((temp.getValue())%(total)*100);
            partyRating.put(part, temp.getKey());
        }
        return partyRating;
    }

    @RequestMapping(value = "/voices_count", method = RequestMethod.GET)
    public long voicesCount() {
        return 0;
    }
}