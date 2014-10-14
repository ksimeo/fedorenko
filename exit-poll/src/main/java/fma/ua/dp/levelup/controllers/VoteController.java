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
    public String doChoice(@RequestParam("voter_id") String voterId, @RequestParam("party_id") int partyId) {
        voiceService.addNewVoice(new Voice(voterId, partyId));
        return "election_results";
    }

    @RequestMapping(value = "/election_results", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map showResults() {
        List voices = voiceService.getAllVoices();
        Map<Integer, Long> results = new HashMap<>();
        for(int i=0; i < voices.size(); i++) {
            Voice v = (Voice)voices.get(i);
            int partyId = v.getPartyId();
            if(results.containsKey(partyId)) {
                long res = results.get(partyId);
                results.put(partyId, res+1);
            } else {
                results.put(partyId, 1L);
            }
        }
        return results;
    }
}


//    @RequestMapping(value = "/vote/{id}", method = RequestMethod.GET)
////    @ResponseBody
//    public String chekingChoice(@PathVariable("id") long userId) {
//        if (voiceService.isDoneChoice(userId)) {
//            return "election_results.jsp";
//        } else {
//            return "vote_page.jsp";
//        }
//    }
//    @RequestMapping(value = "/vote_page", method = RequestMethod.POST)
//    @ResponseBody
//    public String doChoice(@RequestParam("user-id") long userId, @RequestParam("party-id") long partyId)
//    {
//        voiceService.addNewVoice(new Voice(userId, partyId));
//        return "election_results.jsp";
//    }

//    @RequestMapping(value = "election_results", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public void showResults(Model model) {
//        model.addAttribute("voices_list", voiceService.getAllVoices());
//
//    }
