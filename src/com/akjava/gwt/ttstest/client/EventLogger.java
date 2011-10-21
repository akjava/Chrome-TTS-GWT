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

import com.akjava.gwt.chrometts.client.TtsEvent;
import com.google.gwt.user.client.ui.HTMLPanel;

public class EventLogger extends HTMLPanel{
private int maxSize=10;


	public EventLogger(int logSize) {
		super("");
		this.maxSize=logSize;
	}
	public void add(String line){
		add(new HTMLPanel(line));
	}
	public void add(HTMLPanel panel){
		
		getElement().insertFirst(panel.getElement());//add first
		
		//remove over element
		for(int i=this.getElement().getChildCount()-1;i>=maxSize;i--){
			this.getElement().removeChild(this.getElement().getChild(i));
		}
	}
	
	public void add(TtsEvent event){
		HTMLPanel newPanel=createAddLog(event);
		add(newPanel);
	}
	
	public HTMLPanel createAddLog(TtsEvent event){
		
		String log="TYPE:"+event.getType();
		log+=",index:"+event.getCharIndex();
		log+=",error:"+event.getErrorMessage();
		return new HTMLPanel(log);
	}

}
