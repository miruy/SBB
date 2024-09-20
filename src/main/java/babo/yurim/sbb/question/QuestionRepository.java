package babo.yurim.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository : JPA가 제공하는 CRUD작업 인터페이스
// <Question, Integer> : Question 엔티티로 레파지토리 생성, 기본키는 Integer라는 의미
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // findBySubject() 예제를 위해 오버라이드
    Question findBySubject(String subject);

    // findBySubjectAndContent() 예제를 위해 오버라이드
    Question findBySubjectAndContent(String subject, String content);

    // findBySubjectLike() 예제를 위해 오버라이드
    List<Question> findBySubjectLike(String subject);

    // 페이지네이션 예제를 위해 오버라이드
    Page<Question> findAll(Pageable pageable);
}
