package ir.amitis.taskManagement.controller;

import ir.amitis.taskManagement.dto.PasswordDto;
import ir.amitis.taskManagement.dto.UserSaveDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void save(@RequestBody @Valid UserSaveDto userDto){
        userService.save(userDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteByIId(@PathVariable Long id) throws RecordNotFoundException {
        userService.deleteById(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public void getById(@PathVariable Long id) throws RecordNotFoundException {
        userService.getById(id);
    }


    @PatchMapping("/{id}")
    public void updatePassword(@PathVariable Long id, @RequestBody @Valid PasswordDto newPassword)
            throws RecordNotFoundException {
        userService.updatePassword(id,newPassword );
    }


}
