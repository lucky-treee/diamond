package com.luckytree.member.member.adapter.mysql.member;

import com.luckytree.member.member.domain.member.Member;
import com.luckytree.member.member.domain.member.MemberDetail;
import jakarta.persistence.*;
import lombok.*;
import luckytree.poom.core.enums.MemberStatus;
import luckytree.poom.core.jwt.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

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
    private Long id;

    @Enumerated(STRING)
    @Column(length = 10, nullable = false)
    private Role role;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    @Enumerated(STRING)
    private MemberStatus status;

    @Column(length = 200)
    private String photo;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;

    public MemberEntity(Member member) {
        this.id = member.getId();
        this.role = (member.getRole() == null) ? Role.MEMBER : member.getRole();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.status = (member.getStatus() == null) ? MemberStatus.NORMAL : member.getStatus();
        this.photo = member.getPhoto();
        this.createAt = member.getCreateAt();
    }

    public Member toDomain() {
        return new Member(id, role, nickname, email, status, photo, updateAt, createAt);
    }
}
