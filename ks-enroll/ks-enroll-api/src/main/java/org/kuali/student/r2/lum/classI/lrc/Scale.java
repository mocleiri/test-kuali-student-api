package org.kuali.student.r2.lum.classI.lrc;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.kuali.student.common.dto.RichTextInfo;

public interface Scale extends Serializable {
	
    /**
     * Date and time that this scale became effective. This is a similar concept to the effective date on enumerated values. When an expiration date has been specified, this field must be less than or equal to the expiration date.
     */
    public Date getEffectiveDate() ;
    /**
     * Date and time that this scale expires. This is a similar concept to the expiration date on enumerated values. If specified, this should be greater than or equal to the effective date. If this field is not specified, then no expiration date has been currently defined and should automatically be considered greater than the effective date.
     */
    public Date getExpirationDate() ;

    /**
     * Unique identifier for a scale.
     */
    public String getId() ;

}
