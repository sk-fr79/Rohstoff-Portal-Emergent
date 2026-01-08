package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

//2012-02-28: neuer selektor, der alle adressen sucht, die einen JT_ADRESS_INFO-eintrag innerhalb eines zeitraums haben
public class FS_Selektor_letzte_info_im_zeitraum extends E2_SelektorDateFromTo_NG
{

	public FS_Selektor_letzte_info_im_zeitraum() throws myException
	{
		super();
		this.get_oTFDatumVon().get_oTextField().set_iWidthPixel(70);
		this.get_oTFDatumBis().get_oTextField().set_iWidthPixel(70);
		
		String cToolTip = new MyE2_String("Selektiert Adresse aus, die im angegebenen Zeitraum mindestens einen Eintrag in den <Kunden-Zusatzinfos> besitzen").CTrans();
		
		String cToolTipDel1 = new MyE2_String("Löschen des Startdatums (Selektor: Findet Adressen, die im angegebenen Zeitraum mindestens einen Eintrag in den <Kunden-Zusatzinfos> besitzen)").CTrans();
		String cToolTipDel2 = new MyE2_String("Löschen des Enddatums (Selektor: Findet Adressen, die im angegebenen Zeitraum mindestens einen Eintrag in den <Kunden-Zusatzinfos> besitzen)").CTrans();

		this.get_oTFDatumVon().get_oTextField().setToolTipText(cToolTip);
		this.get_oTFDatumVon().get_oButtonCalendar().setToolTipText(cToolTip);
		this.get_oTFDatumVon().get_oButtonEraser().setToolTipText(cToolTipDel1);
		this.get_oTFDatumBis().get_oTextField().setToolTipText(cToolTip);
		this.get_oTFDatumBis().get_oButtonCalendar().setToolTipText(cToolTip);
		this.get_oTFDatumBis().get_oButtonEraser().setToolTipText(cToolTipDel2);
		
		this.get_oTFDatumVon().get_oTextField().set_bEnabled_For_Edit(false);
		this.get_oTFDatumBis().get_oTextField().set_bEnabled_For_Edit(false);
	}

	
	/*
	 * die (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG#get_WhereBlock()
	 * die generierung des where-blocks muss ueberschrieben werden
	 */
	public String get_WhereBlock() throws myException
	{
		String cDBTextVon = "";
		String cDBTextBis = "";
		
		TestingDate oDate = new TestingDate(this.get_oTFDatumVon().get_oTextField().getText());
		if (!bibALL.isEmpty(this.get_oTFDatumVon().get_oTextField().getText()))
		{
			if (oDate.testing())
			{
				cDBTextVon=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextVon = "'"+cDBTextVon.replace('.','-')+"'";
			}
		}
		
		oDate = new TestingDate(this.get_oTFDatumBis().get_oTextField().getText());
		if (!bibALL.isEmpty(this.get_oTFDatumBis().get_oTextField().getText()))
		{
			if (oDate.testing())
			{
				cDBTextBis=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextBis = "'"+cDBTextBis.replace('.','-')+"'";
			}
		}
		
		String cRueck="JT_ADRESSE.ID_ADRESSE IN (SELECT IF.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE_INFO IF WHERE NVL(IF.IST_MESSAGE,'N')='N' AND ";
		
		
		String cWhereBlock = "";
		if 		(!cDBTextVon.equals("")  && !cDBTextBis.equals(""))
		{
			cWhereBlock = "(("+cDBTextVon+"<=TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') AND "+cDBTextBis+" >= TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD')"+")";
			cWhereBlock += "OR ("+cDBTextVon+"<=TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') AND "+cDBTextBis+" >= TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD'))";
			cWhereBlock += "OR (TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') <= "+cDBTextVon+" AND TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') >= "+cDBTextBis+"))";
		}
		else if (cDBTextVon.equals("")  && !cDBTextBis.equals(""))
		{
			cWhereBlock = "TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') <= "+cDBTextBis;
		}
		else if (! cDBTextVon.equals("")  && cDBTextBis.equals(""))
		{
			cWhereBlock = "TO_CHAR(IF.DATUMEINTRAG,'YYYY-MM-DD') >= "+cDBTextVon;
		}
		
		if (S.isEmpty(cWhereBlock))
		{
			cRueck ="";
		}
		else
		{
			cRueck = cRueck + cWhereBlock +")";
		}
		
		return cRueck;
	}

	
}