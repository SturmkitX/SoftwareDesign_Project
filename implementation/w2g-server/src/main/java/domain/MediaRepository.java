package domain;

import model.Media;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MediaRepository extends CrudRepository<Media, Integer> {
    List<Media> findAll();

}
