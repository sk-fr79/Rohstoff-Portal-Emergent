package panter.gmbh.Echo2.components.activeReport_NG;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public abstract class AR_DataBlock extends Vector<AR_DataRow> {

	public abstract IF_AR_Component[][] 	_generate_titelComponentsInFrontOfBlock() throws myException;
	public abstract IF_AR_Component[][] 	_generate_footComponentsAfterBlock() throws myException;
	public abstract boolean  				_must_be_filled() throws myException;
	public abstract int      				_get_i_cols_to_inset_relativ_to_mother();

	public abstract boolean       			_is_top_level_block();
	
	private AR_DataRow              motherDataRow = null;
	
	public AR_DataBlock() {
		super();
	}
	
	public boolean add(AR_DataRow data_row) {
		data_row.set_DataBlockThisBelongsTo(this);
		return super.add(data_row);
	}
	
	
	
	public void set_mother_DataRow(AR_DataRow mother) {
		this.motherDataRow = mother;
	}
	
	
	public void fill(AR_Grid grid2fill) throws myException {
		if (this._must_be_filled()) {
			new AR_GridFiller(this.get_complete_insets(),grid2fill, this._generate_titelComponentsInFrontOfBlock());

			for (AR_DataRow dataLine: this ) {
				if (dataLine._must_be_filled()) {
					dataLine.fill(grid2fill);
				}
			}
			new AR_GridFiller(this.get_complete_insets(),grid2fill, this._generate_footComponentsAfterBlock());
		}
	}
	
	
	public int get_complete_insets() {
		int i=this._get_i_cols_to_inset_relativ_to_mother();
		
		AR_DataRow 		help = 			this.motherDataRow;
		
		if (help != null)  {
			AR_DataBlock 	helpBlock = 	help.get_DataBlockThisBelongsTo();
			if (helpBlock != null) {
				i += helpBlock.get_complete_insets();
			}
		}
		
		return i;
	}

	
	
	/**
	 * 
	 * @return s the top-lebe-block in the chain or null
	 */
	public AR_DataBlock find_top_levelBlock_in_chain() {
		AR_DataBlock block = null;
		
		while (true) {
			if (this._is_top_level_block()) {
				block = this;
				break;
			}
			
			AR_DataRow row = this.motherDataRow;
			if (row==null) {
				break;
			}
			
			AR_DataBlock tempBlock = row.get_DataBlockThisBelongsTo();
			if (tempBlock==null) {
				break;
			}
			
			return tempBlock.find_top_levelBlock_in_chain();
			
		}
		
		return block;
	}
	
}
