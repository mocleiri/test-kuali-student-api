/**
 * Copyright 2012 The Kuali Foundation Licensed under the
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
 *
 * Created by Mezba Mahtab on 8/21/12
 */
package org.kuali.student.r2.core.scheduling.service.impl;

/**
 * This class represents a mock implementation of Scheduling Service.
 *
 * @author Mezba Mahtab
 */

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.common.mock.MockService;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.MetaInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.TimeOfDayInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.*;
import org.kuali.student.r2.core.scheduling.dto.ScheduleBatchInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleRequestInfo;
import org.kuali.student.r2.core.scheduling.dto.ScheduleTransactionInfo;
import org.kuali.student.r2.core.scheduling.dto.TimeSlotInfo;
import org.kuali.student.r2.core.scheduling.service.SchedulingService;
import org.kuali.student.r2.core.scheduling.util.SchedulingServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SchedulingServiceMockImpl implements SchedulingService, MockService {

    ////////////////////////////////
    // DATA VARIABLES
    ////////////////////////////////

    // The LinkedHashMap is just so the values come back in a predictable order
    private Map<String, ScheduleInfo> scheduleMap = new LinkedHashMap<String, ScheduleInfo>();

    // The LinkedHashMap is just so the values come back in a predictable order
    private Map<String, ScheduleBatchInfo> scheduleBatchMap = new LinkedHashMap<String, ScheduleBatchInfo>();

    // The LinkedHashMap is just so the values come back in a predictable order
    private Map<String, ScheduleRequestInfo> scheduleRequestMap = new LinkedHashMap<String, ScheduleRequestInfo>();

    // The LinkedHashMap is just so the values come back in a predictable order
    private Map<String, TimeSlotInfo> timeSlotMap = new LinkedHashMap<String, TimeSlotInfo>();

    // The LinkedHashMap is just so the values come back in a predictable order
    private Map<String, ScheduleTransactionInfo> scheduleTransactionMap = new LinkedHashMap<String, ScheduleTransactionInfo>();

    ////////////////////////////////
    // IMPLEMENTING METHODS
    ////////////////////////////////
    
    @Override
    public ScheduleInfo getSchedule(String scheduleId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (!this.scheduleMap.containsKey(scheduleId)) {
            throw new DoesNotExistException(scheduleId);
        }
        return new ScheduleInfo(this.scheduleMap.get (scheduleId));
    }

    @Override
	public void clear() {
		
    	this.scheduleBatchMap.clear();
    	this.scheduleMap.clear();
    	this.scheduleRequestMap.clear();
    	this.scheduleTransactionMap.clear();
    	this.timeSlotMap.clear();
	}

	@Override
    public List<ScheduleInfo> getSchedulesByIds(List<String> scheduleIds, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<ScheduleInfo> list = new ArrayList<ScheduleInfo> ();
        for (String id: scheduleIds) {
            list.add (this.getSchedule(id, contextInfo));
        }
        return list;
    }

    @Override
    public List<String> getScheduleIdsByType(String scheduleTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        for (ScheduleInfo info: scheduleMap.values ()) {
            if (scheduleTypeKey.equals(info.getTypeKey())) {
                list.add (info.getId ());
            }
        }
        return list;
    }

    @Override
    public List<String> searchForScheduleIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleIds has not been implemented");
    }

    @Override
    public List<ScheduleInfo> searchForSchedules(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForSchedules has not been implemented");
    }

    @Override
    public List<ValidationResultInfo> validateSchedule(String validationTypeKey, String scheduleTypeKey, ScheduleInfo scheduleInfo, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        // validate
        return new ArrayList<ValidationResultInfo> ();
    }

    @Override
    public ScheduleInfo createSchedule(String scheduleTypeKey, ScheduleInfo scheduleInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
    {
        // create
        if (!scheduleTypeKey.equals (scheduleInfo.getTypeKey())) {
            throw new InvalidParameterException ("The type parameter does not match the type on the info object");
        }
        ScheduleInfo copy = new ScheduleInfo(scheduleInfo);
        if (copy.getId() == null) {
            copy.setId(scheduleMap.size() + "");
        }
        copy.setMeta(newMeta(contextInfo));
        scheduleMap.put(copy.getId(), copy);
        return new ScheduleInfo(copy);
    }

    @Override
    public ScheduleInfo updateSchedule(String scheduleId, ScheduleInfo scheduleInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
            ,VersionMismatchException
    {
        // update
        if (!scheduleId.equals (scheduleInfo.getId())) {
            throw new InvalidParameterException ("The id parameter does not match the id on the info object");
        }
        ScheduleInfo copy = new ScheduleInfo(scheduleInfo);
        ScheduleInfo old = this.getSchedule(scheduleInfo.getId(), contextInfo);
        if (!old.getMeta().getVersionInd().equals(copy.getMeta().getVersionInd())) {
            throw new VersionMismatchException(old.getMeta().getVersionInd());
        }
        copy.setMeta(updateMeta(copy.getMeta(), contextInfo));
        this.scheduleMap .put(scheduleInfo.getId(), copy);
        return new ScheduleInfo(copy);
    }

    @Override
    public StatusInfo deleteSchedule(String scheduleId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (this.scheduleMap.remove(scheduleId) == null) {
            throw new DoesNotExistException(scheduleId);
        }
        return newStatus();
    }

    @Override
    public ScheduleBatchInfo getScheduleBatch(String scheduleBatchId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (!this.scheduleBatchMap.containsKey(scheduleBatchId)) {
            throw new DoesNotExistException(scheduleBatchId);
        }
        return new ScheduleBatchInfo(this.scheduleBatchMap.get (scheduleBatchId));
    }

    @Override
    public List<ScheduleBatchInfo> getScheduleBatchesByIds(List<String> scheduleBatchIds, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<ScheduleBatchInfo> list = new ArrayList<ScheduleBatchInfo> ();
        for (String id: scheduleBatchIds) {
            list.add (this.getScheduleBatch(id, contextInfo));
        }
        return list;
    }

    @Override
    public List<String> getScheduleBatchIdsByType(String scheduleBatchTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        for (ScheduleBatchInfo info: scheduleBatchMap.values ()) {
            if (scheduleBatchTypeKey.equals(info.getTypeKey())) {
                list.add (info.getId ());
            }
        }
        return list;
    }

    @Override
    public List<String> searchForScheduleBatchIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleBatchIds has not been implemented");
    }

    @Override
    public List<ScheduleBatchInfo> searchForScheduleBatches(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleBatches has not been implemented");
    }

    @Override
    public List<ValidationResultInfo> validateScheduleBatch(String validationTypeKey, String scheduleBatchTypeKey, ScheduleBatchInfo scheduleBatchInfo, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        // validate
        return new ArrayList<ValidationResultInfo> ();
    }

    @Override
    public ScheduleBatchInfo createScheduleBatch(String scheduleBatchTypeKey, ScheduleBatchInfo scheduleBatchInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
    {
        // create
        if (!scheduleBatchTypeKey.equals (scheduleBatchInfo.getTypeKey())) {
            throw new InvalidParameterException ("The type parameter does not match the type on the info object");
        }
        ScheduleBatchInfo copy = new ScheduleBatchInfo(scheduleBatchInfo);
        if (copy.getId() == null) {
            copy.setId(scheduleBatchMap.size() + "");
        }
        copy.setMeta(newMeta(contextInfo));
        scheduleBatchMap.put(copy.getId(), copy);
        return new ScheduleBatchInfo(copy);
    }

    @Override
    public ScheduleBatchInfo updateScheduleBatch(String scheduleBatchId, ScheduleBatchInfo scheduleBatchInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
            ,VersionMismatchException
    {
        // update
        if (!scheduleBatchId.equals (scheduleBatchInfo.getId())) {
            throw new InvalidParameterException ("The id parameter does not match the id on the info object");
        }
        ScheduleBatchInfo copy = new ScheduleBatchInfo(scheduleBatchInfo);
        ScheduleBatchInfo old = this.getScheduleBatch(scheduleBatchInfo.getId(), contextInfo);
        if (!old.getMeta().getVersionInd().equals(copy.getMeta().getVersionInd())) {
            throw new VersionMismatchException(old.getMeta().getVersionInd());
        }
        copy.setMeta(updateMeta(copy.getMeta(), contextInfo));
        this.scheduleBatchMap .put(scheduleBatchInfo.getId(), copy);
        return new ScheduleBatchInfo(copy);
    }

    @Override
    public StatusInfo deleteScheduleBatch(String scheduleBatchId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (this.scheduleBatchMap.remove(scheduleBatchId) == null) {
            throw new DoesNotExistException(scheduleBatchId);
        }
        return newStatus();
    }

    @Override
    public ScheduleRequestInfo getScheduleRequest(String scheduleRequestId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (!this.scheduleRequestMap.containsKey(scheduleRequestId)) {
            throw new DoesNotExistException(scheduleRequestId);
        }
        return new ScheduleRequestInfo(this.scheduleRequestMap.get (scheduleRequestId));
    }

    @Override
    public List<ScheduleRequestInfo> getScheduleRequestsByIds(List<String> scheduleRequestIds, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<ScheduleRequestInfo> list = new ArrayList<ScheduleRequestInfo> ();
        for (String id: scheduleRequestIds) {
            list.add (this.getScheduleRequest(id, contextInfo));
        }
        return list;
    }

    @Override
    public List<String> getScheduleRequestIdsByType(String scheduleRequestTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        for (ScheduleRequestInfo info: scheduleRequestMap.values ()) {
            if (scheduleRequestTypeKey.equals(info.getTypeKey())) {
                list.add (info.getId ());
            }
        }
        return list;
    }

    @Override
    public List<String> getScheduleRequestIdsByRefObject(String refObjectType, String refObjectId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        // go through the list of schedule requests and add those to list that
        // have the specified refObjectType and refObjectId
        for (ScheduleRequestInfo info: scheduleRequestMap.values()) {
            if (refObjectType.equals(info.getRefObjectTypeKey())
                && refObjectId.equals(info.getRefObjectId())) {
                list.add(info.getId());
            }
        }
        return list;
    }

    @Override
    public List<ScheduleRequestInfo> getScheduleRequestsByRefObject(String refObjectType, String refObjectId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        try { return getScheduleRequestsByIds(getScheduleRequestIdsByRefObject(refObjectType, refObjectId, contextInfo), contextInfo); }
        catch (DoesNotExistException e) {
            return new ArrayList<ScheduleRequestInfo> ();
        }
    }

    @Override
    public List<String> searchForScheduleRequestIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleRequestIds has not been implemented");
    }

    @Override
    public List<ScheduleRequestInfo> searchForScheduleRequests(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleRequests has not been implemented");
    }

    @Override
    public List<ValidationResultInfo> validateScheduleRequest(String validationTypeKey, String scheduleRequestTypeKey, ScheduleRequestInfo scheduleRequestInfo, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        // validate
        return new ArrayList<ValidationResultInfo> ();
    }

    @Override
    public ScheduleRequestInfo createScheduleRequest(String scheduleRequestTypeKey, ScheduleRequestInfo scheduleRequestInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
    {
        // create
        if (!scheduleRequestTypeKey.equals (scheduleRequestInfo.getTypeKey())) {
            throw new InvalidParameterException ("The type parameter does not match the type on the info object");
        }
        ScheduleRequestInfo copy = new ScheduleRequestInfo(scheduleRequestInfo);
        if (copy.getId() == null) {
            copy.setId(scheduleRequestMap.size() + "");
        }
        copy.setMeta(newMeta(contextInfo));
        scheduleRequestMap.put(copy.getId(), copy);
        return new ScheduleRequestInfo(copy);
    }

    @Override
    public ScheduleRequestInfo updateScheduleRequest(String scheduleRequestId, ScheduleRequestInfo scheduleRequestInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
            ,VersionMismatchException
    {
        // update
        if (!scheduleRequestId.equals (scheduleRequestInfo.getId())) {
            throw new InvalidParameterException ("The id parameter does not match the id on the info object");
        }
        ScheduleRequestInfo copy = new ScheduleRequestInfo(scheduleRequestInfo);
        ScheduleRequestInfo old = this.getScheduleRequest(scheduleRequestInfo.getId(), contextInfo);
        if (!old.getMeta().getVersionInd().equals(copy.getMeta().getVersionInd())) {
            throw new VersionMismatchException(old.getMeta().getVersionInd());
        }
        copy.setMeta(updateMeta(copy.getMeta(), contextInfo));
        this.scheduleRequestMap .put(scheduleRequestInfo.getId(), copy);
        return new ScheduleRequestInfo(copy);
    }

    @Override
    public StatusInfo deleteScheduleRequest(String scheduleRequestId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (this.scheduleRequestMap.remove(scheduleRequestId) == null) {
            throw new DoesNotExistException(scheduleRequestId);
        }
        return newStatus();
    }

    @Override
    public TimeSlotInfo getTimeSlot(String timeSlotId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (!this.timeSlotMap.containsKey(timeSlotId)) {
            throw new DoesNotExistException(timeSlotId);
        }
        return new TimeSlotInfo(this.timeSlotMap.get (timeSlotId));
    }

    @Override
    public List<TimeSlotInfo> getTimeSlotsByIds(List<String> timeSlotIds, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<TimeSlotInfo> list = new ArrayList<TimeSlotInfo> ();
        for (String id: timeSlotIds) {
            list.add (this.getTimeSlot(id, contextInfo));
        }
        return list;
    }

    @Override
    public List<String> getTimeSlotIdsByType(String timeSlotTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        for (TimeSlotInfo info: timeSlotMap.values ()) {
            if (timeSlotTypeKey.equals(info.getTypeKey())) {
                list.add (info.getId ());
            }
        }
        return list;
    }

    @Override
    public List<TimeSlotInfo> getTimeSlotsByDaysAndStartTime(String timeSlotTypeKey, List<Integer> daysOfWeek, TimeOfDayInfo startTime, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<TimeSlotInfo> list = new ArrayList<TimeSlotInfo> ();
        for (TimeSlotInfo info: timeSlotMap.values ()) {
            if (timeSlotTypeKey.equals(info.getTypeKey())) {
                if (daysOfWeek.equals(info.getWeekdays())) {
                    if (startTime.equals(info.getStartTime())) {
                        list.add (info);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<TimeSlotInfo> getTimeSlotsByDaysAndStartTimeAndEndTime(String timeSlotTypeKey, List<Integer> daysOfWeek, TimeOfDayInfo startTime, TimeOfDayInfo endTime, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<TimeSlotInfo> list = new ArrayList<TimeSlotInfo> ();
        for (TimeSlotInfo info: timeSlotMap.values ()) {
            if (timeSlotTypeKey.equals(info.getTypeKey())) {
                if (daysOfWeek.equals(info.getWeekdays())) {
                    if (startTime.equals(info.getStartTime())) {
                        if (endTime.equals(info.getEndTime())) {
                            list.add (info);
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<String> searchForTimeSlotIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForTimeSlotIds has not been implemented");
    }

    @Override
    public List<TimeSlotInfo> searchForTimeSlots(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForTimeSlots has not been implemented");
    }

    @Override
    public List<ValidationResultInfo> validateTimeSlot(String validationTypeKey, String timeSlotTypeKey, TimeSlotInfo timeSlotInfo, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        // validate
        return new ArrayList<ValidationResultInfo> ();
    }

    @Override
    public TimeSlotInfo createTimeSlot(String timeSlotTypeKey, TimeSlotInfo timeSlotInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
    {
        // create
        if (!timeSlotTypeKey.equals (timeSlotInfo.getTypeKey())) {
            throw new InvalidParameterException ("The type parameter does not match the type on the info object");
        }
        TimeSlotInfo copy = new TimeSlotInfo(timeSlotInfo);
        if (copy.getId() == null) {
            copy.setId(timeSlotMap.size() + "");
        }
        copy.setMeta(newMeta(contextInfo));
        timeSlotMap.put(copy.getId(), copy);
        return new TimeSlotInfo(copy);
    }

    @Override
    public TimeSlotInfo updateTimeSlot(String timeSlotId, TimeSlotInfo timeSlotInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
            ,VersionMismatchException
    {
        // update
        if (!timeSlotId.equals (timeSlotInfo.getId())) {
            throw new InvalidParameterException ("The id parameter does not match the id on the info object");
        }
        TimeSlotInfo copy = new TimeSlotInfo(timeSlotInfo);
        TimeSlotInfo old = this.getTimeSlot(timeSlotInfo.getId(), contextInfo);
        if (!old.getMeta().getVersionInd().equals(copy.getMeta().getVersionInd())) {
            throw new VersionMismatchException(old.getMeta().getVersionInd());
        }
        copy.setMeta(updateMeta(copy.getMeta(), contextInfo));
        this.timeSlotMap .put(timeSlotInfo.getId(), copy);
        return new TimeSlotInfo(copy);
    }

    @Override
    public StatusInfo deleteTimeSlot(String timeSlotId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (this.timeSlotMap.remove(timeSlotId) == null) {
            throw new DoesNotExistException(timeSlotId);
        }
        return newStatus();
    }

    @Override
    public StatusInfo submitScheduleBatch(String scheduleBatchId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("submitScheduleBatch has not been implemented");
    }

    @Override
    public StatusInfo commitSchedules(String scheduleBatchId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("commitSchedules has not been implemented");
    }

    @Override
    public List<Integer> getValidDaysOfWeekByTimeSlotType(String timeSlotTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("getValidDaysOfWeekByTimeSlotType has not been implemented");
    }

    @Override
    public List<ScheduleBatchInfo> getScheduleBatchesForScheduleTransaction(String scheduleTransactionId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("getScheduleBatchesForScheduleTransaction has not been implemented");
    }

    @Override
    public ScheduleTransactionInfo getScheduleTransaction(String scheduleTransactionId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (!this.scheduleTransactionMap.containsKey(scheduleTransactionId)) {
            throw new DoesNotExistException(scheduleTransactionId);
        }
        return new ScheduleTransactionInfo(this.scheduleTransactionMap.get (scheduleTransactionId));
    }

    @Override
    public List<ScheduleTransactionInfo> getScheduleTransactionsByIds(List<String> scheduleTransactionIds, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<ScheduleTransactionInfo> list = new ArrayList<ScheduleTransactionInfo> ();
        for (String id: scheduleTransactionIds) {
            list.add (this.getScheduleTransaction(id, contextInfo));
        }
        return list;
    }

    @Override
    public List<String> getScheduleTransactionIdsByType(String scheduleTransactionTypeKey, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        for (ScheduleTransactionInfo info: scheduleTransactionMap.values ()) {
            if (scheduleTransactionTypeKey.equals(info.getTypeKey())) {
                list.add (info.getId ());
            }
        }
        return list;
    }

    @Override
    public List<String> getScheduleTransactionIdsByRefObject(String refObjectType, String refObjectId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        List<String> list = new ArrayList<String> ();
        // go through the list of schedule transactions and add those to list that
        // have the specified refObjectType and refObjectId
        for (ScheduleTransactionInfo info: scheduleTransactionMap.values()) {
            if (refObjectType.equals(info.getRefObjectTypeKey())
                    && refObjectId.equals(info.getRefObjectId())) {
                list.add(info.getId());
            }
        }
        return list;
    }

    @Override
    public List<ScheduleTransactionInfo> getScheduleTransactionsByRefObject(String refObjectType, String refObjectId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        try { return getScheduleTransactionsByIds(getScheduleTransactionIdsByRefObject(refObjectType, refObjectId, contextInfo), contextInfo); }
        catch (DoesNotExistException e) {
            return new ArrayList<ScheduleTransactionInfo> ();
        }
    }

    @Override
    public List<ScheduleTransactionInfo> getScheduleTransactionsForScheduleBatch(String scheduleBatchId, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("getScheduleTransactionsForScheduleBatch has not been implemented");
    }

    @Override
    public List<String> searchForScheduleTransactionIds(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleTransactionIds has not been implemented");
    }

    @Override
    public List<ScheduleTransactionInfo> searchForScheduleTransactions(QueryByCriteria criteria, ContextInfo contextInfo)
            throws InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        throw new OperationFailedException ("searchForScheduleTransactions has not been implemented");
    }

    @Override
    public List<ValidationResultInfo> validateScheduleTransaction(String validationTypeKey, String scheduleTransactionTypeKey, ScheduleRequestInfo scheduleTransactionInfo, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        // validate
        return new ArrayList<ValidationResultInfo> ();
    }

    @Override
    public ScheduleTransactionInfo createScheduleTransaction(String scheduleBatchId, String scheduleTransactionTypeKey, ScheduleTransactionInfo scheduleTransactionInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
    {
        // create
        if (!scheduleTransactionTypeKey.equals (scheduleTransactionInfo.getTypeKey())) {
            throw new InvalidParameterException ("The type parameter does not match the type on the info object");
        }
        ScheduleTransactionInfo copy = new ScheduleTransactionInfo(scheduleTransactionInfo);
        if (copy.getId() == null) {
            copy.setId(scheduleTransactionMap.size() + "");
        }
        copy.setMeta(newMeta(contextInfo));
        scheduleTransactionMap.put(copy.getId(), copy);
        return new ScheduleTransactionInfo(copy);
    }

    @Override
    public ScheduleTransactionInfo updateScheduleTransaction(String scheduleTransactionId, ScheduleTransactionInfo scheduleTransactionInfo, ContextInfo contextInfo)
            throws DataValidationErrorException
            ,DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
            ,ReadOnlyException
            ,VersionMismatchException
    {
        // update
        if (!scheduleTransactionId.equals (scheduleTransactionInfo.getId())) {
            throw new InvalidParameterException ("The id parameter does not match the id on the info object");
        }
        ScheduleTransactionInfo copy = new ScheduleTransactionInfo(scheduleTransactionInfo);
        ScheduleTransactionInfo old = this.getScheduleTransaction(scheduleTransactionInfo.getId(), contextInfo);
        if (!old.getMeta().getVersionInd().equals(copy.getMeta().getVersionInd())) {
            throw new VersionMismatchException(old.getMeta().getVersionInd());
        }
        copy.setMeta(updateMeta(copy.getMeta(), contextInfo));
        this.scheduleTransactionMap .put(scheduleTransactionInfo.getId(), copy);
        return new ScheduleTransactionInfo(copy);
    }

    @Override
    public StatusInfo deleteScheduleTransaction(String scheduleTransactionId, ContextInfo contextInfo)
            throws DoesNotExistException
            ,InvalidParameterException
            ,MissingParameterException
            ,OperationFailedException
            ,PermissionDeniedException
    {
        if (this.scheduleTransactionMap.remove(scheduleTransactionId) == null) {
            throw new DoesNotExistException(scheduleTransactionId);
        }
        return newStatus();
    }

    @Override
    public Boolean areTimeSlotsInConflict(String timeSlot1Id, String timeSlot2Id, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        TimeSlotInfo timeSlotInfo1 = getTimeSlot(timeSlot1Id, contextInfo);
        TimeSlotInfo timeSlotInfo2 = getTimeSlot(timeSlot2Id, contextInfo);
        return SchedulingServiceUtil.areTimeSlotsInConflict(timeSlotInfo1, timeSlotInfo2);
    }

    private StatusInfo newStatus() {
        StatusInfo status = new StatusInfo();
        status.setSuccess(Boolean.TRUE);
        return status;
    }

    private MetaInfo newMeta(ContextInfo context) {
        MetaInfo meta = new MetaInfo();
        meta.setCreateId(context.getPrincipalId());
        meta.setCreateTime(new Date());
        meta.setUpdateId(context.getPrincipalId());
        meta.setUpdateTime(meta.getCreateTime());
        meta.setVersionInd("0");
        return meta;
    }

    private MetaInfo updateMeta(MetaInfo old, ContextInfo context) {
        MetaInfo meta = new MetaInfo(old);
        meta.setUpdateId(context.getPrincipalId());
        meta.setUpdateTime(new Date());
        meta.setVersionInd((Integer.parseInt(meta.getVersionInd()) + 1) + "");
        return meta;
    }

}
