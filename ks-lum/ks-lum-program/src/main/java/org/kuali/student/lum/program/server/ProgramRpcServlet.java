package org.kuali.student.lum.program.server;

import java.util.ArrayList;
import java.util.List;

import org.kuali.student.common.ui.server.gwt.DataGwtServlet;
import org.kuali.student.core.dto.StatusInfo;
import org.kuali.student.core.statement.dto.ReqComponentInfo;
import org.kuali.student.core.statement.dto.StatementTreeViewInfo;
import org.kuali.student.core.statement.service.StatementService;
import org.kuali.student.lum.program.client.requirements.ProgramRequirementsDataModel;
import org.kuali.student.lum.program.client.requirements.ProgramRequirementsSummaryView;
import org.kuali.student.lum.program.client.rpc.ProgramRpcService;
import org.kuali.student.lum.program.dto.MajorDisciplineInfo;
import org.kuali.student.lum.program.dto.ProgramRequirementInfo;
import org.kuali.student.lum.program.service.ProgramService;

public class ProgramRpcServlet extends DataGwtServlet implements ProgramRpcService {

    private static final long serialVersionUID = 1L;
    
    private ProgramService programService;
    private StatementService statementService;
	
    public List<ProgramRequirementInfo> getProgramRequirements(List<String> programRequirementIds) throws Exception {

        List<ProgramRequirementInfo> programReqInfos = new ArrayList<ProgramRequirementInfo>();

        for (String programReqId : programRequirementIds) {
            ProgramRequirementInfo rule = programService.getProgramRequirement(programReqId, null, null);
            setProgReqNL(rule);
            programReqInfos.add(rule);
        }

        return programReqInfos;
    }

    //TODO remove if we are setting program requirement by triggering events that are handled by other parts of program ui
    public ProgramRequirementInfo addProgramRequirement(ProgramRequirementInfo programRequirement, String programId) throws Exception {
        ProgramRequirementInfo progReq = this.createProgramRequirement(programRequirement);
        MajorDisciplineInfo major = ((ProgramService) getDataService()).getMajorDiscipline(programId);
        major.getProgramRequirements().add(programRequirement.getId());
        updateMajorDiscipline(major);
        return progReq;
    }

    public ProgramRequirementInfo createProgramRequirement(ProgramRequirementInfo programRequirementInfo) throws Exception {

        if (programRequirementInfo.getId().indexOf(ProgramRequirementsSummaryView.NEW_PROG_REQ_ID) >= 0) {
            programRequirementInfo.setId(null);
        }

        programRequirementInfo.setState("Active");
        ProgramRequirementsDataModel.stripStatementIds(programRequirementInfo.getStatement());
        ProgramRequirementInfo rule = programService.createProgramRequirement(programRequirementInfo);
        setProgReqNL(rule);
        
        return rule;
    }

    public StatusInfo deleteProgramRequirement(String programRequirementId) throws Exception {
        return programService.deleteProgramRequirement(programRequirementId);
    }

    public ProgramRequirementInfo updateProgramRequirement(ProgramRequirementInfo programRequirementInfo) throws Exception {
        programRequirementInfo.setState("Active");        
        ProgramRequirementsDataModel.stripStatementIds(programRequirementInfo.getStatement());
        ProgramRequirementInfo rule = programService.updateProgramRequirement(programRequirementInfo);
        setProgReqNL(rule);
        return rule;
    }    

    //TODO remove?
    public MajorDisciplineInfo updateMajorDiscipline(MajorDisciplineInfo majorDisciplineInfo) throws Exception {
        return programService.updateMajorDiscipline(majorDisciplineInfo);
    }

    private void setProgReqNL(ProgramRequirementInfo programRequirementInfo) throws Exception {
        setReqCompNL(programRequirementInfo.getStatement());
    }

    private void setReqCompNL(StatementTreeViewInfo tree) throws Exception {
        List<StatementTreeViewInfo> statements = tree.getStatements();
        List<ReqComponentInfo> reqComponentInfos = tree.getReqComponents();

         if ((statements != null) && (statements.size() > 0)) {
            // retrieve all statements
            for (StatementTreeViewInfo statement : statements) {
                setReqCompNL(statement); // inside set the children of this statementTreeViewInfo
            }
        } else if ((reqComponentInfos != null) && (reqComponentInfos.size() > 0)) {
            // retrieve all req. component LEAFS
            for (ReqComponentInfo reqComponent : reqComponentInfos) {
                reqComponent.setNaturalLanguageTranslation(statementService.translateReqComponentToNL(reqComponent, "KUALI.RULE", "en"));
            }
        }
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setStatementService(StatementService statementService) {
        this.statementService = statementService;
    }
}
