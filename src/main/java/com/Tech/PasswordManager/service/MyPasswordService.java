package com.Tech.PasswordManager.service;
import com.Tech.PasswordManager.model.dto.MyPasswordDTO;
import com.Tech.PasswordManager.model.entity.MyPassword;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.model.repository.MyPasswordRepository;
import com.Tech.PasswordManager.security.CryptPasswordService;
import com.Tech.PasswordManager.security.UserTolls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyPasswordService {
    private MyPasswordRepository myPasswordRepository;
    private UserService userService;


    @Autowired
    public MyPasswordService(MyPasswordRepository myPasswordRepository, UserService userService) {
        this.myPasswordRepository = myPasswordRepository;
        this.userService = userService;
    }
    public List<MyPasswordDTO> getAll() {
        List<MyPassword> passwords = myPasswordRepository.findAllByUserId(UserTolls.getUserContextId());
        return PasswordConverter.convertMyPasswordToDTOList(passwords);
    }

    public MyPasswordDTO getById(long id) throws Exception {
        MyPassword myPassword = myPasswordRepository.findById(id)
                .orElseThrow(() -> new Exception("Password not found with id " + id));

        UserTolls.isAutorizate(myPassword.getUser().getId());

        return PasswordConverter.convertMyPasswordToDTO(myPassword);
    }

    public MyPasswordDTO save(MyPasswordDTO myPasswordDTO) throws Exception {

        User user = UserConverter.convertToUser(userService.getById(UserTolls.getUserContextId()));
        MyPassword myPassword = PasswordConverter.convertDTOToMyPassword(myPasswordDTO);
        myPassword.setUser(user);

        return PasswordConverter.convertMyPasswordToDTO(myPasswordRepository.save(myPassword));
    }

    public MyPasswordDTO update(MyPasswordDTO myPasswordDTO) throws Exception {

        MyPassword existingPassword = myPasswordRepository.findById(myPasswordDTO.id())
                .orElseThrow(() -> new Exception("Password not found with id "));

        UserTolls.isAutorizate(existingPassword.getUser().getId());

        CryptPasswordService cryptPassword = new CryptPasswordService();

        existingPassword.setNameService(myPasswordDTO.nameService());
        existingPassword.setPassword(cryptPassword.encode(myPasswordDTO.password()));
        existingPassword.setLogin(myPasswordDTO.login());
        existingPassword.setObservation(myPasswordDTO.observation());

        return PasswordConverter.convertMyPasswordToDTO(myPasswordRepository.save(existingPassword));
    }

    public void delete(long id) throws Exception {
        MyPassword existingPassword = myPasswordRepository.findById(id)
                .orElseThrow(() -> new Exception("Password not found with id "));

        UserTolls.isAutorizate(existingPassword.getUser().getId());

        myPasswordRepository.deleteById(id);
    }
}
