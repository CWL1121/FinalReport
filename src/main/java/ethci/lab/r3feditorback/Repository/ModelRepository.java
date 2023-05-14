package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.ObjModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelRepository extends MongoRepository<ObjModel,String> {
}
