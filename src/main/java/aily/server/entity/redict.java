package aily.server.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "dict")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class redict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "name",length = 300)
    private String title;

    @Column(name = "imgfile", length = 100)
    private String imgfile;

    @Column(name = "content", length = 2000)
    private String content;


}