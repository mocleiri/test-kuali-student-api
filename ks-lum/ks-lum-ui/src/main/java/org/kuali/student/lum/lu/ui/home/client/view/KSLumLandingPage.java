/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.lum.lu.ui.home.client.view;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.mvc.Controller;
import org.kuali.student.common.ui.client.mvc.ViewComposite;
import org.kuali.student.common.ui.client.widgets.KSActionItemList;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.KSLandingPage;
import org.kuali.student.common.ui.client.widgets.NavigationHandler;
import org.kuali.student.common.ui.client.widgets.StylishDropDown;
import org.kuali.student.common.ui.client.widgets.KSActionItemList.ListLocation;
import org.kuali.student.common.ui.client.widgets.menus.KSMenuItemData;
import org.kuali.student.common.ui.client.widgets.menus.KSMenu.MenuImageLocation;
import org.kuali.student.lum.lu.ui.main.client.controller.LUMApplicationManager.LUMViews;
import org.kuali.student.lum.lu.ui.main.client.theme.LumTheme;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class KSLumLandingPage extends ViewComposite{
	private KSLandingPage landingPageShell = new KSLandingPage("Curriculum Management", LumTheme.INSTANCE.getLumImages().getCurriculumManagementImage(),
			"Find a learning unit to <b>view</b>, <b>modify</b>, or <b>retire.</b>");

	private VerticalPanel layout = new VerticalPanel();
	private HorizontalPanel searchPanel = new HorizontalPanel();
	private HorizontalPanel actionItemPanel = new HorizontalPanel();
	private KSLabel searchLabel = new KSLabel("Search");
	private KSActionItemList recentlyViewedList = new KSActionItemList("Recently Viewed");
	private KSActionItemList tasklistItemsList = new KSActionItemList("Tasklist Items");
	//private KSSearchBox searchBox = new KSSearchBox();  //use KSPicker
	private ListBox luTypeSelector = new ListBox();
	private StylishDropDown proposeDropDown = new StylishDropDown(
			"Propose New Curriculum",
			LumTheme.INSTANCE.getLumImages().getAddProposalImage(),
			MenuImageLocation.LEFT);

	//TODO fix these widgets
	private KSLabel subDescription = new KSLabel("Or manage a group of them.");
	//Unknown behavior for analyze

    public class LumLandingPageHandler extends NavigationHandler{
		public LumLandingPageHandler(Enum<?> navEnum) {
			super(KSLumLandingPage.this.getController(), navEnum);
		}

		@Override
		public void beforeNavigate(Callback<Boolean> callback) {
			//TODO do we need to do anything before navigation on this page?
			callback.exec(true);
		}

    }

	public KSLumLandingPage(Controller controller){
		super(controller, "Curriculum Management");
		layout.add(searchLabel);
		setupSearchParams();
		createLuTypeSelector();
		searchPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		searchPanel.add(luTypeSelector);
		//searchPanel.add(searchBox);     //use KSPicker


		layout.add(searchPanel);
		recentlyViewedList.add("The Novel", "Course Proposal", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}}, ListLocation.TOP);
		recentlyViewedList.add("Introduction to Geology", "Course Proposal", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}}, ListLocation.TOP);
		tasklistItemsList.add("The Novel", "Course Proposal", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}}, ListLocation.TOP);
		tasklistItemsList.add("Introduction to Geology", "Course Proposal", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}}, ListLocation.TOP);
		actionItemPanel.add(recentlyViewedList);
		actionItemPanel.add(tasklistItemsList);
		layout.add(actionItemPanel);
		landingPageShell.setContent(layout);
		landingPageShell.setSubDescWidget(subDescription);
		//landingPageShell.addTitlePanelWidget(analyzeCurriculum);
		createProposeDropDown();
		landingPageShell.addTitlePanelWidget(proposeDropDown);
		this.initWidget(landingPageShell);

		proposeDropDown.addStyleName("KS-LPNavigation-DropDown");
		searchLabel.addStyleName("KS-LumLandingPage-SearchLabel");
		searchPanel.addStyleName("KS-LumLandingPage-SearchPanel");
		actionItemPanel.addStyleName("KS-LumLandingPage-ActionItemPanel");
		landingPageShell.addStyleName("KS-LumLandingPage");
	}

	private void createProposeDropDown() {

		List<KSMenuItemData> proposeItems = new ArrayList<KSMenuItemData>();
    	proposeItems.add(new KSMenuItemData("Courses"){{
    		addSubItem(new KSMenuItemData("Academic Course", new LumLandingPageHandler(LUMViews.CREATE_COURSE)));
    		addSubItem(new KSMenuItemData("Non Academic Course", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    	}});
    	proposeItems.add(new KSMenuItemData("Programs"){{
    		addSubItem(new KSMenuItemData("Undergraduate"){{
    			addSubItem(new KSMenuItemData("Associate Degree", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Baccalaureate Degree Major", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Baccalaureate Degree Minor", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Undergraduate Certificate", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    		}});
    		addSubItem(new KSMenuItemData("Graduate"){{
    			addSubItem(new KSMenuItemData("Graduate Certificate", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Masters Degree", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Doctoral Degree", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    		}});
    		addSubItem(new KSMenuItemData("Professional"){{
    			addSubItem(new KSMenuItemData("Professional Degree", new LumLandingPageHandler(LUMViews.HOME_MENU)));
        		addSubItem(new KSMenuItemData("Professional Certificate", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    		}});
    	}});

    	proposeItems.add(new KSMenuItemData("Experiential Learning"){{
    		addSubItem(new KSMenuItemData("Externship", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    		addSubItem(new KSMenuItemData("Internship", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    		addSubItem(new KSMenuItemData("Practicum", new LumLandingPageHandler(LUMViews.HOME_MENU)));
    	}});
		proposeDropDown.setItems(proposeItems);
	}

	private void createLuTypeSelector(){

		//TODO
		luTypeSelector.addItem("Courses", "TODO");
		luTypeSelector.addItem("TODO", "TODO");
		luTypeSelector.addItem("TODO", "TODO");
		luTypeSelector.addItem("TODO", "TODO");
	}

	private void setupSearchParams(){
		//TODO
		//searchBox.addRequiredSearchParameter("luType", luTypeSelector.getValue(luTypeSelector.getSelectedIndex()));
	}

	public KSActionItemList getRecentlyViewedList(){

		return recentlyViewedList;
	}

	public KSActionItemList getTasklistItemsList(){
		return tasklistItemsList;
	}
}
