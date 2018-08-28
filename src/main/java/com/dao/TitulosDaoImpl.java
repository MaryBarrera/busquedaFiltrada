package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.entidades.BinTitulos;

import com.dao.AbstractSession;

@Repository
@Transactional
public class TitulosDaoImpl extends AbstractSession implements TitulosDao {

	@PersistenceContext 
	EntityManager em;
	
	@Override
	public void InsertaNuevo(BinTitulos titulo) {
		getSession().persist(titulo);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BinTitulos> BuscaTodosTitulos() {
		return getSession().createQuery("from BinTitulos").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BinTitulos> BuscarTitulos(String palabraClave) {
		//idreg_cat_titulos, titulo_original, titulo_en_espa, anio, realizador, pais , observaciones_cat_titulos, 
		//productor, circa, t_revisado, posible_dup 
		String queryString = "SELECT * from cat_titulos" 
				+ " where titulo_original like '%" + palabraClave + "%'"
				+ " order by titulo_original asc";
		return getSession().createNativeQuery(queryString, BinTitulos.class).getResultList();
		
		///from https://stackoverflow.com/a/11626877
//		Criteria cr = getSession().createCriteria(BinTitulos.class)
//			    .setProjection(Projections.projectionList()
//			    .add(Projections.property("titulo_original"), "titulo_original")
//			    .add(Projections.property("realizador"), "realizador")
//			    .add(Projections.property("anio"), "anio"))
//			    
//			    .setResultTransformer(Transformers.aliasToBean(BinTitulos.class));
//		return cr.list();
		
		///from https://stackoverflow.com/a/25233668
//		 CriteriaQuery<BinTitulos> c = em.getCriteriaBuilder().createQuery(BinTitulos.class); 
//		 Root<BinTitulos> from = c.from(BinTitulos.class); 
//
//		 c.select(from);
//		 c.where(em.getCriteriaBuilder().like(from.get("titulo_original"),"'%" + palabraClave + "%'")); // <- this will add the restriction. 
//		 c.orderBy(em.getCriteriaBuilder().asc(from.get("titulo_original"))); 
//		 return em.createQuery(c).getResultList(); 
		
	}

	@Override
	public void ActualizarTitulo(BinTitulos titulo) {
		getSession().update(titulo);	
	}

	@Override
	public BinTitulos BuscarID(int idreg_cat_titulos) {
		return (BinTitulos) getSession().get(BinTitulos.class, idreg_cat_titulos);
	}

	@Override
	public List<BinTitulos> MostrarSoloTitulos() {
		return getSession().createQuery("select titulo_original from BinTitulos order by titulo_original asc").list();
	}

}
