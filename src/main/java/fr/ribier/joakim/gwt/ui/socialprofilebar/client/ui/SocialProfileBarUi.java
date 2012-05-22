package fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.ribier.joakim.gwt.ui.socialprofilebar.client.UiBarConfiguration;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.profile.SocialProfileWidget;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfileCategory;

public abstract class SocialProfileBarUi extends Composite {

	protected final Map<SocialProfileCategory, Integer> categoriesIndex;
	private final UiBarConfiguration uiBarConfig;
	
	public abstract void fill(Collection<SocialProfile> socialProfiles);
	public abstract void fill(Collection<SocialProfile> socialProfiles, SocialProfile socialProfile);
	
	protected abstract void init();
	protected abstract void addOrUpdateIndex(Collection<SocialProfile> socialProfiles, boolean add);
	protected abstract String socialProfileStyleName();
	protected abstract String socialProfileCategoryStyleName();
	
	public SocialProfileBarUi(UiBarConfiguration uiBarConfig) {
		this.uiBarConfig = uiBarConfig;
		this.categoriesIndex = Maps.newHashMap();
		init();
	}
	
	protected void updatesIndex(Collection<SocialProfile> socialProfiles) {
		categoriesIndex.clear();
		addOrUpdateIndex(socialProfiles, false);
	}

	protected SimplePanel createSocialProfileWidget(SocialProfile socialProfile) {
		SimplePanel simplePanel = new SimplePanel();
		simplePanel.setStyleName(socialProfileStyleName());
		SocialProfileWidget socialProfileWidget = 
				new SocialProfileWidget(socialProfile, uiBarConfig.isDisplayTitle(), 
						uiBarConfig.getWidth(), uiBarConfig.getHeight());
		simplePanel.add(socialProfileWidget);
		return simplePanel;
	}
	
	protected HTML createTitleCategoryWidget(SocialProfileCategory category) {
		HTML html = new HTML(category.getTitle());
		html.setStyleName(socialProfileCategoryStyleName());
		html.addStyleName("social-profile-bar-category");
		return html;
	}
	
	protected boolean displayCategory() {
		return uiBarConfig.isDisplayCategory();
	}
	
	protected boolean displayForkMeImg() {
		return uiBarConfig.isForkMeOnGitHub();
	}
	
	protected String forkMeImg() {
		return uiBarConfig.getForkMeImg();
	}
	
	protected int initializeIndex() {
		int index = -1;
		if (displayForkMeImg()) {
			index += 1;
		}
		return index;
	}
}
