package org.example.springsecurityjwttest.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.jwt.JwtFilter;
import org.example.springsecurityjwttest.jwt.LoginFilter;
import org.example.springsecurityjwttest.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity  // 기본적인 웹 보안을 활성화하겠다.
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    //AuthenticationManager 가 인자로 받을 AuthenticationConfiguraion 객체 생성자
    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenProvider tokenProvider;
    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        //log.info("bCryptPasswordEncoder call.....");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CORS 설정
        http.cors((corsCustomizer ->
                corsCustomizer.configurationSource(new CorsConfigurationSource()
                {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        //localhost에서의 접근만 허용을 해줌, 어디서든 모든 헤더에 대해 접근 허용을 해주려면 "*" 로 수정할 것
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        //모든 메서드 GET,POST,PUT,DELETE 에 대해 접근 허용
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        // 내 서버가 응답할 때 json을 자바스크립트 fetch 에서 처리할 수 있게 설정
                        configuration.setAllowCredentials(true);

                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                        return configuration;
                    }
                })));

        //경로별 인가 작업
        http.authorizeHttpRequests((auth) ->
                auth
                  .requestMatchers("/favicon.ico", "/api/main","/api/users", "/api/users/**", "/api/posts").permitAll()
                  .requestMatchers("/admin").hasRole("ADMIN")
                  .anyRequest().authenticated()
        );

        //csrf disable
        http.csrf((auth) -> auth.disable()); //csrf공격을 방어하기 위한 토큰 주고 받는 부분을 비활성화!

        //Form 로그인 방식 disable -> React, JWT 인증 방식으로 변경예정
        //disable 를 설정하면 시큐리티의 UsernamePasswordAuthenticationFilter 비활성됨.
        http.formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        //세션 설정 - JWT 를 이용 할것이기 때문에 stateless 설정
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager()
        //메소드에 authenticationConfiguration 객체를 넣어야 함)
        //addFilterAt 은 UsernamePasswordAuthenticationFilter 의 자리에 LoginFilter 가 실행되도록 설정하는 것
        //JWTFilter 등록
        http.addFilterBefore(new JwtFilter(tokenProvider), LoginFilter.class);
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), tokenProvider), UsernamePasswordAuthenticationFilter.class);   //UsernamePasswordAuthenticationFilter 대신 LoginFilter를 쓰겠다.는 뜻

        return http.build();
    }
}
