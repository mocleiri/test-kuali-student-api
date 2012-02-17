package org.kuali.student.r2.core.class1.process.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
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
import org.kuali.student.r2.common.exceptions.ReadOnlyException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.common.util.constants.ProcessServiceConstants;
import org.kuali.student.r2.core.class1.process.dao.CheckDao;
import org.kuali.student.r2.core.class1.process.dao.CheckTypeDao;
import org.kuali.student.r2.core.class1.process.dao.InstructionDao;
import org.kuali.student.r2.core.class1.process.dao.InstructionTypeDao;
import org.kuali.student.r2.core.class1.process.dao.ProcessDao;
import org.kuali.student.r2.core.class1.process.dao.ProcessTypeDao;
import org.kuali.student.r2.core.class1.process.model.CheckEntity;
import org.kuali.student.r2.core.class1.process.model.InstructionEntity;
import org.kuali.student.r2.core.class1.process.model.ProcessEntity;
import org.kuali.student.r2.core.class1.process.model.ProcessRichTextEntity;
import org.kuali.student.r2.core.class1.state.model.StateEntity;
import org.kuali.student.r2.core.process.dto.CheckInfo;
import org.kuali.student.r2.core.process.dto.InstructionInfo;
import org.kuali.student.r2.core.process.dto.ProcessCategoryInfo;
import org.kuali.student.r2.core.process.dto.ProcessInfo;
import org.kuali.student.r2.core.process.service.ProcessService;
import org.kuali.student.r2.core.state.dto.StateInfo;
import org.kuali.student.r2.core.state.service.StateService;
import org.kuali.student.r2.core.type.service.TypeService;
import org.springframework.transaction.annotation.Transactional;

public class ProcessServiceImpl implements ProcessService {

    private CheckDao checkDao;
    private CheckTypeDao checkTypeDao;
    private InstructionDao instructionDao;
    private InstructionTypeDao instructionTypeDao;
    private ProcessDao processDao;
    private ProcessTypeDao processTypeDao;
    private StateService stateService;
    private TypeService typeService;

    public CheckDao getCheckDao() {
        return checkDao;
    }

    public void setCheckDao(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

    public CheckTypeDao getCheckTypeDao() {
        return checkTypeDao;
    }

    public void setCheckTypeDao(CheckTypeDao checkTypeDao) {
        this.checkTypeDao = checkTypeDao;
    }

    public InstructionDao getInstructionDao() {
        return instructionDao;
    }

    public void setInstructionDao(InstructionDao instructionDao) {
        this.instructionDao = instructionDao;
    }

    public InstructionTypeDao getInstructionTypeDao() {
        return instructionTypeDao;
    }

    public void setInstructionTypeDao(InstructionTypeDao instructionTypeDao) {
        this.instructionTypeDao = instructionTypeDao;
    }

    public ProcessDao getProcessDao() {
        return processDao;
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

    public ProcessTypeDao getProcessTypeDao() {
        return processTypeDao;
    }

    public void setProcessTypeDao(ProcessTypeDao processTypeDao) {
        this.processTypeDao = processTypeDao;
    }

    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public ProcessCategoryInfo getProcessCategory(@WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ProcessCategoryInfo> getProcessCategoriesByIds(
            @WebParam(name = "processCategoryIds") List<String> processCategoryIds,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<String> getProcessCategoryIdsByType(@WebParam(name = "processTypeKey") String processTypeKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ProcessCategoryInfo> getProcessCategoriesForProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<String> searchForProcessCategoryIds(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ProcessCategoryInfo> searchForProcessCategories(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ValidationResultInfo> validateProcessCategory(
            @WebParam(name = "validationTypeKey") String validationTypeKey,
            @WebParam(name = "processCategoryInfo") ProcessCategoryInfo processCategoryInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public ProcessCategoryInfo createProcessCategory(@WebParam(name = "processInfo") ProcessCategoryInfo processInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public ProcessCategoryInfo updateProcessCategory(@WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "processInfo") ProcessCategoryInfo processInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, ReadOnlyException, VersionMismatchException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public StatusInfo deleteProcessCategory(@WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public StatusInfo addProcessToProcessCategory(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public StatusInfo removeProcessFromProcessCategory(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public ProcessInfo getProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        ProcessEntity processEntity = processDao.find(processKey);
        if (processEntity == null) {
            throw new DoesNotExistException(processKey);
        }

        return processEntity.toDto();
    }

    @Override
    public List<ProcessInfo> getProcessesByKeys(@WebParam(name = "processKeys") List<String> processKeys,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<ProcessEntity> processEntities = processDao.findByIds(processKeys);
        if (processEntities == null || processEntities.isEmpty()) {
            throw new DoesNotExistException("No records found");
        }
        List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
        for (ProcessEntity processEntity : processEntities) {
            if (processEntity == null) {
                throw new DoesNotExistException();
            }
            processInfoList.add(processEntity.toDto());
        }
        return processInfoList;
    }

    @Override
    public List<String> getProcessKeysByType(@WebParam(name = "processTypeKey") String processTypeKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<ProcessEntity> processEntities = processDao.getByProcessTypeId(processTypeKey);

        List<String> processIds = new ArrayList<String>();
        for (ProcessEntity processEntity : processEntities) {
            processIds.add(processEntity.getId());
        }

        return processIds;
    }

    @Override
    public List<ProcessInfo> getProcessesForProcessCategory(
            @WebParam(name = "processCategoryId") String processCategoryId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<String> searchForProcessKeys(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ProcessInfo> searchForProcesss(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ValidationResultInfo> validateProcess(@WebParam(name = "validationTypeKey") String validationTypeKey,
            @WebParam(name = "processInfo") ProcessInfo processInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    @Transactional(readOnly = false)
    public ProcessInfo createProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "processInfo") ProcessInfo processInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException, ReadOnlyException {

        ProcessEntity existing = processDao.find(processKey);
        if (existing != null) {
            throw new AlreadyExistsException(processKey);
        }

        ProcessEntity process = new ProcessEntity(processInfo);

        process.setId(processKey);

        if (processInfo.getDescr() != null) {
            process.setDescr(new ProcessRichTextEntity(processInfo.getDescr()));
        }

        if (StringUtils.isNotBlank(processInfo.getStateKey())) {
            process.setProcessState(findState(ProcessServiceConstants.PROCESS_LIFECYCLE_KEY, processInfo.getStateKey(),
                    contextInfo));
        }

        if (StringUtils.isNotBlank(processInfo.getTypeKey())) {
            process.setProcessType(processTypeDao.find(processInfo.getTypeKey()));
        }

        processDao.persist(process);

        ProcessEntity retrieved = processDao.find(processKey);

        return retrieved.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public ProcessInfo updateProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "processInfo") ProcessInfo processInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, ReadOnlyException, VersionMismatchException {

        ProcessEntity processEntity = processDao.find(processKey);

        if (processEntity == null) {
            throw new DoesNotExistException(processKey);
        }

        ProcessEntity toUpdate = new ProcessEntity(processInfo);

        if (StringUtils.isNotBlank(processInfo.getStateKey())) {
            toUpdate.setProcessState(findState(ProcessServiceConstants.PROCESS_LIFECYCLE_KEY,
                    processInfo.getStateKey(), contextInfo));
        }

        if (StringUtils.isNotBlank(processInfo.getTypeKey())) {
            toUpdate.setProcessType(processTypeDao.find(processInfo.getTypeKey()));
        }

        processDao.merge(toUpdate);
        return processDao.find(toUpdate.getId()).toDto();

    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        ProcessEntity processEntity = processDao.find(processKey);
        StatusInfo status = new StatusInfo();

        if (processEntity == null) {
            status.setSuccess(false);
            return status;
        }

        List<InstructionEntity> instructionEntities = instructionDao.getByProcess(processKey);
        for (InstructionEntity instructionEntity : instructionEntities) {
            instructionDao.remove(instructionEntity);
        }

        processDao.remove(processEntity);

        status.setSuccess(true);
        return status;

    }

    @Override
    public CheckInfo getCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        CheckEntity checkEntity = checkDao.find(checkKey);
        if (checkEntity == null) {
            throw new DoesNotExistException(checkKey);
        }
        return checkEntity.toDto();
    }

    @Override
    public List<CheckInfo> getChecksByIds(@WebParam(name = "checkKeys") List<String> checkKeys,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<CheckEntity> checkEntities = checkDao.findByIds(checkKeys);
        if (checkEntities == null || checkEntities.isEmpty()) {
            throw new DoesNotExistException("No records found");
        }
        List<CheckInfo> checkInfoList = new ArrayList<CheckInfo>();
        for (CheckEntity checkEntity : checkEntities) {
            if (checkEntity == null) {
                throw new DoesNotExistException();
            }
            checkInfoList.add(checkEntity.toDto());
        }
        return checkInfoList;
    }

    @Override
    public List<String> getCheckKeysByType(@WebParam(name = "checkTypeKey") String checkTypeKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<CheckEntity> checkEntities = checkDao.getByCheckTypeId(checkTypeKey);

        List<String> checkIds = new ArrayList<String>();
        for (CheckEntity processEntity : checkEntities) {
            checkIds.add(processEntity.getId());
        }

        return checkIds;
    }

    @Override
    public List<String> searchForCheckKeys(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<CheckInfo> searchForChecks(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ValidationResultInfo> validateCheck(@WebParam(name = "validationTypeKey") String validationTypeKey,
            @WebParam(name = "checkInfo") CheckInfo checkInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    @Transactional(readOnly = false)
    public CheckInfo createCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "checkInfo") CheckInfo checkInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws AlreadyExistsException, DataValidationErrorException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException {

        if (checkDao.find(checkKey) != null) {
            throw new AlreadyExistsException(checkInfo.getKey());
        }

        CheckEntity checkEntity = new CheckEntity(checkInfo);
        checkEntity.setId(checkKey);

        checkEntity.setCheckType(checkTypeDao.find(checkInfo.getTypeKey()));
        checkEntity.setCheckState(findState(ProcessServiceConstants.CHECK_LIFECYCLE_KEY, checkInfo.getStateKey(),
                contextInfo));

        if (StringUtils.isNotBlank(checkInfo.getProcessKey())) {
            ProcessEntity process = processDao.find(checkInfo.getProcessKey());
            if (null == process) {
                throw new InvalidParameterException("Check processKey not valid.");
            }
            checkEntity.setProcess(process);
        } else {
            checkEntity.setProcess(null);
        }

        checkDao.persist(checkEntity);

        CheckEntity retrieved = checkDao.find(checkKey);

        return retrieved.toDto();

    }

    @Override
    @Transactional(readOnly = false)
    public CheckInfo updateCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "checkInfo") CheckInfo checkInfo, @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, ReadOnlyException,
            VersionMismatchException {

        CheckEntity exists = checkDao.find(checkKey);

        if (exists == null) {
            throw new DoesNotExistException(checkKey);
        }

        CheckEntity toUpdate = new CheckEntity(checkInfo);

        toUpdate.setCheckType(checkTypeDao.find(checkInfo.getTypeKey()));
        toUpdate.setCheckState(findState(ProcessServiceConstants.CHECK_LIFECYCLE_KEY, checkInfo.getStateKey(),
                contextInfo));

        if (StringUtils.isNotBlank(checkInfo.getProcessKey())) {
            ProcessEntity process = processDao.find(checkInfo.getProcessKey());
            if (null == process) {
                throw new InvalidParameterException("Check processKey not valid.");
            }
            toUpdate.setProcess(process);
        } else {
            toUpdate.setProcess(null);
        }

        checkDao.merge(toUpdate);

        CheckEntity retrieved = checkDao.find(checkInfo.getKey());

        return retrieved.toDto();

    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        CheckEntity exists = checkDao.find(checkKey);
        StatusInfo status = new StatusInfo();

        if (exists == null) {
            status.setSuccess(false);
            return status;
        }

        List<InstructionEntity> instructionEntities = instructionDao.getByCheck(checkKey);
        for (InstructionEntity instructionEntity : instructionEntities) {
            instructionDao.remove(instructionEntity);
        }

        checkDao.remove(exists);
        status.setSuccess(true);
        return status;
    }

    @Override
    public InstructionInfo getInstruction(@WebParam(name = "instructionId") String instructionId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        InstructionEntity instructionEntity = instructionDao.find(instructionId);
        if (instructionEntity == null) {
            throw new DoesNotExistException(instructionId);
        }
        return instructionEntity.toDto();
    }

    @Override
    public List<InstructionInfo> getInstructionsByIds(@WebParam(name = "instructionIds") List<String> instructionIds,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<InstructionEntity> instructionEntities = instructionDao.findByIds(instructionIds);
        if (instructionEntities == null || instructionEntities.isEmpty()) {
            throw new DoesNotExistException();
        }
        List<InstructionInfo> instructionInfos = new ArrayList<InstructionInfo>();

        for (InstructionEntity instructionEntity : instructionEntities) {
            if (instructionEntity == null) {
                throw new DoesNotExistException();
            }
            instructionInfos.add(instructionEntity.toDto());
        }
        return instructionInfos;
    }

    @Override
    public List<String> getInstructionIdsByType(@WebParam(name = "instructionTypeKey") String instructionTypeKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<InstructionEntity> instructionEntities = instructionDao.getByInstructionTypeId(instructionTypeKey);

        List<String> instructionIds = new ArrayList<String>();
        for (InstructionEntity instructionEntity : instructionEntities) {
            instructionIds.add(instructionEntity.getId());
        }

        return instructionIds;
    }

    @Override
    public List<InstructionInfo> getInstructionsByProcess(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<InstructionInfo> instructionInfos = new ArrayList<InstructionInfo>();
        List<InstructionEntity> instructionEntities = instructionDao.getByProcess(processKey);
        if (null != instructionEntities) {
            for (InstructionEntity instructionEntity : instructionEntities) {
                instructionInfos.add(instructionEntity.toDto());
            }
        }
        return instructionInfos;
    }

    @Override
    public List<InstructionInfo> getInstructionsByCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<InstructionEntity> instructionEntities = instructionDao.getByCheck(checkKey);
        List<InstructionInfo> instructionInfoList = new ArrayList<InstructionInfo>();

        for (InstructionEntity instructionEntity : instructionEntities) {
            instructionInfoList.add(instructionEntity.toDto());
        }

        return instructionInfoList;
    }

    @Override
    public List<InstructionInfo> getInstructionsByProcessAndCheck(@WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "processKey") String processKey, @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        List<InstructionEntity> instructionEntities = instructionDao.getByProcessAndCheck(processKey, checkKey);
        List<InstructionInfo> instructionInfoList = new ArrayList<InstructionInfo>();

        for (InstructionEntity instructionEntity : instructionEntities) {
            instructionInfoList.add(instructionEntity.toDto());
        }

        return instructionInfoList;
    }

    @Override
    public List<String> searchForInstructionIds(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<InstructionInfo> searchForInstructions(@WebParam(name = "criteria") QueryByCriteria criteria,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    public List<ValidationResultInfo> validateInstruction(
            @WebParam(name = "validationTypeKey") String validationTypeKey,
            @WebParam(name = "processKey") String processKey, @WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "instructionInfo") InstructionInfo instructionInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new OperationFailedException("Method not implemented."); // TODO implement
    }

    @Override
    @Transactional(readOnly = false)
    public InstructionInfo createInstruction(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "checkKey") String checkKey,
            @WebParam(name = "instructionInfo") InstructionInfo instructionInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException,
            ReadOnlyException {
        InstructionEntity instruction = new InstructionEntity(instructionInfo);
        instruction.setInstructionState(findState(ProcessServiceConstants.INSTRUCTION_LIFECYCLE_KEY,
                instructionInfo.getStateKey(), contextInfo));
        instruction.setInstructionType(instructionTypeDao.find(instructionInfo.getTypeKey()));

        List<String> appliedAtpTypes = instructionInfo.getAppliedAtpTypeKeys();
        if (appliedAtpTypes.contains(null)) {
            throw new InvalidParameterException("Could not find all appliedAtpTypes: "
                    + instructionInfo.getAppliedAtpTypeKeys());
        }
        instruction.setAppliedAtpTypes(appliedAtpTypes);

        ProcessEntity process = processDao.find(processKey);
        if (null == process) {
            throw new InvalidParameterException("processKey");
        }
        instruction.setProcess(process);

        CheckEntity check = checkDao.find(checkKey);
        if (null == check) {
            throw new InvalidParameterException("checkKey");
        }
        instruction.setCheck(check);

        instructionDao.persist(instruction);
        return instruction.toDto();
    }

    @Override
    @Transactional(readOnly = false)
    public InstructionInfo updateInstruction(@WebParam(name = "instructionId") String instructionId,
            @WebParam(name = "instructionInfo") InstructionInfo instructionInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, ReadOnlyException, VersionMismatchException {
        InstructionEntity exists = instructionDao.find(instructionId);

        if (exists == null) {
            throw new DoesNotExistException(instructionId);
        }

        InstructionEntity toUpdate = new InstructionEntity(instructionInfo);
        toUpdate.setInstructionState(findState(ProcessServiceConstants.INSTRUCTION_LIFECYCLE_KEY,
                instructionInfo.getStateKey(), contextInfo));
        toUpdate.setInstructionType(instructionTypeDao.find(instructionInfo.getTypeKey()));

        List<String> appliedAtpTypes = instructionInfo.getAppliedAtpTypeKeys();
        if (appliedAtpTypes.contains(null)) {
            throw new InvalidParameterException("Could not find all appliedAtpTypes: "
                    + instructionInfo.getAppliedAtpTypeKeys());
        }
        toUpdate.setAppliedAtpTypes(appliedAtpTypes);

        ProcessEntity process = processDao.find(instructionInfo.getProcessKey());
        if (null == process) {
            throw new InvalidParameterException("Instruction processKey");
        }
        toUpdate.setProcess(process);

        CheckEntity check = checkDao.find(instructionInfo.getCheckKey());
        if (null == check) {
            throw new InvalidParameterException("Instruction checkKey");
        }
        toUpdate.setCheck(check);

        instructionDao.merge(toUpdate);

        return instructionDao.find(instructionId).toDto();

    }

    @Override
    @Transactional(readOnly = false)
    public StatusInfo deleteInstruction(@WebParam(name = "instructionId") String instructionId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {

        InstructionEntity exists = instructionDao.find(instructionId);
        StatusInfo status = new StatusInfo();

        if (exists == null) {
            status.setSuccess(false);
            return status;
        }

        instructionDao.remove(exists);

        status.setSuccess(true);
        return status;
    }

    @Override
    public List<InstructionInfo> getInstructionsForEvaluation(@WebParam(name = "processKey") String processKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<InstructionInfo> instructions = getInstructionsByProcess(processKey, contextInfo);
        for (InstructionInfo instruction : instructions) {
            if (!ProcessServiceConstants.PROCESS_ENABLED_STATE_KEY.equals(instruction.getStateKey())) {
                // remove non-active
                instructions.remove(instruction);
            } else if (!isInstructionCurrent(instruction, contextInfo)) {
                // remove non-current
                instructions.remove(instruction);
            }
        }

        // order instructions
        Collections.sort(instructions, new Comparator<InstructionInfo>() {
            @Override
            public int compare(InstructionInfo instruction1, InstructionInfo instruction2) {
                return instruction1.getPosition().compareTo(instruction2.getPosition());
            }
        });

        return instructions;
    }

    private StateEntity findState(String processKey, String stateKey, ContextInfo context)
            throws InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        try {
            StateInfo state = stateService.getState(stateKey, context);
            if (null == state) {
                throw new OperationFailedException("The state does not exist. processKey " + processKey
                        + " and stateKey: " + stateKey);
            }
            return new StateEntity(state);
        } catch (DoesNotExistException e) {
            throw new OperationFailedException("The state does not exist. processKey " + processKey + " and stateKey: "
                    + stateKey);
        }
    }

    private boolean isInstructionCurrent(InstructionInfo instruction, ContextInfo contextInfo)
            throws InvalidParameterException {
        boolean result = false;
        Date effectiveDate = instruction.getEffectiveDate();
        Date expirationDate = instruction.getExpirationDate();
        Date currentDate = Calendar.getInstance().getTime(); // TODO incorporate context

        if (null == effectiveDate) { // TODO move to validation decorator?
            throw new InvalidParameterException("Instruction does not have an effective date.");
        }

        if (!effectiveDate.after(currentDate)) {
            if (null == expirationDate) {
                result = true;
            } else if (!expirationDate.before(currentDate)) {
                result = true;
            }
        }

        return result;
    }

}