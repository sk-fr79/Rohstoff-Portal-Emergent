package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * klasse zum bau von sequencen
 */
public class E2_SequenceBuilder
{
	/*
	 * der Basisname der sequence, wird durch ein voreingeseztes SEQ_ zum komplette seqname
	 */
	private String cSEQ_BASE_NAME = null;
	
	private String cSeqCompleteName = null;
	/*
	 * wird die sequence innerhalb einer datenbank benutzt, dann ist die Query
	 * noetig, um den hoechsten wert +1 aus der datenbank zu lesen
	 */
	private String cSQL_DATABASEQUERY_NEXTVALUE = null;

	
	private long iINITVALUE = 1;
	
	private MyE2_Button		oButtonReadFromSequence = new MyE2_Button(new MyE2_String("Lese SEQUENCE"));
	private MyE2_Button		oButtonReadFromDatabase = new MyE2_Button(new MyE2_String("Lese DATENBANK"));
	
	private MyE2_Button		oButtonSaveNewSequenceValue = new MyE2_Button(new MyE2_String("Speichern"));
	
	private MyE2_TextField	oTextFieldNewValue = new MyE2_TextField("",200,14);
	
	
	//falls true, wird der initwert auch genommen, falls die sequence neu geschrieben wird
	private boolean 		bForceInitValue	= false;
	

	/**
	 * @param cseq_base_name --> der Basisname der sequence, wird durch ein voreingeseztes SEQ_ zum komplette seqname
	 * @param csql_databasequery_NEXT_VALUE --> 	um den hoechsten wert +1 aus der datenbank zu lesen (falls nicht uebergeben, dann wird der ausleseknopf
	 * @param cINIT_Value
	 * @param odb
	 * @param bforceInitValue
	 * @param oMessageagent
	 */
	public E2_SequenceBuilder(	String 			cseq_base_name, 
								String 			csql_databasequery_NEXT_VALUE, 
								String 			cINIT_Value, 
								boolean 		bforceInitValue,
								String          cMODULE_IDENTIFIER)
	{
		super();
		this.cSEQ_BASE_NAME = cseq_base_name;
		this.cSQL_DATABASEQUERY_NEXTVALUE = csql_databasequery_NEXT_VALUE;
		
		this.cSeqCompleteName = "SEQ_"+this.cSEQ_BASE_NAME;
		this.bForceInitValue = bforceInitValue;
		
		this.oButtonReadFromDatabase.add_oActionAgent(new ownActionAgentReadFromDatabase());
		this.oButtonReadFromSequence.add_oActionAgent(new ownActionAgentReadOrBuildSequence());
		this.oButtonSaveNewSequenceValue.add_oActionAgent(new ownActionAgentSaveNewSequenceValue());
		this.oButtonSaveNewSequenceValue.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULE_IDENTIFIER,"SAVE_SEQUENCE_VALUE"));
	
		// zahlen rechtsbuendig formatieren
		this.oTextFieldNewValue.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		try
		{
			if (cINIT_Value != null)
			{
				Long iHelp = new Long(cINIT_Value);
				this.iINITVALUE = iHelp.longValue();
			}
		}
		catch (Exception ex) {}
		
	}

	
	
	public MyE2_TextField get_oTextFieldNewValue()
	{
		return oTextFieldNewValue;
	}
	
	
	
	
	/**
	 * @return vector of Strings - errormessages (or empty vector)
	 */
	public MyE2_MessageVector readOrBuild_SequenceValue() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		String cSQL = "SELECT "+bibE2.cTO()+"."+this.cSeqCompleteName+".nextval from DUAL"; 
		String cSEQ_Wert = bibDB.EinzelAbfrage(cSQL,"@@ERROR@@","@@ERROR@@","@@ERROR@@");

		/*
		 * jetzt den wert gleich wieder korrigieren, indem eine neue sequence erzeugt wird
		 */
		if (!cSEQ_Wert.equals("@@ERROR@@"))
		{
			try
			{
				Long longHelp = new Long(cSEQ_Wert);
				long lActuell=longHelp.longValue();   // aktueller wert
				
				if (this.bForceInitValue)
					if (this.iINITVALUE>lActuell)
						cSEQ_Wert = ""+this.iINITVALUE;
				
				
				Vector<String> vSQL = new Vector<String>();
				
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+
									"DROP SEQUENCE "+bibE2.cTO()+"."+this.cSeqCompleteName);
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+
									DB_META.get_SequenceBuilder(bibALL.get_DBKENNUNG(), bibE2.cTO(), this.cSeqCompleteName, cSEQ_Wert));
				
				oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
				
				if (oMV.get_bHasAlarms())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Ursprungswert der Sequence "+this.cSeqCompleteName+" von "+cSEQ_Wert+" Konnte nicht wiederhergestellt werden !!!",false), false);
				}
				else
				{
					this.oTextFieldNewValue.setText(cSEQ_Wert);
					oMV.add_MESSAGE(new MyE2_Info_Message("Die Sequence "+this.cSeqCompleteName+" (Wert: "+cSEQ_Wert+") wurde eingelesen !!!",false), false);
				}
			}
			catch (Exception ex)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("ERROR: Der Ursprungswert der Sequence "+this.cSeqCompleteName+" von "+cSEQ_Wert+" Konnte nicht wiederhergestellt werden !!!",false), false);
			}
		}
		else
		{
			// falls keine vorhanden ist, wird sie erzeugt
			if (this.iINITVALUE>0)
			{
				cSEQ_Wert = ""+this.iINITVALUE;
			}
			else
			{
				cSEQ_Wert = "1";
			}

			
			
			Vector<String> vSQL = new Vector<String>();
			vSQL.add(DB_META.get_SequenceBuilder(bibALL.get_DBKENNUNG(), bibE2.cTO(), this.cSeqCompleteName, cSEQ_Wert));

			oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
			if (oMV.get_bIsOK())
			{
				oMV.add_MESSAGE(new MyE2_Info_Message("OK: 		Die Sequence "+this.cSeqCompleteName+" wurde mit dem Startwert "+this.iINITVALUE+" neu erstellt !!!",false), false);
				this.oTextFieldNewValue.setText(""+this.iINITVALUE);
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Info_Message("ERROR: 	Die Sequence "+this.cSeqCompleteName+" ist nicht vorhanden, kann aber nicht neu gebaut werden ! ",false), false);
				this.oTextFieldNewValue.setText("@@@ERROR@@@");
			}
			
		}
		
		
		return oMV;
		
	}
	
	
	
	
	/**
	 * @return vector of Strings - errormessages (or empty vector)
	 */
	public MyE2_MessageVector Save_NewSequenceValue()
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		String cWert = this.oTextFieldNewValue.getText();

		if (!bibALL.isLong(cWert))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Achtung ! Der neue Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" ist keine Zahl !!!"), false);
		}

		if (oMV.get_bIsOK())
		{
			try
			{
				Long lWert = new Long(cWert);
				
				if (this.bForceInitValue)
					if (this.iINITVALUE>lWert.longValue())
						cWert = ""+this.iINITVALUE;
	
				Vector<String> vSQL = new Vector<String>();
				
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+
									"DROP SEQUENCE "+bibE2.cTO()+"."+this.cSeqCompleteName);
				vSQL.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+
									DB_META.get_SequenceBuilder(bibALL.get_DBKENNUNG(), bibE2.cTO(), this.cSeqCompleteName, cWert));
	
				oMV.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
				
				if (oMV.get_bHasAlarms())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Der neue Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" konnte nicht geschrieben werden !!!",false), false);
				}
				else
				{
					oMV.add_MESSAGE(new MyE2_Info_Message("Der neue Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" wurde geschrieben !!!",false), false);
					this.oTextFieldNewValue.setText(cWert);
				}
			
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Der neue Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" konnte nicht geschrieben werden !!!",false), false);
				oMV.add_MESSAGE(new MyE2_Alarm_Message(ex.getLocalizedMessage(),false), false);
			}
		}
		
		return oMV;
		
	}
	

	
	
	/**
	 * @return vector of Strings - errormessages (or empty vector)
	 */
	public MyE2_MessageVector Build_New_SequenceBased_on_DatabaseQuery()
	{
		MyE2_MessageVector vMessages = new MyE2_MessageVector();
		
		if (bibALL.isEmpty(this.cSQL_DATABASEQUERY_NEXTVALUE))
		{
			vMessages.add_MESSAGE(new MyE2_Alarm_Message(" Es ist keine Datenbank-Query definiert !!! --->"+this.cSeqCompleteName,false), false);
			return vMessages;
		}
		
		
		String cWert = bibDB.EinzelAbfrage(this.cSQL_DATABASEQUERY_NEXTVALUE,"@ERROR@","@ERROR@","@ERROR@",false,false);
		

		if (!bibALL.isLong(cWert))
		{
			vMessages.add_MESSAGE(new MyE2_Alarm_Message("ERROR: Der Datenbankwert der Sequence "+this.cSeqCompleteName+" konnte nicht gelesen werden !!!",false), false);
			
			return vMessages;
		}
		
		try
		{
		
			Long lWert = new Long(cWert);
			
			if (this.bForceInitValue)
				if (this.iINITVALUE>lWert.longValue())
					cWert = ""+this.iINITVALUE;


			String cMessage = "";
			
			String cDEL_SEQ = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"DROP SEQUENCE "+bibE2.cTO()+"."+this.cSeqCompleteName;
			String cMAKE_SEQ = DB_META.get_SequenceBuilder(bibALL.get_DBKENNUNG(), bibE2.cTO(), this.cSeqCompleteName, cWert);

			if (bibDB.ExecSQL(cDEL_SEQ,true))
			{
				cMessage += "GELÖSCHT        -->";
			}
			else
			{
				cMessage += "NICHT VORHANDEN -->";
			}
			
			if (!(bibDB.ExecSQL(cMAKE_SEQ,true)))
			{
				vMessages.add_MESSAGE(new MyE2_Alarm_Message(cMessage+" Der neue DB-Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" konnte nicht geschrieben werden !!!",false), false);
			}
			else
			{
				vMessages.add_MESSAGE(new MyE2_Info_Message(cMessage+" Der neue DB-Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" wurde geschrieben !!!",false), false);
				this.oTextFieldNewValue.setText(cWert);
			}
			
		}
		catch (Exception ex)
		{
			vMessages.add_MESSAGE(new MyE2_Alarm_Message("Der neue DB-Wert der Sequence "+this.cSeqCompleteName+" von "+cWert+" konnte nicht geschrieben werden !!!",false), false);
		}
		
		return vMessages;
		
	}
	
	

	
	public MyE2_Button get_oButtonReadFromDatabase()
	{
		return oButtonReadFromDatabase;
	}



	public MyE2_Button get_oButtonReadFromSequence()
	{
		return oButtonReadFromSequence;
	}



	public MyE2_Button get_oButtonSaveNewSequenceValue()
	{
		return oButtonSaveNewSequenceValue;
	}

	
	
	
	
	private class ownActionAgentReadOrBuildSequence extends XX_ActionAgent
	{
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_SequenceBuilder oThis = E2_SequenceBuilder.this;
			bibMSG.add_MESSAGE(oThis.readOrBuild_SequenceValue());
		}
		
	}
	
	
	

	private class ownActionAgentSaveNewSequenceValue extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_SequenceBuilder oThis = E2_SequenceBuilder.this;
			bibMSG.add_MESSAGE(oThis.Save_NewSequenceValue());
		}
	}



	private class ownActionAgentReadFromDatabase extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_SequenceBuilder oThis = E2_SequenceBuilder.this;
			bibMSG.add_MESSAGE(oThis.Build_New_SequenceBased_on_DatabaseQuery());
			
		}
	}


	

	
	
	
	
}
