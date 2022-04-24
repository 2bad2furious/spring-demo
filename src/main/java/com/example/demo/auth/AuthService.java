package com.example.demo.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
    private final Map<UUID, AuthenticationData> ids = new HashMap<>();
    private final Map<String, String> users = new HashMap<>();

    public AuthService() throws UsernameTaken {
        this.register(RegisterData.of("a@b.cz", "Bábovka1234"));
        this.register(RegisterData.of("b@c.cz", "Bábovka12345"));
    }

    public void register(@NotNull RegisterData data) throws UsernameTaken {
        if (users.containsKey(data.username))
            throw new UsernameTaken();

        users.put(data.username, bCrypt.encode(data.password));
    }

    public Optional<UUID> login(@NotNull LoginData data) {
        var passwordHash = users.get(data.username);
        if (passwordHash == null || !bCrypt.matches(data.password, passwordHash))
            return Optional.empty();

        var authData = new AuthenticationData(/* TODO pass expiration */);
        var id = UUID.randomUUID();
        // TODO check isUnique
        ids.put(id, authData);
        return Optional.of(id);
    }

    public Optional<AuthenticationData> getDataByToken(UUID token) {
        return Optional.ofNullable(ids.get(token));
    }

    public boolean remove(UUID uuid) {
        return this.ids.remove(uuid) != null;
    }
}
