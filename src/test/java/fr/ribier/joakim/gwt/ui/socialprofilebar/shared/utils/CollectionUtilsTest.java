package fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils;

import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfileCategory;
import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.utils.CollectionUtils;

public class CollectionUtilsTest {

	private static final SocialProfileCategory CAT_UTILITY = new SocialProfileCategory("Utility", 0);
	private static final SocialProfileCategory CAT_CODING = new SocialProfileCategory("Coding", 1);
	private static final SocialProfileCategory CAT_PROFESSIONAL = new SocialProfileCategory("Professional", 2);
	
	private static final SocialProfile FACEBOOK = 
			new SocialProfile.Builder().title("Facebook").category(CAT_UTILITY).build();
	
	private static final SocialProfile GITHUB = 
			new SocialProfile.Builder().title("GitHub").category(CAT_CODING).build();
	
	private static final SocialProfile LINKEDIN = 
			new SocialProfile.Builder().title("LinkedIn").category(CAT_PROFESSIONAL).build();
	
	private static final SocialProfile OHLOH = 
			new SocialProfile.Builder().title("Ohloh").category(CAT_CODING).build();

	private CollectionUtils collectionUtils;

	@Before
	public void before() {
		collectionUtils = new CollectionUtils();
	}

	@Test
	public void testAscendingOrderedSocialProfileCollection() {
		Collection<SocialProfile> orderedCollection = 
				collectionUtils.ascendingOrdered(Lists.newArrayList(GITHUB, LINKEDIN, FACEBOOK));
		
		Assert.assertEquals(Iterables.get(orderedCollection, 0), FACEBOOK);
		Assert.assertEquals(Iterables.get(orderedCollection, 1), GITHUB);
		Assert.assertEquals(Iterables.get(orderedCollection, 2), LINKEDIN);
	}
	
	@Test
	public void testAscendingOrderedSocialProfileCollectionWithSameCategory() {
		Collection<SocialProfile> orderedSocialProfils = 
				collectionUtils.ascendingOrdered(Lists.newArrayList(GITHUB, LINKEDIN, FACEBOOK, OHLOH));
		
		Assert.assertEquals(Iterables.get(orderedSocialProfils, 0), FACEBOOK);
		Assert.assertEquals(Iterables.get(orderedSocialProfils, 1), GITHUB);
		Assert.assertEquals(Iterables.get(orderedSocialProfils, 2), OHLOH);
		Assert.assertEquals(Iterables.get(orderedSocialProfils, 3), LINKEDIN);
	}
	
	@Test
	public void testFilterSocialProfileCollection() {
		Iterable<SocialProfile> orderedCollection =
				collectionUtils.filter(Lists.newArrayList(FACEBOOK, GITHUB, OHLOH, LINKEDIN), CAT_CODING);
		
		Assert.assertEquals(Iterables.get(orderedCollection, 0), GITHUB);
		Assert.assertEquals(Iterables.get(orderedCollection, 1), OHLOH);
	}
	
	@Test
	public void testFilterSocialProfileCollectionWithCategoryNotExist() {
		Iterable<SocialProfile> orderedCollection = 
				collectionUtils.filter(Lists.newArrayList(FACEBOOK, GITHUB, OHLOH, LINKEDIN),
						new SocialProfileCategory("new category", 4));
		
		Assert.assertTrue(Iterables.isEmpty(orderedCollection));
	}
	
	@Test
	public void testCopyAndAddSocialProfileCollection() {
		List<SocialProfile> list = Lists.newArrayList(FACEBOOK, GITHUB);
		Iterable<SocialProfile> copy = collectionUtils.copyAndAdd(list, OHLOH, LINKEDIN);
		
		Assert.assertEquals(Iterables.size(list), 2);
		Assert.assertEquals(Iterables.size(copy), 4);
		
		Assert.assertEquals(Iterables.get(copy, 0), FACEBOOK);
		Assert.assertEquals(Iterables.get(copy, 1), GITHUB);
		Assert.assertEquals(Iterables.get(copy, 2), OHLOH);
		Assert.assertEquals(Iterables.get(copy, 3), LINKEDIN);
	}
}
