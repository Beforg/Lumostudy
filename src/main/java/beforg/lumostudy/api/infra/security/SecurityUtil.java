package beforg.lumostudy.api.infra.security;

import beforg.lumostudy.api.domain.user.Conta;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static Conta getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Conta) authentication.getPrincipal();
    }
}