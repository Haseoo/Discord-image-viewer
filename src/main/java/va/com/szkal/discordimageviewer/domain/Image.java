package va.com.szkal.discordimageviewer.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "discordimages")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serverid")
    private Long serverId;
    private String username;
    private String channel;
    @Column(name = "imgurl")
    private String imageUrl;
    @Column(name = "sendtime")
    private LocalDateTime sendTime;
}
