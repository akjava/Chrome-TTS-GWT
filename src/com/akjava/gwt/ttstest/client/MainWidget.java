package com.akjava.gwt.ttstest.client;

import java.io.IOException;
import java.util.ArrayList;

import com.akjava.gwt.chrometts.client.ChromeTts;
import com.akjava.gwt.chrometts.client.TtsEvent;
import com.akjava.gwt.chrometts.client.TtsVoice;
import com.akjava.gwt.chrometts.client.ChromeTts.GetVoiceHandler;
import com.akjava.gwt.chrometts.client.ChromeTts.IsSpeakingeHandler;
import com.akjava.gwt.chrometts.client.ChromeTts.SpeakHandler;
import com.akjava.gwt.chrometts.client.ChromeTts.TtsEventHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT.create(MainWidgetUiBinder.class);

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> {
	}
	private CanvasSlider pitchSlider;
	private CanvasSlider volumeSlider;

	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		controls.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		
		rateSlider = new CanvasSlider(1,50,10,200);
		rateSlider.setFloatingPoint(true);
		rateSlider.update();
		controls.add(new Label("Rate:"));
		controls.add(rateSlider);
		
		pitchSlider = new CanvasSlider(0,20,10,200);
		pitchSlider.setFloatingPoint(true);
		pitchSlider.update();
		
		controls.add(new Label("Pitch:"));
		controls.add(pitchSlider);
		
		volumeSlider = new CanvasSlider(0,10,5,200);
		volumeSlider.setFloatingPoint(true);
		volumeSlider.update();
		
		controls.add(new Label("Volume:"));
		controls.add(volumeSlider);
		
		
		
		
		input.setText("hello world.\nare you Ready?");
		
		logger = new EventLogger(30);
		log.add(logger);
	}
@UiField TextBox input;
@UiField HorizontalPanel controls;
@UiField Button speak,stop,speaking,voice_list;
@UiField Label logbox;
@UiField VerticalPanel log;
private EventLogger logger;
private CanvasSlider rateSlider;

	

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
	double rate=(double)rateSlider.getValue()/10;
	double pitch=(double)pitchSlider.getValue()/10;
	double volume=(double)volumeSlider.getValue()/10;
	
	SpeakHandler callback=new SpeakHandler() {

		@Override
		public void callback() {
			logger.add(new HTMLPanel("speak callbacked"));
		}
		
	
	};
	ChromeTts.speak(input.getText(), ChromeTts.options()
			.rate(rate).volume(volume).pitch(pitch)
			.onEvent(new TtsEventHandler() {
		
		@Override
		public void event(TtsEvent event) {
			logger.add(event);
			if(event.getCharIndex()>=0){
				input.setSelectionRange(event.getCharIndex(), 1);//TODO find real one.
			}
		}
	}), callback);
}
@UiHandler("stop")
void clickStop(ClickEvent e) {
	ChromeTts.stop();
}
@UiHandler("speaking")
void clickSpeaking(ClickEvent e) {

		ChromeTts.isSpeaking(new IsSpeakingeHandler(){

			@Override
			public void isSpeaking(boolean speaking) {
				logger.add("speaking:"+speaking);
			}});
	
}
@UiHandler("voice_list")
void clickVoice_list(ClickEvent e) {
	ChromeTts.getVoices(new GetVoiceHandler() {
		
		@Override
		public void voices(JsArray<TtsVoice> voices) {
			GWT.log("voice:");
			ArrayList<TtsVoice> array=new ArrayList<TtsVoice>();
			for(int i=0;i<voices.length();i++){
				TtsVoice voice=voices.get(i);
				String label=voice.getVoiceName()+","+voice.getExtensionId()+","+voice.getGender()+","+voice.getLang()+",type:"+voice.getEventTypes();
				logger.add(label);
			}

		}
	});
}


}
