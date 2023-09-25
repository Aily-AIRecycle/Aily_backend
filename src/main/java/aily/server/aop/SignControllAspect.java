package aily.server.aop;

import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SignControllAspect {

    @Before("execution(* aily.server.service.*Service.findpassworduser(..))")
    public void finduserpassword(JoinPoint jp) throws Exception {
        //System.out.println("aop실행됨");
        Object[] args= jp.getArgs();
        //System.out.println("jp = " + jp.getArgs());
        User m= (User)args[0];// Member의 주소가 들어감
        String password=m.getPassword();
        //System.out.println("암호화 전 PW:"+password);
        String encPw = SHA256Util.encData(password);
        //System.out.println("암호화 된 PW:"+encPw);
        m.setPassword(encPw);
    }


    @Before("execution(* aily.server.service.*Service.deleteuser(..))")
    public void beforedeleteuser(JoinPoint jp) throws Exception {
        //System.out.println("aop실행됨");
        Object[] args= jp.getArgs();
        //System.out.println("jp = " + jp.getArgs());
        User m= (User)args[0];// Member의 주소가 들어감
        String password=m.getPassword();
        //System.out.println("암호화 전 PW:"+password);
        String encPw = SHA256Util.encData(password);
        //System.out.println("암호화 된 PW:"+encPw);
        m.setPassword(encPw);
    }



    @Before("execution(* aily.server.service.*Service.signUp(..))")
    public void beforeAdvice(JoinPoint jp) throws Exception {
        //System.out.println("aop실행됨");
        Object[] args= jp.getArgs();
        //System.out.println("jp = " + jp.getArgs());
        User m= (User)args[0];// Member의 주소가 들어감
        String password=m.getPassword();
        //System.out.println("암호화 전 PW:"+password);
        String encPw = SHA256Util.encData(password);
        //System.out.println("암호화 된 PW:"+encPw);
        m.setPassword(encPw);
    }

    @Before("execution(* aily.server.service.*Service.signIn(..))")
    public void beforeAdviceLogin(JoinPoint jp) throws Exception {
        Object[] args= jp.getArgs();
        UserDTO m= (UserDTO)args[0];
        String password=m.getPassword();
        String encPw = SHA256Util.encData(password);
        m.setPassword(encPw);
    }
    @Before("execution(* aily.server.service.*Service.changPwd(..))")
    public void beforeAdviceChangPwd(JoinPoint jp) throws Exception {
        Object[] args= jp.getArgs();
        UserDTO m= (UserDTO)args[0];
        String password=m.getPassword();
        String encPw = SHA256Util.encData(password);
        m.setPassword(encPw);
    }
}