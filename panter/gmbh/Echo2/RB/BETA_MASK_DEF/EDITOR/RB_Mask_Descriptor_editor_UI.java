package panter.gmbh.Echo2.RB.BETA_MASK_DEF.EDITOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.exceptions.myException;

public class RB_Mask_Descriptor_editor_UI extends E2_Grid {

	private RB_TextField 	maskname_tf;
	private RB_TextArea 	maskname_l_tf;
	private RB_TextField	spaltenzahl_tf;
	private RB_TextField	spaltenbreite_tf;
	private RB_TextField	offset_tf;
	
	private own_table_field tab_selfield;
	
	public RB_Mask_Descriptor_editor_UI() throws myException {
		super();
		
		RB_gld gld_label 		= new RB_gld()._ins(2)._left_mid();
		RB_gld gld_textfield	= new RB_gld()._ins(2)._left_mid();
		
		this.tab_selfield		= new own_table_field();
		this.maskname_tf 		= new RB_TextField(200);
		this.maskname_l_tf		= new RB_TextArea(400,5);
		this.spaltenzahl_tf		= new RB_TextField(200);
		this.spaltenbreite_tf	= new RB_TextField(200);
		this.offset_tf			= new RB_TextField(200);	
		
		this._setSize(160,410);
		
		this
		._a("Tabellename", 			gld_label)._a(this.tab_selfield,	gld_textfield)
		._a("Maskname", 			gld_label)._a(this.maskname_tf, 	gld_textfield)
		._a("Maskname (lang)", 		gld_label)._a(this.maskname_l_tf, 	gld_textfield)
		._a("Spaltenzahl", 			gld_label)._a(this.spaltenzahl_tf, 	gld_textfield)
		._a("Spaltenbreite (in px)",gld_label)._a(this.spaltenbreite_tf,gld_textfield)
		._a("Offset links", 		gld_label)._a(this.offset_tf, 		gld_textfield)
		;
	}
	
	public String get_tabellename() throws myException{
		return tab_selfield.get_ActualWert();
	}
	
	public String get_maskname() throws myException{
		return this.maskname_tf.rb_readValue_4_dataobject();
	}
	
	public String get_maskname_lang() throws myException{
		return this.maskname_l_tf.rb_readValue_4_dataobject();
	}
	
	public String get_spaltenzahl() throws myException{
		return this.spaltenzahl_tf.rb_readValue_4_dataobject();
	}
	
	public String get_spaltenbreite() throws myException{
		return this.spaltenbreite_tf.rb_readValue_4_dataobject();
	}
	
	public String get_offset() throws myException{
		return this.offset_tf.rb_readValue_4_dataobject();
	}

	
	private class own_table_field extends MyE2_SelectField {
		public own_table_field() throws myException {
			super(DB_META.get_TablesQuerySort_A_to_Z(bibE2.cTO(),true, true, true)
				,true
				,false 
				,false
				,false
				,new Extent(200)
					);
		}
	}
}
