package fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;


public class SocialProfile {

	public static class Builder {
		private String title;
		private String img;
		private String url;
		private SocialProfileCategory category;
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder img(String img) {
			this.img = img;
			return this;
		}
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder category(SocialProfileCategory category) {
			this.category = category;
			return this;
		}
		
		public SocialProfile build() {
			Preconditions.checkNotNull(title);
			Preconditions.checkNotNull(category);
			return new SocialProfile(title, img, url, category);
		}
	}
	
	private final String title;
	private final String img;
	private final String url;
	private final SocialProfileCategory category;

	private SocialProfile(String title, String img, String url, SocialProfileCategory category) {
		this.title = title;
		this.img = img;
		this.url = url;
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getImg() {
		return img;
	}
	
	public String getUrl() {
		return url;
	}
	
	public SocialProfileCategory getCategory() {
		return category;
	}

	@Override
	public int hashCode(){
		return Objects.hashCode(title, img, url, category);
	}
	
	@Override
	public boolean equals(Object object){
		if (object instanceof SocialProfile) {
			SocialProfile that = (SocialProfile) object;
			return Objects.equal(this.title, that.title)
				&& Objects.equal(this.img, that.img)
				&& Objects.equal(this.url, that.url)
				&& Objects.equal(this.category, that.category);
		}
		return false;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
			.add("title", title)
			.add("img", img)
			.add("url", url)
			.add("category", category)
			.toString();
	}
}
