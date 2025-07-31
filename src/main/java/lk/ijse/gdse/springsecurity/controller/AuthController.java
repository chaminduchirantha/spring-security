package lk.ijse.gdse.springsecurity.controller;

import lk.ijse.gdse.springsecurity.Dto.ApiResponce;
import lk.ijse.gdse.springsecurity.Dto.AuthDto;
import lk.ijse.gdse.springsecurity.Dto.RegisterDto;
import lk.ijse.gdse.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponce> registerUser(
            @RequestBody RegisterDto registerDTO) {
        return ResponseEntity.ok(
                new ApiResponce(
                        200,
                        "User registered successfully",
                        authService.register(registerDTO)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponce> login(@RequestBody AuthDto authDTO) {
        return ResponseEntity.ok(new ApiResponce(200,
                "OK", authService.authenticate(authDTO)));
    }
}
