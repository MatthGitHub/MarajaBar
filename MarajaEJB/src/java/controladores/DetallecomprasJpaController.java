/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Compras;
import entidades.Detallecompras;
import entidades.DetallecomprasPK;
import entidades.Mercaderia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Administrador
 */
public class DetallecomprasJpaController implements Serializable {

    public DetallecomprasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallecompras detallecompras) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (detallecompras.getDetallecomprasPK() == null) {
            detallecompras.setDetallecomprasPK(new DetallecomprasPK());
        }
        detallecompras.getDetallecomprasPK().setFkMerca(detallecompras.getMercaderia().getIdMerca());
        detallecompras.getDetallecomprasPK().setFkCompras(detallecompras.getCompras().getIdCompras());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Compras compras = detallecompras.getCompras();
            if (compras != null) {
                compras = em.getReference(compras.getClass(), compras.getIdCompras());
                detallecompras.setCompras(compras);
            }
            Mercaderia mercaderia = detallecompras.getMercaderia();
            if (mercaderia != null) {
                mercaderia = em.getReference(mercaderia.getClass(), mercaderia.getIdMerca());
                detallecompras.setMercaderia(mercaderia);
            }
            em.persist(detallecompras);
            if (compras != null) {
                compras.getDetallecomprasList().add(detallecompras);
                compras = em.merge(compras);
            }
            if (mercaderia != null) {
                mercaderia.getDetallecomprasList().add(detallecompras);
                mercaderia = em.merge(mercaderia);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDetallecompras(detallecompras.getDetallecomprasPK()) != null) {
                throw new PreexistingEntityException("Detallecompras " + detallecompras + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallecompras detallecompras) throws NonexistentEntityException, RollbackFailureException, Exception {
        detallecompras.getDetallecomprasPK().setFkMerca(detallecompras.getMercaderia().getIdMerca());
        detallecompras.getDetallecomprasPK().setFkCompras(detallecompras.getCompras().getIdCompras());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detallecompras persistentDetallecompras = em.find(Detallecompras.class, detallecompras.getDetallecomprasPK());
            Compras comprasOld = persistentDetallecompras.getCompras();
            Compras comprasNew = detallecompras.getCompras();
            Mercaderia mercaderiaOld = persistentDetallecompras.getMercaderia();
            Mercaderia mercaderiaNew = detallecompras.getMercaderia();
            if (comprasNew != null) {
                comprasNew = em.getReference(comprasNew.getClass(), comprasNew.getIdCompras());
                detallecompras.setCompras(comprasNew);
            }
            if (mercaderiaNew != null) {
                mercaderiaNew = em.getReference(mercaderiaNew.getClass(), mercaderiaNew.getIdMerca());
                detallecompras.setMercaderia(mercaderiaNew);
            }
            detallecompras = em.merge(detallecompras);
            if (comprasOld != null && !comprasOld.equals(comprasNew)) {
                comprasOld.getDetallecomprasList().remove(detallecompras);
                comprasOld = em.merge(comprasOld);
            }
            if (comprasNew != null && !comprasNew.equals(comprasOld)) {
                comprasNew.getDetallecomprasList().add(detallecompras);
                comprasNew = em.merge(comprasNew);
            }
            if (mercaderiaOld != null && !mercaderiaOld.equals(mercaderiaNew)) {
                mercaderiaOld.getDetallecomprasList().remove(detallecompras);
                mercaderiaOld = em.merge(mercaderiaOld);
            }
            if (mercaderiaNew != null && !mercaderiaNew.equals(mercaderiaOld)) {
                mercaderiaNew.getDetallecomprasList().add(detallecompras);
                mercaderiaNew = em.merge(mercaderiaNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallecomprasPK id = detallecompras.getDetallecomprasPK();
                if (findDetallecompras(id) == null) {
                    throw new NonexistentEntityException("The detallecompras with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallecomprasPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Detallecompras detallecompras;
            try {
                detallecompras = em.getReference(Detallecompras.class, id);
                detallecompras.getDetallecomprasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallecompras with id " + id + " no longer exists.", enfe);
            }
            Compras compras = detallecompras.getCompras();
            if (compras != null) {
                compras.getDetallecomprasList().remove(detallecompras);
                compras = em.merge(compras);
            }
            Mercaderia mercaderia = detallecompras.getMercaderia();
            if (mercaderia != null) {
                mercaderia.getDetallecomprasList().remove(detallecompras);
                mercaderia = em.merge(mercaderia);
            }
            em.remove(detallecompras);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallecompras> findDetallecomprasEntities() {
        return findDetallecomprasEntities(true, -1, -1);
    }

    public List<Detallecompras> findDetallecomprasEntities(int maxResults, int firstResult) {
        return findDetallecomprasEntities(false, maxResults, firstResult);
    }

    private List<Detallecompras> findDetallecomprasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallecompras.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detallecompras findDetallecompras(DetallecomprasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallecompras.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallecomprasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallecompras> rt = cq.from(Detallecompras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
