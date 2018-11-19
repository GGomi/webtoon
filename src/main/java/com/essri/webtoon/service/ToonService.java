package com.essri.webtoon.service;

import com.essri.webtoon.web.data.Toons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ToonService {
//    private final BatchSave batchSave;

    private EntityManagerFactory emf;

    private EntityManager em;

    private int batchSize = 100;
//    List<Toons> savedEntities;

    @Transactional
    public void saveData(List<Toons> info) {
//        int size = info.size();
//        savedEntities = new ArrayList<>(size);

        try {
//            for (int i = 0; i < size; i += batchSize) {
//                int toIndex = i + (((i + batchSize) < size) ? batchSize : size - i);
//                List<Toons> temp = batchSave.process(info.subList(i, toIndex));
//                System.out.println(temp.size());
//                savedEntities.addAll(temp);
//            }
            emf = Persistence.createEntityManagerFactory("persistence");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            for(Toons t : info) {
                em.persist(t);
            }
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception ign) {
            log.debug(":::::::::: batch Error :::::::::::> "+ign.getMessage());
        }
//        return savedEntities;

    }
}
