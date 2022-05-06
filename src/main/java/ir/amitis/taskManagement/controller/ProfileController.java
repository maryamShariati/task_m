package ir.amitis.taskManagement.controller;

import ir.amitis.taskManagement.dto.NameDto;
import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public void save(@RequestBody @Valid ProfileDto profileDto){
        profileService.save(profileDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        profileService.deleteProfileById(id);
    }


    @GetMapping
    @ResponseBody
    public List<ProfileDto> getAllProfile() throws RecordNotFoundException {
        return profileService.getAllProfile();
    }

    @PutMapping("/update/{id}")
    public void updateName(@PathVariable Long id,@RequestBody @Valid NameDto nameDto){
        profileService.updateNameById(id, nameDto);
    }

}

