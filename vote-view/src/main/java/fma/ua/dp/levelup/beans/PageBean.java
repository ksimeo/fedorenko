package fma.ua.dp.levelup.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fma.ua.dp.levelup.communication.IVoteSystem;
import fma.ua.dp.levelup.communication.VoteSystem;
import fma.ua.dp.levelup.models.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 13.10.2014.
 */
@ManagedBean
@SessionScoped
public class PageBean {

    private String partyChoice;

    IVoteSystem vs = new VoteSystem();

    public String allParty()throws Exception {

        return vs.sendGet("http://localhost:8080/bulletin");

    }

    public String doChoice() throws Exception {

        vs.sendPost("/voter_page", partyChoice);

        return "/result_page";
    }
}
