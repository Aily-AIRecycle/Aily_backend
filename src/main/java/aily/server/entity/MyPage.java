package aily.server.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class MyPage {
    @Id
    private String nickname;
    @Column(name = "point")
    @ColumnDefault("0")
    private int point;
    @Column(name = "profile")
    private String profile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phonenumber", referencedColumnName = "phone_number")
    private User user;
}