package fma.ua.dp.levelup.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import fma.ua.dp.levelup.communication.IVoteSystem;
import fma.ua.dp.levelup.communication.VoteSystem;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 12.10.2014.
 */
@ManagedBean
@SessionScoped
public class VoterBean {
    private String email;

    private IVoteSystem vs = new VoteSystem();

    public String doIdentificate(String email) throws Exception{

        String isDoneChoise = vs.sendPost("http://localhost:8080/vote", email);

       if(isDoneChoise == "true") {

           return "http://localhost:8080//election_results";

       } else {

           return "http://localhost:8080///voter_page";
       }
    }
}
