package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Profile;

import ir.amitis.taskManagement.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional( isolation = Isolation.READ_COMMITTED)
    public void save(ProfileDto profileDto){
        profileRepository.save(Profile.profileFromDto(profileDto));
    }


    @Transactional(readOnly = true)
    public List<ProfileDto> getAllProfile() throws RecordNotFoundException {
        List<Profile> profileList= (List<Profile>) profileRepository.findAll();
        if (!profileList.isEmpty()){
            List<ProfileDto> profileDto=new ArrayList<>();
            profileList.forEach(profile->profileDto.add(ProfileDto.profileDto(profile)));
            return profileDto;
        }
        throw new RecordNotFoundException();
    }
//    @Transactional(readOnly = true)
//    public List<Profile>getAll(){
//        return (List<Profile>)profileRepository.findAll();
//    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Profile getProfileById(Long id) throws RecordNotFoundException {
        return profileRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );

    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateNameById(Long id, String name){
        Optional<Profile> task=profileRepository.findById(id);
        if (task.isPresent()) {
            var update = task.get();
            update.setName(String.valueOf(name));
            profileRepository.save(update);
        }
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteProfileById(Long id){
        Optional<Profile> profile =profileRepository.findById(id);
        if (profile.isPresent()){
            var prof=profile.get();
            prof.setDeleted(true);
            profileRepository.save(prof);
        }
    }



}
