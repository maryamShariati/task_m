package ir.amitis.taskManagement.service;


import ir.amitis.taskManagement.dto.UserPostDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.User;
import ir.amitis.taskManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(UserPostDto userDto){
        userRepository.save(User.userFromDto(userDto));
    }

    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }

    public User getById(Long id) throws RecordNotFoundException {
        return userRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation =Isolation.REPEATABLE_READ )
    public void updatePassword(Long id, String newPassword) throws RecordNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteById(Long id)throws RecordNotFoundException {
        User user =userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        user.setDeleted(true);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndDeletedIsFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));

    }
}
