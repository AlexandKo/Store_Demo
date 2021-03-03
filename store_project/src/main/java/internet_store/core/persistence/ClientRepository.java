package internet_store.core.persistence;

import internet_store.core.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

    List<Client> findBySurname(String surname);

    List<Client> findByPhoneNumber(String phone);

    List<Client> findByEmail(String email);

    boolean existsByName(String name);

    @Query(value = "SELECT * FROM Client limit ? offset ?", nativeQuery = true)
    List<Client> getLimitsClientRecords(int limit, int offset);
}