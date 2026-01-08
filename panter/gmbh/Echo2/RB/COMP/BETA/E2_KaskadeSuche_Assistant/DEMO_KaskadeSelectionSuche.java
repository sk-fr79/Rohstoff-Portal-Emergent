/**
 * panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant
 * @author sebastien
 * @date 17.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.BETA.E2_KaskadeSuche_Assistant;

import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


public class DEMO_KaskadeSelectionSuche extends E2_Grid {

	private RB_TextField 					oTextField_Land_input 	= new RB_TextField(100);
	private ownLandKaskadeSelektionSuche 	oLandSelektionSuche 	= new ownLandKaskadeSelektionSuche();

	private RB_TextField 					oTextField_idAdresse_input 	= new RB_TextField(100);
	private ownAdresseKaskadeSelektionSuche oAdresseSelektionSuche 		= new ownAdresseKaskadeSelektionSuche();
	
	private RB_TextField 					oTextField_idArtikel_input 	= new RB_TextField(100);
	private ownArtikelKaskadeSelektionSuche oArtikelSelektionSuche 		= new ownArtikelKaskadeSelektionSuche();
	
	private RB_TextField oTextField_lager_input					= new RB_TextField(100);
	private ownLagerSucheAssistant oLagerSucheAssitant 			= new ownLagerSucheAssistant();
	
	public DEMO_KaskadeSelectionSuche() throws myException{
		super();
		this._s(3)._bo_no();



		this
		._a("Land", 				new RB_gld()._ins(2,2,2,2)._left_mid())		
		._a(oTextField_Land_input, 	new RB_gld()._ins(2,2,2,2)._left_mid())
		._a(oLandSelektionSuche, 	new RB_gld()._ins(2,2,2,2)._left_mid())
		;

		this
		._a("ID Adresse", 				new RB_gld()._ins(2,2,2,2)._left_mid())		
		._a(oTextField_idAdresse_input, new RB_gld()._ins(2,2,2,2)._left_mid())
		._a(oAdresseSelektionSuche,		new RB_gld()._ins(2,2,2,2)._left_mid())
		;
		
		this
		._a("ID Artikel", 				new RB_gld()._ins(2,2,2,2)._left_mid())		
		._a(oTextField_idArtikel_input, new RB_gld()._ins(2,2,2,2)._left_mid())
		._a(oArtikelSelektionSuche,		new RB_gld()._ins(2,2,2,2)._left_mid())
		;
		
		
		this
		._a("Lager", 				new RB_gld()._ins(2,2,2,2)._left_mid())		
		._a(oTextField_lager_input, 	new RB_gld()._ins(2,2,2,2)._left_mid())
		._a(oLagerSucheAssitant,		new RB_gld()._ins(2,2,2,2)._left_mid())
		;
	}

	private class ownAdresseKaskadeSelektionSuche extends E2_KaskadeSelectionSucheButton{


		public ownAdresseKaskadeSelektionSuche() throws myException {
			super();

			this
			._addCriteria("SELECT ID_ADRESSKLASSE_DEF,KURZBEZEICHNUNG,BEZEICHNUNG FROM JT_ADRESSKLASSE_DEF ORDER BY KURZBEZEICHNUNG" , 
					"Adressklasse")

			._addCriteria("SELECT ID_LAND,LAENDERNAME FROM JD_LAND WHERE ID_LAND IN (SELECT ID_LAND FROM JT_ADRESSE WHERE " +
					"  ADRESSTYP=1 AND" +
					" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
					"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE WHERE ID_ADRESSKLASSE_DEF = #WERT1#))" +
					"  ORDER BY LAENDERNAME",
					"Land")

			._addCriteria("SELECT ID_ADRESSE, " +
					"NVL(NAME1,'')||' '||NVL(NAME2,'')||' '||PLZ||'  '||NVL(ORT,'')" +
					" FROM JT_ADRESSE WHERE " +
					" ADRESSTYP=1  AND" +
					" NVL(JT_ADRESSE.AKTIV,'N')='Y' AND (NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N') AND (NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N') AND " +
					"  ID_ADRESSE IN (SELECT ID_ADRESSE FROM JT_ADRESSKLASSE WHERE ID_ADRESSKLASSE_DEF = #WERT1#) " +
					" AND ID_LAND='#WERT2#' ORDER BY NAME1", 
					"Firmen");
		}


		@Override
		public void define_actions_4_lastSelection(String outputValue) throws myException {
			oTextField_idAdresse_input.rb_set_db_value_manual(outputValue);
			this.getPopUp().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}

	}

	private class ownLandKaskadeSelektionSuche extends E2_KaskadeSelectionSucheButton {

		public ownLandKaskadeSelektionSuche() throws myException {
			super();
 
			this
			._addCriteria(new SEL().ADD_Distinct().ADDFIELD(LAND.intrastat_jn).FROM(_TAB.land).s(), "Intrastat?")
			
			._addCriteria(new SEL().ADD_Distinct().ADDFIELD(LAND.hat_postcode).FROM(_TAB.land).WHERE("NVL("+LAND.intrastat_jn.tnfn()+",'N')", COMP.EQ.ausdruck(),"'#WERT1#'").s(), "Hat Postcode?")
			
			._addCriteria(new SEL().ADDFIELD(LAND.id_land).ADDFIELD(LAND.laendername).FROM(_TAB.land).WHERE("NVL("+LAND.intrastat_jn.tnfn()+",'N')", COMP.EQ.ausdruck(),"'#WERT1#'").AND("NVL("+LAND.hat_postcode.tnfn()+",'N')", COMP.EQ.ausdruck(),"'#WERT2#'").s(),"Land")
			;

		}

		@Override
		public void define_actions_4_lastSelection(String outputValue) throws myException {
			oTextField_Land_input.rb_set_db_value_manual(outputValue);
			this.getPopUp().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	private class ownArtikelKaskadeSelektionSuche extends E2_KaskadeSelectionSucheButton {


		public ownArtikelKaskadeSelektionSuche() throws myException {
			super();
 
			this._addCriteria(new SEL().ADDFIELD(EAK_BRANCHE.id_eak_branche).ADD_Distinct().ADDFIELD(EAK_BRANCHE.key_branche).FROM(_TAB.eak_branche).s(), "Branche");

			this._addCriteria(new SEL().ADDFIELD(EAK_GRUPPE.id_eak_gruppe).ADD_Distinct().ADDFIELD(EAK_GRUPPE.key_gruppe).FROM(_TAB.eak_gruppe).WHERE(EAK_GRUPPE.id_eak_branche.tnfn(),COMP.EQ.ausdruck(),"'#WERT1#'").s(), "Gruppe");

			this._addCriteria(new SEL().ADDFIELD(EAK_CODE.id_eak_code).ADD_Distinct().ADDFIELD(EAK_CODE.key_code).FROM(_TAB.eak_code).WHERE(EAK_CODE.id_eak_gruppe.tnfn(),COMP.EQ.ausdruck(),"'#WERT2#'").s(), "Code");

			this._addCriteria(new SEL().ADDFIELD(ARTIKEL.id_artikel).ADD_Distinct().ADDFIELD(ARTIKEL.anr1).FROM(_TAB.artikel).WHERE(ARTIKEL.id_eak_code.tnfn(),COMP.EQ.ausdruck(),"'#WERT3#'").s(), "Artikel nr. 1");

		}

		@Override
		public void define_actions_4_lastSelection(String outputValue) throws myException {
			oTextField_idArtikel_input.rb_set_db_value_manual(outputValue);
			this.getPopUp().CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	
	
	private class ownLagerSucheAssistant extends E2_KaskadeSelectionSucheButton{
		
		public ownLagerSucheAssistant() throws myException {
			super();
			
			this._addCriteria(new SEL().ADD_Distinct().ADDFIELD(LAND.id_land).ADDFIELD(LAND.laendername).FROM(_TAB.land)
					.INNERJOIN(_TAB.adresse, ADRESSE.id_land, LAND.id_land)
					.INNERJOIN(_TAB.lieferadresse, LIEFERADRESSE.id_adresse_liefer, ADRESSE.id_adresse)
					.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT()))
					.AND(new vgl(ADRESSE.id_adresse,COMP.GT, "1000"))
					.ORDER(LAND.id_land.tnfn()).s()
					
					,"Land");
			
			this._addCriteria(new SEL().ADD_Distinct().ADDFIELD(ADRESSE.ort).FROM(_TAB.adresse)
					.INNERJOIN(_TAB.lieferadresse, LIEFERADRESSE.id_adresse_liefer, ADRESSE.id_adresse)
					.INNERJOIN(_TAB.land, ADRESSE.id_land, LAND.id_land)
					.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT()))
					.AND(new vgl(ADRESSE.id_adresse,COMP.GT, "1000")).AND(LAND.id_land, COMP.EQ.ausdruck(), "#WERT1#")
					.ORDER(LAND.id_land.tnfn()).s()
					,"Ort");
			
			this._addCriteria(new SEL().ADDFIELD(ADRESSE.id_adresse).ADD_Distinct().ADDFIELD(ADRESSE.name1.tnfn() +"||' '||" + ADRESSE.name2.tnfn()).FROM(_TAB.adresse)
					.INNERJOIN(_TAB.lieferadresse, LIEFERADRESSE.id_adresse_liefer, ADRESSE.id_adresse)
					.INNERJOIN(_TAB.land, ADRESSE.id_land, LAND.id_land)
					.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, bibALL.get_ID_ADRESS_MANDANT()))
					.AND(new vgl(ADRESSE.id_adresse,COMP.GT, "1000"))
					.AND(LAND.id_land, COMP.EQ.ausdruck(), "#WERT1#")
					.AND("UPPER("+ADRESSE.ort.tnfn()+")", COMP.LIKE.ausdruck(), "UPPER('%#WERT2#%')")
					.ORDER(LAND.id_land.tnfn()).s()
					,"Lager");
			
		}
		
		@Override
		public void define_actions_4_lastSelection(String outputValue) throws myException {
			oTextField_lager_input.rb_set_db_value_manual(outputValue);
			this.getPopUp().CLOSE_AND_DESTROY_POPUPWINDOW(true);		
		}
	}
}
