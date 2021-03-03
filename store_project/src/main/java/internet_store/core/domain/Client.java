package internet_store.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "client", schema = "store")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
}