package tel_ran.imagga.entities;

public class Tag {
Double confidence;
String tag;
public Tag(Double confidence, String tag) {
	super();
	this.confidence = confidence;
	this.tag = tag;
}
public Tag() {
	super();
}
public void setConfidence(Double confidence) {
	this.confidence = confidence;
}
public void setTag(String tag) {
	this.tag = tag;
}
public Double getConfidence() {
	return confidence;
}
public String getTag() {
	return tag;
}
@Override
public String toString() {
	return "Tag [confidence=" + confidence + ", tag=" + tag + "]";
}

}
