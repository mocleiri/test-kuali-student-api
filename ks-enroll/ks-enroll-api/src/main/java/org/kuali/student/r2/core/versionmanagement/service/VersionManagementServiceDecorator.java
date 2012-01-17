/*
 * Copyright 2011 The Kuali Foundation Licensed under the
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

package org.kuali.student.r2.common.versionmanagement.service;

import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.versionmanagement.dto.VersionDisplayInfo;

import java.util.Date;
import java.util.List;

/**
 * @Version 2.0
 * @Author Sri komandur@uw.edu
 */
public class VersionManagementServiceDecorator implements VersionManagementService {

    private VersionManagementService nextDecorator;

    public VersionManagementService getNextDecorator() {
        return nextDecorator;
    }

    public void setNextDecorator(VersionManagementService nextDecorator) {
        this.nextDecorator = nextDecorator;
    }

    @Override
    public List<VersionDisplayInfo> getVersions(String refObjectTypeURI, String refObjectId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getVersions(refObjectTypeURI, refObjectId, contextInfo);
    }

    @Override
    public VersionDisplayInfo getFirstVersion(String refObjectTypeURI, String refObjectId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getFirstVersion(refObjectTypeURI, refObjectId, contextInfo);
    }

    @Override
    public VersionDisplayInfo getLatestVersion(String refObjectTypeURI, String refObjectId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getLatestVersion(refObjectTypeURI, refObjectId, contextInfo);
    }

    @Override
    public VersionDisplayInfo getCurrentVersion(String refObjectTypeURI, String refObjectId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getCurrentVersion(refObjectTypeURI, refObjectId, contextInfo);
    }

    @Override
    public VersionDisplayInfo getVersionBySequenceNumber(String refObjectTypeURI, String refObjectId, Long sequence, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getVersionBySequenceNumber(refObjectTypeURI, refObjectId, sequence, contextInfo);
    }

    @Override
    public VersionDisplayInfo getCurrentVersionOnDate(String refObjectTypeURI, String refObjectId, Date date, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getCurrentVersionOnDate(refObjectTypeURI, refObjectId, date, contextInfo);
    }

    @Override
    public List<VersionDisplayInfo> getVersionsInDateRange(String refObjectTypeURI, String refObjectId, Date from, Date to, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return nextDecorator.getVersionsInDateRange(refObjectTypeURI, refObjectId, from, to, contextInfo);
    }
}
