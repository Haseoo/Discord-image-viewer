package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.com.szkal.discordimageviewer.security.JwtService;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ApiRestController {

    private final JwtService jwtService;

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> generateToken() {
        return ResponseEntity.ok(new TokenResponse(jwtService.getJwt()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addImage(StoreImageRequest request) {
        return ResponseEntity.noContent().build();
    }
}
