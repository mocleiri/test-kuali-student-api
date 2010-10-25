DROP TABLE KSST_STMT_TYP_JN_RC_TYP;
DROP TABLE KSST_STMT_TYP_JN_STMT_TYP;

CREATE TABLE KSST_STMT_TYP_JN_RC_TYP (ID VARCHAR2(255) NOT NULL, OBJ_ID VARCHAR2(36), VER_NBR NUMBER(19), REQ_COM_TYPE_ID VARCHAR2(255), SORT_ORDER NUMBER(10), STMT_TYPE_ID VARCHAR2(255), PRIMARY KEY (ID));
CREATE TABLE KSST_STMT_TYP_JN_STMT_TYP (ID VARCHAR2(255) NOT NULL, OBJ_ID VARCHAR2(36), VER_NBR NUMBER(19), CHLD_STMT_TYPE_ID VARCHAR2(255), SORT_ORDER NUMBER(10), STMT_TYPE_ID VARCHAR2(255), PRIMARY KEY (ID));

ALTER TABLE KSST_STMT_TYP_JN_RC_TYP ADD CONSTRAINT FKEE1C67E3F942310E FOREIGN KEY (REQ_COM_TYPE_ID) REFERENCES KSST_REQ_COM_TYPE;
ALTER TABLE KSST_STMT_TYP_JN_RC_TYP ADD CONSTRAINT FKEE1C67E3BD6236BE FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSST_STMT_TYPE;
ALTER TABLE KSST_STMT_TYP_JN_STMT_TYP ADD CONSTRAINT FKA518B07A38283320 FOREIGN KEY (CHLD_STMT_TYPE_ID) REFERENCES KSST_STMT_TYPE;
ALTER TABLE KSST_STMT_TYP_JN_STMT_TYP ADD CONSTRAINT FKA518B07ABD6236BE FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSST_STMT_TYPE;
