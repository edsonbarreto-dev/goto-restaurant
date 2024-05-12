package br.com.gotorestaurant.application.controller;

import br.com.gotorestaurant.infra.security.DataResponseToken;
import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.DataLoginUser;
import br.com.gotorestaurant.infra.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService serviceToken;

  @PostMapping
  public ResponseEntity<DataResponseToken> login(@RequestBody @Valid DataLoginUser data) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var authentication = manager.authenticate(authenticationToken);
    var tokenJWT = serviceToken.generateToken((User) authentication.getPrincipal());

    return ResponseEntity.ok(new DataResponseToken(tokenJWT));
  }
}
