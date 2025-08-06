package lk.ijse.gdse.springsecurity.controller;

import lk.ijse.gdse.springsecurity.Dto.ApiResponce;
import lk.ijse.gdse.springsecurity.Dto.AuthDto;
import lk.ijse.gdse.springsecurity.Dto.AuthResponseDto;
import lk.ijse.gdse.springsecurity.Dto.RegisterDto;
import lk.ijse.gdse.springsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
        try {
            AuthResponseDto auth = authService.authenticate(authDTO);
            return ResponseEntity.ok(new ApiResponce(200, "OK", auth));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponce(401, "Invalid username or password", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponce(500, "Server error", null));
        }
    }
}
