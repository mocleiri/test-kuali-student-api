package org.kuali.student.r2.core.class1.atp.service.impl;

import java.util.*;

import javax.jws.WebService;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.r2.common.dao.TypeTypeRelationDao;
import org.kuali.student.r2.common.datadictionary.dto.DictionaryEntryInfo;
import org.kuali.student.r2.common.datadictionary.service.DataDictionaryService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StateInfo;
import org.kuali.student.r2.common.dto.StateProcessInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.TypeTypeRelationInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.entity.BaseAttributeEntity;
import org.kuali.student.r2.common.entity.TypeEntity;
import org.kuali.student.r2.common.entity.TypeTypeRelationEntity;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.model.StateEntity;
import org.kuali.student.r2.common.service.StateService;
import org.kuali.student.r2.common.util.constants.AtpServiceConstants;
import org.kuali.student.r2.common.util.constants.TypeServiceConstants;
import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.dto.AtpMilestoneRelationInfo;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.kuali.student.r2.core.class1.atp.dao.AtpAtpRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpMilestoneRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpRichTextDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpStateDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpTypeDao;
import org.kuali.student.r2.core.class1.atp.dao.MilestoneDao;
import org.kuali.student.r2.core.class1.atp.model.AtpAtpRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpMilestoneRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpRichTextEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpTypeEntity;
import org.kuali.student.r2.core.class1.atp.model.MilestoneEntity;
import org.springframework.transaction.annotation.Transactional;

@WebService(name = "AtpService", serviceName = "AtpService", portName = "AtpService", targetNamespace = "http://student.kuali.org/wsdl/atp")
@Transactional(readOnly = true, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
public class AtpServiceImpl implements AtpService {

    private AtpDao atpDao;
    private AtpTypeDao atpTypeDao;
    private AtpStateDao atpStateDao;
    private AtpRichTextDao atpRichTextDao;
    private AtpAtpRelationDao atpRelDao;
    private MilestoneDao milestoneDao;
    private AtpMilestoneRelationDao atpMilestoneRelationDao;
    private TypeTypeRelationDao typeTypeRelationDao;
    private StateService stateService;
    private DataDictionaryService dataDictionaryService;

    public AtpDao getAtpDao() {
        return atpDao;
    }
    public void setAtpDao(AtpDao atpDao) {
        this.atpDao = atpDao;
    }

    public AtpTypeDao getAtpTypeDao() {
        return atpTypeDao;
    }
    public void setAtpTypeDao(AtpTypeDao atpTypeDao) {
        this.atpTypeDao = atpTypeDao;
    }

    public AtpStateDao getAtpStateDao() {
        return atpStateDao;
    }
    public void setAtpStateDao(AtpStateDao atpStateDao) {
        this.atpStateDao = atpStateDao;
    }

    public AtpRichTextDao getAtpRichTextDao() {
        return atpRichTextDao;
    }
    public void setAtpRichTextDao(AtpRichTextDao atpRichTextDao) {
        this.atpRichTextDao = atpRichTextDao;
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

    public TypeTypeRelationDao getTypeTypeRelationDao() {
        return typeTypeRelationDao;
    }
    public void setTypeTypeRelationDao(TypeTypeRelationDao typeTypeRelationDao) {
        this.typeTypeRelationDao = typeTypeRelationDao;
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
    public List<String> getDataDictionaryEntryKeys(ContextInfo context) throws OperationFailedException,
            MissingParameterException, PermissionDeniedException {
        return dataDictionaryService.getDataDictionaryEntryKeys(context);
    }

    @Override
    public DictionaryEntryInfo getDataDictionaryEntry(String entryKey, ContextInfo context)
            throws OperationFailedException, MissingParameterException, PermissionDeniedException,
            DoesNotExistException {
        return dataDictionaryService.getDataDictionaryEntry(entryKey, context);
    }

    @Override
    public StateProcessInfo getProcessByKey(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        StateProcessInfo spInfo = stateService.getProcessByKey(processKey, context);
        return spInfo;
    }

    @Override
    public List<String> getProcessByObjectType(String objectTypeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
    	return new ArrayList<String>();
    }

    @Override
    public StateInfo getState(String processKey, String stateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        StateInfo stateInfo = stateService.getState(processKey, stateKey, context);
        return stateInfo;
    }

    @Override
    public List<StateInfo> getStatesByProcess(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        List<StateInfo> stateInfos = stateService.getStatesByProcess(processKey, context);
        return stateInfos;
    }

    @Override
    public List<StateInfo> getInitialValidStates(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        List<StateInfo> stateInfos = stateService.getInitialValidStates(processKey, context);
        return stateInfos;
    }

    @Override
    public StateInfo getNextHappyState(String processKey, String currentStateKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        StateInfo stateInfo = stateService.getNextHappyState(processKey, currentStateKey, context);
        return stateInfo;
    }

    @Override
    public AtpInfo getAtp(String atpKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        AtpEntity atp = atpDao.find(atpKey);
        if (null == atp) {
            throw new DoesNotExistException(atpKey);
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
    public List<AtpInfo> getAtpsByDateAndType(Date searchDate, String searchTypeKey, ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AtpInfo> getAtpsByDatesAndType(Date startDate, Date endDate, String searchTypeKey, ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AtpInfo> getAtpsByStartDateRange(Date searchDateRangeStart, Date searchDateRangeEnd, ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
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
    public List<AtpInfo> getAtpsByStartDateRangeAndType(Date searchDateRangeStart, Date searchDateRangeEnd, String searchTypeKey, ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<AtpEntity> atps = atpDao.getByStartDateRangeAndType(searchDateRangeStart, searchDateRangeEnd, searchTypeKey);

        List<AtpInfo> result = new ArrayList<AtpInfo>(atps.size());
        if (null != atps) {
            for (AtpEntity entity : atps) {
                result.add(entity.toDto());
            }
        }
        return result;
    }

    
    
    
    @Override
    public List<AtpInfo> getAtpsByKeyList(List<String> atpKeyList, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<AtpEntity> atps = atpDao.findByIds(atpKeyList);

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
    public List<String> getAtpKeysByType(String atpTypeKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        try {
            TypeInfo type = getType(atpTypeKey, context);
            if (type == null) {
                throw new InvalidParameterException("No type found for key: " + atpTypeKey);
            }
        } catch (DoesNotExistException e) {
            throw new InvalidParameterException("No type found for key: " + atpTypeKey);
        }

        List<AtpEntity> results = atpDao.getByAtpTypeId(atpTypeKey);

        List<String> keys = new ArrayList<String>(results.size());

        for (AtpEntity atp : results) {
            keys.add(atp.getId());
        }

        return keys;
    }

    @Override
    public MilestoneInfo getMilestone(String milestoneKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        MilestoneEntity entity = milestoneDao.find(milestoneKey);

        if (entity != null) {
            return entity.toDto();
        } else {
            throw new DoesNotExistException(milestoneKey);
        }

    }

    @Override
    public List<MilestoneInfo> getMilestonesByKeyList(List<String> milestoneKeyList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        List<MilestoneEntity> milestones = milestoneDao.findByIds(milestoneKeyList);

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
    public List<String> getMilestoneKeysByType(String milestoneTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        AtpTypeEntity type = atpTypeDao.find(milestoneTypeKey);

        if (type == null) {
            throw new InvalidParameterException(milestoneTypeKey);
        }

        List<MilestoneEntity> entities = milestoneDao.getByMilestoneTypeId(milestoneTypeKey);

        if (entities == null) {
            return Collections.emptyList();
        }

        List<String> results = new ArrayList<String>(entities.size());

        for (MilestoneEntity entity : entities) {
            results.add(entity.getId());
        }

        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByAtp(String atpKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        AtpEntity atp = atpDao.find(atpKey);

        if (atp == null) {
            throw new InvalidParameterException(atpKey);
        }

        List<MilestoneEntity> entities = milestoneDao.getByAtp(atpKey);

        if (entities == null) {
            return Collections.emptyList();
        }

        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());

        for (MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }

        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDates(Date startDate, Date endDate, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        List<MilestoneEntity> entities = milestoneDao.getByDateRange(startDate, endDate);

        if (entities == null) {
            return Collections.emptyList();
        }

        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());

        for (MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }

        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDatesAndType(String milestoneTypeKey, Date startDate, Date endDate,
            ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {

        AtpTypeEntity type = atpTypeDao.find(milestoneTypeKey);

        if (type == null) {
            throw new InvalidParameterException(milestoneTypeKey);
        }

        List<MilestoneEntity> entities = milestoneDao.getByDateRangeAndType(startDate, endDate, milestoneTypeKey);

        if (entities == null) {
            return Collections.emptyList();
        }

        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());

        for (MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }

        return results;
    }


    @Override
    public List<String> searchForAtpKeys(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<String>();
    }

    @Override
    public List<AtpInfo> searchForAtps(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<AtpInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateAtp(String validationType, AtpInfo atpInfo, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
    	return new ArrayList<ValidationResultInfo>();
    }

    @Override
    @Transactional
    public AtpInfo createAtp(String atpKey, AtpInfo atpInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        AtpEntity atp = new AtpEntity(atpInfo);
        if (null != atpInfo.getStateKey()) {
        	atp.setAtpState(findState(AtpServiceConstants.ATP_PROCESS_KEY, atpInfo.getStateKey(), context));
        }
        if (null != atpInfo.getTypeKey()) {
            atp.setAtpType(atpTypeDao.find(atpInfo.getTypeKey()));
        }
        if (null != atpInfo.getDescr()) {
            atp.setDescr(new AtpRichTextEntity(atpInfo.getDescr()));
        }

        AtpEntity existing = atpDao.find(atpKey);
        if (existing != null) {
            throw new AlreadyExistsException();
        }
        atpDao.persist(atp);

		AtpEntity retrived = atpDao.find(atpKey);
		AtpInfo info = null;
		if(retrived != null){
			info = retrived.toDto();
		}

        return info;
    }

    private StateEntity findState(String processKey, String stateKey, ContextInfo context) throws InvalidParameterException, 
			MissingParameterException, OperationFailedException{
		StateEntity state = null;
		try {
			StateInfo stInfo = getState(processKey, stateKey, context);
			if(stInfo != null){
				state = new StateEntity(stInfo);
				return state;
			}
			else
				throw new OperationFailedException("The state does not exist. processKey " + processKey + " and stateKey: " + stateKey);
		} catch (DoesNotExistException e) {
			throw new OperationFailedException("The state does not exist. processKey " + processKey + " and stateKey: " + stateKey);
		}			
    }
    
    @Override
    @Transactional
    public AtpInfo updateAtp(String atpKey, AtpInfo atpInfo, ContextInfo context) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {

        AtpEntity atp = atpDao.find(atpKey);

        if (null != atp) {
            AtpEntity modifiedAtp = new AtpEntity(atpInfo);
            if (atpInfo.getStateKey() != null)
            	modifiedAtp.setAtpState(findState(AtpServiceConstants.ATP_PROCESS_KEY, atpInfo.getStateKey(), context));
            if (atpInfo.getTypeKey() != null)
                modifiedAtp.setAtpType(atpTypeDao.find(atpInfo.getTypeKey()));
            atpDao.merge(modifiedAtp);
            return atpDao.find(modifiedAtp.getId()).toDto();
        } else
            throw new DoesNotExistException(atpKey);
    }

    @Override
    @Transactional
    public StatusInfo deleteAtp(String atpKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        AtpEntity atp = atpDao.find(atpKey);
        if (null != atp) {
            List<AtpAtpRelationEntity> aarEntities = atpRelDao.getAtpAtpRelationsByAtp(atpKey);
            if (null != aarEntities) {
                for (AtpAtpRelationEntity aarEntity : aarEntities) {
                    atpRelDao.remove(aarEntity);
                }
            }
            List<AtpMilestoneRelationEntity> amrEntities = atpMilestoneRelationDao.getByAtpId(atpKey);
            if (null != amrEntities) {
                for (AtpMilestoneRelationEntity amrEntity : amrEntities) {
                    atpMilestoneRelationDao.remove(amrEntity);
                }
            }

            atpDao.remove(atp);
        } else
            status.setSuccess(Boolean.FALSE);

        // TODO Handle removal of orphan RichTextEntities

        return status;
    }


    @Override
    public List<String> searchForMilestoneKeys(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<String>();
    }
    
    @Override
    public List<MilestoneInfo> searchForMilestones(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<MilestoneInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateMilestone(String validationType, MilestoneInfo milestoneInfo,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {

        return null;
    }

    @Override
    @Transactional
    public MilestoneInfo createMilestone(String milestoneKey, MilestoneInfo milestoneInfo, ContextInfo context)
            throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        if (milestoneDao.find(milestoneKey) != null) {
            throw new AlreadyExistsException(milestoneKey);
        }

        MilestoneEntity entity = new MilestoneEntity(milestoneInfo);

        if (milestoneInfo.getTypeKey() != null) {
            entity.setAtpType(atpTypeDao.find(milestoneInfo.getTypeKey()));
        }

        if (milestoneInfo.getStateKey() != null) {
        	entity.setAtpState(findState(AtpServiceConstants.MILESTONE_PROCESS_KEY, milestoneInfo.getStateKey(), context));
        }

        if (milestoneInfo.getDescr() != null) {
            entity.setDescr(new AtpRichTextEntity(milestoneInfo.getDescr()));
        }

        milestoneDao.persist(entity);

        MilestoneInfo result = entity.toDto();

        return result;
    }

    @Override
    @Transactional
    public MilestoneInfo updateMilestone(String milestoneKey, MilestoneInfo milestoneInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {

        MilestoneEntity existingEntity = milestoneDao.find(milestoneKey);

        if (existingEntity == null) {
            throw new DoesNotExistException(milestoneKey);
        }

        MilestoneEntity updatedEntity = new MilestoneEntity(milestoneInfo);
        updatedEntity.setAtpState(findState(AtpServiceConstants.MILESTONE_PROCESS_KEY, milestoneInfo.getStateKey(), context));
        updatedEntity.setAtpType(atpTypeDao.find(milestoneInfo.getTypeKey()));

        milestoneDao.merge(updatedEntity);

        return updatedEntity.toDto();
    }

    @Override
    @Transactional
    public StatusInfo deleteMilestone(String milestoneKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        MilestoneEntity existingEntity = milestoneDao.find(milestoneKey);
        if (existingEntity != null) {
            milestoneDao.remove(existingEntity);
        } else {
            throw new DoesNotExistException(milestoneKey);
        }

        // TODO Handle removal of orphan RichTextEntities

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
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByIdList(List<String> atpAtpRelationIdList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
    	return new ArrayList<AtpAtpRelationInfo>();
    }

    @Override
    public List<String> getAtpAtpRelationIdsByType(String atpAtpRelationTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
    	return new ArrayList<String>();
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtp(String atpKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        List<AtpAtpRelationEntity> relEntities = atpRelDao.getAtpAtpRelationsByAtp(atpKey);
        List<AtpAtpRelationInfo> relInfos = new ArrayList<AtpAtpRelationInfo>();
        for (AtpAtpRelationEntity relEntity : relEntities) {
            AtpAtpRelationInfo relInfo = relEntity.toDto();
            relInfos.add(relInfo);
        }

        return relInfos;
    }

    @Override
    public List<String> searchForAtpAtpRelationIds(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<String>();
    }

    @Override
    public List<AtpAtpRelationInfo> searchForAtpAtpRelations(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<AtpAtpRelationInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateAtpAtpRelation(String validationType,
            AtpAtpRelationInfo atpAtpRelationInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
    	return new ArrayList<ValidationResultInfo>();
    }

    private boolean checkRelationExistence(AtpAtpRelationInfo atpAtpRelationInfo) {
        boolean exist = false;

        List<AtpAtpRelationEntity> rels = atpRelDao.getAtpAtpRelationsByAtpAndRelationType(
                atpAtpRelationInfo.getAtpKey(), atpAtpRelationInfo.getTypeKey());
        if (rels != null && !rels.isEmpty()) {
            for (AtpAtpRelationEntity rel : rels) {
                if (rel.getRelatedAtp().getId().equals(atpAtpRelationInfo.getRelatedAtpKey())) {
                    exist = true;
                    break;
                }
            }
        }

        return exist;
    }

    @Override
    @Transactional
    public AtpAtpRelationInfo createAtpAtpRelation(AtpAtpRelationInfo atpAtpRelationInfo, ContextInfo context)
            throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {

        if (!checkRelationExistence(atpAtpRelationInfo)) {
            AtpAtpRelationEntity atpRel = new AtpAtpRelationEntity(atpAtpRelationInfo);
            atpRel.setId(UUIDHelper.genStringUUID());

            if (null != atpAtpRelationInfo.getStateKey()) {
            	atpRel.setAtpState(findState(AtpServiceConstants.ATP_ATP_RELATION_PROCESS_KEY, atpAtpRelationInfo.getStateKey(), context));
            }
            if (null != atpAtpRelationInfo.getTypeKey()) {
                atpRel.setAtpType(atpTypeDao.find(atpAtpRelationInfo.getTypeKey()));
            }
            if (null != atpAtpRelationInfo.getAtpKey()) {
                atpRel.setAtp(atpDao.find(atpAtpRelationInfo.getAtpKey()));
            }
            if (null != atpAtpRelationInfo.getRelatedAtpKey()) {
                atpRel.setRelatedAtp(atpDao.find(atpAtpRelationInfo.getRelatedAtpKey()));
            }

            atpRelDao.persist(atpRel);

            return atpRelDao.find(atpRel.getId()).toDto();
        } else
            throw new AlreadyExistsException("The Atp-Atp relation already exists. atp="
                    + atpAtpRelationInfo.getAtpKey() + ", relatedAtp=" + atpAtpRelationInfo.getRelatedAtpKey());

    }

    @Override
    @Transactional
    public AtpAtpRelationInfo updateAtpAtpRelation(String atpAtpRelationId, AtpAtpRelationInfo atpAtpRelationInfo,
            ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {

        AtpAtpRelationEntity atpRel = atpRelDao.find(atpAtpRelationId);

        if (null != atpRel) {
            AtpAtpRelationEntity modifiedAtpRel = new AtpAtpRelationEntity(atpAtpRelationInfo);
            if (atpAtpRelationInfo.getAtpKey() != null)
                modifiedAtpRel.setAtp(atpDao.find(atpAtpRelationInfo.getAtpKey()));
            if (atpAtpRelationInfo.getRelatedAtpKey() != null)
                modifiedAtpRel.setRelatedAtp(atpDao.find(atpAtpRelationInfo.getRelatedAtpKey()));
            if (atpAtpRelationInfo.getTypeKey() != null)
                modifiedAtpRel.setAtpType(atpTypeDao.find(atpAtpRelationInfo.getTypeKey()));
            if (atpAtpRelationInfo.getStateKey() != null)
            	modifiedAtpRel.setAtpState(findState(AtpServiceConstants.ATP_ATP_RELATION_PROCESS_KEY, atpAtpRelationInfo.getStateKey(), context));

            atpRelDao.merge(modifiedAtpRel);
            return atpRelDao.find(modifiedAtpRel.getId()).toDto();
        } else
            throw new DoesNotExistException(atpAtpRelationId);
    }

    @Override
    @Transactional
    public StatusInfo deleteAtpAtpRelation(String atpAtpRelationId, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        AtpAtpRelationEntity atpRel = atpRelDao.find(atpAtpRelationId);
        if (atpRel != null)
            atpRelDao.remove(atpRel);
        else
            status.setSuccess(Boolean.FALSE);

        return status;
    }

    @Override
    public AtpMilestoneRelationInfo getAtpMilestoneRelation(String atpMilestoneRelationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        AtpMilestoneRelationEntity entity = atpMilestoneRelationDao.find(atpMilestoneRelationId);

        if (entity != null) {
            return entity.toDto();
        } else {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByIdList(List<String> atpMilestoneRelationIdList,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.findByIds(atpMilestoneRelationIdList);

        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for (AtpMilestoneRelationEntity entity : relations) {
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
    public List<String> getAtpMilestoneRelationIdsByType(String atpMilestoneRelationTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        AtpTypeEntity type = atpTypeDao.find(atpMilestoneRelationTypeKey);

        if (type == null) {
            throw new InvalidParameterException(atpMilestoneRelationTypeKey);
        }

        List<AtpMilestoneRelationEntity> entities = atpMilestoneRelationDao.getByTypeId(atpMilestoneRelationTypeKey);

        List<String> results = new ArrayList<String>(entities.size());

        for (AtpMilestoneRelationEntity entity : entities) {
            results.add(entity.getId());
        }

        return results;
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByAtp(String atpKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        AtpEntity atp = atpDao.find(atpKey);

        if (atp == null) {
            throw new DoesNotExistException(atpKey);
        }

        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.getByAtpId(atpKey);

        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for (AtpMilestoneRelationEntity entity : relations) {
            result.add(entity.toDto());
        }

        return result;
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByMilestone(String milestoneKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        MilestoneEntity milestone = milestoneDao.find(milestoneKey);

        if (milestone == null) {
            throw new DoesNotExistException(milestoneKey);
        }

        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.getByMilestoneId(milestoneKey);

        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for (AtpMilestoneRelationEntity entity : relations) {
            result.add(entity.toDto());
        }

        return result;
    }


    @Override
    public List<String> searchForAtpMilestoneRelationIds(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<String>();
    }

    @Override
    public List<AtpMilestoneRelationInfo> searchForAtpMilestoneRelations(QueryByCriteria criteria, ContextInfo context) 
	throws InvalidParameterException, MissingParameterException, 
	       OperationFailedException, PermissionDeniedException {

	return new ArrayList<AtpMilestoneRelationInfo>();
    }

    @Override
    public List<ValidationResultInfo> validateAtpMilestoneRelation(String validationType,
            AtpMilestoneRelationInfo atpMilestoneRelationInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
    	return new ArrayList<ValidationResultInfo>();
    }

    @Override
    @Transactional
    public AtpMilestoneRelationInfo createAtpMilestoneRelation(AtpMilestoneRelationInfo atpMilestoneRelationInfo,
            ContextInfo context) throws AlreadyExistsException, DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        AtpMilestoneRelationEntity atpMilestoneRel = atpMilestoneRelationDao.find(atpMilestoneRelationInfo.getId());

        if (atpMilestoneRel != null) {
            throw new AlreadyExistsException(atpMilestoneRelationInfo.getId());
        }

        AtpMilestoneRelationEntity createdRel = new AtpMilestoneRelationEntity(atpMilestoneRelationInfo);
        if (atpMilestoneRelationInfo.getAtpKey() != null) {
            createdRel.setAtp(atpDao.find(atpMilestoneRelationInfo.getAtpKey()));
        }
        if (atpMilestoneRelationInfo.getMilestoneKey() != null) {
            createdRel.setMilestone(milestoneDao.find(atpMilestoneRelationInfo.getMilestoneKey()));
        }
        if (atpMilestoneRelationInfo.getTypeKey() != null) {
            createdRel.setAtpType(atpTypeDao.find(atpMilestoneRelationInfo
                    .getTypeKey()));
        }
        if (atpMilestoneRelationInfo.getStateKey() != null) {
        	createdRel.setAtpState(findState(AtpServiceConstants.ATP_MILESTONE_RELATION_PROCESS_KEY, atpMilestoneRelationInfo.getStateKey(), context));
        }

        atpMilestoneRelationDao.persist(createdRel);

        return createdRel.toDto();
    }

    @Override
    @Transactional
    public AtpMilestoneRelationInfo updateAtpMilestoneRelation(String atpMilestoneRelationId,
            AtpMilestoneRelationInfo atpMilestoneRelationInfo, ContextInfo context)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {

        AtpMilestoneRelationEntity atpMilestoneRel = atpMilestoneRelationDao.find(atpMilestoneRelationId);

        if (atpMilestoneRel == null) {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }

        AtpMilestoneRelationEntity modifiedRel = new AtpMilestoneRelationEntity(atpMilestoneRelationInfo);
        if (atpMilestoneRelationInfo.getAtpKey() != null) {
            modifiedRel.setAtp(atpDao.find(atpMilestoneRelationInfo.getAtpKey()));
        }
        if (atpMilestoneRelationInfo.getMilestoneKey() != null) {
            modifiedRel.setMilestone(milestoneDao.find(atpMilestoneRelationInfo.getMilestoneKey()));
        }
        if (atpMilestoneRelationInfo.getTypeKey() != null) {
            modifiedRel.setAtpType(atpTypeDao.find(atpMilestoneRelationInfo
                    .getTypeKey()));
        }
        if (atpMilestoneRelationInfo.getStateKey() != null) {
        	modifiedRel.setAtpState(findState(AtpServiceConstants.ATP_MILESTONE_RELATION_PROCESS_KEY, atpMilestoneRelationInfo.getStateKey(), context));
        }

        atpMilestoneRelationDao.merge(modifiedRel);

        return modifiedRel.toDto();
    }

    @Override
    @Transactional
    public StatusInfo deleteAtpMilestoneRelation(String atpMilestoneRelationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);

        AtpMilestoneRelationEntity existingEntity = atpMilestoneRelationDao.find(atpMilestoneRelationId);
        if (existingEntity != null) {
            atpMilestoneRelationDao.remove(existingEntity);
        } else {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }

        return status;
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtpAndRelationType(String atpKey, String relationType,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<AtpAtpRelationInfo> aaRelInfos = new ArrayList<AtpAtpRelationInfo>();

        List<AtpAtpRelationEntity> aaRelEntities = atpRelDao.getAtpAtpRelationsByAtpAndRelationType(atpKey,
                relationType);
        if (null != aaRelEntities) {
            if (aaRelEntities.size() == 0) { // need to throw
                                             // DoesNotExistException if no such
                                             // ATP
                try {
                    getAtp(atpKey, context);
                } catch (DoesNotExistException dnee) { // catch so as to add
                                                       // more info
                    throw new DoesNotExistException("Atp does not exist: " + dnee.getMessage(), dnee);
                }
            }
            for (AtpAtpRelationEntity aaRelEntity : aaRelEntities) {
                aaRelInfos.add(aaRelEntity.toDto());
            }
        }
        return aaRelInfos;
    }

    // TypeService methods
    @Override
    public TypeInfo getType(String typeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        AtpTypeEntity atpType = atpTypeDao.find(typeKey);

        if (null == atpType) {
            throw new DoesNotExistException();
        }
        return atpType.toDto();
    }

    @Override
    public List<TypeInfo> getTypesByRefObjectURI(String refObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {

        List<TypeEntity<? extends BaseAttributeEntity<?>>> typeEntities = new ArrayList<TypeEntity<? extends BaseAttributeEntity<?>>>();

        if (null == refObjectURI) {
            throw new MissingParameterException("refObjectUri parameter cannot be null");
        }
        if (refObjectURI.startsWith(AtpServiceConstants.NAMESPACE)) {
            typeEntities.addAll(atpTypeDao.findAll(refObjectURI));
        } else {
            throw new DoesNotExistException("This method does not know how to handle object type:" + refObjectURI);
        }
        List<TypeInfo> typeInfos = new ArrayList<TypeInfo>();
        for (TypeEntity<? extends BaseAttributeEntity<?>> typeEntity : typeEntities) {
            typeInfos.add(typeEntity.toDto());
        }
        return typeInfos;
    }

    @Override
    public List<TypeInfo> getAllowedTypesForType(String ownerTypeKey, String relatedRefObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        
        if ( ! relatedRefObjectURI.startsWith(AtpServiceConstants.NAMESPACE) ) {
            throw new DoesNotExistException("This method does not know how to handle object type:"
                    + relatedRefObjectURI);
        }

        // get the TypeTypeRelations
        List<TypeTypeRelationEntity> typeTypeRelations = typeTypeRelationDao
                .getTypeTypeRelationsByOwnerAndRelationTypes(ownerTypeKey,
                        TypeServiceConstants.TYPE_TYPE_RELATION_ALLOWED_TYPE_KEY);
        
        // create a List of the related Types' IDs
        List<String> ids = new ArrayList<String>();
        for (TypeTypeRelationEntity entity : typeTypeRelations) {
            ids.add(entity.getRelatedTypeId());
        }

        // now get the List of the related Types based on those IDs
        List<TypeEntity<? extends BaseAttributeEntity<?>>> typeEntities = new ArrayList<TypeEntity<? extends BaseAttributeEntity<?>>>();
        typeEntities.addAll(atpTypeDao.findByIds(ids));
        
        // convert them to DTOs and return them
        List<TypeInfo> typeInfos = new ArrayList<TypeInfo>();
        for (TypeEntity<? extends BaseAttributeEntity<?>> entity : typeEntities) {
            typeInfos.add(entity.toDto());
        }
        
        return typeInfos;
    }

    @Override
    public List<TypeTypeRelationInfo> getTypeRelationsByOwnerType(String ownerTypeKey, String relationTypeKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {

        List<TypeTypeRelationEntity> typeTypeReltns = new ArrayList<TypeTypeRelationEntity>();

        if (null == relationTypeKey || null == ownerTypeKey) {
            throw new MissingParameterException("Neither ownerTypeKey nor relationTypeKey parameters may be null");
        } else {
            typeTypeReltns.addAll(typeTypeRelationDao.getTypeTypeRelationsByOwnerAndRelationTypes(ownerTypeKey,
                    relationTypeKey));
        }
        List<TypeTypeRelationInfo> ttrInfos = new ArrayList<TypeTypeRelationInfo>();
        for (TypeTypeRelationEntity ttrEntity : typeTypeReltns) {
            ttrInfos.add(ttrEntity.toDto());
        }
        return ttrInfos;
    }
    // end TypeService methods
}
