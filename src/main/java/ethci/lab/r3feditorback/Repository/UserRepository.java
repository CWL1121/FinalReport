package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    User findByName(String name);
}
