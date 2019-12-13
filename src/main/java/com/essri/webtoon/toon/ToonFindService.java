package com.essri.webtoon.toon;

import com.essri.webtoon.database.entity.Toons;
import com.essri.webtoon.database.repository.ToonRepository;
import com.essri.webtoon.exception.ToonNotFoundException;
import com.essri.webtoon.toon.dto.ToonsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToonFindService {
    private final ToonRepository toonRepository;
    private final String[] dayList = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

    public ToonFindService(ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }

    public Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> convertList() {

        Map<Byte, String> map = new HashMap<>();
        byte[] arr = {64, 32, 16, 8, 4, 2, 1};
        byte pow = 1;

        for (String a : dayList) {
            map.put(pow, a);
            pow *= 2;
        }

        // 전체 테이블데이터를 불러와서 클라이언트로 넘겨줄 새로운 모델로 변형
        List<Toons> lists = toonRepository.findAll();

        if (lists.isEmpty()) {
            throw new ToonNotFoundException();
        }

        Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> providerMap = new HashMap<>();

        providerMap.put("NAVER", new HashMap<>());
        providerMap.put("DAUM", new HashMap<>());

        for (Toons t : lists) {
            Map<String, List<ToonsDTO.ConvertWebToonLists>> dayMap = providerMap.get(t.getToonProvider());

            byte serial = t.getSerializeDay();
            List<String> tempList = new ArrayList<>();

            for (byte num : arr) {
                if (serial >= num) {
                    String key = map.get(num);
                    tempList.add(key);
                    serial = (byte) (serial - num);
                }
            }

            String[] array = new String[tempList.size()];
            array = tempList.toArray(array);

            ToonsDTO.ConvertWebToonLists toon =
                    ToonsDTO.ConvertWebToonLists.builder()
                            .toonName(t.getToonName())
                            .serializeDay(array)
                            .toonHref(t.getToonHref())
                            .toonImgsrc(t.getToonImgsrc())
                            .toonProvider(t.getToonProvider())
                            .build();

            for (String day : tempList) {
                List<ToonsDTO.ConvertWebToonLists> innerList = new ArrayList<>();
                if (dayMap.get(day) != null) {
                    innerList = dayMap.get(day);
                }
                innerList.add(toon);
                dayMap.put(day, innerList);
            }

            providerMap.put(t.getToonProvider(), dayMap);
        }

        return providerMap;
    }
}
