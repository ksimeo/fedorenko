package fma.ua.dp.levelup.dao;

import fma.ua.dp.levelup.models.Voice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 05.10.2014.
 */
@Repository
public interface VoiceRepository extends CrudRepository<Voice, String> {

}
