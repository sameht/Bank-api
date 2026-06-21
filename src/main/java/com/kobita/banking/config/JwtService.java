package com.kobita.banking.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "bXlzZWNyZXRrZXlteXNlY3JldGtleW15c2VjcmV0a2V5";
    private static final Integer EXPIRATION = 10000 * 60 * 24;

    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, claims -> claims.getSubject());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder() //Creates a JWT parser
            .setSigningKey(getSignInKey()) //Sets the secret key used to validate token signature
            .build() //Builds the parser instance
            .parseClaimsJws(token) // Validates token format, signature and expiration
            .getBody(); // Returns payload
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userdetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userdetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        return buildToken(new HashMap<>(), userDetails, EXPIRATION);
    }

    public String buildToken(
        Map<String, Object> extractClaims,
        UserDetails userDetails,
        long expiration
    ){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
