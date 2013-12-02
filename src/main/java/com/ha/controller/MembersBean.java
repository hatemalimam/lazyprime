package com.ha.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.ha.data.MemberLazyDataModel;
import com.ha.model.Member;

@ManagedBean(name = "membersBean")
@ViewScoped
public class MembersBean {

	private LazyDataModel<Member> dataModel;
	
	public MembersBean() {
		
	}
	
	@PostConstruct
	public void init() {
		dataModel = new MemberLazyDataModel();
	}

	public LazyDataModel<Member> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<Member> dataModel) {
		this.dataModel = dataModel;
	}
	
}
