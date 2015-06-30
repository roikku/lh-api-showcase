/*
 * Copyright 2015 Loic Merckel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package lh.api.showcase.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Dialogs {

	private Dialogs () { super () ; throw new IllegalStateException () ; } 
	
	public interface DialogConfirmCallback {
	    void onOk();
	    void onCancel();
	}

	public static void confirm (String msg, final DialogConfirmCallback callback) {
		ComfirmDialog dlg = new ComfirmDialog (msg, callback) ;
		dlg.center();
		dlg.show();
	}
	
	public static void Error (String msg, final DialogConfirmCallback callback) {
		MessageDialog dlg = new MessageDialog (msg, "Error", callback) ;
		dlg.center();
		dlg.show();
	}
	
	private static class MessageDialog extends DialogBox {

		public MessageDialog(String msg, String title, final DialogConfirmCallback callback) {

			setText(title);

			setAnimationEnabled(true);
			setGlassEnabled(true);

			Panel main = new VerticalPanel () ;
			
			this.getElement().getStyle().setZIndex(10000);
			main.getElement().getStyle().setZIndex(10001);
			
			Panel message = new FlowPanel () ;
			message.getElement().getStyle().setMargin(10.0, Unit.PX);
			message.add(UiUtils.inlined(new Label (msg)));
			
			Panel control = new FlowPanel () ;
			control.getElement().getStyle().setMargin(10.0, Unit.PX);
			control.getElement().getStyle().setMarginTop(35.0, Unit.PX);
			
			Button ok = UiUtils.inlined(new Button("Ok"));
			ok.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					hide();
					if (callback != null) {
						callback.onOk();
					}
				}
			});
			ok.getElement().getStyle().setMargin(10.0, Unit.PX);
			
			control.add(ok);
	
			main.add (message) ;
			main.add (control) ;
			
			setWidget(main);
		}
	}
	
	
	private static class ComfirmDialog extends DialogBox {

		public ComfirmDialog(String msg, final DialogConfirmCallback callback) {

			setText("Confirmation");
			setAnimationEnabled(true);
			setGlassEnabled(true);

			Panel main = new VerticalPanel () ;
			
			this.getElement().getStyle().setZIndex(10000);
			main.getElement().getStyle().setZIndex(10001);
			
			Panel message = new FlowPanel () ;
			message.getElement().getStyle().setMargin(10.0, Unit.PX);
			message.add(UiUtils.inlined(new Label (msg)));
			
			Panel control = new FlowPanel () ;
			control.getElement().getStyle().setMargin(10.0, Unit.PX);
			control.getElement().getStyle().setMarginTop(35.0, Unit.PX);
			
			Button ok = UiUtils.inlined(new Button("Yes"));
			ok.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					hide();
					if (callback != null) {
						callback.onOk();
					}
				}
			});
			ok.getElement().getStyle().setMargin(10.0, Unit.PX);
			
			Button cancel = UiUtils.inlined(new Button("Cancel"));
			cancel.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					hide();
					if (callback != null) {
						callback.onCancel();
					}
				}
			});
			cancel.getElement().getStyle().setMargin(10.0, Unit.PX);
			
			control.add(ok);
			control.add(cancel);
			
			main.add (message) ;
			main.add (control) ;
			
			setWidget(main);
		}
	}
	
}
