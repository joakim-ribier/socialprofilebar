package fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfileCategory;


public class CollectionUtils {
	
	@VisibleForTesting CollectionUtils() {}

	public Collection<SocialProfile> copyAndAdd(Collection<SocialProfile> socialProfiles, SocialProfile... elements)  {
		Collection<SocialProfile> copy = copy(socialProfiles);
		for (SocialProfile element: elements) {
			copy.add(element);	
		}
		return copy;
	}
	
	private Collection<SocialProfile> copy(Collection<SocialProfile> socialProfiles)  {
		return Lists.newArrayList(socialProfiles);
	}
	
	public Collection<SocialProfile> ascendingOrdered(Collection<SocialProfile> socialProfiles) {
		Collection<List<SocialProfile>> orderedPermutations = orderedSocialNetworkCollection(socialProfiles);
		return orderedPermutations.iterator().next();
	}
	
	private Collection<List<SocialProfile>> orderedSocialNetworkCollection(Collection<SocialProfile> socialProfiles) {
		Collection<List<SocialProfile>> orderedPermutations = 
				Collections2.orderedPermutations(socialProfiles, new Comparator<SocialProfile>() {
			@Override
			public int compare(SocialProfile o1, SocialProfile o2) {
				return o1.getCategory().getWeight() - o2.getCategory().getWeight();
			}
		});
		return orderedPermutations;
	}
	
	public Iterable<SocialProfile> filter(Collection<SocialProfile> socialProfiles, final SocialProfileCategory category) {
		return Iterables.filter(socialProfiles, new Predicate<SocialProfile>() {
			@Override
			public boolean apply(SocialProfile arg0) {
				return arg0.getCategory().equals(category);
			}
		});
	}
}
