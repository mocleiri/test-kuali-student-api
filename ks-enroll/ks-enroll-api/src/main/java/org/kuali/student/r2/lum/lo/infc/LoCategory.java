package org.kuali.student.r2.lum.lo.infc;

import org.kuali.student.common.dto.MetaInfo;
import org.kuali.student.common.dto.RichTextInfo;
import org.kuali.student.r2.common.infc.IdEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */
public interface LoCategory  extends IdEntity{

    /**
     * Unique identifier for a learning objective repository. Once set in creation, this is immutable.
     */
    public String getLoRepository() ;

    /**
     * Date and time that this learning objective category became effective. This is a similar concept to the effective date on enumerated values. When an expiration date has been specified, this field must be less than or equal to the expiration date.
     */
    public Date getEffectiveDate();


    /**
     * Date and time that this learning objective category expires. This is a similar concept to the expiration date on enumerated values. If specified, this should be greater than or equal to the effective date. If this field is not specified, then no expiration date has been currently defined and should automatically be considered greater than the effective date.
     */
    public Date getExpirationDate() ;


}
