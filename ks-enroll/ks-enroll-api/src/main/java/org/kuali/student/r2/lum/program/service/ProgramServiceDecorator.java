package org.kuali.student.r2.lum.program.service;

import java.util.Date;
import java.util.List;


import org.kuali.student.r2.common.datadictionary.dto.DictionaryEntryInfo;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StateInfo;
import org.kuali.student.r2.common.dto.StateProcessInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TypeInfo;
import org.kuali.student.r2.common.dto.TypeTypeRelationInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.IllegalVersionSequencingException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.lum.program.dto.CoreProgramInfo;
import org.kuali.student.r2.lum.program.dto.CredentialProgramInfo;
import org.kuali.student.r2.lum.program.dto.HonorsProgramInfo;
import org.kuali.student.r2.lum.program.dto.MajorDisciplineInfo;
import org.kuali.student.r2.lum.program.dto.MinorDisciplineInfo;
import org.kuali.student.r2.lum.program.dto.ProgramRequirementInfo;
import org.kuali.student.r2.lum.program.dto.ProgramVariationInfo;

public class ProgramServiceDecorator implements ProgramService {
    private ProgramService nextDecorator;

    public ProgramService getNextDecorator() throws OperationFailedException {
        if (null == nextDecorator) {
            throw new OperationFailedException("Misconfigured application: nextDecorator is null");
        }
        return nextDecorator;
    }

    @Override
    public List<String> getDataDictionaryEntryKeys(ContextInfo context) throws OperationFailedException, MissingParameterException, PermissionDeniedException {

        return this.nextDecorator.getDataDictionaryEntryKeys(context);
    }

    @Override
    public DictionaryEntryInfo getDataDictionaryEntry(String entryKey, ContextInfo context) throws OperationFailedException, MissingParameterException, PermissionDeniedException,
            DoesNotExistException {
        return this.nextDecorator.getDataDictionaryEntry(entryKey, context);
    }

    @Override
    public TypeInfo getType(String typeKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getType(typeKey, context);
    }

    @Override
    public List<TypeInfo> getTypesByRefObjectURI(String refObjectURI, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getTypesByRefObjectURI(refObjectURI, context);
    }

    @Override
    public List<TypeInfo> getAllowedTypesForType(String ownerTypeKey, String relatedRefObjectURI, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.getAllowedTypesForType(ownerTypeKey, relatedRefObjectURI, context);
    }

    @Override
    public List<TypeTypeRelationInfo> getTypeRelationsByOwnerType(String ownerTypeKey, String relationTypeKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.getTypeRelationsByOwnerType(ownerTypeKey, relationTypeKey, context);
    }

    @Override
    public List<String> getProcessByObjectType(String refObjectUri, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getProcessByObjectType(refObjectUri, context);
    }

    @Override
    public StateInfo getState(String processKey, String stateKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getState(processKey, stateKey, context);
    }

    @Override
    public List<StateInfo> getStatesByProcess(String processKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getStatesByProcess(processKey, context);

    }

    @Override
    public List<StateInfo> getInitialValidStates(String processKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getInitialValidStates(processKey, context);

    }

    @Override
    public StateInfo getNextHappyState(String processKey, String currentStateKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return this.nextDecorator.getNextHappyState(processKey, currentStateKey, context);
    }

    @Override
    public CredentialProgramInfo getCredentialProgram(String credentialProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getCredentialProgram(credentialProgramId, contextInfo);
    }

    @Override
    public List<CredentialProgramInfo> getCredentialProgramsByIds(List<String> credentialProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getCredentialProgramsByIds(credentialProgramId, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateCredentialProgram(String validationType, CredentialProgramInfo credentialProgramInfo, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.validateCredentialProgram(validationType, credentialProgramInfo, contextInfo);
    }

    @Override
    public CredentialProgramInfo createCredentialProgram(String credentialProgramTypeKey, CredentialProgramInfo credentialProgramInfo, ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createCredentialProgram(credentialProgramTypeKey, credentialProgramInfo, contextInfo);
    }

    @Override
    public CredentialProgramInfo createNewCredentialProgramVersion(String credentialProgramId, String versionComment, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException, DataValidationErrorException {
        return this.nextDecorator.createNewCredentialProgramVersion(credentialProgramId, versionComment, contextInfo);
    }

    @Override
    public StatusInfo setCurrentCredentialProgramVersion(String credentialProgramId, Date currentVersionStart, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, IllegalVersionSequencingException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.setCurrentCredentialProgramVersion(credentialProgramId, currentVersionStart, contextInfo);
    }

    @Override
    public CredentialProgramInfo updateCredentialProgram(String credentialProgramId, CredentialProgramInfo credentialProgramInfo, ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.updateCredentialProgram(credentialProgramId, credentialProgramInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteCredentialProgram(String credentialProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.deleteCredentialProgram(credentialProgramId, contextInfo);
    }

    @Override
    public MajorDisciplineInfo getMajorDiscipline(String majorDisciplineId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getMajorDiscipline(majorDisciplineId, contextInfo);
    }

    @Override
    public List<String> getMajorDisciplineIdsByCredentialProgramType(String programType, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return this.nextDecorator.getMajorDisciplineIdsByCredentialProgramType(programType, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateMajorDiscipline(String validationType, MajorDisciplineInfo majorDisciplineInfo, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.validateMajorDiscipline(validationType, majorDisciplineInfo, contextInfo);
    }

    @Override
    public MajorDisciplineInfo createMajorDiscipline(String majorDisciplineTypeKey, MajorDisciplineInfo majorDisciplineInfo, ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createMajorDiscipline(majorDisciplineTypeKey, majorDisciplineInfo, contextInfo);
    }

    @Override
    public MajorDisciplineInfo updateMajorDiscipline(String majorDisciplineId, MajorDisciplineInfo majorDisciplineInfo, ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.updateMajorDiscipline(majorDisciplineId, majorDisciplineInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteMajorDiscipline(String majorDisciplineId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.deleteMajorDiscipline(majorDisciplineId, contextInfo);
    }

    @Override
    public MajorDisciplineInfo createNewMajorDisciplineVersion(String majorDisciplineId, String versionComment, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException, DataValidationErrorException {
        return this.nextDecorator.createNewMajorDisciplineVersion(majorDisciplineId, versionComment, contextInfo);
    }

    @Override
    public HonorsProgramInfo getHonorsProgram(String honorsProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getHonorsProgram(honorsProgramId, contextInfo);
    }

    @Override
    public List<String> getHonorProgramIdsByCredentialProgramType(String programType, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return this.nextDecorator.getHonorProgramIdsByCredentialProgramType(programType, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateHonorsProgram(String validationType, HonorsProgramInfo honorsProgramInfo, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.validateHonorsProgram(validationType, honorsProgramInfo, contextInfo);
    }

    @Override
    public HonorsProgramInfo createHonorsProgram(String honorsProgramTypeKey, HonorsProgramInfo honorsProgramInfo, ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createHonorsProgram(honorsProgramTypeKey, honorsProgramInfo, contextInfo);
    }

    @Override
    public HonorsProgramInfo updateHonorsProgram(String honorsProgramId, String honorsProgramTypeKey, HonorsProgramInfo honorsProgramInfo, ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException,
            PermissionDeniedException {

        return this.nextDecorator.updateHonorsProgram(honorsProgramId, honorsProgramTypeKey, honorsProgramInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteHonorsProgram(String honorsProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.deleteHonorsProgram(honorsProgramId, contextInfo);
    }

    @Override
    public CoreProgramInfo getCoreProgram(String coreProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        return this.nextDecorator.getCoreProgram(coreProgramId, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateCoreProgram(String validationType, CoreProgramInfo coreProgramInfo, ContextInfo contextInfo) throws InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return this.nextDecorator.validateCoreProgram(validationType, coreProgramInfo, contextInfo);
    }

    @Override
    public CoreProgramInfo createCoreProgram(String coreProgramTypeKey, CoreProgramInfo coreProgramInfo, ContextInfo contextInfo) throws AlreadyExistsException, DataValidationErrorException,
            InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createCoreProgram(coreProgramTypeKey, coreProgramInfo, contextInfo);
    }

    @Override
    public CoreProgramInfo createNewCoreProgramVersion(String coreProgramId, String versionComment, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException, VersionMismatchException, DataValidationErrorException {
        return this.nextDecorator.createNewCoreProgramVersion(coreProgramId, versionComment, contextInfo);
    }

    @Override
    public StatusInfo setCurrentCoreProgramVersion(String coreProgramId, Date currentVersionStart, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, IllegalVersionSequencingException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.setCurrentCoreProgramVersion(coreProgramId, currentVersionStart, contextInfo);
    }

    @Override
    public CoreProgramInfo updateCoreProgram(String coreProgramId, String coreProgramTypeKey, CoreProgramInfo coreProgramInfo, ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.updateCoreProgram(coreProgramId, coreProgramTypeKey, coreProgramInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteCoreProgram(String coreProgramId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException {
        return this.nextDecorator.deleteCoreProgram(coreProgramId, contextInfo);
    }

    @Override
    public ProgramRequirementInfo getProgramRequirement(String programRequirementId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getProgramRequirement(programRequirementId, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateProgramRequirement(String validationType, ProgramRequirementInfo programRequirementInfo, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.validateProgramRequirement(validationType, programRequirementInfo, contextInfo);
    }

    @Override
    public ProgramRequirementInfo createProgramRequirement(String programRequirementTypeKey, ProgramRequirementInfo programRequirementInfo, ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createProgramRequirement(programRequirementTypeKey, programRequirementInfo, contextInfo);
    }

    @Override
    public ProgramRequirementInfo updateProgramRequirement(String programRequirementId, String programRequirementTypeKey, ProgramRequirementInfo programRequirementInfo, ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException,
            PermissionDeniedException {
        return this.nextDecorator.updateProgramRequirement(programRequirementId, programRequirementTypeKey, programRequirementInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteProgramRequirement(String programRequirementId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.deleteProgramRequirement(programRequirementId, contextInfo);
    }

    @Override
    public StatusInfo setCurrentMajorDisciplineVersion(String majorDisciplineId, Date currentVersionStart, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, IllegalVersionSequencingException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.setCurrentMajorDisciplineVersion(majorDisciplineId, currentVersionStart, contextInfo);
    }

    @Override
    public MinorDisciplineInfo getMinorDiscipline(String minorDisciplineId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getMinorDiscipline(minorDisciplineId, contextInfo);
    }

    @Override
    public List<String> getMinorsByCredentialProgramType(String programType, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException {
        return this.nextDecorator.getMinorsByCredentialProgramType(programType, contextInfo);
    }

    @Override
    public List<ValidationResultInfo> validateMinorDiscipline(String validationType, MinorDisciplineInfo minorDisciplineInfo, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException {
        return this.nextDecorator.validateMinorDiscipline(validationType, minorDisciplineInfo, contextInfo);
    }

    @Override
    public MinorDisciplineInfo createMinorDiscipline(String minorDisciplineTypeKey, MinorDisciplineInfo minorDisciplineInfo, ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.createMinorDiscipline(minorDisciplineTypeKey, minorDisciplineInfo, contextInfo);
    }

    @Override
    public MinorDisciplineInfo updateMinorDiscipline(String minorDisciplineId, String minorDisciplineTypeKey, MinorDisciplineInfo minorDisciplineInfo, ContextInfo contextInfo)
            throws DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException, VersionMismatchException, OperationFailedException,
            PermissionDeniedException {
        return this.nextDecorator.updateMinorDiscipline(minorDisciplineId, minorDisciplineTypeKey, minorDisciplineInfo, contextInfo);
    }

    @Override
    public StatusInfo deleteMinorDiscipline(String minorDisciplineId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.deleteMinorDiscipline(minorDisciplineId, contextInfo);
    }

    @Override
    public StateProcessInfo getProcessByKey(String processKey, ContextInfo context) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException {
        return this.nextDecorator.getProcessByKey(processKey, context);
    }

    @Override
    public List<MajorDisciplineInfo> getMajorDisciplinesByIds(List<String> majorDisciplineIds, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        // TODO sambit - THIS METHOD NEEDS JAVADOCS
        return null;
    }

    @Override
    public List<HonorsProgramInfo> getHonorsProgramsByIds(List<String> honorsProgramIds, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getHonorsProgramsByIds(honorsProgramIds, contextInfo);
    }

    @Override
    public List<CoreProgramInfo> getCoreProgramsByIds(List<String> coreProgramIds, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getCoreProgramsByIds(coreProgramIds, contextInfo);
    }

    @Override
    public List<ProgramRequirementInfo> getProgramRequirementsByIds(List<String> programRequirementIds, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        return this.nextDecorator.getProgramRequirementsByIds(programRequirementIds, contextInfo);
    }

}