package lk.ijse.gdse.springsecurity.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lk.ijse.gdse.springsecurity.Dto.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    // Exception Handler for username not found Exception
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponce handleUserNameNotFoundException
    (UsernameNotFoundException ex){
        return new ApiResponce(404, "User Not Found",null);
    }
    // Exception Handler for Bad Credentials Exception
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponce handleBadCredentials(BadCredentialsException ex){
        return new ApiResponce(400, "Bad Credentials",null);
    }
    // Exception Handler for JWT Token Expired Exception
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponce handleJWTTokenExpiredException(ExpiredJwtException ex){
        return new ApiResponce(401, "JWT Token Expired",null);
    }
    // Exception Handler for all other exceptions
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponce handleAllExceptions(RuntimeException ex){
        return new ApiResponce(500, "Internal Server Error",null);
    }
}
