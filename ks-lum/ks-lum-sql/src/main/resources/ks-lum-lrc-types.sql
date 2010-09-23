insert into KSLR_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('kuali.resultComponentType.credit.degree.fixed', 'This records a single fixed number of credits that are awarded if the student passes the course.', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Credits, Fixed');
insert into KSLR_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('kuali.resultComponentType.credit.degree.multiple', 'This records multiple numbers of credits that can be awarded for this course.', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Credits, Multiple');
insert into KSLR_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('kuali.resultComponentType.credit.degree.range', 'This records a range of number of credits that can be awarded for this course.', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Credits, Variable');
insert into KSLR_RESCOMP_TYPE (TYPE_KEY, TYPE_DESC, EFF_DT, EXPIR_DT, NAME) values ('kuali.resultComponentType.grade.finalGrade', 'This records that a final grade is a result for this course', {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Final Grade');

insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-100', null, 'Letter, satisfactory');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-101', null, 'This course will have a student selectable pass/fail option');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-102', null, 'Percentage');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-103', null, 'Recital review');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-104', null, 'Design review');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-105', null, 'Completed notation');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-106', null, 'This course can be audited');
insert into KSLR_RICH_TEXT_T (ID, FORMATTED, PLAIN) values ('RICHTEXT-LRC-RC-107', null, 'Satisfactory/unsatisfactory');

insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.audit', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Audit', null, 'RICHTEXT-LRC-RC-106', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.completedNotation', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Completed notation', null, 'RICHTEXT-LRC-RC-105', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.designReview', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Design review', null, 'RICHTEXT-LRC-RC-104', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.letter', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Letter', null, 'RICHTEXT-LRC-RC-100', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.passFail', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Pass-Fail', null, 'RICHTEXT-LRC-RC-101', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.percentage', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Percentage', null, 'RICHTEXT-LRC-RC-102', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.recitalReview', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Recital review', null, 'RICHTEXT-LRC-RC-103', 'kuali.resultComponentType.grade.finalGrade');
insert into KSLR_RESCOMP (ID, CREATEID, CREATETIME, UPDATEID, UPDATETIME, VERSIONIND, EFF_DT, EXPIR_DT, NAME, STATE, RT_DESCR_ID, TYPE) values ('kuali.resultComponent.grade.satisfactory', null, null, null, null, 1, {ts '2000-01-01 00:00:00.0'}, {ts '2020-01-01 00:00:00.0'},'Satisfactory/unsatisfactory', null, 'RICHTEXT-LRC-RC-107', 'kuali.resultComponentType.grade.finalGrade');