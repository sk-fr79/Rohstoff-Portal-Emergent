package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.exceptions.myException;

public interface IF_RecordListFilter<T> {
   public boolean isInFilter(T rec) throws myException;
}
