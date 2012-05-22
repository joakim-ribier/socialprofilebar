package fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.bar;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.ribier.joakim.gwt.ui.socialprofilebar.client.UiBarConfiguration;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.SocialProfileBarUi;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfileCategory;

public class SocialProfileHorizontalBar extends SocialProfileBarUi {

	private static SocialProfileHorizontalBarUiBinder uiBinder = GWT.create(SocialProfileHorizontalBarUiBinder.class);
	interface SocialProfileHorizontalBarUiBinder extends UiBinder<Widget, SocialProfileHorizontalBar> {}

	@UiField FlowPanel panel;
	
	public SocialProfileHorizontalBar(UiBarConfiguration uiBarConfig) {
		super(uiBarConfig);
	}

	@Override
	public void init() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void fill(Collection<SocialProfile> socialProfiles) {
		addOrUpdateIndex(socialProfiles, true);
	}

	@Override
	protected void addOrUpdateIndex(Collection<SocialProfile> socialProfiles, boolean add) {
		SocialProfileCategory category = null;
		FlowPanel contains = null;
		FlowPanel socialProfileWidgetPanel = null;
		int index = -1;
		for (SocialProfile socialProfile: socialProfiles) {
			if (category == null || !category.equals(socialProfile.getCategory())) {
				category = socialProfile.getCategory();
				if (add) {
					socialProfileWidgetPanel = new FlowPanel();
					contains = createFloatPanel(socialProfileWidgetPanel, category);
					panel.add(contains);
				}
				categoriesIndex.put(category, index+=1);
			}
			if (add) {
				addSocialProfileWidget(socialProfileWidgetPanel, socialProfile);
			}
		}
	}

	private FlowPanel createFloatPanel(FlowPanel widgets, SocialProfileCategory category) {
		FlowPanel main = new FlowPanel();
		main.setStyleName("table-cell");
		main.add(widgets);
		if (displayCategory()) {
			main.add(createTitleCategoryWidget(category));
		}
		return main;
	}

	private void addSocialProfileWidget(FlowPanel socialProfileWidgetPanel, SocialProfile socialProfile) {
		socialProfileWidgetPanel.add(createSocialProfileWidget(socialProfile));
	}

	@Override
	public void fill(Collection<SocialProfile> socialProfiles, SocialProfile socialProfile) {
		boolean categoryExist = categoriesIndex.containsKey(socialProfile.getCategory());
		updatesIndex(socialProfiles);
		Integer index = categoriesIndex.get(socialProfile.getCategory());
		if (categoryExist) {
			FlowPanel flowPanel = (FlowPanel) panel.getWidget(index);
			FlowPanel widget = (FlowPanel) flowPanel.getWidget(0);
			widget.add(createSocialProfileWidget(socialProfile));
		} else {
			FlowPanel widgets = new FlowPanel();
			widgets.add(createSocialProfileWidget(socialProfile));
			panel.insert(createFloatPanel(widgets, socialProfile.getCategory()), index);
		}
	}

	@Override
	protected String socialProfileStyleName() {
		return "social-profile-horizontal-bar-widget";
	}

	@Override
	protected String socialProfileCategoryStyleName() {
		return "social-profile-horizontal-bar-category";
	}
}
