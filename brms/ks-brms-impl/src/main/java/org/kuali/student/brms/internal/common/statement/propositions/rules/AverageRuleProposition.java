/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.brms.internal.common.statement.propositions.rules;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.kuali.student.brms.factfinder.dto.FactStructureInfo;
import org.kuali.student.brms.internal.common.entity.ComparisonOperator;
import org.kuali.student.brms.internal.common.statement.MessageContextConstants;
import org.kuali.student.brms.internal.common.statement.exceptions.PropositionException;
import org.kuali.student.brms.internal.common.statement.propositions.AverageProposition;
import org.kuali.student.brms.internal.common.statement.propositions.Fact;
import org.kuali.student.brms.internal.common.statement.propositions.PropositionType;
import org.kuali.student.brms.internal.common.statement.report.PropositionReport;
import org.kuali.student.brms.internal.common.utils.FactUtil;
import org.kuali.student.brms.rulemanagement.dto.RulePropositionInfo;
import org.kuali.student.brms.rulemanagement.dto.YieldValueFunctionInfo;

public class AverageRuleProposition<E extends Number> extends AbstractRuleProposition<Number> {

	public AverageRuleProposition(String id, String propositionName, 
			RulePropositionInfo ruleProposition, Map<String, ?> factMap) {
    	super(id, propositionName, PropositionType.AVERAGE, ruleProposition);

		YieldValueFunctionInfo yvf = ruleProposition.getLeftHandSide().getYieldValueFunction();
		List<FactStructureInfo> factStructureList = yvf.getFactStructureList();
		FactStructureInfo factStructure = factStructureList.get(0);

		if (factStructure == null) {
			throw new PropositionException("Fact structure cannot be null");
		}

		Fact fact = getFacts(factMap, factStructure, MessageContextConstants.PROPOSITION_AVERAGE_COLUMN_KEY);

		super.factDTO = fact.getFactDTO();
		super.factColumn = fact.getFactColumn();
		
		ComparisonOperator comparisonOperator = ComparisonOperator.valueOf(ruleProposition.getComparisonOperatorTypeKey()); 
		BigDecimal expectedValue = new BigDecimal(ruleProposition.getRightHandSide().getExpectedValue());

		if(logger.isDebugEnabled()) {
			logger.debug("\n---------- YVFAverageProposition ----------"
					+ "\nFact static="+factStructure.isStaticFact()
					+ "\nFact key="+FactUtil.createFactKey(factStructure)
					+ "\nYield value function type="+yvf.getYieldValueFunctionType()
					+ "\nComparison operator="+comparisonOperator
					+ "\nExpected value="+expectedValue
					+ "\nFact list="+factDTO
					+ "\n--------------------------------------------------");
		}

		super.proposition = new AverageProposition<Number>(id, propositionName, 
				comparisonOperator, expectedValue, factDTO, factColumn); 
	}

    @Override
    public PropositionReport buildReport() {
        return buildDefaultReport(MessageContextConstants.PROPOSITION_AVERAGE_SUCCESS_MESSAGE, MessageContextConstants.PROPOSITION_AVERAGE_FAILURE_MESSAGE);
    }
}
