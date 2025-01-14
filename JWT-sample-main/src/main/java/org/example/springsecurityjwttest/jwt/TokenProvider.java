package org.example.springsecurityjwttest.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurityjwttest.domain.Role;
import org.example.springsecurityjwttest.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/** token 생성과 유효성 검증을 담당
 */
@Component
@Slf4j
public class TokenProvider {
    private SecretKey secretKey;
    //private long tokenValidityInMilliSeconds;

    public TokenProvider(@Value("{spring.jwt.secret}") String secret) {
        // 설정파일에 추가해놓은 시크릿 키를 가져다 사용
                         //@Value("{spring.jwt.expiration-seconds}") String tokenValidityInSeconds) {
        // 주입받은 시크릿문자 값을 가지고 주어진 알고리즘으로 decode 해서 시크릿키를 생성
        log.info("1 tokenProvider 생성자 call");
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                                      Jwts.SIG.HS256.key().build().getAlgorithm());
        log.info("2 secretKey ==> {} ", secretKey);
//        this.tokenValidityInMilliSeconds = Long.parseLong(tokenValidityInSeconds) * 1000;
//        log.info("3 tokenValidity ==> {} ", tokenValidityInMilliSeconds);
    }

    // 토큰의 유효일자를 설정해 주고, 사용자의 이름과 아이디, 역할정보를 포함해서 JWT token을 생성
    public String createJwt(User user, String role, Long expiredMs) {
        //log.info("createJwt  call");
        return Jwts.builder()
                .claim("name", user.getName()) //이름
                .claim("userid", user.getUserid()) //아이디
                .claim("role", role) // 역할 (USER, ADMIN)
                .issuedAt(new Date(System.currentTimeMillis())) //현재 로그인된 시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    // 토큰에 포함되어 있는 사용자의 이름과 아이디, 역할정보를 꺼내서 검증
    public String getName(String token) {
        //log.info("getUsername(String token)  call");
        String name = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("name", String.class);
        //log.info("getUsername(String token)  username = {}" ,re);
        return name;
    }

    public String getUserid(String token) {
        //log.info("getUserid(String token)  call");
        String userid = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userid", String.class);
        //log.info("getUserid(String token)  userid = {}" ,userid);
        return userid;
    }

    public Role getRole(String token) {
        //log.info("getRole(String token)  call");
        Role role = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", Role.class);
        //log.info("getRole(String token)  role = {} " , role);
        return role;
    }

    // 발급받은 토큰 만료 여부 확인
    public Boolean isExpired(String token) {
        //log.info("isExpired(String token)  call");
        boolean isValid = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        //log.info("isExpired(String token)  isValid  = {}", isValid);
        return isValid;
    }
}
