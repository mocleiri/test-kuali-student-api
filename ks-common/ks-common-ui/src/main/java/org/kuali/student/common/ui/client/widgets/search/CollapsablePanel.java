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

import org.kuali.student.common.ui.client.theme.Theme;
import org.kuali.student.common.ui.client.widgets.KSButton;
import org.kuali.student.common.ui.client.widgets.KSButtonAbstract.ButtonStyle;
import org.kuali.student.common.ui.client.widgets.layout.HorizontalBlockFlowPanel;
import org.kuali.student.common.ui.client.widgets.layout.VerticalFlowPanel;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CollapsablePanel extends Composite {
	protected KSButton label;
	protected VerticalFlowPanel layout = new VerticalFlowPanel();
	protected HorizontalBlockFlowPanel linkPanel = new HorizontalBlockFlowPanel();
	protected SimplePanel content = new SimplePanel();
	protected ContentAnimation animation = new ContentAnimation();

	protected boolean isOpen = false;
	protected boolean withImages = true;
	protected ImagePosition imagePosition = ImagePosition.ALIGN_LEFT;
	
	public enum ImagePosition {
		ALIGN_LEFT, ALIGN_RIGHT
	}
	
	protected Image closedImage = Theme.INSTANCE.getCommonImages().getDisclosureClosedIcon();
	protected Image openedImage = Theme.INSTANCE.getCommonImages().getDisclosureOpenedIcon();

	private static class ContentAnimation extends Animation {
		/**
		 * Whether the item is being opened or closed.
		 */
		private boolean opening;

		/**
		 * The {@link DisclosurePanel} being affected.
		 */
		private CollapsablePanel curPanel;

		/**
		 * Open or close the content.
		 * 
		 * @param panel
		 *            the panel to open or close
		 * @param animate
		 *            true to animate, false to open instantly
		 */
		public void setOpen(CollapsablePanel panel, boolean animate) {
			// Immediately complete previous open
			cancel();

			// Open the new item
			if (animate) {
				curPanel = panel;
				opening = panel.isOpen;
				run(1000);
			} else {
				panel.content.setVisible(panel.isOpen);
				if (panel.isOpen) {
					// Special treatment on the visible case to ensure LazyPanel
					// works
					panel.content.setVisible(true);
				}
			}
		}

		@Override
		protected void onComplete() {
			if (!opening) {
				curPanel.content.setVisible(false);
			}
			DOM.setStyleAttribute(curPanel.content.getElement(), "height", "auto");
			DOM.setStyleAttribute(curPanel.content.getElement(), "overflow", "visible");
			curPanel = null;
		}

		@Override
		protected void onStart() {
			super.onStart();
			DOM.setStyleAttribute(curPanel.content.getElement(), "overflow", "hidden");
			if (opening) {
				curPanel.content.setVisible(true);
				// Special treatment on the visible case to ensure LazyPanel works
				curPanel.content.setVisible(true);
			}
		}

		@Override
		protected void onUpdate(double progress) {
			int scrollHeight = DOM.getElementPropertyInt(curPanel.content.getElement(), "scrollHeight");
			int height = (int) (progress * scrollHeight);
			if (!opening) {
				height = scrollHeight - height;
			}
			height = Math.max(height, 1);

			DOM.setStyleAttribute(curPanel.content.getElement(), "height", height + "px");
			DOM.setStyleAttribute(curPanel.content.getElement(), "width", "auto");
		}
	}

	protected CollapsablePanel(){		
	}
	
	public CollapsablePanel(String name, Widget content, boolean isOpen) {
		init(name, content, isOpen, true, ImagePosition.ALIGN_RIGHT);
	}

	public CollapsablePanel(String name, Widget content, boolean isOpen, boolean withImages) {
		init(name, content, isOpen, withImages, ImagePosition.ALIGN_RIGHT);
	}

	public CollapsablePanel(String name, Widget content, boolean isOpen, boolean withImages, ImagePosition imagePosition) {
		init(name, content, isOpen, withImages, imagePosition);
	}

	protected void init(String name, Widget content, boolean isOpen, boolean withImages, ImagePosition imagePosition) {
		this.isOpen = isOpen;
		this.withImages = withImages;
		this.imagePosition = imagePosition;
		
		label = new KSButton(name, ButtonStyle.DEFAULT_ANCHOR);
		this.content.setWidget(content);
		label = new KSButton(name, ButtonStyle.DEFAULT_ANCHOR);

		if (this.imagePosition == ImagePosition.ALIGN_RIGHT){
			linkPanel.add(label);
		}
		
		if (this.withImages){
			linkPanel.add(closedImage);
			linkPanel.add(openedImage);
			setImageState();
		}

		if (this.imagePosition == ImagePosition.ALIGN_LEFT){
			linkPanel.add(label);
		}

		if (!isOpen) {
			this.content.setVisible(false);
		}

		label.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (CollapsablePanel.this.isOpen) {
					CollapsablePanel.this.close();
				} else {
					CollapsablePanel.this.open();
				}
			}
		});

		layout.add(linkPanel);
		layout.add(this.content);
		closedImage.addStyleName("ks-image-middle-alignment");
		openedImage.addStyleName("ks-image-middle-alignment");
		content.addStyleName("top-padding");
		this.initWidget(layout);
	}

	public KSButton getLabel() {
		return label;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void open() {
		isOpen = true;
		if (withImages) {
			setImageState();
		}
		animation.setOpen(this, true);
	}

	public void close() {
		isOpen = false;
		if (withImages) {
			setImageState();
		}
		animation.setOpen(this, true);
	}
		
	/**
	 * Update the image state to display opened/closed image based in isOpen() status
	 */
	protected void setImageState(){
		closedImage.setVisible(!isOpen);
		openedImage.setVisible(isOpen);						
	}

}
