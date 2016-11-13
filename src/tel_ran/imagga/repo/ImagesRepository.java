package tel_ran.imagga.repo;

import org.springframework.data.repository.CrudRepository;

import tel_ran.imagga.entities.Image;


public interface ImagesRepository extends CrudRepository<Image, String> {

}
