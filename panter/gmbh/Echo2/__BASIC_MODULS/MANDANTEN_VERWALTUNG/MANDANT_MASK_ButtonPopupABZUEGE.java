package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ABZUGSSCHABLONEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG.AbzugsDef;

public class MANDANT_MASK_ButtonPopupABZUEGE extends MyE2_Button
{

	private ownPopupContainer oPopup = null;
	
	public MANDANT_MASK_ButtonPopupABZUEGE()
	{
		super(new MyE2_String("Abzugsformulierungen"));
		
		this.setFont(new E2_FontBold(4));
		
		this.setToolTipText(new MyE2_String("Beschriftung der Abzüge im Drop-Down und im Ausdruck definieren").CTrans());
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MANDANT_MASK_ButtonPopupABZUEGE.this.oPopup = new ownPopupContainer();
			}
		});
	}

	
	
	private class ownPopupContainer extends E2_BasicModuleContainer
	{

		public ownPopupContainer() throws myException
		{
			super();
			this.add(new Tool_SetAbzugsSchablonen(), E2_INSETS.I_2_2_2_2);
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Darstellung der Abzüge definieren ..."));
		}
	}

	
	
	private class Tool_SetAbzugsSchablonen extends MyE2_Column
	{
		private Vector<SPEICHER_OBJECTE>   vSPEICHEROBJEKTE = new Vector<SPEICHER_OBJECTE>();
		
		private MyE2_Button   			oButtonSave = new MyE2_Button("Speichern");
		private MyE2_Grid 				oGrid = new MyE2_Grid(5,1);
		
		
		public Tool_SetAbzugsSchablonen() 
		{
			super();
			oButtonSave.add_oActionAgent(new ownAction());
			this.__aufbau();
		}
		
		
		
		private void __aufbau() 
		{
			
			this.removeAll();
			this.oGrid.removeAll();
			this.vSPEICHEROBJEKTE.removeAllElements();
			
			/*
			 * Platzhalter:
			 * 	#EHP# = preiseinheit    
	    		#BW# = Basiswährung    
	    		#FW# = Fremdwährung
	    		#PM# = Positionsmenge
	        	#LM# = Laufende Menge
	        	#PP" = Positionspreis
	        	"LP" = Laufender Preis  
	    		#EINGABE#=Mengenfeld 1    
	    		#EINGABE2#=Mengenfeld 2

			 */
			
			this.add(new MyE2_Label(new MyE2_String("In den Schablonen dürfen folgende Platzhalter stehen:")), E2_INSETS.I_10_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("#EH#=Mengeneinheit/#EHP#=Preiseinheit/#BW#=Basiswährung/#FW#=Fremdwährung/Abrechnungswährung")), E2_INSETS.I_10_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("#EINGABE#=Mengenfeld 1/#EINGABE2#=Mengenfeld 2")), E2_INSETS.I_10_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("#PM#=Ausgangsmenge/#LM#=Laufende Menge (Vorgänger) /#PP#=Ausgangs-Positionspreis")), E2_INSETS.I_10_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("#LP#=Laufender Positionspreis (Vorgänger)")), E2_INSETS.I_10_2_10_2);
			
			MyE2_ContainerEx  oContEx = new MyE2_ContainerEx(oGrid);
			oContEx.setWidth(new Extent(100,Extent.PERCENT));
			oContEx.setHeight(new Extent(400));
			this.add(oContEx, E2_INSETS.I_10_20_10_5);
			this.add(new E2_ComponentGroupHorizontal(0,oButtonSave, E2_INSETS.I_0_0_0_0), E2_INSETS.I_10_20_10_5);
			
			GridLayoutData  glAussen = LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_2);
			
			//ueberschrift
			oGrid.add(new MyE2_Label(new MyE2_String("Bezeichner-Konstante"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), glAussen);
			oGrid.add(new ownAnzeigeGrid(	new MyE2_Label(new MyE2_String("Anzeige im DropDown-Selektor"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()),
											new MyE2_Label(new MyE2_String("Schablone für den eingedruckten Text"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC())), glAussen);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Hilfstext"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), 	glAussen);
			oGrid.add(new MyE2_Label(new MyE2_String("Sortierung"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), 	glAussen);
			oGrid.add(new MyE2_Label(new MyE2_String("Sichtbar"),MyE2_Label.STYLE_SMALL_BOLD_ITALIC()), 	glAussen);
			try
			{
				HashMap<String, BL_CONST_ABZUG.AbzugsDef> hm_ABZUGSDEF = BL_CONST_ABZUG.get_HM_ABZUGS_DEFS();

				Vector<BL_CONST_ABZUG.AbzugsDef> vHelp = new Vector<BL_CONST_ABZUG.AbzugsDef>();
				
				Iterator<BL_CONST_ABZUG.AbzugsDef> oIter = hm_ABZUGSDEF.values().iterator();
				while (oIter.hasNext())
				{
					vHelp.add(oIter.next());
				}
				
				//jetzt nach der sort-spalte sortieren ([3])
				Collections.sort(vHelp, new Comparator<BL_CONST_ABZUG.AbzugsDef>()
				{
					@Override
					public int compare(BL_CONST_ABZUG.AbzugsDef o1, BL_CONST_ABZUG.AbzugsDef o2)
					{
						return S.NN(o1.SORTIERUNG).compareTo(S.NN(o2.SORTIERUNG));
					}
				});
				

				for (BL_CONST_ABZUG.AbzugsDef oDef: vHelp)
				{
					SPEICHER_OBJECTE  oInputObject = new SPEICHER_OBJECTE(oDef);
					
					vSPEICHEROBJEKTE.add(oInputObject);
					
					oGrid.add(oInputObject.TFAbzug_Typ, 				glAussen);
					oGrid.add(new ownAnzeigeGrid(oInputObject.TFAbzug_ABZUG_DROP_DOWN_TEXT,oInputObject.TFAbzug_ABZUG_BELEGTEXT_SCHABLONE), glAussen);

					oGrid.add(oInputObject.TFAbzug_HILFETEXT, 			glAussen);
					oGrid.add(oInputObject.TFAbzug_ABZUG_SORTIERUNG,	glAussen);
					oGrid.add(oInputObject.CB_Sichtar, 					glAussen);
				}
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbau der Abzugsdefinitionen !"));
			}

		}
		
		
		private class ownAnzeigeGrid extends MyE2_Grid
		{

			public ownAnzeigeGrid(Component oComp1, Component oComp2)
			{
				super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				
				this.add(oComp1,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_0_0));
				this.add(oComp2,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_1_0_0));
			}
			
		}
		
		
		private class SPEICHER_OBJECTE 
		{
			public labelText 					TFAbzug_Typ = null;
			public MyE2_TextField 				TFAbzug_ABZUG_DROP_DOWN_TEXT = null;
			public MyE2_TextField 				TFAbzug_ABZUG_BELEGTEXT_SCHABLONE = null;
			public MyE2_TextField 				TFAbzug_ABZUG_SORTIERUNG = null;
			public MyE2_TextArea  				TFAbzug_HILFETEXT = null;
			public MyE2_CheckBox  				CB_Sichtar = null;
			
			private BL_CONST_ABZUG.AbzugsDef  	oAbzugDef = null;

			public SPEICHER_OBJECTE(AbzugsDef oAbzugDef)
			{
				super();
				this.oAbzugDef = oAbzugDef;
				
				this.TFAbzug_Typ =  						new labelText(oAbzugDef.ABZUG_TYP,250,200);
				this.TFAbzug_ABZUG_DROP_DOWN_TEXT = 		new MyE2_TextField(oAbzugDef.ABZUG_DROP_DOWN_TEXT,250,200);
				this.TFAbzug_ABZUG_BELEGTEXT_SCHABLONE = 	new MyE2_TextField(oAbzugDef.ABZUG_BELEGTEXT_SCHABLONE,250,200);
				this.TFAbzug_ABZUG_SORTIERUNG = 			new MyE2_TextField(oAbzugDef.SORTIERUNG,30,50);
				this.TFAbzug_HILFETEXT = 					new MyE2_TextArea(oAbzugDef.HILFETEXT, 300, 800, 3);
				this.CB_Sichtar = 							new MyE2_CheckBox(oAbzugDef.get_bIST_SICHTBAR(),false);
				
				this.TFAbzug_ABZUG_DROP_DOWN_TEXT.setFont(new E2_FontPlain(-2));
				this.TFAbzug_ABZUG_BELEGTEXT_SCHABLONE.setFont(new E2_FontPlain(-2));
				this.TFAbzug_ABZUG_SORTIERUNG.setFont(new E2_FontPlain(-2));
				this.TFAbzug_HILFETEXT.setFont(new E2_FontPlain(-2));
				
			}
			

			
			public boolean get_bIST_Alles_Gefuellt_was_muss()
			{
				boolean bRueck = 	S.isFull(this.TFAbzug_Typ.getText()) &&
									S.isFull(this.TFAbzug_ABZUG_DROP_DOWN_TEXT.getText()) &&
									S.isFull(this.TFAbzug_ABZUG_BELEGTEXT_SCHABLONE.getText()) &&
									S.isFull(this.TFAbzug_ABZUG_SORTIERUNG.getText()) ;

				return bRueck;
				
			}
			
			public RECORDNEW_ABZUGSSCHABLONEN  get_recNew() throws myException
			{
				RECORDNEW_ABZUGSSCHABLONEN recnew = new RECORDNEW_ABZUGSSCHABLONEN();
				recnew.set_NEW_VALUE_ABZUG_TYP(this.TFAbzug_Typ.getText());
				recnew.set_NEW_VALUE_ABZUG_DROP_DOWN_TEXT(this.TFAbzug_ABZUG_DROP_DOWN_TEXT.getText());
				recnew.set_NEW_VALUE_ABZUG_BELEGTEXT_SCHABLONE(this.TFAbzug_ABZUG_BELEGTEXT_SCHABLONE.getText());
				recnew.set_NEW_VALUE_SORTIERUNG(this.TFAbzug_ABZUG_SORTIERUNG.getText());
				recnew.set_NEW_VALUE_HILFETEXT(this.TFAbzug_HILFETEXT.getText());
				recnew.set_NEW_VALUE_SICHTBAR(this.CB_Sichtar.isSelected()?"Y":"N");
				
				return recnew;
			}
			
		}
		
		
		
		private class labelText extends MyE2_TextField
		{

			public labelText(String cText, int iwidthPixel, int imaxInputSize)
			{
				super(cText, iwidthPixel, imaxInputSize);
				this.setEnabled(false);
				this.setFont(new E2_FontItalic(-2));
				this.setBackground(new E2_ColorBase());
				this.setDisabledForeground(Color.BLACK);
				
				this.setToolTipText(cText);
			}
			
		}
		
		
		private class ownAction extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				Tool_SetAbzugsSchablonen oThis = Tool_SetAbzugsSchablonen.this;
				
				boolean bAllesGefuellt = true;

				for (SPEICHER_OBJECTE oSpeicherObjekt: oThis.vSPEICHEROBJEKTE)
				{
					bAllesGefuellt = bAllesGefuellt&&oSpeicherObjekt.get_bIST_Alles_Gefuellt_was_muss();
					break;
				}
				
				if (bAllesGefuellt)
				{
					Vector<String> vSQL = new Vector<String>();
					
					vSQL.add("DELETE FROM "+bibE2.cTO()+".JT_ABZUGSSCHABLONEN ");
					for (SPEICHER_OBJECTE oSpeicherObjekt:oThis.vSPEICHEROBJEKTE)
					{
						vSQL.add(oSpeicherObjekt.get_recNew().get_InsertSQLStatementWith_Id_Field(true, true));
					}
					MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSQL,true);
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Abzugsdefinitionen wurden gespeichert !"));
						MANDANT_MASK_ButtonPopupABZUEGE.this.oPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
						oThis.__aufbau();
					}
					
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte alles komplett füllen (nur Hilfstexte können frei bleiben !")));
				}
				
			}
		}
	}	
}
