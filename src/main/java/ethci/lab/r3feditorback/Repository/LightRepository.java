package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.Light;
import ethci.lab.r3feditorback.Entity.projectsModel.World;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LightRepository extends MongoRepository<Light,String> {
}
