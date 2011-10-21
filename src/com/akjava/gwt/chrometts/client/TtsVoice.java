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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class TtsVoice extends JavaScriptObject{
protected TtsVoice(){}
	public   final  native String getVoiceName()/*-{
	return this["voiceName"];
	}-*/;
	
	public   final  native String getLang()/*-{
	return this["lang"];
	}-*/;
	
	public   final  native String getGender()/*-{
	return this["gender"];
	}-*/;
	
	public   final  native String getExtensionId()/*-{
	return this["extensionId"];
	}-*/;
	
	public   final  native JsArrayString getEventTypes ()/*-{
	return this["eventTypes"];
	}-*/;
}
