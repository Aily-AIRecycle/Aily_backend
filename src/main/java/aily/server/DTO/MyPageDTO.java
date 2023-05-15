package aily.server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyPageDTO {
    private String phonenumber;
    private String nickname;
    private int point;
    private String profile;
}
