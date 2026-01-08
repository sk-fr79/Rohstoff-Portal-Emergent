package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_KON_EXT;

public class FS___RECORD_VPOS_KON_EXT_NG extends __RECORD_VPOS_KON_EXT
{	


	public FS___RECORD_VPOS_KON_EXT_NG(RECORD_VPOS_KON recordOrig)
	{
		super(recordOrig);
	}

	
	/*
 				String cBuchungsNummer = recVKON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+recVKON.get_ID_VKOPF_KON_cF()+">")+"-"+recVKON.get_POSITIONSNUMMER_cF_NN("??");
 				this.add(new ownLabelLeft(cBuchungsNummer,recVKON));

				this.add(new ownLabelLeft(recVKON.get_BESTELLNUMMER_cUF_NN("<Best-Nr.>"),recVKON));
				
				String cGueltig = recVKON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(0,6);
				cGueltig += (" - "+recVKON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(0,6));
				this.add(new ownLabelLeft(cGueltig,recVKON));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( recVKON.get_ANZAHL_bdValue(BD0),0,true),recVKON));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[0],0,true),recVKON));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdMengePlanEcht[1],0,true),recVKON));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( bdBerechnet,0,true),recVKON));
				this.add(new ownLabelLeft(recVKON.get_ANR1_cUF_NN("")+"-"+recVKON.get_ANR2_cUF_NN(""),recVKON));
				this.add(new ownLabelLeft(recVKON.get_ARTBEZ1_cF_NN("<Artbez1>"),recVKON));
				this.add(new ownLabelLeft(recVKON.get_ARTBEZ2_cF_NN("<Artbez2>"),recVKON));
				this.add(new ownLabelRight(MyNumberFormater.formatDez( recVKON.get_EINZELPREIS_FW_bdValue(BD0),2,true),recVKON));
	 
	 */
	

	public FSI_SortObject_NG get_View_and_Sort_Pair(String cKenner) throws myException
	{
		
		BigDecimal[] bdMengePlanEcht = 			this.get_MengeGeliefertPlanEcht();
		BigDecimal   bdBerechnet     = 			this.get_MengeBerechnet();
		BigDecimal   bdPlan_oder_Geladen   = 	bdMengePlanEcht[0].subtract(bdMengePlanEcht[2]);
		BigDecimal   bdGeladen   =	 			bdMengePlanEcht[1].subtract(bdMengePlanEcht[2]);
		BigDecimal   bdRestMenge     = 			this.get_ANZAHL_bdValue(BigDecimal.ZERO).subtract((bdMengePlanEcht[1].subtract(bdMengePlanEcht[2])));

		
		if (cKenner.equals(FSI_CONST_NG.KON_BUCHNUMMER))
		{
			return new  FSI_SortObject_NG(this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cUF_NN("<ID:"+this.get_ID_VKOPF_KON_cF()+">")+"-"+this.get_POSITIONSNUMMER_cF_NN("??"));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_FR_BEST_NUMMER))
		{
			return new  FSI_SortObject_NG(this.get_BESTELLNUMMER_cUF_NN("<Best-Nr.>"));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_DRUCKDATUM))
		{
			return  new FSI_SortObject_NG(this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_DRUCKDATUM_cF_NN("??.??.????"),
						this.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_DRUCKDATUM_VALUE_FOR_SQLSTATEMENT());
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_GUELTIG))
		{
//2012-11-30: aenderung: Gültigkeitsbereich der vertraege wird im format dd.mm.yyyy angezeigt (nicht mehr verkuerzt)			
//			String cGueltig = this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF_NN("??.??.????").substring(0,6);
//			cGueltig += (" - "+this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF_NN("??.??.????").substring(0,6));
			String cGueltig = this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF_NN("??.??.????");
			cGueltig += (" - "+this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF_NN("??.??.????"));
			return  new FSI_SortObject_NG(	cGueltig,
										this.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_VALUE_FOR_SQLSTATEMENT());
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_MENGE))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( this.get_ANZAHL_bdValue(BigDecimal.ZERO),0,true),
										this.get_ANZAHL_bdValue(BigDecimal.ZERO));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_FU_PLAN_MENGE))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( bdPlan_oder_Geladen,0,true),
										bdPlan_oder_Geladen);
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_FU_LADE_MENGE))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( bdGeladen,0,true),
										bdGeladen);
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_REST_MENGE))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( bdRestMenge,0,true),
										bdRestMenge);
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_RECH_GUT))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( bdBerechnet,0,true),
										bdBerechnet);
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_ANR1_2))
		{
			return new FSI_SortObject_NG(this.get_ANR1_cUF_NN("")+"-"+this.get_ANR2_cUF_NN(""));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_ARTBEZ1))
		{
			return new FSI_SortObject_NG(this.get_ARTBEZ1_cF_NN("<Artbez1>"));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_ARTBEZ2))
		{
			return new FSI_SortObject_NG(this.get_ARTBEZ2_cF_NN("<Artbez2>"));
		}
		else if (cKenner.equals(FSI_CONST_NG.KON_PREIS))
		{
			return new FSI_SortObject_NG(	MyNumberFormater.formatDez( this.get_EINZELPREIS_FW_bdValue(BigDecimal.ZERO),2,true),
										this.get_EINZELPREIS_FW_bdValue(BigDecimal.ZERO));
		}
		else
		{
			throw new myException(this,"type is not known: "+cKenner);
		}

		
	}

	
}
