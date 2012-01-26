//StateProcessEntity
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.atp.process', 'kuali.atp.process', 'kuali atp state process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.milestone.process', 'kuali.milestone.process', 'kuali milestone state process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.atp.atp.relation.process', 'kuali.atp.atp.relation.process', 'kuali atp-atp relation state process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.atp.milestone.relation.process', 'kuali.atp.milestone.relation.process', 'kuali atp-milestone relation state process', 0)

//HOLD Process
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.hold.restriction.process', 'kuali.hold.restriction.process', 'kuali hold restriction process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.hold.issue.process', 'kuali.hold.issue.process', 'kuali hold issue process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.hold.process.student', 'kuali.hold.process.student', 'kuali hold process student', 0)

//LUI Process
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.course.offering.process', 'kuali.course.offering.process', 'kuali course offering process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.lui.capacity.process', 'kuali.lui.capacity.process', 'kuali lui capacity process', 0)
INSERT INTO KSEN_STATE_PROCESS (ID, NAME, DESCR, VER_NBR) VALUES ('kuali.lui.lui.relationship.process', 'kuali.lui.lui.relationship.process', 'kuali lui-lui relationship process', 0)

//StateEntity
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.state.Draft', 'Draft', 'kuali.atp.process', 'Indicates that this Time Period is just tentative', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.state.Official', 'Official', 'kuali.atp.process', 'Indicates that this Time Period has been established', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.milestone.state.Draft', 'Draft', 'kuali.milestone.process', 'Indicates that this milestone is just tentative', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.milestone.state.Official', 'Official', 'kuali.milestone.process', 'Indicates that this milestone has been established', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.atp.relation.state.active', 'Active', 'kuali.atp.atp.relation.process', 'Indicates that this Atp-Atp relation is active', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.atp.relation.state.inactive', 'Inactive', 'kuali.atp.atp.relation.process', 'Indicates that this Atp-Atp relation is inactive', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.milestone.relation.state.active', 'Active', 'kuali.atp.milestone.relation.process', 'Indicates that this Atp-Milestone relation is active', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.atp.milestone.relation.state.inactive', 'Inactive', 'kuali.atp.milestone.relation.process', 'Indicates that this Atp-Milestone relation is inactive', 0)
//HOLD States
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.restriction.state.active', 'Active', 'kuali.hold.restriction.process', 'This restriction is active and should be enforced', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.restriction.state.inactive', 'Inactive', 'kuali.hold.restriction.process', 'The restriction is inactive and should not be enforced', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.issue.state.active', 'Active', 'kuali.hold.issue.process', 'This issue is active and can be attached to holds ', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.issue.state.inactive', 'Inactive', 'kuali.hold.issue.process', 'The hold is inactive an cannot be attached to new holds ', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.state.active', 'Active', 'kuali.hold.process.student', 'This hold is active and should be enforced', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.state.released', 'Released', 'kuali.hold.process.student', 'The hold was active and the issue has been resolved', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.hold.state.canceled', 'Canceled', 'kuali.hold.process.student', 'The hold was canceled or removed because it was originally put on in error', 0)
//LUI states
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.lui.state.draft', 'Draft', 'kuali.course.offering.process', 'Proposed for Departmental Planning purposed', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.lui.state.submitted', 'Submitted', 'kuali.course.offering.process', 'Submitted to central admin for approval (or scheduling)', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.lui.state.approved', 'Approved', 'kuali.course.offering.process', 'Approved and ready to be scheduled', 0)

INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.lui.lui.relation.state.active', 'Active', 'kuali.lui.lui.relationship.process', 'The relationship between the two LUIs is active ', 0)
INSERT INTO KSEN_COMM_STATE (ID, NAME, PROCESS_KEY, DESCR, VER_NBR) VALUES ('kuali.lui.lui.relation.state.inactive', 'Inactive', 'kuali.lui.lui.relationship.process', 'The relationship between the two LUIs is in-active ', 0)

//StateProcessRelationEntity
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-1', 'kuali.atp.process', null, 'kuali.atp.state.Draft', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-2', 'kuali.atp.process', 'kuali.atp.state.Draft', 'kuali.atp.state.Official', 0)

//HOLD StateProcessRelation
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-3', 'kuali.hold.restriction.process', null, 'kuali.hold.restriction.state.active', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-4', 'kuali.hold.restriction.process', 'kuali.hold.restriction.state.active', 'kuali.hold.restriction.state.inactive', 0)

INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-5', 'kuali.hold.issue.process', null, 'kuali.hold.issue.state.active', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-6', 'kuali.hold.issue.process', 'kuali.hold.issue.state.active', 'kuali.hold.issue.state.inactive', 0)

INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-7', 'kuali.hold.process.student', null, 'kuali.hold.state.active', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-8', 'kuali.hold.process.student', 'kuali.hold.state.active', 'kuali.hold.state.released', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-9', 'kuali.hold.process.student', 'kuali.hold.state.released', 'kuali.hold.state.canceled', 0)

INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-10', 'kuali.course.offering.process', null, 'kuali.lui.state.draft', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-11', 'kuali.course.offering.process', 'kuali.lui.state.draft', 'kuali.lui.state.submitted', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-12', 'kuali.course.offering.process', 'kuali.lui.state.submitted', 'kuali.lui.state.approved', 0)

INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-13', 'kuali.lui.lui.relationship.process', null, 'kuali.lui.lui.relation.state.active', 0)
INSERT INTO KSEN_STATEPROCESS_RELTN(ID, PROCESS_KEY, PRIOR_STATEKEY, NEXT_STATEKEY, VER_NBR)VALUES('PROCESS-14', 'kuali.lui.lui.relationship.process', 'kuali.lui.lui.relation.state.active', 'kuali.lui.lui.relation.state.inactive', 0)

//AtpTypeEntity
INSERT INTO KSEN_ATP_TYPE(TYPE_KEY, NAME, TYPE_DESC, REF_OBJECT_URI, VER_NBR) VALUES ('kuali.atp.type.AcademicCalendar', 'Academic Calendar', 'Academic Calendar', 'http://student.kuali.org/wsdl/atp/AtpInfo', 0)
INSERT INTO KSEN_ATP_TYPE(TYPE_KEY, NAME, TYPE_DESC, REF_OBJECT_URI, VER_NBR) VALUES ('kuali.atp.type.HolidayCalendar', 'Holiday Calendar', 'Holiday Calendar', 'http://student.kuali.org/wsdl/atp/AtpInfo', 0)
INSERT INTO KSEN_ATP_TYPE(TYPE_KEY, NAME, TYPE_DESC, REF_OBJECT_URI, VER_NBR) VALUES ('kuali.atp.type.Fall', 'Fall', 'Fall Semester', 'http://student.kuali.org/wsdl/atp/AtpInfo', 0)
INSERT INTO KSEN_ATP_TYPE(TYPE_KEY, NAME, TYPE_DESC, REF_OBJECT_URI, VER_NBR) VALUES ('kuali.atp.type.Spring', 'Spring', 'Spring Semester', 'http://student.kuali.org/wsdl/atp/AtpInfo', 0)

// TypeTypeRelationEntity - TODO: move to object-URI-neutral sql file
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.1', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Fall' , 0, 'kuali.atp.type.Fall is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.2', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.HalfFall1' , 0, 'kuali.atp.type.HalfFall1 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.3', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.HalfFall2' , 0, 'kuali.atp.type.HalfFall2 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.4', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.HalfSpring1' , 0, 'kuali.atp.type.HalfSpring1 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.5', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.HalfSpring2' , 0, 'kuali.atp.type.HalfSpring2 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.6', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Mini-mester1A' , 0, 'kuali.atp.type.Mini-mester1A is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.7', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Mini-mester1B' , 0, 'kuali.atp.type.Mini-mester1B is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.8', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Mini-mester2C' , 0, 'kuali.atp.type.Mini-mester2C is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.9', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Mini-mester2D' , 0, 'kuali.atp.type.Mini-mester2D is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.10', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Session1' , 0, 'kuali.atp.type.Session1 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.11', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Session2' , 0, 'kuali.atp.type.Session2 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.12', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.SessionG1' , 0, 'kuali.atp.type.SessionG1 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.13', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.SessionG2' , 0, 'kuali.atp.type.SessionG2 is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.14', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Spring' , 0, 'kuali.atp.type.Spring is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.15', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.SpringBreak' , 0, 'kuali.atp.type.SpringBreak is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.16', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Summer' , 0, 'kuali.atp.type.Summer is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.17', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.SummerEve' , 0, 'kuali.atp.type.SummerEve is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.18', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Winter' , 0, 'kuali.atp.type.Winter is a type of Term')
INSERT INTO KSEN_TYPETYPE_RELTN(ID, VER_NBR, EFF_DT, EXPIR_DT, TYPETYPE_RELATION_TYPE, OWNER_TYPE_ID, RELATED_TYPE_ID, RANK, NAME) values ('kuali.termtype.group.member.19', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.type.type.relation.type.group', 'kuali.atp.type.group.term', 'kuali.atp.type.Adhoc' , 0, 'kuali.atp.type.Adhoc is a type of Term')

//Term
INSERT INTO KSEN_RICH_TEXT_T (ID, FORMATTED, PLAIN,VER_NBR) VALUES ('RICHTEXT-101', '<p>Desc 101</p>', 'Desc 101',0)

//LuiTypeEntity
INSERT INTO KSEN_LUI_TYPE(TYPE_KEY, NAME, TYPE_DESC, VER_NBR)VALUES ('kuali.lui.type.course.offering', 'Course Offering', 'An offering of a course for a particular term', 0)
INSERT INTO KSEN_LUI_TYPE(TYPE_KEY, NAME, TYPE_DESC, VER_NBR)VALUES ('kuali.lui.type.registration.group', 'registration Group', 'The collection of activity offerings of a single course that are grouped together for registration purposes', 0)
INSERT INTO KSEN_LUI_TYPE(TYPE_KEY, NAME, TYPE_DESC, VER_NBR)VALUES ('kuali.lui.type.activity.offering.lecture', 'Lecture', 'Instructor presentation of course materials', 0)
INSERT INTO KSEN_LUI_TYPE(TYPE_KEY, NAME, TYPE_DESC, VER_NBR)VALUES ('kuali.lui.type.activity.offering.lab', 'Lab', 'Student working on projects in a defined laboratory space. Instructors are on-hand for students to ask questions and guidance', 0)

INSERT INTO KSEN_LUI_TYPE(TYPE_KEY, NAME, TYPE_DESC, VER_NBR)VALUES ('kuali.lui.lui.relation.associated', 'Associated', 'The first LUI is associated with the second LUI', 0)

// LuiRichTextEntity
INSERT INTO KSEN_LUI_RICH_TEXT (ID, FORMATTED, PLAIN, VER_NBR) VALUES ('Lui-1-Desc', '<p>Lui Desc 101</p>', 'Lui Desc 101',0)
INSERT INTO KSEN_LUI_RICH_TEXT (ID, FORMATTED, PLAIN, VER_NBR) VALUES ('Lui-2-Desc', '<p>Lui Desc 201</p>', 'Lui Desc 201',0)
INSERT INTO KSEN_LUI_RICH_TEXT (ID, FORMATTED, PLAIN, VER_NBR) VALUES ('Lui-3-Desc', '<p>Lui Desc 301</p>', 'Lui Desc 301 for deletion',0)
INSERT INTO KSEN_LUI_RICH_TEXT (ID, FORMATTED, PLAIN, VER_NBR) VALUES ('Lui-4-Desc', '<p>Lui Desc 401</p>', 'Lui Desc 401 for deletion',0)
INSERT INTO KSEN_LUI_RICH_TEXT (ID, FORMATTED, PLAIN, VER_NBR) VALUES ('Lui-5-Desc', '<p>Lui Desc 501</p>', 'Lui Desc 501',0)

//LuiInstructorEntity
INSERT INTO KSEN_LUI_INSTR (ID, ORG_ID, PERS_ID, PERS_OVRID, PERCT_EFFT, VER_NBR) VALUES ('LUI-INSTR-1', 'Org-1', 'Pers-1', 'Instr-1', 30.5, 0)

// LuiIdentifierEntity
INSERT INTO KSEN_LUI_IDENT (ID, CD, DIVISION, SUFX_CD, LNG_NAME, SHRT_NAME, ST, TYPE, VARTN, VER_NBR) VALUES ('LUI-IDENT-1', 'CHEM123', 'CHEM', '123', 'Chemistry 123', 'Chem 123', null, null, null, 0)

//LuiEntity
INSERT INTO KSEN_LUI (ID, NAME, CLU_ID, TYPE_ID, STATE_ID, RT_DESCR_ID, EFF_DT, EXP_DT, OFFIC_LUI_ID, VER_NBR) VALUES ('Lui-1', 'Lui one',  'cluId1', 'kuali.lui.type.course.offering', 'kuali.lui.state.draft', 'Lui-1-Desc', {ts '2011-01-01 00:00:00.0'}, {ts '2011-12-31 00:00:00.0'}, 'LUI-IDENT-1', 0)
INSERT INTO KSEN_LUI (ID, NAME, CLU_ID, TYPE_ID, STATE_ID, RT_DESCR_ID, EFF_DT, EXP_DT, VER_NBR) VALUES ('Lui-2', 'Lui rwo',  'cluId2', 'kuali.lui.type.activity.offering.lecture', 'kuali.lui.state.draft', 'Lui-2-Desc', {ts '2011-01-01 00:00:00.0'}, {ts '2011-12-31 00:00:00.0'}, 0)
INSERT INTO KSEN_LUI (ID, NAME, CLU_ID, TYPE_ID, STATE_ID, RT_DESCR_ID, EFF_DT, EXP_DT, VER_NBR) VALUES ('Lui-3', 'Lui three', 'cluId3', 'kuali.lui.type.course.offering', 'kuali.lui.state.draft', 'Lui-3-Desc', {ts '2011-01-01 00:00:00.0'}, {ts '2011-12-31 00:00:00.0'}, 0)
INSERT INTO KSEN_LUI (ID, NAME, CLU_ID, TYPE_ID, STATE_ID, RT_DESCR_ID, EFF_DT, EXP_DT, VER_NBR) VALUES ('Lui-4', 'Lui four',  'cluId4', 'kuali.lui.type.activity.offering.lecture', 'kuali.lui.state.draft', 'Lui-4-Desc', {ts '2011-01-01 00:00:00.0'}, {ts '2011-12-31 00:00:00.0'}, 0)
INSERT INTO KSEN_LUI (ID, NAME, CLU_ID, TYPE_ID, STATE_ID, RT_DESCR_ID, EFF_DT, EXP_DT, VER_NBR) VALUES ('Lui-5', 'Lui five',  'cluId5', 'kuali.lui.type.activity.offering.lab', 'kuali.lui.state.draft', 'Lui-5-Desc', {ts '2011-01-01 00:00:00.0'}, {ts '2011-12-31 00:00:00.0'}, 0)

//LuiLuiRelationEntity
INSERT INTO KSEN_LUILUI_RELTN(ID, VER_NBR, EFF_DT, EXP_DT, STATE_ID, LUI_ID, TYPE_ID, RELATED_LUI_ID) VALUES ('LUILUIREL-1', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.lui.lui.relation.state.active', 'Lui-1', 'kuali.lui.lui.relation.associated', 'Lui-2')
INSERT INTO KSEN_LUILUI_RELTN(ID, VER_NBR, EFF_DT, EXP_DT, STATE_ID, LUI_ID, TYPE_ID, RELATED_LUI_ID) VALUES ('LUILUIREL-2', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.lui.lui.relation.state.active', 'Lui-3', 'kuali.lui.lui.relation.associated', 'Lui-4')
INSERT INTO KSEN_LUILUI_RELTN(ID, VER_NBR, EFF_DT, EXP_DT, STATE_ID, LUI_ID, TYPE_ID, RELATED_LUI_ID) VALUES ('LUILUIREL-3', 0, {ts '2011-01-01 00:00:00.0'}, {ts '2100-01-01 00:00:00.0'}, 'kuali.lui.lui.relation.state.active', 'Lui-5', 'kuali.lui.lui.relation.associated', 'Lui-2')
