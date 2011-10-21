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

import com.google.gwt.core.client.JsArray;

public class ChromeTts {

	public static native boolean isAvaialbe()/*-{

	if($wnd.chrome){
		 if($wnd.chrome.tts){
		 	return true;
		 }
	}
	return false;
	}-*/;
	
	public static native void speak(String text)/*-{
	$wnd.chrome.tts.speak(text);
	}-*/;
	
	public static native void isSpeaking(IsSpeakingeHandler handler)/*-{
		var callback;
	if(handler!=null){
		callback=function (speaking) {
		handler.@com.akjava.gwt.chrometts.client.ChromeTts$IsSpeakingeHandler::isSpeaking(Z)(speaking);
		}
	}	
	 $wnd.chrome.tts.isSpeaking(callback);
	}-*/;
	
	public static native void stop()/*-{
	 $wnd.chrome.tts.stop();
	}-*/;
	
	
	public static native void speak(String text,TtsOption options,SpeakHandler handler)/*-{
	var callback;
	if(handler!=null){
		callback=function () {
		handler.@com.akjava.gwt.chrometts.client.ChromeTts$SpeakHandler::callback()();
		}
	}	
	 $wnd.chrome.tts.speak(text,options,callback);
	}-*/;
	
	public static TtsOption options(){
		return TtsOption.create();
	}
	

	public static final native void getVoices(GetVoiceHandler handler)/*-{
	var fc=function ( voices ) {
		handler.@com.akjava.gwt.chrometts.client.ChromeTts$GetVoiceHandler::voices(*)(voices);
		//handler.@com.akjava.gwt.chrometts.client.ChromeTts$GetVoiceHandler::event(Lcom/akjava/gwt/chrometts/client/TtsEvent;)(event);
		}
		$wnd.chrome.tts.getVoices(fc);
	}-*/;
	
	
	public static interface TtsEventHandler{
		public void event(TtsEvent event);
	}
	
	public static interface GetVoiceHandler{
		//public void voices(JsArray voices);
		public void voices(JsArray<TtsVoice> voices);
	}
	
	public static interface IsSpeakingeHandler{
		public void isSpeaking(boolean speaking);
	}
	public static interface SpeakHandler{
		public void callback();
	}
	
	
}
