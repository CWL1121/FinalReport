package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.Collision;
import ethci.lab.r3feditorback.Entity.projectsModel.World;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollisionRepository extends MongoRepository<Collision,String> {
}
