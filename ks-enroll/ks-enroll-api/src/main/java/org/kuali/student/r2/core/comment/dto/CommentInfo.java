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

package org.kuali.student.r2.core.comment.dto;

import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.core.comment.infc.Comment;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Detailed information about a comment.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommentInfo", propOrder = {"id", "typeKey", "stateKey", "name",
        "descr", "commentText", "referenceTypeKey", "referenceId", "effectiveDate",
        "expirationDate", "meta", "attributes", "_futureElements"})
public class CommentInfo extends IdEntityInfo implements Comment, Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private RichTextInfo commentText;

    @XmlElement
    private String referenceTypeKey;

    @XmlElement
    private String referenceId;

    @XmlElement
    private Date effectiveDate;

    @XmlElement
    private Date expirationDate;

    @XmlAnyElement
    private List<Element> _futureElements;

    public static CommentInfo newInstance() {
        return new CommentInfo();
    }

    public static CommentInfo getInstance(Comment comment) {
        return new CommentInfo(comment);
    }

    /**
     * Default constructor needs to be provided as we have an explicit parameterized constructor
     */
    public CommentInfo() {
    }

    /**
     * Constructs a new CommentInfo from another Comment.
     *
     * @param comment the COMMENT to copy
     */
    public CommentInfo(Comment comment) {
        super(comment);
        if (null != comment) {
            this.commentText = new RichTextInfo(comment.getCommentText());
            this.referenceTypeKey = comment.getReferenceTypeKey();
            this.referenceId = comment.getReferenceId();
            this.effectiveDate = new Date(comment.getEffectiveDate().getTime());
            this.expirationDate = new Date(comment.getExpirationDate().getTime());
            this._futureElements = null;
        }
    }

    /**
     * Narrative text of the comment
     */
    @Override
    public RichTextInfo getCommentText() {
        return commentText;
    }

    public void setCommentText(RichTextInfo commentText) {
        this.commentText = commentText;
    }

    /**
     * Unique identifier for a reference type.
     */
    @Override
    public String getReferenceTypeKey() {
        return referenceTypeKey;
    }

    public void setReferenceTypeKey(String referenceTypeKey) {
        this.referenceTypeKey = referenceTypeKey;
    }

    /**
     * Identifier component for a reference. This is an external identifier and such may not uniquely identify a particular reference unless combined with the type. A referenceId could be a cluId, a luiId, an orgId, a documentId, etc.
     */
    @Override
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Date and time that this comment became effective. This is a similar concept to the effective date on enumerated values. When an expiration date has been specified, this field must be less than or equal to the expiration date.
     */
    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Date and time that this comment expires. This is a similar concept to the expiration date on enumerated values. If specified, this should be greater than or equal to the effective date. If this field is not specified, then no expiration date has been currently defined and should automatically be considered greater than the effective date.
     */
    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
