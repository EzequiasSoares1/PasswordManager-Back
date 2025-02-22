package com.Tech.PasswordManager.controller;
import com.Tech.PasswordManager.model.dto.AuthenticationDTO;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.security.TokenService;
import jakarta.validation.Valid;
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
            var token = tokenService.genToken((User) auth.getPrincipal());
            return ResponseEntity.ok(token);
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("Login or Password invalid");
        }
    }

    @PostMapping("/isValidToken")
    public ResponseEntity isValidToken(@RequestParam String token){
        try{
            if(!tokenService.isValidToken(token)) {return ResponseEntity.badRequest().body("token invalid");}
            else{ return ResponseEntity.ok().build(); }
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/tokenRefresh")
    public ResponseEntity tokenRefresh(@RequestParam String token){
        try{
             return ResponseEntity.ok(tokenService.genNewToken(token));
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
