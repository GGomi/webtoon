package com.essri.webtoonService.user.domain;

import com.essri.webtoonService.favorite.domain.Favorites;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @CreationTimestamp
    @Column(name = "created_dt")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_dt")
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Favorites> favorites = new ArrayList<>();

    @Builder
    public Users(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public void addFavorite(Favorites favorite) {
        getFavorites().add(favorite);
    }
}
