package aily.server.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendMail(String to, String subject, String body) throws Exception {
//
        String html1 = "<html>";
        String html2 = "<body style='font-family: Arial, sans-serif; font-size: 16px; color: #333; line-height: 1.5; background-color: #f6f6f6;'>";
        String html3 = "<div style='max-width: 600px; margin: 0 auto; padding: 24px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 16px rgba(0, 0, 0, 0.1);'>";
        String html4 = "<h1 style='font-size: 28px; font-weight: bold; margin-top: 0; margin-bottom: 24px; text-align: center;'>이메일 인증</h1>";
        String html5 = "<p style='margin-top: 0; margin-bottom: 24px; text-align: center;'>안녕하세요 Aily 입니다.</p>";
        String html6 = "<p style='margin-top: 0; margin-bottom: 24px; text-align: center;'>아래 인증번호 4자리를 진행 중인 화면에 입력하고 인증을 완료해주세요.</p>";
        String html7 = "<div style='font-size: 56px; font-weight: bold; color: #f8b195; text-align: center; margin-bottom: 32px;'>";
        String html8 = body;
        String html9 = "</div>";
        String html10 = "</div>";
        String html11 = "</body>";
        String html12 = "</html>";
        String htmlMail = html1 + html2 + html3 + html4 + html5 + html6 + html7 + html8 + html9 + html10 + html11 + html12;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("ailyDMU@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText("", htmlMail);


        javaMailSender.send(mimeMessage);
    }
}