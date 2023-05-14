package ethci.lab.r3feditorback.Repository;

import ethci.lab.r3feditorback.Entity.projectsModel.World;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorldRepository  extends MongoRepository<World,String> {
    List<World> findWorldByOwner(String owner);
}
