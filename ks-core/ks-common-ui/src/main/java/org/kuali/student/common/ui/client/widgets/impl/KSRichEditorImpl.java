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

package org.kuali.student.common.ui.client.widgets.impl;



import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.theme.Theme;
import org.kuali.student.common.ui.client.widgets.KSLightBox;
import org.kuali.student.common.ui.client.widgets.KSRichEditorAbstract;
import org.kuali.student.common.ui.client.widgets.KSRichTextToolbar;
import org.kuali.student.common.ui.client.widgets.KSStyles;
import org.kuali.student.common.ui.client.widgets.buttongroups.ConfirmCancelGroup;
import org.kuali.student.common.ui.client.widgets.buttongroups.ButtonEnumerations.ConfirmCancelEnum;
import org.kuali.student.common.ui.client.widgets.focus.FocusGroup;
import org.kuali.student.common.ui.client.widgets.layout.VerticalFlowPanel;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RichTextArea;

/** 
 * KS default rich text editor
 * 
 * TODO implement with a clean toolbar and i18n
 */
public class KSRichEditorImpl extends KSRichEditorAbstract {
	private final VerticalFlowPanel content = new VerticalFlowPanel();
	
	private final RichTextArea textArea = new RichTextArea();
	private final KSRichTextToolbar toolbar;
	private final FocusGroup group = new FocusGroup(this);
	private final PopupPanel popoutImagePanel = new PopupPanel();
	
	private final KSLightBox popoutWindow = new KSLightBox();
	private KSRichEditorImpl popoutEditor = null;
	
	private boolean focused = false;
	private boolean toolbarShowing = false;
	private boolean popoutActive = false;
	
	private boolean isUsedInPopup = false;
	private int toolbarHeight;
	
	//private KSImages images = (KSImages) GWT.create(KSImages.class);
	private final Image popoutImage = Theme.INSTANCE.getRichTextEditorImages().popout().getImage();
	
	private int height;
	private int width;
	private boolean dimensionsObtained = false;
	
	private final FocusHandler focusHandler = new FocusHandler() {
		@Override
		public void onFocus(FocusEvent event) {
			if(!popoutActive){
				focused = true;
				if(!toolbar.inUse()){
					showToolbar(focused);
				}
			}
		}
	};
	
	private final BlurHandler blurHandler = new BlurHandler() {

		@Override
		public void onBlur(BlurEvent event) {			
			if(!popoutActive){
				focused = false;
				if(!toolbar.inUse()){
					showToolbar(focused);
				}
			}
		}
	};
	
	
	public KSRichEditorImpl(){
	    toolbar = new KSRichTextToolbar(textArea);
	    content.add(toolbar);
	    content.add(textArea);
        super.initWidget(content);
	}
	
	@Override
    protected void init(boolean isUsedInPopup) {
	    this.isUsedInPopup = isUsedInPopup;		
		setupDefaultStyle();
		group.addWidget(toolbar);
		group.addWidget(textArea);
		if(isUsedInPopup){
	        toolbar.setVisible(true);
            content.addStyleName(KSStyles.KS_RICH_TEXT_LARGE_CONTENT);
            textArea.addStyleName(KSStyles.KS_RICH_TEXT_LARGE);
            toolbar.addStyleName(KSStyles.KS_RICH_TEXT_LARGE_TOOLBAR);
		}
		else
		{
			textArea.addFocusHandler(focusHandler);
			textArea.addBlurHandler(blurHandler);
            content.addStyleName(KSStyles.KS_RICH_TEXT_NORMAL_CONTENT);
            textArea.addStyleName(KSStyles.KS_RICH_TEXT_NORMAL);
            toolbar.addStyleName(KSStyles.KS_RICH_TEXT_NORMAL_TOOLBAR);
			toolbar.setVisible(false);
			
			popoutEditor = new KSRichEditorImpl();
			popoutEditor.init(true);	
			
			ConfirmCancelGroup buttonPanel = new ConfirmCancelGroup(new Callback<ConfirmCancelEnum>(){

                @Override
                public void exec(ConfirmCancelEnum result) {
                    switch(result){
                        case CONFIRM:
                            textArea.setHTML(popoutEditor.getHTML());
                            popoutEditor.setHTML("");
                            textArea.setEnabled(true);
                            textArea.setFocus(true);
                            popoutWindow.hide();
                            popoutImagePanel.show();
                            popoutActive = false;
                            break;
                        case CANCEL:
                            popoutEditor.setHTML("");
                            textArea.setEnabled(true);
                            textArea.setFocus(true);
                            popoutWindow.hide();
                            popoutImagePanel.show();
                            popoutActive = false;
                            break;
                    }
                    
                }
            });		
			
/*			popoutImage.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
				    popoutWindow.show();
				    focused = true;
					textArea.setEnabled(false);
					popoutEditor.setHTML(textArea.getHTML());
					popoutImagePanel.hide();
					popoutEditor.getRichTextArea().setFocus(true);
				}
			});
			
			popoutImage.addMouseDownHandler(new MouseDownHandler(){

				@Override
				public void onMouseDown(MouseDownEvent event) {
					popoutActive = true;
				}
			});
			popoutImagePanel.add(popoutImage);*/
			buttonPanel.setContent(popoutEditor);
			popoutWindow.setWidget(buttonPanel);
			textArea.setFocus(false);
		}
	}
	
	public RichTextArea getRichTextArea(){
	    return textArea;
	}
	
	private void showToolbar(boolean show){
		
		if(!dimensionsObtained){
			height = textArea.getOffsetHeight();
			width = textArea.getOffsetWidth();
			dimensionsObtained = true;
		}
		
		
		if(show){
			if(!toolbarShowing){
				toolbar.setVisible(true);
				//firefox fix
				
				toolbarHeight = toolbar.getOffsetHeight();
				textArea.setHeight(height-toolbarHeight + "px");
				
/*				if(!isUsedInPopup){
					popoutImagePanel.show();
					int left = textArea.getAbsoluteLeft() + width - popoutImagePanel.getOffsetWidth() -18; 
					int top = textArea.getAbsoluteTop() + textArea.getOffsetHeight() - popoutImagePanel.getOffsetHeight() -2;
					popoutImagePanel.setPopupPosition(left, top);
				}*/
				
			}
			toolbarShowing = true;
		}
		else{
			if(toolbarShowing){
				
				toolbar.setVisible(false);
				textArea.setHeight(height + "px");
			}
			popoutImagePanel.hide();	
			toolbarShowing = false;
		}
	}
	
	private void setupDefaultStyle(){
/*		popoutImagePanel.addStyleName(KSStyles.KS_POPOUT_IMAGE_PANEL);
		popoutImage.addStyleName(KSStyles.KS_POPOUT_IMAGE);
		popoutImage.addMouseOverHandler(new MouseOverHandler(){

			@Override
			public void onMouseOver(MouseOverEvent event) {
				popoutImage.addStyleName(KSStyles.KS_POPOUT_IMAGE_HOVER);
			}
		});
		
		popoutImage.addMouseOutHandler(new MouseOutHandler(){

			@Override
			public void onMouseOut(MouseOutEvent event) {
				popoutImage.removeStyleName(KSStyles.KS_POPOUT_IMAGE_HOVER);
			}
		});*/
		
	}
	
	protected void onLoad() {
		super.onLoad();
		textArea.setEnabled(true);
	}
	
	protected void onUnload() {
		super.onUnload();
		textArea.setEnabled(false);
		//textArea.setFocus(false);
        //popoutImagePanel.hide();
        //textArea.setFocus(false);
	}
	
	
	
	@Override
	protected void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		textArea.setEnabled(false);
		//textArea.setFocus(false);
	}

	// delegate methods to RichTextArea
	public String getHTML() {
		return textArea.getHTML();
	}

	public String getText() {
		return textArea.getText();
	}

	public void setHTML(String html) {
		textArea.setHTML(html);
	}

	public void setText(String text) {
		textArea.setText(text);
	}
	
	public RichTextArea getTextArea(){
		return textArea;
	}

	@Override
	public void setStyleName(String style) {
		content.setStyleName(style);
		
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return group.addBlurHandler(handler);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return group.addFocusHandler(handler);
	}
	
	
}
