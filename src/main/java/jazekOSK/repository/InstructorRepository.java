package jazekOSK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jazekOSK.entity.Instructor;

@Repository
@Transactional
public interface InstructorRepository extends JpaRepository<Instructor,Integer>{

}
