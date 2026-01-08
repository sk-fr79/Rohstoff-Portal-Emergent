package panter.gmbh.Echo2.components.activeReport_NG;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public abstract class AR_DataRow {
	
	private AR_DataRow   			mother_DataRow = null;
	private Vector<AR_DataBlock> 	daughter_Fillers = new Vector<AR_DataBlock>();
	private AR_DataBlock            dataBlockThisBelongsTo = null;

	public abstract IF_AR_Component[][] _generate_Components() throws myException;       
	public abstract IF_AR_Component[][] _generate_titelComponentsInFrontOfRow() throws myException;
	public abstract IF_AR_Component[][] _generate_footComponentsAfterRow() throws myException;
	
	public abstract boolean  			_must_be_filled() throws myException;
	
	public AR_DataRow() {
		super();
	}
	
	
	public void add_daughter_DataBlock(AR_DataBlock daughterDataBlock) throws myException {
		daughterDataBlock.set_mother_DataRow(this);
		this.daughter_Fillers.add(daughterDataBlock);
	}

	
	public void fill(AR_Grid grid2fill) throws myException {
		boolean bMustBeFilled = this._must_be_filled();
		int   	i_empty_cols_left = 0;
		if (this.dataBlockThisBelongsTo != null) {
			bMustBeFilled = bMustBeFilled && this.dataBlockThisBelongsTo._must_be_filled();
			i_empty_cols_left = this.dataBlockThisBelongsTo.get_complete_insets();
		}
		
		if (bMustBeFilled) {
			
			
			new AR_GridFiller(i_empty_cols_left, grid2fill, this._generate_titelComponentsInFrontOfRow());
			new AR_GridFiller(i_empty_cols_left, grid2fill, this._generate_Components());
			
			for (AR_DataBlock dataBlock: this.daughter_Fillers) {
				dataBlock.fill(grid2fill);
			}
			new AR_GridFiller(i_empty_cols_left, grid2fill, this._generate_footComponentsAfterRow());
		}
	}

	

	public AR_DataRow get_mother_DataRow() {
		return mother_DataRow;
	}


	public void set_mother_DataRow(AR_DataRow mother_DataRow) {
		this.mother_DataRow = mother_DataRow;
	}


	public AR_DataBlock get_DataBlockThisBelongsTo() {
		return this.dataBlockThisBelongsTo;
	}


	public void set_DataBlockThisBelongsTo(AR_DataBlock p_fillerBlockThisBelongsTo) {
		this.dataBlockThisBelongsTo = p_fillerBlockThisBelongsTo;
	}
	
	/**
	 * 
	 * @return s the top-lebe-block in the chain or null
	 */
	public AR_DataBlock find_top_levelBlock_in_chain() {
		AR_DataBlock block = this.dataBlockThisBelongsTo;
		if (block == null) {
			return null;
		}
		
		if (block._is_top_level_block()) {
			return block;
		} else {
			return block.find_top_levelBlock_in_chain();
		}
	}

}
