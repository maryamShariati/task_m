package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.NameDto;
import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Profile;

import ir.amitis.taskManagement.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;


    public void save(ProfileDto profileDto){
        profileRepository.save(Profile.profileFromDto(profileDto));
    }

    public List<ProfileDto> getAllProfile() throws RecordNotFoundException {
        List<Profile> profileList= (List<Profile>) profileRepository.findAll();
        if (!profileList.isEmpty()){
            List<ProfileDto> profileDto=new ArrayList<>();
            profileList.forEach(profile->profileDto.add(ProfileDto.profileDto(profile)));
            return profileDto;
        }
        throw new RecordNotFoundException();
    }

    public void updateNameById(Long id, NameDto nameDto){
        Optional<Profile> task=profileRepository.findById(id);
        if (task.isPresent()) {
            var update = task.get();
            update.setName(String.valueOf(nameDto));
            profileRepository.save(update);
        }
    }

    public void deleteProfileById(Long id){
        Optional<Profile> profile =profileRepository.findById(id);
        if (profile.isPresent()){
            var prof=profile.get();
            prof.setDeleted(true);
            profileRepository.save(prof);
        }
    }



}
