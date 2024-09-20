package babo.yurim.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SequrityConfig에 설정한 암호화 설정 메서드로 대체

    // 회원가입
    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser(); // 빈 유저 객체 생성

        user.setUsername(username); // 아이디 세팅
        user.setEmail(email); // 이메일 세팅

        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화 클래스, 빈 암호화 객체 생성

        // SequrityConfig에 설정한 암호화 설정 메서드로 대체
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화해서 세팅
        this.userRepository.save(user); // 유저 저장
        return user;
    }
}
