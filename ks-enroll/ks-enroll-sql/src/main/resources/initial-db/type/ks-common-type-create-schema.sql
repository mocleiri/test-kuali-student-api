
CREATE TABLE KSEN_TYPE
(
	TYPE_KEY             VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	NAME                 VARCHAR2(255) NOT NULL ,
	DESCR_PLAIN          VARCHAR2(4000) NOT NULL ,
	DESCR_FORMATTED      VARCHAR2(4000) NULL ,
	EFF_DT               TIMESTAMP(6) NULL ,
	EXPIR_DT             TIMESTAMP(6) NULL ,
	REF_OBJECT_URI       VARCHAR2(255) NULL ,
	VER_NBR              NUMBER(19) NULL ,
	CREATETIME           TIMESTAMP(6) NULL ,
	CREATEID             VARCHAR2(255) NULL ,
	UPDATETIME           TIMESTAMP(6) NULL ,
	UPDATEID             VARCHAR2(255) NULL 
)/



CREATE UNIQUE INDEX KSEN_TYPE_P ON KSEN_TYPE
(TYPE_KEY   ASC)/


CREATE  INDEX KSEN_TYPE_I1 ON KSEN_TYPE
(REF_OBJECT_URI   ASC)/



CREATE TABLE KSEN_TYPE_ATTR
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	ATTR_KEY             VARCHAR2(255) NULL ,
	ATTR_VALUE           VARCHAR2(4000) NULL ,
	OWNER_ID             VARCHAR2(255) NULL 
)/



CREATE UNIQUE INDEX KSEN_TYPE_ATTR_P ON KSEN_TYPE_ATTR
(ID   ASC)/



ALTER TABLE KSEN_TYPE_ATTR
	ADD CONSTRAINT  KSEN_TYPE_ATTR_P PRIMARY KEY (ID)/



CREATE  INDEX KSEN_TYPE_ATTR_IF1 ON KSEN_TYPE_ATTR
(OWNER_ID   ASC)/



CREATE TABLE KSEN_TYPETYPE_RELTN
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	TYPETYPE_RELTN_TYPE  VARCHAR2(255) NOT NULL ,
	TYPETYPE_RELTN_STATE VARCHAR2(255) NOT NULL ,
	EFF_DT               TIMESTAMP(6) NULL ,
	EXPIR_DT             TIMESTAMP(6) NULL ,
	OWNER_TYPE_ID        VARCHAR2(255) NOT NULL ,
	RELATED_TYPE_ID      VARCHAR2(255) NOT NULL ,
	RANK                 NUMBER(10) NULL ,
	VER_NBR              NUMBER(19) NULL ,
	CREATETIME           TIMESTAMP(6) NULL ,
	CREATEID             VARCHAR2(255) NULL ,
	UPDATETIME           TIMESTAMP(6) NULL ,
	UPDATEID             VARCHAR2(255) NULL 
)/



CREATE UNIQUE INDEX KSEN_TYPETYPE_RELTN_P ON KSEN_TYPETYPE_RELTN
(ID   ASC)/



CREATE  INDEX KSEN_TYPETYPE_RELTN_IF1 ON KSEN_TYPETYPE_RELTN
(OWNER_TYPE_ID   ASC)/



CREATE  INDEX KSEN_TYPETYPE_RELTN_IF2 ON KSEN_TYPETYPE_RELTN
(RELATED_TYPE_ID   ASC)/



CREATE TABLE KSEN_TYPETYPE_RELTN_ATTR
(
	ID                   VARCHAR(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	ATTR_KEY             VARCHAR2(255) NULL ,
	ATTR_VALUE           VARCHAR2(4000) NULL ,
	OWNER_ID             VARCHAR2(255) NULL 
)/



CREATE UNIQUE INDEX KSEN_TYPETYPE_RELTN_ATTR_P ON KSEN_TYPETYPE_RELTN_ATTR
(ID   ASC)/





CREATE  INDEX KSEN_TYPETYPE_RELTN_ATTR_IF1 ON KSEN_TYPETYPE_RELTN_ATTR
(OWNER_ID   ASC)/
