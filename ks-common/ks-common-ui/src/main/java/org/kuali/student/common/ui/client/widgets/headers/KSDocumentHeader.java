package org.kuali.student.common.ui.client.widgets.headers;

import org.kuali.student.common.ui.client.mvc.Callback;
import org.kuali.student.common.ui.client.mvc.Controller;
import org.kuali.student.common.ui.client.util.ExportUtils;
import org.kuali.student.common.ui.client.util.PrintUtils;
import org.kuali.student.common.ui.client.widgets.ApplicationPanel;
import org.kuali.student.common.ui.client.widgets.dialog.ReportExportDialog;
import org.kuali.student.common.ui.client.widgets.field.layout.element.SpanPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class KSDocumentHeader extends Composite {

    private static KSDocumentHeaderUiBinder uiBinder = GWT.create(KSDocumentHeaderUiBinder.class);

    private ReportExportDialog exportDialog = null;

    interface KSDocumentHeaderUiBinder extends UiBinder<Widget, KSDocumentHeader> {}

    @UiField
    HTML headerHTML;

    @UiField
    HTML infoLabel;

    @UiField
    SpanPanel widgetPanel;

    @UiField
    Image printImage;

    @UiField
    Image jasperImage;

    private boolean hasSeparator = true;

    public KSDocumentHeader() {
        initWidget(uiBinder.createAndBindUi(this));
        setupPrint();
        setupJasperPrint();
    }

    public KSDocumentHeader(boolean hasContentWidgetSeparator) {
        this.hasSeparator = hasContentWidgetSeparator;
        initWidget(uiBinder.createAndBindUi(this));
        setupPrint();
        setupJasperPrint();
    }

    private void setupPrint() {
        printImage.setVisible(false);
        printImage.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                PrintUtils.print(ApplicationPanel.get().getWidget(0));
            }
        });
    }

    private void setupJasperPrint() {
        exportDialog = new ReportExportDialog();
        jasperImage.setVisible(false);
        jasperImage.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (exportDialog != null) {
                    exportDialog.show();
                }

            }
        });

        exportDialog.addSelectCompleteCallback(new Callback<String>() {
            @Override
            public void exec(String format) {
                Controller currController = ExportUtils.getController(KSDocumentHeader.this);
                ExportUtils.handleExportClickEvent(currController, format, KSDocumentHeader.this.headerHTML.getTitle());
            }
        });
    }

    public void setTitle(String header) {
        headerHTML.setHTML("<h2>" + header + "</h2>");
    }

    public void addWidget(Widget w) {
        if (w != null) {
            if (hasSeparator) {
                if (widgetPanel.getElement().hasChildNodes()) {
                    widgetPanel.add(new HTML("<span style='float: left; margin-left: .7em; margin-right: .7em'>|</span>"));
                }
            }
            widgetPanel.add(w);
        }
        w.getElement().setAttribute("style", "float: left");
    }

    public HTML getInfoLabel() {
        return infoLabel;
    }

    public void showPrint(boolean show) {
        printImage.setVisible(true);
    }

    /**
     * This method set the visibility of the export button to the value of the parameter
     * 
     * @param show
     */
    public void showJasper(boolean show) {
        jasperImage.setVisible(show);
    }

    public Image getJasperImage() {
        return jasperImage;
    }
}
