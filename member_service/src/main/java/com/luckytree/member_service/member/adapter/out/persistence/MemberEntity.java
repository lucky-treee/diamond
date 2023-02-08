package com.luckytree.member_service.member.adapter.out.persistence;

import com.luckytree.member_service.member.application.port.in.MemberRequest;
import com.luckytree.member_service.member.domain.MemberStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "member")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @Column(length = 50)
    private String photo;

    public MemberEntity(MemberRequest memberRequest) {
        this.nickname = memberRequest.getNickname();
        this.email = memberRequest.getEmail();
        this.password = memberRequest.getPassword();
        this.status = MemberStatus.NORMAL;
        this.photo = memberRequest.getPhoto();
    }
}
