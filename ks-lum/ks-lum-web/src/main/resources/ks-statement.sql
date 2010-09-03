--
-- Copyright 2010 The Kuali Foundation Licensed under the
-- Educational Community License, Version 2.0 (the "License"); you may
-- not use this file except in compliance with the License. You may
-- obtain a copy of the License at
--
-- http://www.osedu.org/licenses/ECL-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an "AS IS"
-- BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
-- or implied. See the License for the specific language governing
-- permissions and limitations under the License.
--

// ---------------------------------------------------------------------------
// |   Statement, Requirement Component and Natural Language Configuration   |
// ---------------------------------------------------------------------------

//STATEMENT TYPES
// Course
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course',                                      'Academic Readiness Rules', 'Rules used in the evaluation of a person''s academic readiness for enrollment.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.prereq',             'Academic Readiness Pre Reqs', 'Pre-requisite rules used in the evaluation of a person''s academic readiness for enrollment.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.coreq',              'Academic Readiness Co Reqs', 'Co-requisite rules used in the evaluation of a person''s academic readiness for enrollment.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.antireq',            'Academic Readiness Anti Reqs', 'Anti-requisite rules used in the evaluation of a person''s academic readiness for enrollment.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.studentEligibility', 'Academic Readiness Student Eligibilities', 'Enrollment eligibility rules used in the evaluation of a person''s academic readiness for enrollment.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.credit.repeatable',                    'Repeatable for Credit', 'Rules used for courses which are repeatable for credit.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.credit.restriction',                   'Courses that Restrict Credit', 'Rules used for courses that restrict the awarding of credit in other courses.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.recommendedPreparation',               'Recommended Preparation', 'Non-enforceable rules used for recommended course preparation.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
// Program
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.program',                      'Program Requirements List', 'List of program requirements (rules).', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.program.entrance',             'Program Entrance Requirements', 'Program requirements (rules) used in the evaluation of a person''s entry into a program.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.program.satisfactoryProgress', 'Program Satisfactory Progress Requirements', 'Program requirements (rules) used in maintaining minimum scholarship standards.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.program.completion',           'Program Completion Requirements', 'Program requirements (rules) used in the evaluation of a person''s program completion.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})


// STMT_TYPE <-> STMT_TYPE
// Course
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.academicReadiness.prereq')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.academicReadiness.coreq')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.academicReadiness.antireq')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.academicReadiness.studentEligibility')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.credit.repeatable')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.credit.restriction')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.course','kuali.statement.type.course.recommendedPreparation')
// Program
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.program','kuali.statement.type.program.entrance')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.program','kuali.statement.type.program.satisfactoryProgress')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.statement.type.program','kuali.statement.type.program.completion')


// REQUIREMENT TYPES
// Courses
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.completed.none',         'None of required courses', 'Must not have successfully completed any courses from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.completed.all',          'All of required courses', 'Must have successfully completed all courses from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.completed.nof',          'N of required courses', 'Must have successfully completed a minimum of <n> courses from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.enrolled.nof',           'Enrolled in N of required courses', 'Must be concurrently enrolled in a minimum of <n> courses from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.nof',  'Completed N credits from courses', 'Must have successfully completed a minimum of <n> credits from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.none', 'Completed none credits from courses', 'Must not have successfully completed any credits from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.max',  'Maximum credit from courses', 'Must successfully complete no more than  <n> credits from <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.gpa.min',                'Minimum overall GPA', 'Must have earned a minimum GPA of <GPA> in <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2001-11-30 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.courseList.coreq.all',                    'All of required courses', 'Must be enrolled in all of the following courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.courseList.coreq.oneof',                  'One of required courses', 'Must be enrolled in one of the following courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.programList.enroll.oneof',                'One of required programs', 'Enrollment is limited to students in the following programs', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.programList.enroll.none',                 'None of required programs', 'Enrollment is not available to students in the following programs', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.grade.min',              'Grade in required courses', 'Must have earned a minimum grade of <gradeType> <grade> in <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.grade.max',              'Grade in required courses', 'Must not have earned a grade of <gradeType> <grade> or higher in <courses>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.permission.org.required',          'Organization permisison required', 'Permission of <administering org> required', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.permission.instructor.required',   'Instructor permission required', 'Permission of <instructor> required', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.test.score.min',                   'Minimum score on test', 'Must have achieved a minimum score of <score> on <tests>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.test.score.max',                   'Maximum score on test', 'Must have achieved a score no higher than <score> on <test> ', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min',          'Minimum grade in required courses', 'Must successfully complete a minimum of <n> courses  from <courses> with a minimum grade of <gradeType> <grade>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration',    'Program admission for courses in duration', 'Students admitted to <program> may take no more than <n> courses in the <org> in <duration> <durationType>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration', 'Program admission course restriction in duration', 'Students not admitted to <program> may take no more than <n> courses in the <org> in <duration> <durationType>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.org.program.admitted',             'Program admission at campus', 'Must be admitted to any program offered at the course campus location', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.program.notadmitted',              'Program admission restriction', 'Must not have been admitted to the <program> program', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.credits.repeat.max',               'Course repeatable for maximum credit', 'May be repeated for a maximum of <n> credits', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.course.org.credits.completed.min',        'Minimum credit completion in organization/department', 'Must have successfully completed a minimum of <n> credits from courses in the <org>', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
// Programs
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.programset.completed.nof',        'Minimum of n programs from programs', 'Must have successfully completed a minimum of <n> programs from <programs> programs', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.programset.notcompleted.nof',     'Not completed any programs', 'Must not have successfully completed any of <programs> programs', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.programset.completed.all',        'All of required programs', 'Must have successfully completed all of <programs> programs', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.programset.coursecompleted.nof',  'Minimum courses from programs', 'Must have successfully completed a minimum of <n> courses from <programs> programs', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.admitted.credits',                'Earning n credits', 'Must be admitted to program prior to earning <n> credits', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.credits.min',                     'Earned minimum n credits', 'Must have earned a minimum of <n> total credits', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.credits.max',                     'Earned more than n credits', 'Must not have earned more than <n> credits', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.completion.duration',             'Not exceed n duration without completing program', 'Must not exceed <n> <duration> without completing program', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.candidate.status.duration',       'Candidate status within duration after program entry', 'Must attain candidate status within <n> <duration> after program entry term', null, null)
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, NAME, TYPE_DESC, EFF_DT, EXPIR_DT) VALUES ('kuali.reqComponent.type.program.completion.duration.afterentry',  'Complete program within duration after program entry', 'Must complete program within <n> <durations> after program entry term', null, null)


// REQUIREMENT COMPONENT FIELD TYPES
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.value', 'Value', 'Value like Date, String, Integer, Double etc.','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.valueDataType', 'Value Data Type', 'Value data type like Date, String, Integer, Double etc.','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.operator', 'Relational Operator', 'Relational Operator','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.clu.id', 'CLU Id', 'CLU identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.cluSet.id', 'CLU Set Id', 'CLU set identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.gpa', 'Grade Point Average Value', 'Grade point average value','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.grade.id', 'Grade Value Id', 'Grade value identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.gradeType.id', 'Grade Type Id', 'Grade type identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.org.id', 'Organization Id', 'Organization identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.person.id', 'Person Id', 'Person identifier','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.test.score', 'Test Score', 'Test score','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.duration', 'Duration Value', 'Duration value','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('kuali.reqComponent.field.type.durationType.id', 'Duration Type Id', 'Duration type identifier','string',null,null,null,null,null,null,null,null,0)


// REQ_COMP_TYPE -> REQ_COMP_FIELD_TYPE
// Courses
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.completed.none','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.completed.all','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.completed.nof','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.completed.nof','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.completed.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.enrolled.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.enrolled.nof','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.enrolled.nof','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.nof','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.nof','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.none','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.none','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.none','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.max','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.max','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.credits.completed.max','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.gpa.min','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.gpa.min','kuali.reqComponent.field.type.gpa')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.courseList.coreq.all','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.courseList.coreq.oneof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.programList.enroll.oneof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.programList.enroll.none','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.min','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.min','kuali.reqComponent.field.type.grade.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.min','kuali.reqComponent.field.type.gradeType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.max','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.max','kuali.reqComponent.field.type.grade.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.grade.max','kuali.reqComponent.field.type.gradeType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.permission.org.required','kuali.reqComponent.field.type.org.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.permission.instructor.required','kuali.reqComponent.field.type.person.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.test.score.min','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.test.score.min','kuali.reqComponent.field.type.test.score')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.test.score.max','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.test.score.max','kuali.reqComponent.field.type.test.score')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min','kuali.reqComponent.field.type.grade.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.courseset.nof.grade.min','kuali.reqComponent.field.type.gradeType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.org.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.duration')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.admitted.org.duration','kuali.reqComponent.field.type.durationType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.org.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.duration')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted.org.duration','kuali.reqComponent.field.type.durationType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.program.notadmitted','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.credits.repeat.max','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.credits.repeat.max','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.org.credits.completed.min','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.org.credits.completed.min','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.course.org.credits.completed.min','kuali.reqComponent.field.type.org.id')
// Programs
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.completed.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.completed.nof','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.completed.nof','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.notcompleted.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.completed.all','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.coursecompleted.nof','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.coursecompleted.nof','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.programset.coursecompleted.nof','kuali.reqComponent.field.type.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.admitted.credits','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.admitted.credits','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.credits.min','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.credits.min','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.credits.max','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.credits.max','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration','kuali.reqComponent.field.type.durationType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.candidate.status.duration','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.candidate.status.duration','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.candidate.status.duration','kuali.reqComponent.field.type.durationType.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration.afterentry','kuali.reqComponent.field.type.value')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration.afterentry','kuali.reqComponent.field.type.valueDataType')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqComponent.type.program.completion.duration.afterentry','kuali.reqComponent.field.type.durationType.id')


// REQ_COMPONENT_TYPE_NL_TEMPLATE
// Courses
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1', 'KUALI.RULEEDIT', '#if($cluSet.getCluList().size() == 1)Must not have successfully completed $cluSet.getCluSetAsCode()#{else}Must not have successfully completed any courses from $cluSet.getCluSetAsCode()#end', 'kuali.reqComponent.type.course.courseset.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2', 'KUALI.RULEEDIT', '#if($cluSet.getCluList().size() == 1)Must have successfully completed $cluSet.getCluSetAsCode()#{else}Must have successfully completed all courses from $cluSet.getCluSetAsCode()#end', 'kuali.reqComponent.type.course.courseset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3', 'KUALI.RULEEDIT', '#if($value == 1 && $cluSet.getCluList().size() == 1)Must have successfully completed $cluSet.getCluSetAsCode()#{else}Must have successfully completed a minimum of $value $NLHelper.getProperGrammar($value, "course") from $cluSet.getCluSetAsCode()#end', 'kuali.reqComponent.type.course.courseset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('4', 'KUALI.RULEEDIT', 'Must be concurrently enrolled in a minimum of $value $NLHelper.getProperGrammar($value, "course") from $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.enrolled.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('5', 'KUALI.RULEEDIT', 'Must have successfully completed a minimum of $value $NLHelper.getProperGrammar($value, "credit") from $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.credits.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('6', 'KUALI.RULEEDIT', 'Must not have successfully completed any credits from $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.credits.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('7', 'KUALI.RULEEDIT', 'Must successfully complete no more than $value $NLHelper.getProperGrammar($value, "credit") from $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.credits.completed.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('8', 'KUALI.RULEEDIT', 'Must have earned a minimum GPA of $gpa in $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.gpa.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('9', 'KUALI.RULEEDIT', 'Must be enrolled in all of $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('10', 'KUALI.RULEEDIT', 'Must be enrolled in one of of $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('11', 'KUALI.RULEEDIT', 'Restricted to students in the $cluSet.getCluSetAsCode() only', 'kuali.reqComponent.type.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('12', 'KUALI.RULEEDIT', 'Not for students in $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.programList.enroll.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('13', 'KUALI.RULEEDIT', 'Must have earned a minimum grade of $gradeType.getName().toLowerCase() $grade in $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.grade.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('14', 'KUALI.RULEEDIT', 'Must not have earned a maximum grade of $gradeType.getName().toLowerCase() $grade or higher in $cluSet.getCluSetAsCode()', 'kuali.reqComponent.type.course.courseset.grade.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('15', 'KUALI.RULEEDIT', 'Permission of $org.getLongName() required', 'kuali.reqComponent.type.course.permission.org.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('16', 'KUALI.RULEEDIT', 'Permission of instructor required', 'kuali.reqComponent.type.course.permission.instructor.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('17', 'KUALI.RULEEDIT', 'Must have achieved a minimum score of $fields.get(''kuali.reqComponent.field.type.test.score'') on $cluSet.getCluSetAsShortName()', 'kuali.reqComponent.type.course.test.score.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('18', 'KUALI.RULEEDIT', 'Must have achieved a score no higher than $fields.get(''kuali.reqComponent.field.type.test.score'') on $cluSet.getCluSetAsShortName()', 'kuali.reqComponent.type.course.test.score.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('19', 'KUALI.RULEEDIT', 'Must successfully complete a minimum of $value $NLHelper.getProperGrammar($value, "course") from $cluSet.getCluSetAsCode() with a minimum grade of $gradeType.getName().toLowerCase() $grade', 'kuali.reqComponent.type.course.courseset.nof.grade.min', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('20', 'KUALI.RULEEDIT', 'Students admitted to $NLHelper.getCluOrCluSetAsShortNames($clu,$cluSet) may take no more than $value $NLHelper.getProperGrammar($value, "course") in the $org.getLongName() in $duration $NLHelper.getProperGrammar($duration, $durationType.getName()).toLowerCase()', 'kuali.reqComponent.type.course.program.admitted.org.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('21', 'KUALI.RULEEDIT', 'Students not admitted to $NLHelper.getCluOrCluSetAsShortNames($clu,$cluSet) may take no more than $value $NLHelper.getProperGrammar($value, "course") in the $org.getLongName() in $duration $NLHelper.getProperGrammar($duration, $durationType.getName()).toLowerCase()', 'kuali.reqComponent.type.course.program.notadmitted.org.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('22', 'KUALI.RULEEDIT', 'Must be admitted to any program offered at the course campus location', 'kuali.reqComponent.type.course.org.program.admitted', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('23', 'KUALI.RULEEDIT', 'Must not have been admitted to the $NLHelper.getCluOrCluSetAsShortNames($clu,$cluSet) program', 'kuali.reqComponent.type.course.program.notadmitted', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('24', 'KUALI.RULEEDIT', 'May be repeated for a maximum of $value credits', 'kuali.reqComponent.type.course.credits.repeat.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('25', 'KUALI.RULEEDIT', 'Must have successfully completed a minimum of $value $NLHelper.getProperGrammar($value, "credit") from courses in the $org.getLongName()', 'kuali.reqComponent.type.course.org.credits.completed.min', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('101', 'KUALI.EXAMPLE', 'Must not have successfully completed any courses from (MATH111, 140, 220, or STAT100)', 'kuali.reqComponent.type.course.courseset.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('102', 'KUALI.EXAMPLE', 'Must have successfully completed all courses from (MATH111, 140, 220, and STAT100)', 'kuali.reqComponent.type.course.courseset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('103', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 1 course from (MATH111, 140, 220, or STAT100)', 'kuali.reqComponent.type.course.courseset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('104', 'KUALI.EXAMPLE', 'Must be concurrently enrolled in a minimum of 1 course from (MATH111, 140, 220, or STAT100)', 'kuali.reqComponent.type.course.courseset.enrolled.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('105', 'KUALI.EXAMPLE', 'Must have earned a minimum of 3 credits from (MATH111, 140, 220, or STAT100)', 'kuali.reqComponent.type.course.courseset.credits.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('106', 'KUALI.EXAMPLE', 'No credits may be used from (Developmental Math courses (MATH001, 002, 003, 010, 011, 013 or 015)', 'kuali.reqComponent.type.course.courseset.credits.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('107', 'KUALI.EXAMPLE', 'Must not have earned more than 6 credits from (MATH111, 140, 220, and STAT100)', 'kuali.reqComponent.type.course.courseset.credits.completed.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('108', 'KUALI.EXAMPLE', 'Must have earned a minimum GPA of 2.00 in (MATH111, 140, 220, and  STAT100)', 'kuali.reqComponent.type.course.courseset.gpa.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('109', 'KUALI.EXAMPLE', 'Must be enrolled in all of MATH100, MATH101', 'kuali.reqComponent.type.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('110', 'KUALI.EXAMPLE', 'Must be enrolled in one of MATH100, MATH101, MATH102', 'kuali.reqComponent.type.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('111', 'KUALI.EXAMPLE', 'Must be in one of the following program(s): Creative Writing Major', 'kuali.reqComponent.type.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('112', 'KUALI.EXAMPLE', 'Not for students in a Music Major or Minor', 'kuali.reqComponent.type.programList.enroll.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('113', 'KUALI.EXAMPLE', 'Must have earned a minimum grade of letter B in (MATH111, 140, 220, and STAT100)', 'kuali.reqComponent.type.course.courseset.grade.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('114', 'KUALI.EXAMPLE', 'Must not have earned a maximum grade of letter C or higher in (MATH111, 140, 220, and STAT100)', 'kuali.reqComponent.type.course.courseset.grade.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('115', 'KUALI.EXAMPLE', 'Permission of English Department Required', 'kuali.reqComponent.type.course.permission.org.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('116', 'KUALI.EXAMPLE', 'Permission of instructor required', 'kuali.reqComponent.type.course.permission.instructor.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('117', 'KUALI.EXAMPLE', 'Must have achieved a minimum score of 600 on SAT Critical Reading Exam', 'kuali.reqComponent.type.course.test.score.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('118', 'KUALI.EXAMPLE', 'Must have achieved a score no higher than 5 on AP Japanese', 'kuali.reqComponent.type.course.test.score.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('119', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 1 course from (MATH111, 140, 220, or STAT100) with a minimum grade of letter B', 'kuali.reqComponent.type.course.courseset.nof.grade.min', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('120', 'KUALI.EXAMPLE', 'Students admitted to Sociology may take no more than 2 courses in the Math department in 1 year', 'kuali.reqComponent.type.course.program.admitted.org.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('121', 'KUALI.EXAMPLE', 'Students not admitted to Sociology may take no more than 3 courses in the Computer Science Dept department in 1 year', 'kuali.reqComponent.type.course.program.notadmitted.org.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('122', 'KUALI.EXAMPLE', 'Must be admitted to any program offered at the course campus location', 'kuali.reqComponent.type.course.org.program.admitted', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('123', 'KUALI.EXAMPLE', 'Must not have been admitted to the Sociology program', 'kuali.reqComponent.type.course.program.notadmitted', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('124', 'KUALI.EXAMPLE', 'May be repeated for a maximum of $value credits', 'kuali.reqComponent.type.course.credits.repeat.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('125', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 3 credits from courses in the Math department', 'kuali.reqComponent.type.course.org.credits.completed.min', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('201', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('202', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('203', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('204', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.enrolled.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('205', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Credits> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.credits.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('206', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.credits.completed.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('207', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses;reqCompFieldLabel=Credits> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.credits.completed.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('208', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.gpa;reqCompFieldLabel=GPA> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.gpa.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('209', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('210', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('211', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqComponent.type.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('212', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqComponent.type.programList.enroll.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('213', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.gradeType.id;reqCompFieldLabel=Grade Type> of <reqCompFieldType=kuali.reqComponent.field.type.grade.id;reqCompFieldLabel=Grade> in <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.grade.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('214', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.gradeType.id;reqCompFieldLabel=Grade Type> of <reqCompFieldType=kuali.reqComponent.field.type.grade.id;reqCompFieldLabel=Grade> in <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.course.courseset.grade.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('215', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.org.id;reqCompFieldLabel=Organization>', 'kuali.reqComponent.type.course.permission.org.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('216', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.person.id;reqCompFieldLabel=Instructor>', 'kuali.reqComponent.type.course.permission.instructor.required', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('217', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.test.score;reqCompFieldLabel=Test Score> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Tests>', 'kuali.reqComponent.type.course.test.score.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('218', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.test.score;reqCompFieldLabel=Test Score> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Tests>', 'kuali.reqComponent.type.course.test.score.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('219', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses> with <reqCompFieldType=kuali.reqComponent.field.type.gradeType.id;reqCompFieldLabel=Grade Type> of <reqCompFieldType=kuali.reqComponent.field.type.grade.id;reqCompFieldLabel=Grade>', 'kuali.reqComponent.type.course.courseset.nof.grade.min', 'en')

// Programs
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1001', 'KUALI.RULEEDIT', 'Must have successfully completed a minimum of $value $NLHelper.getProperGrammar($value, "program") from $cluSet.getCluSetAsShortName() $NLHelper.getProperGrammar($cluSet.getCluList().size(), "program")', 'kuali.reqComponent.type.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1002', 'KUALI.RULEEDIT', 'Must not have successfully completed #if($cluSet.getCluList().size() > 1)any of #end$cluSet.getCluSetAsShortName() $NLHelper.getProperGrammar($cluSet.getCluList().size(), "program")', 'kuali.reqComponent.type.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1003', 'KUALI.RULEEDIT', 'Must have successfully completed #if($cluSet.getCluList().size() != 1)all of #end$cluSet.getCluSetAsShortName() $NLHelper.getProperGrammar($cluSet.getCluList().size(), "program")', 'kuali.reqComponent.type.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1004', 'KUALI.RULEEDIT', 'Must have successfully completed a minimum of $value $NLHelper.getProperGrammar($value, "course") from $cluSet.getCluSetAsShortName() $NLHelper.getProperGrammar($cluSet.getCluList().size(), "program")', 'kuali.reqComponent.type.program.programset.coursecompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1005', 'KUALI.RULEEDIT', 'Must be admitted to program prior to earning $value $NLHelper.getProperGrammar($value, "credit")', 'kuali.reqComponent.type.program.admitted.credits', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1006', 'KUALI.RULEEDIT', 'Must have earned a minimum of $value total $NLHelper.getProperGrammar($value, "credit")', 'kuali.reqComponent.type.program.credits.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1007', 'KUALI.RULEEDIT', 'Must not have earned more than $value $NLHelper.getProperGrammar($value, "credit")', 'kuali.reqComponent.type.program.credits.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1008', 'KUALI.RULEEDIT', 'Must not exceed $duration $NLHelper.getProperGrammar($duration, $durationType.getName()).toLowerCase() without completing program', 'kuali.reqComponent.type.program.completion.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1009', 'KUALI.RULEEDIT', 'Must attain candidate status within $duration $NLHelper.getProperGrammar($duration, $durationType.getName()).toLowerCase() after program entry term', 'kuali.reqComponent.type.program.candidate.status.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1010', 'KUALI.RULEEDIT', 'Must complete program within $duration $NLHelper.getProperGrammar($duration, $durationType.getName()).toLowerCase() after program entry term', 'kuali.reqComponent.type.program.completion.duration.afterentry', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2001', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 1 program from (Geology or  Sociology) programs', 'kuali.reqComponent.type.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2002', 'KUALI.EXAMPLE', 'Must not have successfully completed any of (Geology or Sociology) programs', 'kuali.reqComponent.type.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2003', 'KUALI.EXAMPLE', 'Must have successfully completed all of (Sociology and CORE Advanced Studies)', 'kuali.reqComponent.type.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2004', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 14 courses from ( Sociology and CORE Advanced Studies) programs', 'kuali.reqComponent.type.program.programset.coursecompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2005', 'KUALI.EXAMPLE', 'Must be admitted to program prior to earning 60 credits', 'kuali.reqComponent.type.program.admitted.credits', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2006', 'KUALI.EXAMPLE', 'Must have earned a minimum of 120 total credits', 'kuali.reqComponent.type.program.credits.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2007', 'KUALI.EXAMPLE', 'Must not have earned more than 130 total credits', 'kuali.reqComponent.type.program.credits.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2008', 'KUALI.EXAMPLE', 'Must not exceed 10 semesters without completing program', 'kuali.reqComponent.type.program.completion.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2009', 'KUALI.EXAMPLE', 'Must attain candidate status within 3 semesters after program entry term', 'kuali.reqComponent.type.program.candidate.status.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2010', 'KUALI.EXAMPLE', 'Must complete program within 10 semesters after program entry term', 'kuali.reqComponent.type.program.completion.duration.afterentry', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3001', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqComponent.type.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3002', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqComponent.type.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3003', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqComponent.type.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3004', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number> from <reqCompFieldType=kuali.reqComponent.field.type.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqComponent.type.program.programset.coursecompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3005', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Credits>', 'kuali.reqComponent.type.program.admitted.credits', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3006', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number>', 'kuali.reqComponent.type.program.credits.min', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3007', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number>', 'kuali.reqComponent.type.program.credits.max', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3008', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number>', 'kuali.reqComponent.type.program.completion.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3009', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number>', 'kuali.reqComponent.type.program.candidate.status.duration', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3010', 'KUALI.COMPOSITION', '<reqCompFieldType=kuali.reqComponent.field.type.value;reqCompFieldLabel=Number>', 'kuali.reqComponent.type.program.completion.duration.afterentry', 'en')


// STMT_TYPE <-> REQ_COM_TYPE
// Courses
// Courses - Anti-req
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.antireq','kuali.reqComponent.type.course.courseset.credits.completed.none')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.antireq','kuali.reqComponent.type.course.courseset.credits.completed.max')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.antireq','kuali.reqComponent.type.course.courseset.completed.none')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.antireq','kuali.reqComponent.type.course.test.score.max')
// Courses - Pre-req
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.completed.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.completed.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.credits.completed.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.gpa.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.grade.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.grade.max')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.permission.org.required')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.permission.instructor.required')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.test.score.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.courseset.nof.grade.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.program.admitted.org.duration')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.prereq','kuali.reqComponent.type.course.org.credits.completed.min')
// Courses - Co-req
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.coreq','kuali.reqComponent.type.courseList.coreq.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.coreq','kuali.reqComponent.type.courseList.coreq.oneof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.coreq','kuali.reqComponent.type.course.courseset.enrolled.nof')
// Courses - Student Eligibility
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.studentEligibility','kuali.reqComponent.type.programList.enroll.oneof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.studentEligibility','kuali.reqComponent.type.programList.enroll.none')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.studentEligibility','kuali.reqComponent.type.course.program.notadmitted.org.duration')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.studentEligibility','kuali.reqComponent.type.course.org.program.admitted')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.academicReadiness.studentEligibility','kuali.reqComponent.type.course.program.notadmitted')
// Courses - Repeatable for Credit
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.credit.repeatable','kuali.reqComponent.type.course.credits.repeat.max')
// Courses - Recommended Preparation
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.recommendedPreparation','kuali.reqComponent.type.course.org.credits.completed.min')
// Courses - Courses that Restrict Credit 
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.course.credit.restriction','kuali.reqComponent.type.course.courseset.completed.none')

// Program Satisfactory Progress
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.program.admitted.credits')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.program.credits.max')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.program.completion.duration')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.program.candidate.status.duration')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.course.test.score.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.satisfactoryProgress','kuali.reqComponent.type.course.courseset.nof.grade.min')
// Program Entrance
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.program.programset.completed.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.program.programset.notcompleted.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.program.programset.completed.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.program.programset.coursecompleted.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.course.courseset.gpa.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.course.test.score.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.entrance','kuali.reqComponent.type.program.admitted.credits')
// Program Completion
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.program.programset.completed.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.program.programset.completed.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.program.programset.coursecompleted.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.course.courseset.gpa.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.program.credits.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.course.test.score.min')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.statement.type.program.completion','kuali.reqComponent.type.program.credits.max')


// KSST_NL_USAGE_TYPE
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.CATALOG', 'Kuali Catalog', 'Kuali Catalog', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.EXAMPLE', 'Kuali Rule Example', 'Rule Example', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.COMPOSITION', 'Kuali Rule Composition', 'Rule Composition', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.RULEEDIT', 'Kuali Rule Editing', 'Rule Edit', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.RULEVIEW', 'Kuali Rule Viewing', 'Rule View', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})

// KSST_OBJECT_TYPE
INSERT INTO KSST_OBJECT_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu', 'Kuali CLU', 'Kuali CLU', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_OBJECT_SUB_TYPE
INSERT INTO KSST_OBJECT_SUB_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('course', 'Kuali Course', 'Kuali Course', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_OBJECT_SUB_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('program', 'Kuali Program', 'Kuali Program', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_OBJ_TYP_JN_OBJ_SUB_TYP
INSERT INTO KSST_OBJ_TYP_JN_OBJ_SUB_TYP (OBJ_TYPE_ID, OBJ_SUB_TYPE_ID) VALUES ('clu', 'course')
INSERT INTO KSST_OBJ_TYP_JN_OBJ_SUB_TYP (OBJ_TYPE_ID, OBJ_SUB_TYPE_ID) VALUES ('clu', 'program')

// KSST_REF_STMT_REL_TYPE
// Course
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.prerequisites', 'CLU Pre-requisites', 'CLU Pre-requisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.corequisites', 'CLU Co-requisites', 'CLU Co-requisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.antirequisites', 'CLU Anti-requisites', 'CLU Anti-requisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.eligibilities', 'CLU Eligibilities', 'CLU Eligibilities', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.credit.repeatable', 'CLU Repeatable for Credit', 'CLU Repeatable for Credit', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.credit.restriction', 'CLU That Restrict Credit', 'CLU That Restrict Credit', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.credit.recommendedpreparation', 'CLU Recommended Preparations', 'CLU Recommended Preparations', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
//INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course', 'CLU Create Course', 'CLU Create Course', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
//INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.prereq', 'CLU Prerequisites', 'CLU Prerequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
//INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.coreq', 'CLU Corequisites', 'CLU Corequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
//INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.studentEligibility', 'CLU Enroll', 'CLU Enroll', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
//INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.statement.type.course.academicReadiness.antireq', 'CLU Antirequisites', 'CLU Antirequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
// Program
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.entrance', 'CLU Entrance Requirements', 'CLU Entrance Requirements', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.satisfactoryprogress', 'CLU Satisfactory Progress Requirements', 'CLU Satisfactory Progress Requirements', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.completion', 'CLU Completion Requirements', 'CLU Completion Requirements', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
// Deprecated - remove
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.rule', 'CLU Rules', 'CLU Rules', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_RSTMT_RTYP_JN_OSUB_TYP <-> KSST_OBJECT_SUB_TYPE
// Course
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.prerequisites')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.corequisites')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.antirequisites')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.eligibilities')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.credit.repeatable')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.credit.restriction')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('course', 'clu.credit.recommendedpreparation')
// Program
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('program', 'clu.entrance')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('program', 'clu.satisfactoryprogress')
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (OBJ_SUB_TYPE_ID, REF_STMT_REL_TYPE_ID) VALUES ('program', 'clu.completion')

// KSST_RSTMT_RTYP_JN_STMT_TYP <-> KSST_STMT_TYPE
// Course
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.prerequisites', 'kuali.statement.type.course.academicReadiness.prereq')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.corequisites', 'kuali.statement.type.course.academicReadiness.coreq')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.antirequisites', 'kuali.statement.type.course.academicReadiness.antireq')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.eligibilities', 'kuali.statement.type.course.academicReadiness.studentEligibility')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.credit.repeatable', 'kuali.statement.type.course.credit.repeatable')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.credit.restriction', 'kuali.statement.type.course.credit.restriction')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.credit.recommendedpreparation', 'kuali.statement.type.course.recommendedPreparation')
// Program
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.entrance', 'kuali.statement.type.program.entrance')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.satisfactoryprogress', 'kuali.statement.type.program.satisfactoryProgress')
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.completion', 'kuali.statement.type.program.completion')
