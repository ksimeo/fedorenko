package fma.ua.dp.levelup.services;

import fma.ua.dp.levelup.dao.VoiceRepository;
import fma.ua.dp.levelup.models.Voice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 05.10.2014.
 */
@Service
public class VoiceService implements IVoiceService {

    @Autowired
    private VoiceRepository voiceRepository;

    @Override
    public boolean isDoneChoice(String userId) {
        return voiceRepository.exists(userId);
    }

    @Override
    public void addNewVoice(Voice voice) {
        voiceRepository.save(voice);
    }

    @Override
    public List<Voice> getAllVoices() {
        return (ArrayList)voiceRepository.findAll();
    }

    @Override
    public Voice getById(String id) {
        return voiceRepository.findOne(id);
    }

    @Override
    public void clearAll() {
        voiceRepository.deleteAll();
    }

    @Override
    public long getTotalCount() {return voiceRepository.count();}
}
