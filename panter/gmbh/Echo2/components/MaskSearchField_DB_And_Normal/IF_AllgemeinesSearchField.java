package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

//interface zur vereinheitlichung der MyE2_MaskSearchField und MyE2_DB_MaskSearchField 
public interface IF_AllgemeinesSearchField
{
	public Vector<DefSpalteLayout_And_Else>  get_vSortierInfo() throws myException;
	
	public Boolean                    get_bResultListIsSortable();
	
}
