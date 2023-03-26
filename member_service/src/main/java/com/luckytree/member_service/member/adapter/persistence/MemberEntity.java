package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.member.domain.Member;
import com.luckytree.member_service.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.luckytree.member_service.common.enums.Status.LEAVE;

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
    private Status status;

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
        status = LEAVE;
    }

    public void isAlreadyDeleted() {
        status.isAlreadyDeleted();
    }

    public MemberEntity(String nickname, String email, String photo) {
        this.nickname = nickname;
        this.email = email;
        this.status = Status.NORMAL;
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
