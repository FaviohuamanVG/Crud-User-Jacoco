
package pe.edu.vallegrande.quality.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.quality.dto.UserRequest;
import pe.edu.vallegrande.quality.exception.UserNotFoundException;
import pe.edu.vallegrande.quality.exception.UserValidationException;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        log.info("Retrieving all users");
        List<User> users = userRepository.findAll();
        // Ordenamiento consistente usando Comparator
        users.sort(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER));
        log.info("Retrieved {} users", users.size());
        return users;
    }

    public User createUser(UserRequest userRequest) {
        log.info("Creating user with name: {}", userRequest.getName());
        validateUserRequest(userRequest);
        
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userRequest.getName().trim());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge() != null ? userRequest.getAge() : 0);
        
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    public User getUserById(String id) {
        log.info("Searching for user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.warn("User not found with ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return user.get();
    }

    public void deleteUser(String id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent user with ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
    }

    private void validateUserRequest(UserRequest userRequest) {
        if (userRequest.getName() == null || userRequest.getName().trim().isEmpty()) {
            throw new UserValidationException("Name is required");
        }
        if (userRequest.getEmail() == null || !userRequest.getEmail().contains("@")) {
            throw new UserValidationException("Valid email is required");
        }
        if (userRequest.getAge() != null && userRequest.getAge() < 0) {
            throw new UserValidationException("Age must be positive");
        }
    }
}
