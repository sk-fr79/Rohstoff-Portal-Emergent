package panter.gmbh.Echo2.ListAndMask;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;



/*
 * vector, der mehrere E2_ComponentMaps zusammenfasst, die in einer
 * maske in 1-1-1-... Beziehungen stehen.
 * Klasse stellt die noetigen Methoden zur verfuegung, um immer wieder kehrende Ablaeufe zu sammeln
 */
public class E2_vCombinedComponentMAPs extends Vector<E2_ComponentMAP>
{

	private E2_ComponentMAP 	  	oE2_ComponentMAP_MAIN = null;
	
	
	/*
	 * hier koennen automatik-felder fuer die ganze maskenspeicherprozedur untergebracht werden.
	 * Falls dieses Feld null ist, dann gelten die globalen Feldzusaetze (ID_MANDANT, geaendert_von, letzte_aenderung
	 */
	private String[][] 				arraySonderFelder = null;
	
	
	
	private String    				cLAST_FILLED_STATUS = null;
	
	

	public E2_vCombinedComponentMAPs()
	{
		super();
	}

	
	public boolean add(E2_ComponentMAP oE2_ComponentMAP)
	{
		E2_ComponentMAP oMap = (E2_ComponentMAP)oE2_ComponentMAP;
		
		if (this.size() == 0)
		{
			oE2_ComponentMAP_MAIN = oMap;
		}
		super.add(oMap);
		oMap.set_E2_vCombinedComponentMAPs(this);
		
		return true;
	}
	
	
	
	
	/**
	 * @param cID_MainTableUnformated
	 * @param cE2_ComponentMAP_STATUS
	 * @throws myException
	 * Methode fuellt alle E2_ComponentMAP eines Vectors, beginnend mit der HauptMap
	 */
	private void fill_ComponentMapsFromDB(String cID_MainTableUnformated, String cE2_ComponentMAP_STATUS) throws myException
	{
		if ( !cE2_ComponentMAP_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) && 
			 !cE2_ComponentMAP_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) &&
			 !cE2_ComponentMAP_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) &&
			 !cE2_ComponentMAP_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
			throw new myException("E2_vCombinedMaps:Fill_All_Maps_FromQuery: Status is NOT ALLOWED !");

		
		
		/*
		 * Vector sammelt die resultmaps alle E2_ComponentMAPs, damit eine folgende, verbundene E2_ComponentMAP, ihren primary-key ermitteln kann damit ein
		 */
		Vector<SQLResultMAP> vResultMAPS = new Vector<SQLResultMAP>();
		
		for (int k=0;k<this.size();k++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP) this.get(k);
			/*
			 * erste maskmap geht auf die ID
			 */
			if (k==0)
			{
				oMap.fill_ComponentMapFromDB(null, cID_MainTableUnformated, cE2_ComponentMAP_STATUS,false,null);
				vResultMAPS.add(oMap.get_oInternalSQLResultMAP());
			}
			else
			{
				/*
				 * alle folgenden sind abhaengig von den vorigen
				 */
				this.FillDaughterMAP(vResultMAPS,oMap,cE2_ComponentMAP_STATUS);
			}
		}

	}
	
	
	
	private void FillDaughterMAP(Vector<SQLResultMAP> vResultMAPs, E2_ComponentMAP oMapDaughter, String STATUS_MAP  ) throws myException
	{
			if (oMapDaughter.get_oSQLFieldMAP().get_bIsSQLMapLEADINGMAP())
				throw new myException("ButtonActionAgentEDIT:FillDaughterMAP: Only allowed for real daughtermaps !!");
			
			/*
			 * raussuchen, welches das Feld ist, das die SQLFieldMAP verbindet (das externe Urspungsfeld)
			 */
			SQLFieldJoinOutside oJoinField = oMapDaughter.get_oSQLFieldMAP().get_oSQLFieldJoinOutside();
			SQLField FieldFomMotherTable = oJoinField.get_oFieldFromConnectedTable();
			
			/*
			 * jetzt alle ResultMaps nach diesem Feld durchsuchen, es muss drin in ein einer
			 */
			boolean bFieldFound = false;
			for (int i=0;i<vResultMAPs.size();i++)
			{
				SQLResultMAP oResMap = (SQLResultMAP) vResultMAPs.get(i);
				SQLFieldMAP  oSQLFieldMAP = oResMap.get_oSQLFieldMAP();
				
				if (oSQLFieldMAP.containsValue(FieldFomMotherTable))
				{
					bFieldFound = true;
					
					/*
					 * jetzt den passenden wert aus der resultmap ziehen
					 */
					String cActualValueFormated = oResMap.get_FormatedValue(FieldFomMotherTable.get_cFieldLabel());
					
					MyDBToolBox oDB = bibALL.get_myDBToolBox();
					String cNamePkActualMAP = 			oMapDaughter.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cTableName()+"."+oMapDaughter.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cFieldName();
					String cNameJoinFieldActualMAP = 	oJoinField.get_cTableName()+"."+oJoinField.get_cFieldName();
					
					/*
					 * aenderung am 22.05.2007: in die abfrage fuer die tochter gehen auch restricted-fields ein
					 * dies erweitert die moeglichkeit der zusammengesetzten masken
					 */
					String cWhereBlock = cNameJoinFieldActualMAP+"="+oJoinField.get_cValueString_For_DB(cActualValueFormated);
					String cWhereFromRestricted = oMapDaughter.get_oSQLFieldMAP().get_cSQL_WHERE_BLOCK_FROM_RestrictFields_FROM_MAIN_TABLE().trim();
					if (!bibALL.isEmpty(cWhereFromRestricted))
						cWhereBlock += " AND "+cWhereFromRestricted;
							
					String cQuery = "SELECT "+cNamePkActualMAP+ " FROM "+bibALL.get_TABLEOWNER()+"."+oMapDaughter.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cTableName()+
									" WHERE "+cWhereBlock;
					
					String[][] cResult = oDB.EinzelAbfrageInArray(cQuery);
					bibALL.destroy_myDBToolBox(oDB);
					
					if (cResult.length != 1)
						throw new myException("ButtonActionAgentEDIT:FillDaughterMAP:Connection only allowed for one-one-connections !!!");
					
					String cID_Daughter = cResult[0][0];
					
					oMapDaughter.fill_ComponentMapFromDB(null, cID_Daughter, STATUS_MAP,false,null);
					vResultMAPs.add(oMapDaughter.get_oInternalSQLResultMAP());
					
					break;
				}
				
			}
			
			if (! bFieldFound)
				throw new myException("ButtonActionAgentEDIT:FillDaughterMAP: Couldnt find Connection  from Mothertable !!!");
			
	}

	
	

	
	
	
	

	/**
	 * Methode speichert komplette maske vom Type neu oder kopie ab
	 * Wenn das Speichern klappt, dann gibt ist der Primaere Schlussel, der neu erzeugt wurde, die Rueckgabe
	 * @param cSTATUS
	 * @param oEvent
	 * @return
	 * @throws myException
	 */
	public String SaveALL_E2_ComponentMAPS_AFTER_NEW(String cSTATUS) throws myException
	{
		if ( !(cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY)) && !(cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY)))
			throw new myException("E2_vCombinedMaps:SaveALL_E2_ComponentMAPS_AFTER_NEW:Status MUST be NEW !");
		
		
		String cCreated_Unformated_KeyOfLeadingTable = null;
		
		
		MyE2_MessageVector vMessagesALL = new MyE2_MessageVector();
		Vector<String> vInserts   = new Vector<String>();

		
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.get(l);

			SQLMaskInputMAP oInputMap=oMap.MakeCompleteCycle_of_Validation_After_Input(null, vMessagesALL, cSTATUS);

			if (vMessagesALL.get_bIsOK())
			{
				vInserts.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap));
				vInserts.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
				vInserts.addAll(oMap.get_vInsert_ADDON_STATEMENTS(true));
				
				//jetzt noch die ADDON-Statement-Builders abarbeiten
				for (E2_ComponentMAP.builder_AddOnSQL_STATEMENTS builder: oMap.get_V_ADDON_SQL_STATEMENT_BUILDER())
				{
					vInserts.addAll(bibALL.notNullVectorString(builder.get_vSQL_ADDON_INSERT_STATEMENTS(oMap,oInputMap,vMessagesALL)));
				}
				
			}
			else
			{
				/*
				 * falls in der ersten map fehler vorhanden sind, dann muss hier rausgesprungen werden, da die 
				 * maps erst in der get_SQL_INSERTSTACK - anweisung primaerschlusselwerte bekommen, die im fehlerfall nicht ausgefuert wird.
				 * das fuehrt zu merkwuerdigen meldungen, da untergeordnete maps dann versuchen ein sql-statement zu bauen,
				 * was wegen fehlendem verbindungs-key schief geht.
				 * 
				 * NACHTEIL: falls beide map eingabefehler haben, werden diese in 2 tranchen angezeigt.
				 */
				
				break;
			}
		}
		
		
		if (vMessagesALL.get_bIsOK())
		{
			if (this.arraySonderFelder==null)
			{
				vMessagesALL.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true));
			}
			else
			{
				vMessagesALL.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true,this.arraySonderFelder));
			}
			if (vMessagesALL.get_bIsOK())
			{
				cCreated_Unformated_KeyOfLeadingTable = this.oE2_ComponentMAP_MAIN.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cLastCreatedPrimaryKEY_Unformated();
			}
			
		}

		/*
		 * jetzt alle internen resultmaps loeschen
		 */
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.get(l);
			oMap.set_InternResultMAP_TO_NULL();
		}		
		
		bibMSG.add_MESSAGE(vMessagesALL);
		
		
		return cCreated_Unformated_KeyOfLeadingTable;
	}
	

	
	
	
	/**
	 * Methode speichert komplette maske vom Typ EDIT ab
	 * Wenn das Speichern klappt, dann ist die Rueckgabe TRUE
	 * @param cSTATUS
	 * @return
	 * @throws myException
	 */
	public boolean SaveALL_E2_ComponentMAPS_AFTER_EDIT(String cSTATUS) throws myException
	{
		if ( !cSTATUS.equals(E2_ComponentMAP.STATUS_EDIT))
			throw new myException("E2_vCombinedMaps:SaveALL_E2_ComponentMAPS_AFTER_EDIT:Status MUST be EDIT !");

		
		MyE2_MessageVector 	oMV = new MyE2_MessageVector();
		Vector<String>		vInserts   = new Vector<String>();
		Vector<String> 		vChanges   = new Vector<String>();
		
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP 	oMap = (E2_ComponentMAP)this.get(l);
			SQLResultMAP		oResultMap = oMap.get_oInternalSQLResultMAP();
			
			SQLMaskInputMAP oInputMap=oMap.MakeCompleteCycle_of_Validation_After_Input(oResultMap, oMV,  cSTATUS);
			
			vChanges.addAll(oMap.get_ChangedFieldResults());

			if (oMV.get_bIsOK())
			{
				vInserts.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResultMap,oInputMap));
				vInserts.addAll(oMap.get_UpdateStackFromComplexFields(oMap,oInputMap));
				vInserts.addAll(oMap.get_vUpdate_ADDON_STATEMENTS(true));

				//jetzt noch die ADDON-Statement-Builders abarbeiten
				for (E2_ComponentMAP.builder_AddOnSQL_STATEMENTS builder: oMap.get_V_ADDON_SQL_STATEMENT_BUILDER())
				{
					vInserts.addAll(bibALL.notNullVectorString(builder.get_vSQL_ADDON_UPDATE_STATEMENTS(oMap,oInputMap,oMV)));
				}

			}
		}

		
		if (vChanges.size() == 0)
		{
			if (oMV.get_bIsOK())
			{
				if (this.arraySonderFelder==null)
				{
					oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true));
				}
				else
				{
					oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true,this.arraySonderFelder));
				}
			}
		}
		else
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Datensatz wurde von einem anderen Teilnehmer veraendert (1)!"), false);
			oMV.add_ALARMMESSAGE_Vector_Untranslated(vChanges);
		}

		bibMSG.add_MESSAGE(oMV);
		
		return oMV.get_bIsOK();
	}

	
	
	////////////////////////////////////////////////////////
	
	
	
	

	/**
	 * Methode macht das gleiche, wie speichern, nur fuehrt es den schreibvorgang nicht durch, sondern
	 * gibt die SQL-Statements in vSQL_StatementsReturn zurueck
	 * Wenn das Speichern klappt, dann gibt ist der Primaere Schlussel, der neu erzeugt wurde, die Rueckgabe
	 * @param vSQL_StatementsReturn
	 * @param cSTATUS
	 * @param oEvent
	 * @param oMessageVectorSammler
	 * @return
	 * @throws myException
	 */
	public String SaveALL_E2_ComponentMAPS_AFTER_NEW_DUMMY(Vector<String> vSQL_StatementsReturn, String cSTATUS, MyE2_MessageVector oMessageVectorSammler) throws myException
	{
		if ( !(cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY)) && !(cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY)))
			throw new myException("E2_vCombinedMaps:SaveALL_E2_ComponentMAPS_AFTER_NEW:Status MUST be NEW !");
		
		
		String cCreated_Unformated_KeyOfLeadingTable = null;
		
		
		MyE2_MessageVector 		vMessagesALL = new MyE2_MessageVector();
		Vector<String> 			vInserts   = new Vector<String>();

		
		
		
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.get(l);
		
			SQLMaskInputMAP oInputMap=oMap.MakeCompleteCycle_of_Validation_After_Input(null, vMessagesALL,  cSTATUS);
			
			if (vMessagesALL.get_bIsOK())
			{
				vInserts.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap));
				vInserts.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
				vInserts.addAll(oMap.get_vInsert_ADDON_STATEMENTS(true));
				
				//jetzt noch die ADDON-Statement-Builders abarbeiten
				for (E2_ComponentMAP.builder_AddOnSQL_STATEMENTS builder: oMap.get_V_ADDON_SQL_STATEMENT_BUILDER())
				{
					vInserts.addAll(bibALL.notNullVectorString(builder.get_vSQL_ADDON_INSERT_STATEMENTS(oMap,oInputMap,vMessagesALL)));
				}

			}
			else
			{
				/*
				 * falls in der ersten map fehler vorhanden sind, dann muss hier rausgesprungen werden, da die 
				 * maps erst in der get_SQL_INSERTSTACK - anweisung primaerschlusselwerte bekommen, die im fehlerfall nicht ausgefuert wird.
				 * das fuehrt zu merkwuerdigen meldungen, da untergeordnete maps dann versuchen ein sql-statement zu bauen,
				 * was wegen fehlendem verbindungs-key schief geht.
				 * 
				 * NACHTEIL: falls beide map eingabefehler haben, werden diese in 2 tranchen angezeigt.
				 */
				
				break;
			}
		}
		
		
		if (vMessagesALL.get_bIsOK())
		{
			vSQL_StatementsReturn.addAll(vInserts);
			cCreated_Unformated_KeyOfLeadingTable = this.oE2_ComponentMAP_MAIN.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cLastCreatedPrimaryKEY_Unformated();
		}
		
		/*
		 * jetzt alle internen resultmaps loeschen
		 */
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP)this.get(l);
			oMap.set_InternResultMAP_TO_NULL();
		}		
		
		oMessageVectorSammler.add_MESSAGE(vMessagesALL);
		
		
		return cCreated_Unformated_KeyOfLeadingTable;
	}
	

	
	
	
	/**
	 * Methode macht das gleiche, wie speichern, nur fuehrt es den schreibvorgang nicht durch, sondern
	 * gibt die SQL-Statements in vSQL_StatementsReturn zurueck
	 * Wenn das ales klappt, dann ist die Rueckgabe TRUE
	 * @param cSTATUS
	 * @return
	 * @throws myException
	 */
	public boolean SaveALL_E2_ComponentMAPS_AFTER_EDIT_DUMMY(Vector<String> vSQL_StatementsReturn, String cSTATUS,MyE2_MessageVector oMessageVectorSammler) throws myException
	{
		if ( !cSTATUS.equals(E2_ComponentMAP.STATUS_EDIT))
			throw new myException("E2_vCombinedMaps:SaveALL_E2_ComponentMAPS_AFTER_EDIT:Status MUST be EDIT !");

		MyE2_MessageVector 	vMessagesALL = new MyE2_MessageVector();
		Vector<String>		vInserts   = new Vector<String>();
		Vector<String>		vChanges   = new Vector<String>();
		
		
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP 	oMap = (E2_ComponentMAP)this.get(l);
			SQLResultMAP		oResultMap = oMap.get_oInternalSQLResultMAP();
			
			SQLMaskInputMAP oInputMap=oMap.MakeCompleteCycle_of_Validation_After_Input(oResultMap, vMessagesALL,  cSTATUS);
			
			vChanges.addAll(oMap.get_ChangedFieldResults());

			if (vMessagesALL.get_bIsOK())
			{
				vInserts.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResultMap,oInputMap));
				vInserts.addAll(oMap.get_UpdateStackFromComplexFields(oMap,oInputMap));
				vInserts.addAll(oMap.get_vUpdate_ADDON_STATEMENTS(true));
				
				//jetzt noch die ADDON-Statement-Builders abarbeiten
				for (E2_ComponentMAP.builder_AddOnSQL_STATEMENTS builder: oMap.get_V_ADDON_SQL_STATEMENT_BUILDER())
				{
					vInserts.addAll(bibALL.notNullVectorString(builder.get_vSQL_ADDON_UPDATE_STATEMENTS(oMap,oInputMap,vMessagesALL)));
				}
				
			}
		}

		
		if (vChanges.size() == 0)
		{
			if (vMessagesALL.get_bIsOK())
			{
				vSQL_StatementsReturn.addAll(vInserts);
			}
		}
		else
		{
			vMessagesALL.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Datensatz wurde von einem anderen Teilnehmer veraendert !"), false);
			vMessagesALL.add_ALARMMESSAGE_Vector_Untranslated(vChanges);
		}

		oMessageVectorSammler.add_MESSAGE(vMessagesALL);

		return vMessagesALL.get_bIsOK();
		
	}

	
	
	
	
	///////////////////////////////////////////////////////
	
	
	
	
	
	private void do_MapSettings_BEFORE(String cSTATUS) throws myException
	{
		if ( !cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) && 
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) &&
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_EDIT) &&
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_VIEW))
			throw new myException("E2_vCombinedMaps:Activate_E2_ComponentMAP_Setter_BEFORE: Status is NOT ALLOWED !");

		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2.do_MapSettings_BEFORE(cSTATUS);
		}

	}
	
	public void do_MapSettings_AFTER(String cSTATUS) throws myException
	{
		if ( !cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) && 
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) &&
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_EDIT) &&
			 !cSTATUS.equals(E2_ComponentMAP.STATUS_VIEW))
			throw new myException("E2_vCombinedMaps:Activate_E2_ComponentMAP_Setter_AFTER: Status is NOT ALLOWED !");

		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2.do_MapSettings_AFTER(cSTATUS);
		}

	}


	public E2_ComponentMAP get_oE2_ComponentMAP_MAIN()
	{
		return oE2_ComponentMAP_MAIN;
	}

	
	
	public void Requery_All_ActualResultMAPs(String cSTATUS_MAP) throws myException
	{
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2._DO_REFRESH_COMPONENTMAP(cSTATUS_MAP,false,null);
		}

	}
	
	public void set_AllComponentsEnabled_For_Edit(boolean bEnabled, String cSTATUS_MAP) throws myException
	{
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2.set_AllComponentsEnabled_For_Edit(bEnabled, cSTATUS_MAP);
		}

	}
	
	
	
	
	
	public String get_actual_Formated_ROWID_of_Leading_ComponentMAP() throws myException
	{
		if (this.oE2_ComponentMAP_MAIN.get_oInternalSQLResultMAP() == null)
			throw new myException("E2_vCombinedComponentMAPs:get_actual_Formated_of_Leading_ComponentMAP:Internal Resultmap is empty !");
		
		return this.oE2_ComponentMAP_MAIN.get_oInternalSQLResultMAP().get_cFormatedROW_ID();
	}
	
	public String get_actual_UNFormated_ROWID_of_Leading_ComponentMAP() throws myException
	{
		if (this.oE2_ComponentMAP_MAIN.get_oInternalSQLResultMAP() == null)
			throw new myException("E2_vCombinedComponentMAPs:get_actual_UNFormated_of_Leading_ComponentMAP:Internal Resultmap is empty !");
		
		return this.oE2_ComponentMAP_MAIN.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
	}
	
	
	
	private void set_ALL_InternResultMAPs_TO_NULL()
	{
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2.set_InternResultMAP_TO_NULL();
		}
	}
	
	
	private void prepare_ALL_ContentForNEW() throws myException
	{
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap2 = (E2_ComponentMAP) this.get(l);
			oMap2.PrepareMaskContentForNew();
		}
	}

	
	
	
	/*
	 * ein makro, die bestimmte schritte zur anzeige einer maske
	 * vorgeben
	 */
	public void MAKRO_Fill_Build_Set_MASK(String STATUS_MASKE, String cID_MAIN_Unformated) throws myException
	{
		
		this.cLAST_FILLED_STATUS = null;
		
	
		if ( !STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) && 
			 !STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) &&
			 !STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) &&
			 !STATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW))
				throw new myException("E2_vCombinedMaps:MAKRO_BuildMASK: Status is NOT ALLOWED !");

		if ( (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) || 
			 STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) ||
			 STATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW)) && bibALL.isEmpty(cID_MAIN_Unformated))
					throw new myException("E2_vCombinedMaps:MAKRO_BuildMASK: Mask-Loading-Action MUST have an ID of loaded Row !");
		
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			this.set_ALL_InternResultMAPs_TO_NULL();
			this.prepare_ALL_ContentForNEW();
			this.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_NEW_EMPTY);
			this.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_NEW_EMPTY);
		}
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			/*
			 * erst wird alles gefuellt
			 */
			this.fill_ComponentMapsFromDB(cID_MAIN_Unformated,E2_ComponentMAP.STATUS_EDIT);
			this.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_EDIT);
			this.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_EDIT);
			/*
			 * dann werden die resultmaps geloescht
			 */
			this.set_ALL_InternResultMAPs_TO_NULL();
			
			//2012-12-13: vermutlich BUG!!! falscher STATUS wird an die E2_ComponentMAPs uebergeben, deshalb wird hier fuer das neue
			//                              Interaktionsframework der status neu gesetzt
			for (E2_ComponentMAP oMAP: this)
			{
				oMAP.set_STATUS_LAST_FILL_TO_NEW_COPY();
			}
			// -------------------------------------------------------------
			
		}
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.fill_ComponentMapsFromDB(cID_MAIN_Unformated,E2_ComponentMAP.STATUS_EDIT);
			this.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_EDIT);
			this.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_EDIT);
		}
		
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.fill_ComponentMapsFromDB(cID_MAIN_Unformated,E2_ComponentMAP.STATUS_VIEW);
			this.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_VIEW);
			this.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
		}

		this.cLAST_FILLED_STATUS = STATUS_MASKE;

		
		
		//2012-12-13: settings-collection anwenden
		this.execute_ComponentMAPs_interactiv_settings();
		
	}


	
	//2012-12-13: in den ComponentMaps die settingscollections anwenden
	private void execute_ComponentMAPs_interactiv_settings() throws myException
	{
		for (int l=0;l<this.size();l++)
		{
			E2_ComponentMAP oMap = (E2_ComponentMAP) this.get(l);
			oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMap);
		}
	}

	
	
	public Vector<MyE2IF__Component> get_REAL_ComponentVector_OfAllMAPS()
	{
		Vector<MyE2IF__Component> vRueck = new Vector<MyE2IF__Component>();
		
		for (int i=0;i<this.size();i++)
		{
			vRueck.addAll( ((E2_ComponentMAP)this.get(i)).get_REAL_ComponentVector());
			
		}
		return vRueck;
	}
	
	

	public HashMap<String,MyE2IF__Component> get_REAL_ComponentHashMAP_OfAllMAPS()
	{
		HashMap<String,MyE2IF__Component> hmRueck = new HashMap<String,MyE2IF__Component>();
		
		for (int i=0;i<this.size();i++)
		{
			hmRueck.putAll( this.get(i).get_REAL_ComponentHashMap());
		}
		return hmRueck;
	}


	public String[][] get_ArraySonderFelder()
	{
		return arraySonderFelder;
	}


	public void set_Array_DB_SonderFelder(String[][] arraySonderFelder)
	{
		this.arraySonderFelder = arraySonderFelder;
	}
	

	public String get_cLAST_FILLED_STATUS() 
	{
		return cLAST_FILLED_STATUS;
	}


	
}
