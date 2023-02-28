package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.domain.Photo;
import com.luckytree.member_service.member.domain.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name = "member")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(length = 50)
    private Photo photo;

    @LastModifiedDate
    private LocalDateTime updateAt;

    @CreatedDate
    private LocalDateTime createAt;

    public void updateNicknameAndPhoto(String nickname, Photo photo) {
        this.nickname = nickname;
        this.photo = photo;
    }

    public MemberEntity(String nickname, String email, Photo photo) {
        this.nickname = nickname;
        this.email = email;
        this.status = Status.NORMAL;
        this.photo = photo;
    }
}
