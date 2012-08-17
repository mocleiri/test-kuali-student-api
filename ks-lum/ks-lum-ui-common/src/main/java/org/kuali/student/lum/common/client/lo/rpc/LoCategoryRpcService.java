/*
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

package org.kuali.student.lum.common.client.lo.rpc;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import org.kuali.student.common.ui.client.service.BaseDataOrchestrationRpcService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.lum.lo.dto.LoCategoryInfo;
import org.kuali.student.r1.lum.lo.dto.LoCategoryTypeInfo;
import org.kuali.student.r2.lum.lo.dto.LoInfo;

import java.util.List;


/**
 * 
 * @author Kuali Student Team
 */
@RemoteServiceRelativePath("rpcservices/LoCategoryRpcService")
public interface LoCategoryRpcService extends BaseDataOrchestrationRpcService {

    public List<LoCategoryInfo> getLoCategories(String loRepositoryKey);
    public StatusInfo deleteLoCategory(String loCategoryId);

    public List<LoCategoryTypeInfo> getLoCategoryTypes();

    public LoCategoryTypeInfo getLoCategoryType(String loCategoryTypeKey);

}
