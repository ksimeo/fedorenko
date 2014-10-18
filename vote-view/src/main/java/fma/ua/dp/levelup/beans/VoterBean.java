package fma.ua.dp.levelup.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import fma.ua.dp.levelup.communicat.IVoteSystem;
import fma.ua.dp.levelup.communicat.VoteSystemRest;
import fma.ua.dp.levelup.models.Party;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 13.10.2014.
 */
@Component
@ManagedBean
@SessionScoped
public class VoterBean implements Serializable {

    IVoteSystem vs = new VoteSystemRest();
    private List<Party> parties = vs.getParties();
    private boolean isError;
    private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

    public String submit() {
        Party checkedParty;
        int checkCount = 0;
        for (Party party : parties) {
            if (checked.get(party.getId())) {
                checkedParty = party;
                checkCount++;
            }
        }

//        if(checkCount == null && checkCount > 1) {
//            isError = true;
//        } else {
            UserBean ub = new UserBean();
            vs.vote(ub.getEmail(), checkedParty);
            return "/election_results";
        }
    }

    private DataModel<Party> party = new ArrayDataModel<>(parties);

    public DataModel<Party> getPartyList() {
        return party;
    }
}