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

package org.kuali.student.common.ui.client.widgets.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.configurable.mvc.DefaultWidgetFactory;
import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSDropDown;
import org.kuali.student.common.ui.client.widgets.KSLabel;
import org.kuali.student.common.ui.client.widgets.KSButtonAbstract.ButtonStyle;
import org.kuali.student.common.ui.client.widgets.field.layout.element.FieldElement;
import org.kuali.student.common.ui.client.widgets.layout.HorizontalBlockFlowPanel;
import org.kuali.student.common.ui.client.widgets.layout.VerticalFlowPanel;
import org.kuali.student.common.ui.client.widgets.list.KSSelectItemWidgetAbstract;
import org.kuali.student.common.ui.client.widgets.list.ListItems;
import org.kuali.student.common.ui.client.widgets.list.SelectionChangeEvent;
import org.kuali.student.common.ui.client.widgets.list.SelectionChangeHandler;
import org.kuali.student.common.ui.client.widgets.searchtable.ResultRow;
import org.kuali.student.core.assembly.data.LookupMetadata;
import org.kuali.student.core.assembly.data.LookupParamMetadata;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.data.LookupMetadata.Usage;
import org.kuali.student.core.assembly.data.Metadata.WriteAccess;
import org.kuali.student.core.search.dto.SearchParam;
import org.kuali.student.core.search.dto.SearchRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchPanel extends Composite{

    //Layout configuration
    private VerticalFlowPanel layout = new VerticalFlowPanel();
    private SimplePanel searchSelectorPanel = new SimplePanel();
    private VerticalFlowPanel tablePanel = new VerticalFlowPanel();
    private HorizontalBlockFlowPanel enteredCriteriaString = new HorizontalBlockFlowPanel();
    private CollapsablePanel modifySearchPanel;
    private boolean resultsShown = false;

    //Search data
    private List<LookupMetadata> lookups = new ArrayList<LookupMetadata>();
    private LookupMetadata currentSearchLookupMetadata;
    private TempSearchBackedTable table = null;             //FIXME: Swap with new table implementation
    public static enum SearchStyle{ADVANCED, CUSTOM};

    private String criteriaInstructions = getMessage("searchPanelEnterFields");
    private KSLabel enteredCriteriaHeading = new KSLabel(getMessage("searchPanelCriteria"));
    private boolean multiSelect = false;

    private List<Callback<LookupMetadata>> lookupChangedCallbacks = new ArrayList<Callback<LookupMetadata>>();
    private SearchParametersWidget activeSearchParametersWidget = null;
    // uses "name" of the lookup metadata to lookup the widget that layouts the search UI
    private Map<String, SearchParametersWidget> searchParameterWidgetMap = new HashMap<String, SearchParametersWidget>();
    private String selectedLookupName;

    interface SearchParametersWidget {
        public SearchRequest getSearchRequest();
    }

    public SearchPanel(LookupMetadata meta){
        lookups.add(meta);
        setupSearch();
        this.initWidget(layout);
    }

    public SearchPanel(List<LookupMetadata> metas){
        lookups = metas;
        setupSearch();
        this.initWidget(layout);
    }

    public void setupSearch() {
        Widget searchParamPanel;
        activeSearchParametersWidget = null;
        if (lookups.size() == 1) {
            searchParamPanel = createSearchParamPanel(lookups.get(0));
            selectedLookupName = lookups.get(0).getName();
        } else {
            LinkedHashMap<String, Widget> searches = new LinkedHashMap<String, Widget>();
            LinkedHashMap<String, LookupMetadata> searchLookups = new LinkedHashMap<String, LookupMetadata>();
            for(LookupMetadata lookup: lookups){
                searches.put(lookup.getName(), createSearchParamPanel(lookup));
                searchLookups.put(lookup.getName(), lookup);
            }
            selectedLookupName = lookups.get(0).getName();
            searchParamPanel = new SwappablePanel(searches);
            ((SwappablePanel)searchParamPanel).setSearchLookups(searchLookups);
            ((SwappablePanel)searchParamPanel).addLookupChangedCallback(new Callback<LookupMetadata>() {
                @Override
                public void exec(LookupMetadata selectedLookup) {
                    activeSearchParametersWidget = searchParameterWidgetMap.get(selectedLookup.getName());
                    selectedLookupName = selectedLookup.getName();
                    if (lookupChangedCallbacks != null) {
                        for (Callback<LookupMetadata> callback : lookupChangedCallbacks) {
                            callback.exec(selectedLookup);
                        }
                    }
                }
            });
        }

        tablePanel.clear();
        layout.clear();
        layout.addStyleName("KS-Picker-Border");
        layout.addStyleName("KS-Search-Panel");


        searchSelectorPanel.setWidget(searchParamPanel);
        layout.add(searchSelectorPanel);
        enteredCriteriaHeading.addStyleName("ks-form-module-single-line-margin");
        enteredCriteriaHeading.addStyleName("KS-Advanced-Search-Search-Criteria-Label");
        tablePanel.add(enteredCriteriaHeading);
        tablePanel.add(enteredCriteriaString);

        table = new TempSearchBackedTable();
        table.addStyleName("KS-Advanced-Search-Results-Table");
        tablePanel.add(table);
        layout.add(tablePanel);
        tablePanel.setVisible(false);
    }

    private Widget createSearchParamPanel(LookupMetadata meta){
        ParamListItems listItems = new ParamListItems(meta);
        final AdvancedSearch advancedSearch = new AdvancedSearch(meta);
        LinkPanel panel = new LinkPanel(SearchStyle.ADVANCED, advancedSearch);
        if (activeSearchParametersWidget == null) {
            activeSearchParametersWidget = advancedSearch;
        }
        searchParameterWidgetMap.put(meta.getName(), advancedSearch);

        //check whether we need custom tab i.e. whether we have at least one parameter that should appear on custom tab
        for(LookupParamMetadata metaParam: meta.getParams()){
            if ((metaParam.getUsage() == Usage.CUSTOM) || (metaParam.getUsage() == Usage.ADVANCED_CUSTOM)) {
                final CustomizedSearch customizedSearch = new CustomizedSearch(meta, listItems);
                KSButton button = panel.addLinkToPanel(SearchStyle.ADVANCED, getMessage("searchPanelCustomizeSearch"), SearchStyle.CUSTOM);
                button.addClickHandler(new ClickHandler(){

                    @Override
                    public void onClick(ClickEvent event) {
                        tablePanel.setVisible(false);
                        activeSearchParametersWidget = customizedSearch;
                    }});
                button.addStyleName("KS-Advanced-Search-Button");
                button.getParent().addStyleName("clearfix");
                panel.addPanel(SearchStyle.CUSTOM, customizedSearch);
                button = panel.addLinkToPanel(SearchStyle.CUSTOM, getMessage("searchPanelReturnToAdvancedSearch"), SearchStyle.ADVANCED);
                button.addClickHandler(new ClickHandler(){

                    @Override
                    public void onClick(ClickEvent event) {
                        tablePanel.setVisible(false);
                        activeSearchParametersWidget = advancedSearch;
                    }});
                button.addStyleName("KS-Advanced-Search-Button");
                button.getParent().addStyleName("clearfix");
                break;
            }
        }

        return panel;
    }

    public SearchRequest getSearchRequest() {
        if (activeSearchParametersWidget != null) {
            return activeSearchParametersWidget.getSearchRequest();
        }
        return null;
    }

    private class CustomizedSearch extends Composite implements SearchParametersWidget {

        private List<CustomLine> lines = new ArrayList<CustomLine>();
        private VerticalFlowPanel layout = new VerticalFlowPanel();
        private VerticalFlowPanel linePanel = new VerticalFlowPanel();
        private VerticalFlowPanel buttonPanel = new VerticalFlowPanel();
        private LookupMetadata meta;

        public CustomizedSearch(final LookupMetadata meta, final ParamListItems listItems){

            KSLabel instrLabel = new KSLabel(criteriaInstructions);
            layout.add(instrLabel);

            layout.add(linePanel);
            CustomLine line = new CustomLine(meta, listItems);
            line.addStyleName("ks-form-module-single-line-margin");
            linePanel.add(line);
            lines.add(line);
            this.meta = meta;

            KSButton addCriteria = new KSButton(getMessage("searchPanelAddCriteria"), ButtonStyle.SECONDARY);
            addCriteria.addClickHandler(new ClickHandler(){

                @Override
                public void onClick(ClickEvent event) {
                    CustomLine line = new CustomLine(meta, listItems);
                    line.addStyleName("ks-form-module-single-line-margin");
                    linePanel.add(line);
                    lines.add(line);
                }
            });

            addCriteria.addStyleName("ks-form-module-single-line-margin");
            layout.add(addCriteria);

            KSButton searchButton = new KSButton(getMessage("search"), ButtonStyle.PRIMARY);
            String previewMode = meta.getWidgetOptionValue(LookupMetadata.WidgetOption.ADVANCED_LIGHTBOX_PREVIEW_MODE);
            if (previewMode != null && previewMode.equals("true")) {
                searchButton.setText("Preview");
            }
            searchButton.addStyleName("KS-Advanced-Search-Button");
            searchButton.addStyleName("clearboth");
            searchButton.addClickHandler(new ClickHandler(){

                @Override
                public void onClick(ClickEvent event) {
                    currentSearchLookupMetadata = meta;
                    SearchRequest sr = getSearchRequest();
                    table.performSearch(sr, meta.getResults(), meta.getResultReturnKey()); //temporary hack before we get a new table
                    tablePanel.setVisible(true);
                    List<HasSearchParam> userCriteria = new ArrayList<HasSearchParam>();
                    for(CustomLine field: lines){
                        SearchParam param = field.getSearchParam();
                        //TODO is this check needed here? probably. assuming string here
                        if((param.getValue() != null)){
                            userCriteria.add(field);
                        }
                    }
                    showCriteriaChosen(userCriteria);

                    if(!resultsShown){
                        searchSelectorPanel.removeFromParent();
                        modifySearchPanel = new CollapsablePanel(getMessage("searchPanelModifySearch"), searchSelectorPanel, false);
                        KSButton label = modifySearchPanel.getLabel();
                        label.addClickHandler(new ClickHandler(){

                            @Override
                            public void onClick(ClickEvent event) {
                                tablePanel.setVisible(false);

                            }});
                        SearchPanel.this.layout.insert(modifySearchPanel, 0);
                    }
                    else{
                        modifySearchPanel.close();
                    }
                    resultsShown = true;
                }
            });
            layout.add(searchButton);
            this.initWidget(layout);
        }

        @Override
        public SearchRequest getSearchRequest() {
            //Create search request and then pass it to the table
            //TODO pass search to the table
            SearchRequest sr = new SearchRequest();
            List<SearchParam> params = new ArrayList<SearchParam>();
            for(CustomLine field: lines){
                SearchParam param = field.getSearchParam();
                //TODO is this check needed here? probably. assuming string here
                if((param.getValue() != null)){
                    params.add(param);
                }
            }

            //add search criteria widgets to the custom tab
            for(LookupParamMetadata metaParam: meta.getParams()){

                //select only parameters shown on custom search tab
                if ((metaParam.getUsage() != Usage.CUSTOM) && (metaParam.getUsage() != Usage.ADVANCED_CUSTOM)) {
                    continue;
                }

                if(metaParam.getWriteAccess() == WriteAccess.NEVER){
                    SearchParam param = new SearchParam();
                    param.setKey(metaParam.getKey());
                    if(metaParam.getDefaultValueList()==null){
                        param.setValue(metaParam.getDefaultValueString());
                    }else{
                        param.setValue(metaParam.getDefaultValueList());
                    }
                    params.add(param);
                }
                else if(metaParam.getWriteAccess() == WriteAccess.WHEN_NULL){
                    if((metaParam.getDefaultValueString() != null && !metaParam.getDefaultValueString().isEmpty())||
                       (metaParam.getDefaultValueList() != null && !metaParam.getDefaultValueList().isEmpty())){
                        SearchParam param = new SearchParam();
                        param.setKey(metaParam.getKey());
                        if(metaParam.getDefaultValueList()==null){
                            param.setValue(metaParam.getDefaultValueString());
                        }else{
                            param.setValue(metaParam.getDefaultValueList());
                        }
                        params.add(param);
                    }
                }
            }

            sr.setParams(params);
            sr.setSearchKey(meta.getSearchTypeId());
            return sr;
        }

    }

    private interface HasSearchParam{
        public SearchParam getSearchParam();
        public String getFieldName();
    }

    private static class CustomLine extends Composite implements HasSearchParam{
        private KSDropDown paramSelector = new KSDropDown();
        private SimplePanel widgetPanel = new SimplePanel();
        private Widget widget = null;
        private String key;
        private HorizontalBlockFlowPanel layout = new HorizontalBlockFlowPanel();
        private ParamListItems listItems;

        public CustomLine(LookupMetadata meta, final ParamListItems listItems){
            this.listItems = listItems;
            paramSelector.setBlankFirstItem(false);
            paramSelector.setListItems(listItems);
            String id = meta.getParams().get(0).getKey();
            paramSelector.selectItem(id);
            widget = listItems.getWidget(id);
            key = id;
            widgetPanel.setWidget(widget);
            paramSelector.addSelectionChangeHandler(new SelectionChangeHandler(){

                @Override
                public void onSelectionChange(SelectionChangeEvent event) {
                    String id = ((KSSelectItemWidgetAbstract)event.getWidget()).getSelectedItem();
                    widget = listItems.getWidget(id);
                    widgetPanel.setWidget(widget);
                    key = id;

                }
            });
            layout.add(paramSelector);
            layout.add(widgetPanel);
            this.initWidget(layout);
        }

        public SearchParam getSearchParam(){
            return SearchPanel.getSearchParam(widget, key);
        }

        public String getKey(){
            return key;
        }

        public String getFieldName(){
            String id = paramSelector.getSelectedItem();
            return listItems.getItemText(id);
        }
    }

    private class AdvancedSearch extends Composite implements SearchParametersWidget {
        private List<SearchField> searchFields = new ArrayList<SearchField>();
        private LookupMetadata meta;

        public AdvancedSearch(final LookupMetadata meta){
            VerticalFlowPanel panel = new VerticalFlowPanel();

            KSLabel instrLabel = new KSLabel();
            panel.add(instrLabel);
            this.meta = meta;

            //add widget for each search criteria to the advanced tab
            boolean allFieldsRequired = true;
            for(LookupParamMetadata param: meta.getParams()){

                //select only parameters shown on advanced search tab
                if ((param.getUsage() != Usage.ADVANCED) && (param.getUsage() != Usage.ADVANCED_CUSTOM)) {
                    continue;
                }

                if ((param.getWriteAccess() == WriteAccess.ALWAYS) || (param.getWriteAccess() == WriteAccess.REQUIRED)){
                    SearchField paramField = new SearchField(param);
                    searchFields.add(paramField);
                    panel.add(paramField);
                }
                else if (param.getWriteAccess() == WriteAccess.WHEN_NULL){
                    if(param.getDefaultValueString() == null && param.getDefaultValueList() == null){
                        SearchField paramField = new SearchField(param);
                        searchFields.add(paramField);
                        panel.add(paramField);
                    }
                }

                if (param.getWriteAccess() != Metadata.WriteAccess.REQUIRED) {
                    allFieldsRequired = false;
                }
            }

            //do not show criteria instructions if we have only one criteria field or in case all fields are required
            if ((searchFields.size() > 1) || (allFieldsRequired == false)) {
                instrLabel.setText(criteriaInstructions);
            }

            KSButton searchButton = new KSButton(getMessage("search"), ButtonStyle.PRIMARY);
            String previewMode = meta.getWidgetOptionValue(LookupMetadata.WidgetOption.ADVANCED_LIGHTBOX_PREVIEW_MODE);
            if (previewMode != null && previewMode.equals("true")) {
                searchButton.setText("Preview");
            }
            //searchButton.addStyleName("KS-Advanced-Search-Button");
            //searchButton.addStyleName("clearboth");
            searchButton.addClickHandler(new ClickHandler(){

                @Override
                public void onClick(ClickEvent event) {
                    SearchRequest sr = getSearchRequest();
                    currentSearchLookupMetadata = meta;
                    table.performSearch(sr, meta.getResults(), meta.getResultReturnKey());
                    tablePanel.setVisible(true);
                    List<HasSearchParam> userCriteria = new ArrayList<HasSearchParam>();

                    //initialize search parameters if user entered values into search criteria fields in UI
                    for(SearchField field: searchFields){
                        SearchParam param = field.getSearchParam();
                        //TODO is this null check needed here? probably. assuming string here
                        //TODO make check more robust here/inserting params more robust
                        //do not pass to the search parameters that are empty
                        //FIXME hack - comparison to 'optional' - replace with check against 'optional' field and update related lookup metadata
                        if ((param.getValue() != null) && ((param.getValue().toString().trim().isEmpty() == false) || (param.getKey().toLowerCase().indexOf("optional") == -1))) {
                            userCriteria.add(field);
                        }
                    }
                    showCriteriaChosen(userCriteria);

                    if(!resultsShown){
                        searchSelectorPanel.removeFromParent();
                        modifySearchPanel = new CollapsablePanel(getMessage("searchPanelModifySearch"), searchSelectorPanel, false);
                        KSButton label = modifySearchPanel.getLabel();
                        label.addClickHandler(new ClickHandler(){

                            @Override
                            public void onClick(ClickEvent event) {
                                tablePanel.setVisible(false);

                            }});
                        SearchPanel.this.layout.insert(modifySearchPanel, 0);
                    }
                    else{
                        modifySearchPanel.close();
                    }
                    resultsShown = true;
                }
            });
            panel.add(searchButton);
            this.initWidget(panel);
        }

        @Override
        public SearchRequest getSearchRequest() {
            SearchRequest sr = new SearchRequest();
            List<SearchParam> params = new ArrayList<SearchParam>();

            //initialize search parameters if user entered values into search criteria fields in UI
            for(SearchField field: searchFields){
                SearchParam param = field.getSearchParam();
                //TODO is this null check needed here? probably. assuming string here
                //TODO make check more robust here/inserting params more robust
                //do not pass to the search parameters that are empty
                //FIXME hack - comparison to 'optional' - replace with check against 'optional' field and update related lookup metadata
                if ((param.getValue() != null) && ((param.getValue().toString().trim().isEmpty() == false) || (param.getKey().toLowerCase().indexOf("optional") == -1))) {
                    params.add(param);
                }
            }

            //initialize search parameters that are hidden from the UI because they are set to default context specific values
            for(LookupParamMetadata metaParam: meta.getParams()){
                if(metaParam.getWriteAccess() == WriteAccess.NEVER){
                    if ((metaParam.getDefaultValueString() == null || metaParam.getDefaultValueString().isEmpty())&&
                        (metaParam.getDefaultValueList() == null || metaParam.getDefaultValueList().isEmpty())) {
                        //FIXME throw an exception?
                        GWT.log("Key = " + metaParam.getKey() + " has write access NEVER but has no default value!", null);
                        continue;
                    }
                    SearchParam param = new SearchParam();
                    param.setKey(metaParam.getKey());
                    if(metaParam.getDefaultValueList()==null){
                        param.setValue(metaParam.getDefaultValueString());
                    }else{
                        param.setValue(metaParam.getDefaultValueList());
                    }
                    params.add(param);
                }
                else if(metaParam.getWriteAccess() == WriteAccess.WHEN_NULL){
                    if((metaParam.getDefaultValueString() != null && !metaParam.getDefaultValueString().isEmpty())||
                       (metaParam.getDefaultValueList() != null && !metaParam.getDefaultValueList().isEmpty())){
                        SearchParam param = new SearchParam();
                        param.setKey(metaParam.getKey());
                        if(metaParam.getDefaultValueList()==null){
                            param.setValue(metaParam.getDefaultValueString());
                        }else{
                            param.setValue(metaParam.getDefaultValueList());
                        }
                        params.add(param);
                    }
                }
            }
            sr.setParams(params);
            if (meta.getResultSortKey() != null) {
                sr.setSortColumn(meta.getResultSortKey());
            }
            sr.setSearchKey(meta.getSearchTypeId());
            return sr;
        }

    }

    private static class SearchField extends Composite implements HasSearchParam{

        private Widget widget = null;
        private LookupParamMetadata meta = null;
        private VerticalFlowPanel panel = new VerticalFlowPanel();
        private String fieldName;

        public SearchParam getSearchParam(){
            return SearchPanel.getSearchParam(widget, meta.getKey());
        }

        public SearchField(LookupParamMetadata param){
            meta = param;
            //TODO use message call here
            fieldName = param.getName();
            widget = DefaultWidgetFactory.getInstance().getWidget(param);
            if(param.getDefaultValueString() != null){
                //TODO Add handling of default value lists here
                if(widget instanceof HasText){
                    ((HasText) widget).setText(param.getDefaultValueString().toString());
                }
                else if(widget instanceof HasValue){
                    ((HasValue) widget).setValue(param.getDefaultValueString());
                }
            }

            //FIXME: remove because required field '*' indication will be part of FieldElement class
            if (param.getWriteAccess() == Metadata.WriteAccess.REQUIRED) {
                fieldName += " *";
            }

            FieldElement fieldElement = new FieldElement(fieldName, widget);
            fieldElement.getTitleWidget().addStyleName("KS-Picker-Criteria-Text");
            panel.add(fieldElement);
            panel.addStyleName("clear");

            this.initWidget(panel);
        }

        public Widget getFieldPanel(){
            return panel;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

    private static SearchParam getSearchParam(final Widget widget, String key){
        SearchParam param = new SearchParam();
        param.setKey(key);
        if(widget instanceof HasText){
            param.setValue(((HasText) widget).getText());
        }
        else if(widget instanceof HasValue){
            Object value = ((HasValue) widget).getValue();
            if(value != null){
            //TODO need to handle date and other types here, how they are converted for search, etc
                if(value instanceof String){
                    param.setValue((String)value);
                }
                else{
                    param.setValue(value.toString());
                    GWT.log("Fields in search probably(?) shouldnt have values other than string", null);
                }
            }
        }
        else if (widget instanceof KSPicker){
            param.setValue(((KSPicker)widget).getDisplayValue());
        }
        else {
            param.setValue("");
        }

        return param;
    }

    private void showCriteriaChosen(List<HasSearchParam> fields){
        enteredCriteriaString.clear();
        boolean first = true;;
        for(HasSearchParam field: fields){
            String name = field.getFieldName();
            //TODO Should be string only, needs type safety
            String value = field.getSearchParam().getValue().toString();
            if(!value.isEmpty()){
                HTMLPanel label = new HTMLPanel(name + ": <b>" + value + "</b> ");
                if (!first) {
                    label.addStyleName("KS-Advanced-Search-Search-Criteria-Text");
                }
                enteredCriteriaString.add(label);
                first = false;
            }
        }
    }

    public List<String> getSelectedIds(){
        List<String> ids = new ArrayList<String>();
        if(table != null){
            ids = table.getSelectedIds();
        }
        return ids;
    }

    public List<SelectedResults> getSelectedValues() {

        List<SelectedResults> selectedValues = new ArrayList<SelectedResults>();
        if (table != null) {
            List<ResultRow> selectedRows = table.getSelectedRows();
            for (ResultRow row : selectedRows) {
                String displayKey = row.getValue(currentSearchLookupMetadata.getResultDisplayKey());
                String returnKey = row.getValue(currentSearchLookupMetadata.getResultReturnKey());
                selectedValues.add(new SelectedResults(displayKey, returnKey));
                if (multiSelect == false) {
                    break;
                }
            }
        }

        return selectedValues;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    private static class ParamListItems implements ListItems{

        private List<LookupParamMetadata> params = new ArrayList<LookupParamMetadata>();

        public ParamListItems(LookupMetadata meta){
            params = meta.getParams();
        }

        @Override
        public List<String> getAttrKeys() {
            return new ArrayList<String>();
        }

        @Override
        public String getItemAttribute(String id, String attrkey) {
            return "";
        }

        @Override
        public int getItemCount() {
            return params.size();
        }

        @Override
        public List<String> getItemIds() {
            List<String> ids = new ArrayList<String>();
            for(LookupParamMetadata param: params){
                ids.add(param.getKey());
            }
            return ids;
        }

        @Override
        public String getItemText(String id) {
            String itemText = id;
            for(LookupParamMetadata param: params){
                if(param.getKey().equals(id)){
                    //TODO this should be a message key
                    itemText = param.getName();
                    break;
                }
            }
            return itemText;
        }

        public Widget getWidget(String id){
            Widget w = null;
            for(LookupParamMetadata param: params){
                if(param.getKey().equals(id)){
                    w = DefaultWidgetFactory.getInstance().getWidget(param);
                    break;
                }
            }
            return w;
        }
    }

    private String getMessage(final String msgKey) {
        return Application.getApplicationContext().getMessage(msgKey);
    }

    public void addLookupChangedCallback(Callback<LookupMetadata> callback) {
        lookupChangedCallbacks.add(callback);
    }

    public String getSelectedLookupName() {
        return selectedLookupName;
    }

    public void setSelectedLookupName(String selectedLookupName) {
        this.selectedLookupName = selectedLookupName;
    }
    
}
