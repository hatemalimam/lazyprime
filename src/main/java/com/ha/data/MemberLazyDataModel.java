package com.ha.data;

import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ha.model.Member;
import com.ha.model.session.MemberSessionBean;



public class MemberLazyDataModel extends LazyDataModel<Member> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberSessionBean memSession;
	
	private Integer count;
	
	public MemberLazyDataModel() {
		injectEjb();
	}
	
	private void injectEjb() {
		try {
			InitialContext ic = new InitialContext();
			memSession = (MemberSessionBean) ic
					.lookup("java:app/lazyprime/MemberSessionBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		if(count == null) {
			count = memSession.findAllCount();
			this.setRowCount(count);
		}
		return memSession.findAll(first, pageSize);
	}
}
