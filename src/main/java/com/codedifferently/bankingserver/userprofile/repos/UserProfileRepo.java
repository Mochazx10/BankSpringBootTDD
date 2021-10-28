package com.codedifferently.bankingserver.userprofile.repos;

import com.codedifferently.bankingserver.userprofile.models.UserProfile;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserProfileRepo extends CrudRepository<UserProfile, Integer> {
    Optional<UserProfile> findByEmail(String email);
}
