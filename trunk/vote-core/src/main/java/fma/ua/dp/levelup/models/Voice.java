package fma.ua.dp.levelup.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Admin on 05.10.2014.
 */
@Entity(name = "voice_list")
public class Voice {
    @Id
//    @GeneratedValue
    private String id;
    @Column(name = "choice_party")
    private int partyId;

    public Voice() {
    }

    public Voice(String id, int partyId) {
        this.id = id;
        this.partyId = partyId;
    }

    public Voice(int partyId) {
        this.partyId = partyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }
}
