package fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.bar;

import java.util.Collection;

import com.google.common.collect.Iterables;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import fr.ribier.joakim.gwt.ui.socialprofilebar.client.UiBarConfiguration;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.SocialProfileBarUi;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfileCategory;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils.CollectionUtils;

public class SocialProfileVerticalBar extends SocialProfileBarUi {

	private static SocialProfileVerticalBarUiBinder uiBinder = GWT.create(SocialProfileVerticalBarUiBinder.class);
	interface SocialProfileVerticalBarUiBinder extends UiBinder<Widget, SocialProfileVerticalBar> {}

	@UiField FlowPanel panel;
	
	public SocialProfileVerticalBar(UiBarConfiguration barUIConfig) {
		super(barUIConfig);
	}

	@Override
	public void init() {
		initWidget(uiBinder.createAndBindUi(this));
		initializeVerticalBar();
	}

	private void initializeVerticalBar() {
		if (displayForkMeImg()) {
			SimplePanel forkMePanel = new SimplePanel();
			forkMePanel.add(new Image(forkMeImg()));
			forkMePanel.setStyleName("social-profile-vertical-bar-fork-me");
			this.panel.add(forkMePanel);
		}
	}

	@Override
	public void fill(Collection<SocialProfile> socialProfiles) {
		addOrUpdateIndex(socialProfiles, true);
	}
	
	@Override
	protected void addOrUpdateIndex(Collection<SocialProfile> socialProfiles, boolean add) {
		SocialProfileCategory category = null;
		int index = initializeIndex();
		for (SocialProfile socialProfile: socialProfiles) {
			if (category == null || !category.equals(socialProfile.getCategory())) {
				index+=1;
				category = socialProfile.getCategory();
				categoriesIndex.put(category, index);
				if (add && displayCategory()) {
					addCategoryWidget(category);
				}
			}
			index+=1;
			if (add) {
				addSocialProfileWidget(socialProfile);
			}
		}
	}
	
	@Override
	public void fill(Collection<SocialProfile> socialProfiles, SocialProfile socialProfile) {
		boolean categoryExist = categoriesIndex.containsKey(socialProfile.getCategory());
		updatesIndex(socialProfiles);
		if (categoryExist) {
			Integer index = categoriesIndex.get(socialProfile.getCategory());
			int size = Iterables.size(CollectionUtils.filter(socialProfiles, socialProfile.getCategory()));
			addSocialProfileWidget(socialProfile, index+size);
		} else {
			Integer index = categoriesIndex.get(socialProfile.getCategory());
			addCategoryWidget(socialProfile.getCategory(), index);
			addSocialProfileWidget(socialProfile, index+1);
		}
	}

	private void addCategoryWidget(SocialProfileCategory category) {
		panel.add(createTitleCategoryWidget(category));
	}
	
	private void addCategoryWidget(SocialProfileCategory category, int index) {
		panel.insert(createTitleCategoryWidget(category), index);
	}
	
	private void addSocialProfileWidget(SocialProfile socialNetwork) {
		panel.add(createSocialProfileWidget(socialNetwork));
	}

	private void addSocialProfileWidget(SocialProfile socialProfile, int beforeIndex) {
		panel.insert(createSocialProfileWidget(socialProfile), beforeIndex);
	}
	
	@Override
	protected String socialProfileStyleName() {
		return "social-profile-vertical-bar-widget";
	}

	@Override
	protected String socialProfileCategoryStyleName() {
		return "social-profile-vertical-bar-category";
	}
}
