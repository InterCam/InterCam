package com.example.intercam.entity;

import com.example.intercam.dto.UserJoinDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long user_id; // Identify 적용 시 Analyst 상속이 Error 발생함. User 는 Analyst 랑 같은 Id를 써야하기 때문..

    @OneToOne @JoinColumn(name = "major_id")
    private Major major_id;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH}) // 동영상 리스트  // User - VideoList 저장 시 persist Error 발생함. persist 는 빼야함
    private List<VideoList> listId;

    @NotNull @Column(unique = true, columnDefinition = "varchar(10)")
    private String username; // 이메일(아이디)

    @NotNull
    private String password; // 비밀번호

    @NotNull @Column(columnDefinition = "varchar(20)")
    private String phone; // 전화번호

    @NotNull @Column(columnDefinition = "varchar(20)")
    private String birth; // 생일

    @NotNull @Column(columnDefinition = "varchar(20)")
    private String name;

    @Column(columnDefinition = "varchar(32) default 'User'")
    @Enumerated(EnumType.STRING) // 권한
    private Auth auth;


    public User(UserJoinDto userJoinDto) {
        this.username = userJoinDto.getUsername();
        this.password = userJoinDto.getPassword();
        this.phone = userJoinDto.getPhone();
        this.birth = userJoinDto.getBirth();
        this.name = userJoinDto.getName();
        this.auth = Auth.USER;
    }

    public User(@NotNull String username, @NotNull String password,
                @NotNull String phone, @NotNull String name,
                @NotNull String birth) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
        this.name = name;
        this.auth = Auth.ANALYST;
    }

    public void addVideoList(VideoList videoList){
        if(listId == null){
            listId = new ArrayList<>();
        }
        listId.add(videoList);
        videoList.addUser(this);
    }

    public void addMajor(Major major){
        major_id = major;
    }

    public void changePassword(String password){
        this.password = password;
    }

}
