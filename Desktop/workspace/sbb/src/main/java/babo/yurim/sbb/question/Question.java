package babo.yurim.sbb.question;

import babo.yurim.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity // 엔티티임을 의미
public class Question {

    @Id // primaryKey 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primaryKey를 고유하면서 차례로 숫자를 늘려줌
    private Integer id; // 기본키

    @Column(length = 200) // 열의 세부설정 - 길이
    private String subject;

    @Column(columnDefinition = "TEXT") // 열의 세부설정 - 텍스트 형태의 데이터
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // foreignKey 설정 | 일대다(1:N) 관계 | 질문 삭제 시 답변도 같이 삭제
    private List<Answer> answerList; // 외래키

}
