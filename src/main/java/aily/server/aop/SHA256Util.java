package aily.server.aop;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component("SHA256Util")
public class SHA256Util {
    public static String encData(String data) throws Exception {// 패스워드 값을 매개변수로 받음
        //MessageDigest 클래스를 이용한 암호화
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        //password 값을 바이트 배열로 바꿔서 mDigest에 넣어줌
        mDigest.update(data.getBytes());
        //SHA-256으로 변환된 데이터를 byte배열로 꺼냄
        byte[] msgStr = mDigest.digest();
        //1byte 0~255까지 사용가능하지만
        //정수는 음수까지 포함해서 -128~127 까지의 범위를 표현 가능
        //16진수로 변경 00~FF 범위로 변경할 예정
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < msgStr.length; i++) {
            byte tmpStrByte = msgStr[i];
            String tmpTxt = Integer.toString((tmpStrByte & 0xff) + 0x100, 16).substring(1);
            sb.append(tmpTxt);
        }
        return sb.toString();
    }
}
