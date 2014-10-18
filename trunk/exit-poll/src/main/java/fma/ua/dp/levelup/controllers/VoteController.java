package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Voice;
import fma.ua.dp.levelup.services.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Admin on 05.10.2014.
 */
@Controller
public class VoteController {

    @Autowired
    VoiceService voiceService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ResponseBody
    public boolean isMadeChoise(@RequestParam("voter_id") long voterId) {
        return voiceService.isDoneChoice(voterId);
    }

    @RequestMapping(value = "/voter_page", method = RequestMethod.POST)
    public String doChoice(@RequestParam("voter_id") long voterId, @RequestParam("party_id") String partyId) {
        voiceService.addNewVoice(new Voice(voterId, partyId));
        return "/election_results";
    }

    @RequestMapping(value = "/election_results", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map showResults() {
        List voices = voiceService.getAllVoices();
        Map<String, Long> res = new TreeMap<>();
        for(int i = 0; i < voices.size(); i++) {
            Voice v = (Voice)voices.get(i);
            String partyId = v.getPartyId();
            if(res.containsKey(partyId)) {
                long partyResult = res.get(partyId);
                res.put(partyId, partyResult+1);
            } else {
                res.put(partyId, 1L);
            }
        }
        return res;
    }

    @RequestMapping(value = "/voices_count", method = RequestMethod.GET)
    public long voicesCount() {
        return voiceService.getAllVoices().size();
    }
}