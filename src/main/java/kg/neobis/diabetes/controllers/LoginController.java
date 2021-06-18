package kg.neobis.diabetes.controllers;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kg.neobis.diabetes.models.security.AuthenticationRequest;
import kg.neobis.diabetes.models.security.AuthenticationResponse;
import kg.neobis.diabetes.services.JwtUtil;
import kg.neobis.diabetes.services.impl.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final MyUserServiceImpl myUserServiceImpl;
    private final JwtUtil jwtTokenUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, MyUserServiceImpl myUserServiceImpl, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserServiceImpl = myUserServiceImpl;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // Authentication with jwt token
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get JWT token"),
            @ApiResponse(code = 500, message = "Toke is expired")
    })
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails userDetails = myUserServiceImpl
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }




}