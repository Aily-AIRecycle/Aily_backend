package aily.server.authEmail;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
public class AuthRequest {
    private String email;
    private String code;

    public AuthRequest() {
        Random rd = new Random();
        this.code = "" + (rd.nextInt(899999) + 100000) +"";
    }
}
