package panter.gmbh.Echo2.RB.BETA_MASK_DEF;

import java.util.Set;
import java.util.TreeMap;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class RB_Mask_Descriptor extends TreeMap<RB_Mask_Key, RB_Cell>{
	private int 	mask_offset 	= 0;
	private int 	spaltenZahl 	= 0;
	private int 	spaltenBreite 	= 0;
	private int 	row_height 		= 25;

	private String 	maskname 		= "";
	private String 	maskname_l 		= "";
	private _TAB 	tabelle		 	= null;

	public RB_Mask_Descriptor() throws myException{
		super();
		define_mask();
		define_mask_cell();
	}

	public abstract void define_mask() throws myException;

	public abstract void define_mask_cell() throws myException;

	public  RB_Mask_Descriptor set_spaltenzahl(int i_spaltenzahl) throws myException{
		this.spaltenZahl = i_spaltenzahl;
		return this;
	}

	public  RB_Mask_Descriptor set_spaltenBreite(int i_spaltenbreite) throws myException{
		this.spaltenBreite = i_spaltenbreite;
		return this;
	}

	public  RB_Mask_Descriptor set_mask_offset(int i_maskoffset) throws myException{
		this.mask_offset = i_maskoffset;
		return this;
	}

	public  RB_Mask_Descriptor set_maskname(String p_maskname) throws myException{
		this.maskname = p_maskname;
		return this;
	}

	public  RB_Mask_Descriptor set_maskname_lang(String p_maskname_l) throws myException{
		this.maskname_l = p_maskname_l;
		return this;
	}

	public  RB_Mask_Descriptor set_tab(_TAB p_tab) throws myException{
		this.tabelle = p_tab;
		return this;
	}

	public int get_mask_offset() {
		return mask_offset;
	}

	public int get_spaltenZahl() {
		return spaltenZahl;
	}

	public int get_spaltenBreite() {
		return spaltenBreite;
	}

	public String get_maskname() {
		return maskname;
	}

	public String get_maskname_l() {
		return maskname_l;
	}

	public _TAB getTabelle() {
		return tabelle;
	}

	public int get_row_height() {
		return row_height;
	}

	public RB_Mask_Descriptor _row_height(int row_height) {
		this.row_height = row_height;
		return this;
	}

	public void register_cell(RB_Cell cell) throws myException{
		this.put(new RB_Mask_Key(cell.get_column_coordinate(), cell.get_row_coordinate()), cell);	
	}

	public VEK<RB_Mask_Key> select_cells_by_row(int i_row) throws myException{
		Set<RB_Mask_Key> keylist = this.keySet();

		VEK<RB_Mask_Key> unordered_rueck_keylist = new VEK<>();

		for(RB_Mask_Key k: keylist) {
			if(k.get_row()==i_row) {
				unordered_rueck_keylist._a(k);
			}
		}

		TreeMap<Integer, RB_Mask_Key> temp_map = new TreeMap<Integer, RB_Mask_Key>();
		for(RB_Mask_Key k: unordered_rueck_keylist) {
			temp_map.put(k.get_column(), k);
		}

		VEK<RB_Mask_Key> ordered_keymap = new VEK<>();
		ordered_keymap._a(temp_map.values());

		return ordered_keymap;
	}

	public int get_max_row() throws myException{
		int i_last_line = this.lastEntry().getKey().get_row();

		return i_last_line;
	}
}
