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

package org.kuali.student.core.organization.ui.client.view;


import org.kuali.student.common.messages.dto.MessageList;
import org.kuali.student.common.ui.client.application.Application;
import org.kuali.student.common.ui.client.application.ApplicationComposite;
import org.kuali.student.common.ui.client.application.ApplicationContext;
import org.kuali.student.common.ui.client.application.KSAsyncCallback;
import org.kuali.student.common.ui.client.mvc.Controller;
import org.kuali.student.common.ui.client.service.MessagesRpcService;
import org.kuali.student.common.ui.client.service.SecurityRpcService;
import org.kuali.student.common.ui.client.service.SecurityRpcServiceAsync;
import org.kuali.student.common.ui.client.util.BrowserUtils;
import org.kuali.student.common.ui.client.widgets.ApplicationPanel;
import org.kuali.student.core.organization.ui.client.mvc.controller.OrgApplicationManager;
import org.kuali.student.core.organization.ui.client.theme.OrgTheme;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.ui.SimplePanel;

public class OrgEntryPoint implements EntryPoint{

    ApplicationComposite app;
    private OrgApplicationManager manager = null;
    SimplePanel content = new SimplePanel();
//    private OrgMenu orgMenu = new OrgMenu(content);;

    public void onModuleLoad() {
        final ApplicationContext context = Application.getApplicationContext();
        final String injectString = OrgTheme.INSTANCE.getOrgCss().getCssString();
        StyleInjector.injectStylesheet(injectString);

        loadApp(context);

        try {
            MessageList messageList =  getSerializedObject( "i18nMessages");
            context.addMessages(messageList.getMessages());

        } catch (Exception e) {
            GWT.log("Error on ModuleLoad",e);
        }

//        StyleInjector.injectStylesheet(CoreUIResources.INSTANCE.generalCss().getText());

//        History.addValueChangeHandler(orgMenu);
        History.fireCurrentHistoryState();

        if(DOM.getElementById("loadingSpinner") != null)
            DOM.removeChild(ApplicationPanel.get().getElement(), DOM.getElementById("loadingSpinner"));
    }

    /* Not used anywhere that I can tell GNL
    public Widget getContent(){
        DockPanel mainPanel = new DockPanel();

        Label pageTitle = new Label(Application.getApplicationContext().getMessage("orgAppTitle"));
        pageTitle.setStyleName("page-title");
        mainPanel.setStyleName("ks-main");
        mainPanel.add(pageTitle, DockPanel.NORTH);

//        mainPanel.add(orgMenu, DockPanel.WEST);
//        mainPanel.setCellWidth(orgMenu, "200px");
        mainPanel.add(content, DockPanel.CENTER);

//        mainPanel.add(new KSButton("Logout", new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                orgMenu.saveState();
//                Location.assign("http://localhost:18080/cas-server-webapp-3.3.2/login?service="+URL.encodeComponent(Location.getHref()));
//            }}), DockPanel.NORTH);

        return mainPanel;
    }
    */

    @SuppressWarnings("unchecked")
    public  <T> T getSerializedObject( String name ) throws SerializationException
    {
        String serialized = BrowserUtils.getString( name );
        SerializationStreamFactory ssf = GWT.create( MessagesRpcService.class); // magic
        return (T)ssf.createStreamReader( serialized ).readObject();
    }

    public void loadApp(final ApplicationContext context){
        SecurityRpcServiceAsync securityRpc = GWT.create(SecurityRpcService.class);

        securityRpc.getPrincipalUsername(new KSAsyncCallback<String>(){
            public void handleFailure(Throwable caught) {
                context.setUserId("Unknown");
                initScreen();
            }

            @Override
            public void onSuccess(String principalId) {
                context.setUserId(principalId);
                initScreen();
            }
        });
    }

    private void initScreen(){
        app = new ApplicationComposite();
        manager = new OrgApplicationManager();
        app.setContent(manager);
//        app.setContent(getContent());
        ApplicationPanel.get().add(app);
        if(manager.getCurrentView() == null)
            manager.showDefaultView(Controller.NO_OP_CALLBACK);
    }
}
