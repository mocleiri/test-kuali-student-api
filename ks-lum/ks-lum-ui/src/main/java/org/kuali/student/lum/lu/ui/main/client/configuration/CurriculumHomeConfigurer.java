package org.kuali.student.lum.lu.ui.main.client.configuration;

import java.util.List;

import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.application.ViewContext;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.common.ui.client.widgets.layout.ContentBlockLayout;
import org.kuali.student.common.ui.client.widgets.layout.LinkContentBlock;
import org.kuali.student.common.ui.client.widgets.search.KSPicker;
import org.kuali.student.common.ui.client.widgets.search.SearchPanel;
import org.kuali.student.common.ui.client.widgets.search.SelectedResults;
import org.kuali.student.common.ui.shared.IdAttributes.IdType;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.rice.StudentIdentityConstants;
import org.kuali.student.lum.common.client.widgets.AppLocations;
import org.kuali.student.lum.lu.ui.course.client.widgets.RecentlyViewedBlock;
import org.kuali.student.lum.program.client.ProgramConstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CurriculumHomeConfigurer implements CurriculumHomeConstants{
	
	protected Metadata searchMetadata;
	
	public Widget configure(Metadata searchMeta){
		this.searchMetadata = searchMeta;
		ContentBlockLayout layout = new ContentBlockLayout(getMessage(CURRICULUM_MANAGEMENT));
		layout.addContentTitleWidget(getHowToWidget());
		
		//Create
    	LinkContentBlock create = new LinkContentBlock(
    			getMessage(CREATE), 
    			getMessage(CREATE_DESC));
    	create.addNavLinkWidget(getMessage(CREATE_COURSE), AppLocations.Locations.COURSE_PROPOSAL.getLocation());
    	create.add(getFindProposalsWidget());
    	create.addNavLinkWidget(getMessage(CREATE_PROGRAM), AppLocations.Locations.EDIT_PROGRAM.getLocation());
    	create.addNavLinkWidget(getMessage(ACTIONLIST), "/HOME");
    	
    	//View + Modify
    	LinkContentBlock viewModify = new LinkContentBlock(
    			getMessage(VIEW_MODIFY), 
    			getMessage(VIEW_MODIFY_DESC));
    	viewModify.addNavLinkWidget(getMessage(BROWSE_CATALOG), AppLocations.Locations.BROWSE_CATALOG.getLocation());
    	viewModify.add(getFindCoursesWidget());
    	viewModify.add(getFindProgramsWidget());
    	
    	//Tools
    	LinkContentBlock tools = new LinkContentBlock(
    			getMessage(TOOLS), 
    			getMessage(TOOLS_DESC));
    	tools.addNavLinkWidget(getMessage(COURSE_SETS), AppLocations.Locations.MANAGE_CLU_SETS.getLocation());
    	tools.addNavLinkWidget(getMessage(LO_CATEGORIES), AppLocations.Locations.MANAGE_LO_CATEGORIES.getLocation());
    	//Coming soon
    	Label depAnalysis = new Label(getMessage(DEP_ANALYSIS));
    	depAnalysis.setStyleName("contentBlock-navLink-disabled");
    	tools.add(depAnalysis);
    	Label learningObjectives = new Label(getMessage(LOS));
    	learningObjectives.setStyleName("contentBlock-navLink-disabled");
    	tools.add(learningObjectives);
    	
    	//RecentlyViewed
    	RecentlyViewedBlock recent = new RecentlyViewedBlock(
    			getMessage(RECENTLY_VIEWED), 
    			getMessage(RV_DESC));
    	
    	//Add all blocks
    	layout.addContentBlock(create);
    	layout.addContentBlock(viewModify);
    	layout.addContentBlock(tools);
    	layout.addContentBlock(recent);
    	
    	return layout;
	}
	
	
	protected Widget getFindProposalsWidget(){
		final Widget searchWidget;
		if(searchMetadata != null){
		Metadata metadata = searchMetadata.getProperties().get("findProposal");                
	        searchWidget = new KSPicker(metadata.getInitialLookup(), metadata.getAdditionalLookups());
	        SearchPanel panel = ((KSPicker) searchWidget).getSearchPanel();
	        if(panel != null){
	        	panel.setMutipleSelect(false);
	        }
	        ((KSPicker) searchWidget).setAdvancedSearchCallback(new Callback<List<SelectedResults>>(){
	
				@Override
				public void exec(List<SelectedResults> result) {
					SelectedResults value = result.get(0);
					ViewContext viewContext = new ViewContext();
					viewContext.setId(value.getResultRow().getId());
					viewContext.setAttribute(StudentIdentityConstants.DOCUMENT_TYPE_NAME, value.getResultRow().getValue("proposal.resultColumn.proposalType"));
					viewContext.setIdType(IdType.KS_KEW_OBJECT_ID);
					Application.navigate(AppLocations.Locations.COURSE_PROPOSAL.getLocation(), viewContext);
					((KSPicker) searchWidget).getSearchWindow().hide();
				}
			});
		}
		else{
			searchWidget = new Label(getMessage(FIND_PROPOSALS));
			searchWidget.setStyleName("contentBlock-navLink-disabled");
		}
        searchWidget.setStyleName("contentBlock-navLink");
        return searchWidget;
	}
	
	protected Widget getFindCoursesWidget(){
		Widget searchWidget;
		if(searchMetadata != null){
			Metadata metadata = searchMetadata.getProperties().get("findCourseTmp");
	        searchWidget = new KSPicker(metadata.getInitialLookup(), metadata.getAdditionalLookups());
	        SearchPanel panel = ((KSPicker) searchWidget).getSearchPanel();
	        if(panel != null){
	        	panel.setMutipleSelect(false);
	        }
	        ((KSPicker) searchWidget).addValuesChangeHandler(new ValueChangeHandler<List<String>>(){
	            public void onValueChange(ValueChangeEvent<List<String>> event) {
	                List<String> selection = event.getValue();
	                ViewContext viewContext = new ViewContext();
	                viewContext.setId(selection.get(0));
	                viewContext.setIdType(IdType.OBJECT_ID);
	                Application.navigate(AppLocations.Locations.VIEW_COURSE.getLocation(), viewContext);
	            }                    
	        });
	        searchWidget.setStyleName("contentBlock-navLink");
		}
		else{
			searchWidget = new Label(getMessage(FIND_COURSES));
			searchWidget.setStyleName("contentBlock-navLink-disabled");
		}
        return searchWidget;
	}
	
	protected Widget getFindProgramsWidget(){
		final Widget searchWidget;
		if(searchMetadata != null){
			Metadata metadata = searchMetadata.getProperties().get("findMajor");  
	        searchWidget = new KSPicker(metadata.getInitialLookup(), metadata.getAdditionalLookups());
	        SearchPanel panel = ((KSPicker) searchWidget).getSearchPanel();
	        if(panel != null){
	        	panel.setMutipleSelect(false);
	        }
//	        ((KSPicker) searchWidget).addValuesChangeHandler(new ValueChangeHandler<List<String>>(){
//	            public void onValueChange(ValueChangeEvent<List<String>> event) {
//	                List<String> selection = event.getValue();
//	                ViewContext viewContext = new ViewContext();
//	                viewContext.setId(selection.get(0));
//	                viewContext.setIdType(IdType.OBJECT_ID);
//	                Application.navigate(AppLocations.Locations.VIEW_PROGRAM.getLocation(), viewContext);
//	            }
//	        });
            ((KSPicker) searchWidget).setAdvancedSearchCallback(new Callback<List<SelectedResults>>(){

                @Override
                public void exec(List<SelectedResults> result) {
                    SelectedResults value = result.get(0);
                    ViewContext viewContext = new ViewContext();
                    viewContext.setId(value.getResultRow().getId());
                    viewContext.setAttribute(ProgramConstants.TYPE, value.getResultRow().getValue("lu.resultColumn.luOptionalType"));
                    viewContext.setIdType(IdType.OBJECT_ID);
                    Application.navigate(AppLocations.Locations.VIEW_PROGRAM.getLocation(), viewContext);
                    ((KSPicker) searchWidget).getSearchWindow().hide();
                }
            });

		}
		else{
			searchWidget = new Label(getMessage(FIND_MAJORS));
			searchWidget.setStyleName("contentBlock-navLink-disabled");
		}
        searchWidget.setStyleName("contentBlock-navLink");
        return searchWidget;
	}
	
	protected Widget getHowToWidget(){
		Anchor widget = new Anchor(getMessage(HOW_TO));
		widget.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
	            final KSLightBox pop = new KSLightBox();
	            pop.setWidget(new CurriculumHomeHelpTable());
	            pop.setSize(800, 600);
	            pop.show();
			}
		});
		widget.setStyleName("contentBlock-navLink");
		return widget;
	}
	
	private String getMessage(String key){
		return Application.getApplicationContext().getMessage(key);
	}

}
