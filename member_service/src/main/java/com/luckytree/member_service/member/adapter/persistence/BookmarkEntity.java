package com.luckytree.member_service.member.adapter.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class BookmarkEntity {

    @Id
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private Long member_id;

    @Column(length = 20, nullable = false)
    private Long shop_id;

    @Column(length = 50)
    private String category;

    @Column(name = "create_at")
    @CreatedDate
    private LocalDateTime createAt;
}
