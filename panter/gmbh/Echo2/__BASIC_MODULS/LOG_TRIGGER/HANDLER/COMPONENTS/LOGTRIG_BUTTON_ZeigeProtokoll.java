package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER.COMPONENTS;

import java.util.Date;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_CHANGELOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_CHANGELOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class LOGTRIG_BUTTON_ZeigeProtokoll extends MyE2_Button
{

	private String 				FELDNAME = null;
	private String 				TABELLE_BaseName = null;    //name ohne JT_ oder JD_
	 
	private boolean 			bHatBegruendungseingabe = false;
	private E2_ComponentMAP  	oMAP = null; 
	private RECORD_TRIGGER_DEF  RecTriggerDef = null;
	
	
	public LOGTRIG_BUTTON_ZeigeProtokoll(RECORD_TRIGGER_DEF recTriggerDef, String cTABELLE_BaseName, E2_ComponentMAP  MAP) throws myException
	{
		//super(E2_ResourceIcon.get_RI("feld_protokoll.png"));
		super(E2_ResourceIcon.get_RI("inforound_mini.png"));
		this.FELDNAME = 		recTriggerDef.get_SPALTE_cF_NN("@@@ZUSATZ@@@");
		this.TABELLE_BaseName = cTABELLE_BaseName;
		this.oMAP = 			MAP;
		this.RecTriggerDef   =  recTriggerDef; 
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				new ownContainer();
			}
		});
		
		this.setToolTipText(new RECORD_TRIGGER_DEF_SPECIAL(this.RecTriggerDef).get_InfoText_ToolTip().CTrans());
		
	}

	//ist immer enabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}


	
	private class ownContainer extends E2_BasicModuleContainer
	{

		public ownContainer() throws myException
		{
			super();
			
			LOGTRIG_BUTTON_ZeigeProtokoll oThis = LOGTRIG_BUTTON_ZeigeProtokoll.this;
			
			String cID_Table = null;
			
			//jetzt den aktuellen maskenstatus ermitteln
			E2_ComponentMAP oMAP = oThis.oMAP;
			
			if (oMAP instanceof RB_ComponentMap) {    //neue struktur
				RB_ComponentMap oRBMap = (RB_ComponentMap)oMAP;
				RB_Dataobject   oData = oRBMap.getRbDataObjectActual();
				if (oData != null) {
					MyRECORD_IF_RECORDS rec = oData.get_RecORD();
					if (rec!=null) {
						cID_Table = rec.get_PRIMARY_KEY_UF();
					}
				}
				if (S.isEmpty(cID_Table)) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Änderunglog kann nur bei Bearbeitungsmasken aufgerufen werden (2)!")));
					return;
				}
				
			} else {
			
				if (oMAP.get_oInternalSQLResultMAP()==null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Änderunglog kann nur bei Bearbeitungsmasken aufgerufen werden !")));
					return;
				}
				
				cID_Table= oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			}

			
			if (S.isEmpty(cID_Table))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Achtung! Änderunglog kann nicht angezeigt werden, da die Tabellen-ID nicht vorhanden ist !")));
				return;
			}
			
			
			
			RECLIST_CHANGELOG  rlAenderungsProtokoll = new RECLIST_CHANGELOG(
									"SELECT * FROM "+bibE2.cTO()+".JT_CHANGELOG " +
									" WHERE   "+RECORD_CHANGELOG.FIELD__TABLENAME+"="+bibALL.MakeSql(oThis.TABELLE_BaseName)+
									" AND     "+RECORD_CHANGELOG.FIELD__COLNAME+"="+bibALL.MakeSql(oThis.FELDNAME)+
									" AND     "+RECORD_CHANGELOG.FIELD__ID_TABLE+"="+cID_Table+
									" ORDER BY "+RECORD_CHANGELOG.FIELD__LETZTE_AENDERUNG+" DESC"
									);
			
			//2015-09-23: aenderungslog zeigt auch werte vor der aenderung an
			
			if (rlAenderungsProtokoll.get_vKeyValues().size()>0)
			{

				String[] Titel = null;
				if (oThis.bHatBegruendungseingabe)
				{
					Titel = new String[6]; Titel[0]="Datum/Uhrzeit";Titel[1]="Benutzer";Titel[2]="Kürzel";Titel[3]="Wert vor Änderung";Titel[4]="Wert nach Änderung";Titel[5]="Begründung";
				}
				else
				{
					Titel = new String[5]; Titel[0]="Datum/Uhrzeit";Titel[1]="Benutzer";Titel[2]="Kürzel";Titel[3]="Wert vor Änderung";Titel[4]="Wert nach Änderung";
				}
				
				
				MutableStyle oStyle = new MutableStyle();
				oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
				oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
				oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));

				MyE2_Grid  oGridInnen = new MyE2_Grid(Titel.length, oStyle);
				

				
				//ueberschrift dunkler hinterlegt
				GridLayoutData oGLTitelLeft = MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_0_0_2_2);
				GridLayoutData oGLTitelCenter = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_2_2);
				oGLTitelLeft.setBackground(new E2_ColorDDark());
				oGLTitelCenter.setBackground(new E2_ColorDDark());

				
				GridLayoutData oGLListLeft = MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_0_0_2_2);
				GridLayoutData oGLListCenter = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_0_0_2_2);
				
				//ueberschrift aufbauen
				oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[0])), oGLTitelLeft);
				oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[1])), oGLTitelCenter);
				oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[2])), oGLTitelCenter);
				oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[3])), oGLTitelCenter);
				oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[4])), oGLTitelCenter);
				if (oThis.bHatBegruendungseingabe)
				{
					oGridInnen.add(new MyE2_Label(new MyE2_String(Titel[5])), oGLTitelLeft);
				}
			
				
				E2_MutableStyle oStyleTextLetzteAenderung = MyE2_Label.STYLE_NORMAL_BOLD();
				E2_MutableStyle oStyleTextSonst = 			MyE2_Label.STYLE_NORMAL_GREY();
				
				//liste aufbauen
				for (int i=0;i<rlAenderungsProtokoll.get_vKeyValues().size();i++)
				{
					RECORD_CHANGELOG  recP = rlAenderungsProtokoll.get(i);
					
					Date Datum = recP.get_TimeStampValue(RECORD_CHANGELOG.FIELD__LETZTE_AENDERUNG, null);
					
					String DatumText = myDateHelper.FormatDateTimeLang(Datum);
					String cBenutzer = recP.get_UP_RECORD_USER_id_user().get___KETTE(bibVECTOR.get_Vector(RECORD_USER.FIELD__VORNAME, RECORD_USER.FIELD__NAME1));
					String cKuerzel  = recP.get_ERZEUGT_VON_cUF_NN("<Kürzel>");
					String AlterWert = recP.get_OLD_VALUE_cUF_NN("-");
					String NeuerWert = recP.get_NEW_VALUE_cUF_NN("-");
					
					//String cBegruendung = recP.get_EINGABE_GRUND_cUF_NN("");
					
					if (i==0)
					{
						oGridInnen.add(new MyE2_Label(DatumText,oStyleTextLetzteAenderung), oGLListLeft);
						oGridInnen.add(new MyE2_Label(cBenutzer,oStyleTextLetzteAenderung), oGLListCenter);
						oGridInnen.add(new MyE2_Label(cKuerzel,oStyleTextLetzteAenderung), oGLListCenter);
						oGridInnen.add(new MyE2_Label(AlterWert,oStyleTextLetzteAenderung), oGLListCenter);
						oGridInnen.add(new MyE2_Label(NeuerWert,oStyleTextLetzteAenderung), oGLListCenter);
						if (oThis.bHatBegruendungseingabe)
						{
							oGridInnen.add(new MyE2_Label("",oStyleTextLetzteAenderung), oGLListLeft);
						}
					}
					else
					{
						oGridInnen.add(new MyE2_Label(DatumText,oStyleTextSonst), oGLListLeft);
						oGridInnen.add(new MyE2_Label(cBenutzer,oStyleTextSonst), oGLListCenter);
						oGridInnen.add(new MyE2_Label(cKuerzel,oStyleTextSonst), oGLListCenter);
						oGridInnen.add(new MyE2_Label(AlterWert,oStyleTextSonst), oGLListCenter);
						oGridInnen.add(new MyE2_Label(NeuerWert,oStyleTextSonst), oGLListCenter);
						if (oThis.bHatBegruendungseingabe)
						{
							oGridInnen.add(new MyE2_Label("",oStyleTextSonst), oGLListLeft);
						}
						
					}
					
				}
				
				
				this.add(new MyE2_Label(rlAenderungsProtokoll.get(0).get_BESCHREIBUNG_cUF_NN("<info>")), E2_INSETS.I_2_2_2_2);
				this.add(oGridInnen, E2_INSETS.I_2_2_2_2);
			}
			else
			{
				this.add(new MyE2_Label(new RECORD_TRIGGER_DEF_SPECIAL(oThis.RecTriggerDef).get_InfoText_LEER().CTrans()), E2_INSETS.I_2_2_2_2);
			}
			
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(500),new RECORD_TRIGGER_DEF_SPECIAL(oThis.RecTriggerDef).get_InfoText_Titel());
		}
		
	}
	
	
	

	public boolean get_bHatBegruendungseingabe()
	{
		return bHatBegruendungseingabe;
	}


	public void set_bHatBegruendungseingabe(boolean bHatBegruendungseingabe)
	{
		this.bHatBegruendungseingabe = bHatBegruendungseingabe;
	}
	
	
	
	

}
