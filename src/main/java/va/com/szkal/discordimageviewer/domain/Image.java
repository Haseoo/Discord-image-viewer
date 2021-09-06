package va.com.szkal.discordimageviewer.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Table(name = "discordimages")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "imgurl")
    private String imageUrl;
    @Column(name = "sendtime")
    private LocalDateTime sendTime;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "channelid")
    private Channel channel;
}
