package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "session", schema = "store")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id")
    private String sessionId;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}