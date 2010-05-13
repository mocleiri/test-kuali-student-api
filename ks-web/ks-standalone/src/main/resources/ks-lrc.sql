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
// RichText
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-1', '<p>Credit 1</p>', 'Credit 1')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-2', '<p>Credit 2</p>', 'Credit 2')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-3', '<p>Credit 3</p>', 'Credit 3')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-4', '<p>Credential 1</p>', 'Credential 1')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-5', '<p>Credential 2</p>', 'Credential 2')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-6', '<p>Credential 3</p>', 'Credential 3')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-7', '<p>ResultComponent 1</p>', 'ResultComponent 1')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-8', '<p>ResultComponent 2</p>', 'ResultComponent 2')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-9', '<p>ResultComponent 3</p>', 'ResultComponent 3')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-10', '<p>Grade 1</p>', 'Grade 1')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-11', '<p>Grade 2</p>', 'Grade 2')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-12', '<p>Grade 3</p>', 'Grade 3')
/
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RT-DESC-LCR-13', '<p>Scale 3</p>', 'Scale 3')
/



// Credit Type
INSERT INTO KSLU_LRC_CREDIT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credit.1', 'A Basic Credit 1', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Credit 1')
/
INSERT INTO KSLU_LRC_CREDIT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credit.2', 'A Basic Credit 2', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Credit 2')
/
INSERT INTO KSLU_LRC_CREDIT_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credit.3', 'A Basic Credit 3', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Credit 3')
/
// Grade Type
INSERT INTO KSLU_LRC_GRADE_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.grade.1', 'A Basic ResultComponent 1', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Grade 1')
/
INSERT INTO KSLU_LRC_GRADE_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.grade.2', 'A Basic ResultComponent 2', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Grade 2')
/
INSERT INTO KSLU_LRC_GRADE_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.grade.3', 'A Basic ResultComponent 3', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'Grade 3')
/

// ResultComponentType
INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) values ('resultComponentType.credential', 'A Basic ResultComponent 1', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'ResultComponent 1', 'org.kuali.student.lum.lrc.entity.Credential')
/
INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) values ('resultComponentType.credit', 'A Basic ResultComponent 2', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'ResultComponent 2', 'org.kuali.student.lum.lrc.entity.Credit')
/
INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) values ('resultComponentType.grade', 'A Basic ResultComponent 3', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'ResultComponent 3', 'org.kuali.student.lum.lrc.entity.Grade')
/

// ResultComponent
INSERT INTO KSLU_LRC_RESCOMP (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, TYPE, STATE, VERSIONIND) VALUES ('LRC-RESCOMP-1', 'ResultComponent 1', 'RT-DESC-LCR-7',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 'resultComponentType.credential', 'Active', 0)
/
INSERT INTO KSLU_LRC_RESCOMP (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, TYPE, STATE, VERSIONIND) VALUES ('LRC-RESCOMP-2', 'ResultComponent 2', 'RT-DESC-LCR-8',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 'resultComponentType.credit', 'Active', 0)
/
INSERT INTO KSLU_LRC_RESCOMP (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, TYPE, STATE, VERSIONIND) VALUES ('LRC-RESCOMP-3', 'ResultComponent 3', 'RT-DESC-LCR-9',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 'resultComponentType.grade', 'Active', 0)
/
INSERT INTO KSLU_LRC_RESCOMP (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, TYPE, STATE, VERSIONIND) VALUES ('LRC-RESCOMP-4', 'ResultComponent 4', 'RT-DESC-LCR-9',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 'resultComponentType.grade', 'Active', 0)
/

// ResultValue
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDIT-1', 'Credit 1', 'RT-DESC-LCR-1',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDIT-2', 'Credit 2', 'RT-DESC-LCR-2',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDIT-3', 'Credit 3', 'RT-DESC-LCR-3',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDIT-4', 'Credit 4', 'RT-DESC-LCR-4',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-1', 'Credential 1', 'RT-DESC-LCR-5',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-2', 'Credential 2', 'RT-DESC-LCR-6',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-3', 'Credential 3', 'RT-DESC-LCR-7',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-4', 'Credential 4', 'RT-DESC-LCR-7',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-GRADE-1', 'GRADE 1', 'RT-DESC-LCR-10',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-GRADE-2', 'GRADE 2', 'RT-DESC-LCR-11',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-GRADE-3', 'GRADE 3', 'RT-DESC-LCR-12',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-RESULT_VALUE-GRADE-4', 'GRADE 4', 'RT-DESC-LCR-12',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/

// Credit
INSERT INTO KSLU_LRC_CREDIT (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDIT-1',  'lrcType.credit.1')
/
INSERT INTO KSLU_LRC_CREDIT (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDIT-2',  'lrcType.credit.2')
/
INSERT INTO KSLU_LRC_CREDIT (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDIT-3',  'lrcType.credit.3')
/
INSERT INTO KSLU_LRC_CREDIT (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDIT-4',  'lrcType.credit.3')
/

// Credit Attribute
INSERT INTO KSLU_LRC_CREDIT_ATTR (ID, ATTR_NAME, ATTR_VALUE, OWNER) VALUES('LRC-CREDIT-ATTR-1', 'Credit Attr 1', 'Credit Attr Value 1', 'LRC-RESULT_VALUE-CREDIT-1')
/
INSERT INTO KSLU_LRC_CREDIT_ATTR (ID, ATTR_NAME, ATTR_VALUE, OWNER) VALUES('LRC-CREDIT-ATTR-2', 'Credit Attr 2', 'Credit Attr Value 2', 'LRC-RESULT_VALUE-CREDIT-1')
/

// Credential Type
INSERT INTO KSLU_LRC_CREDENTIAL_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credential.1', 'A Basic credential 1', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'credential 1')
/
INSERT INTO KSLU_LRC_CREDENTIAL_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credential.2', 'A Basic credential 2', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'credential 2')
/
INSERT INTO KSLU_LRC_CREDENTIAL_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('lrcType.credential.3', 'A Basic credential 3', {ts '2000-01-01 00:00:00.0'}, {ts '2000-12-31 00:00:00.0'}, 'credential 3')
/

// Credential
INSERT INTO KSLU_LRC_CREDENTIAL (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-1', 'lrcType.credential.1')
/
INSERT INTO KSLU_LRC_CREDENTIAL (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-2', 'lrcType.credential.2')
/
INSERT INTO KSLU_LRC_CREDENTIAL (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-3', 'lrcType.credential.3')
/
INSERT INTO KSLU_LRC_CREDENTIAL (ID, TYPE) VALUES ('LRC-RESULT_VALUE-CREDENTIAL-4', 'lrcType.credential.3')
/

// Scale
INSERT INTO KSLU_LRC_SCALE(ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-SCALE-1', 'Scale 1', 'RT-DESC-LCR-13',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_SCALE(ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-SCALE-2', 'Scale 3', 'RT-DESC-LCR-13',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/
INSERT INTO KSLU_LRC_SCALE(ID, NAME, RT_DESCR_ID, EFF_DT, EXPIR_DT, VERSIONIND) VALUES ('LRC-SCALE-3', 'Scale 3', 'RT-DESC-LCR-13',  {ts '2000-01-01 00:00:00.0'},  {ts '2000-12-31 00:00:00.0'}, 0)
/

// Grade
INSERT INTO KSLU_LRC_GRADE (ID, SCALE_KEY, RANK, TYPE) VALUES ('LRC-RESULT_VALUE-GRADE-1','LRC-SCALE-1', 'Rank 1',  'lrcType.grade.1')
/
INSERT INTO KSLU_LRC_GRADE (ID, SCALE_KEY, RANK, TYPE) VALUES ('LRC-RESULT_VALUE-GRADE-2','LRC-SCALE-2', 'Rank 2', 'lrcType.grade.2')
/
INSERT INTO KSLU_LRC_GRADE (ID, SCALE_KEY, RANK, TYPE) VALUES ('LRC-RESULT_VALUE-GRADE-3','LRC-SCALE-3', 'Rank 3', 'lrcType.grade.3')
/
INSERT INTO KSLU_LRC_GRADE (ID, SCALE_KEY, RANK, TYPE) VALUES ('LRC-RESULT_VALUE-GRADE-4','LRC-SCALE-1', 'Rank 4', 'lrcType.grade.3')
/

//
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-1', 'LRC-RESULT_VALUE-CREDIT-1')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-1', 'LRC-RESULT_VALUE-CREDIT-2')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-2', 'LRC-RESULT_VALUE-CREDENTIAL-1')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-2', 'LRC-RESULT_VALUE-CREDENTIAL-2')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-3', 'LRC-RESULT_VALUE-GRADE-1')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-1', 'LRC-RESULT_VALUE-GRADE-1')
/
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('LRC-RESCOMP-3', 'LRC-RESULT_VALUE-GRADE-2')
/

//------------------------------------------------------------------------------

INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) VALUES ('kuali.resultComponentType.grade.finalGrade', 'This records that a final grade is a result for this course', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Final Grade', NULL);
INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) VALUES ('kuali.resultComponentType.credit.degree.fixed', 'This records a single fixed number of credits that are awarded if the student passes the course.', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Fixed Number', NULL);
INSERT INTO KSLU_LRC_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME, VALUE_TYPE) VALUES ('kuali.resultComponentType.credit.degree.range', 'This records a range of number of credits that can be awarded for this course.', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Range of Numbers', NULL);

INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-1' ,'<p>Pass or Fail</p>'                                        , 'Pass or Fail');                                        
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-2' ,'<p>A Letter used as a grade (e.g. A, B, C, D, F)</p>'       , 'A Letter used as a grade (e.g. A, B, C, D, F)');        
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-3' ,'<p>0 units of academic credit</p>'                          , '0 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-4' ,'<p>1 unit of academic credit</p>'                           , '1 unit of academic credit');                            
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-5' ,'<p>2 units of academic credit</p>'                          , '2 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-6' ,'<p>3 units of academic credit</p>'                          , '3 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-7' ,'<p>4 units of academic credit</p>'                          , '4 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-8' ,'<p>5 units of academic credit</p>'                          , '5 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-9' ,'<p>6 units of academic credit</p>'                          , '6 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-10','<p>7 units of academic credit</p>'                          , '7 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-11','<p>8 units of academic credit</p>'                          , '8 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-12','<p>9 units of academic credit</p>'                          , '9 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-13','<p>10 units of academic credit</p>'                         , '10 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-14','<p>11 units of academic credit</p>'                         , '11 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-15','<p>12 units of academic credit</p>'                         , '12 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-16','<p>13 units of academic credit</p>'                         , '13 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-17','<p>14 units of academic credit</p>'                         , '14 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-18','<p>15 units of academic credit</p>'                         , '15 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-19','<p>16 units of academic credit</p>'                         , '16 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-20','<p>17 units of academic credit</p>'                         , '17 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-21','<p>18 units of academic credit</p>'                         , '18 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-22','<p>19 units of academic credit</p>'                         , '19 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-23','<p>20 units of academic credit</p>'                         , '20 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-24','<p>21 units of academic credit</p>'                         , '21 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-25','<p>22 units of academic credit</p>'                         , '22 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-26','<p>23 units of academic credit</p>'                         , '23 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-27','<p>24 units of academic credit</p>'                         , '24 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-28','<p>25 units of academic credit</p>'                         , '25 units of academic credit');                          
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-29','<p>A range of 1 through 5 academic credits, inclusive</p>'  , 'A range of 1 through 5 academic credits, inclusive');  
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-30','<p>A range of 1 through 10 academic credits, inclusive</p>' , 'A range of 1 through 10 academic credits, inclusive');  
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-31','<p>A range of 5 through 10 academic credits, inclusive</p>' , 'A range of 5 through 10 academic credits, inclusive');  
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-32','<p>A range of 10 through 15 academic credits, inclusive</p>', 'A range of 10 through 15 academic credits, inclusive');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RC-33','<p>A range of 1 through 20 academic credits, inclusive</p>' , 'A range of 1 through 20 academic credits, inclusive');  

INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.resultComponent.grade.passFail'                             , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Pass-Fail'    , NULL, 'RICHTEXT-LRC-RC-1' , 'kuali.resultComponentType.grade.finalGrade');
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.resultComponent.grade.letter'                               , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Letter'       , NULL, 'RICHTEXT-LRC-RC-2' , 'kuali.resultComponentType.grade.finalGrade');
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.0'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '0 Credit'     , NULL, 'RICHTEXT-LRC-RC-3' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.1'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '1 Credit'     , NULL, 'RICHTEXT-LRC-RC-4' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.2'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '2 Credits'    , NULL, 'RICHTEXT-LRC-RC-5' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.3'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '3 Credits'    , NULL, 'RICHTEXT-LRC-RC-6' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.4'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '4 Credits'    , NULL, 'RICHTEXT-LRC-RC-7' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.5'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '5 Credits'    , NULL, 'RICHTEXT-LRC-RC-8' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.6'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '6 Credits'    , NULL, 'RICHTEXT-LRC-RC-9' , 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.7'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '7 Credits'    , NULL, 'RICHTEXT-LRC-RC-10', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.8'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '8 Credits'    , NULL, 'RICHTEXT-LRC-RC-11', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.9'                                 , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '9 Credits'    , NULL, 'RICHTEXT-LRC-RC-12', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.10'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '10 Credits'   , NULL, 'RICHTEXT-LRC-RC-13', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.11'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '11 Credits'   , NULL, 'RICHTEXT-LRC-RC-14', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.12'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '12 Credits'   , NULL, 'RICHTEXT-LRC-RC-15', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.13'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '13 Credits'   , NULL, 'RICHTEXT-LRC-RC-16', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.14'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '14 Credits'   , NULL, 'RICHTEXT-LRC-RC-17', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.15'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '15 Credits'   , NULL, 'RICHTEXT-LRC-RC-18', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.16'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '16 Credits'   , NULL, 'RICHTEXT-LRC-RC-19', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.17'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '17 Credits'   , NULL, 'RICHTEXT-LRC-RC-20', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.18'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '18 Credits'   , NULL, 'RICHTEXT-LRC-RC-21', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.19'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '19 Credits'   , NULL, 'RICHTEXT-LRC-RC-22', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.20'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '20 Credits'   , NULL, 'RICHTEXT-LRC-RC-23', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.21'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '21 Credits'   , NULL, 'RICHTEXT-LRC-RC-24', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.22'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '22 Credits'   , NULL, 'RICHTEXT-LRC-RC-25', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.23'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '23 Credits'   , NULL, 'RICHTEXT-LRC-RC-26', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.24'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '24 Credits'   , NULL, 'RICHTEXT-LRC-RC-27', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.25'                                , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '25 Credits'   , NULL, 'RICHTEXT-LRC-RC-28', 'kuali.resultComponentType.credit.degree.fixed');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.1, plus each of those for 2 - 5'   , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '1-5 Credits'  , NULL, 'RICHTEXT-LRC-RC-29', 'kuali.resultComponentType.credit.degree.range');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.1, plus each of those for 2 - 10'  , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '1-10 Credits' , NULL, 'RICHTEXT-LRC-RC-30', 'kuali.resultComponentType.credit.degree.range');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.5, plus each of those for 6 - 10'  , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '5-10 Credits' , NULL, 'RICHTEXT-LRC-RC-31', 'kuali.resultComponentType.credit.degree.range');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.10, plus each of those for 11 - 15', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '10-15 Credits', NULL, 'RICHTEXT-LRC-RC-32', 'kuali.resultComponentType.credit.degree.range');    
INSERT INTO KSLU_LRC_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) VALUES ('kuali.creditType.credit.degree.1, plus each of those for 2 - 20'  , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, '1-20 Credits' , NULL, 'RICHTEXT-LRC-RC-33', 'kuali.resultComponentType.credit.degree.range');    

INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-1', '<p>Final Grade of A</p>', 'Final Grade of A');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-2', '<p>Final Grade of B</p>', 'Final Grade of B');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-3', '<p>Final Grade of C</p>', 'Final Grade of C');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-4', '<p>Final Grade of D</p>', 'Final Grade of D');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-5', '<p>Final Grade of F</p>', 'Final Grade of F');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-6', '<p>Final Grade of P</p>', 'Final Grade of P');
INSERT INTO KSLU_LRC_RICH_TEXT_T (ID, FORMATTED, PLAIN) VALUES ('RICHTEXT-LRC-RV-7', '<p>Final Grade of F</p>', 'Final Grade of F');

INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.grade.letter.A', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of A', 'A', 'RICHTEXT-LRC-RV-1');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.grade.letter.B', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of B', 'B', 'RICHTEXT-LRC-RV-2');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.grade.letter.C', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of C', 'C', 'RICHTEXT-LRC-RV-3');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.grade.letter.D', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of D', 'D', 'RICHTEXT-LRC-RV-4');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.grade.letter.F', NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of F', 'F', 'RICHTEXT-LRC-RV-5');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.passFail.P'    , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of P', 'P', 'RICHTEXT-LRC-RV-6');
INSERT INTO KSLU_LRC_RESULT_VALUE (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, VALUE, RT_DESCR_ID) VALUES ('kuali.resultValue.passFail.F'    , NULL, NULL, NULL, NULL, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'}, 'Grade of F', 'F', 'RICHTEXT-LRC-RV-7');

INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.passFail', 'kuali.resultValue.passFail.P')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.passFail', 'kuali.resultValue.passFail.F')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.letter', 'kuali.resultValue.grade.letter.A')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.letter', 'kuali.resultValue.grade.letter.B')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.letter', 'kuali.resultValue.grade.letter.C')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.letter', 'kuali.resultValue.grade.letter.D')
INSERT INTO KSLU_LRC_RESCOMP_JN_RESVALUE (COMPONENT_ID, RESULT_ID) VALUES('kuali.resultComponent.grade.letter', 'kuali.resultValue.grade.letter.F')

