package va.com.szkal.discordimageviewer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Channel {

    @Id
    private Long id;

    private String name;

    @Column(name = "serverid")
    private Long serverId;

    @OneToMany(fetch = LAZY, mappedBy = "channel", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Image> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Channel channel = (Channel) o;
        return id != null && Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
