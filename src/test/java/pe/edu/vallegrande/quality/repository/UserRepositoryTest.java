package pe.edu.vallegrande.quality.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.vallegrande.quality.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        testUser = new User("1", "John Doe", "john@example.com", 25);
    }

    @Test
    void save_ShouldStoreUser() {
        // When
        User savedUser = userRepository.save(testUser);

        // Then
        assertNotNull(savedUser);
        assertEquals(testUser.getId(), savedUser.getId());
        assertEquals(testUser.getName(), savedUser.getName());
        assertTrue(userRepository.existsById("1"));
    }

    @Test
    void findById_WithExistingId_ShouldReturnUser() {
        // Given
        userRepository.save(testUser);

        // When
        Optional<User> result = userRepository.findById("1");

        // Then
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmpty() {
        // When
        Optional<User> result = userRepository.findById("999");

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Given
        User user2 = new User("2", "Jane Doe", "jane@example.com", 30);
        userRepository.save(testUser);
        userRepository.save(user2);

        // When
        List<User> result = userRepository.findAll();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void deleteById_WithExistingId_ShouldRemoveUser() {
        // Given
        userRepository.save(testUser);
        assertTrue(userRepository.existsById("1"));

        // When
        boolean deleted = userRepository.deleteById("1");

        // Then
        assertTrue(deleted);
        assertFalse(userRepository.existsById("1"));
    }

    @Test
    void deleteById_WithNonExistingId_ShouldReturnFalse() {
        // When
        boolean deleted = userRepository.deleteById("999");

        // Then
        assertFalse(deleted);
    }

    @Test
    void existsById_WithExistingId_ShouldReturnTrue() {
        // Given
        userRepository.save(testUser);

        // When
        boolean exists = userRepository.existsById("1");

        // Then
        assertTrue(exists);
    }

    @Test
    void existsById_WithNonExistingId_ShouldReturnFalse() {
        // When
        boolean exists = userRepository.existsById("999");

        // Then
        assertFalse(exists);
    }

    @Test
    void count_ShouldReturnCorrectNumber() {
        // Given
        assertEquals(0, userRepository.count());

        userRepository.save(testUser);
        assertEquals(1, userRepository.count());

        User user2 = new User("2", "Jane", "jane@example.com", 30);
        userRepository.save(user2);

        // When
        long count = userRepository.count();

        // Then
        assertEquals(2, count);
    }
}
