INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,DOC_TYP_DESC,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_HDLR_URL,HELP_DEF_URL,DOC_SEARCH_HELP_URL,POST_PRCSR,GRP_ID,BLNKT_APPR_GRP_ID,BLNKT_APPR_PLCY,RPT_GRP_ID,RTE_VER_NBR,NOTIFY_ADDR,SEC_XML,EMAIL_XSL,APPL_ID,OBJ_ID,VER_NBR) VALUES ('3020','2681','AcademicCalendarWrapperMaintenanceDocument','0','true','true','New Academic Calendar','Academic Calendar Maintenance Document','','','${application.url}/spring/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class2.acal.dto.AcademicCalendarWrapper','','','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','1','1','','','2','','','','','97a4a467-5403-4d6d-922b-6c35c91de521','1')
/
INSERT INTO KREW_RTE_NODE_T (RTE_NODE_ID,DOC_TYP_ID,NM,TYP,RTE_MTHD_NM,FNL_APRVR_IND,MNDTRY_RTE_IND,GRP_ID,RTE_MTHD_CD,ACTVN_TYP,BRCH_PROTO_ID,NEXT_DOC_STAT,VER_NBR) VALUES ('2965','3020','Initiated','org.kuali.rice.kew.engine.node.InitialNode','','false','false','1','','P','','','1')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,KEY_CD,VAL) VALUES ('2626','2965','contentFragment','<start name="Initiated">
<activationType>P</activationType>
<mandatoryRoute>false</mandatoryRoute>
<finalApproval>false</finalApproval>
</start>
')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,KEY_CD,VAL) VALUES ('2627','2965','activationType','P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,KEY_CD,VAL) VALUES ('2628','2965','mandatoryRoute','false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,KEY_CD,VAL) VALUES ('2629','2965','finalApproval','false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,KEY_CD,VAL) VALUES ('2630','2965','ruleSelector','Template')
/
INSERT INTO KREW_DOC_TYP_PROC_T (DOC_TYP_PROC_ID,DOC_TYP_ID,INIT_RTE_NODE_ID,NM,INIT_IND,VER_NBR) VALUES ('2966','3020','2965','PRIMARY','true','1')
/
UPDATE KREW_DOC_TYP_T SET PARNT_ID='2680',DOC_TYP_NM='RiceDocument',DOC_TYP_VER_NBR='0',ACTV_IND='true',CUR_IND='true',DOC_TYP_DESC='Parent Document Type for all Rice Documents',LBL='Rice Document',PREV_DOC_TYP_VER_NBR='',DOC_HDR_ID='',DOC_HDLR_URL='',HELP_DEF_URL='',DOC_SEARCH_HELP_URL='',POST_PRCSR='',GRP_ID='',BLNKT_APPR_GRP_ID='',BLNKT_APPR_PLCY='',RPT_GRP_ID='',RTE_VER_NBR='2',NOTIFY_ADDR='',SEC_XML='',EMAIL_XSL='',APPL_ID='',OBJ_ID='6166CBA1BA82644DE0404F8189D86C09',VER_NBR='3' WHERE DOC_TYP_ID = '2681'  AND VER_NBR = '2'
/