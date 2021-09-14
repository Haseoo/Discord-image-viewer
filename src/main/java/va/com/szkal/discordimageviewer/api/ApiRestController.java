package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/channel/{id}")
    public ResponseEntity<Object> updateChannelName(@PathVariable long id,
                                                    @RequestBody ChannelRequest request) {
        imageService.renameChannel(id, request.getNewName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/channel/{id}")
    public ResponseEntity<Object> deleteChannel(@PathVariable long id) {
        imageService.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }

}
