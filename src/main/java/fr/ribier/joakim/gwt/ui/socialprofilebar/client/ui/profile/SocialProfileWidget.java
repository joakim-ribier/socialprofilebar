package fr.ribier.joakim.gwt.ui.socialprofilebar.client.ui.profile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import fr.ribier.joakim.gwt.ui.socialprofilebar.shared.beans.SocialProfile;

public class SocialProfileWidget extends Composite {

	private static SocialProfileWidgetUiBinder uiBinder = 
			GWT.create(SocialProfileWidgetUiBinder.class);

	interface SocialProfileWidgetUiBinder extends UiBinder<Widget, SocialProfileWidget> {}

	@UiField Image image;
	@UiField HTML html;
	
	public SocialProfileWidget(final SocialProfile socialProfile, boolean displayTitle, int width, int height) {
		initWidget(uiBinder.createAndBindUi(this));
		
		image.setUrl(socialProfile.getImg());
		image.setAltText(socialProfile.getTitle());
		image.setTitle(socialProfile.getTitle());
		if (width != -1) {
			image.setWidth(toPX(width));
		}
		if (height != -1) {
			image.setHeight(toPX(height));
		}
		if (displayTitle) {
			html.setHTML(socialProfile.getTitle());
		}
		addClickHandler(socialProfile);
	}

	private void addClickHandler(final SocialProfile socialProfile) {
		if (socialProfile.getUrl() != null) {
			image.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.open(socialProfile.getUrl(), socialProfile.getTitle(), "");
				}
			});
		}
	}
	
	private String toPX(int value) {
		return String.valueOf(value) + "px";
	}
}
