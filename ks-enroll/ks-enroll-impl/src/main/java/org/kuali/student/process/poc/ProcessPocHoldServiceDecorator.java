/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.student.process.poc;

import java.util.Date;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.util.constants.HoldServiceConstants;
import org.kuali.student.r2.core.hold.dto.HoldInfo;
import org.kuali.student.r2.core.hold.dto.IssueInfo;
import org.kuali.student.r2.core.hold.service.HoldService;
import org.kuali.student.r2.core.hold.service.HoldServiceDecorator;

/**
 *
 * @author nwright
 */
public class ProcessPocHoldServiceDecorator extends HoldServiceDecorator {

    public ProcessPocHoldServiceDecorator(HoldService nextDecorator) {
        super();
        this.setNextDecorator(nextDecorator);
        _initializeData();
    }

    private void _initializeData() {
        ContextInfo context = new ContextInfo();
        context.setPrincipalId("POC-Initializer");

        IssueInfo unpaidTuitionIssue = _createIssue(HoldServiceConstants.ISSUE_KEY_UNPAID_TUITION_PRIOR_TERM,
                "Unpaid tuition from last term", HoldServiceConstants.FINANCIAL_ISSUE_TYPE_KEY, context);


        IssueInfo overdueBookIssue = _createIssue(HoldServiceConstants.ISSUE_KEY_BOOK_OVERDUE,
                "Overdue Library Book", HoldServiceConstants.OVERDUE_LIBRARY_MATERIALS_ISSUE_TYPE_KEY, context);

        this._createHold(ProcessPocConstants.PERSON_ID_CLIFFORD_RIDDLE_2397, unpaidTuitionIssue, context);
        this._createHold(ProcessPocConstants.PERSON_ID_NINA_WELCH_2166, unpaidTuitionIssue, context);
        this._createHold(ProcessPocConstants.PERSON_ID_BETTY_MARTIN_2005, overdueBookIssue, context);
        this._createHold(ProcessPocConstants.PERSON_ID_NINA_WELCH_2166, overdueBookIssue, context);
    }

    private IssueInfo _createIssue(String key, String name, String type, ContextInfo context) {
        IssueInfo issue = new IssueInfo();
        issue.setKey(key);
        issue.setName(name);
        issue.setTypeKey(type);
        issue.setStateKey(HoldServiceConstants.ISSUE_ACTIVE_STATE_KEY);
        try {
            issue = this.createIssue(issue, context);
        } catch (Exception ex) {
            throw new RuntimeException("error creating hold", ex);
        }
        return issue;
    }

    private HoldInfo _createHold(String personId, IssueInfo issue, ContextInfo context) {
        HoldInfo hold = new HoldInfo();
        hold.setTypeKey(HoldServiceConstants.STUDENT_HOLD_TYPE_KEY);
        hold.setStateKey(HoldServiceConstants.HOLD_ACTIVE_STATE_KEY);
        hold.setIssueKey(issue.getKey());
        hold.setName(issue.getName());
        hold.setIsOverridable(true);
        hold.setIsWarning(false);
        hold.setEffectiveDate(new Date());
        hold.setPersonId(personId);
        try {
            hold = this.createHold(hold, context);
        } catch (Exception ex) {
            throw new RuntimeException("error creating hold", ex);
        }
        return hold;
    }
}