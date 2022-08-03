package kr.human.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail() {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				// 보낼 메일의 양식을 만든다.
				helper.setFrom("hyunwoungkim@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				helper.setTo("ithuman202204@gmail.com");
				helper.setSubject("나는 제목입니다.");
				helper.setText("<html><body><p>나는 내용!!!!</p><img src='cid:company-logo'></body></html>", true);
				// 첨부파일을 메일의 내용에 이름을 company-logo로 바꿔서 붙여 준다.
				helper.addInline("company-logo", new ClassPathResource("linux-icon.png"));
			}
		};
		try {
			mailSender.send(preparator);
			System.out.println("메일 발송 성공!!!");
		}catch (Exception e) {
			System.err.print("에러 : " + e.getMessage());
		}
	}

	@Override
	public void sendEmail(String toAddress, String subject, String content) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				// 보낼 메일의 양식을 만든다.
				helper.setFrom("hyunwoungkim@naver.com"); // XML 인증 계정과 반드시 같아야 한다.
				helper.setTo(toAddress);
				helper.setSubject(subject);
				helper.setText(content);
				
				// 파일을 첨부 파일로 추가한다.
				helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
			}
		};
		try {
			mailSender.send(preparator);
			System.out.println("메일 발송 성공!!!");
		}catch (Exception e) {
			System.err.print("에러 : " + e.getMessage());
		}
		
	}
}
