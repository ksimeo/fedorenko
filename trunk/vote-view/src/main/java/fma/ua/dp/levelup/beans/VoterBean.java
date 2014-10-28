package fma.ua.dp.levelup.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import fma.ua.dp.levelup.communicat.IVoteSystem;
import fma.ua.dp.levelup.communicat.VoteSystemRest;
import fma.ua.dp.levelup.models.Party;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Admin on 13.10.2014.
 */
@ManagedBean
@ViewScoped
public class VoterBean {

    IVoteSystem vs = new VoteSystemRest();
    private List<SelectItem> parties;
    private int partySelected;

    public VoterBean() {
        try {
            this.parties = new ArrayList<>();
            List<Party> all = vs.getParties();
            Iterator<Party> iter = all.iterator();
            Party tmp;
            while (iter.hasNext()) {
                tmp = iter.next();
                parties.add(new SelectItem(tmp.getId(), tmp.getPartyName(), tmp.getPartyDescr()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String submit() throws Exception {
        UserBean ub = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userBean");
        vs.vote(ub.getEmail(), partySelected);
        return "current_results";
    }

    public List<SelectItem> getParties() {
        return parties;
    }

    public int getPartySelected() {
        return partySelected;
    }

    public void setPartySelected(int partySelected) {
        this.partySelected = partySelected;
    }
}