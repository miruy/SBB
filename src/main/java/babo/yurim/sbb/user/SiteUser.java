package babo.yurim.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 차례대로 자동으로 생성
    private Long id;

    @Column(unique = true) // 중복 되면 안되고 유니크해야됨
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
