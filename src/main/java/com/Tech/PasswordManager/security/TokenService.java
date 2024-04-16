package com.Tech.PasswordManager.security;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.model.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    private CryptPasswordService cryptPasswordService;
    private UserRepository userRepository;

    @Autowired
    public TokenService(CryptPasswordService cryptPasswordService, UserRepository userRepository){
        this.cryptPasswordService = cryptPasswordService;
        this.userRepository = userRepository;
    }

    public String genToken(User user){
        try {
          Algorithm algorithm =  Algorithm.HMAC256(secret);
          String token = JWT.create()
                  .withIssuer("auth-api")
                  .withSubject(user.getLogin())
                  .withExpiresAt(genExpirationToken())
                  .sign(algorithm);

          return "{\"token\":\"" + token + "\",\"tokenRefresh\":\"" + genTokenRefresh(user) + "\"}";

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro generating token: ", e);
        }
    }
    public String genTokenRefresh(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String codeUser = cryptPasswordService.encode(user.getName());

            return JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("codeUser", codeUser)
                    .withClaim("codeAccess", CodeAccess.genCode(user.getId()))
                    .withExpiresAt(genExpirationTokenForRefresh())
                    .withClaim("isRefreshToken", true)
                    .sign(algorithm);

        } catch (Exception e) {
            throw new RuntimeException("Error generating refresh token: " + e.getMessage(), e);
        }
    }
    public String genNewToken(String refreshToken){
        try {
            isValidRefreshToken(refreshToken);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("isRefreshToken", true)
                    .build()
                    .verify(refreshToken);

            String codeUser =  cryptPasswordService.decode(decodedJWT.getClaim("codeUser").asString());
            long id = CodeAccess.reversCode(decodedJWT.getClaim("codeAccess").asLong());

            User user = userRepository.findById(id).orElseThrow();

            if(!user.getName().equals(codeUser)){
                throw new RuntimeException("access denied");
            }

            return genToken(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Instant genExpirationToken(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
    private Instant genExpirationTokenForRefresh(){
        return LocalDateTime.now().minusSeconds(10).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm =  Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
             return "";
        }
    }
    public boolean isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
           var teste = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token);
           System.out.println(teste);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    public void isValidRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("isRefreshToken", true)
                    .build()
                    .verify(refreshToken);

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token Refresh invalid");
        }
    }
}
