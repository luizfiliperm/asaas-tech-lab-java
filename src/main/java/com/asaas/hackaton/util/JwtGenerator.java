package com.asaas.hackaton.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtGenerator {

    private static final String SECRET_KEY = "ad0496b5-290c-4c0a-99ee-4ca6fb18153e";

    public static String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withIssuer(username)
                .sign(algorithm);
        return token;
    }

    public static String getIssuer(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getIssuer();
        } catch (Exception exception) {
            return null;
        }
    }
}
