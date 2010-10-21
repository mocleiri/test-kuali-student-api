package org.kuali.student.core.atp.document;

import java.util.Map;

import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.student.core.atp.bo.Atp;
import org.kuali.student.core.atp.bo.AtpRichText;
import org.kuali.student.core.atp.bo.DateRange;
import org.kuali.student.core.atp.bo.Milestone;

public class AtpMaintainbleImpl extends KualiMaintainableImpl {
	private static final long serialVersionUID = 9026116304410006845L;

	/**
	 * Clear out keys of child collections
	 */
	@Override
	public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parms) {
		super.processAfterCopy(document, parms);

		Atp atp = (Atp) document.getNewMaintainableObject().getBusinessObject();

		for (DateRange dateRange : atp.getDateRanges()) {
			dateRange.setId(null);
			
			AtpRichText description = dateRange.getDescription();
			if (description != null) {
				description.setId(null);
				dateRange.setDescriptionId(null);
			}
		}

		for (Milestone milestone : atp.getMilestones()) {
			milestone.setId(null);
			
			AtpRichText description = milestone.getDescription();
			if (description != null) {
				description.setId(null);
				milestone.setDescriptionId(null);
			}
		}
		
		AtpRichText description = atp.getDescription();
		if (description != null) {
			description.setId(null);
			atp.setDescriptionId(null);
		}
	}

}
