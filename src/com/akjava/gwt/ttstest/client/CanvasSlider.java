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

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CanvasSlider extends VerticalPanel{
	public  int height=40;
	private int min;
	private int max;
	private int current;
	private int margin=8;
	//private int marginW=8;
	private int width;
	private Canvas canvas;
	CssColor bgColor=CssColor.make("#fff");
	CssColor frameColor=CssColor.make("#333");
	CssColor valueColor=CssColor.make("#ccc");
	CssColor labelColor=CssColor.make("#000");
	public CanvasSlider(int min,int max,int current,int widthPx){
		this.min=min;
		this.max=max;
		this.width=widthPx;
		this.current=current;
		this.setWidth(widthPx+"px");
		canvas = Canvas.createIfSupported();
		canvas.setWidth(widthPx+"px");
		canvas.setHeight(height+"px");
		canvas.setCoordinateSpaceHeight(height);
		canvas.setCoordinateSpaceWidth(width);
		canvas.getContext2d().setFont("16px sans-serif");
		this.add(canvas);
		update();
		MouseListener listener=new MouseListener();
		canvas.addMouseDownHandler(listener);
		canvas.addMouseUpHandler(listener);
		canvas.addMouseMoveHandler(listener);
		canvas.addMouseOutHandler(listener);
		
	}
	public int getValue(){
		return current;
	}
	public class MouseListener implements MouseDownHandler,MouseUpHandler,MouseMoveHandler,MouseOutHandler{
		private boolean drag;
		@Override
		public void onMouseDown(MouseDownEvent event) {
			int x=event.getX();
			setMouseValue(x);
			drag=true;
			
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			// TODO Auto-generated method stub
			
			drag=false;
		}

		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if(drag){
				setMouseValue(event.getX());
			}
		}

		@Override
		public void onMouseOut(MouseOutEvent event) {
			if(drag){
				setMouseValue(event.getX());
			}
			drag=false;
		}
	}
	public String toLabel(){
		return ""+current;
	}
	public void setMouseValue(int x){
		int pv=(width-margin*2)/(max-min);
		int cv=(x-margin)/pv;
		cv=Math.max(0, cv);
		cv+=min;
		cv=Math.min(max, cv);
		current=cv;
		update();
	}
	
	public void update(){
		int x=margin;
		int y=margin;
		int w=width-margin*2;
		int h=height-margin*2;
		
		canvas.getContext2d().setFillStyle(bgColor);
		canvas.getContext2d().fillRect(x, y, w, h);
		
		int vwidth=calcurateValueWidth();
		
		canvas.getContext2d().setFillStyle(valueColor);
		canvas.getContext2d().fillRect(x, y, vwidth, h);
		
		canvas.getContext2d().setStrokeStyle(frameColor);
		canvas.getContext2d().strokeRect(x, y, w, h);
		
		//canvas.getContext2d().setStrokeStyle(bgColor);
		//canvas.getContext2d().strokeText(toLabel(), margin*2, -margin+20);
		
		canvas.getContext2d().setFillStyle(labelColor);
		canvas.getContext2d().fillText(toLabel(), margin*2, height/2+8);
	}
	public int calcurateValueWidth(){
		return (width-margin*2)*(current-min)/(max-min);
	}
}
