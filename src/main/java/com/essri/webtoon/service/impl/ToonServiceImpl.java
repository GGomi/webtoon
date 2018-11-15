/**
 * @author jminoh
 */
package com.essri.webtoon.service.impl;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.web.data.ToonInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ToonServiceImpl implements ToonService {
    /**
     * @description
     * @return
     */
    @Override
    public List<ToonInfo> getToonData() {
        List<ToonInfo>  info        = new ArrayList<>();
        ObjectMapper    mapper      = new ObjectMapper();
        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document    doc             = Jsoup.connect("https://comic.naver.com/webtoon/weekday.nhn").get();
            Elements    contents        = doc.select(".col_inner h4");
            ArrayNode  resArrNode      = mapper.createArrayNode();

            for(Element e:contents) {
                String      dayInfo         = e.className();
                Elements    a               = e.nextElementSibling().select(".thumb");

                for(Element i:a) {

                    ObjectNode  objectNode  = mapper.createObjectNode();
                    Element     aTag        = i.selectFirst("a");
                    Element     img         = aTag.selectFirst("img");
                    String      href        = aTag.attr("href");
                    String      src         = img.attr("src");
                    String      name        = img.attr("title");


                    objectNode.put("toon_name",name);
                    objectNode.put("day",dayInfo);
                    objectNode.put("toon_href",href);
                    objectNode.put("toon_imgsrc",src);

                    resArrNode.add(objectNode);
                }
//                log.debug("obj ==========>" + arrayNode.toString());

            }

            Object json = mapper.readValue(resArrNode.toString(),Object.class);
            log.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

        } catch (IOException e) {
            log.debug(e.getMessage());
        }

        return info;

    }
}
