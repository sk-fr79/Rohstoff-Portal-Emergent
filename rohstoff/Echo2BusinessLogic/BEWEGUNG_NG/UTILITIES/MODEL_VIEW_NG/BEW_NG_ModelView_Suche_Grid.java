package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW_NG;

import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextFieldWithEraser;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class BEW_NG_ModelView_Suche_Grid extends MyE2_Grid {

	private MyE2_TextFieldWithEraser id_feld_bew;
	private MyE2_TextFieldWithEraser id_feld_vekt;
	private MyE2_TextFieldWithEraser id_feld_vekt_pos;
	private MyE2_TextFieldWithEraser id_feld_atom;
	private MyE2_TextFieldWithEraser id_feld_stat;

	public BEW_NG_ModelView_Suche_Grid() throws myException {
		super(3);
		this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());

		id_feld_bew 			= new MyE2_TextFieldWithEraser(true);
		id_feld_vekt 			= new MyE2_TextFieldWithEraser(true);
		id_feld_vekt_pos		= new MyE2_TextFieldWithEraser(true);
		id_feld_atom 			= new MyE2_TextFieldWithEraser(true);
		id_feld_stat 			= new MyE2_TextFieldWithEraser(true);

		this.add(new MyE2_Label("ID BEWEGUNG: "));
		this.add(id_feld_bew);
		this.add(new BEW_NG_ModelView_bt(_TAB.bewegung) {
			@Override
			public String get_id_table() {

				return id_feld_bew.getText();
			}
		});

		this.add(new MyE2_Label("ID BEWEGUNG VEKTOR: "));
		this.add(id_feld_vekt);
		this.add(new BEW_NG_ModelView_bt(_TAB.bewegung_vektor) {

			@Override
			public String get_id_table() {

				return id_feld_vekt.getText();
			}
		});

		this.add(new MyE2_Label("ID BEWEGUNG VEKTOR POSITION: "));
		this.add(id_feld_vekt_pos);
		this.add(new BEW_NG_ModelView_bt(_TAB.bewegung_vektor_pos) {

			@Override
			public String get_id_table() {
				return id_feld_vekt_pos.getText();
			}
		});
		
		this.add(new MyE2_Label("ID BEWEGUNG ATOM: "));
		this.add(id_feld_atom);
		this.add(new BEW_NG_ModelView_bt(_TAB.bewegung_atom) {

			@Override
			public String get_id_table() {

				return id_feld_atom.getText();
			}
		});

		this.add(new MyE2_Label("ID BEWEGUNG STATION: "));
		this.add(id_feld_stat);
		this.add(new BEW_NG_ModelView_bt(_TAB.bewegung_station) {

			@Override
			public String get_id_table() {

				return id_feld_stat.getText();
			}
		});
	}
}
