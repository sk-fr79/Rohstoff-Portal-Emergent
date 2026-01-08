package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.db.Project_TableSequenceBuilder;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class PluginCol_Sequence_NumTab_Builder extends Basic_PluginColumn
{

	public PluginCol_Sequence_NumTab_Builder(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);
		
		MyE2_Button oButtonBuildTableSeqs = new MyE2_Button(new MyE2_String("Baue Tabellen-Sequencer neu"));
		oButtonBuildTableSeqs.add_oActionAgent(new actionAgentBuildTableSequences());
		
		PopDown_AlleTabellen  oPop = new PopDown_AlleTabellen(new actionAgentBuildSingle()) ;

		
		
		/*
		 * jetzt die globalen nummernkreise aufbauen und anzeigen
		 */
		String[] cNUMGLOB = myCONST.NUMMERNKREISE_GLOBAL;
		
		MyE2_Grid oGridGlob = new MyE2_Grid(4,0);
		
		
		oGridGlob.add(new MyE2_Label(new MyE2_String("Globale Nummernkreise (Nummern über alle Mandanten gleich)")),4,ContainerForVerwaltungsTOOLS.INSETS_LIST);
		for (int i=0;i<cNUMGLOB.length;i++)
		{
			E2_SequenceBuilder oSeq = 
				new E2_SequenceBuilder(	cNUMGLOB[i],
										null,
										(String) bibALL.getSessionValue("MINIMALTABLESEQ"),
										true,
										oMothercontainer.get_MODUL_IDENTIFIER());
			
			
			oGridGlob.add(new LabelSEQ(cNUMGLOB[i]),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
			oGridGlob.add(oSeq.get_oButtonReadFromSequence(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
			oGridGlob.add(oSeq.get_oTextFieldNewValue(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
			oGridGlob.add(oSeq.get_oButtonSaveNewSequenceValue(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
		}
		

		// jetzt den selector fuer die Mandanten-spezifische SEQUENCES bauen
		MyE2_Grid oGridMandant = new MyE2_Grid(4,0);
		
		E2_DropDownSettings oDD = new E2_DropDownSettings("JD_MANDANT","NAME1","ID_MANDANT",null,true);
		MyE2_SelectField oSelField = new MyE2_SelectField(oDD.getDD(),"",false);
		oSelField.add_oActionAgent(new actionClearOutput());
		oSelField.add_oActionAgent(new actionAgentBuildMandantenSequences(oGridMandant));
		
		
		// column fuer das tab-pane aufbauen
		MyE2_Row 		oRowHelp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		MyE2_Column    	oColLeft = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		MyE2_Column    	oColRight = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		oRowHelp.add(oColLeft);
		oRowHelp.add(oColRight);
		
		this.add(oRowHelp);
		
		//linke spalte: sequencer-aufbau
		oColLeft.add(new E2_ComponentGroupHorizontal(0,oButtonBuildTableSeqs,oPop,E2_INSETS.I_1_1_10_1),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		oColLeft.add(new Separator(), ContainerForVerwaltungsTOOLS.INSETS_LIST);
		oColLeft.add(oGridGlob,ContainerForVerwaltungsTOOLS.INSETS_LIST);
		oColLeft.add(new Separator(), ContainerForVerwaltungsTOOLS.INSETS_LIST);
		oColLeft.add(new E2_ComponentGroupHorizontal(0,   new MyE2_Label(new MyE2_String("Mandanten-Sequencen Laden/Bauen")),
				                                      oSelField,
				                                      new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);

		
		this.add(oGridMandant,ContainerForVerwaltungsTOOLS.INSETS_LIST);

		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
	}
	
	
	
	private class actionAgentBuildTableSequences extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{

			new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Aufbau der Sequenzer läuft ..."),true,true,false,1000)
			{
				@Override
				public void Run_Loop() throws myException
				{

					
					String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true);
					
					String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable,false);
		
					if (cTabellen==null || cTabellen.length==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage der Tabellen des OWNERS "+bibE2.cTO()));
					}
					else
					{
						StringBuffer oStringOutput = new StringBuffer();
						
						for (int k=0;k<cTabellen.length;k++)
						{
							Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(
																	      cTabellen[k][0],
																	      PluginCol_Sequence_NumTab_Builder.this.get_oMotherContainer().get_MODUL_IDENTIFIER());
							
							oStringOutput.append(oSeq.Build_New_SequenceBased_on_DatabaseQuery().get_MessagesAsText());
							
							//fortschrittsinfo
							this.get_oGridBaseForMessages().removeAll();
							this.get_oGridBaseForMessages().add(new MyE2_Label(new MyE2_String(cTabellen[k][0]+"   ("+k+"/"+cTabellen.length+")",false)));

						}
						
						PluginCol_Sequence_NumTab_Builder.this.get_TextArea4Output().setText(PluginCol_Sequence_NumTab_Builder.this.get_TextArea4Output().getText()+"\n"+oStringOutput.toString());
					}
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
				{
				}

			};
		}
	}
	
	

	//action-agent fuer die einzel-Tabellen-Aktionen (im EXT-Objekt des ausloesenden buttons steht der tabellenname im Feld C_MERKMAL
	private class actionAgentBuildSingle extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_Button oButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(
												      oButton.EXT().get_C_MERKMAL(),
												      PluginCol_Sequence_NumTab_Builder.this.get_oMotherContainer().get_MODUL_IDENTIFIER());
			
			bibMSG.add_MESSAGE(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
			
		}
	}

	
	
	private class LabelSEQ extends MyE2_Label
	{

		public LabelSEQ(String cSEQ_NAME)
		{
			super(cSEQ_NAME);
			this.setFont(new E2_FontItalic(0));
			
		}
		
	}
	
	
	
	private class actionClearOutput extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			PluginCol_Sequence_NumTab_Builder.this.get_TextArea4Output().setText("");
		}
	}


	
	

	
	
	
	
	
	
	
	
	/*
	 * actionagent fuer die selectbox der mandanten,
	 * wird einer aufgerufen, dann werden alle sequencen des mandanten durchgezaehlt und
	 * eingelesen/erzeugt
	 */
	private class actionAgentBuildMandantenSequences extends XX_ActionAgent
	{
		private MyE2_Grid oGridSeqs = null;
		
		
		
		public actionAgentBuildMandantenSequences(MyE2_Grid gridseqs)
		{
			super();
			oGridSeqs = gridseqs;
		}



		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				MyE2_SelectField ownSelect = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
				
				String cActualMandant = bibALL.ReplaceTeilString(ownSelect.get_ActualWert(),".","").trim();
				this.oGridSeqs.removeAll();
				
				if (cActualMandant.equals(""))
					return;

				
				// ab hier wird neu aufgebaut ...
				PluginCol_Sequence_NumTab_Builder oThis = PluginCol_Sequence_NumTab_Builder.this;
				
				/*
				 * jetzt die Mandanten-nummernkreise aufbauen und anzeigen
				 */
				String[] cNUMMAND = bibARR.concatenate(myCONST.NUMMERNKREISE_MANDANT,myCONST_ENUM.SEQ_MANDANT.getNames());
				
				StringBuffer  cMessages = new StringBuffer();
				
				this.oGridSeqs.add(new MyE2_Label(new MyE2_String("Mandanteneigene Nummernkreise (Nummern für jeden Mandanten separat)")),4,ContainerForVerwaltungsTOOLS.INSETS_LIST);
				for (int i=0;i<cNUMMAND.length;i++)
				{
					E2_SequenceBuilder oSeq = 
						new E2_SequenceBuilder(	cActualMandant+"_"+cNUMMAND[i],
												null,
												(String) bibALL.getSessionValue("MINIMALTABLESEQ"),
												false,
												oThis.get_oMotherContainer().get_MODUL_IDENTIFIER());
					
					this.oGridSeqs.add(new LabelSEQ("SEQ_"+cActualMandant+"_"+cNUMMAND[i]),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
					this.oGridSeqs.add(oSeq.get_oButtonReadFromSequence(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
					this.oGridSeqs.add(oSeq.get_oTextFieldNewValue(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
					this.oGridSeqs.add(oSeq.get_oButtonSaveNewSequenceValue(),1,ContainerForVerwaltungsTOOLS.INSETS_LIST);
					
					// lesen und wenn nicht vorhanden bauen einer neuen sequence
					cMessages.append(oSeq.readOrBuild_SequenceValue().get_MessagesAsText());
				}
				
				oThis.get_TextArea4Output().setText(cMessages.toString());
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
}
