package lk.ijse.gdse.springsecurity.service;

import lk.ijse.gdse.springsecurity.Dto.AuthDto;
import lk.ijse.gdse.springsecurity.Dto.AuthResponseDto;
import lk.ijse.gdse.springsecurity.Dto.RegisterDto;
import lk.ijse.gdse.springsecurity.entity.Role;
import lk.ijse.gdse.springsecurity.entity.User;
import lk.ijse.gdse.springsecurity.repo.UserRepo;
import lk.ijse.gdse.springsecurity.utill.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDto authenticate(AuthDto authDto) {
        User user = userRepo.findByUsername(authDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        String token = jwtUtil.generateToken(authDto.getUsername());

        System.out.println("Login successful for user: " + authDto.getUsername());
        System.out.println(" Role: " + user.getRole());

        return new AuthResponseDto(token);
    }



    public String register(RegisterDto registerDTO) {
        if (userRepo.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already exist");
        }
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepo.save(user);
        return "User registered successfully";
    }

}
