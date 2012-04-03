/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.student.enrollment.class2.courseoffering.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.kuali.student.common.dto.RichTextInfo;
import org.kuali.student.enrollment.courseoffering.service.R1ToR2CopyHelper;
import org.kuali.student.lum.course.dto.ActivityInfo;
import org.kuali.student.lum.course.dto.CourseInfo;
import org.kuali.student.lum.course.dto.FormatInfo;
import org.kuali.student.lum.course.service.CourseService;
import org.kuali.student.r2.common.util.constants.LuServiceConstants;

/**
 *
 * @author nwright
 */
public class CourseR1TestDataLoader {

    private CourseService courseService;
    private static final String principalId = CourseR1TestDataLoader.class.getSimpleName();

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseR1TestDataLoader(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseR1TestDataLoader() {
    }

    public void loadData() {
        loadCourse("COURSE1", "CHEM", "CHEM123", "Chemistry 123", "description 1", "COURSE1-FORMAT1", LuServiceConstants.COURSE_ACTIVITY_LECTURE_TYPE_KEY, LuServiceConstants.COURSE_ACTIVITY_LAB_TYPE_KEY);
        loadCourse("COURSE2", "ENG", "ENG101", "Intro English", "description 2", "COURSE2-FORMAT1", LuServiceConstants.COURSE_ACTIVITY_LECTURE_TYPE_KEY);  
    }

    private void loadCourse(String id,
            String subjectArea,
            String code,
            String title,
            String description,
            String formatId,
            String... activityTypeKeys) {
        CourseInfo info = new CourseInfo();
        info.setId(id);
        info.setSubjectArea(subjectArea);
        info.setCode(code);
        info.setCourseTitle(title);
        RichTextInfo rt = new RichTextInfo ();
        rt.setPlain(description);
        info.setDescr(rt);
        info.setType(LuServiceConstants.CREDIT_COURSE_LU_TYPE_KEY);
        info.setState("Active");
        info.setFormats(new ArrayList<FormatInfo>());
        FormatInfo format = new FormatInfo();
        info.getFormats().add(format);
        format.setId(formatId);
        format.setType(LuServiceConstants.COURSE_FORMAT_TYPE_KEY);
        format.setState("Active");
        format.setActivities(new ArrayList<ActivityInfo>());
        for (String activityTypeKey : activityTypeKeys) {
            ActivityInfo activity = new ActivityInfo();
            format.getActivities().add(activity);
            activity.setId(format.getId() + "-"+ activityTypeKey);
            activity.setActivityType(activityTypeKey);
            activity.setState("Draft");
        }
        try {
            CourseInfo newInfo = this.courseService.createCourse(info);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Date str2Date(String str, String context) {
        if (str == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.S");
        try {
            Date date = df.parse(str);
            return date;
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Bad date " + str + " in " + context);
        }
    }
}
