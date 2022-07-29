package kr.human.email;

public interface MailService {
	void sendEmail();
	void sendEmail(String toAddress, String subject, String content);
}
