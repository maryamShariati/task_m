package ir.amitis.taskManagement.service;


import ir.amitis.taskManagement.dto.PasswordDto;
import ir.amitis.taskManagement.dto.UserGetDto;
import ir.amitis.taskManagement.dto.UserSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.User;
import ir.amitis.taskManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public void save(UserSaveDto userDto){
        userRepository.save(User.userFromDto(userDto));

    }

    public void deleteById(Long id)throws RecordNotFoundException {
        User user =userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        user.setDeleted(true);
        userRepository.save(user);
    }

    public void updatePassword(Long id, PasswordDto newPassword) throws RecordNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        user.setPassword(newPassword.password());
        userRepository.save(user);
    }

    public UserGetDto getById(Long id) throws RecordNotFoundException {
        var user=userRepository.findById(id).orElseThrow(()->new RecordNotFoundException());
        var userGetDto= UserGetDto.userGetDto(user);
        return userGetDto;
    }




}
