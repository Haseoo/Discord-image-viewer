package va.com.szkal.discordimageviewer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "discordimages")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "messageid")
    private Long messageId;
    private String username;
    @Column(name = "imgurl")
    private String imageUrl;
    @Column(name = "sendtime")
    private LocalDateTime sendTime;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "channelid")
    @ToString.Exclude
    private Channel channel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Image image = (Image) o;
        return id != null && Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
