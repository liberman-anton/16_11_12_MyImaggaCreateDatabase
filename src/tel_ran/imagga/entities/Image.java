package tel_ran.imagga.entities;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {
	@Id
	String image;
	Tag[] tags;
	public Image(String image, Tag[] tags) {
		super();
		this.image = image;
		this.tags = tags;
	}
	public Image() {
		super();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Tag[] getTags() {
		return tags;
	}
	public void setTags(Tag[] tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Image [image=" + image + ", tags=" + Arrays.toString(tags) + "]";
	}
	
}
