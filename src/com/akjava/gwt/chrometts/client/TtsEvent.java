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

public class TtsEvent extends JavaScriptObject{
public static final String TYPE_END="end";
public static final String TYPE_INTERRUPTED="interrupted";
public static final String TYPE_CANCELLED="cancelled";
public static final String TYPE_ERROR="error";
protected TtsEvent(){}

public   final  native String getType()/*-{
return this["type"];
}-*/;

public  final native int getCharIndex()/*-{
return this["charIndex"];
}-*/;

public  final   native String getErrorMessage()/*-{
return this["errorMessage"];
}-*/;

}
