package fr.ribier.joakim.gwt.ui.socialprofilebar.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils.CollectionUtils;

@GinModules(SocialProfileBarClientModule.class)
public interface SocialProfileBarGinjector extends Ginjector {

	CollectionUtils getCollectionUtils();
}
