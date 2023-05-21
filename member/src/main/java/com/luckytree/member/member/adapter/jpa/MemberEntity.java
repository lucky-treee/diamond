package com.luckytree.member.member.adapter.jpa;

import com.luckytree.member.member.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;
import luckytree.poom.core.enums.MemberStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @Column(length = 200)
    private String photo;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public void updateNicknameAndPhoto(String nickname, String photo) {
        this.nickname = nickname;
        this.photo = photo;
    }

    public void leave() {
        status = MemberStatus.LEAVE;
    }

    public void isAlreadyDeleted() {
        status.isAlreadyDeleted();
    }

    public MemberEntity(String nickname, String email, String photo) {
        this.nickname = nickname;
        this.email = email;
        this.status = MemberStatus.NORMAL;
        this.photo = photo;
    }

    public Member toDomain() {
        return new Member(
                this.nickname,
                this.email,
                this.status,
                this.photo
        );
    }

    public void update(String nickname, String photo) {
        this.nickname = nickname;
        this.photo = photo;
    }
}
