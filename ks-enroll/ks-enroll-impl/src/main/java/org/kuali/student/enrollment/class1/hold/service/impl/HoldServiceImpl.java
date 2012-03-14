/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.enrollment.class1.hold.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.enrollment.class1.hold.dao.HoldDao;
import org.kuali.student.enrollment.class1.hold.dao.HoldRichTextDao;
import org.kuali.student.enrollment.class1.hold.dao.IssueDao;
import org.kuali.student.enrollment.class1.hold.dao.RestrictionDao;
import org.kuali.student.enrollment.class1.hold.model.HoldEntity;
import org.kuali.student.enrollment.class1.hold.model.HoldRichTextEntity;
import org.kuali.student.enrollment.class1.hold.model.IssueEntity;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.util.constants.HoldServiceConstants;
import org.kuali.student.r2.core.class1.type.dao.TypeTypeRelationDao;
import org.kuali.student.r2.core.hold.dto.HoldInfo;
import org.kuali.student.r2.core.hold.dto.IssueInfo;
import org.kuali.student.r2.core.hold.service.HoldService;
import org.kuali.student.r2.core.state.service.StateService;
import org.springframework.transaction.annotation.Transactional;

@WebService(name = "HoldService", serviceName = "HoldService", portName = "HoldService", targetNamespace = "http://student.kuali.org/wsdl/hold")
@Transactional(readOnly = true, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
public class HoldServiceImpl implements HoldService {

    private IssueDao issueDao;
    private RestrictionDao restrictionDao;
    private HoldDao holdDao;
    private HoldRichTextDao holdRichTextDao;
    private TypeTypeRelationDao typeTypeRelationDao;
    private StateService stateService;
    private DataDictionaryService dataDictionaryService;

    public IssueDao getIssueDao() {
        return issueDao;
    }

    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public RestrictionDao getRestrictionDao() {
        return restrictionDao;
    }

    public void setRestrictionDao(RestrictionDao restrictionDao) {
        this.restrictionDao = restrictionDao;
    }

    public HoldDao getHoldDao() {
        return holdDao;
    }

    public void setHoldDao(HoldDao holdDao) {
        this.holdDao = holdDao;
    }

    public HoldRichTextDao getHoldRichTextDao() {
        return holdRichTextDao;
    }

    public void setHoldRichTextDao(HoldRichTextDao holdRichTextDao) {
        this.holdRichTextDao = holdRichTextDao;
    }

    public TypeTypeRelationDao getTypeTypeRelationDao() {
        return typeTypeRelationDao;
    }

    public void setTypeTypeRelationDao(TypeTypeRelationDao typetypeRelationDao) {
        this.typeTypeRelationDao = typetypeRelationDao;
    }

    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @Override
    public HoldInfo getHold(String holdId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        HoldEntity entity = holdDao.find(holdId);

        if (entity == null)
            throw new DoesNotExistException(holdId);

        return entity.toDto();
    }

    @Override
    public List<String> searchForHoldIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return new ArrayList<String>();
    }

    @Override
    public List<HoldInfo> searchForHolds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        return new ArrayList<HoldInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateHold(String validationTypeKey, HoldInfo holdInfo, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return new ArrayList<ValidationResultInfo>();
    }

    /*private StateEntity findState(String stateKey, ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException{
    	StateEntity state = null;
    	try {
        	StateInfo stInfo = stateService.getState(stateKey, context);
        	if(stInfo != null){
        		state = new StateEntity(stInfo);
        		return state;
        	}
        	else
        		throw new OperationFailedException("The state does not exist. stateKey: " + stateKey);
    	} catch (DoesNotExistException e) {
    		throw new OperationFailedException("The state does not exist. stateKey: " + stateKey);
    	}			
    }*/

    private IssueEntity findIssue(String issueId) throws OperationFailedException {
        IssueEntity issue = issueDao.find(issueId);
        if (null != issue)
            return issue;
        else
            throw new OperationFailedException("The issue does not exist. issue " + issueId);
    }

    @Override
    @Transactional
    public HoldInfo createHold(HoldInfo holdInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        HoldEntity entity = new HoldEntity(holdInfo);
        entity.setId(UUIDHelper.genStringUUID());

        if (null != holdInfo.getIssueKey())
            entity.setIssue(findIssue(holdInfo.getIssueKey()));

        if (null != holdInfo.getStateKey())
            entity.setHoldState(holdInfo.getStateKey());

        if (null != holdInfo.getTypeKey())
            entity.setHoldType(holdInfo.getTypeKey());

        if (null != holdInfo.getDescr())
            entity.setDescr(new HoldRichTextEntity(holdInfo.getDescr()));

        HoldEntity existing = holdDao.find(entity.getId());
        if (existing != null) {
            throw new AlreadyExistsException();
        }
        holdDao.persist(entity);

        return holdDao.find(entity.getId()).toDto();
    }

    @Override
    @Transactional
    public HoldInfo updateHold(String holdId, HoldInfo holdInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        HoldEntity entity = holdDao.find(holdId);

        if (null != entity) {
            HoldEntity modifiedEntity = new HoldEntity(holdInfo);
            if (null != holdInfo.getIssueKey())
                modifiedEntity.setIssue(findIssue(holdInfo.getIssueKey()));
            if (holdInfo.getStateKey() != null)
                modifiedEntity.setHoldState(holdInfo.getStateKey());
            if (holdInfo.getTypeKey() != null)
                modifiedEntity.setHoldType(holdInfo.getTypeKey());

            holdDao.merge(modifiedEntity);
            return holdDao.find(modifiedEntity.getId()).toDto();
        }
        else
            throw new DoesNotExistException(holdId);
    }

    private HoldInfo updateHoldState(String holdId, String stateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        HoldEntity entity = holdDao.find(holdId);

        if (null != entity) {
            entity.setHoldState(stateKey);
            entity.setReleasedDate(new Date());

            holdDao.merge(entity);
            return holdDao.find(entity.getId()).toDto();
        }
        else
            throw new DoesNotExistException("The hold does not exist." + holdId);

    }

    @Override
    @Transactional
    public HoldInfo releaseHold(String holdId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return updateHoldState(holdId, HoldServiceConstants.HOLD_RELEASED_STATE_KEY, context);
    }

    @Override
    @Transactional
    public StatusInfo deleteHold(String holdId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        updateHoldState(holdId, HoldServiceConstants.HOLD_CANCELED_STATE_KEY, context);
        return status;
    }

    @Override
    public IssueInfo getIssue(String issueId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        IssueEntity entity = issueDao.find(issueId);

        if (entity == null) {
            throw new DoesNotExistException(issueId);
        }

        return entity.toDto();
    }

    @Override
    public List<String> getIssueKeysByType(String issueTypeKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        return new ArrayList<String>();
    }

    @Override
    public List<IssueInfo> getIssuesByOrg(String organizationId, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<IssueEntity> issues = issueDao.getByOrganizationId(organizationId);

        List<IssueInfo> results = new ArrayList<IssueInfo>(issues.size());

        for (IssueEntity issue : issues) {
            results.add(issue.toDto());
        }

        return results;
    }

    @Override
    public List<String> searchForIssueKeys(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return new ArrayList<String>();
    }

    @Override
    public List<IssueInfo> searchForIssues(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        return new ArrayList<IssueInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateIssue(String validationTypeKey, IssueInfo issueInfo, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return new ArrayList<ValidationResultInfo>();
    }

    @Override
    public IssueInfo createIssue(IssueInfo issueInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return null;
    }

    @Override
    public IssueInfo updateIssue(String issueId, IssueInfo issueInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        return null;
    }

    @Override
    public StatusInfo deleteIssue(String issueId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return null;
    }

    @Override
    public List<HoldInfo> getHoldsByIds(List<String> holdIds, ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getHoldIdsByType(String holdTypeKey, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HoldInfo> getHoldsByIssue(String issueId, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HoldInfo> getHoldsByPerson(String personId, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HoldInfo> getActiveHoldsByPerson(String personId, ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HoldInfo> getHoldsByIssueAndPerson(String issueId, String personId, ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HoldInfo> getActiveHoldsByIssueAndPerson(String issueId, String personId, ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<IssueInfo> getIssuesByIds(List<String> issueIds, ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<IssueEntity> issues = issueDao.findByIds(issueIds);

        if (issues == null) {
            throw new DoesNotExistException();
        }

        List<IssueInfo> result = new ArrayList<IssueInfo>(issues.size());
        for (IssueEntity entity : issues) {
            if (entity == null) {
                // if one of the entities from "findByIds" is returned as null, then one of the keys in the list was not found
                throw new DoesNotExistException();
            }
            result.add(entity.toDto());
        }

        return result;
    }

}
