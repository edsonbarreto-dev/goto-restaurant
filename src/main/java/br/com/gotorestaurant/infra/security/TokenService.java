package br.com.gotorestaurant.infra.security;

import br.com.gotorestaurant.infra.user.User;
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

    public TokenService(String secret) {
      this.secret = secret;
    }
    public TokenService(){}

    public String generateToken(User user) {
    System.out.println("SENHA: " + secret);
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
              .withIssuer("API Voll.med")
              .withSubject(user.getLogin())
              .withClaim("id", user.getId())
              .withExpiresAt(expiritDate())
              .sign(algorithm);
    } catch (JWTCreationException exception){
      throw new RuntimeException("Erro ao gerar token", exception);
    }
  }

  public String getSubject(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .withIssuer("API Voll.med")
              .build()
              .verify(token)
              .getSubject();
    } catch (JWTVerificationException exception){
      throw new RuntimeException("O token JWT enviado é inválido ou expirou.");
    }
  }

  private Instant expiritDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
