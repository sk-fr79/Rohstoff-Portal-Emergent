package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class BSRG__POPUP_Select_EINZELPREIS extends E2_BasicModuleContainer
{

	private String  cID_ARTIKEL = null;
	private String  cLEISTUNGS_DATUM_Formated = null;         //z.B. 30.10.2009
	private String  cID_ADRESSE = null;
	private String  cEK_VK = null;
	
	//optional (wenn vorhanden):
	private String  cID_VPOS_KON = null;
	
	//das textfeld fuer die rueckgabe
	private MyE2_TextField   oTextFuerRueckgabe = null;

	public BSRG__POPUP_Select_EINZELPREIS(		String 			cid_artikel, 
												String 			cleistungs_datum_formated, 
												String 			cid_adresse, 
												String 			c_ek_vk,
												String 			cid_vpos_kon, 
												MyE2_TextField 	TextFuerRueckgabe) throws myException
	{
		super();
		
		
		this.cID_ARTIKEL = cid_artikel;
		this.cLEISTUNGS_DATUM_Formated = cleistungs_datum_formated;
		this.cID_ADRESSE = cid_adresse;
		this.cEK_VK = c_ek_vk;
		this.cID_VPOS_KON = cid_vpos_kon;
		this.oTextFuerRueckgabe = TextFuerRueckgabe;
		

		if (S.isEmpty(this.cID_ARTIKEL) || S.isEmpty(this.cLEISTUNGS_DATUM_Formated) || S.isEmpty(this.cID_ADRESSE))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Preisfindung ist erst möglich, wenn der Kunde/Lieferant, die Sorte und das Leistungsdatum feststehen !"));
			return;
		}

		if (S.isEmpty(this.cEK_VK) || !(this.cEK_VK.equals("EK") || this.cEK_VK.equals("VK")))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler: Der Belegtyp (EK oder VK) steht nicht fest !"));
			return;
		}
				
		Vector<MyE2_Button>  vAuswahlButtonsFuerPreise = new Vector<MyE2_Button>();
		
		//zuerst den kontrakt
		if (!S.isEmpty(this.cID_VPOS_KON))
		{
			RECORD_VPOS_KON recVPOS_KON = new RECORD_VPOS_KON(this.cID_VPOS_KON);
			MyE2_String cText = new MyE2_String("Kontrakt:  ",true,
											recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get___KETTE(bibALL.get_Vector("NAME1", "ORT"), "", "  <", ">  ", " - "),false,
											recVPOS_KON.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"), "", "  ", "  ", " - "),false,
											recVPOS_KON.get_ARTBEZ1_cF_NN(""),false,
											" --> ",false,
											recVPOS_KON.get_EINZELPREIS_cF_NN("<kein Preis>"),false);
				
			vAuswahlButtonsFuerPreise.add(new btUebernahme(cText,recVPOS_KON.get_EINZELPREIS_cF_NN(""),true));
		}
		
		//jetzt die angebote suchen ...
		//String cLeistungsdatumFormated = oThis.get_cLeistungsDatumFormated();
		if (bibMSG.get_bIsOK())
		{
			TestingDate oTestDate = new TestingDate(this.cLEISTUNGS_DATUM_Formated);
			if (!oTestDate.testing())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Leistungsdatum ist nicht korrekt !"));
			}
			else
			{
				//EK oder VK
				String cBelegType = 	 	myCONST.VORGANGSART_ANGEBOT;
				String cBelegTyp4User = 	myCONST.VORGANGSART_ANGEBOT_FOR_USER;
				if 		(this.cEK_VK.equals("EK"))
				{
					cBelegType = 		myCONST.VORGANGSART_ABNAHMEANGEBOT;
					cBelegTyp4User =	myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER;
				}
				
				String cLeistungsdatumSQL = oTestDate.get_ISO_DateFormat(true);
				
				RECLIST_VPOS_STD recListAngebote = new RECLIST_VPOS_STD("SELECT JT_VPOS_STD.* FROM "+bibE2.cTO()+".JT_VPOS_STD   " +
								" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD_ANGEBOT.ID_VPOS_STD=JT_VPOS_STD.ID_VPOS_STD) " +
								" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_STD ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) " +
										" WHERE " +
								" NVL(JT_VPOS_STD.DELETED,'N')='N' AND "+
								" NVL(JT_VKOPF_STD.DELETED,'N')='N' AND "+
								" JT_VPOS_STD_ANGEBOT.GUELTIG_VON<="+cLeistungsdatumSQL +" AND "+" JT_VPOS_STD_ANGEBOT.GUELTIG_BIS>="+cLeistungsdatumSQL+
								" AND JT_VKOPF_STD.ID_ADRESSE="+this.cID_ADRESSE+
								" AND JT_VPOS_STD.ID_ARTIKEL="+this.cID_ARTIKEL);
				
				for (int i=0;i<recListAngebote.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_STD  recVPOS_STD = recListAngebote.get(i);
					
					if (	recVPOS_STD.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL) && 
							recVPOS_STD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_VORGANG_TYP_cUF().equals(cBelegType) &&
							recVPOS_STD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().is_ABGESCHLOSSEN_YES())
					{
						MyE2_String cText = new MyE2_String(cBelegTyp4User+":",true,
								recVPOS_STD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get___KETTE(bibALL.get_Vector("NAME1", "ORT"), "", "  <", ">  ", " - "),false,
								recVPOS_STD.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"), "", "  ", "  ", " - "),false,
								recVPOS_STD.get_ARTBEZ1_cF_NN(""),false,
								" --> ",false,
								recVPOS_STD.get_EINZELPREIS_cF_NN(""),false,
								"    (Gültig:",true,
								recVPOS_STD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF_NN("       ").substring(0,6).trim(),false,
								"bis:",true,
								recVPOS_STD.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF_NN("        ").substring(0,6).trim()+")",false
								);
						
						vAuswahlButtonsFuerPreise.add(new btUebernahme(cText,recVPOS_STD.get_EINZELPREIS_cF_NN(""),false));
					}
				}
				
				if (vAuswahlButtonsFuerPreise.size()>0)
				{
				
					MyE2_Column  oColPreise = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
					for (int i=0;i<vAuswahlButtonsFuerPreise.size();i++)
					{
						oColPreise.add(vAuswahlButtonsFuerPreise.get(i), E2_INSETS.I_10_2_2_2);
					}
					
					this.add(oColPreise, E2_INSETS.I_10_10_10_10);
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), new MyE2_String("Preisauswahl"));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurde kein Preis gefunden !"));
				}
			}
		}
		
	    
	}


	private class btUebernahme extends MyE2_Button
	{
		private String cPreisFormated = null;

		public btUebernahme(MyE2_String ButtonText, String PreisFormated, boolean bFett)
		{
			super(ButtonText);
			
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL(bFett?new E2_FontBold(-2):new E2_FontItalic(-2)));
			this.cPreisFormated = PreisFormated;

			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					BSRG__POPUP_Select_EINZELPREIS.this.oTextFuerRueckgabe.setText(btUebernahme.this.cPreisFormated);
					BSRG__POPUP_Select_EINZELPREIS.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
		
	}
	
}
