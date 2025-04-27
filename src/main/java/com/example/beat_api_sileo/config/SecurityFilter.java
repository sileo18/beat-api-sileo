package com.example.beat_api_sileo.config;

import com.example.beat_api_sileo.domain.User.User;
import com.example.beat_api_sileo.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Caso seja uma rota permitida
        if (path.contains("user/register") || path.contains("user/get")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Caso não seja uma rota permitida
        var token = getToken(request);

        if (token != null) {
            var valid = tokenService.validateJWT(token);

            if (valid != null) {
                User usuario = userRepository.findByEmail(valid)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                var authorities = usuario.getRoles().stream()
                        .map(role -> {
                            logger.info("Role encontrada: {}", role.getName());
                            return new SimpleGrantedAuthority(role.getName().name());
                        })
                        .toList();

                logger.info("Usuário autenticado: {}, Roles: {}", usuario.getEmail(), authorities);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.info("Autenticação configurada para o usuário: {}", usuario.getEmail());
            }
        }

        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ", "");
    }

}
