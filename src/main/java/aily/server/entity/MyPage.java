package aily.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "my_page")
public class MyPage {
    @Id
    private String nickname;

    @Column(name = "point")
    @ColumnDefault("0")
    private int point;

    @Column(name = "profile")
    @ColumnDefault("https://aliy.store/member/image/default.png")
    private String profile;

    @ColumnDefault("0")
    private int PET;

    @ColumnDefault("0")
    private int GEN;

    @ColumnDefault("0")
    private int CAN;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "phonenumber", referencedColumnName = "phone_number")
    private User user;
}