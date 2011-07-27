--------------------------------------------------------
--  Constraints for Table KSEN_LUILUI_RELTN
--------------------------------------------------------

  ALTER TABLE "KSEN_LUILUI_RELTN" MODIFY ("ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" MODIFY ("TYPE_ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" MODIFY ("STATE_ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" ADD PRIMARY KEY ("ID") ENABLE
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI" MODIFY ("ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI" MODIFY ("STATE_ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI" MODIFY ("TYPE_ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI" ADD PRIMARY KEY ("ID") ENABLE
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI_INSTR
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_INSTR" MODIFY ("ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI_INSTR" ADD PRIMARY KEY ("ID") ENABLE
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI_JN_LUI_INSTR
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_JN_LUI_INSTR" MODIFY ("LUI_ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI_JN_LUI_INSTR" MODIFY ("LUI_INSTR_ID" NOT NULL ENABLE)
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI_RICH_TEXT
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_RICH_TEXT" MODIFY ("ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI_RICH_TEXT" ADD PRIMARY KEY ("ID") ENABLE
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI_TYPE
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_TYPE" MODIFY ("TYPE_KEY" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI_TYPE" ADD PRIMARY KEY ("TYPE_KEY") ENABLE
/

--------------------------------------------------------
--  Constraints for Table KSEN_LUI_ATTR
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_ATTR" MODIFY ("ID" NOT NULL ENABLE)
/
 
  ALTER TABLE "KSEN_LUI_ATTR" ADD PRIMARY KEY ("ID") ENABLE
/

--------------------------------------------------------
--  Ref Constraints for Table KSEN_LUILUI_RELTN
--------------------------------------------------------

  ALTER TABLE "KSEN_LUILUI_RELTN" ADD CONSTRAINT "FKF07F59221343973C" FOREIGN KEY ("RELATED_LUI_ID")
	  REFERENCES "KSEN_LUI" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" ADD CONSTRAINT "FKF07F592255882CA8" FOREIGN KEY ("LUI_ID")
	  REFERENCES "KSEN_LUI" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" ADD CONSTRAINT "FKF07F59228D1000ED" FOREIGN KEY ("RT_DESCR_ID")
	  REFERENCES "KSEN_LUI_RICH_TEXT" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" ADD CONSTRAINT "FKF07F592298517E2C" FOREIGN KEY ("STATE_ID")
	  REFERENCES "KSEN_COMM_STATE" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUILUI_RELTN" ADD CONSTRAINT "FKF07F5922B7C4E988" FOREIGN KEY ("TYPE_ID")
	  REFERENCES "KSEN_LUI_TYPE" ("TYPE_KEY") ENABLE
/

--------------------------------------------------------
--  Ref Constraints for Table KSEN_LUI
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI" ADD CONSTRAINT "FKFD33EB28D1000ED" FOREIGN KEY ("RT_DESCR_ID")
	  REFERENCES "KSEN_LUI_RICH_TEXT" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUI" ADD CONSTRAINT "FKFD33EB298517E2C" FOREIGN KEY ("STATE_ID")
	  REFERENCES "KSEN_COMM_STATE" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUI" ADD CONSTRAINT "FKFD33EB2B7C4E988" FOREIGN KEY ("TYPE_ID")
	  REFERENCES "KSEN_LUI_TYPE" ("TYPE_KEY") ENABLE
/

  ALTER TABLE "KSEN_LUI" ADD CONSTRAINT "FKFD33EB2656AF27" FOREIGN KEY ("OFFIC_LUI_ID")
	    REFERENCES "KSEN_LUI_IDENT" ("ID") ENABLE
/

--------------------------------------------------------
--  Ref Constraints for Table KSEN_LUI_ATTR
--------------------------------------------------------

  ALTER TABLE "KSEN_LUI_ATTR" ADD CONSTRAINT "FKDD9BAB5E6831FFE7" FOREIGN KEY ("OWNER")
	  REFERENCES "KSEN_LUILUI_RELTN" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUI_ATTR" ADD CONSTRAINT "FKDD9BAB5E9CEED1A1" FOREIGN KEY ("OWNER")
	  REFERENCES "KSEN_LUI" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUI_ATTR" ADD CONSTRAINT "FKDD9BAB5EA1B912DE" FOREIGN KEY ("OWNER")
	  REFERENCES "KSEN_LUI_INSTR" ("ID") ENABLE
/
 
  ALTER TABLE "KSEN_LUI_ATTR" ADD CONSTRAINT "FKDD9BAB5EF0F1FBFB" FOREIGN KEY ("OWNER")
	  REFERENCES "KSEN_LUI_TYPE" ("TYPE_KEY") ENABLE
/
