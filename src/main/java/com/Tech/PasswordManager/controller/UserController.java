package com.Tech.PasswordManager.controller;
import com.Tech.PasswordManager.model.dto.UserDTO;
import com.Tech.PasswordManager.model.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserDTO userDTO) {
        try {
            return ResponseEntity.created(null).body(userService.save(userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity find() {
        try {
            UserDTO user = userService.getMyUser();
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UserDTO userDTO){
        try {
            userService.update(userDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity delete(){
        try{
            userService.deleteMyUser();
            return ResponseEntity.ok().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
