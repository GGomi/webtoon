package com.essri.webtoonService.webtoon.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "types")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Types {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "types", cascade = CascadeType.ALL)
    private List<Webtoons> webtoons = new ArrayList<>();

    public void addWebtoon(Webtoons toon) {
        getWebtoons().add(toon);
    }

}
