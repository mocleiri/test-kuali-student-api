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
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.createCourseAcademicReadiness', 'Rules used in the evaluation of a person''s academic readiness for enrollment in an LU.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Overall Academic Readiness Rules')
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.prereqAcademicReadiness', 'Pre req rules used in the evaluation of a person''s academic readiness for enrollment in an LU.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Academic Readiness Pre Reqs')
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.coreqAcademicReadiness', 'Co req used in the evaluation of a person''s academic readiness for enrollment in an LU.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Academic Readiness Co Reqs')
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.enrollAcademicReadiness', 'Enrollment req used in the evaluation of a person''s academic readiness for enrollment in an LU.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Academic Readiness Enroll Reqs')
INSERT INTO KSST_STMT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.luStatementType.antireqAcademicReadiness', 'Anti req used in the evaluation of a person''s academic readiness for enrollment in an LU.', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Academic Readiness Anti Reqs')

// REQUIREMENT TYPES
// Course
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList.none', 'Student must have completed none of the specified courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'None of required courses')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList.all', 'Student must have completed all of the specified courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'All of required courses')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList.nof', 'Student must take <n> courses from the specified courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'N of required courses')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.gradecheck', 'Student needs to have attained a minimum specified GPA', {ts '2000-01-01 00:00:00.0'}, {ts '2001-11-30 00:00:00.0'}, 'Minimum overall GPA')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.grdCondCourseList', 'Student must take <n> credits from the specified courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Course completed with minimum specified grade')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList.coreq.all', 'Student must be enrolled in all of the following courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'All of required courses')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.courseList.coreq.oneof', 'Student must be enrolled in one of the following courses', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'One of required courses')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.programList.enroll.oneof', 'Enrollment is limited to students in the following programs', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'One of required programs')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.programList.enroll.none', 'Enrollment is not available to students in the following programs', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'None of required programs')
// Programs
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.program.programset.completed.nof', 'Must have successfully completed a minimum of <n> programs from <programs> programs', null, null, 'All of required programs')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.program.programset.notcompleted.nof', 'Must not have successfully completed any of <programs> programs', null, null, 'All of required programs')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.program.programset.completed.all', 'Must have successfully completed all of <programs> programs ', null, null, 'All of required programs')
INSERT INTO KSST_REQ_COM_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) VALUES ('kuali.reqCompType.program.programset.coursecompleted.nof', 'Must have successfully completed a minimum of <n> courses from <programs> programs', null, null, 'All of required programs')

// REQUIREMENT FIELD TYPES
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.clu.id', 'CLU', 'CLU','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.cluSet.id', 'CLUSET', 'CLUSET','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.requiredCount', 'REQUIRED_COUNT', 'Required Count','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.gpa', 'GPA', 'GAP','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.totalCredits', 'TOTAL_CREDITS', 'Total Credits','number',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.operator', 'CLU', 'CLU','string',null,null,null,null,null,null,null,null,0)
INSERT INTO KSST_REQ_COM_FIELD_TYPE (ID, NAME, DESCR, DATA_TYPE, MIN_VALUE, MAX_VALUE, MIN_LENGTH, MAX_LENGTH, VALID_CHARS, INVALID_CHARS, MIN_OCCURS, MAX_OCCURS, READ_ONLY) VALUES ('reqCompFieldType.countType', 'CLU', 'CLU','string',null,null,null,null,null,null,null,null,0)

// REQ_COMP_TYPE -> REQ_COMP_FIELD_TYPE
// Course
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.all','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.nof','reqCompFieldType.requiredCount')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.nof','reqCompFieldType.operator')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.nof','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.gradecheck','reqCompFieldType.gpa')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.grdCondCourseList','reqCompFieldType.totalCredits')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.grdCondCourseList','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.coreq.all','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.courseList.coreq.oneof','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.programList.enroll.oneof','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.programList.enroll.none','reqCompFieldType.cluSet.id')
// Programs
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.completed.nof','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.completed.nof','reqCompFieldType.requiredCount')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.notcompleted.nof','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.completed.all','reqCompFieldType.cluSet.id')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.coursecompleted.nof','reqCompFieldType.requiredCount')
INSERT INTO KSST_RCTYP_JN_RCFLDTYP (REQ_COMP_TYPE_ID,REQ_COMP_FIELD_TYPE_ID) VALUES ('kuali.reqCompType.program.programset.coursecompleted.nof','reqCompFieldType.cluSet.id')

// REQ_COMPONENT_TYPE_NL_TEMPLATE
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1', 'KUALI.CATALOG', 'Student must have completed none of $cluSet.getCluSetAsCode()', 'kuali.reqCompType.courseList.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2', 'KUALI.CATALOG', 'Student must have completed all of $cluSet.getCluSetAsCode()', 'kuali.reqCompType.courseList.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3', 'KUALI.CATALOG', '#if($expectedValue == 1 && $cluSet.getCluList().size() == 1)Student must have completed $cluSet.getCluSetAsCode()#{else}Student must have completed $expectedValue of $cluSet.getCluSetAsCode()#end', 'kuali.reqCompType.courseList.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('4', 'KUALI.CATALOG', 'Student needs a minimum GPA of $gpa', 'kuali.reqCompType.gradecheck', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('5', 'KUALI.CATALOG', 'Students must take $totalCredits credits from $cluSet.getCluSetAsCode()', 'kuali.reqCompType.grdCondCourseList', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('6', 'KUALI.CATALOG', 'Student must be enrolled in all of $cluSet.getCluSetAsCode()', 'kuali.reqCompType.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('7', 'KUALI.CATALOG', 'Student must be enrolled in one of of $cluSet.getCluSetAsCode()', 'kuali.reqCompType.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('8', 'KUALI.CATALOG', 'Restricted to students in the $cluSet.getCluSetAsCode() only', 'kuali.reqCompType.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('9', 'KUALI.CATALOG', 'Not for students in $cluSet.getCluSetAsCode()', 'kuali.reqCompType.programList.enroll.none', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('101', 'KUALI.EXAMPLE', 'Student must have completed none of MATH100, MATH101', 'kuali.reqCompType.courseList.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('102', 'KUALI.EXAMPLE', 'Student must have completed all of MATH100, MATH101, MATH102', 'kuali.reqCompType.courseList.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('103', 'KUALI.EXAMPLE', 'Student must have completed 2 of MATH100, MATH101, MATH102', 'kuali.reqCompType.courseList.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('104', 'KUALI.EXAMPLE', 'Student needs a minimum GPA of 2.5', 'kuali.reqCompType.gradecheck', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('105', 'KUALI.EXAMPLE', 'Students must take 3 credits from ENGL 100, ENGL 102 or ENGL 104', 'kuali.reqCompType.grdCondCourseList', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('106', 'KUALI.EXAMPLE', 'Student must be enrolled in all of MATH100, MATH101', 'kuali.reqCompType.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('107', 'KUALI.EXAMPLE', 'Student must be enrolled in one of MATH100, MATH101, MATH102', 'kuali.reqCompType.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('108', 'KUALI.EXAMPLE', 'Students must be in one of the following program(s): Creative Writing Major', 'kuali.reqCompType.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('109', 'KUALI.EXAMPLE', 'Not for students in a Music Major or Minor', 'kuali.reqCompType.programList.enroll.none', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('201', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.courseList.none', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('202', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.courseList.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('203', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.requiredCount;reqCompFieldLabel=Number> from <reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.courseList.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('204', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.gpa;reqCompFieldLabel=GPA>', 'kuali.reqCompType.gradecheck', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('205', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.totalCredits;reqCompFieldLabel=Credits> from <reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.grdCondCourseList', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('206', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.courseList.coreq.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('207', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.courseList.coreq.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('208', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqCompType.programList.enroll.oneof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('209', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqCompType.programList.enroll.none', 'en')

// Programs
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1001', 'KUALI.CATALOG', 'Must have successfully completed a minimum of $expectedValue $NLHelper.getProperGrammer($expectedValue, "program", "programs") from $cluSet.getCluSetAsShortName() $NLHelper.getProperGrammer($cluSet.getCluList().size(), "program", "programs")', 'kuali.reqCompType.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1002', 'KUALI.CATALOG', 'Must not have successfully completed #if($cluSet.getCluList().size() > 1)any of #end$cluSet.getCluSetAsShortName() $NLHelper.getProperGrammer($cluSet.getCluList().size(), "program", "programs")', 'kuali.reqCompType.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1003', 'KUALI.CATALOG', 'Must have successfully completed #if($cluSet.getCluList().size() != 1)all of #end$cluSet.getCluSetAsShortName() $NLHelper.getProperGrammer($cluSet.getCluList().size(), "program", "programs")', 'kuali.reqCompType.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('1004', 'KUALI.CATALOG', 'Must have successfully completed a minimum of $expectedValue $NLHelper.getProperGrammer($expectedValue, "course", "courses") from $cluSet.getCluSetAsShortName() $NLHelper.getProperGrammer($cluSet.getCluList().size(), "program", "programs")', 'kuali.reqCompType.program.programset.coursecompleted.nof', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2001', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 1 program from (Geology or  Sociology) programs', 'kuali.reqCompType.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2002', 'KUALI.EXAMPLE', 'Must not have successfully completed any of (Geology or Sociology) programs', 'kuali.reqCompType.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2003', 'KUALI.EXAMPLE', 'Must have successfully completed all of (Sociology and CORE Advanced Studies)', 'kuali.reqCompType.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('2004', 'KUALI.EXAMPLE', 'Must have successfully completed a minimum of 14 courses from ( Sociology and CORE Advanced Studies) programs', 'kuali.reqCompType.program.programset.coursecompleted.nof', 'en')

INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3001', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.requiredCount;reqCompFieldLabel=Number> from <reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqCompType.program.programset.completed.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3002', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqCompType.program.programset.notcompleted.nof', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3003', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Programs>', 'kuali.reqCompType.program.programset.completed.all', 'en')
INSERT INTO KSST_REQ_COM_TYPE_NL_TMPL (ID, NL_USUAGE_TYPE_KEY, TEMPLATE, OWNER, LANGUAGE) values ('3004', 'KUALI.COMPOSITION', '<reqCompFieldType=reqCompFieldType.requiredCount;reqCompFieldLabel=Number> from <reqCompFieldType=reqCompFieldType.cluSet.id;reqCompFieldLabel=Courses>', 'kuali.reqCompType.program.programset.coursecompleted.nof', 'en')

// STMT_TYPE <-> REQ_COM_TYPE
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.courseList.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.courseList.nof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.grdCondCourseList')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.prereqAcademicReadiness','kuali.reqCompType.gradecheck')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.coreqAcademicReadiness','kuali.reqCompType.courseList.coreq.all')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.coreqAcademicReadiness','kuali.reqCompType.courseList.coreq.oneof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.enrollAcademicReadiness','kuali.reqCompType.programList.enroll.oneof')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.enrollAcademicReadiness','kuali.reqCompType.programList.enroll.none')
INSERT INTO KSST_STMT_TYP_JN_RC_TYP (STMT_TYPE_ID,REQ_COM_TYPE_ID) values ('kuali.luStatementType.antireqAcademicReadiness','kuali.reqCompType.courseList.none')

// STMT_TYPE <-> STMT_TYPE
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.prereqAcademicReadiness')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.coreqAcademicReadiness')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.enrollAcademicReadiness')
INSERT INTO KSST_STMT_TYP_JN_STMT_TYP (STMT_TYPE_ID, CHLD_STMT_TYPE_ID) values ('kuali.luStatementType.createCourseAcademicReadiness','kuali.luStatementType.antireqAcademicReadiness')

// KSST_NL_USAGE_TYPE
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.CATALOG', 'Kuali Catalog', 'Kuali Catalog', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.EXAMPLE', 'Kuali Rule Example', 'Rule Example', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})
INSERT INTO KSST_NL_USAGE_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('KUALI.COMPOSITION', 'Kuali Rule Composition', 'Rule Composition', {ts '2010-01-01 01:01:01.0'}, {ts '2010-12-31 01:01:01.0'})

// KSST_OBJECT_TYPE
INSERT INTO KSST_OBJECT_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu', 'Kuali CLU', 'Kuali CLU', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_OBJECT_SUB_TYPE
INSERT INTO KSST_OBJECT_SUB_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('course', 'Kuali Course', 'Kuali Course', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_OBJECT_SUB_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('program', 'Kuali Program', 'Kuali Program', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_OBJ_TYP_JN_OBJ_SUB_TYP
INSERT INTO KSST_OBJ_TYP_JN_OBJ_SUB_TYP (OBJ_TYPE_ID, OBJ_SUB_TYPE_ID) VALUES ('clu', 'course')
INSERT INTO KSST_OBJ_TYP_JN_OBJ_SUB_TYP (OBJ_TYPE_ID, OBJ_SUB_TYPE_ID) VALUES ('clu', 'program')

// KSST_REF_STMT_REL_TYPE
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.prerequisites', 'CLU Prerequisites', 'CLU Prereq', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.luStatementType.createCourseAcademicReadiness', 'CLU Create Course', 'CLU Create Course', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.luStatementType.prereqAcademicReadiness', 'CLU Prerequisites', 'CLU Prerequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.luStatementType.coreqAcademicReadiness', 'CLU Corequisites', 'CLU Corequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.luStatementType.enrollAcademicReadiness', 'CLU Enroll', 'CLU Enroll', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('kuali.luStatementType.antireqAcademicReadiness', 'CLU Antirequisites', 'CLU Antirequisites', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})
INSERT INTO KSST_REF_STMT_REL_TYPE (TYPE_KEY, TYPE_DESC, NAME, EFF_DT, EXPIR_DT) VALUES ('clu.rule', 'CLU Rule', 'CLU Rule', {ts '2000-01-01 00:00:00.0'}, {ts '2100-12-31 00:00:00.0'})

// KSST_RSTMT_RTYP_JN_OSUB_TYP <-> KSST_OBJECT_SUB_TYPE
INSERT INTO KSST_RSTMT_RTYP_JN_OSUB_TYP (REF_STMT_REL_TYPE_ID, OBJ_SUB_TYPE_ID) VALUES ('clu.prerequisites', 'course')

// KSST_RSTMT_RTYP_JN_STMT_TYP <-> KSST_STMT_TYPE
INSERT INTO KSST_RSTMT_RTYP_JN_STMT_TYP (REF_STMT_REL_TYPE_ID, STMT_TYPE_ID) VALUES ('clu.prerequisites', 'kuali.luStatementType.prereqAcademicReadiness')
