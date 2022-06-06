package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Profile;
import ir.amitis.taskManagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public void save(@RequestBody @Valid ProfileDto profileDto){
        profileService.save(profileDto);
    }


    @GetMapping
    @ResponseBody
    public List<ProfileDto> getAllProfile() throws RecordNotFoundException {
        return profileService.getAll().stream()
                .map(profile -> new ProfileDto(profile.getName(),profile.getSurname()
                        ,profile.getSex(),profile.getBirthday(),profile.getEmail()
                        ,profile.getMobileNumber())).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @Validated
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) throws RecordNotFoundException {
        Profile profile = profileService.getProfileById( id );
        return ResponseEntity.ok().body( profile );
    }

    @PutMapping("/update/{id}/{name}")
    public void updateName(@PathVariable Long id,@PathVariable String name){
        profileService.updateNameById(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        profileService.deleteProfileById(id);
    }


}

