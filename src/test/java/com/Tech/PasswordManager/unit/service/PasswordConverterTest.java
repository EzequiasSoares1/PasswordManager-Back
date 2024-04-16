package com.Tech.PasswordManager.unit.service;
import com.Tech.PasswordManager.ConfigSpringTest;
import com.Tech.PasswordManager.model.dto.MyPasswordDTO;
import com.Tech.PasswordManager.model.entity.MyPassword;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.service.PasswordConverter;
import com.Tech.PasswordManager.security.CryptPasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PasswordConverterTest extends ConfigSpringTest {

    private MyPasswordDTO myPasswordDTO;
    private  MyPassword myPassword;


    @BeforeEach
    public void up() {
        CryptPasswordService cryptPasswordService = new CryptPasswordService();
        myPasswordDTO = new MyPasswordDTO(1L,2L,"Test Service","TestPassword",
                "Test Login","Test Observation");

        try {
            myPassword = new MyPassword(1L,"name", cryptPasswordService.encode("12SDFCSD35"),"eu","obs",
                    new User(1L,"fghfghfg","null","sdfdfSD"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testConvertMyPasswordToDTO() throws Exception {
        MyPasswordDTO result = PasswordConverter.convertMyPasswordToDTO(myPassword);
        assertNotNull(result);
        assertEquals(myPassword.getId(), result.id());
        assertEquals(myPassword.getUser().getId(), result.userId());
        assertEquals(myPassword.getNameService(), result.nameService());
    }

    @Test
    public void testConvertDTOToMyPassword() throws Exception {
        MyPassword result = PasswordConverter.convertDTOToMyPassword(myPasswordDTO);
        assertNotNull(result);
        assertEquals(myPasswordDTO.id(), result.getId());
        assertEquals(myPasswordDTO.nameService(), result.getNameService());
    }

    @Test
    public void testConvertMyPasswordToDTOList() throws Exception {
        List<MyPassword> myPasswords = Arrays.asList(myPassword, myPassword);
        List<MyPasswordDTO> result = PasswordConverter.convertMyPasswordToDTOList(myPasswords);
        assertNotNull(result);
        assertEquals(myPasswords.size(), result.size());
    }

    @Test
    public void testConvertDTOToMyPasswordList() throws Exception {
        List<MyPasswordDTO> myPasswordDTOs = Arrays.asList(myPasswordDTO, myPasswordDTO);
        List<MyPassword> result = PasswordConverter.convertDTOToMyPasswordList(myPasswordDTOs);
        assertNotNull(result);
        assertEquals(myPasswordDTOs.size(), result.size());
    }


}
