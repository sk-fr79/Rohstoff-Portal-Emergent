package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button_To_FastEdit;
import panter.gmbh.Echo2.components.unboundDataFields.IF_UB_Fields;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.BigDecimalFactory;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashList;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;


public class FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN extends  MyE2_DB_Button_To_FastEdit
{

	private UB_TextField   						tfKennzeichenLKW = 		new UB_TextField("TRANSPORTKENNZEICHEN",true,"",200,100);
	private UB_TextField   						tfKennzeichenANH = 		new UB_TextField("ANHAENGERKENNZEICHEN",true,"",200,100);
	
	private RECORD_VPOS_TPA_FUHRE   			recVPOS_TPA_FUHRE = 			null;
	
	private Vector<IF_UB_Fields>      			vTF_EK_Fields = new Vector<IF_UB_Fields>();
	private Vector<IF_UB_Fields>     			vTF_VK_Fields = new Vector<IF_UB_Fields>();
	
	private SQLField   							ownSQLField = null;
	private MyE2_Grid   						oBaseGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	
	public FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(SQLField osqlField) throws myException
	{
		super(osqlField, new MyE2_String("Eingabe der Laden/Ablade-Mengen und Daten"), MyE2_Button.StyleTextButton(), "EINGABE_MENGEN_LADE_ABLADE_DATUM", 
							new Extent(650), new Extent(500));
		
		this.setToolTipText(new MyE2_String("Schnelleingabe von Menge und Preis und Zeiten").CTrans());
		this.add_IDValidator(new ownValidator());
		
		//aenderung 2010-11-25: Buttons rechtsbuendig
		this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		this.ownSQLField = osqlField;
	}

	
	
	private void prepareFields() throws myException
	{
		this.recVPOS_TPA_FUHRE = new RECORD_VPOS_TPA_FUHRE(this.get_cActualRowID());
		
		this.vTF_EK_Fields.removeAllElements();
		this.vTF_VK_Fields.removeAllElements();
		this.oBaseGrid.removeAll();
		
		
		RECLIST_VPOS_TPA_FUHRE_ORT reclistFUHRE_ORT_EK = this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='EK' ","ID_VPOS_TPA_FUHRE_ORT",true);
		RECLIST_VPOS_TPA_FUHRE_ORT reclistFUHRE_ORT_VK = this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N' AND DEF_QUELLE_ZIEL='VK' ","ID_VPOS_TPA_FUHRE_ORT",true);

		if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_LADEMENGE_NO())   //nur ungepruefte duerfen erfasst werden
		{
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_PLANMENGE_LIEF_cF_NN(""),		"ANTEIL_PLANMENGE_LIEF",true));
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_LIEF_cF_NN(""),		"ANTEIL_LADEMENGE_LIEF",true));
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(""),	"ANTEIL_ABLADEMENGE_LIEF",this.recVPOS_TPA_FUHRE.is_LADEMENGE_GUTSCHRIFT_NO()));
			vTF_EK_Fields.add(new ownTF4Datum(	this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_DATUM_AUFLADEN_cF_NN(""),				"DATUM_AUFLADEN",true,true));
		}
		else
		{
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_PLANMENGE_LIEF_cF_NN(""),		"ANTEIL_PLANMENGE_LIEF",false));
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_LIEF_cF_NN(""),		"ANTEIL_LADEMENGE_LIEF",false));
			vTF_EK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(""),	"ANTEIL_ABLADEMENGE_LIEF",false));
			vTF_EK_Fields.add(new ownTF4Datum(	this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_DATUM_AUFLADEN_cF_NN(""),				"DATUM_AUFLADEN",false,false));
		}
		
		if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_ABLADEMENGE_NO())   //nur ungepruefte duerfen erfasst werden
		{
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_PLANMENGE_ABN_cF_NN(""),		"ANTEIL_PLANMENGE_ABN",false));
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_ABN_cF_NN(""),		"ANTEIL_LADEMENGE_ABN",this.recVPOS_TPA_FUHRE.is_ABLADEMENGE_RECHNUNG_NO()));
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_ABN_cF_NN(""),		"ANTEIL_ABLADEMENGE_ABN",true));
			vTF_VK_Fields.add(new ownTF4Datum(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_DATUM_ABLADEN_cF_NN(""),				"DATUM_ABLADEN",true,false));
		}
		else
		{
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_PLANMENGE_ABN_cF_NN(""),		"ANTEIL_PLANMENGE_ABN",false));
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_ABN_cF_NN(""),		"ANTEIL_LADEMENGE_ABN",false));
			vTF_VK_Fields.add(new ownTF4Numbers(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_ABN_cF_NN(""),		"ANTEIL_ABLADEMENGE_ABN",false));
			vTF_VK_Fields.add(new ownTF4Datum(this.recVPOS_TPA_FUHRE,this.recVPOS_TPA_FUHRE.get_DATUM_ABLADEN_cF_NN(""),				"DATUM_ABLADEN",false,false));
		}
		
		
		
		for (int i=0;i<reclistFUHRE_ORT_EK.get_vKeyValues().size();i++)
		{
			if (reclistFUHRE_ORT_EK.get(i).is_PRUEFUNG_MENGE_NO())   //nur ungepruefte duerfen erfasst werden
			{
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_PLANMENGE_cF_NN(""),"ANTEIL_PLANMENGE",true));
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_LADEMENGE_cF_NN(""),"ANTEIL_LADEMENGE",true));
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_ABLADEMENGE_cF_NN(""),"ANTEIL_ABLADEMENGE",reclistFUHRE_ORT_EK.get(i).is_LADEMENGE_GUTSCHRIFT_NO()));
				vTF_EK_Fields.add(new ownTF4Datum(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_DATUM_LADE_ABLADE_cF_NN(""),"DATUM_LADE_ABLADE",true,false));
			}
			else
			{
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_PLANMENGE_cF_NN(""),"ANTEIL_PLANMENGE",false));
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_LADEMENGE_cF_NN(""),"ANTEIL_LADEMENGE",false));
				vTF_EK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_ANTEIL_ABLADEMENGE_cF_NN(""),"ANTEIL_ABLADEMENGE",false));
				vTF_EK_Fields.add(new ownTF4Datum(reclistFUHRE_ORT_EK.get(i),	reclistFUHRE_ORT_EK.get(i).get_DATUM_LADE_ABLADE_cF_NN(""),"DATUM_LADE_ABLADE",false,false));
			}
		}
		for (int i=0;i<reclistFUHRE_ORT_VK.get_vKeyValues().size();i++)
		{
			if (reclistFUHRE_ORT_VK.get(i).is_PRUEFUNG_MENGE_NO())   //nur ungepruefte duerfen erfasst werden
			{
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_PLANMENGE_cF_NN(""),"ANTEIL_PLANMENGE",false));
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_LADEMENGE_cF_NN(""),"ANTEIL_LADEMENGE",reclistFUHRE_ORT_VK.get(i).is_ABLADEMENGE_RECHNUNG_NO()));
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_ABLADEMENGE_cF_NN(""),"ANTEIL_ABLADEMENGE",true));
				vTF_VK_Fields.add(new ownTF4Datum(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_DATUM_LADE_ABLADE_cF_NN(""),"DATUM_LADE_ABLADE",true,false));
			}
			else
			{
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_PLANMENGE_cF_NN(""),"ANTEIL_PLANMENGE",false));
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_LADEMENGE_cF_NN(""),"ANTEIL_LADEMENGE",false));
				vTF_VK_Fields.add(new ownTF4Numbers(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_ANTEIL_ABLADEMENGE_cF_NN(""),"ANTEIL_ABLADEMENGE",false));
				vTF_VK_Fields.add(new ownTF4Datum(reclistFUHRE_ORT_VK.get(i),	reclistFUHRE_ORT_VK.get(i).get_DATUM_LADE_ABLADE_cF_NN(""),"DATUM_LADE_ABLADE",false,false));
			}
		}
		
		
		if  	(this.ownSQLField.get_cFieldLabel().equals("ANTEIL_PLANMENGE_LIEF"))
		{
			((MyE2IF__Component)this.vTF_EK_Fields.get(0)).EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("ANTEIL_LADEMENGE_LIEF"))
		{
			((MyE2IF__Component)this.vTF_EK_Fields.get(1)).EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("ANTEIL_ABLADEMENGE_LIEF"))
		{
			((MyE2IF__Component)this.vTF_VK_Fields.get(2)).EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("DATUM_AUFLADEN"))
		{
			((MyE2IF__Component)this.vTF_EK_Fields.get(3)).EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("DATUM_ABLADEN"))
		{
			((MyE2IF__Component)this.vTF_VK_Fields.get(3)).EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("TRANSPORTKENNZEICHEN"))
		{
			this.tfKennzeichenLKW.EXT().set_bHasFocusOnWindowPopup(true);
		}
		else if (this.ownSQLField.get_cFieldLabel().equals("ANHAENGERKENNZEICHEN"))
		{
			this.tfKennzeichenANH.EXT().set_bHasFocusOnWindowPopup(true);
		}


		E2_Subgrid_4_Mask oSubGridKennzeichen = new E2_Subgrid_4_Mask(	bibALL.get_Vector(new MyE2_String("LKW-Kennzeichen"),new MyE2_String("Anhängerkennzeichen")),
																		bibALL.get_Vector(this.tfKennzeichenLKW, this.tfKennzeichenANH,null, null),
																		E2_INSETS.I_0_0_10_5, E2_INSETS.I_0_0_10_5, true);


		Alignment alHelp = new Alignment(Alignment.LEFT, Alignment.TOP);
		this.oBaseGrid.add(new ownAnzeigeGrid("EK",	this.vTF_EK_Fields),	1, 1, 	E2_INSETS.I_0_10_10_10, alHelp);
		this.oBaseGrid.add(new ownAnzeigeGrid("VK",	this.vTF_VK_Fields),	1, 1,	E2_INSETS.I_0_10_10_10, alHelp);
		
		this.oBaseGrid.add(oSubGridKennzeichen,	2, 1, 	E2_INSETS.I_0_10_10_10, alHelp);
		
		this.tfKennzeichenLKW.set_StartValue(			this.recVPOS_TPA_FUHRE.get_TRANSPORTKENNZEICHEN_cUF_NN(""));
		this.tfKennzeichenANH.set_StartValue(			this.recVPOS_TPA_FUHRE.get_ANHAENGERKENNZEICHEN_cUF_NN(""));

		this.oBaseGrid.add(new E2_ComponentGroupHorizontal(0,new ownSaveButtonAndReload(), new ownSaveButton(),new ownCancelButton(), E2_INSETS.I_0_0_10_0), E2_INSETS.I_0_10_10_10);
		
	}
	
	
	private class ownAnzeigeGrid extends MyE2_Grid
	{
		public ownAnzeigeGrid(String kennzeichenTyp,Vector<IF_UB_Fields> vtf_fields) throws myException
		{
			super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			String cHelpAddon =  (kennzeichenTyp.equals("EK")?"Lief":"Abn");
			String cHelpAddon2 = (kennzeichenTyp.equals("EK")?"Ladeort/Kontr.":"Abladeort/Kontr.");
			String cHelpAddon3 = (kennzeichenTyp.equals("EK")?"Ladedatum":"Abladedatum");
			
			this.add(new MyE2_Label(new MyE2_String("PlanMg "+cHelpAddon),	MyE2_Label.STYLE_SMALL_ITALIC()), 	E2_INSETS.I_0_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("LadeMg "+cHelpAddon),	MyE2_Label.STYLE_SMALL_ITALIC()), 	E2_INSETS.I_0_2_10_2);
			this.add(new MyE2_Label(new MyE2_String("AbladeMg "+cHelpAddon),MyE2_Label.STYLE_SMALL_ITALIC()), 	E2_INSETS.I_0_2_10_2);
			this.add(new MyE2_Label(new MyE2_String(cHelpAddon3),MyE2_Label.STYLE_SMALL_ITALIC()), 	E2_INSETS.I_0_2_10_2);

			for (int i=0;i<vtf_fields.size();i+=4)
			{
				Object oTest= ((MyE2IF__Component)vtf_fields.get(i)).EXT().get_O_PLACE_FOR_EVERYTHING();
				if (oTest instanceof RECORD_VPOS_TPA_FUHRE_ORT)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = (RECORD_VPOS_TPA_FUHRE_ORT)oTest;
					this.add(new MyE2_Label(new MyE2_String(cHelpAddon2+"->  ",true,recOrt.get_NAME1_cF_NN(""),false),MyE2_Label.STYLE_SMALL_BOLD()),4, 	E2_INSETS.I_0_2_5_2);
				}
				this.add((Component)vtf_fields.get(i),E2_INSETS.I_0_2_10_2);
				this.add((Component)vtf_fields.get(i+1),E2_INSETS.I_0_2_10_2);
				this.add((Component)vtf_fields.get(i+2),E2_INSETS.I_0_2_10_2);
				this.add((Component)vtf_fields.get(i+3),E2_INSETS.I_0_2_10_2);
			}
		}
	}

	private class ownTF4Numbers extends UB_TextFieldForNumbers
	{
		public ownTF4Numbers(MyRECORD recordBase, String cStartWert, String fieldName, boolean bEnabled4Edit) throws myException
		{
			super(fieldName,3, true, cStartWert, 70, 10);
			this.EXT().set_O_PLACE_FOR_EVERYTHING(recordBase);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
		}
	}
	
	private class ownTF4Datum extends UB_TextField_With_DatePOPUP_OWN
	{
		public ownTF4Datum(MyRECORD recordBase, String cStartWert, String fieldName, boolean bEnabled4Edit, boolean bFillOthers) throws myException
		{
			super(fieldName, true, cStartWert, 80);
			this.EXT().set_O_PLACE_FOR_EVERYTHING(recordBase);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			
			if (bFillOthers)
			{
				//bei der eingabe des datums von, die anderen daten vorbesetzen
				this.get_vActionAgentsZusatz().add(
						new XX_ActionAgent()
						{

							@Override
							public void executeAgentCode(ExecINFO execInfo) throws myException
							{
								FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oThis = FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this;
								
								Vector<IF_UB_Fields> vAlle = new Vector<IF_UB_Fields>();
								vAlle.addAll(oThis.vTF_EK_Fields);
								vAlle.addAll(oThis.vTF_VK_Fields);
								
								for (int i=0;i<vAlle.size();i++)
								{
									if (vAlle.get(i) instanceof ownTF4Datum && vAlle.get(i)!=ownTF4Datum.this)
									{
										if (S.isEmpty(((ownTF4Datum)vAlle.get(i)).get_cText()))
										{
											((ownTF4Datum)vAlle.get(i)).get_oTextField().setText(ownTF4Datum.this.get_cText());
										}
									}
									
								}
							}
							
						});

			}
			
		}
		
		
	}

	

	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_TPA_FUHRE) 	throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VPOS_TPA_FUHRE  recTest = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			if (recTest.get_STATUS_BUCHUNG_cUF_NN("").equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT) || recTest.get_STATUS_BUCHUNG_cUF_NN("").equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre besitzt bereits Buchungspositionen !"));
			}
			if (recTest.is_FUHRE_KOMPLETT_NO())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde noch nicht komplett bearbeitet - Bitte gehen Sie über die Maske !"));
			}
			if (recTest.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde bereits gelöscht !"));
			}
			if (recTest.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Fuhre wurde bereits storniert !"));
			}
			return oMV;
		}
		
	}
	
	

	@Override
	public E2_BasicModuleContainer create_BasicContainer4Popup()  throws myException
	{
		return new ownBasicModuleContainer();
	}

	
	
	public FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN get_Copy(Object o) throws  myExceptionCopy
	{
		FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oRueck;
		try
		{
			oRueck = new FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN(this.get_oSQLField());
			return oRueck;
		} 
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy("Error Copy: "+e.getMessage());
		}
	}
	
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer() throws myException
		{
			super();
			FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oThis = FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this;
			oThis.prepareFields();
			this.add(oThis.oBaseGrid, E2_INSETS.I_10_10_10_10);
		}
	}

	
	
	private class ownSaveButton extends MyE2_Button
	{
		public ownSaveButton()
		{
			super(new MyE2_String("Speichern/Fenster schliessen"));
			this.add_oActionAgent(new saveAction());
			this.add_oActionAgent(new actionCloseWindow());
		}

	}


	private class ownSaveButtonAndReload extends MyE2_Button
	{
		public ownSaveButtonAndReload()
		{
			super(new MyE2_String("Speichern/neu laden"));
			this.add_oActionAgent(new saveAction());
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this.prepareFields();
				}
			});
		}
		

	}
	
	
	
	
	private class saveAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oThis = FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this;
			
			VECTOR_UB_FIELDS vCheck = new VECTOR_UB_FIELDS();
			vCheck.addAll(oThis.vTF_EK_Fields);
			vCheck.addAll(oThis.vTF_VK_Fields);
			vCheck.add(oThis.tfKennzeichenANH);
			vCheck.add(oThis.tfKennzeichenLKW);
			
			bibMSG.add_MESSAGE(vCheck.get_MV_AllFieldsAreOK_ShowErrorInput());
			
			if (bibMSG.get_bIsOK())
			{
				if (oThis.tfKennzeichenLKW.get_bFieldHasChanged())		{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_TRANSPORTKENNZEICHEN(oThis.tfKennzeichenLKW.get_cText()));}
				if (oThis.tfKennzeichenANH.get_bFieldHasChanged())		{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANHAENGERKENNZEICHEN(oThis.tfKennzeichenANH.get_cText()));}
				
				if (oThis.vTF_EK_Fields.get(0).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_PLANMENGE_LIEF(oThis.vTF_EK_Fields.get(0).get_cText()));}
				if (oThis.vTF_EK_Fields.get(1).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_LADEMENGE_LIEF(oThis.vTF_EK_Fields.get(1).get_cText()));}
				if (oThis.vTF_EK_Fields.get(2).get_bFieldHasChanged())	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_ABLADEMENGE_LIEF(oThis.vTF_EK_Fields.get(2).get_cText()));}
				if (oThis.vTF_EK_Fields.get(3).get_bFieldHasChanged())	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_DATUM_AUFLADEN(oThis.vTF_EK_Fields.get(3).get_cText()));}
				
				if (oThis.vTF_VK_Fields.get(0).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_PLANMENGE_ABN(oThis.vTF_VK_Fields.get(0).get_cText()));}
				if (oThis.vTF_VK_Fields.get(1).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_LADEMENGE_ABN(oThis.vTF_VK_Fields.get(1).get_cText()));}
				if (oThis.vTF_VK_Fields.get(2).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_ANTEIL_ABLADEMENGE_ABN(oThis.vTF_VK_Fields.get(2).get_cText()));}
				if (oThis.vTF_VK_Fields.get(3).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(oThis.recVPOS_TPA_FUHRE.set_NEW_VALUE_DATUM_ABLADEN(oThis.vTF_VK_Fields.get(3).get_cText()));}

				//abzuege
				String cLademengeLief = "0";
				String cAbladeMengeAbn = "0";
				if (S.isFull(oThis.vTF_EK_Fields.get(1).get_cText())) {cLademengeLief=	oThis.vTF_EK_Fields.get(1).get_cText();}
				if (S.isFull(oThis.vTF_VK_Fields.get(2).get_cText())) {cAbladeMengeAbn=	oThis.vTF_VK_Fields.get(2).get_cText();}
				new calcFuhrenAbzuege(oThis.recVPOS_TPA_FUHRE,cLademengeLief,cAbladeMengeAbn);

				
				//jetzt die fuhrenorte durchscannen
				for (int i=4; i<oThis.vTF_EK_Fields.size(); i+=4)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = (RECORD_VPOS_TPA_FUHRE_ORT)((MyE2IF__Component)oThis.vTF_EK_Fields.get(i)).EXT().get_O_PLACE_FOR_EVERYTHING();
					if (oThis.vTF_EK_Fields.get(i+0).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_EK_Fields.get(i+0).get_cDBFieldName(), oThis.vTF_EK_Fields.get(i+0).get_cText()));}
					if (oThis.vTF_EK_Fields.get(i+1).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_EK_Fields.get(i+1).get_cDBFieldName(), oThis.vTF_EK_Fields.get(i+1).get_cText()));}
					if (oThis.vTF_EK_Fields.get(i+2).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_EK_Fields.get(i+2).get_cDBFieldName(), oThis.vTF_EK_Fields.get(i+2).get_cText()));}
					if (oThis.vTF_EK_Fields.get(i+3).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_EK_Fields.get(i+3).get_cDBFieldName(), oThis.vTF_EK_Fields.get(i+3).get_cText()));}
					
					//abzuege
					String cLademenge = "0";
					if (S.isFull(oThis.vTF_EK_Fields.get(i+1).get_cText())) {cLademenge=	oThis.vTF_EK_Fields.get(i+1).get_cText();}
					new calcFuhrenAbzuege(recOrt,cLademenge);
				}
				for (int i=4; i<oThis.vTF_VK_Fields.size(); i+=4)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = (RECORD_VPOS_TPA_FUHRE_ORT)((MyE2IF__Component)oThis.vTF_VK_Fields.get(i)).EXT().get_O_PLACE_FOR_EVERYTHING();
					if (oThis.vTF_VK_Fields.get(i+0).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_VK_Fields.get(i+0).get_cDBFieldName(), oThis.vTF_VK_Fields.get(i+0).get_cText()));}
					if (oThis.vTF_VK_Fields.get(i+1).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_VK_Fields.get(i+1).get_cDBFieldName(), oThis.vTF_VK_Fields.get(i+1).get_cText()));}
					if (oThis.vTF_VK_Fields.get(i+2).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_VK_Fields.get(i+2).get_cDBFieldName(), oThis.vTF_VK_Fields.get(i+2).get_cText()));}
					if (oThis.vTF_VK_Fields.get(i+3).get_bFieldHasChanged()) 	{bibMSG.add_MESSAGE(recOrt.set_NewValueForDatabase(oThis.vTF_VK_Fields.get(i+3).get_cDBFieldName(), oThis.vTF_VK_Fields.get(i+3).get_cText()));}

					//abzuege
					String cAblademenge = "0";
					if (S.isFull(oThis.vTF_VK_Fields.get(i+2).get_cText())) {cAblademenge=	oThis.vTF_VK_Fields.get(i+2).get_cText();}
					new calcFuhrenAbzuege(recOrt,cAblademenge);
				}
				
				
				Vector<String> vSQL = new Vector<String>();
				// immer eine aenderung auf die fuhre schreiben, damit bei aenderungen nur im fuhrenort der trigger trotzdem anlaeuft
				vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET FUHRENGRUPPE=FUHRENGRUPPE WHERE ID_VPOS_TPA_FUHRE="+oThis.recVPOS_TPA_FUHRE.get_ID_VPOS_TPA_FUHRE_cUF());
				
				
				
				if (oThis.recVPOS_TPA_FUHRE.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
				{
					vSQL.add(oThis.recVPOS_TPA_FUHRE.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE", bibE2.cTO(), 
							"ID_VPOS_TPA_FUHRE="+oThis.recVPOS_TPA_FUHRE.get_ID_VPOS_TPA_FUHRE_cUF(), null));
				}
				//jetzt die fuhrenorte durchscannen
				for (int i=4; i<oThis.vTF_EK_Fields.size(); i+=4)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = (RECORD_VPOS_TPA_FUHRE_ORT)((MyE2IF__Component)oThis.vTF_EK_Fields.get(i)).EXT().get_O_PLACE_FOR_EVERYTHING();
					if (recOrt.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
					{
						vSQL.add(recOrt.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), 
								"ID_VPOS_TPA_FUHRE_ORT="+recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), null));
					}
				}
				for (int i=4; i<oThis.vTF_VK_Fields.size(); i+=4)
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = (RECORD_VPOS_TPA_FUHRE_ORT)((MyE2IF__Component)oThis.vTF_VK_Fields.get(i)).EXT().get_O_PLACE_FOR_EVERYTHING();
					if (recOrt.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
					{
						vSQL.add(recOrt.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().get_CompleteUPDATEString("JT_VPOS_TPA_FUHRE_ORT", bibE2.cTO(), 
								"ID_VPOS_TPA_FUHRE_ORT="+recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), null));
					}
				}
				
				
				if (vSQL.size()>0)
				{
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
					FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
					if (bibMSG.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Eingaben wurden erfolgreich gespeichert !"));
					}
				}
			}
		}
	}
		

	private class actionCloseWindow extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this.get_oModuleContainer4Popup().CLOSE_AND_DESTROY_POPUPWINDOW(true);

			FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oThis = FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this;
			oThis.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			
		}
	}
	
	
	private class ownCancelButton extends MyE2_Button
	{
		public ownCancelButton()
		{
			super(new MyE2_String("Abbruch"));
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this.get_oModuleContainer4Popup().CLOSE_AND_DESTROY_POPUPWINDOW(false);
					
					FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN oThis = FU_LIST_BT_ED_IN_LIST_MENGEN_LADE_ABLADEDATUM_KENNZEICHEN.this;
					oThis.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);

				}
			});
		}
	}
	
	
	
	
	private class calcFuhrenAbzuege
	{
		
		private Vector<String> 			vUpdate = new Vector<String>();
		
		public calcFuhrenAbzuege(RECORD_VPOS_TPA_FUHRE recFuhre, String cAnteilLadeMengeLiefFormated, String cAnteilAblademengeAbnFormated) throws myException
		{
			super();
			
			vAbzugsFeldListe  vFelderEK = new vAbzugsFeldListe("ID_VPOS_TPA_FUHRE_ABZUG_EK");
			vAbzugsFeldListe  vFelderVK = new vAbzugsFeldListe("ID_VPOS_TPA_FUHRE_ABZUG_VK");
			
			MyDataRecordHashList 	listAbzuegeEK = new MyDataRecordHashList(vFelderEK,	bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_EK",
									"ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),
									"ID_VPOS_TPA_FUHRE_ABZUG_EK");

			MyDataRecordHashList 	listAbzuegeVK = new MyDataRecordHashList(vFelderVK,	bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ABZUG_VK",
									"ID_VPOS_TPA_FUHRE="+recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),
									"ID_VPOS_TPA_FUHRE_ABZUG_VK");
			
			BigDecimal bdAbzugEK = this.makeCalculation(listAbzuegeEK, 
										"ID_VPOS_TPA_FUHRE_ABZUG_EK",
										cAnteilLadeMengeLiefFormated, 
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cF_NN("1"),
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cF_NN(""),
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cF_NN(""));

			BigDecimal bdAbzugVK = this.makeCalculation(listAbzuegeVK, 
										"ID_VPOS_TPA_FUHRE_ABZUG_VK",
										cAnteilAblademengeAbnFormated, 
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cF_NN("1"),
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cF_NN(""),
										recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cF_NN(""));

			
			recFuhre.set_NEW_VALUE_ABZUG_LADEMENGE_LIEF(MyNumberFormater.formatDezWithRound(bdAbzugEK,3));
			recFuhre.set_NEW_VALUE_ABZUG_ABLADEMENGE_ABN(MyNumberFormater.formatDezWithRound(bdAbzugVK,3));
			
			this.vUpdate.add(recFuhre.get_SQL_UPDATE_STATEMENT(null, true));
		}

		

		public Vector<String> get_vUpdate()
		{
			return vUpdate;
		}



		public calcFuhrenAbzuege(RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt, String cNewMengeLadeOderAblade) throws myException
		{
			super();

			vAbzugsFeldListe  vFelder = new vAbzugsFeldListe("ID_VPOS_TPA_FUHRE_ORT_ABZUG");
			
			MyDataRecordHashList listAbzuege = new MyDataRecordHashList(vFelder,	bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT_ABZUG",
													"ID_VPOS_TPA_FUHRE_ORT="+recFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(),
													"ID_VPOS_TPA_FUHRE_ORT_ABZUG");
			
			BigDecimal bdAbzug = this.makeCalculation(	listAbzuege, 
														"ID_VPOS_TPA_FUHRE_ORT_ABZUG",
														cNewMengeLadeOderAblade, 
													    recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cF_NN("1"),
													    recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cF_NN(""),
													    recFuhreOrt.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cF_NN(""));

			recFuhreOrt.set_NEW_VALUE_ABZUG_MENGE(MyNumberFormater.formatDezWithRound(bdAbzug,3));
			
			this.vUpdate.add(recFuhreOrt.get_SQL_UPDATE_STATEMENT(null, true));
		}

		
		private BigDecimal makeCalculation(MyDataRecordHashList listAbzuege, String cID_ABZUG_TABELLE, String cMengeFormated, String cMengenDivisor, String cEinheit, String cEinheitPreis) throws myException
		{
			BL_AbzugsKalkulator oBLA = new BL_AbzugsKalkulator(cMengeFormated,
																"0",
																"0",
																cMengenDivisor,
																"1",
																bibE2.get_cBASISWAEHRUNG_SYMBOL(),
																bibE2.get_cBASISWAEHRUNG_SYMBOL(),
																cEinheit,
																cEinheitPreis);
				
			for (int i=0;i<listAbzuege.get_size();i++)
			{
				if (i==0)
				{
					oBLA.add_AbzugsKalkulationsZeile(listAbzuege.get_FormatedValue("ABZUGTYP", i),
													cMengeFormated,
													"0",
													"0",
													listAbzuege.get_FormatedValue("ABZUG", i),
													listAbzuege.get_FormatedValue("ABZUG2", i),
													listAbzuege.get_FormatedValue(cID_ABZUG_TABELLE, i),
													listAbzuege.get_FormatedValue("ABZUG_BELEGTEXT_SCHABLONE", i));
				}
				else
				{
					oBLA.add_AbzugsKalkulationsZeile(listAbzuege.get_FormatedValue("ABZUGTYP", i),
													MyNumberFormater.formatDez(oBLA.get_dMengeNachAbzugLetzteAbzugsZeile(),3,false), 
													MyNumberFormater.formatDez(oBLA.get_dEPreisNachAbzugLetzteAbzugsZeile(),2,false), 
													MyNumberFormater.formatDez(oBLA.get_dEPreisNachAbzugLetzteAbzugsZeile_FW(),2,false),
													listAbzuege.get_FormatedValue("ABZUG", i),
													listAbzuege.get_FormatedValue("ABZUG2", i),
													listAbzuege.get_FormatedValue(cID_ABZUG_TABELLE, i),
													listAbzuege.get_FormatedValue("ABZUG_BELEGTEXT_SCHABLONE", i));
				}
			}
			
			
			return BigDecimalFactory.BigDecimal3Round(oBLA.get_GESAMTER_MENGENABZUG());
		}
		
	
		private class vAbzugsFeldListe extends Vector<String>
		{
			public vAbzugsFeldListe(String cINDEXFELD)
			{
				super();
				this.add(cINDEXFELD);
				this.add("ABZUGTYP");
				this.add("MENGE_VOR_ABZUG");
				this.add("EPREIS_VOR_ABZUG");
				this.add("EPREIS_VOR_ABZUG_FW");
				this.add("ABZUG");
				this.add("ABZUG2");	
				this.add("ABZUG_BELEGTEXT_SCHABLONE");
			}

		}
		
	}
	
	
	
}
