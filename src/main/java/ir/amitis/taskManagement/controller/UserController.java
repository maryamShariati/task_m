package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.UserGetDto;
import ir.amitis.taskManagement.dto.UserPostDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public void save(@RequestBody @Valid UserPostDto userDto){
        userService.save(userDto);
    }

    @GetMapping
    @ResponseBody
    public List<UserGetDto>getAllUser(){
        return userService.getAllUser().stream().map(user -> new UserGetDto(user.getUsername())).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @ResponseBody
    public UserGetDto getById(@PathVariable Long id) throws RecordNotFoundException {
        return new UserGetDto(userService.getById(id).getUsername());
    }




    @PatchMapping("/{id}")
    public void updatePassword(@PathVariable Long id, @RequestParam     @Min(8) String newPassword)
            throws RecordNotFoundException {
        userService.updatePassword(id,newPassword );
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByIId(@PathVariable Long id) throws RecordNotFoundException {
        userService.deleteById(id);
    }



}
