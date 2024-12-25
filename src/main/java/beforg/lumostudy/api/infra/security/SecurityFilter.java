package beforg.lumostudy.api.infra.security;

import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.repository.ContaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    ContaRepository contaRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);

        if (token != null) {
            System.out.println("Token: " + token);
            var login = tokenService.tokenValidation(token);

            Optional<UserDetails> searchConta =  contaRepository.findByEmail(login);
            if (searchConta.isEmpty()) {
                throw new RuntimeException("Não autorizada"); // melhorar
            }
            Conta conta = (Conta) searchConta.get();
            var autenticacao = new UsernamePasswordAuthenticationToken(conta, null, conta.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);

        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
