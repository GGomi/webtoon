package com.essri.webtoonService.favorite.domain;

import com.essri.webtoonService.user.domain.Users;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;

    @CreationTimestamp
    @Column(name = "created_dt")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_dt")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    private Users users;


}
