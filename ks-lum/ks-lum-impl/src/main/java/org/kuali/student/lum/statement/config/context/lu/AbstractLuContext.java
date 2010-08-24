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

package org.kuali.student.lum.statement.config.context.lu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.student.core.statement.entity.ReqComponent;
import org.kuali.student.core.statement.naturallanguage.AbstractContext;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.OperationFailedException;
import org.kuali.student.lum.lu.dto.CluInfo;
import org.kuali.student.lum.lu.dto.CluSetInfo;
import org.kuali.student.lum.lu.dto.CluSetTreeViewInfo;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.statement.typekey.ReqComponentFieldTypes;

public abstract class AbstractLuContext<T> extends AbstractContext<T> {
    /**
     * Learning unit service.
     */
	private LuService luService;

	/**
	 * Constructor.
	 */
	public AbstractLuContext() {
		this.luService = null;
	}

	/**
	 * Sets the LU service.
	 *
	 * @param luService LU service
	 */
    public void setLuService(LuService luService) {
		this.luService = luService;
	}

	/**
     * Gets a CLU.
     *
     * @param cluId CLU id
     * @return CLU
     * @throws OperationFailedException If retrieving CLU fails
     */
    public CluInfo getCluInfo(String cluId) throws OperationFailedException {
		try {
			CluInfo clu = this.luService.getClu(cluId);
			return clu;
		} catch(Exception e) {
			throw new OperationFailedException(e.getMessage(), e);
		}
    }

    /**
     * Gets a CLU set.
     *
     * @param cluSetId CLU set id
     * @return CLU set
     * @throws OperationFailedException If retrieving CLU set fails
     */
    public CluSetInfo getCluSetInfo(String cluSetId) throws OperationFailedException {
		try {
	    	CluSetInfo cluSet = this.luService.getCluSetInfo(cluSetId);
	    	return cluSet;
		} catch(Exception e) {
			throw new OperationFailedException(e.getMessage(), e);
		}
    }

    /**
     * Gets the CLU set.
     *
     * @param cluSetId CLU set id
     * @return CLU set
     * @throws OperationFailedException If building a custom CLU set fails
     */
    public NLCluSet getCluSet(String cluSetId) throws OperationFailedException {
    	CluSetInfo cluSet = getCluSetInfo(cluSetId);
		try {
	    	List<CluInfo> list = new ArrayList<CluInfo>();
	    	CluSetTreeViewInfo tree = luService.getCluSetTreeView(cluSetId);
	    	findClusInCluSet(tree, list);
	    	return new NLCluSet(cluSet.getId(), list);
		} catch(Exception e) {
			throw new OperationFailedException(e.getMessage(), e);
		}
    }

    private static void findClusInCluSet(CluSetTreeViewInfo tree, List<CluInfo> cluList) {
		if (tree.getCluSets() != null) {
			for (CluSetTreeViewInfo cluSet : tree.getCluSets()) {
				findClusInCluSet(cluSet, cluList);
			}
		} else {
			for (CluInfo clu : tree.getClus()) {
				if (!containsClu(cluList, clu)) {
					cluList.add(clu);
				}
			}
		}
	}

	private static boolean containsClu(List<CluInfo> cluList, CluInfo clu) {
		for (CluInfo clu2 : cluList) {
			if (clu2.getId().equals(clu.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
     * Gets a new CLU set from comma separated list of CLU ids.
     *
     * @param cluIds Comma separated list of CLU ids
     * @return A new CLU set
     * @throws OperationFailedException If building a custom CLU set fails
     */
    public NLCluSet getClusAsCluSet(String cluIds) throws OperationFailedException {
    	String[] cluIdArray = cluIds.split("\\s*,\\s*");
    	List<CluInfo> list = new ArrayList<CluInfo>();
    	for(String cluId : cluIdArray) {
    		CluInfo clu = getCluInfo(cluId);
    		list.add(clu);
    	}
    	return new NLCluSet(null, list);
    }

    /**
     * Gets a custom CLU set from a requirement component.
     *
     * @param reqComponent Requirement component
     * @return custom CLU set
     * @throws OperationFailedException If building a custom CLU set fails
     */
    public NLCluSet getCluSet(ReqComponent reqComponent) throws OperationFailedException {
        Map<String, String> map = getReqComponentFieldMap(reqComponent);
    	NLCluSet cluSet = null;
    	if(map.containsKey(ReqComponentFieldTypes.CLU_KEY.getId())) {
        	String cluIds = map.get(ReqComponentFieldTypes.CLU_KEY.getId());
        	cluSet = getClusAsCluSet(cluIds);
        } else if(map.containsKey(ReqComponentFieldTypes.CLUSET_KEY.getId())) {
        	String cluSetId = map.get(ReqComponentFieldTypes.CLUSET_KEY.getId());
            cluSet = getCluSet(cluSetId);
        }
    	return cluSet;
    }

    /**
     * Creates the context map (template data) for the requirement component.
     * Also, adds the field token map to the context map.
     *
     * @param reqComponent Requirement component
     * @throws DoesNotExistException If CLU, CluSet or relation does not exist
     */
    public Map<String, Object> createContextMap(ReqComponent reqComponent) throws OperationFailedException {
        Map<String, Object> contextMap = super.createContextMap(reqComponent);
        contextMap.put(CommonTemplateTokens.NL_HELPER_TOKEN, NLHelper.class);
        contextMap.put(CommonTemplateTokens.EXPECTED_VALUE_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.REQUIRED_COUNT_KEY.getId()));
        contextMap.put(CommonTemplateTokens.OPERATOR_TOKEN, getReqComponentFieldValue(reqComponent, ReqComponentFieldTypes.OPERATOR_KEY.getId()));
        return contextMap;
    }
}
