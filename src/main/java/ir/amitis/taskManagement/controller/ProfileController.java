package ir.amitis.taskManagement.controller;


import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Profile;
import ir.amitis.taskManagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @GetMapping("/get")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public List<ProfileDto> getAllProfile() throws RecordNotFoundException {
        return profileService.getAll().stream()
                .map(profile -> new ProfileDto(profile.getName(),profile.getSurname()
                        ,profile.getSex(),profile.getBirthday(),profile.getEmail()
                        ,profile.getMobileNumber())).collect(Collectors.toList());

    }

    @GetMapping("/get/{id}")
    @Secured("ROLE_GET_PROFILE")
    @Validated
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) throws RecordNotFoundException {
        var prof=profileService.getProfileById(id);
        ProfileDto profileDto=new ProfileDto(prof.getName()
        ,prof.getSurname(),prof.getSex(),prof.getBirthday()
        ,prof.getEmail(),prof.getMobileNumber());
        return ResponseEntity.ok().body(profileDto);
    }

    @GetMapping("/get/byUsername")
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    public ProfileDto getProfileByUsername() throws RecordNotFoundException {
        var profile = profileService.getProfileByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ProfileDto(profile.getName(), profile.getSurname(), profile.getSex()
                , profile.getBirthday(), profile.getEmail(), profile.getMobileNumber());
    }


    @PutMapping("/update/{id}/{name}")
    @Secured("ROLE_UPDATE_PROFILE")
    public void updateName(@PathVariable Long id,@PathVariable String name){
        profileService.updateNameById(id, name);
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_PROFILE")
    public void deleteById(@PathVariable Long id){
        profileService.deleteProfileById(id);
    }


}

