package org.kuali.student.r2.core.class1.atp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.kuali.rice.core.api.criteria.GenericQueryResults;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.r1.common.dictionary.dto.ObjectStructureDefinition;

import org.kuali.student.r1.common.search.dto.*;
import org.kuali.student.r1.common.search.service.SearchDispatcher;
import org.kuali.student.r1.common.search.service.SearchManager;
import org.kuali.student.r1.common.search.service.impl.SearchManagerImpl;
import org.kuali.student.r2.common.criteria.CriteriaLookupService;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;

import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.kuali.student.r2.core.class1.atp.dao.AtpAtpRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpMilestoneRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.MilestoneDao;
import org.kuali.student.r2.core.class1.atp.model.AtpAtpRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpMilestoneRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.MilestoneEntity;
import org.kuali.student.r2.common.class1.state.model.StateEntity;
import org.kuali.student.r2.core.constants.AtpServiceConstants;
import org.kuali.student.r2.common.state.dto.StateInfo;
import org.kuali.student.r2.common.state.service.StateService;
import org.kuali.student.r2.common.type.service.TypeService;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
public class AtpServiceImpl implements AtpService {

    private AtpDao atpDao;
    private AtpAtpRelationDao atpRelDao;
    private MilestoneDao milestoneDao;
    private AtpMilestoneRelationDao atpMilestoneRelationDao;
    private CriteriaLookupService criteriaLookupService;
    private SearchManager searchManager;
    private SearchDispatcher searchDispatcher;

    public AtpDao getAtpDao() {
        return atpDao;
    }

    public void setAtpDao(AtpDao atpDao) {
        this.atpDao = atpDao;
    }

    public AtpAtpRelationDao getAtpRelDao() {
        return atpRelDao;
    }

    public void setAtpRelDao(AtpAtpRelationDao atpRelDao) {
        this.atpRelDao = atpRelDao;
    }

    public MilestoneDao getMilestoneDao() {
        return milestoneDao;
    }

    public void setMilestoneDao(MilestoneDao milestoneDao) {
        this.milestoneDao = milestoneDao;
    }

    public AtpMilestoneRelationDao getAtpMilestoneRelationDao() {
        return atpMilestoneRelationDao;
    }

    public void setAtpMilestoneRelationDao(AtpMilestoneRelationDao atpMilestoneRelationDao) {
        this.atpMilestoneRelationDao = atpMilestoneRelationDao;
    }

    public void setCriteriaLookupService(CriteriaLookupService criteriaLookupService) {
        this.criteriaLookupService = criteriaLookupService;
    }

    public CriteriaLookupService getCriteriaLookupService() {
        return criteriaLookupService;
    }

    /**
     * Check for missing parameter and throw localized exception if missing
     *
     * @param param
     * @param paramName
     * @throws MissingParameterException
     */
    private void checkForMissingParameter(Object param, String paramName)
            throws MissingParameterException {
        if (param == null) {
            throw new MissingParameterException(paramName + " can not be null");
        }
    }

    @Override
    public SearchCriteriaTypeInfo getSearchCriteriaType(
            String searchCriteriaTypeKey) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException {

        return searchManager.getSearchCriteriaType(searchCriteriaTypeKey);

    }

    @Override
    public List<SearchCriteriaTypeInfo> getSearchCriteriaTypes()
            throws OperationFailedException {
        return searchManager.getSearchCriteriaTypes();
    }

    @Override
    public SearchResultTypeInfo getSearchResultType(String searchResultTypeKey)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        checkForMissingParameter(searchResultTypeKey, "searchResultTypeKey");
        return searchManager.getSearchResultType(searchResultTypeKey);
    }

    @Override
    public List<SearchResultTypeInfo> getSearchResultTypes()
            throws OperationFailedException {
        return searchManager.getSearchResultTypes();
    }

    @Override
    public SearchTypeInfo getSearchType(String searchTypeKey)
            throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        checkForMissingParameter(searchTypeKey, "searchTypeKey");
        return searchManager.getSearchType(searchTypeKey);
    }

    @Override
    public List<SearchTypeInfo> getSearchTypes()
            throws OperationFailedException {
        return getSearchManager().getSearchTypes();
    }

    @Override
    public List<SearchTypeInfo> getSearchTypesByCriteria(
            String searchCriteriaTypeKey) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException {
        checkForMissingParameter(searchCriteriaTypeKey, "searchCriteriaTypeKey");
        return searchManager.getSearchTypesByCriteria(searchCriteriaTypeKey);
    }

    @Override
    public List<SearchTypeInfo> getSearchTypesByResult(
            String searchResultTypeKey) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException {
        checkForMissingParameter(searchResultTypeKey, "searchResultTypeKey");
        return searchManager.getSearchTypesByResult(searchResultTypeKey);
    }

    public SearchManager getSearchManager() {
        return searchManager;
    }

    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    @Override
    public SearchResult search(SearchRequest searchRequest) throws MissingParameterException {
        return this.searchDispatcher.dispatchSearch(searchRequest);
    }


    @Override
    public AtpInfo getAtp(String atpId, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        AtpEntity atp = atpDao.find(atpId);
        if (null == atp) {
            throw new DoesNotExistException(atpId);
        }
        return atp.toDto();
    }

    @Override
    public List<AtpInfo> getAtpsByDate(Date searchDate, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<AtpEntity> atps = atpDao.getByDate(searchDate);

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        if (null != atps) {
            for (AtpEntity entity : atps) {
                result.add(entity.toDto());
            }
        }
        return result;
    }

    @Override
    public List<AtpInfo> getAtpsByDates(Date startDate, Date endDate, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<AtpEntity> atps = atpDao.getByDates(startDate, endDate);

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        if (null != atps) {
            for (AtpEntity entity : atps) {
                result.add(entity.toDto());
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<AtpInfo> getAtpsByDateAndType(Date searchDate, String searchTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AtpInfo> getAtpsByDatesAndType(Date startDate, Date endDate, String searchTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AtpInfo> getAtpsByStartDateRange(Date searchDateRangeStart, Date searchDateRangeEnd, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<AtpEntity> atps = atpDao.getByStartDateRange(searchDateRangeStart, searchDateRangeEnd);

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        if (null != atps) {
            for (AtpEntity entity : atps) {
                result.add(entity.toDto());
            }
        }
        return result;
    }

    @Override
    public List<AtpInfo> getAtpsByStartDateRangeAndType(Date searchDateRangeStart, Date searchDateRangeEnd,
                                                        String searchTypeKey, ContextInfo context) throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<AtpEntity> atps = atpDao.getByStartDateRangeAndType(searchDateRangeStart, searchDateRangeEnd,
                searchTypeKey);

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        if (null != atps) {
            for (AtpEntity entity : atps) {
                result.add(entity.toDto());
            }
        }
        return result;
    }

    @Override
    public List<AtpInfo> getAtpsByCode(String code, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO
        return new ArrayList<AtpInfo>();
    }

    @Override
    public List<AtpInfo> getAtpsByIds(@WebParam(name = "atpIds") List<String> atpIds,
                                      @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<AtpEntity> atps = atpDao.findByIds(atpIds);

        if (atps == null) {
            throw new DoesNotExistException();
        }

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        for (AtpEntity entity : atps) {
            if (entity == null) {
                // if one of the entities from "findByIds" is returned as null,
                // then one of the keys in the list was not found
                throw new DoesNotExistException();
            }
            result.add(entity.toDto());
        }

        return result;
    }

    @Override
    public List<String> getAtpIdsByType(String atpTypeKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        List<AtpEntity> results = atpDao.getByAtpTypeId(atpTypeKey);

        List<String> keys = new ArrayList<String>(results.size());

        for (AtpEntity atp : results) {
            keys.add(atp.getId());
        }

        return keys;
    }

    @Override
    public MilestoneInfo getMilestone(String milestoneId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        MilestoneEntity entity = milestoneDao.find(milestoneId);

        if (entity != null) {
            return entity.toDto();
        } else {
            throw new DoesNotExistException(milestoneId);
        }

    }

    @Override
    public List<MilestoneInfo> getMilestonesByIds(List<String> milestoneIds, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<MilestoneEntity> milestones = milestoneDao.findByIds(milestoneIds);

        if (milestones == null) {
            throw new DoesNotExistException();
        }

        List<MilestoneInfo> result = new ArrayList<MilestoneInfo>(milestones.size());
        for (MilestoneEntity entity : milestones) {
            if (entity == null) {
                // if one of the entities from "findByIds" is returned as null,
                // then one of the keys in the list was not found
                throw new DoesNotExistException();
            }
            result.add(entity.toDto());
        }

        return result;
    }

    @Override
    public List<String> getMilestoneIdsByType(String milestoneTypeKey,
                                              ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<MilestoneEntity> entities = milestoneDao.getByMilestoneTypeId(milestoneTypeKey);
        List<String> results = new ArrayList<String>(entities.size());
        for (MilestoneEntity entity : entities) {
            results.add(entity.getId());
        }
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesForAtp(@WebParam(name = "atpId") String atpId,
                                                   @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        AtpEntity atp = atpDao.find(atpId);
        if (atp == null) {
            throw new InvalidParameterException(atpId);
        }
        List<String> ids = milestoneDao.getIdsByAtp(atpId);
        try {
            return this.getMilestonesByIds(ids, contextInfo);
        } catch (DoesNotExistException ex) {
            throw new OperationFailedException("Atp to Milestone relation exists to a milestone that has been deleted", ex);
        }
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDates(Date startDate, Date endDate, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        List<MilestoneEntity> entities = milestoneDao.getByDateRange(startDate, endDate);
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());
        for (MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDatesForAtp(String atpId, Date startDate, Date endDate,
                                                          ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<MilestoneEntity> entities = milestoneDao.getByDatesForAtp(atpId, startDate, endDate);
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());
        for (MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByTypeForAtp(String atpId,
                                                         String milestoneTypeKey,
                                                         ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<MilestoneInfo> list = this.getMilestonesForAtp(atpId, contextInfo);
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(list.size());
        for (MilestoneInfo info : list) {
            results.add(info);
        }
        return results;
    }

    @Override
    public List<String> searchForAtpIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        return new ArrayList<String>();
    }

    @Override
    public List<AtpInfo> searchForAtps(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        List<AtpInfo> atpInfos = new ArrayList<AtpInfo>();
        GenericQueryResults<AtpEntity> results = criteriaLookupService.lookup(AtpEntity.class, criteria);

        if (null != results && results.getResults().size() > 0) {
            for (AtpEntity atp : results.getResults()) {
                atpInfos.add(atp.toDto());
            }
        }

        return atpInfos;
    }

    @Override
    public List<ValidationResultInfo> validateAtp(String validationTypeKey, String atpTypeKey,
                                                  AtpInfo atpInfo, ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return new ArrayList<ValidationResultInfo>();
    }

    @Override
    @Transactional(readOnly = false)
    public AtpInfo createAtp(String atpTypeKey, AtpInfo atpInfo, ContextInfo contextInfo) throws
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {

        AtpEntity entity = new AtpEntity(atpInfo);
        entity.setId(atpInfo.getId());
        entity.setAtpType(atpTypeKey);
        entity.setCreateId(contextInfo.getPrincipalId());
        entity.setCreateTime(contextInfo.getCurrentDate());
        entity.setUpdateId(contextInfo.getPrincipalId());
        entity.setUpdateTime(contextInfo.getCurrentDate());
//        System.out.println ("AtpEntity: before persist ATP " + entity.getId());
        atpDao.persist(entity);
//        System.out.println ("AtpEntity: after persist ATP " + entity.getId());
//        System.out.println ("AtpEntity: flushmode=" + atpDao.getEm().getFlushMode());
//        atpDao.getEm().flush ();
//        System.out.println ("AtpEntity: after flush ATP " + entity.getId());
        return entity.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public AtpInfo updateAtp(String atpId, AtpInfo atpInfo, ContextInfo context) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {
        AtpEntity entity = atpDao.find(atpId);
        if (entity == null) {
            throw new DoesNotExistException(atpId);
        }
        entity.fromDTO(atpInfo);
        entity.setUpdateId(context.getPrincipalId());
        entity.setUpdateTime(context.getCurrentDate());
        atpDao.merge(entity);
        return entity.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteAtp(String atpId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        AtpEntity entity = atpDao.find(atpId);
        if (null == entity) {
            throw new DoesNotExistException(atpId);
        }
        List<AtpAtpRelationEntity> aarEntities = atpRelDao.getAtpAtpRelationsByAtp(atpId);
        if (null != aarEntities) {
            for (AtpAtpRelationEntity aarEntity : aarEntities) {
                atpRelDao.remove(aarEntity);
            }
        }
        List<AtpMilestoneRelationEntity> amrEntities = atpMilestoneRelationDao.getByAtpId(atpId);
        if (null != amrEntities) {
            for (AtpMilestoneRelationEntity amrEntity : amrEntities) {
                atpMilestoneRelationDao.remove(amrEntity);
            }
        }
        atpDao.remove(entity);
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        return status;
    }

    @Override
    public List<MilestoneInfo> getImpactedMilestones(String milestoneId, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<MilestoneInfo> impactedMilestones = new ArrayList<MilestoneInfo>();
        for (MilestoneEntity impactedMilestone : milestoneDao.getImpactedMilestones(milestoneId)) {
            impactedMilestones.add(impactedMilestone.toDto());
        }
        return impactedMilestones;
    }

    @Override
    public List<String> searchForMilestoneIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MilestoneInfo> searchForMilestones(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        List<MilestoneInfo> milestoneInfos = new ArrayList<MilestoneInfo>();
        GenericQueryResults<MilestoneEntity> results = criteriaLookupService.lookup(MilestoneEntity.class, criteria);

        if (null != results && results.getResults().size() > 0) {
            for (MilestoneEntity milestone : results.getResults()) {
                milestoneInfos.add(milestone.toDto());
            }
        }

        return milestoneInfos;
    }

    @Override
    public List<ValidationResultInfo> validateMilestone(String validationType, MilestoneInfo milestoneInfo,
                                                        ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {

        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public MilestoneInfo createMilestone(String milestoneTypeKey, MilestoneInfo milestoneInfo, ContextInfo contextInfo)
            throws DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {
        if (!milestoneTypeKey.equals(milestoneInfo.getTypeKey())) {
            throw new InvalidParameterException(milestoneTypeKey + " does not match " + milestoneInfo.getTypeKey());
        }
        MilestoneEntity entity = new MilestoneEntity(milestoneInfo);
        entity.setId(milestoneInfo.getId());
        entity.setAtpType(milestoneTypeKey);
        entity.setCreateId(contextInfo.getPrincipalId());
        entity.setCreateTime(contextInfo.getCurrentDate());
        entity.setUpdateId(contextInfo.getPrincipalId());
        entity.setUpdateTime(contextInfo.getCurrentDate());
        milestoneDao.persist(entity);
        MilestoneInfo result = entity.toDto();
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public MilestoneInfo updateMilestone(String milestoneId, MilestoneInfo milestoneInfo, ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {

        MilestoneEntity entity = milestoneDao.find(milestoneId);
        if (entity == null) {
            throw new DoesNotExistException(milestoneId);
        }
        entity.fromDto(milestoneInfo);
        entity.setUpdateId(context.getPrincipalId());
        entity.setUpdateTime(context.getCurrentDate());
        milestoneDao.merge(entity);
        return entity.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteMilestone(String milestoneId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {

        MilestoneEntity existingEntity = milestoneDao.find(milestoneId);
        if (existingEntity == null) {
            throw new DoesNotExistException(milestoneId);
        }
        List<AtpMilestoneRelationEntity> amrEntities = atpMilestoneRelationDao.getByMilestoneId(milestoneId);
        if (null != amrEntities) {
            for (AtpMilestoneRelationEntity amrEntity : amrEntities) {
                atpMilestoneRelationDao.remove(amrEntity);
            }
        }
        milestoneDao.remove(existingEntity);
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        return status;
    }

    @Override
    @Transactional(readOnly = false)
    public MilestoneInfo calculateMilestone(String milestoneId, ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method implemented in calculation decorator.");
    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo addMilestoneToAtp(String milestoneId,String atpId, ContextInfo contextInfo)
            throws AlreadyExistsException, DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        AtpEntity atp = atpDao.find(atpId);
        if (atp == null) {
            throw new DoesNotExistException(atpId);
        }
        MilestoneEntity milestone = milestoneDao.find(milestoneId);
        if (milestone == null) {
            throw new DoesNotExistException(milestoneId);
        }
        List<String> ids = atpMilestoneRelationDao.getIdsByAtpAndMilestone(atpId, milestoneId);
        if (!ids.isEmpty()) {
            throw new AlreadyExistsException("Milestone " + milestoneId + " already exists for ATP " + atpId);
        }

        AtpMilestoneRelationEntity entity = new AtpMilestoneRelationEntity();
        entity.setAtpId(atpId);
        entity.setMilestoneId(milestoneId);
        entity.setCreateId(contextInfo.getPrincipalId());
        entity.setCreateTime(contextInfo.getCurrentDate());
        entity.setUpdateId(contextInfo.getPrincipalId());
        entity.setUpdateTime(contextInfo.getCurrentDate());

        atpMilestoneRelationDao.persist(entity);
        StatusInfo info = new StatusInfo();
        info.setSuccess(true);

        return info;
    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo removeMilestoneFromAtp(String milestoneId, String atpId, ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<String> ids = atpMilestoneRelationDao.getIdsByAtpAndMilestone(atpId, milestoneId);
        if (ids.isEmpty()) {
            throw new DoesNotExistException("No relationship exists between the atp " + atpId + " and milestone " + milestoneId);
        }
        if (ids.size() > 1) {
            throw new OperationFailedException("Database error there should only be one relationship record found " + ids.size());
        }
        AtpMilestoneRelationEntity entity = atpMilestoneRelationDao.find(ids.get(0));
        if (entity == null) {
            throw new OperationFailedException("rror there should only be one relationship record found " + ids.size());
        }
        atpMilestoneRelationDao.remove(entity);
        StatusInfo status = new StatusInfo();
        status.setSuccess(true);
        return status;

    }

    @Override
    public AtpAtpRelationInfo getAtpAtpRelation(String atpAtpRelationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        AtpAtpRelationEntity atpRel = atpRelDao.find(atpAtpRelationId);
        if (null == atpRel) {
            throw new DoesNotExistException(atpAtpRelationId);
        }
        return atpRel.toDto();
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByIds(List<String> atpAtpRelationIds,
                                                            ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return new ArrayList<AtpAtpRelationInfo>();
    }

    @Override
    public List<String> getAtpAtpRelationIdsByType(String atpAtpRelationTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        return new ArrayList<String>();
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtp(String atpId, ContextInfo context)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        List<AtpAtpRelationEntity> relEntities = atpRelDao.getAtpAtpRelationsByAtp(atpId);
        List<AtpAtpRelationInfo> relInfos = new ArrayList<AtpAtpRelationInfo>();
        for (AtpAtpRelationEntity relEntity : relEntities) {
            AtpAtpRelationInfo relInfo = relEntity.toDto();
            relInfos.add(relInfo);
        }

        return relInfos;
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtps(String atpId, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByTypeAndAtp(String atpId, String atpRelationTypeKey,
                                                                   ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<AtpAtpRelationEntity> rels = atpRelDao.getAtpAtpRelationsByAtpAndRelationType(atpId, atpRelationTypeKey);
        List<AtpAtpRelationInfo> atpRelation = new ArrayList<AtpAtpRelationInfo>();
        for (AtpAtpRelationEntity rel : rels) {
            atpRelation.add(rel.toDto());
        }
        return atpRelation;
    }

    @Override
    public List<String> searchForAtpAtpRelationIds(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        return new ArrayList<String>();
    }

    @Override
    public List<AtpAtpRelationInfo> searchForAtpAtpRelations(QueryByCriteria criteria, ContextInfo context)
            throws InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        return new ArrayList<AtpAtpRelationInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateAtpAtpRelation(
            String validationTypeKey, String atpId, String atpPeerKey,
            String atpAtpRelationTypeKey, AtpAtpRelationInfo atpAtpRelationInfo, ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return new ArrayList<ValidationResultInfo>();
    }

//    private boolean checkRelationExistence(AtpAtpRelationInfo atpAtpRelationInfo) {
//        boolean exist = false;
//
//        List<AtpAtpRelationEntity> rels = atpRelDao.getAtpAtpRelationsByAtpAndRelationType(
//                atpAtpRelationInfo.getAtpId(), atpAtpRelationInfo.getTypeKey());
//        if (rels != null && !rels.isEmpty()) {
//            for (AtpAtpRelationEntity rel : rels) {
//                if (rel.getRelatedAtp().getId().equals(atpAtpRelationInfo.getRelatedAtpId())) {
//                    exist = true;
//                    break;
//                }
//            }
//        }
//
//        return exist;
//    }

    @Override
    @Transactional(readOnly = false)
    public AtpAtpRelationInfo createAtpAtpRelation(String atpId,
                                                   String relatedAtpId,
                                                   String atpAtpRelationTypeKey,
                                                   AtpAtpRelationInfo atpAtpRelationInfo,
                                                   ContextInfo contextInfo)
            throws DoesNotExistException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {

        if (!atpId.equals(atpAtpRelationInfo.getAtpId())) {
            throw new InvalidParameterException(atpId + " does not match the " + atpAtpRelationInfo.getAtpId());
        }
        if (!relatedAtpId.equals(atpAtpRelationInfo.getRelatedAtpId())) {
            throw new InvalidParameterException(relatedAtpId + " does not match the "
                    + atpAtpRelationInfo.getRelatedAtpId());
        }
        if (!atpAtpRelationTypeKey.equals(atpAtpRelationInfo.getTypeKey())) {
            throw new InvalidParameterException(atpAtpRelationTypeKey + " does not match the "
                    + atpAtpRelationInfo.getTypeKey());
        }
        // TODO: Why is this check here?  Is there such a restriction? the model allos there to be many such relations over time
        //        if (checkRelationExistence(atpAtpRelationInfo)) {
        //            throw new DataValidationErrorException("The Atp-Atp relation already exists. atp=" + atpAtpRelationInfo.getAtpId() + ", relatedAtp=" + atpAtpRelationInfo.getRelatedAtpId());
        //        }
        AtpAtpRelationEntity entity = new AtpAtpRelationEntity(atpAtpRelationInfo);
        entity.setId(atpAtpRelationInfo.getId());
        entity.setAtpType(atpAtpRelationTypeKey);
        entity.setAtp(atpDao.find(atpId));
        if (entity.getAtp() == null) {
            throw new DoesNotExistException(atpId);
        }
        entity.setRelatedAtp(atpDao.find(relatedAtpId));
        if (entity.getRelatedAtp() == null) {
            throw new DoesNotExistException(relatedAtpId);
        }
        entity.setCreateId(contextInfo.getPrincipalId());
        entity.setCreateTime(contextInfo.getCurrentDate());
        entity.setUpdateId(contextInfo.getPrincipalId());
        entity.setUpdateTime(contextInfo.getCurrentDate());
        atpRelDao.persist(entity);
        return entity.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public AtpAtpRelationInfo updateAtpAtpRelation(String atpAtpRelationId,
                                                   AtpAtpRelationInfo atpAtpRelationInfo,
                                                   ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException,
            InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, VersionMismatchException {
        AtpAtpRelationEntity entity = atpRelDao.find(atpAtpRelationId);
        if (null == entity) {
            throw new DoesNotExistException(atpAtpRelationId);
        }
        entity.fromDTO(atpAtpRelationInfo);
        entity.setUpdateId(context.getPrincipalId());
        entity.setUpdateTime(context.getCurrentDate());
        atpRelDao.merge(entity);
        return entity.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteAtpAtpRelation(String atpAtpRelationId, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        AtpAtpRelationEntity atpRel = atpRelDao.find(atpAtpRelationId);
        if (atpRel == null) {
            throw new DoesNotExistException(atpAtpRelationId);
        }
        atpRelDao.remove(atpRel);
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        return status;
    }

}
