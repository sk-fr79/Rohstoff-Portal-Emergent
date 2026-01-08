package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum erzeugen der SQL-Insert-Statements für die verschiedenen Objekte
 * 
 * @author manfred
 *
 */
public class MODUL_LINK_SQL_Factory {

	public MODUL_LINK_SQL_Factory() {
	}
	
	
	/**
	 * Methode liefert das SQL-Statement zurück, das in einer Nachricht (id-Source) angezeigt wird.
	 * Das Target ist dabei das Objekt, auf das gesprungen werden soll.
	 * TargetZusatz ist z.B. der Karteireiter der geöffnet werden soll
	 * @param idSource
	 * @param idTarget
	 * @param Target
	 * @param TargetZusatz
	 * @param Description
	 * @param LinkType: 1 Liste, 2 Maske (nur für Fuhre relevant)
	 * @return
	 * @throws myException
	 * 
	 * Link zu Info in Adresse wird besondert behandelt, sonst standard
	 */
	public Vector<String> get_SQL_Statement_For_Insert_Message_Button ( String idSource, String idTarget, String Target, String TargetZusatz, String Description) throws myException{
		Vector<String> vSql = null;
		if (Target.equals(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE)){
			vSql = get_SQL_Statement_For_Insert(
					MODUL_LINK_Button_Goto_Mask_Adressen_Tab_Info.class,
					idSource,
					E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE,
					idTarget,
					Target,
					TargetZusatz,
					Description,
					(String)null);
		} else if (Target.equals(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK)){
			vSql = get_SQL_Statement_For_Insert(
					MODUL_LINK_Button_Goto_Mask_Adressen_Tab.class,
					idSource,
					E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE,
					idTarget,
					Target,
					TargetZusatz,
					Description,
					(String)null);
		} else if (Target.equals(E2_MODULNAMES.NAME_MODUL_FUHRENMASKE))	{
			vSql = get_SQL_Statement_For_Insert(
					MODUL_LINK_Button_Goto_Mask_FUHRE.class,
					idSource,
					E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE,
					idTarget,
					Target, 
					TargetZusatz,
					Description,
					(String)null);
		}else	{
			vSql = get_SQL_Statement_For_Insert(
					MODUL_LINK_Button_Goto_Modul.class,
					idSource,
					E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE,
					idTarget,
					Target, 
					TargetZusatz,
					Description,
					(String)null);
		}
		
		return vSql;
	}
	
	
	
	/**
	 * Methode liefert das SQL-Statement für die verknüpfung zwischen Maschine(n-Aufgabe) und Workflow 
	 * Das Target ist dabei das Objekt, auf das gesprungen werden soll.
	 * @param idSource
	 * @param idTarget
	 * @param Target
	 * @param Description
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SQL_Statement_For_MaschinenAufgabe_to_Workflow ( String idMaschinenAufgabe, String idWorkflow, String DescriptionInMaschine ) throws myException{
		return get_SQL_Statement_For_Insert(
			 MODUL_LINK_Button_Goto_Modul.class,
			 idMaschinenAufgabe,
			 E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_AUFGABE_LISTE,
			 idWorkflow,
			 E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, 
			 DescriptionInMaschine,null
			 );
	}
	
	/**
	 * Methode liefert das SQL-Statement für die verknüpfung zwischen Workflow und Maschine 
	 * Das Target ist dabei das Objekt, auf das gesprungen werden soll.
	 * @param idSource
	 * @param idTarget
	 * @param Target
	 * @param Description
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SQL_Statement_For_Workflow_to_Maschine ( String idWorkflowEintrag, String idMaschine, String DescriptionInWorkflowEntry ) throws myException{
		return get_SQL_Statement_For_Insert(
			 MODUL_LINK_Button_Goto_Modul.class,
			 idWorkflowEintrag,
			 E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE, 
			 idMaschine ,
			 E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE,
			 DescriptionInWorkflowEntry,null
			 );
	}
		
	
	/**
	 * Strings ohne Ziel-Zusatz
	 * @param ObjectClass
	 * @param idSource
	 * @param Source
	 * @param idTarget
	 * @param Target
	 * @param Description
	 * @param Description_BackLink
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SQL_Statement_For_Insert (
			Class<?> ObjectClass, 
			String idSource, 
			String Source, 
			String idTarget, 
			String Target, 
			String Description,
			String Description_BackLink) throws myException{
		return get_SQL_Statement_For_Insert(ObjectClass, idSource, Source, idTarget, Target,null, Description, Description_BackLink);
	}
	
	
	/**
	 * Erzeugt 1 oder 2 Sql-Insert-Stmts für die Verknüpfung der Module
	 * @param ObjectClass    Klasse des Connect-Objektes der erscheinen soll (ergibt die ID in der Definitionstabelle)
	 * @param idSource		 RecordID der quelle
	 * @param Source		 (Modul)name der quelle
	 * @param idTarget 		 RecordID des Ziels
	 * @param Target		 (Modul)name des Ziels
	 * @param Description	 Beschreibung der Connection
	 * @param Description_BackLink  Wenn != null: Es wird automatisch eine Rückverbindung in die Tabelle eingetragen.
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SQL_Statement_For_Insert (
											Class<?> ObjectClass, 
											String idSource, 
											String Source, 
											String idTarget, 
											String Target, 
											String TargetZusatz,
											String Description,
											String Description_BackLink) throws myException{
		
		Vector<String> vSql = new Vector<String>();
		String sType = null;
		
		if (ObjectClass.equals(MODUL_LINK_Button_Goto_Modul.class)){
			sType = MODUL_LINK_CONST.ps_modul_liste;
		} else if (ObjectClass.equals(MODUL_LINK_Button_Goto_Mask_Adressen_Tab_Info.class)){
			sType = MODUL_LINK_CONST.ps_modul_maske;
		} else if (ObjectClass.equals(MODUL_LINK_Button_Goto_Mask_Adressen_Tab.class)){
			sType = MODUL_LINK_CONST.ps_modul_maske;
		} else if (ObjectClass.equals(MODUL_LINK_Button_Goto_Mask_FUHRE.class)){
			sType = MODUL_LINK_CONST.ps_modul_maske;
		} 
		
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		oSql.clear();
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, idSource, false);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__QUELLE, Source, true);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_ZIEL, idTarget , false);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ZIEL, Target, true);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__BESCHREIBUNG, Description, true);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_TYP, sType, false);
		oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ZIEL_ZUSATZ, TargetZusatz, true);

		vSql.add(oSql.get_CompleteInsertString("JT_MODUL_CONNECT",bibE2.cTO()));
		
		// Rückverbindung setzen, falls gewünscht
		if (Description_BackLink != null){
			oSql.clear();
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, idTarget, false);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__QUELLE, Target, true);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_ZIEL, idSource , false);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ZIEL, Source, true);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__BESCHREIBUNG, Description_BackLink, true);
			oSql.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_TYP, sType, false);
			
	
			vSql.add(oSql.get_CompleteInsertString("JT_MODUL_CONNECT",bibE2.cTO()));
		}
		
		return vSql;
		
	}
	
}
