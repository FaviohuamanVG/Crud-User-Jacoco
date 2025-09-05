package pe.edu.vallegrande.quality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.vallegrande.quality.dto.UserRequest;
import pe.edu.vallegrande.quality.exception.UserNotFoundException;
import pe.edu.vallegrande.quality.exception.UserValidationException;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserRequest validUserRequest;
    private User testUser;

    @BeforeEach
    void setUp() {
        validUserRequest = new UserRequest("John Doe", "john@example.com", 25);
        testUser = new User("1", "John Doe", "john@example.com", 25);
    }

    @Test
    void getAllUsers_ShouldReturnSortedUsers() {
        // Given
        List<User> users = Arrays.asList(
                new User("2", "Charlie", "charlie@example.com", 30),
                new User("1", "Alice", "alice@example.com", 25)
        );
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Charlie", result.get(1).getName());
        verify(userRepository).findAll();
    }

    @Test
    void createUser_WithValidRequest_ShouldCreateUser() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // When
        User result = userService.createUser(validUserRequest);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals(25, result.getAge());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_WithNullName_ShouldThrowValidationException() {
        // Given
        UserRequest invalidRequest = new UserRequest(null, "john@example.com", 25);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_WithEmptyName_ShouldThrowValidationException() {
        // Given
        UserRequest invalidRequest = new UserRequest("  ", "john@example.com", 25);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_WithInvalidEmail_ShouldThrowValidationException() {
        // Given
        UserRequest invalidRequest = new UserRequest("John", "invalid-email", 25);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_WithNegativeAge_ShouldThrowValidationException() {
        // Given
        UserRequest invalidRequest = new UserRequest("John", "john@example.com", -5);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_WithNullAge_ShouldDefaultToZero() {
        // Given
        UserRequest requestWithNullAge = new UserRequest("John", "john@example.com", null);
        User expectedUser = new User("1", "John", "john@example.com", 0);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        // When
        User result = userService.createUser(requestWithNullAge);

        // Then
        assertEquals(0, result.getAge());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUserById_WithExistingId_ShouldReturnUser() {
        // Given
        when(userRepository.findById("1")).thenReturn(Optional.of(testUser));

        // When
        User result = userService.getUserById("1");

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).findById("1");
    }

    @Test
    void getUserById_WithNonExistingId_ShouldThrowNotFoundException() {
        // Given
        when(userRepository.findById("999")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("999"));
        verify(userRepository).findById("999");
    }

    @Test
    void deleteUser_WithExistingId_ShouldDeleteUser() {
        // Given
        when(userRepository.existsById("1")).thenReturn(true);

        // When
        assertDoesNotThrow(() -> userService.deleteUser("1"));

        // Then
        verify(userRepository).existsById("1");
        verify(userRepository).deleteById("1");
    }

    @Test
    void deleteUser_WithNonExistingId_ShouldThrowNotFoundException() {
        // Given
        when(userRepository.existsById("999")).thenReturn(false);

        // When & Then
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser("999"));
        verify(userRepository).existsById("999");
        verify(userRepository, never()).deleteById(anyString());
    }
}
