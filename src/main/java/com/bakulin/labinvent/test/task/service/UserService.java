package com.bakulin.labinvent.test.task.service;

import com.bakulin.labinvent.test.task.exception.user.*;
import com.bakulin.labinvent.test.task.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException, InvalidEmailException;

    List<User> getUsers();

    User findByUserName(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String userName, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, MessagingException, NotAnImageFileException, InvalidEmailException;

    User updateUser(String currentUserName,String newFirstName, String newLastName, String newUserName, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException, InvalidEmailException;

    void deleteUser(String userName) throws IOException;

    void resetPassword(String email) throws EmailNotFoundException, MessagingException;

    User updateProfileImage(String userName, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException, InvalidEmailException;

}
