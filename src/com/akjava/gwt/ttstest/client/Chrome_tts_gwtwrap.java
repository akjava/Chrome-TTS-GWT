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
			HTMLPanel panel=new HTMLPanel("TTS not supported,access this page as extension.\nload manifest.json from extension setting page");
			RootPanel.get().add(panel);
			return;
		}
		
		MainWidget main=new MainWidget();
		RootPanel.get().add(main);
	}
}
