package com.Tech.PasswordManager.service;
import com.Tech.PasswordManager.model.dto.UserDTO;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.model.repository.MyPasswordRepository;
import com.Tech.PasswordManager.model.repository.UserRepository;
import com.Tech.PasswordManager.security.UserTolls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private MyPasswordRepository myPasswordRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       MyPasswordRepository myPasswordRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.myPasswordRepository = myPasswordRepository;
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(login);
        return user != null ? user: null;
    }

    public UserDTO getById(long id) throws Exception {
        UserTolls.isAutorizate(id);
        User u =  userRepository.findById(id).orElseThrow(() ->  new Exception("User not found"));
        return  UserConverter.convertToUserDTO(u);
    }

    public UserDTO getMyUser() throws Exception {
        User u =  userRepository.findById(UserTolls.getUserContextId()).get();
        return  UserConverter.convertToUserDTO(u);
    }

    public List<UserDTO> getAll() {
        List<User> u = userRepository.findAll();
        return u != null ? UserConverter.convertToUserDTOList(u): null;
    }

    public UserDTO save(UserDTO userDTO) {
       if(userRepository.findByLogin(userDTO.login()) != null){
           throw new RuntimeException("User already exists");
       }

        User newUser = UserConverter.convertToUser(userDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return UserConverter.convertToUserDTO(userRepository.save(newUser));
    }
    public void deleteMyUser() {
        myPasswordRepository.deleteAllByUserId(UserTolls.getUserContextId());
        userRepository.deleteById(UserTolls.getUserContextId());
    }
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(() ->  new RuntimeException("User not found"));
        myPasswordRepository.deleteAllByUserId(UserTolls.getUserContextId());
        userRepository.deleteById(UserTolls.getUserContextId());
    }

    public void update(UserDTO userDTO) {
        UserTolls.isAutorizate(userDTO.id());

        User user =  userRepository.findById(userDTO.id())
            .orElseThrow(() ->  new RuntimeException("User not found"));

        if (userDTO.name() != null) { user.setName(userDTO.name());}
        if (userDTO.login() != null && !user.getLogin().equals(userDTO.login())) {
            if(userRepository.findByLogin(userDTO.login()) == null){
                user.setLogin(userDTO.login());
            }else {
                throw new RuntimeException("Login already exists");
            }
        }
        if (userDTO.password() != null) {user.setPassword(passwordEncoder.encode(userDTO.password()));}
        userRepository.save(user);
    }
}
