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
package com.akjava.gwt.chrometts.client;

import com.akjava.gwt.chrometts.client.ChromeTts.TtsEventHandler;
import com.google.gwt.core.client.JavaScriptObject;

public class TtsOption extends JavaScriptObject{
protected TtsOption(){}

public final static TtsOption create(){
	return (TtsOption) TtsOption.createObject();
}

public final native TtsOption rate(double rate)/*-{
this["rate"]=rate;
return this;
}-*/;

public final native TtsOption pitch(double pitch)/*-{
this["pitch"]=pitch;
return this;
}-*/;

public final native TtsOption onEvent(TtsEventHandler handler)/*-{
this["onEvent"]=function ( event ) {
	handler.@com.akjava.gwt.chrometts.client.ChromeTts$TtsEventHandler::event(Lcom/akjava/gwt/chrometts/client/TtsEvent;)(event);
	}
return this;
}-*/;




}
