package org.kuali.student.r2.core.class1.atp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.kuali.student.r2.common.datadictionary.dto.DictionaryEntryInfo;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StateInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.TypeTypeRelationInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.core.atp.dto.AtpAtpRelationInfo;
import org.kuali.student.r2.core.atp.dto.AtpInfo;
import org.kuali.student.r2.core.atp.dto.AtpMilestoneRelationInfo;
import org.kuali.student.r2.core.atp.dto.MilestoneInfo;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.kuali.student.r2.core.class1.atp.dao.AtpAtpRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpAtpRelationTypeDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpMilestoneRelationDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpMilestoneRelationTypeDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpRichTextDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpStateDao;
import org.kuali.student.r2.core.class1.atp.dao.AtpTypeDao;
import org.kuali.student.r2.core.class1.atp.dao.MilestoneDao;
import org.kuali.student.r2.core.class1.atp.dao.MilestoneTypeDao;
import org.kuali.student.r2.core.class1.atp.model.AtpAtpRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpMilestoneRelationEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpMilestoneRelationTypeEntity;
import org.kuali.student.r2.core.class1.atp.model.AtpRichTextEntity;
import org.kuali.student.r2.core.class1.atp.model.MilestoneEntity;
import org.kuali.student.r2.core.class1.atp.model.MilestoneTypeEntity;
import org.springframework.transaction.annotation.Transactional;

@WebService(name = "AtpService", serviceName = "AtpService", portName = "AtpService", targetNamespace = "http://student.kuali.org/wsdl/atp")
@Transactional(readOnly=true,noRollbackFor={DoesNotExistException.class},rollbackFor={Throwable.class})
public class AtpServiceImpl implements AtpService{

    private AtpDao atpDao;
    private AtpTypeDao atpTypeDao;
    private AtpStateDao atpStateDao;
    private AtpRichTextDao atpRichTextDao;
    private AtpAtpRelationDao atpRelDao;
    private AtpAtpRelationTypeDao atpRelTypeDao;
    private MilestoneDao milestoneDao;
    private MilestoneTypeDao milestoneTypeDao;
    private AtpMilestoneRelationDao atpMilestoneRelationDao;
    private AtpMilestoneRelationTypeDao atpMilestoneRelationTypeDao;
    
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

    public AtpAtpRelationTypeDao getAtpRelTypeDao() {
        return atpRelTypeDao;
    }

    public void setAtpRelTypeDao(AtpAtpRelationTypeDao atpRelTypeDao) {
        this.atpRelTypeDao = atpRelTypeDao;
    }
    
    public MilestoneDao getMilestoneDao() {
        return milestoneDao;
    }

    public void setMilestoneDao(MilestoneDao milestoneDao) {
        this.milestoneDao = milestoneDao;
    }

    public MilestoneTypeDao getMilestoneTypeDao() {
        return milestoneTypeDao;
    }

    public void setMilestoneTypeDao(MilestoneTypeDao milestoneTypeDao) {
        this.milestoneTypeDao = milestoneTypeDao;
    }

    public AtpMilestoneRelationDao getAtpMilestoneRelationDao() {
        return atpMilestoneRelationDao;
    }

    public void setAtpMilestoneRelationDao(AtpMilestoneRelationDao atpMilestoneRelationDao) {
        this.atpMilestoneRelationDao = atpMilestoneRelationDao;
    }

    public AtpMilestoneRelationTypeDao getAtpMilestoneRelationTypeDao() {
        return atpMilestoneRelationTypeDao;
    }

    public void setAtpMilestoneRelationTypeDao(AtpMilestoneRelationTypeDao atpMilestoneRelationTypeDao) {
        this.atpMilestoneRelationTypeDao = atpMilestoneRelationTypeDao;
    }

    @Override
    public List<String> getDataDictionaryEntryKeys(ContextInfo context) throws OperationFailedException,
            MissingParameterException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public DictionaryEntryInfo getDataDictionaryEntry(String entryKey, ContextInfo context)
            throws OperationFailedException, MissingParameterException, PermissionDeniedException,
            DoesNotExistException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public TypeInfo getType(String typeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getTypesByRefObjectURI(String refObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeInfo> getAllowedTypesForType(String ownerTypeKey, String relatedRefObjectURI, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<TypeTypeRelationInfo> getTypeRelationsByOwnerType(String ownerTypeKey, String relationTypeKey,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getProcessKeys(String typeKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getState(String processKey, String stateKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getStatesByProcess(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<StateInfo> getInitialValidStates(String processKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public StateInfo getNextHappyState(String processKey, String currentStateKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
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
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AtpInfo> getAtpsByDates(Date startDate, Date endDate, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AtpInfo> getAtpsByKeyList(List<String> atpKeyList, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getAtpKeysByType(String atpTypeKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public MilestoneInfo getMilestone(String milestoneKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        MilestoneEntity entity = milestoneDao.find(milestoneKey);
        
        if(entity != null) {
            return entity.toDto();
        }
        else {
            throw new DoesNotExistException(milestoneKey);
        }
        
    }

    @Override
    public List<MilestoneInfo> getMilestonesByKeyList(List<String> milestoneKeyList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        List<MilestoneEntity> milestones = milestoneDao.findByIds(milestoneKeyList);
        
        if(milestones == null) {
            throw new DoesNotExistException();
        }
        
        List<MilestoneInfo> result = new ArrayList<MilestoneInfo>(milestones.size());
        for(MilestoneEntity entity : milestones) {
            if(entity == null) {
                // if one of the entities from "findByIds" is returned as null, then one of the keys in the list was not found
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
        
        MilestoneTypeEntity type = milestoneTypeDao.find(milestoneTypeKey);
        
        if(type == null) {
            throw new InvalidParameterException(milestoneTypeKey);
        }
        
        List<MilestoneEntity> entities = milestoneDao.getByMilestoneTypeId(milestoneTypeKey);
        
        if(entities == null) {
            return Collections.emptyList();
        }
        
        List<String> results = new ArrayList<String>(entities.size());
        
        for(MilestoneEntity entity : entities) {
            results.add(entity.getId());
        }
        
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByAtp(String atpKey, ContextInfo context) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        
        AtpEntity atp = atpDao.find(atpKey);
        
        if(atp == null) {
            throw new InvalidParameterException(atpKey);
        }
        
        List<MilestoneEntity> entities = milestoneDao.getByAtp(atpKey);
        
        if(entities == null) {
            return Collections.emptyList();
        }
        
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());
        
        for(MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }
        
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDates(Date startDate, Date endDate, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        
        List<MilestoneEntity> entities = milestoneDao.getByDateRange(startDate, endDate);
        
        if(entities == null) {
            return Collections.emptyList();
        }
        
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());
        
        for(MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }
        
        return results;
    }

    @Override
    public List<MilestoneInfo> getMilestonesByDatesAndType(String milestoneTypeKey, Date startDate, Date endDate,
            ContextInfo context) throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        
        MilestoneTypeEntity type = milestoneTypeDao.find(milestoneTypeKey);
        
        if(type == null) {
            throw new InvalidParameterException(milestoneTypeKey);
        }
        
        List<MilestoneEntity> entities = milestoneDao.getByDateRangeAndType(startDate, endDate, milestoneTypeKey);
        
        if(entities == null) {
            return Collections.emptyList();
        }
        
        List<MilestoneInfo> results = new ArrayList<MilestoneInfo>(entities.size());
        
        for(MilestoneEntity entity : entities) {
            results.add(entity.toDto());
        }
        
        return results;
    }

    @Override
    public List<ValidationResultInfo> validateAtp(String validationType, AtpInfo atpInfo, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional
    public AtpInfo createAtp(String atpKey, AtpInfo atpInfo, ContextInfo context) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {

        AtpEntity atp = new AtpEntity(atpInfo);
        if (null != atpInfo.getStateKey()) {
            atp.setAtpState(atpStateDao.find(atpInfo.getStateKey()));
        }
        if (null != atpInfo.getTypeKey()) {
            atp.setAtpType(atpTypeDao.find(atpInfo.getTypeKey()));
        }
        if (null != atpInfo.getDescr()) {
            atp.setDescr(new AtpRichTextEntity(atpInfo.getDescr()));
        }
        
        AtpEntity existing = atpDao.find(atpKey);
        if( existing != null) {
            throw new AlreadyExistsException();
	    }
        atpDao.persist(atp);
        
        return atpDao.find(atpKey).toDto();
    }

    @Override
    @Transactional(readOnly=false)
    public AtpInfo updateAtp(String atpKey, AtpInfo atpInfo, ContextInfo context) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {

        AtpEntity atp = atpDao.find(atpKey);
        
        if( null != atp){
            AtpEntity modifiedAtp = new AtpEntity(atpInfo);
            if(atpInfo.getStateKey() != null)
                modifiedAtp.setAtpState(atpStateDao.find(atpInfo.getStateKey()));
            if(atpInfo.getTypeKey() != null)
                modifiedAtp.setAtpType(atpTypeDao.find(atpInfo.getTypeKey()));
            atpDao.merge(modifiedAtp);
	        return atpDao.find(modifiedAtp.getId()).toDto();
        }
        else
            throw new DoesNotExistException(atpKey);
    }

    @Override
    @Transactional(readOnly=false)
    public StatusInfo deleteAtp(String atpKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        
        AtpEntity atp = atpDao.find(atpKey);
        if( null != atp){
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
        }
        else
            status.setSuccess(Boolean.FALSE);
        
        // TODO Handle removal of orphan RichTextEntities
        
        return status;
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
        
        if(milestoneDao.find(milestoneKey) != null) {
            throw new AlreadyExistsException(milestoneKey);
        }
        
        MilestoneEntity entity = new MilestoneEntity(milestoneInfo);
        
        if(milestoneInfo.getTypeKey() != null) {
            entity.setMilestoneType(milestoneTypeDao.find(milestoneInfo.getTypeKey()));
        }
        
        if(milestoneInfo.getStateKey() != null) {
            entity.setAtpState(atpStateDao.find(milestoneInfo.getStateKey()));
        }
        
        if(milestoneInfo.getDescr() != null) {
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
        
        if(existingEntity == null) {
            throw new DoesNotExistException(milestoneKey);
        }
        
        MilestoneEntity updatedEntity = new MilestoneEntity(milestoneInfo);
        updatedEntity.setAtpState(atpStateDao.find(milestoneInfo.getStateKey()));
        updatedEntity.setMilestoneType(milestoneTypeDao.find(milestoneInfo.getTypeKey()));
        
        milestoneDao.merge(updatedEntity);
        
        return updatedEntity.toDto();
    }

    @Override
    @Transactional
    public StatusInfo deleteMilestone(String milestoneKey, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        StatusInfo status =new  StatusInfo();
        status.setSuccess(Boolean.TRUE);
        
        MilestoneEntity existingEntity = milestoneDao.find(milestoneKey);
        if(existingEntity != null){
            milestoneDao.remove(existingEntity);
        }
        else {
            throw new DoesNotExistException(milestoneKey);
        }
        
        // TODO Handle removal of orphan RichTextEntities
        
        return status;
    }

    @Override
    public AtpAtpRelationInfo getAtpAtpRelation(String atpAtpRelationId, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByIdList(List<String> atpAtpRelationIdList, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<String> getAtpAtpRelationIdsByType(String atpAtpRelationTypeKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtp(String atpKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        List<AtpAtpRelationEntity> relEntities = atpRelDao.getAtpAtpRelationsByAtp(atpKey);
        List<AtpAtpRelationInfo> relInfos = new ArrayList<AtpAtpRelationInfo>();
        for(AtpAtpRelationEntity relEntity : relEntities){
            AtpAtpRelationInfo relInfo = relEntity.toDto();
            relInfos.add(relInfo);
        }
        
        return relInfos;
    }

    @Override
    public List<ValidationResultInfo> validateAtpAtpRelation(String validationType,
            AtpAtpRelationInfo atpAtpRelationInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional(readOnly=false)
    public AtpAtpRelationInfo createAtpAtpRelation(AtpAtpRelationInfo atpAtpRelationInfo, ContextInfo context)
            throws AlreadyExistsException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        AtpAtpRelationEntity atpRel = new AtpAtpRelationEntity(atpAtpRelationInfo);
        if (null != atpAtpRelationInfo.getStateKey()) {
            atpRel.setAtpState(atpStateDao.find(atpAtpRelationInfo.getStateKey()));
        }
        if (null != atpAtpRelationInfo.getTypeKey()) {
            atpRel.setAtpAtpRelationType(atpRelTypeDao.find(atpAtpRelationInfo.getTypeKey()));
        }
        if (null != atpAtpRelationInfo.getAtpKey()) {
            atpRel.setAtp(atpDao.find(atpAtpRelationInfo.getAtpKey()));
        }
        if(null != atpAtpRelationInfo.getRelatedAtpKey()) {
            atpRel.setRelatedAtp(atpDao.find(atpAtpRelationInfo.getRelatedAtpKey()));
        }
               
        atpRelDao.persist(atpRel);
        
        return atpAtpRelationInfo;

    }

    @Override
    @Transactional(readOnly=false)
    public AtpAtpRelationInfo updateAtpAtpRelation(String atpAtpRelationId, AtpAtpRelationInfo atpAtpRelationInfo,
            ContextInfo context) throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException {
        
        AtpAtpRelationEntity atpRel = atpRelDao.find(atpAtpRelationId);
        
        if( null != atpRel){
            AtpAtpRelationEntity modifiedatpRel = new AtpAtpRelationEntity(atpAtpRelationInfo);
            if(atpAtpRelationInfo.getAtpKey() != null)
                modifiedatpRel.setAtp(atpDao.find(atpAtpRelationInfo.getAtpKey()));
            if(atpAtpRelationInfo.getRelatedAtpKey() != null)
                modifiedatpRel.setRelatedAtp(atpDao.find(atpAtpRelationInfo.getRelatedAtpKey()));
            if(atpAtpRelationInfo.getTypeKey() != null)
                modifiedatpRel.setAtpAtpRelationType(atpRelTypeDao.find(atpAtpRelationInfo.getTypeKey()));
            if(atpAtpRelationInfo.getStateKey() != null)
                modifiedatpRel.setAtpState(atpStateDao.find(atpAtpRelationInfo.getStateKey()));
            atpRelDao.merge(modifiedatpRel);
        }
        else
            throw new DoesNotExistException(atpAtpRelationId);
        return atpAtpRelationInfo;
    }

    @Override
    @Transactional(readOnly=false)
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
        
        if(entity != null) {
            return entity.toDto();
        }
        else {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByIdList(List<String> atpMilestoneRelationIdList,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.findByIds(atpMilestoneRelationIdList);
        
        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for(AtpMilestoneRelationEntity entity : relations) {
            if(entity == null) {
                // if one of the entities from "findByIds" is returned as null, then one of the keys in the list was not found
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
        AtpMilestoneRelationTypeEntity type = atpMilestoneRelationTypeDao.find(atpMilestoneRelationTypeKey);
        
        if(type == null) {
            throw new InvalidParameterException(atpMilestoneRelationTypeKey);
        }
        
        List<AtpMilestoneRelationEntity> entities = atpMilestoneRelationDao.getByTypeId(atpMilestoneRelationTypeKey);
        
        List<String> results = new ArrayList<String>(entities.size());
        
        for(AtpMilestoneRelationEntity entity : entities) {
            results.add(entity.getId());
        }
        
        return results;
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByAtp(String atpKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        AtpEntity atp = atpDao.find(atpKey);
        
        if(atp == null) {
            throw new DoesNotExistException(atpKey);
        }
        
        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.getByAtpId(atpKey);
        
        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for(AtpMilestoneRelationEntity entity : relations) {
            result.add(entity.toDto());
        }
        
        return result;
    }

    @Override
    public List<AtpMilestoneRelationInfo> getAtpMilestoneRelationsByMilestone(String milestoneKey, ContextInfo context)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        MilestoneEntity milestone = milestoneDao.find(milestoneKey);
        
        if(milestone == null) {
            throw new DoesNotExistException(milestoneKey);
        }
        
        List<AtpMilestoneRelationEntity> relations = atpMilestoneRelationDao.getByMilestoneId(milestoneKey);
        
        List<AtpMilestoneRelationInfo> result = new ArrayList<AtpMilestoneRelationInfo>(relations.size());
        for(AtpMilestoneRelationEntity entity : relations) {
            result.add(entity.toDto());
        }
        
        return result;
    }

    @Override
    public List<ValidationResultInfo> validateAtpMilestoneRelation(String validationType,
            AtpMilestoneRelationInfo atpMilestoneRelationInfo, ContextInfo context) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException {
        // TODO Li Pan - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    @Transactional
    public AtpMilestoneRelationInfo createAtpMilestoneRelation(AtpMilestoneRelationInfo atpMilestoneRelationInfo,
            ContextInfo context) throws AlreadyExistsException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        
        AtpMilestoneRelationEntity atpMilestoneRel = atpMilestoneRelationDao.find(atpMilestoneRelationInfo.getId());
        
        if(atpMilestoneRel != null) {
            throw new AlreadyExistsException(atpMilestoneRelationInfo.getId());
        }
        
        AtpMilestoneRelationEntity createdRel = new AtpMilestoneRelationEntity(atpMilestoneRelationInfo);
        if(atpMilestoneRelationInfo.getAtpKey() != null) {
            createdRel.setAtp(atpDao.find(atpMilestoneRelationInfo.getAtpKey()));
        }
        if(atpMilestoneRelationInfo.getMilestoneKey() != null) {
            createdRel.setMilestone(milestoneDao.find(atpMilestoneRelationInfo.getMilestoneKey()));
        }
        if(atpMilestoneRelationInfo.getTypeKey() != null) {
            createdRel.setAtpMilestoneRelationType(atpMilestoneRelationTypeDao.find(atpMilestoneRelationInfo.getTypeKey()));
        }
        if(atpMilestoneRelationInfo.getStateKey() != null) {
            createdRel.setAtpState(atpStateDao.find(atpMilestoneRelationInfo.getStateKey()));
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
        
        if(atpMilestoneRel == null) {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }
        
        AtpMilestoneRelationEntity modifiedRel = new AtpMilestoneRelationEntity(atpMilestoneRelationInfo);
        if(atpMilestoneRelationInfo.getAtpKey() != null) {
            modifiedRel.setAtp(atpDao.find(atpMilestoneRelationInfo.getAtpKey()));
        }
        if(atpMilestoneRelationInfo.getMilestoneKey() != null) {
            modifiedRel.setMilestone(milestoneDao.find(atpMilestoneRelationInfo.getMilestoneKey()));
        }
        if(atpMilestoneRelationInfo.getTypeKey() != null) {
            modifiedRel.setAtpMilestoneRelationType(atpMilestoneRelationTypeDao.find(atpMilestoneRelationInfo.getTypeKey()));
        }
        if(atpMilestoneRelationInfo.getStateKey() != null) {
            modifiedRel.setAtpState(atpStateDao.find(atpMilestoneRelationInfo.getStateKey()));
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
        if(existingEntity != null){
            atpMilestoneRelationDao.remove(existingEntity);
        }
        else {
            throw new DoesNotExistException(atpMilestoneRelationId);
        }
        
        return status;
    }

    @Override
    public List<AtpAtpRelationInfo> getAtpAtpRelationsByAtpAndRelationType(String atpKey, String relationType,
            ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

}
