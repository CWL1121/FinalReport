package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.Material;
import ethci.lab.r3feditorback.Entity.projectsModel.World;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepository extends MongoRepository<Material,String> {
}
