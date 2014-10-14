package fma.ua.dp.levelup.services;

import fma.ua.dp.levelup.models.Party;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 28.09.2014.
 */
public interface IPartyService {

    void addParty(Party party);
    Party getById(Long id);
    List<Party> getAllParties();
    Long getLastId();
    void deleteParty(Party party);
    void dropAll();
}