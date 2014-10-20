package fma.ua.dp.levelup.dao;

import fma.ua.dp.levelup.models.Party;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 28.09.2014.
 */
@Repository
public interface PartyRepository extends CrudRepository<Party, Integer> {

}
