package fma.ua.dp.levelup.models;


import javax.persistence.*;

/**
 * Created by Admin on 28.09.2014.
 */
@Entity (name = "party_list")
public class Party {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String partyName;
    @Lob
    private String partyDescr;

    public Party() {
    }

    public Party(String partyName, String partyDescr) {
        this.partyName = partyName;
        this.partyDescr = partyDescr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyDescr() {
        return partyDescr;
    }

    public void setPartyDescr(String partyDescr) {
        this.partyDescr = partyDescr;
    }
}
