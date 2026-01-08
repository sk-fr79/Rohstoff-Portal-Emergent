package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class RB_HL_SelectLagerMandant extends RB_SelectField {
	
	
	public RB_HL_SelectLagerMandant(IF_Field oField, int iWidth, boolean b_ids_formated) throws myException {
		super(oField, new Extent(iWidth) );
				
		String lageradresseQuery = "SELECT  NVL("+ADRESSE.plz.tnfn()+",'-')||' '|| NVL("+ADRESSE.ort.tnfn()+",'-')||' '|| NVL("+ADRESSE.name1.tnfn()+",'-')," 
									+ ADRESSE.id_adresse.tnfn()+" " 
									+" FROM "
									+	bibE2.cTO()+"."+ADRESSE.fullTabName()+" INNER JOIN " +bibE2.cTO()+"."+LIEFERADRESSE.fullTabName()+" ON ( "+ADRESSE.id_adresse.tnfn()+"="+LIEFERADRESSE.id_adresse_liefer.tnfn()+" )"
									+ " WHERE " 
									+	LIEFERADRESSE.id_adresse_basis.tnfn()+" ="+bibALL.get_ID_ADRESS_MANDANT()
									+ " ORDER BY JT_ADRESSE.ORT";
									
		String hauptadresseQuery = new SEL().ADDFIELD("NVL("+ADRESSE.plz.tnfn()+",'-')||' '|| NVL("+ADRESSE.ort.tnfn()+",'-')||' '|| NVL("+ADRESSE.name1.tnfn()+",'-') || (' (Hauptadresse)')")
				.ADDFIELD(ADRESSE.id_adresse)
				.FROM(_TAB.adresse.fullTableName())
				.WHERE(ADRESSE.id_adresse.fieldName() ,"=",bibALL.get_ID_ADRESS_MANDANT()).s();
				
		if (b_ids_formated) {
			String[][] hauptAdresseErg = bibDB.EinzelAbfrageInArrayFormatiert(hauptadresseQuery);
			String[][] lageradresseErg = bibDB.EinzelAbfrageInArrayFormatiert(lageradresseQuery);
			this.set_oDataToView(new dataToView(bibALL.concat_arrays(hauptAdresseErg, lageradresseErg), false, bibE2.get_CurrSession()));
		} else {
			String[][] hauptAdresseErg = bibDB.EinzelAbfrageInArray(hauptadresseQuery);
			String[][] lageradresseErg = bibDB.EinzelAbfrageInArray(lageradresseQuery);
			this.set_oDataToView(new dataToView(bibALL.concat_arrays(hauptAdresseErg, lageradresseErg), false, bibE2.get_CurrSession()));
		}

		
		
		
	}

}
