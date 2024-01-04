package com.Tech.PasswordManager.controller;
import com.Tech.PasswordManager.model.dto.AuthenticationDTO;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.security.TokenService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        try{
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.login(),dto.password());
            var auth = authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Login or Password invalid");
        }
    }
    @PostMapping("/isvalidtoken")
    public ResponseEntity isValidToken(@RequestParam String token){
        try{
            if(tokenService.isValidToken(token) == false) {return ResponseEntity.badRequest().body("token invalid");}
            else{ return ResponseEntity.ok().build(); }
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
