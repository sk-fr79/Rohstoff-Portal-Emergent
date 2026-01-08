/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 30.07.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Date;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

/**
 * @author martin
 * @date 30.07.2020
 * ermittelt jeweils passende Reclist21 vom typ _TAB.vpos_std oder _TAB.vpos_kon
 */
public class PdServiceErmittleRecListAngebotKontrakt {
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 31.07.2020
	 *
	 * @param idAdresseLager
	 * @param idArtikelBez
	 * @param leistungsDatum
	 * @param ladeSeite
	 * @return Reclist mit Rec21_VPosStd-objekten
	 * @throws Exception
	 * @throws myExceptionForUser
	 */
	public RecList21  getReclistAngebote(Long idAdresseLager, Long idArtikelBez,  Date leistungsDatum, boolean ladeSeite) throws Exception,myExceptionForUser {
		
		if (O.isOneNull(idAdresseLager,idArtikelBez,leistungsDatum)) {
			throw new myExceptionForUser(S.ms("Bitte Lieferadresse, Sorte und Leistungsdatum auf der "+(ladeSeite?"Ladeseite":"Abladeseite")+" ausfüllen !"));
		}
		
		// ResultValue<VEK<Rec21>> retVal = new ResultValue<VEK<Rec21>>();
		RecList21 ret = new RecList21(_TAB.vpos_std);
		
		Rec21_adresse 		adresse = new Rec21_adresse()._fill_id(idAdresseLager); 
		Rec21_artikel_bez  	artBez  = new Rec21_artikel_bez()._fill_id(idArtikelBez);
		
			
		SEL sel = new SEL(_TAB.vpos_std).FROM(_TAB.vpos_std)
						.INNERJOIN(_TAB.vpos_std_angebot, VPOS_STD.id_vpos_std,VPOS_STD_ANGEBOT.id_vpos_std)
						.INNERJOIN(_TAB.vkopf_std, 		  VPOS_STD.id_vkopf_std,VKOPF_STD.id_vkopf_std)
						.WHERE(new vglParam(VPOS_STD_ANGEBOT.gueltig_von,COMP.LE))
						.AND(  new vglParam(VPOS_STD_ANGEBOT.gueltig_bis,COMP.GE))
						.AND(  new vglParam(VPOS_STD.id_artikel))
						.AND( new vglParam(VKOPF_STD.id_adresse))
						.AND( new vglParam(VKOPF_STD.vorgang_typ))
						.AND(  new vgl_YN(VPOS_STD.deleted,false))
						;
			
		RecList21 rl = new RecList21(_TAB.vpos_std)._fill(new SqlStringExtended(sel.s())._addParameters(
					new VEK<ParamDataObject>()	._a(new Param_Date(leistungsDatum))
												._a(new Param_Date(leistungsDatum))
												._a(new Param_Long(artBez._getRec21Artikel().getIdLong()))
												._a(new Param_Long(adresse._getMainAdresse().getIdLong()))
												._a(new Param_String("",ladeSeite?myCONST.VORGANGSART_ABNAHMEANGEBOT:myCONST.VORGANGSART_ANGEBOT))
												));
			

		for (Rec21 r: rl) {
			Rec21_VPosStd recVpos = new Rec21_VPosStd(r);
			ret._add(recVpos);
		}
		
		return ret;
		
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 31.07.2020
	 *
	 * @param idAdresseLager
	 * @param idArtikelBez
	 * @param leistungsDatum
	 * @param ladeSeite
	 * @return Reclist mit Rec21_VPosKon-objekten
	 * @throws Exception
	 * @throws myExceptionForUser
	 */
	public RecList21  getReclistKontrakte(Long idAdresseLager, Long idArtikelBez,  Date leistungsDatum, boolean ladeSeite) throws Exception,myExceptionForUser {
		
		if (O.isOneNull(idAdresseLager,idArtikelBez,leistungsDatum)) {
			throw new myExceptionForUser(S.ms("Bitte Lieferadresse, Sorte und Leistungsdatum auf der "+(ladeSeite?"Ladeseite":"Abladeseite")+" ausfüllen !"));
		}
		
		// ResultValue<VEK<Rec21>> retVal = new ResultValue<VEK<Rec21>>();
		RecList21 ret = new RecList21(_TAB.vpos_kon);
		
		Rec21_adresse 		adresse = new Rec21_adresse()._fill_id(idAdresseLager); 
		Rec21_artikel_bez  	artBez  = new Rec21_artikel_bez()._fill_id(idArtikelBez);
		
			
		SEL sel = new SEL(_TAB.vpos_kon).FROM(_TAB.vpos_kon)
						.INNERJOIN(_TAB.vpos_kon_trakt, VPOS_KON.id_vpos_kon,VPOS_KON_TRAKT.id_vpos_kon)
						.INNERJOIN(_TAB.vkopf_kon, 		VPOS_KON.id_vkopf_kon,VKOPF_KON.id_vkopf_kon)
						.WHERE(	new vglParam(VPOS_KON_TRAKT.gueltig_von,COMP.LE))
						.AND(  	new vglParam(VPOS_KON_TRAKT.gueltig_bis,COMP.GE))
						.AND(  	new vglParam(VPOS_KON.id_artikel))
						.AND( 	new vglParam(VKOPF_KON.id_adresse))
						.AND( 	new vglParam(VKOPF_KON.vorgang_typ))
						.AND(  new vgl_YN(VPOS_KON.deleted,false))
						.AND(  new vgl_YN(VPOS_KON_TRAKT.abgeschlossen,false))
						;
			
		RecList21 rl = new RecList21(_TAB.vpos_kon)._fill(new SqlStringExtended(sel.s())._addParameters(
					new VEK<ParamDataObject>()	._a(new Param_Date(leistungsDatum))
												._a(new Param_Date(leistungsDatum))
												._a(new Param_Long(artBez._getRec21Artikel().getIdLong()))
												._a(new Param_Long(adresse._getMainAdresse().getIdLong()))
												._a(new Param_String("",ladeSeite?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT))
												));
			

		for (Rec21 r: rl) {
			Rec21_VPosKon recVpos = new Rec21_VPosKon(r);
			ret._add(recVpos);
		}
		
		return ret;
		
	}

	
}
 