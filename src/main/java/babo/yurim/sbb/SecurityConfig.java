package babo.yurim.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration // 스프링의 환경설정 파일임을 의미
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 하는 의미
public class SecurityConfig {
    // Bean : 스프링에 의해 생성 또는 관리되는 객체
    // 스프링 시큐리티 적용 조건 설정
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 로그인안해도 모든페이지 요청 가능
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())

                // /h2-console/로 시작하는 모든 URL은 CSRF 검증을 하지 않는다는 설정
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))

                // csrf 설정으로 프레임이 깨져보이는 H2콘솔을 동일한 사이트에서 제공되는 거라고 알려주는 설정
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                        )))

                // 로그인 경로, 성공 시 이동경로
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/"))

                // 로그아웃
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)) // 로그아웃 시 생성된 사용자 세션도 삭제

        ;
        return http.build();
    }

    // 회원가입 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 처리
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

