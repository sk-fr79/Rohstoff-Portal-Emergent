package panter.gmbh.Echo2.ListAndMask.List.Selektion.Multiselection;

import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_EXT;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_ListSelector_4Multiselect extends XX_ListSelektor_EXT {
	
	public abstract E2_Grid  render_representation_4_base_selector(Vector<XX_ListSelector_4Multiselect>  	vector_of_inner_selectors) throws myException;
	public abstract E2_Grid  render_representation_4_popup_selector(Vector<XX_ListSelector_4Multiselect>  	vector_of_inner_selectors) throws myException;
	public abstract E2_Grid  render_representation_placeholder(Vector<XX_ListSelector_4Multiselect>  		vector_of_inner_selectors) throws myException;

}
