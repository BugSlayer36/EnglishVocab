package trung.supper.englishgrammar.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_flashcard_srs", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_id", "flashcard_id" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlashcardSrs {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    // --- Thuật toán SM-2 Fields ---

    @Column(nullable = false)
    private Integer repetition = 0; // Số lần lặp lại thành công liên tiếp

    @Column(name = "interval_days", nullable = false)
    private Integer intervalDays = 0; // Khoảng cách đến lần ôn tiếp theo (ngày)

    @Column(name = "ease_factor", nullable = false)
    private Double easeFactor = 2.5; // Độ dễ của thẻ (mặc định 2.5 theo SM-2)

    @Column(name = "next_review_at")
    private LocalDateTime nextReviewAt; // Thời điểm cần ôn tập tiếp theo

    @Column(name = "last_reviewed_at")
    private LocalDateTime lastReviewedAt;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0; // Tổng số lần đã lật thẻ này
}
