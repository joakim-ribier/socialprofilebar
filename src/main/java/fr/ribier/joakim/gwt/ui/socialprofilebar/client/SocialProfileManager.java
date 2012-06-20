package fr.ribier.joakim.gwt.ui.socialprofilebar.client;

import java.util.Collection;

import com.google.common.base.Preconditions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

import fr.ribier.joakim.gwt.ui.socialprofilebar.client.UiBarConfiguration.Align;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.gin.SocialProfileBarGinjector;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.SocialProfileBarUi;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.bar.SocialProfileHorizontalBar;
import fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.bar.SocialProfileVerticalBar;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils.CollectionUtils;

public class SocialProfileManager {
	
	private final SocialProfileBarGinjector injector = GWT.create(SocialProfileBarGinjector.class);
	private final CollectionUtils collectionUtils = injector.getCollectionUtils();

	private Collection<SocialProfile> socialProfiles;
	private final SocialProfileBarUi socialProfileBarUi;
	
	public SocialProfileManager(Collection<SocialProfile> socialProfiles, UiBarConfiguration uiBarConfig) {
		setModel(socialProfiles);
		
		this.socialProfileBarUi = configure(uiBarConfig);
		this.socialProfileBarUi.fill(this.socialProfiles);
	}
	
	public SocialProfileManager(Collection<SocialProfile> socialProfiles) {
		this(socialProfiles, null);
	}
	
	public void setModel(Collection<SocialProfile> socialProfiles) {
		Preconditions.checkState(socialProfiles != null && !socialProfiles.isEmpty());
		this.socialProfiles = collectionUtils.ascendingOrdered(socialProfiles);
	}
	
	private SocialProfileBarUi configure(UiBarConfiguration uiBarConfig) {
		if (uiBarConfig == null) {
			uiBarConfig = new UiBarConfiguration.Builder().build();
		}
		
		if (uiBarConfig.getDisposition() == Align.VERTICAL) {
			return new SocialProfileVerticalBar(uiBarConfig);
		} else {
			return new SocialProfileHorizontalBar(uiBarConfig);
		}
	}

	public Widget asWidget() {
		return (Widget) socialProfileBarUi;
	}
	
	public void add(SocialProfile socialProfile) {
		updateModel(socialProfile);
		socialProfileBarUi.fill(socialProfiles, socialProfile);
	}
	
	private void updateModel(SocialProfile socialProfile) {
		setModel(collectionUtils.copyAndAdd(this.socialProfiles, socialProfile));
	}
}
