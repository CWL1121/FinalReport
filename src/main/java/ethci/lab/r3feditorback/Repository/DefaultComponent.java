package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.defaultComponent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DefaultComponent extends MongoRepository<defaultComponent,String> {
}
