package com.Tech.PasswordManager.security;
import com.Tech.PasswordManager.model.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
          Algorithm algorithm =  Algorithm.HMAC256(secret);
            return JWT.create()
                  .withIssuer("auth-api")
                  .withSubject(user.getLogin())
                  .withExpiresAt(genExpirationToken())
                  .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro generating token: ", e);
        }
    }

    private Instant genExpirationToken(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
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
}
