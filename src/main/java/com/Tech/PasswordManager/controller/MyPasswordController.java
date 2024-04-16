package com.Tech.PasswordManager.controller;
import com.Tech.PasswordManager.model.dto.MyPasswordDTO;
import com.Tech.PasswordManager.service.MyPasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypassword")
public class MyPasswordController {
    @Autowired
    private MyPasswordService myPasswordService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid MyPasswordDTO myPasswordDTO) {
        try {
            return ResponseEntity.created(null).body(myPasswordService.save(myPasswordDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity find() {
        try {
            return ResponseEntity.ok().body(myPasswordService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(myPasswordService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid MyPasswordDTO myPasswordDTO){
        try {
            myPasswordService.update(myPasswordDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        try{
            myPasswordService.delete(id);
            return ResponseEntity.ok().build();

        } catch (Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
