package org.choongang.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@EnableConfigurationProperties(JwtProperties.class)
public class TokenProvider {

    @Autowired
    private JwtProperties props;

    private Key key;

    public TokenProvider() {
        byte[] keyBytes = Decoders.BASE64.decode(props.getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime(); // EpochTime
        Date validity = new Date(now + props.getAccessTokenValidityInSeconds() * 1000);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }
}
