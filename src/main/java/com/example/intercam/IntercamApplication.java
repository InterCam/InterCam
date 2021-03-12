package com.example.intercam;

import com.example.intercam.Repository.NoticeRepository;
import com.example.intercam.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IntercamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IntercamApplication.class, args);
	}

	@Autowired
	private NoticeRepository noticeRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("dddd");
		for(int i = 30;i<30;i++){
			noticeRepository.save(Notice.builder().title("title"+i).content("contents"+i).build());
		}
	}
}