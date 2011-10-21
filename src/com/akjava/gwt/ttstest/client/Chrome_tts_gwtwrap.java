/*
 * Copyright (C) 2011 aki@akjava.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Chrome_tts_gwtwrap implements EntryPoint {
	
	public void onModuleLoad() {
		if(!ChromeTts.isAvaialbe()){
			Window.alert("not supported");
			HTMLPanel panel=new HTMLPanel("TTS not supported,access this page as extension.\nload manifest.json from extension setting page");
			return;
		}
		final VerticalPanel root=new VerticalPanel();
		final CanvasSlider slider=new CanvasSlider(1,50,10,200);
		
		HorizontalPanel h1=new HorizontalPanel();
		h1.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		root.add(h1);
		h1.add(new Label("Rate:"));
		h1.add(slider);
		
		
		final TextBox text=new TextBox();
		text.setWidth("400px");
		text.setText("hello world.\nhere you are");
		root.add(text);
		
		
		final EventLogger logger=new EventLogger(30);
		
		
		Button bt=new Button("speak");
		bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				double rate=(double)slider.getValue()/10;
				
				SpeakHandler callback=new SpeakHandler() {

					@Override
					public void callback() {
						logger.add(new HTMLPanel("speak callbacked"));
					}
					
				
				};
				ChromeTts.speak(text.getText(), ChromeTts.options().rate(rate).onEvent(new TtsEventHandler() {
					
					@Override
					public void event(TtsEvent event) {
						logger.add(event);
						if(event.getCharIndex()>=0){
							text.setSelectionRange(event.getCharIndex(), 1);//TODO find real one.
						}
					}
				}), callback);
			}
		});
		root.add(bt);
		Button stopBt=new Button("stop");
		stopBt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ChromeTts.stop();
			}
			
		});
		root.add(stopBt);
		
		Button isSpeaking=new Button("speaking?");
		isSpeaking.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ChromeTts.isSpeaking(new IsSpeakingeHandler(){

					@Override
					public void isSpeaking(boolean speaking) {
						logger.add("speaking:"+speaking);
					}});
			}
			
		});
		root.add(isSpeaking);
		
		
		root.add(logger);
		
		ChromeTts.getVoices(new GetVoiceHandler() {
			
			@Override
			public void voices(JsArray<TtsVoice> voices) {
				ValueListBox<TtsVoice> listBox=new ValueListBox<TtsVoice>(new Renderer<TtsVoice>() {

					@Override
					public String render(TtsVoice voice) {	
						return voice.getVoiceName()+","+voice.getExtensionId()+","+voice.getGender()+","+voice.getLang()+",type:"+voice.getEventTypes();
					}
					
					

					@Override
					public void render(TtsVoice object, Appendable appendable)
							throws IOException {
						// TODO Auto-generated method stub
						
					}
				});
				listBox.setValue(voices.get(0));
				GWT.log("voice:");
				ArrayList<TtsVoice> array=new ArrayList<TtsVoice>();
				for(int i=0;i<voices.length();i++){
					array.add(voices.get(i));
				}
				listBox.setAcceptableValues(array);
				root.add(listBox);
			}
		});
		
		
		RootPanel.get().add(root);
		
	}
}
