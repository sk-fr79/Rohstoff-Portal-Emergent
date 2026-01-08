package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ColorGrid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSKLASSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSKLASSE_DEF_EXT;

public class FS_LIST_compMarkAdressklasse_color extends MyE2_DB_PlaceHolder_onlyWhenVisisble {

	public FS_LIST_compMarkAdressklasse_color(SQLField osqlField) 	throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String id_adresse_f, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		
		MyE2_Grid  				grid4Colors = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		String id_adresse_uf = new MyLong(id_adresse_f).get_cUF_LongString();
		
		RECORD_ADRESSE 			recAd = new RECORD_ADRESSE(id_adresse_uf);
		RECLIST_ADRESSKLASSE 	rlAD = recAd.get_DOWN_RECORD_LIST_ADRESSKLASSE_id_adresse();
		
		LinkedHashMap<String, RECORD_ADRESSKLASSE_DEF_EXT>  hmCols = bibSES.get_MarkerColorsAdressClass();   //die adressklassen, die eine farbe zudefiniert haben
		
		grid4Colors.setSize(hmCols.size());
		grid4Colors.setRowHeight(0, new Extent(15));
		
		Vector<String>  vAdressklassThisAdressBelongsTo = new Vector<String>(rlAD.get_ID_ADRESSKLASSE_DEF_hmString_UnFormated("").values());
		
		//alle adressklasse-def s durchgehen, die eine farbe haben
		for (String id_adressklasse_def: hmCols.keySet()) {
			boolean bAdresseGehoertDazu = vAdressklassThisAdressBelongsTo.contains(id_adressklasse_def);
			MyE2_Grid gridLabel = null;
			if (bAdresseGehoertDazu) {
				gridLabel = hmCols.get(id_adressklasse_def).grid_with_color(15, 15, new E2_ColorBase());
			} else {
				gridLabel = new E2_ColorGrid(15, 15, new E2_ColorBase());
			}
			
			grid4Colors.add(gridLabel, E2_INSETS.I(1,1,1,1));
		}
		
		this.removeAll();

		grid4Colors.setWidth(new Extent(1+(grid4Colors.getSize()*17)));
		
		this.add(grid4Colors,E2_INSETS.I(0,0,0,0));
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		try {
			return new FS_LIST_compMarkAdressklasse_color(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	
	
}
