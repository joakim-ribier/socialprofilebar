package fr.ribier.joakim.gwt.ui.socialprofilebar.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils.CollectionUtils;

public class SocialProfileBarClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(CollectionUtils.class).in(Singleton.class);
	}
}
