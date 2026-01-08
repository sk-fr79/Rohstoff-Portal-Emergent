package panter.gmbh.indep.myVectors;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.BasicInterfaces.IF__addHelperr;
import panter.gmbh.Echo2.BasicInterfaces.IF_seek_and_find_first;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class VEK<E> extends Vector<E> {

	public VEK() {
		super();
	}

	public VEK(Collection<? extends E> c) {
		super();
	}

	
	public VEK<E>  _a(E o) {
		this.add(o);
		return this;
	}

	
	public VEK<E>  _a(Collection<E> o) {
		if (o!=null) {
			this.addAll(o);
		}
		return this;
	}

	public VEK<E>  _clear() {
		this.removeAllElements();
		return this;
	}
	
	public VEK<E>  _addInFront(E o) {
		this.add(0,o);
		return this;
	}
	
	
	
	/**
	 * adds element and returns it
	 * @param o
	 * @return
	 */
	public E  _ar(E o) {
		this.add(o);
		return o;
	}

	
	public VEK<E>  _a(@SuppressWarnings("unchecked") E ... o) {
		for (E oo: o) {
			this.add(oo);
		}
		return this;
	}
	
	
	@SuppressWarnings("unchecked")
	public E[] toArray(Class<E> c) {
		E[] ret = null;
		if (this.size()>0) {
			ret = (E[])Array.newInstance(c, this.size());
			int i=0;
			for (E oo: this) {
				ret[i++]=oo;
			}
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @param finder
	 * @return
	 * @throws myException
	 */
	public E seek_and_find_first(IF_seek_and_find_first<E> finder) throws myException {
		return finder.get_found(this);
	}
	
	
	public Vector<E> getVector() {
		Vector<E> vRueck = new Vector<E>();
		
		vRueck.addAll(this);
		
		return vRueck;
	}
	
	
	/**
	 * adds validated objects, only when validation is positiv 
	 * @param validator
	 * @param o
	 * @return
	 */
	public VEK<E> _addValidated(Check<E> validator, @SuppressWarnings("unchecked") E ... o) {
		for (E e: o) {
			if (validator.isOk(e)) {
				this._a(e);
			}
		}
		return this;
	}
	
	
	
	
	/**
	 * adds validated objects, only when validation is positiv 
	 * @param validator
	 * @param o
	 * @return
	 */
	public VEK<E> _addValidated(Check<E> validator, Collection<E> c) {
		for (E e: c) {
			if (validator.isOk(e)) {
				this._a(e);
			}
		}
		return this;
	}
	
	
	
	/**
	 * adds validated objects, only when validation is positiv 
	 * @param validator
	 * @param o
	 * @return
	 */
	public VEK<E> _addVektor(IF__addHelperr<E> adder) throws myException {
		this.addAll(adder.getMembers());
		return this;
	}
	
	

	/**
	 * add alle elements of an array to vector
	 * @param array (can be null)
	 * @return this
	 */
	public VEK<E> _addArray(E[][] array ) {
		if (array!=null) {
			for (int i=0; i<array.length;i++) {
				for (int k=0;k<array[i].length; k++) {
					this._a(array[i][k]);
				}
			}
		}
		return this;
	}
	
	

	/**
	 * @deprecated  wenn null uebergeben wird, dann nullpointer-exception <br/>
	 *   	       use {@link #_addIfNotIN()} instead like this: 
	 * fuegt nur elemente ein, die noch nicht vorhanden sind
	 * @param o
	 * @return
	 */
	@Deprecated  
	@SuppressWarnings("unchecked")
	public VEK<E> _addIfNotIn( E ... o) {
		for (E e : o) {
			if (!this.contains(e)) {
				this.add(e);
			}
		}
		return this;
	}
	
	
	/**
	 * fuegt nur elemente ein, die noch nicht vorhanden sind
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public VEK<E> _addIfNotIN( E ... o) {
		if (o!=null) {
			for (E e : o) {
				if (!this.contains(e)) {
					this.add(e);
				}
			}
		}
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 19.12.2018
	 *
	 * @return array of generic-typ or null (when VEK is empty or has no nonNull member)
	 */
	@SuppressWarnings("unchecked")
	public E[] getArray() {
		E[] e = null;
		E elementNotNull = null;
		for (E o: this) {
			if (o!=null) {
				elementNotNull=o;
				break;
			}
		}
		
		if (elementNotNull!=null) {
			e = (E[]) Array.newInstance(elementNotNull.getClass(), this.size());
			int i = 0;
			for (E ee: this) {
				e[i++]=ee;
			}
		}
		return e;
	}
	

	
	public boolean equals(VEK<E> vgl) {
		boolean ret = true;
		if (this.size()!=vgl.size()) {
			return false;
		}
		
		for (int i=0;i<this.size();i++) {
			if (this.get(i)!=null && vgl.get(i)==null) {
				ret = false;
				break;
			} else if (this.get(i)==null && vgl.get(i)!=null) {
				ret = false;
				break;
			} else if (this.get(i)!=null && vgl.get(i)!=null) {
				if (!this.get(i).equals(vgl.get(i))) {
					ret = false;
					break;
				}
			}
		}
		
		return ret;
	}
	
	
    public String concatenante(String p_sep) {

        String sep = S.NN(p_sep);
        StringBuffer b = new StringBuffer();

        for (E e: this) {
            b.append(e.toString()+sep);
        }

        String ret = b.toString();
        if (S.isFull(sep)&&ret.endsWith(sep)) {
            ret = ret.substring(0,ret.length()-sep.length());
        }

        return ret;
    }


    /**
     *
     * @param segmentSize
     * @return s VEK with VEK<E> or empty VEK
     */
    public VEK<VEK<E>> getSegments(int segmentSize)  {
        VEK<VEK<E>> ret = new VEK<>();

        int i=0;

        while (true) {
            VEK<E> v = this.getSegment(segmentSize,i++);
            if (v.size()>0) {
                ret._a(v);
            }
            if (v.size()==0 || v.size()<segmentSize) {
                break;
            }
        }
        return ret;
    }


    /**
     *
     * @param segmentSize
     * @param segmentNumber (first number is 0)
     * @return
     */
    public VEK<E> getSegment(int segmentSize, int segmentNumber) {

        VEK<E> result = new VEK<>();
        if (segmentNumber>=0 && segmentSize>0) {

            int first = segmentNumber * segmentSize;
            int last = (segmentNumber + 1) * segmentSize;

            if (last >= this.size()) {
                last = this.size();
            }

            List<E> segment = this.subList(first, last);
            result._a(segment);
        }
        return result;
    }


    /**
     *
     * @param part
     * @return s position when found or -1
     */
    public long getPositionOf(E part) {
        long ret = -1;

        long count = 0;

        //zuerst mit == pruefen (fuer objecte)
        for (E e: this) {
            if (e==part) {
                ret = count;
                break;
            }
            count ++;
        }

        if (ret==-1) {
            //dann mit dem ungenaueren equals versuchen

            count = 0;
            for (E e : this) {
                if (e.equals(part)) {
                    ret = count;
                    break;
                }
            }
        }
        return ret;
    }


    
    /**
     * etwas elegantere sortierroutine mit lambda-ausdruck moeglich
     * @author martin
     * @date 29.10.2020
     *
     * @param rule
     * @return
     */
    public VEK<E> _sort(sortRule<E> rule) {
    	Comparator<E> comp = new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return rule.compare(o1, o2);
			}
		};
    	
		this.sort(comp);
		return this;
    }
    
	
    public static interface  sortRule<E> {
    	public int compare(E e1,E e2 );
    }
	
    
    
    public boolean isLastMember(E member) {
    	if (this.size()>0) {
    		if (member==null) {
    			if (this.get(this.size()-1)==null) {
    				return true;
    			} else {
    				return false;
    			}
    		} else {
		    	if (member==this.get(this.size()-1)) {
		    		return true;
		    	} else {
		    		return false;
		    	}
    		}
    	} else {
    		return false;
    	}
    }
    
    
    
    public VEK<E> getPartVEK(Check<E> check) {
    	VEK<E> ret = new VEK<E>();
    	for (E e: this) {
    		if (check.isOk(e)) {
    			ret._a(e);
    		}
    	}
    	return ret;
    }
    
    
}
