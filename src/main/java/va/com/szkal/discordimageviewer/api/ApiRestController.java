package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.com.szkal.discordimageviewer.security.JwtService;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ApiRestController {

    private final JwtService jwtService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody AuthData authData) {
        return ResponseEntity.ok(new TokenResponse(jwtService.getJwt(authData)));
    }

}
