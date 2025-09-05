
package pe.edu.vallegrande.quality.repository;

import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.quality.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    // Uso de ConcurrentHashMap para mejor rendimiento y thread safety
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public boolean deleteById(String id) {
        return users.remove(id) != null;
    }

    public boolean existsById(String id) {
        return users.containsKey(id);
    }

    public long count() {
        return users.size();
    }
}
