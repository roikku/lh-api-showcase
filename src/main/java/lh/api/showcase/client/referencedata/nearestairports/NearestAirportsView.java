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

package lh.api.showcase.client.referencedata.nearestairports;


import lh.api.showcase.client.BasicAbstractFormResultView;
import lh.api.showcase.client.Messages;
import lh.api.showcase.client.UiUtils;
import lh.api.showcase.shared.LanguageCode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.DoubleBox;
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

public class NearestAirportsView extends BasicAbstractFormResultView<NearestAirport> implements NearestAirportsPresenter.Display {
	
	static class NearestAirportsGrid extends Grid<NearestAirport> {
		public NearestAirportsGrid() {
			super();
			setSelectionMode(SelectionMode.SINGLE);
			this.setWidth("750px");
			this.setHeightMode(HeightMode.ROW);
			this.setHeightByRows(8);
			
			addColumn(new Column<String, NearestAirport>(Messages.Util.INSTANCE.get().airportCode()) {
				@Override
				public String getValue(NearestAirport row) {
					return row.getAirportCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, NearestAirport>(Messages.Util.INSTANCE.get().countryCode()) {
				@Override
				public String getValue(NearestAirport row) {
					return row.getCountryCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, NearestAirport>(Messages.Util.INSTANCE.get().cityCode()) {
				@Override
				public String getValue(NearestAirport row) {
					return row.getCityCode();
				}
			}).setWidth(110);
			addColumn(new Column<String, NearestAirport>(Messages.Util.INSTANCE.get().distance()) {
				@Override
				public String getValue(NearestAirport row) {
					
					NumberFormat nf = NumberFormat.getFormat("#.0") ;
					StringBuilder sb = new StringBuilder () ;
					sb.append(nf.format(row.getDistance())) ;
					sb.append(" ") ;
					sb.append(row.getUnitOfMeasurement()) ;
					return sb.toString() ;
				}
			}).setWidth(110);
			addColumn(new Column<String, NearestAirport>(Messages.Util.INSTANCE.get().type()) {
				@Override
				public String getValue(NearestAirport row) {
					return row.getLocationType();
				}
			}).setWidth(110);
			addColumn(UiUtils.<NearestAirport>getMultiLingualNameColumn(150));
		}
	}

	private DoubleBox latitude = new DoubleBox() ; 
	private DoubleBox longitude = new DoubleBox() ; 	
	private SuggestBox langs ;
	
	public NearestAirportsView () {
		super (Messages.Util.INSTANCE.get().nearestAirports(), new NearestAirportsGrid ()) ;
		
		final MultiWordSuggestOracle langList = new MultiWordSuggestOracle();
		for (LanguageCode lc : LanguageCode.values ()){
			langList.add(lc.toString());
		}
		langs = new SuggestBox(langList);
		
		latitude = new DoubleBox() ; 
		longitude = new DoubleBox() ; 
		latitude.setValue(0.0);
		latitude.setMaxLength(5);
		latitude.addKeyPressHandler(new UiUtils.DoublesOnlyKeyPressHandler());
		longitude.setValue(0.0);
		longitude.setMaxLength(5);
		longitude.addKeyPressHandler(new UiUtils.DoublesOnlyKeyPressHandler());
		
		init () ;
	}
	
	protected Widget getForm () {
		VerticalPanel panelV = new VerticalPanel () ;
		panelV.setWidth("100%");
		((HasHorizontalAlignment) panelV).setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Panel panel = new FlowPanel() ;
		panel.setStyleName("flowPanelMainForm");

		Panel latitudePanel = new FlowPanel();
		latitudePanel.setStyleName("flowPanelVerRowForm");
		latitudePanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().latitude())));
		latitudePanel.add(UiUtils.setWidth(latitude, 100.0, Unit.PCT));
		
		Panel longitudePanel = new FlowPanel();
		longitudePanel.setStyleName("flowPanelVerRowForm");
		longitudePanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().longiture())));
		longitudePanel.add(UiUtils.setWidth(longitude, 100.0, Unit.PCT));
		
		Panel langPanel = new FlowPanel();
		langPanel.setStyleName("flowPanelVerRowForm");
		langPanel.add(UiUtils.setLeftHorizontalAlignment(new Label (Messages.Util.INSTANCE.get().langOptional())));
		langPanel.add(UiUtils.setWidth(langs, 100.0, Unit.PCT));
		
		panel.add(latitudePanel);
		panel.add(longitudePanel);
		panel.add(langPanel);
		
		Panel goPanel = new FlowPanel();
		goPanel.setStyleName("flowPanelVerRowForm");
		goPanel.add (UiUtils.setWidth(goButton, 100.0, Unit.PCT)) ;
		
		panel.add(goPanel) ;
		
		panelV.add(panel);
		
		return panelV ;
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
	public Double getLatitude() {
		return latitude.getValue();
	}

	@Override
	public Double getLongitude() {
		return longitude.getValue();
	}

}
