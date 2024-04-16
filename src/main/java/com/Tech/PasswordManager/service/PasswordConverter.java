package com.Tech.PasswordManager.service;
import com.Tech.PasswordManager.model.dto.MyPasswordDTO;
import com.Tech.PasswordManager.model.entity.MyPassword;
import com.Tech.PasswordManager.security.CryptPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class PasswordConverter {
   private static CryptPasswordService cryptPassword ;

   @Autowired
   public PasswordConverter(CryptPasswordService cryptPassword){
       this.cryptPassword = cryptPassword;
   }

    public static MyPasswordDTO convertMyPasswordToDTO(MyPassword myPassword) {

        String password =  null;

        try {
            password =  cryptPassword.decode(myPassword.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new MyPasswordDTO(
                myPassword.getId(),
                myPassword.getUser().getId(),
                myPassword.getNameService(),
                password,
                myPassword.getLogin(),
                myPassword.getObservation()
        );
    }
    public static MyPassword convertDTOToMyPassword(MyPasswordDTO myPasswordDTO) {
        CryptPasswordService cryptPassword = new CryptPasswordService();

        String password =  null;

        try {
            password =  cryptPassword.encode(myPasswordDTO.password());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new MyPassword(
            myPasswordDTO.id(),
            myPasswordDTO.nameService(),
            password,
            myPasswordDTO.login(),
            myPasswordDTO.observation(),
            null
        );
    }
    public static List<MyPasswordDTO> convertMyPasswordToDTOList(List<MyPassword> passwords) {
        return passwords.stream()
                .map(PasswordConverter::convertMyPasswordToDTO)
                .collect(Collectors.toList());
    }
    public static List<MyPassword> convertDTOToMyPasswordList(List<MyPasswordDTO> passwords) {
        return passwords.stream()
                .map(PasswordConverter::convertDTOToMyPassword)
                .collect(Collectors.toList());
    }
}
