package org.kuali.student.r2.core.classI.atp.service.decorators;

import java.util.List;

import org.kuali.student.r2.common.datadictionary.DataDictionaryValidator;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.infc.HoldsValidator;
import org.kuali.student.r2.core.classI.atp.dto.AtpInfo;
import org.kuali.student.r2.core.classI.atp.service.AtpServiceDecorator;

public class AtpServiceValidationDecorator extends AtpServiceDecorator  implements HoldsValidator{
    private DataDictionaryValidator validator;

    @Override
    public DataDictionaryValidator getValidator() {
        return validator;
    }

    @Override
    public void setValidator(DataDictionaryValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<ValidationResultInfo> validateAtp(
            String validationType,
            AtpInfo atpInfo,
            ContextInfo context)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException {
        
        try{
            this.validator.validate(DataDictionaryValidator.ValidationType.fromString(validationType), atpInfo, context);
        }catch(PermissionDeniedException ex){
            throw new OperationFailedException();
        }
        
        return super.validateAtp(validationType, atpInfo, context);

    }

}
