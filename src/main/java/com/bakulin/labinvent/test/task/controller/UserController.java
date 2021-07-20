package com.bakulin.labinvent.test.task.controller;

import com.bakulin.labinvent.test.task.domain.HttpResponse;
import com.bakulin.labinvent.test.task.exception.ExceptionHandling;
import com.bakulin.labinvent.test.task.exception.domain.EmailExistException;
import com.bakulin.labinvent.test.task.exception.domain.EmailNotFoundException;
import com.bakulin.labinvent.test.task.exception.domain.UserNotFoundException;
import com.bakulin.labinvent.test.task.exception.domain.UsernameExistException;
import com.bakulin.labinvent.test.task.model.User;
import com.bakulin.labinvent.test.task.security.UserPrincipal;
import com.bakulin.labinvent.test.task.service.UserService;
import com.bakulin.labinvent.test.task.utils.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.bakulin.labinvent.test.task.constant.FileConstant.*;
import static com.bakulin.labinvent.test.task.constant.SecurityConstant.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserController extends ExceptionHandling {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUserName(), user.getPassword());
        User loginUser = userService.findByUserName(user.getUserName());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader , OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("userName") String userName,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNonLocked") String isNonLocked,
                                           @RequestParam(value = "profileImage", required = false)MultipartFile profileImage) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException {
        User newUser = userService.addNewUser(firstName, lastName,userName,email,role, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(newUser, OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam("currentUserName") String currentUserName,
                                           @RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("userName") String userName,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNonLocked") String isNonLocked,
                                           @RequestParam(value = "profileImage", required = false)MultipartFile profileImage) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException {
        User updateUser = userService.updateUser(currentUserName,firstName, lastName,userName,email,role, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImage);
        return new ResponseEntity<>(updateUser, OK);
    }

    @GetMapping("/find/{userName}")
    public ResponseEntity<User> getUser(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);
        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException, MessagingException {
        userService.resetPassword(email);
        return response(OK, EMAIL_SENT + email);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return response(OK, USER_DELETED_SUCCESSFULLY);
    }

    @PostMapping("/update-profile-image")
    public ResponseEntity<User> updateProfileImage(@RequestParam("userName") String userName, @RequestParam(value = "profileImage")MultipartFile profileImage) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException {
        User updateUserImage = userService.updateProfileImage(userName,profileImage);
        return new ResponseEntity<>(updateUserImage, OK);
    }

    @GetMapping(path = "/image/{userName}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("userName") String userName, @PathVariable("fileName") String fileName ) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + userName + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{userName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("userName") String userName) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + userName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message.toUpperCase()), httpStatus);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    }
//Ds2rKmW3Tu
}
