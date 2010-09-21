package org.kuali.student.lum.program.client.rpc;

import java.util.List;

import org.kuali.student.common.ui.client.service.BaseDataOrchestrationRpcService;
import org.kuali.student.core.dto.StatusInfo;
import org.kuali.student.lum.program.dto.MajorDisciplineInfo;
import org.kuali.student.lum.program.dto.ProgramRequirementInfo;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpcservices/programRpcService")
public interface ProgramRpcService extends BaseDataOrchestrationRpcService {
    public List<ProgramRequirementInfo> getProgramRequirements(List<String> programRequirementIds) throws Exception;
    public ProgramRequirementInfo addProgramRequirement(ProgramRequirementInfo programRequirement, String programId) throws Exception;
    public ProgramRequirementInfo createProgramRequirement(ProgramRequirementInfo programRequirementInfo) throws Exception;
    public MajorDisciplineInfo updateMajorDiscipline(MajorDisciplineInfo majorDisciplineInfo) throws Exception;
    public StatusInfo deleteProgramRequirement(String programRequirementId) throws Exception;
    public ProgramRequirementInfo updateProgramRequirement(ProgramRequirementInfo programRequirementInfo) throws Exception;
}
