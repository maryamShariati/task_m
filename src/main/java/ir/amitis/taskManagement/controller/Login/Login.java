package ir.amitis.taskManagement.controller.Login;


import ir.amitis.taskManagement.config.security.JwtTokenUtil;
import ir.amitis.taskManagement.dto.UserPostDto;
import ir.amitis.taskManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class Login {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("login")
    @MethodDurationLog
    public ResponseEntity<Object> login(@RequestBody @Valid UserPostDto request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.accepted().body(jwtTokenUtil.generateAccessToken(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
