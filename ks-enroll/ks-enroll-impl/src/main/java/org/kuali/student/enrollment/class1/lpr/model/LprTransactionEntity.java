package org.kuali.student.enrollment.class1.lpr.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.student.enrollment.lpr.dto.LprTransactionInfo;
import org.kuali.student.enrollment.lpr.infc.LprTransaction;
import org.kuali.student.r2.common.dto.AttributeInfo;
import org.kuali.student.r2.common.entity.AttributeOwner;
import org.kuali.student.r2.common.entity.MetaEntity;
import org.kuali.student.r2.common.model.StateEntity;

@Entity
@Table(name = "KSEN_LPR_TRANS")
public class LprTransactionEntity extends MetaEntity implements AttributeOwner<LprTransAttributeEntity> {

    @Column(name = "REQ_PERSON_ID")
    private String requestingPersonId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RT_DESCR_ID")
    private LprRichTextEntity descr;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "LPR_TRANS_ID")
    private List<LprTransactionItemEntity> lprTransactionItems;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LPR_TYPE_ID")
    private LuiPersonRelationTypeEntity lprTransType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "STATE_ID")
    private StateEntity lprTransState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<LprTransAttributeEntity> attributes;

    public LprTransactionEntity() {}

    public LprTransactionEntity(LprTransaction lprTransaction) {
        super(lprTransaction);
        this.requestingPersonId = lprTransaction.getRequestingPersonId();
        this.lprTransactionItems = new ArrayList<LprTransactionItemEntity>();

    }

    public LprTransactionInfo toDto() {

        LprTransactionInfo lpr = new LprTransactionInfo();
        lpr.setId(getId());

        if (lprTransType != null)
            lpr.setTypeKey(lprTransType.getId());
        if (lprTransState != null)
            lpr.setStateKey(lprTransState.getId());
        lpr.setMeta(super.toDTO());
        if (descr != null)
            lpr.setDescr(descr.toDto());
        if (getAttributes() != null) {
            List<AttributeInfo> atts = new ArrayList<AttributeInfo>();
            for (LprTransAttributeEntity att : getAttributes()) {
                AttributeInfo attInfo = att.toDto();
                atts.add(attInfo);
            }
            lpr.setAttributes(atts);
        }
        return lpr;

    }

    public LprRichTextEntity getDescr() {
        return descr;
    }

    public void setDescr(LprRichTextEntity descr) {
        this.descr = descr;
    }

    public LuiPersonRelationTypeEntity getLprTransType() {
        return lprTransType;
    }

    public void setLprTransType(LuiPersonRelationTypeEntity lprTransType) {
        this.lprTransType = lprTransType;
    }

    public StateEntity getLprTransState() {
        return lprTransState;
    }

    public void setLprTransState(StateEntity lprTransState) {
        this.lprTransState = lprTransState;
    }

    public String getRequestingPersonId() {
        return requestingPersonId;
    }

    public void setRequestingPersonId(String requestingPersonId) {
        this.requestingPersonId = requestingPersonId;
    }

    public List<LprTransactionItemEntity> getLprTransactionItems() {
        return lprTransactionItems;
    }

    public void setLprTransactionItems(List<LprTransactionItemEntity> lprTransactionItems) {
        this.lprTransactionItems = lprTransactionItems;
    }

    @Override
    public void setAttributes(List<LprTransAttributeEntity> attributes) {
        this.setAttributes(attributes);
    }

    @Override
    public List<LprTransAttributeEntity> getAttributes() {
        return this.attributes;
    }

}
