package org.kuali.student.lum.lu.ui.tools.client.configuration;

import org.kuali.student.common.ui.client.service.SearchRpcService;
import org.kuali.student.common.ui.client.service.SearchRpcServiceAsync;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.common.ui.client.widgets.layout.VerticalFlowPanel;
import org.kuali.student.common.ui.client.widgets.search.SearchResultsTable;
import org.kuali.student.core.assembly.data.LookupMetadata;
import org.kuali.student.core.search.dto.SearchRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchResultsLightBox {
    private SearchRpcServiceAsync searchRpcServiceAsync = GWT.create(SearchRpcService.class);
    private KSLightBox lightbox;
    private SearchResultsTable searchResultsTable;
    private KSButton closeButton;
    private SearchRequest searchRequest;
    private LookupMetadata lookupMetadata;
    
    public SearchResultsLightBox(String title, SearchRequest searchRequest, LookupMetadata lookupMetadata) {
        VerticalPanel contents = new VerticalPanel();
        lightbox = new KSLightBox(title);
        searchResultsTable = new SearchResultsTable();
        searchResultsTable.addStyleName("KS-Advanced-Search-Results-Table");
        closeButton = new KSButton("Close");
        this.searchRequest = searchRequest;
        this.lookupMetadata = lookupMetadata;
        contents.add(searchResultsTable);
        contents.add(closeButton);
        
        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        
        lightbox.setWidget(contents);
    }
    
    public void setSize(int width, int height) {
        lightbox.setSize(width, height);
    }
    
    public void show() {
        searchResultsTable.performSearch(searchRequest, lookupMetadata.getResults(), lookupMetadata.getResultReturnKey());
        lightbox.show();
    }
    
    public void hide() {
        lightbox.hide();
    }
}
