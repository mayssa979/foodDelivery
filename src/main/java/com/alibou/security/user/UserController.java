package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private final UserService service;


    @GetMapping("/")
    public List<User> showUsers(){
        System.out.println("aaaaaaaaa"+ service.getUsers());
        return service.getUsers();
    }

   /* @PostMapping("/addUser")
    public User addUsers(@RequestBody User user) {
        return service.addUser(user);
    }*/

    @GetMapping("/getUser/{id}")
    public User findUser(@PathVariable int id) {
        return service.findUser(id);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }
    @GetMapping("/byEmail")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
    @GetMapping("/search")
    public List<User> rechercheAvancee(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Long phoneNumber

    ) {
        return service.rechercheAvancee(firstname, lastname, email, address, phoneNumber);
    }
    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
