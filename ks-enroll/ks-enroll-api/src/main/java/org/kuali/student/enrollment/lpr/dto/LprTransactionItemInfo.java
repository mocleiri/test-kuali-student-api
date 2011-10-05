package org.kuali.student.enrollment.lpr.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.enrollment.lpr.infc.LPRTransactionItem;
import org.kuali.student.enrollment.lpr.infc.RequestOption;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LprTransactionItemInfo", propOrder = {"personId", "newLuiId", "existingLuiId", "resultOptionKeys", "requestOptions", "lprTransactionItemResult", "name", "descr", "typeKey",
        "stateKey", "meta", "attributes", "groupId", "_futureElements"})
public class LprTransactionItemInfo extends IdEntityInfo implements LPRTransactionItem, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String personId;

    @XmlElement
    private String newLuiId;

    @XmlElement
    private String existingLuiId;

    @XmlElement
    private String groupId;

    @XmlElement
    private List<String> resultOptionKeys;

    @XmlElement
    private List<RequestOptionInfo> requestOptions;

    @XmlElement
    private LprTransactionItemResultInfo lprTransactionItemResult;

    @XmlAnyElement
    private List<Element> _futureElements;

    public LprTransactionItemInfo() {
        super();
        this.personId = null;
        this.newLuiId = null;
        this.existingLuiId = null;
        this.requestOptions = new ArrayList<RequestOptionInfo>();
        this.resultOptionKeys = new ArrayList<String>();
        this._futureElements = null;
    }

    public LprTransactionItemInfo(LPRTransactionItem lprTransactionItem) {

        super(lprTransactionItem);
        if (null != lprTransactionItem) {
            this.personId = lprTransactionItem.getPersonId();
            this.newLuiId = lprTransactionItem.getNewLuiId();
            this.existingLuiId = lprTransactionItem.getExistingLuiId();

            this.requestOptions = new ArrayList<RequestOptionInfo>();
            if (null != lprTransactionItem.getRequestOptions()) {
                for (RequestOption reqOp : lprTransactionItem.getRequestOptions()) {
                    this.requestOptions.add(new RequestOptionInfo(reqOp));
                }
            }

            this.resultOptionKeys = new ArrayList<String>();
            if (null != lprTransactionItem.getResultOptionKeys()) {
                resultOptionKeys.addAll(lprTransactionItem.getResultOptionKeys());
            }

            this.lprTransactionItemResult = new LprTransactionItemResultInfo(lprTransactionItem.getLprTransactionItemResult());

            this._futureElements = null;
        }
    }

    @Override
    public LprTransactionItemResultInfo getLprTransactionItemResult() {
        return lprTransactionItemResult;
    }

    public void setLprTransactionResult(LprTransactionItemResultInfo lprTransactionResult) {
        this.lprTransactionItemResult = lprTransactionResult;
    }

    @Override
    public List<RequestOptionInfo> getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(List<RequestOptionInfo> requestOptions) {
        this.requestOptions = requestOptions;
    }

    public void setNewLuiId(String newLuiId) {
        this.newLuiId = newLuiId;
    }

    public void setExistingLuiId(String existingLuiId) {
        this.existingLuiId = existingLuiId;
    }

    @Override
    public String getNewLuiId() {
        return newLuiId;
    }

    @Override
    public String getExistingLuiId() {
        return existingLuiId;
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public List<String> getResultOptionKeys() {
        return resultOptionKeys;
    }

    public void setResultOptionKeys(List<String> resultOptions) {
        this.resultOptionKeys = resultOptions;
    }

    public void setLprTransactionItemResult(LprTransactionItemResultInfo lprTransactionItemResult) {
        this.lprTransactionItemResult = lprTransactionItemResult;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
