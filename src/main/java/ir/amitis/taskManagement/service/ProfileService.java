package ir.amitis.taskManagement.service;

import ir.amitis.taskManagement.dto.ProfileDto;
import ir.amitis.taskManagement.dto.UpdateProfileDto;
import ir.amitis.taskManagement.exception.RecordNotFoundException;
import ir.amitis.taskManagement.model.Profile;

import ir.amitis.taskManagement.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepository profileRepository;


    @Transactional(readOnly = true)
    public List<Profile>getAll()throws RecordNotFoundException{
        return (List<Profile>)profileRepository.findAll();
    }


//    @Transactional(isolation = Isolation.READ_COMMITTED)
//    public Profile getProfileById(Long id) throws RecordNotFoundException {
//        return profileRepository.findById( id ).orElseThrow( () -> new RecordNotFoundException( id ) );
//
//    }

    public Profile getProfileByUsername(String username) throws RecordNotFoundException {
        return profileRepository.findProfileByUsername(username).orElseThrow(RecordNotFoundException::new);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateProfile(UpdateProfileDto updateProfileDto) throws RecordNotFoundException {
        Optional<Profile> optionalProfile = Optional.ofNullable(profileRepository.findProfileByUsername(updateProfileDto.username())
                .orElseThrow(RecordNotFoundException::new));
        if (optionalProfile.isPresent()) {
            var profile = optionalProfile.get();
            profile.setName(updateProfileDto.name());
            profile.setSurname(updateProfileDto.surname());
            profile.setBirthday(updateProfileDto.birthday());
            profile.setEmail(updateProfileDto.email());
            profile.setSex(updateProfileDto.sex());
            profile.setMobileNumber(updateProfileDto.mobileNumber());
            profileRepository.save(profile);
        }}

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
