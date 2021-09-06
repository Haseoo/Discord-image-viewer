package va.com.szkal.discordimageviewer.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
public class Channel {

    @Id
    private Long id;

    private String name;

    @Column(name = "serverid")
    private Long serverId;

    @OneToMany(fetch = LAZY, mappedBy = "channel", cascade = ALL, orphanRemoval = true)
    private List<Image> images;
}
