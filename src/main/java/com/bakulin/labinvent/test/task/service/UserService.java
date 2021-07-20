package com.bakulin.labinvent.test.task.service;

import com.bakulin.labinvent.test.task.exception.domain.EmailExistException;
import com.bakulin.labinvent.test.task.exception.domain.EmailNotFoundException;
import com.bakulin.labinvent.test.task.exception.domain.UserNotFoundException;
import com.bakulin.labinvent.test.task.exception.domain.UsernameExistException;
import com.bakulin.labinvent.test.task.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException;

    List<User> getUsers();

    User findByUserName(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String userName, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    User updateUser(String currentUserName,String newFirstName, String newLastName, String newUserName, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

    void deleteUser(long id);

    void resetPassword(String email) throws EmailNotFoundException, MessagingException;

    User updateProfileImage(String userName, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException;

}
