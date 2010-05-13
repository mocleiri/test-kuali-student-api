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

package org.kuali.student.lum.lu.assembly;

import java.util.List;

import org.kuali.student.common.ui.client.application.ViewContext.IdType;
import org.kuali.student.core.assembly.PassThroughAssemblerFilter;
import org.kuali.student.core.assembly.data.AssemblyException;
import org.kuali.student.core.assembly.data.Data;
import org.kuali.student.core.assembly.data.Metadata;
import org.kuali.student.core.assembly.data.QueryPath;
import org.kuali.student.core.assembly.data.SaveResult;
import org.kuali.student.core.assembly.util.AssemblerUtils;
import org.kuali.student.lum.lu.assembly.data.client.refactorme.orch.CreditCourseProposalHelper;

public class CreditCourseProposalAssemblerFilter extends PassThroughAssemblerFilter<Data, Void> {


    @Override
    public void doSaveFilter(FilterParamWrapper<Data> request, FilterParamWrapper<SaveResult<Data>> response, SaveFilterChain<Data, Void> chain) throws AssemblyException {
        Data data = request.getValue();
        CreditCourseProposalHelper help = CreditCourseProposalHelper.wrap(data);
        String idType = IdType.KS_KEW_OBJECT_ID.toString();
        String proposalId = help.getProposal().getId();
        String state = help.getState();
        Metadata typeMetadata = chain.getManager().getTarget().getMetadata(idType, proposalId, CreditCourseProposalAssembler.CREDIT_COURSE_PROPOSAL_DATA_TYPE, state);
        if(typeMetadata != null && !typeMetadata.isCanEdit()) {
            throw new AssemblyException("Document is read only");
        }
        
        List<QueryPath> dirtyPaths = AssemblerUtils.findDirtyElements(data);
        
        for(QueryPath path : dirtyPaths) {
            
            Metadata fieldMetadata = AssemblerUtils.get(typeMetadata, path);
            
            if(null != fieldMetadata && !fieldMetadata.isCanEdit()) {
                throw new AssemblyException("User does not have edit permission for field");
            }
            
        }
        chain.doSaveFilter(request, response);
        
    }

}
