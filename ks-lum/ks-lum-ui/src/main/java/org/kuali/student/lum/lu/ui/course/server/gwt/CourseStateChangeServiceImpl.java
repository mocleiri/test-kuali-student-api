package org.kuali.student.lum.lu.ui.course.server.gwt;

import java.util.Date;
import java.util.List;

import org.kuali.student.core.dto.StatusInfo;
import org.kuali.student.core.exceptions.DoesNotExistException;
import org.kuali.student.core.exceptions.InvalidParameterException;
import org.kuali.student.core.versionmanagement.dto.VersionDisplayInfo;
import org.kuali.student.lum.common.client.lu.LUUIConstants;
import org.kuali.student.lum.course.dto.CourseInfo;
import org.kuali.student.lum.course.service.CourseService;
import org.kuali.student.lum.course.service.CourseServiceConstants;
import org.springframework.transaction.annotation.Transactional;

@Transactional(noRollbackFor={DoesNotExistException.class},rollbackFor={Throwable.class})
public class CourseStateChangeServiceImpl {
    private CourseService courseService;
    
   public StatusInfo changeState(String courseId, String newState, Date currentVersionStart) throws Exception {
    	
    	CourseInfo thisVerCourse = courseService.getCourse(courseId);
    	String prevState = thisVerCourse.getState();
		String verIndId = thisVerCourse.getVersionInfo().getVersionIndId();
		VersionDisplayInfo curVerDisplayInfo = courseService.getCurrentVersion(CourseServiceConstants.COURSE_NAMESPACE_URI, verIndId);
		String curVerId = curVerDisplayInfo.getId();
		CourseInfo currVerCourse = courseService.getCourse(curVerId);
		String currVerState = currVerCourse.getState();
		boolean isCurrVer = (courseId.equals(currVerCourse.getId()));

		StatusInfo ret = new StatusInfo();
		try {
			if (newState.equals(LUUIConstants.LU_STATE_ACTIVE)) {
				if (prevState.equals(LUUIConstants.LU_STATE_APPROVED)) {
					// since this is approved if isCurrVer we can assume there are no previously active versions to deal with
					if (isCurrVer) {
						// setstate for thisVerCourse and setCurrentVersion(courseId)
						updateCourseVersionStates(thisVerCourse, newState, currVerCourse, null, true, currentVersionStart);
					} else if (currVerState.equals(LUUIConstants.LU_STATE_ACTIVE) ||
							currVerState.equals(LUUIConstants.LU_STATE_INACTIVE)) {
						updateCourseVersionStates(thisVerCourse, newState, currVerCourse, LUUIConstants.LU_STATE_SUPERSEDED, true, currentVersionStart);
					}					

				} else if (prevState.equals(LUUIConstants.LU_STATE_INACTIVE)) {

				}
			}
			ret.setSuccess(new Boolean(true));
		} catch (Exception e) {
			ret.setSuccess(new Boolean(false));
			ret.setMessage(e.getMessage());
		}

		return ret;
    }

    /**
     * Based on null values, updates states of thisVerCourse and currVerCourse and sets thisVerCourse as the current version.  Attempts to rollback transaction on exception.
     *
     * @param thisVerCourse this is the version that the user selected to change the state
     * @param thisVerNewState this is state that the user selected to change thisVerCourse to
     * @param currVerCourse this is the current version of the course (currentVersionStartDt <= now && currentVersionEndDt > now)
     * @param currVerNewState this is the state that we need to set the current version to.  Set to null to not update the currVerCourse state.
     * @param makeCurrent if true we'll set thisVerCourse as the current version.
     * @param currentVersionStart the start date for the new current version to start on and the old current version to end on.  Set to null to use now as the start date.
     * @throws Exception
     */
    @Transactional(readOnly = false)
    private void updateCourseVersionStates (CourseInfo thisVerCourse, String thisVerNewState, CourseInfo currVerCourse, String currVerNewState, boolean makeCurrent, Date currentVersionStart) throws Exception {
    	String thisVerPrevState = thisVerCourse.getState();
    	String currVerPrevState = currVerCourse.getState();
    	
    	// if already current, will throw error if you try to make the current version the current version.
    	boolean isCurrent = thisVerCourse.getId().equals(currVerCourse.getId());
    	makeCurrent &= !isCurrent;        	
    	
    	if (thisVerNewState == null) {
    		throw new InvalidParameterException("new state cannot be null");
    	} else {
    		thisVerCourse.setState(thisVerNewState);
    		courseService.updateCourse(thisVerCourse);
    	}

    	// won't get called if previous exception was thrown
    	if (currVerNewState != null) {
    		currVerCourse.setState(currVerNewState);
    		courseService.updateCourse(currVerCourse);

    	}

    	if (makeCurrent == true) {
    		courseService.setCurrentCourseVersion(thisVerCourse.getId(), currentVersionStart);
    	}
    	
    	// for all draft and approved courses set the state to superseded.
    	// we should only need to evaluated versions with sequence number
    	// higher than previous active course.  If the course you're 
    	// activating is the current course check all versions. 
    	if (thisVerPrevState.equals(LUUIConstants.LU_STATE_APPROVED) &&
    			thisVerNewState.equals(LUUIConstants.LU_STATE_ACTIVE)) {

    		List<VersionDisplayInfo> versions = courseService.getVersions(CourseServiceConstants.COURSE_NAMESPACE_URI, thisVerCourse.getVersionInfo().getVersionIndId());		
    		Long startSeq = new Long(1);
    		
			if (!isCurrent && (currVerCourse.getId() != thisVerCourse.getId())) {
				startSeq = currVerCourse.getVersionInfo().getSequenceNumber() + 1;
			}
			
			for (VersionDisplayInfo versionInfo: versions) {
    			if (versionInfo.getSequenceNumber() >= startSeq) {
    				CourseInfo otherCourse = courseService.getCourse(versionInfo.getId());
    				if (otherCourse.getState().equals(LUUIConstants.LU_STATE_APPROVED) ||
    						otherCourse.getState().equals(LUUIConstants.LU_STATE_SUBMITTED) ||
    						otherCourse.getState().equals(LUUIConstants.LU_STATE_DRAFT)) {
    					otherCourse.setState(LUUIConstants.LU_STATE_SUPERSEDED);
    					courseService.updateCourse(otherCourse);
    				}
    			}
    		}  		
    	}

    }

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
}
