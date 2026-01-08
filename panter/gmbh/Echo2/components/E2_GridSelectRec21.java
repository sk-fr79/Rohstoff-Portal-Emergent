/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 31.07.2020
 * 
 */
package panter.gmbh.Echo2.components;

import java.util.Comparator;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_ExecuterWithObject;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimpleV2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 31.07.2020
 * grid, das mit einer RecList21 gefuellt wird und mit einer buttonZeile pro record gefuellt wird, um eine auswahl treffen zu koennen
 */
public class E2_GridSelectRec21 extends E2_Grid {

	private RecList21            	fillingList = null;
	private RecList21            	firstList  = null;
	
	private VEK<ButtonGenerator> 	listButtonGenerators = 	new VEK<E2_GridSelectRec21.ButtonGenerator>();
	private VEK<SortButton>      	sortButtons = 			new VEK<E2_GridSelectRec21.SortButton>();
	private VEK<Component>  	 	headLine = 				new VEK<Component>();
	private String 				 	filterString = null;
	private VEK<XX_ActionAgent>  	actionAgentsForButtons = new VEK<XX_ActionAgent>();
	
	private RB_gld  			 	layoutData4buttons = 		new RB_gld()._ins(1, 2, 2, 1);
	private RB_gld  			 	layoutData4Markedbuttons = 	new RB_gld()._ins(1, 2, 2, 1)._col_back_ll();
			
	private RB_gld  			 	layoutData4titel = 			new RB_gld()._ins(1, 2, 2, 2)._col_back_d();
	private Component    			titelComponent = 			null;
	private RB_gld       		 	layoutData4Headline = 		null;

	private E2_Font 				listButtonFont = 			new E2_FontPlain();
	private E2_Font 				listButtonFontMarked = 		new E2_FontBold();
	
	private VEK<ButtonWithRec[]> 	vektorOfButtonArrays = 		new VEK<ButtonWithRec[]>();

	private VEK<MarkingVerifier>    markingVerifiers = 			new VEK<E2_GridSelectRec21.MarkingVerifier>();
	
	private ButtonStyler       		buttonStyler = 				null;
	
	
	/**
	 * @author martin
	 * @date 31.07.2020
	 *
	 */
	public E2_GridSelectRec21() {
		super();
	}

	
	public E2_GridSelectRec21 _addListButtonGenerator(ButtonGenerator bg) {
		this.listButtonGenerators._a(bg);
		return this;
	}

	public E2_GridSelectRec21 _addListField(IF_Field field) {
		this.listButtonGenerators._a(new ButtonGeneratorStd()._addField(field));
		
		return this;
	}
	
	public E2_GridSelectRec21 _addActionAgentForListButtons(XX_ActionAgent agent) {
		this.actionAgentsForButtons._a(agent);
		
		for (ButtonWithRec[] buttonLines: vektorOfButtonArrays) {
			for (ButtonWithRec bt: buttonLines) {
				bt.get_vActionAgents().clear();
				bt._aaaV(actionAgentsForButtons);
			}
		}
		
		return this;
	}
	
	public E2_GridSelectRec21 _addActionAgentForListButtons(IF_agentSimple agent) {
		this.actionAgentsForButtons._a(agent.genActionAgent());

		for (ButtonWithRec[] buttonLines: vektorOfButtonArrays) {
			for (ButtonWithRec bt: buttonLines) {
				bt.get_vActionAgents().clear();
				bt._aaaV(actionAgentsForButtons);
			}
		}
		return this;
	}

	
	public E2_GridSelectRec21 _addActionAgentForListButtons(IF_agentSimpleV2 agent) {
		this.actionAgentsForButtons._a(agent.genActionAgent());

		for (ButtonWithRec[] buttonLines: vektorOfButtonArrays) {
			for (ButtonWithRec bt: buttonLines) {
				bt.get_vActionAgents().clear();
				bt._aaaV(actionAgentsForButtons);
			}
		}
		return this;
	}

	
	
	public E2_GridSelectRec21 _addHeadLineComponent(Component comp) {
		this.headLine._a(comp);
		if (comp instanceof SortButton) {
			sortButtons._a((SortButton) comp);
		}
		return this;
	}

	
	
	/**
	 * kann mit einem formatierer ueber alle buttons z.b. zum markieren benutzt werden
	 * @author martin
	 * @date 04.08.2020
	 *
	 * @param formater
	 * @return
	 */
	public E2_GridSelectRec21 _formatListButtons(IF_ExecuterWithObject<ButtonWithRec> formater) {
		for (ButtonWithRec[] buttonLine: vektorOfButtonArrays) {
			for (ButtonWithRec button: buttonLine) {
				formater.execute(button);
			}
		}
		return this;
	}
	
	
	/**
	 * @return the filterString
	 */
	public String getFilterString() {
		return filterString;
	}
	
	/**
	 * @param filterString the filterString to set
	 */
	public E2_GridSelectRec21 _setFilterString(String filterString) {
		this.filterString = filterString;
		return this;
	}

	
	
	
	/**
	 * @return the fillingList
	 */
	public RecList21 getFillingList() {
		return fillingList;
	}


 
	
	public E2_GridSelectRec21 _renderGrid(RecList21 fillingList) {
		this.fillingList = fillingList;
		if (this.firstList==null) {
			firstList = new RecList21(fillingList.get_tab());
			firstList.putAll(this.fillingList);
		}
		return this._renderGrid();
	}
	
	
	
	public E2_GridSelectRec21 _renderGrid() {
		vektorOfButtonArrays = new VEK<ButtonWithRec[]>();
		

		for (Rec21 r: this.fillingList) {
			ButtonWithRec[] line = new ButtonWithRec[this.listButtonGenerators.size()];
			
			int col = 0;
			for (ButtonGenerator gen: this.listButtonGenerators) {
				line[col++] = gen.generateButton(r);
			}
			vektorOfButtonArrays._a(line);
		}

		
		
		//jetzt das grid aufbauen
		this._clear();
		this._s(this.listButtonGenerators.size());
		
	
		//ueberschrift ...
		if (this.titelComponent!=null) {
			RB_gld layout = new RB_gld()._span(this.listButtonGenerators.size());
			if (this.layoutData4Headline!=null) {
				layout = this.layoutData4Headline._c()._span(this.listButtonGenerators.size());
			}
			this._a(titelComponent,layout);
		}
		
		
		// headline (mit sortbuttons)
		if (vektorOfButtonArrays.size()>0 && headLine.size()==vektorOfButtonArrays.get(0).length) {
			headLine.stream().forEach(b->{
				if (b.getLayoutData()!=null) {
					_add_raw(b);
				} else {
					_a(b, layoutData4titel);
				}
			});
		}
		
		for (ButtonWithRec[] line: vektorOfButtonArrays) {
			boolean inFilter = true;
			if (S.isFull(filterString)) {
				inFilter = false;
					
				for (E2_Button b: line) {
					if (b.getText().toUpperCase().contains(filterString.toUpperCase())) {
						inFilter = true;
						break;
					}
				}
			}
			if (inFilter) {
				int col = 0;
				for (ButtonWithRec b: line) {
					
					if (isMarked(b)) {
						_a(b, layoutData4Markedbuttons); 
						b.setFont(listButtonFontMarked);
					} else {
						_a(b, layoutData4buttons); 
						b.setFont(listButtonFont);
					}
					
					// buttonStyler ueberschreibt evtl. vorherige styes
					if (buttonStyler!=null) {
						buttonStyler.applyStyleAndLayoutToButton(b, col);
					}
					
					
					b._aaaV(actionAgentsForButtons);
					col++;
				};
			}
		}
		
		return this;
	}
	
	
	

	/**
	 * @return the titelComponent
	 */
	public Component getTitelComponent() {
		return titelComponent;
	}


	/**
	 * @param titelComponent the titelComponent to set
	 */
	public E2_GridSelectRec21 _setTitelComponent(Component titelComponent,RB_gld titelLayout) {
		this.titelComponent = titelComponent;
		this.layoutData4Headline = titelLayout;
		return this;
	}


	/**
	 * @return the titelLayout
	 */
	public RB_gld getTitelLayout() {
		return layoutData4Headline;
	}

	/**
	 * @return the layoutData4buttons
	 */
	public RB_gld getLayoutData4buttons() {
		return layoutData4buttons;
	}


	/**
	 * @param layoutData4buttons the layoutData4buttons to set
	 */
	public E2_GridSelectRec21 _setLayoutData4buttons(RB_gld layoutData4buttons) {
		this.layoutData4buttons = layoutData4buttons;
		return this;
	}


	/**
	 * @return the layoutData4titel
	 */
	public RB_gld getLayoutData4titel() {
		return layoutData4titel;
	}


	/**
	 * @param layoutData4titel the layoutData4titel to set
	 */
	public E2_GridSelectRec21 _setLayoutData4titel(RB_gld layoutData4titel) {
		this.layoutData4titel = layoutData4titel;
		return this;
	}



	/**
	 * @return the listButtonFont
	 */
	public E2_Font getListButtonFont() {
		return listButtonFont;
	}


	/**
	 * @param listButtonFont the listButtonFont to set
	 */
	public E2_GridSelectRec21 _setListButtonFont(E2_Font listButtonFont) {
		this.listButtonFont = listButtonFont;
		return this;
	}

	
	

	/**
	 * @return the listButtonFontMarked
	 */
	public E2_Font getListButtonFontMarked() {
		return listButtonFontMarked;
	}


	/**
	 * @param listButtonFontMarked the listButtonFontMarked to set
	 */
	public E2_GridSelectRec21 _setListButtonFontMarked(E2_Font listButtonFontMarked) {
		this.listButtonFontMarked = listButtonFontMarked;
		return this;
	}



	/**
	 * @return the layoutData4Markedbuttons
	 */
	public RB_gld getLayoutData4Markedbuttons() {
		return layoutData4Markedbuttons;
	}


	/**
	 * @param layoutData4Markedbuttons the layoutData4Markedbuttons to set
	 */
	public E2_GridSelectRec21 _setLayoutData4Markedbuttons(RB_gld layoutData4Markedbuttons) {
		this.layoutData4Markedbuttons = layoutData4Markedbuttons;
		return this;
	}


	/**
	 * @return the markingVerifiers
	 */
	public VEK<MarkingVerifier> getMarkingVerifiers() {
		return markingVerifiers;
	}


	public E2_GridSelectRec21 _addMarkingVerifier(MarkingVerifier mv) {
		markingVerifiers._a(mv);
		return this;
	}
	
	
	boolean isMarked(ButtonWithRec button) {
		for (MarkingVerifier mv : markingVerifiers) {
			if (mv.isMarked(button)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the buttonStyler
	 */
	public ButtonStyler getButtonStyler() {
		return buttonStyler;
	}


	/**
	 * @param buttonStyler the buttonStyler to set
	 */
	public E2_GridSelectRec21 _setButtonStyler(ButtonStyler buttonStyler) {
		this.buttonStyler = buttonStyler;
		return this;
	}

	
	
	//---------------------------- ab hier klassen und interface-definitionen

	
	public static interface ButtonGenerator {
		public ButtonWithRec generateButton(Rec21 rec);
	}
	
	
	
	public  class ButtonGeneratorStd implements ButtonGenerator {
		private VEK<IF_Field> fields = new VEK<IF_Field>();

		public ButtonGeneratorStd() {
			super();
		}

		public ButtonWithRec generateButton(Rec21 rec) {
			ButtonWithRec button = new ButtonWithRec(rec);
//			button._style(E2_Button.baseStyle());
			
			VEK<String> labels = new VEK<String>();
			
			for (IF_Field f: fields) {
				try {
					String s = rec.get_fs_dbVal(f,"");
					labels._addValidated((val)->{return S.isFull(val);},s);
				} catch (myException e) {
					e.printStackTrace();
				}
			}
			
			String labelButton = labels.concatenante(" ");
			if (S.isEmpty(labelButton.trim())) {
				labelButton="-";
			}
			button._t(labelButton);
			button._fo(listButtonFont);
			return button;
		}

		public ButtonGeneratorStd _addField(IF_Field field) {
			this.fields._a(field);
			return this;
		}
		
	}


	
	
	
	
	public enum EnSortStatus {
		up("down", E2_ResourceIcon.get_RI("sortup.png")),
		down("neutral", E2_ResourceIcon.get_RI("sortdown.png")),
		neutral("up", E2_ResourceIcon.get_RI("sortupdown_mini.png"));
		
		private String 				nextstatus = null;
		private E2_ResourceIcon		icon = null;
		
		private EnSortStatus(String next, E2_ResourceIcon icon) {
			nextstatus = next;
			this.icon = icon;
		}
		
		public EnSortStatus getNextStatus() {
			for (EnSortStatus  s: EnSortStatus.values()) {
				if (s.name().equals(nextstatus)) {
					return s;
				}
			}
			return null;
		}
	}
	
	
	
	
	public  class SortButton extends E2_Button {
		
		private Comparator<Rec21>  comparatorUp = null;
		private Comparator<Rec21>  comparatorDown = null;
		private EnSortStatus       sortStatus = EnSortStatus.neutral;
		

		/**
		 * @author martin
		 * @date 04.08.2020
		 *
		 * @param comparatorUp
		 */
		public SortButton(Comparator<Rec21> p_comparatorUp, Comparator<Rec21>  p_comparatorDown) {
			super();
			this.comparatorUp = p_comparatorUp;
			this.comparatorDown = p_comparatorDown;
			
			this._aaa(()-> {
//				fillingList = fillingList.getSorted(comparatorUp);
				
				for (SortButton sb: sortButtons) {
					if (sb!=SortButton.this) {
						sb._setSortStatus(EnSortStatus.neutral);
					} else {
						EnSortStatus nextStatus = sb.getSortStatus().getNextStatus();
						sb._setSortStatus(nextStatus);
						
						if (nextStatus==EnSortStatus.up) {
							fillingList = fillingList.getSorted(comparatorUp);
						} else if (nextStatus==EnSortStatus.down) {
							fillingList = fillingList.getSorted(comparatorDown);
						} else {
							fillingList.clear();
							fillingList.putAll(firstList);
						}
						_renderGrid();
						
					}
				}
				_renderGrid();
			});
			
			this._setSortStatus(EnSortStatus.neutral);
			this._setBorder(new E2_ColorDDDark());
			this._setRolloverBorder(new E2_ColorDDark());
			this._setBorderPressed(new E2_ColorDDark());
			
		}
		


		public EnSortStatus getSortStatus() {
			return sortStatus;
		}

		public SortButton _setSortStatus(EnSortStatus sortStatus) {
			this.sortStatus = sortStatus;
			this.setIcon(sortStatus.icon);
			this.setIconTextMargin(new Extent(5));
			return this;
		}
		
	}


	
	//button fuer die lookup-liste
	public static class ButtonWithRec extends E2_Button {
		private Rec21 rec21 = null;

		public ButtonWithRec(Rec21 record) {
			super();
			this._style(E2_Button.baseStyle());

			this.rec21 = record;
		}

		public Rec21 getRec21() {
			return rec21;
		}

		public ButtonWithRec _setRec21(Rec21 rec21) {
			this.rec21 = rec21;
			return this;
		}
		
	}

	
	public static interface MarkingVerifier {
		public boolean isMarked(ButtonWithRec button);
	}


	public static interface ButtonStyler {
		public void applyStyleAndLayoutToButton(ButtonWithRec button, int colNumber);
	}

	
}
 