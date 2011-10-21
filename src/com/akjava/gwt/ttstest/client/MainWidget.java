package com.akjava.gwt.ttstest.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT.create(MainWidgetUiBinder.class);

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> {
	}

	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
@UiField TextBox input;
@UiField HorizontalPanel controls;
@UiField Button speak,stop,speaking,voice_list;
@UiField Label logbox;
@UiField VerticalPanel log;

	
public TextBox getInput(){
return input;
}

public HorizontalPanel getControls(){
return controls;
}

public Button getSpeak(){
return speak;
}

public Button getStop(){
return stop;
}

public Button getSpeaking(){
return speaking;
}

public Button getVoice_list(){
return voice_list;
}

public Label getLogbox(){
return logbox;
}

public VerticalPanel getLog(){
return log;
}


	
@UiHandler("speak")
void clickSpeak(ClickEvent e) {

}
@UiHandler("stop")
void clickStop(ClickEvent e) {

}
@UiHandler("speaking")
void clickSpeaking(ClickEvent e) {

}
@UiHandler("voice_list")
void clickVoice_list(ClickEvent e) {

}


}
