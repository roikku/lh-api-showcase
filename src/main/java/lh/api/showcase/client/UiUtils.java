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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lh.api.showcase.shared.LanguageCode;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.renderers.WidgetRenderer;
import com.vaadin.client.widget.grid.RendererCellReference;
import com.vaadin.client.widgets.Grid.Column;

public class UiUtils {

	private UiUtils () { super () ; throw new IllegalStateException () ; } 
	
	public static <T extends Widget> T inlined (T w) {
		if (w == null) {
			throw new NullPointerException ("Cannot inlined a null object...") ;
		}
		w.getElement().getStyle().setDisplay(Display.INLINE);
		return w ;
	}
	
	public static <T extends Widget> T inlineBlock (T w) {
		if (w == null) {
			throw new NullPointerException ("Cannot inlined a null object...") ;
		}
		w.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		return w ;
	}
	
	public static <T extends Widget> T displayBlock (T w) {
		if (w == null) {
			throw new NullPointerException ("Cannot display block a null object...") ;
		}
		w.getElement().getStyle().setDisplay(Display.BLOCK);
		return w ;
	}
	
	public static <T extends Widget> T setWidth (T w, double v, Unit unit) {
		if (w == null) {
			throw new NullPointerException ("Cannot resized a null object...") ;
		}
		w.getElement().getStyle().setWidth(v, unit);
		return w ;
	}
	
	
	public static <T extends HasHorizontalAlignment> T setHorizontalAlignment (T w, HasHorizontalAlignment.HorizontalAlignmentConstant align) {
		if (w == null) {
			throw new NullPointerException ("Cannot set alignment for a null object...") ;
		}
		w.setHorizontalAlignment(align);
		return w ;
	}
	
	public static <T extends HasHorizontalAlignment> T setLeftHorizontalAlignment (T w) {
		if (w == null) {
			throw new NullPointerException ("Cannot set alignment for a null object...") ;
		}
		w.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		return w ;
	}
	
	
	public static ListBox newTimeListBox () {
		
		NumberFormat nf = NumberFormat.getFormat("00") ;
		
		ListBox lb = new ListBox () ;
		for (int h = 0 ; h < 24 ; ++h) {
			for (int m = 0 ; m < 60 ; m+=5) {
				lb.addItem(nf.format(h) + ":" +nf.format(m));
			}
		}
		return lb ;
	}
	
	
	static public <T extends MultiLingualName.HasMultiLingualName> Column<MultiLingualName, T > getMultiLingualNameColumn (final Integer width) {
		return new Column<MultiLingualName, T>(Messages.Util.INSTANCE.get().name()) {
			@Override
			public MultiLingualName getValue(T row) {
				return row.getMultiLingualName();
			}
		}.setRenderer(new WidgetRenderer<MultiLingualName, ListBox>(){

			@Override
			public ListBox createWidget() {
				ListBox lb = new ListBox () ;
				if (width == null) {
					lb.setWidth("300px");
				} else {
					lb.setWidth(String.valueOf(width.intValue()) + "px");
				}
				return lb ;
			}

			@Override
			public void render(RendererCellReference cell,
					MultiLingualName data, ListBox widget) {

				UiUtils.fillListBoxName(data, widget);
			}});
	}
	
	
	private static void fillListBoxName (MultiLingualName mn, ListBox listBox) {
		
		int selected = 0 ;
		int count = 0 ;
		for (Map.Entry<LanguageCode, String> ent : mn.getNamesMap().entrySet()) {
			StringBuilder sb = new StringBuilder () ;	
			sb.append("(") ;
			sb.append(ent.getKey().toString().toLowerCase()) ;
			sb.append(") ") ;
			sb.append(ent.getValue()) ;
			if (LanguageCode.EN.equals(ent.getKey())) {
				selected = count ;
			}
			listBox.addItem(sb.toString());
			++count ;
		}
		listBox.setSelectedIndex(selected);
	}
	
	
	public static class ConstraintSuggestBox extends SuggestBox {
		
		private final Set<String> values ;
		
		public ConstraintSuggestBox (final Collection<String> valuesCollection) {
			super (new MultiWordSuggestOracle()) ;
			
			if (valuesCollection == null) {
				throw new NullPointerException () ;
			}
			
			this.values = new HashSet<String>(valuesCollection) ;			
			((MultiWordSuggestOracle)this.getSuggestOracle()).addAll(values);

			addValueChangeHandler(new ValueChangeHandler<String>() {

				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					String newVal = event.getValue() ;
					if (newVal != null && !newVal.isEmpty()) {
						if (!values.contains(newVal)) {
							// TODO: error message to the user
							// ...
							setValue ("") ;
						}
					}
				}}) ;
		}
		
		public Set<String> getValues () {
			return new HashSet<String> (values) ;
		}
	}
	
	
	// http://stackoverflow.com/questions/8036561/allow-only-numbers-in-textbox-in-gwt
	public static class DoublesOnlyKeyPressHandler implements KeyPressHandler {
		@Override
		public void onKeyPress(KeyPressEvent event) {

			switch (event.getNativeEvent().getKeyCode()) {
			case KeyCodes.KEY_TAB:
			case KeyCodes.KEY_BACKSPACE:
			//case KeyCodes.KEY_DELETE:
			case KeyCodes.KEY_LEFT:
			case KeyCodes.KEY_RIGHT:
			case KeyCodes.KEY_UP:
			case KeyCodes.KEY_DOWN:
			case KeyCodes.KEY_END:
			case KeyCodes.KEY_ENTER:
			case KeyCodes.KEY_ESCAPE:
			case KeyCodes.KEY_PAGEDOWN:
			case KeyCodes.KEY_PAGEUP:
			case KeyCodes.KEY_HOME:
			case KeyCodes.KEY_SHIFT:
			case KeyCodes.KEY_ALT:
			case KeyCodes.KEY_CTRL:
				break;
			default:

				if (event.isAltKeyDown()
						|| (event.isControlKeyDown() && (event.getCharCode() != 'v' && event
								.getCharCode() != 'V'))) {
					break;
				}

				String sep = LocaleInfo.getCurrentLocale().getNumberConstants().decimalSeparator() ;
				if (sep.charAt(0) == event.getCharCode()) {
					if (event.getSource() instanceof DoubleBox) {
						String val = ((DoubleBox) event.getSource()).getText() ;
						if (val.contains(sep)) {
							// already a decimal number
							((DoubleBox) event.getSource()).cancelKey();
							break ;
						} else {
						    // is integer
							break ;
						}
					}
				}

				if (KeyCodes.KEY_NUM_MINUS == event.getCharCode()
						|| 45 == event.getNativeEvent().getKeyCode()) {
					
					if (event.getSource() instanceof DoubleBox) {
						int curPos = ((DoubleBox) event.getSource()).getCursorPos() ;
						if (curPos != 0){
							((DoubleBox) event.getSource()).cancelKey();
							break ;
						} else {
							break;
						}
					}
				}
				
				if (!Character.isDigit(event.getCharCode())) {
					if (event.getSource() instanceof DoubleBox) {
						((DoubleBox) event.getSource()).cancelKey();
						break ;
					}
				}
			}
		}
	}
	
	
	public static enum MessageType {
		INFO,
		SUCCESS,
		WARNING,
		ERROR,
		PRIMARY,
		DEFAULT,
	}
	
	
	public static HTML getMessageTypeLabel (MessageType type, String str) {		
		
		StringBuilder sb = new StringBuilder () ;
		sb.append ("<span class=\"label ") ;
		switch (type) {
			case INFO: {
				sb.append ("label-info\">") ;
				sb.append ((str == null)?("Info"):(str)) ;
				break ;
			}
			case SUCCESS: {
				sb.append ("label-success\">") ;
				sb.append ((str == null)?("Success"):(str)) ;
				break ;
			}
			case WARNING: {
				sb.append ("label-warning\">") ;
				sb.append ((str == null)?("Warning"):(str)) ;
				break ;
			}
			case ERROR: {
				sb.append ("label-error\">") ;
				sb.append ((str == null)?("Error"):(str)) ;
				break ;
			}
			case PRIMARY: {
				sb.append ("label-primary\">") ;
				sb.append ((str == null)?("Primary"):(str)) ;
				break ;
			}
			default: {
				sb.append ("label-default\">") ;
				sb.append ((str == null)?("Default"):(str)) ;
				break ;
			}
		}
		sb.append ("</span>") ;
		return inlined (new HTML (sb.toString()));
	}
	
	
	public static class InlinedLabel extends Label {
		public InlinedLabel (String str) {
			super (str) ;
			this.getElement().getStyle().setDisplay(Display.INLINE);
		}
	}
	
	
	public static void setMessage (Panel parent, String message, MessageType type, String iconStr) {
		
		Panel panel = new FlowPanel () ;
		panel.setWidth("100%");
		panel.getElement().getStyle().setFloat(com.google.gwt.dom.client.Style.Float.LEFT);
		panel.getElement().getStyle().setMarginTop(15, Unit.PX);
		panel.getElement().getStyle().setMarginBottom(5, Unit.PX);
		
		panel.add (UiUtils.getMessageTypeLabel(type, iconStr)) ;

		Label messageLabel = new UiUtils.InlinedLabel(message) ;
		messageLabel.setStyleName("message") ;
		panel.add(messageLabel) ;

		parent.add(panel) ;
	}
}
