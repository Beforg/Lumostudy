package beforg.lumostudy.api.infra.security;

import beforg.lumostudy.api.domain.user.Conta;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${lumostudy.api.token.secret}")
    private String secret;
    public String generateToken(Conta conta) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("lumostudy-auth-api")
                    .withSubject(conta.getEmail())
                    .withExpiresAt(this.getExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String tokenValidation(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("lumostudy-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant getExpirationTime() {
        long expirationTime = 1000L * 60 * 60 * 24 * 365 * 10;
        return Instant.now().plusSeconds(expirationTime);
    }
}
