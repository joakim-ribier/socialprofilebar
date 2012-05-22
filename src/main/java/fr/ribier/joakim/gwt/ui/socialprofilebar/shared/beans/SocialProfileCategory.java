package fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans;

import com.google.common.base.Objects;

public class SocialProfileCategory {

	private final String name;
	private final int weight;

	public SocialProfileCategory(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public String getTitle() {
		return name;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode(){
		return Objects.hashCode(name);
	}
	
	@Override
	public boolean equals(Object object){
		if (object instanceof SocialProfileCategory) {
			SocialProfileCategory that = (SocialProfileCategory) object;
			return Objects.equal(this.name, that.name);
		}
		return false;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
			.add("name", name)
			.add("weight", weight)
			.toString();
	}
}
