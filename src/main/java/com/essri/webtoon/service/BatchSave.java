package com.essri.webtoon.service;

import com.essri.webtoon.web.data.Toons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@Component
public class BatchSave {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Toons> process(List<Toons> batch) {

        int size = batch.size();
        List<Toons> savedEntities = new ArrayList<>(size);
        System.out.println("size>>>>>>>>>> " + size);
        for (Toons t : batch) {
            Toons result;
            if (t.getToon_name() == null) {
                em.persist(t);
                result = t;
            } else {
                result = em.merge(t);
            }
            log.debug("::::::::::::::>> getToon_name >>>>>>>>>>>>"+result.getToon_name()+"\n");
            savedEntities.add(result);
        }
        em.flush();
        em.clear();
        return savedEntities;
    }
}
