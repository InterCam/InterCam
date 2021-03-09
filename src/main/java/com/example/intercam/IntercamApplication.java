package com.example.intercam;

import com.example.intercam.Repository.*;
import com.example.intercam.entity.*;
import com.example.intercam.service.VideoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class IntercamApplication implements CommandLineRunner {

	@Autowired
	private FAQ_repository faq_repository;

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AnalysisRepository analysisRepository;

	@Autowired
	private VideoListRepository videoListRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private VideoListService videoListService;



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











		User u = new User();
		u.setAuth(Auth.USER);
		u.setBirth("2");
		u.setName("aaaaa");
		u.setPassword(passwordEncoder.encode("a"));
		u.setPhone("010");
		u.setUsername("11asdasd1");

		userRepository.save(u);

		Analyst a = new Analyst();
		a.setAuth(Auth.ANALYST);
		a.setBirth("0");
		a.setName("bbbbb");
		a.setPassword(passwordEncoder.encode("b"));
		a.setPhone("555");
		a.setUsername("GGGGG");
		a.setContents("iiiiiiiii");

		analysisRepository.save(a);



		Video v = Video.builder()
				.title("test")
				.url("/WEB-INF/test.mov")
				.build();

		videoRepository.save(v);

		Optional<Video> v1 = videoRepository.findById(1L);
		Optional<User> u1 = userRepository.findById(1L);

		VideoList vl1 = VideoList.builder()
				.major("aaa")
				.views(0)
				.build();

		VideoList vl2 = VideoList.builder()
				.major("aaa")
				.views(0)
				.build();

		VideoList vl3 = VideoList.builder()
				.major("aaa")
				.views(0)
				.build();

		VideoList vl4 = VideoList.builder()
				.major("aaa")
				.views(0)
				.build();

		VideoList vl5 = VideoList.builder()
				.major("aaa")
				.views(0)
				.build();

		vl1.addVideo(v);
		vl1.addUser(u1.get());

		vl2.addVideo(v1.get());
		vl2.addUser(u1.get());

		vl3.addVideo(v1.get());
		vl3.addUser(u1.get());

		vl4.addVideo(v1.get());
		vl4.addUser(u1.get());

		vl5.addVideo(v1.get());
		vl5.addUser(u1.get());

		videoListService.save(vl1);
		videoListService.save(vl2);
		videoListService.save(vl3);
		videoListService.save(vl4);
		videoListService.save(vl5);

	}
}