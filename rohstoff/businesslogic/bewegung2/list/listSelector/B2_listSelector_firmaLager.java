package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_listSelector_firmaLager extends E2_ListSelectorMultiDropDown2 implements IF_CanBePopulated_NG{

	private E2_SelectionComponentsVector 	vSelector	= null;

	public B2_listSelector_firmaLager(E2_SelectionComponentsVector oSelVector, EnTabKeyInMask pos) throws myException {
		super();

		this.vSelector = oSelVector;

		B2_SelectField  selField = new B2_SelectField();
		selField.set_ListenInhalt(new String[][]{{"-",""}}, false);
		selField._fo_s_plus(-2);
		selField.setWidth(new Extent(100));
		
		And bedingung = new And();
		if(pos == EnTabKeyInMask.S1) {
			bedingung.and("S1.id_adresse", COMP.EQ, "#WERT#");
		}else if(pos == EnTabKeyInMask.S3) {
			bedingung.and("S3.id_adresse", COMP.EQ, "#WERT#");
		}
		
		this.INIT(selField, bedingung.s(),null);
	}

	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.get_oComponentWithoutText();
	}
	@Override
	public E2_BasicModuleContainer get_PopupContainer() throws myException {
		return new ownBasicContainer();
	}

	private class ownBasicContainer extends E2_BasicModuleContainer{}

	public void populate(VEK<String> vID_ADRESSE) throws myException
	{
		this.LEER_MACHEN();
		//this.vSelector.makeSelektion();
		if (vID_ADRESSE.size()==0)
		{
			this.get_oSelFieldBasis().set_ListenInhalt(new String[][]{{"-",""}}, false);
		}
		else
		{

			VEK<String >vIds = vID_ADRESSE;

			if(vID_ADRESSE.contains(bibALL.get_ID_ADRESS_MANDANT())){
				vIds.remove(vID_ADRESSE.indexOf(bibALL.get_ID_ADRESS_MANDANT()));

				vIds.add(0, bibALL.get_ID_ADRESS_MANDANT());
			}


			RecList21 reclistLagerAdresse = new RecList21(_TAB.adresse);

			reclistLagerAdresse.clear();

			for(String id:vIds) {
				Rec21_adresse recAdresse = new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(id));

				reclistLagerAdresse._add(recAdresse._getMainAdresse());

				SEL lieferadresse_selection = 
						new SEL("JT_ADRESSE.*")
						.FROM(_TAB.adresse)
						.WHERE(ADRESSE.id_adresse.tnfn(), COMP.IN.ausdruck(), "("+
								(new SEL(LIEFERADRESSE.id_adresse_liefer)
										.FROM(_TAB.lieferadresse)
										.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, ""+id))
										.AND(LIEFERADRESSE.id_adresse_liefer,COMP.GE.ausdruck(), "1000")   // Manfred: wegen Sonderlager sinnvoll
										.s()
										+")")
								
						).ORDER("JT_ADRESSE.aktiv DESC, JT_ADRESSE.ORT, JT_ADRESSE.NAME1, JT_ADRESSE.NAME2 ASC")	;

				RecList21 reclist_lief = new RecList21(_TAB.adresse)._fill(lieferadresse_selection.s());
				for(Rec21 rec_lief:reclist_lief) {
					reclistLagerAdresse._add(rec_lief);
				}
			}

			String[][] adrArr = new String[reclistLagerAdresse.size() + 1][2];

			adrArr[0] = new String[]{"-",""};

			for(int i=0;i<reclistLagerAdresse.size();i++) {
				adrArr[i+1] = new String[2] ;
				Rec21 recAddr = reclistLagerAdresse.get(i);

				String displayWert = 
						(recAddr.is_no_db_val(ADRESSE.aktiv)?"(INAKTIV) ":"") +
						recAddr.getUfs(ADRESSE.ort,"N/A")+" "+ 
						recAddr.getUfs(ADRESSE.plz,"N/A")+" "+
						recAddr.getUfs(ADRESSE.name1,"-")+"-"+
						recAddr.getUfs(ADRESSE.name2,"-")+" ("+
						recAddr.getFs(ADRESSE.id_adresse,"") +")";

				String id = recAddr.get_key_value();
				adrArr[i+1] = new String[] {displayWert, id};

			}

			this.get_oSelFieldBasis().set_ListenInhalt(adrArr,false);
		}
		this.get_oSelFieldBasis().set_ActiveInhalt_or_FirstInhalt("-");
	}
}
