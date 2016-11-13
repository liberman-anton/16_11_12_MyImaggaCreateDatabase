package tel_ran.imagga.model.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.model.IndexOptions;

import tel_ran.imagga.entities.Image;
import tel_ran.imagga.repo.ImagesRepository;

public class ImagesMongoDB {
	@Autowired
	ImagesRepository imagesMongo;
		
	public boolean addImages(Iterable<Image> images){
		if(images == null || !images.iterator().hasNext())
			return false;
		/*for(Image image : images)
			if(imagesMongo.exists(image.getImage()))
				return false;*/
//		IndexOptions options = new IndexOptions();
//		imagesMongo.createIndex(new Document("tags.tag", 1), options.unique(false));
		imagesMongo.save(images);
		return true;
	}

}
