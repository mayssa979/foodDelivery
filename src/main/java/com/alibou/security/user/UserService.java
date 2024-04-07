package com.alibou.security.user;

import com.alibou.security.command.Command;
import com.alibou.security.command.CommandRepository;
import com.alibou.security.menu.Menu;
import com.alibou.security.token.Token;
import com.alibou.security.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final CommandRepository cmdRepository;
    private final TokenRepository tokenRepo;

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getUsers() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> {
                    user.setPassword(null); // Set password to null (or any other placeholder value)
                    return user;
                })
                .collect(Collectors.toList());
    }

   /* public User addUser(User user) {
        user.setRole(Role.valueOf("CLIENT"));
        return repository.save(user);
    }*/

    public User findUser(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteUser(int id) {
        Optional<User> optionalUser = repository.findById(id);
        if ( optionalUser.isPresent()) {
            List<Command> commands = cmdRepository.findByUserId(optionalUser.get().getId());
            for(Command command : commands){
                cmdRepository.deleteById(command.getId());
            }
            List<Token> users = tokenRepo.findByUserId(optionalUser.get().getId());
            for(Token user : users){
                tokenRepo.deleteById(user.getId());
            }

            repository.deleteById(id);
            return "Deleted user with ID:"+id;
        }
        else {
            return "User with ID: "+id+" does not exist!!";
        }
    }

    public User updateUser(User user) {
        User existuser = repository.findById(user.getId()).orElse(null);
        existuser.setAddress(user.getAddress());
        existuser.setEmail(user.getEmail());
        existuser.setPassword(user.getPassword());
        existuser.setFirstname(user.getFirstname());
        existuser.setLastname(user.getLastname());
        existuser.setPhoneNumber(user.getPhoneNumber());
        existuser.setRole(Role.valueOf("CLIENT"));
        return repository.save(existuser);
    }
    public List<User> rechercheAvancee(String firstname, String lastname, String address, String email, Long phoneNumber) {
        return repository.rechercheAvancee(firstname, lastname, address,email, phoneNumber);
    }
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }


}
