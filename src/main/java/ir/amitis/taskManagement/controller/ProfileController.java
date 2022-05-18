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
    @GetMapping("/{id}")
    @Validated
    public ResponseEntity<Profile> getProfileByID(@PathVariable Long id) throws RecordNotFoundException {
        Profile profile = profileService.getProfileById( id );
        return ResponseEntity.ok().body( profile );
    }

    @PutMapping("/update/{id}/{name}")
    public void updateName(@PathVariable Long id,@PathVariable String name){
        profileService.updateNameById(id, name);
    }

}

