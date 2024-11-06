package sw.goku.servidor.securiry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sw.goku.servidor.securiry.auth.dto.AuthResponse;
import sw.goku.servidor.securiry.auth.dto.LoginRequest;
import sw.goku.servidor.securiry.auth.dto.RegisterRequest;
import sw.goku.servidor.securiry.jwt.JwtService;
import sw.goku.servidor.securiry.repository.UserRepository;
import sw.goku.servidor.securiry.repository.user.Role;
import sw.goku.servidor.securiry.repository.user.Users;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetail = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetail);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Users user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
