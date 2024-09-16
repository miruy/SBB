package babo.yurim.sbb;

import babo.yurim.sbb.answer.Answer;
import babo.yurim.sbb.answer.AnswerRepository;
import babo.yurim.sbb.question.Question;
import babo.yurim.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest // 스프링 부트의 테스트 클래스임을 의미
class SbbApplicationTests {

    @Autowired // QuestionRepository 객체 의존성 주입
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    /*  질문 데이터 관리 */
    // insert 예제
//        @Test // 테스트 메서드임을 의미
//        void testJpa() {
//            Question q1 = new Question();
//            q1.setSubject("sbb가 무엇인가요?");
//            q1.setContent("sbb에 대해서 알고 싶습니다.");
//            q1.setCreateDate(LocalDateTime.now());
//            this.questionRepository.save(q1); // 첫번째 질문 저장
//
//            Question q2 = new Question();
//            q2.setSubject("스프링부트 모델 질문입니다.");
//            q2.setContent("id는 자동으로 생성되나요?");
//            q2.setCreateDate(LocalDateTime.now());
//            this.questionRepository.save(q2); // 두번째 질문 저장
//        }

    // select - findAll() 예제
//        @Test
//        void testJpa() {
//            List<Question> all = this.questionRepository.findAll(); // 모든 질문 조회
//            assertEquals(3, all.size()); // assertEquals(기댓값, 실젯값) : 예상한 결과와 실제 결과가 동일한지를 확인 || 모든 질문 갯수가 3인지
//
//            Question q = all.get(0); // 레파지토리 객체에서 객체 꺼냄
//            assertEquals("sbb가 무엇인가요?", q.getSubject()); // 첫번째 질문의 제목이 기댓값과 일치하는지 확인
//        }

    // select - findById() 예제
//    @Test
//    void testJpa() {
//
//        // Optional : null값을 유연하게 처리하기 위한 클래스
//        Optional<Question> oq = this.questionRepository.findById(1); // return값이 있을 수도 있고, 없을수도 있으므로 Optional 사용 | id가 1인 질문 조회
//
//        if (oq.isPresent()) { // isPresent() : 값이 존재하는지 확인
//            Question q = oq.get();
//            assertEquals("sbb가 무엇인가요?", q.getSubject());
//        }
//
//    }

//    // select - findBySubject() 예제
//    @Test
//    void testJpa() {
//        Question q = this.questionRepository.findBySubject("스프링부트 모델 질문입니다."); // QuestionRepository에 오버라이드한 메서드 사용
//        assertEquals(3, q.getId());
//    }

//    // select - findBySubjectAndContent() 예제
//    @Test
//    void testJpa() {
//        Question q = this.questionRepository.findBySubjectAndContent("스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?"); // QuestionRepository에 오버라이드한 메서드 사용
//        assertEquals(3, q.getId());
//    }

//    // select - findBySubjectLike() 예제
//    @Test
//    void testJpa() {
//        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%"); // QuestionRepository에 오버라이드한 메서드 사용
//        Question q = qList.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());
//    }

//    // update 예제
//    @Test
//    void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(1); // id가 1번인 질문 조회
//        assertTrue(oq.isPresent()); // 값 존재하는지 확인
//
//        Question q = oq.get(); // 레파지토리 객체에서 객체 꺼냄
//        q.setSubject("수정된 제목");
//        this.questionRepository.save(q);
//    }

    // delete 예제
//    @Test
//    void testJpa() {
//        assertEquals(2, this.questionRepository.count()); // 질문 전체 갯수가 2개 맞는지 확인
//        Optional<Question> oq = this.questionRepository.findById(2);
//        assertTrue(oq.isPresent());
//
//        Question q = oq.get();
//        this.questionRepository.delete(q);
//        assertEquals(1, this.questionRepository.count());
//
//    }


    /*  답변 데이터 관리 */
    // insert 예제
//    @Test
//    void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(3);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//
//        Answer a = new Answer();
//        a.setContent("네 자동으로 생성됩니다.");
//        a.setQuestion(q); // 외래키 생성 | 해당 질문의 대한 답변이라는 의미
//        a.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a);
//    }

//    // select 예제
//    @Test
//    void testJpa() {
//        Optional<Answer> oa = this.answerRepository.findById(2);
//        assertTrue(oa.isPresent());
//
//        Answer a = oa.get();
//        assertEquals(3, a.getQuestion().getId());
//    }

    // select - 질문으로 여러 답변 조회 예제
    @Transactional // DB 세션이 종료되지 않게 설정
    @Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(3);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList(); // @Transactional 없으면 여기서 에러
        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

}
