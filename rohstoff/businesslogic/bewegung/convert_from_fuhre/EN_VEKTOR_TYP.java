package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


/**
 * Typen, die innerhalb der datenbank unterschieden werden
 */
public enum EN_VEKTOR_TYP implements IF_enumForDb<EN_VEKTOR_TYP> {

	 WE("WE","Wareneingang", 			true, 	E2_ResourceIcon.get_RI("vt_we.png"), 	"Wareneingang auf eigenes Lager")
	,WA("WA","Warenausgang", 			true, 	E2_ResourceIcon.get_RI("vt_wa.png"), 	"Warenausgang von eigenem Lager")
	,UMB("Hand-UB","Hand-Umbuchung", 	true, 	E2_ResourceIcon.get_RI("leer.png"),		"")
	,ST("Strecke","Strecke", 			true, 	E2_ResourceIcon.get_RI("vt_st.png"), 	"")
	,MI("Mixed","Mixed",				false, 	E2_ResourceIcon.get_RI("leer.png"), 	"")
	,LL("L->L","Lager-Lager", 			true, 	E2_ResourceIcon.get_RI("vt_ll.png"), 	"Lager-Lager-Fuhre")
	,KORR("Hand-Korr","Hand-Korrektur", true, 	E2_ResourceIcon.get_RI("leer.png"), 	"")
	,MK("Mge-Korr","Mengenkorrektur",	true,	E2_ResourceIcon.get_RI("leer.png"),		"")
	,SW("Sort.Wech.","Sortenwechsel",	true, 	E2_ResourceIcon.get_RI("leer.png"),		"")
	,LG("Leergut","Leergutfuhre",		true, 	E2_ResourceIcon.get_RI("vt_lg.png"), 	"Leergut-Fuhre")
	,TS("Probe", "Probelieferung", 		true, 	E2_ResourceIcon.get_RI("vt_p.png"), 	"Probelieferung")
	;
	
	private String		  		userText 		= null; 
	private String   			userTextLang 	= null;
	private boolean   			editable 		= false;  
	private E2_ResourceIcon  	icon 			= null;
	private String 				beschreibung	= null;

	private EN_VEKTOR_TYP(String p_user_text, String p_user_text_lang, boolean p_editable, E2_ResourceIcon p_icon, String p_beschreibung) {
		this.userText		=	p_user_text;
		this.userTextLang = 	p_user_text_lang;
		this.editable 		=   p_editable;
		this.icon 			= 	p_icon;
		this.beschreibung 	= 	p_beschreibung;
	}

	

	public boolean isEditable()	{
		return this.editable;
	}

	public String get_beschreibung() {
		return beschreibung;
	}

	public RB_lab get_vektor_typ_label() throws myException {
		return new RB_lab(this.icon)._ttt(this.get_beschreibung());
	}



	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}

	@Override
	public String userTextLang() {
		return S.isFull(this.userTextLang)?this.userTextLang:IF_enumForDb.super.userTextLang();
	}


	
	public static VEK<EN_VEKTOR_TYP> getVektEditableTypes() {
		VEK<EN_VEKTOR_TYP>  ret = new VEK<EN_VEKTOR_TYP>();
		
		for (EN_VEKTOR_TYP t: EN_VEKTOR_TYP.values()) {
			if (t.isEditable()) {
				ret._a(t);
			}
		}
		return ret;
	}

	
	public static String[][] ddArrayEditableTypes(boolean bEmtpyInFront) throws myException {
		
		VEK<EN_VEKTOR_TYP>  vals =  EN_VEKTOR_TYP.getVektEditableTypes();
		
		String[][] ddarray = new String[vals.size()][2];
		int i=0;
		for (IF_enumForDb<EN_VEKTOR_TYP> stat: vals) {
			ddarray[i][0]=new MyE2_String(stat.userTextLang()).CTrans();
			ddarray[i][1]=stat.dbVal();
			i++;
		}
		if (bEmtpyInFront) {
			ddarray= bibARR.add_emtpy_db_value_inFront(ddarray);
		} 
		
		return ddarray;
	}

	
	
	public static EN_VEKTOR_TYP find_typ(String dbVal) {
		for (EN_VEKTOR_TYP typ: EN_VEKTOR_TYP.values()) {
			if (typ.dbVal().equals(dbVal)) {
				return typ;
			}
		}
		return null;
	}

	
	
	public static XX_ActionAgent generateNewAgent(EN_VEKTOR_TYP typ, E2_NavigationList naviList) {
		XX_ActionAgent  agent = null;
		
//		switch (typ) {
//		case WE:
//			agent = new BG_AA_NewWE(naviList);
//			break;
//	
//		case WA:
//			agent = new BG_AA_NewWA(naviList);
//			break;
//			
//		case ST:
//			agent = new BG_AA_NewST(naviList);
//			break;
//			
//		case LL:
//			agent = new BG_AA_NewLL(naviList);
//			break;
//		
//		case LG:
//			agent = new BG_AA_NewLG(naviList);
//			break;
//
//		case TS:
//			agent = new BG_AA_newTS(naviList);
//			break;
//			
//		default:
//			break;
//		}

		return agent;
	}

	@Override
	public EN_VEKTOR_TYP[] getValues() {
		return EN_VEKTOR_TYP.values();
	}


	
	
	/**
	 * 
	 * @return component for info-block left on mask
	 * @throws myException 
	 */
	public Component getComp4Infoblock() throws myException {
		E2_Grid g = new E2_Grid();
		g	._setSize(22)
			._a(new RB_lab()._t(this.name())._fo_bold()._ttt(this.userTextLang), new RB_gld()._center_mid()._ins(0))
			._bo_b();
		return g;
	}
	
	
}