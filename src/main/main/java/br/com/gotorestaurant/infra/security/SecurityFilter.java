package br.com.gotorestaurant.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gotorestaurant.infra.user.UserRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filter
  ) throws ServletException, IOException {

    String tokenJWT = getTokenJWT(request);

    if (tokenJWT != null) {
      var login = tokenService.getSubject(tokenJWT);
      var user = userRepository.findByLogin(login);
      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filter.doFilter(request, response);
  }

  private static String getTokenJWT(HttpServletRequest request) {
    String authorization = request.getHeader("Authorization");
    if (authorization != null) {
      return authorization.replace("Bearer ", "");
    }
    return null;
  }
}
