package fr.ribier.joakim.gwt.ui.socialprofilebar.client;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;


public class UiBarConfiguration {

	public enum Align {
		VERTICAL,
		HORIZONTAL;
	}
	
	public static class Builder {
		private int width = 64;
		private int height = -1;
		private Align disposition;
		private boolean displayTitle;
		private boolean displayCategory;
		private boolean fork;
		private String forkMeImg;
		
		public Builder() {
			width = 64;
			height = -1;
			displayTitle = true;
			displayCategory = true;
			fork = false;
		}
		
		public Builder imgWidthPx(int size) {
			width = size;
			return this;
		}
		
		public Builder imgHeightPx(int size) {
			height = size;
			return this;
		}
		
		public Builder disposition(Align align) {
			this.disposition = align;
			return this;
		}
		
		public Builder showTitle() {
			this.displayTitle = true;
			return this;
		}
		
		public Builder hiddenTitle() {
			this.displayTitle = false;
			return this;
		}
		
		public Builder showCategory() {
			this.displayCategory = true;
			return this;
		}
		
		public Builder hiddenCategory() {
			this.displayCategory = false;
			return this;
		}
		
		public Builder forkMeOnGitHub() {
			this.fork = true;
			return this;
		}
		
		public Builder forkMeImg(String img) {
			this.forkMeImg = img;
			return this;
		}
		
		public UiBarConfiguration build() {
			if (disposition == null) {
				disposition = Align.VERTICAL;
			}
			if (fork) {
				Preconditions.checkArgument(!Strings.isNullOrEmpty(forkMeImg));	
			}
			return new UiBarConfiguration(
					width, height, disposition, displayTitle, displayCategory, fork, forkMeImg);
		}
	}
	
	private final int width;
	private final Align disposition;
	private final boolean displayTitle;
	private final int height;
	private final boolean displayCategory;
	private final boolean fork;
	private final String forkMeImg;

	public UiBarConfiguration(int width, int height, Align disposition, boolean displayTitle, 
			boolean displayCategory, boolean fork, String forkMeImg) {
		
		this.width = width;
		this.height = height;
		this.disposition = disposition;
		this.displayTitle = displayTitle;
		this.displayCategory = displayCategory;
		this.fork = fork;
		this.forkMeImg = forkMeImg;
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Align getDisposition() {
		return disposition;
	}
	
	public boolean isDisplayTitle() {
		return displayTitle;
	}

	public boolean isDisplayCategory() {
		return displayCategory;
	}
	
	public boolean isForkMeOnGitHub() {
		return fork;
	}

	public String getForkMeImg() {
		return forkMeImg;
	}
}
