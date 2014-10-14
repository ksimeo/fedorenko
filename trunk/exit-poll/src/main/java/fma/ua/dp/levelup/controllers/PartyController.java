package fma.ua.dp.levelup.controllers;

import fma.ua.dp.levelup.models.Party;
import fma.ua.dp.levelup.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 28.09.2014.
 */
@Controller
public class PartyController {

    @Autowired
    private PartyService ps;

//    @RequestMapping(value = "/")
//    public String root() {
//        return "bulletin";
//    }

    @RequestMapping(value = "/bulletin", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map getAllParties() {
        Map toSend = new HashMap<String, String>();
        List parties = ps.getAllParties();
        for(int i=0; i < parties.size(); i++) {
            Party p = (Party)parties.get(i);
            toSend.put(p.getPartyName(), p.getPartyDescr());
        }
        return toSend;
    }

    @RequestMapping(value = "/add_new_party", method = RequestMethod.POST)
    public String addNewParty(@RequestParam("party_name") String partyName, @RequestParam("party_descr") String partyDescr) {
        ps.addParty(new Party(partyName, partyDescr));
        return "/bulletin";
    }

    @RequestMapping(value = "/get_party_by_id", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map getParty(@RequestParam("id") long id) {
       Party party = ps.getById(id);
        Map<String, String> toSend = new HashMap<String, String>();
        toSend.put("name", party.getPartyName());
        toSend.put("descr", party.getPartyDescr());
        return toSend;
    }

    @RequestMapping(value = "/clear_all", method = RequestMethod.DELETE)
    public String clearAll() {
        ps.dropAll();
        return "/bulletin";
    }
//    @RequestMapping(value = "/bulletin")
//    @ResponseBody
//    public String allParties(Model model) {
//        model.addAttribute("bulletin", partyService.getAllParties());
//        return "bulletin.jsp";
//    }

//    @RequestMapping(value = "/add_party", method = RequestMethod.POST)
//    public String addParty() {
//        return "add-party.jsp";
//    }

//    @ModelAttribute("party")
//    public Party addNewParty() {
//        return new Party();
//    }

//    @RequestMapping(value = "/bulletin", method = RequestMethod.POST)
//    @ResponseBody
//    public String addParty(@ModelAttribute("party") Party party) {
//        partyService.addParty(party);
//        return "redirect:bulletin";
//    }

//    @RequestMapping(value = "/bulletin/{id}", method = RequestMethod.GET)
//    public String getParty(@PathVariable("id") long id, Model model) {
//        model.addAttribute("party", partyService.getById(id));
//        return "/party.jsp";
//    }


//    @RequestMapping(value = "/delete-all-parties", method = RequestMethod.DELETE)
//    public String deleteAllParties() {
//
//        partyService.dropAll();
//        return "bulletin";
//    }

//    @RequestMapping(value = "/get-by-id/{id}", method = RequestMethod.GET, headers={"content-type=application/json"})
//    @ResponseBody
//    public Party getParty(@PathVariable("id") long id) {
//
//        return partyService.getById(id);
//    }
}