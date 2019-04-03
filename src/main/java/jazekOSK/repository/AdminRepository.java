package jazekOSK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jazekOSK.entity.Admin;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin,Integer> {


}
