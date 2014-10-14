package fma.ua.dp.levelup.services;

import fma.ua.dp.levelup.dao.PartyRepository;
import fma.ua.dp.levelup.models.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 28.09.2014.
 */
@Service
public class PartyService implements IPartyService {
    @Autowired
    private PartyRepository partyRepository;

    @Override
    public void addParty(Party party) {
        partyRepository.save(party);
    }

    @Override
    public Party getById(Long id) {
        return partyRepository.findOne(id);
    }

    @Override
    public List<Party> getAllParties() {
        return (ArrayList)partyRepository.findAll();
    }

    @Override
    public Long getLastId() {
        return partyRepository.count();
    }

    @Override
    public void deleteParty(Party party) {
        partyRepository.delete(party);
    }

    @Override
    public void dropAll() {
        partyRepository.deleteAll();
    }
}
