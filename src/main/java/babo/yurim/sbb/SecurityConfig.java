package babo.yurim.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링의 환경설정 파일임을 의미
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 하는 의미
public class SecurityConfig {
    @Bean
        // Bean : 스프링에 의해 생성 또는 관리되는 객체
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
        ;
        return http.build();
    }
}

