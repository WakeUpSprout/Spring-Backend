package wakeup.sprout.spring.adapter.preview.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.question.out.persistence.entity.QuestionJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "preview")
public class PreviewJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "preview_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long previewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private QuestionJpaEntity question;

    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(100)")
    private String answer;

    @Column(name = "is_correct", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isCorrect;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
