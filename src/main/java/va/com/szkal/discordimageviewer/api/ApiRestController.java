package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import va.com.szkal.discordimageviewer.domain.Image;
import va.com.szkal.discordimageviewer.domain.ImageService;
import va.com.szkal.discordimageviewer.security.JwtService;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ApiRestController {

    private final JwtService jwtService;
    private final ImageService imageService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody AuthData authData) {
        return ResponseEntity.ok(new TokenResponse(jwtService.getJwt(authData)));
    }

    @PostMapping("/add")
    public ResponseEntity<Image> addImage(@RequestBody StoreImageRequest request) {
        return new ResponseEntity<>(imageService.add(request), HttpStatus.CREATED);
    }

}
