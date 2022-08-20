package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.UserGetDto;
import ir.amitis.taskManagement.dto.UserPostDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void save(@RequestBody @Valid UserPostDto userDto){
        userService.save(userDto);
    }

    @GetMapping
    @ResponseBody
    @Secured("ROLE_GET_USER")
    public List<UserGetDto> getAllUser(){
        return userService.getAllUser().stream().map(user -> new UserGetDto(user.getUsername())).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @ResponseBody
    @Secured("ROLE_GET_USER")
    public UserGetDto getById(@PathVariable Long id) throws RecordNotFoundException {
        return new UserGetDto(userService.getById(id).getUsername());
    }


    @PatchMapping("/{id}")
    @Secured("ROLE_UPDATE_USER")
    public void updatePassword(@PathVariable Long id, @RequestParam     @Min(8) String newPassword)
            throws RecordNotFoundException {
        userService.updatePassword(id,newPassword );
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_DELETE_USER')")
//    @Secured("ROLE_DELETE_USER")
    public void deleteByIId(@PathVariable Long id) throws RecordNotFoundException {
        userService.deleteById(id);
    }



}
