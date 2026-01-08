package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

 

public class Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM extends Mandatory_DB_Object_Trigger {

	public static final  String  NAME_TRIGGER = "TR_CALC_ATOM_WERTE"; 
	
	private static final String SQL_Statement = 		
			" CREATE OR REPLACE TRIGGER " + Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM.NAME_TRIGGER+ "  					"+"\n"+
			"		BEFORE INSERT OR UPDATE 																							"+"\n"+
			"		ON JT_BEWEGUNG_ATOM 																								"+"\n"+
			"		FOR EACH ROW 																										"+"\n"+
			"		  DECLARE 																											"+"\n"+
			"		  MENGENDIVISOR NUMBER(10,0); 																						"+"\n"+
			"		  GESAMTPREIS NUMBER(12,2); 																						"+"\n"+
			"		  E_PREIS_RESULT_NETTO_MGE NUMBER(12,2); 																			"+"\n"+
	 		"		  E_PREIS_RESULT_BRUTTO_MGE NUMBER(12,2); 																			"+"\n"+
			"          NETTOMENGE NUMBER(12,2);																							"+"\n"+
			"		BEGIN 																												"+"\n"+
			"		  GESAMTPREIS := NULL; 																								"+"\n"+
			"		  E_PREIS_RESULT_NETTO_MGE := NULL; 																				"+"\n"+
			"		  E_PREIS_RESULT_BRUTTO_MGE := NULL; 																				"+"\n"+
			
//			" 		  IF (:NEW.ID_VPOS_TPA_FUHRE IS NOT NULL) THEN																												"+"\n"+
			" 																															"+"\n"+
			"		  	IF (:NEW.ID_ARTIKEL IS NOT NULL AND nvl(:NEW.MENGE,0) <> 0 ) THEN 												"+"\n"+
			" 				E_PREIS_RESULT_NETTO_MGE := :NEW.E_PREIS_RESULT_NETTO_MGE;														"+"\n"+
			" 				E_PREIS_RESULT_BRUTTO_MGE := :NEW.E_PREIS_RESULT_BRUTTO_MGE;													"+"\n"+
			" 																																"+"\n"+
			"			    SELECT NVL(ART.MENGENDIVISOR,0) INTO MENGENDIVISOR FROM JT_ARTIKEL ART WHERE ART.ID_ARTIKEL = :NEW.ID_ARTIKEL; 	"+"\n"+
			" 																																"+"\n"+
			"  				IF (MENGENDIVISOR <> 0 AND  MENGENDIVISOR  <> 0) THEN 															"+"\n"+
			" 					NETTOMENGE := :NEW.MENGE - nvl(:NEW.ABZUG_MENGE,0);															"+"\n"+
			" 					 																											"+"\n"+
			
			"			       GESAMTPREIS := ROUND((nvl(:NEW.E_PREIS,0) * :NEW.MENGE) / MENGENDIVISOR,2); 									"+"\n"+
			" 																															"+"\n"+
			" 					if (:NEW.GESAMTPREIS is not null) then																		"+"\n"+
			" 						if (NETTOMENGE <> 0) then																										"+"\n"+
			" 						    E_PREIS_RESULT_NETTO_MGE := ROUND((GESAMTPREIS - NVL(:NEW.GPREIS_ABZ_MGE,0) - 							"+"\n"+
			" 							    NVL(:NEW.GPREIS_ABZ_AUF_NETTOMGE,0))/(NETTOMENGE/MENGENDIVISOR),2); 	"+"\n"+
			" 					 	else																										"+"\n"+
			" 					 		E_PREIS_RESULT_NETTO_MGE := 0;																									"+"\n"+
			" 						end if;																									 	"+"\n"+
			" 						E_PREIS_RESULT_BRUTTO_MGE := ROUND((GESAMTPREIS - NVL(:NEW.GPREIS_ABZ_MGE,0) - 							"+"\n"+
			" 						NVL(:NEW.GPREIS_ABZ_AUF_NETTOMGE,0))/(:NEW.MENGE/MENGENDIVISOR),2); 								"+"\n"+
			" 																															"+"\n"+
			" 					END IF;																										"+"\n"+
			" 																														 	"+"\n"+
			"			  		:NEW.GESAMTPREIS := GESAMTPREIS; 																			"+"\n"+
			"			  		:NEW.MENGE_NETTO := NVL(:NEW.MENGE,0)-NVL(:NEW.ABZUG_MENGE,0); 												"+"\n"+
			"			  		:NEW.E_PREIS_RESULT_NETTO_MGE := E_PREIS_RESULT_NETTO_MGE; 													"+"\n"+
			"			  		:NEW.E_PREIS_RESULT_BRUTTO_MGE := E_PREIS_RESULT_BRUTTO_MGE; 												"+"\n"+
			" 																														 	"+"\n"+
			"  				END IF; 																										"+"\n"+
			"		  	END IF; 																											"+"\n"+			
//			"		  END IF; 																											"+"\n"+
			" 																														 	"+"\n"+
			" END; ";

	
	public Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM() {
		super(Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM.NAME_TRIGGER, 
			 Mandatory_DB_Object_Trigger_CalcValues_IN_BEWEGUNG_ATOM.SQL_Statement);
	}
}
