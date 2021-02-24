package com.example.intercam;

import com.example.intercam.entity.FAQ;
import com.example.intercam.Repository.FAQ_repository;
import com.example.intercam.entity.Notice;
import com.example.intercam.Repository.NoticeRepository;
import com.example.intercam.mail.HtmlEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;

@EnableJpaAuditing
@SpringBootApplication
public class IntercamApplication implements CommandLineRunner {

	@Autowired
	private FAQ_repository faq_repository;

	@Autowired
	private HtmlEmailService htmlEmailService;

	@Autowired
	private NoticeRepository noticeRepository;

	public static void main(String[] args) {
		SpringApplication.run(IntercamApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		for(int i =1;i<=11;i++){
			FAQ faq = FAQ.builder()
					.title("FAQ"+i)
					.contents("FAQ 내용"+i)
					.build();

			faq_repository.save(faq);
		}

		for(int i =1;i<=100;i++){
			Notice notice1 = Notice.builder().title("공지사항"+i)
					.content("공지사항 내용"+i)
					.build();

			noticeRepository.save(notice1);
		}
	}
}