package fma.ua.dp.levelup.services;

import fma.ua.dp.levelup.models.Voice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 05.10.2014.
 */
public interface IVoiceService {

    boolean isDoneChoice(Long userId);
    void addNewVoice(Voice voice);
    List<Voice> getAllVoices();
    Voice getById (long id);
    void clearAll();
}
