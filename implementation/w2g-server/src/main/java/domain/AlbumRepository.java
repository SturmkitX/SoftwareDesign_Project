package domain;

import model.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
    List<Album> findAll();

    //@Query("from Album where title = :title")
    Album findByTitle(String title);
}
