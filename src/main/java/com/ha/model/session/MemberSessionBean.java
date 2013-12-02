package com.ha.model.session;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ha.model.Member;

/**
 * Session Bean implementation class MemberSessionBean
 */
@Stateless
public class MemberSessionBean {

	@PersistenceContext
	protected EntityManager entityMan;

	@Inject
	private Logger log;

	/**
	 * Default constructor.
	 */
	public MemberSessionBean() {

	}

	public List<Member> findAll(Integer first, Integer pageSize) {
		CriteriaBuilder cb = entityMan.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.select(member).orderBy(cb.asc(member.get("name")));
		return entityMan.createQuery(criteria).setFirstResult(first)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer findAllCount() {
		CriteriaBuilder cb = entityMan.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.multiselect(member.get("id"));
		return entityMan.createQuery(criteria).getResultList().size();
	}

}
