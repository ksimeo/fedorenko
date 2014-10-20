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
    private String userId;
    @Column(name = "choice_party")
    private int partyId;

    public Voice() { }

    public Voice(String userId, int partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public Voice(int partyId) {
        this.partyId = partyId;
    }

    public String getId() {
        return userId;
    }

    public void setId(String userId) {

        this.userId = userId;
    }

    public int getPartyId() {

        return partyId;
    }

    public void setPartyId(int partyId) {

        this.partyId = partyId;
    }
}
