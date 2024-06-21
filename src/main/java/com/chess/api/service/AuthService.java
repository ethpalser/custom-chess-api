package com.chess.api.service;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import java.nio.charset.StandardCharsets;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

@Service
public class AuthService {

    private static final String JWT_ISS = "www.custom-chess-api.com";

    private static final String JWT_SUB = "www.custom-chess-client.com";

    @Value("${jwt.secret}")
    private String secret;

    public final String createJWT(Date issueExp) {
        return createJWT(JWT_ISS, JWT_SUB, issueExp);
    }

    public final String createJWT(String issuer, String subject, Date issueExp) {
        Algorithm alg = Algorithm.HMAC256(this.secret);

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(issueExp);

        return tokenBuilder.sign(alg);
    }

    @SafeVarargs
    public final String createJWTWithClaims(Date issueExp, Map.Entry<String, String>... claims) {
        return createJWTWithClaims(JWT_ISS, JWT_SUB, issueExp, claims);
    }

    @SafeVarargs
    public final String createJWTWithClaims(String issuer, String subject, Date issueExp, Map.Entry<String, String>... claims) {
        Algorithm alg = Algorithm.HMAC256(this.secret);

        JWTCreator.Builder tokenBuilder = JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(issueExp);

         for (Map.Entry<String, String> c : claims) {
             tokenBuilder.withClaim(c.getKey(), c.getValue());
         }
         return tokenBuilder.sign(alg);
    }

    public String createRefreshToken() {
        byte[] bytes = new byte[32];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Math.floor(Math.random() * 128);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
