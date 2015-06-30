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

package lh.api.showcase.client.referencedata.airports;

import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.shared.AirportCode;
import lh.api.showcase.shared.LanguageCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.grid.HeightMode;

public class AirportsView extends BasicAbstractFormResultView<Airport> implements AirportsPresenter.Display {
	
	static class AirportsGrid extends Grid<Airport> {
		public AirportsGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("750px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(8);
			
			addColumn(new Column<String, Airport>(Messages.Util.INSTANCE.get().airportCode()) {
				@Override
				public String getValue(Airport row) {
					return row.getAirportCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, Airport>(Messages.Util.INSTANCE.get().countryCode()) {
				@Override
				public String getValue(Airport row) {
					return row.getCountryCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, Airport>(Messages.Util.INSTANCE.get().cityCode()) {
				@Override
				public String getValue(Airport row) {
					return row.getCityCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, Airport>(Messages.Util.INSTANCE.get().type()) {
				@Override
				public String getValue(Airport row) {
					return row.getLocationType();
				}
			}).setWidth(110);
			addColumn(new Column<String, Airport>(Messages.Util.INSTANCE.get().coordinate()) {
				@Override
				public String getValue(Airport row) {
					
					NumberFormat nf = NumberFormat.getFormat("#.0") ;
					
					StringBuilder sb = new StringBuilder () ;
					sb.append("(") ;
					sb.append(nf.format(row.getLatitude())) ;
					sb.append(", ") ;
					sb.append(nf.format(row.getLongitude())) ;
					sb.append(")") ;
					return sb.toString();
				}
			}).setWidth(110);
			addColumn(UiUtils.<Airport>getMultiLingualNameColumn(170));
		}
	}

	private SuggestBox airportCodes ;
	private SuggestBox langs ;
	
	public AirportsView () {
		super (Messages.Util.INSTANCE.get().airports(), new AirportsGrid ()) ;
		
		final MultiWordSuggestOracle aiportCodesList = new MultiWordSuggestOracle();
		final MultiWordSuggestOracle langList = new MultiWordSuggestOracle();
		for (AirportCode cc : AirportCode.values ()){
			aiportCodesList.add(cc.toString());
		}
		for (LanguageCode lc : LanguageCode.values ()){
			langList.add(lc.toString());
		}
		airportCodes = new SuggestBox(aiportCodesList);
		langs = new SuggestBox(langList);
		
		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		 
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");
		
		Panel aiportCodesPanel = new FlowPanel();
		aiportCodesPanel.setStyleName("flowPanelVerRowForm");
		aiportCodesPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().airportCodeOptional())));
		aiportCodesPanel.add(UiUtils.setWidth(airportCodes, 100.0, Unit.PCT));
		
		Panel langPanel = new FlowPanel();
		langPanel.setStyleName("flowPanelVerRowForm");
		langPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().langOptional())));
		langPanel.add(UiUtils.setWidth(langs, 100.0, Unit.PCT));
		
		panel.add(aiportCodesPanel);
		panel.add(langPanel);
		
		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panel ;
	}
	
	@Override
	public LanguageCode getLang() {
		try {
			if (langs.getValue().isEmpty()) {
				return null ;
			} else {
				return LanguageCode.valueOf(langs.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}


	@Override
	public AirportCode getAirportCode() {
		try {
			if (airportCodes.getValue().isEmpty()) {
				return null ;
			} else {
				return AirportCode.valueOf(airportCodes.getValue().toUpperCase());
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			// invalid input...
			// ...
			return null ;
		}
	}
}
