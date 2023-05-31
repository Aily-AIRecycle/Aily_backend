package aily.server.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class testboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title",length = 300)
    private String title;

    @Column(name = "content", length = 2000)
    private String content;


}