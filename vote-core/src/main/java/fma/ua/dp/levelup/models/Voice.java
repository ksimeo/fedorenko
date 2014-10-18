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
    private long userId;
    @Column(name = "choice_party")
    private String partyId;

    public Voice() { }

    public Voice(long userId, String partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public Voice(String partyId) {
        this.partyId = partyId;
    }

    public long getId() {
        return userId;
    }

    public void setId(long userId) {

        this.userId = userId;
    }

    public String getPartyId() {

        return partyId;
    }

    public void setPartyId(String partyId) {

        this.partyId = partyId;
    }
}
