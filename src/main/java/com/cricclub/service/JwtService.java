package com.cricclub.service;

import com.cricclub.google.GoogleDataProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {
    public static final String SECRET_KEY = "d00aa3d3974877e44bf85225de18dec29d29ed8ec2c7cf95d4c64646c62fa13b0674a0ad3111dc293e6fe04dadcb7e08b16c3db0693c38261c5064cfb3d1663d169947f0e60b95b27ff9b43a5406a081f7c4e71e995b910228bfe05829de08e2d71a093d531c67ccb3278309cc0cf95e70d331dec95723b3dd572f24389a127321c03455b7eea01dac9d9f855c022d066940e1e0ee6d2483ff03638b10363452ea726195e94295c7da1a9c4b2b4c6df47af7e29f5d6faba3649eab0c1cfbdc81326402fd0e961492ded22d82d04e6a4cd1efcc4fcae10fdff0d99b6e7998f4055a1789fdcc4895c75c3982f4879dcc9248434b64e9d88a151a3b230c393f791d";

    @Autowired
    private GoogleDataProvider googleDataProvider;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractGoogleEmailFromToken(String token) {
        return googleDataProvider.getData(token).getEmail();
    }

    public boolean isGoogleTokenValid(String token) {
        return googleDataProvider.isGoogleTokenValid(token);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder()
                .decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(bytes, "HmacSHA256");
    }
}
