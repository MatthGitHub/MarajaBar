/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Proveedores;
import entidades.Detallecompras;
import entidades.Mercaderia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrador
 */
public class MercaderiaJpaController implements Serializable {

    public MercaderiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mercaderia mercaderia) {
        if (mercaderia.getDetallecomprasList() == null) {
            mercaderia.setDetallecomprasList(new ArrayList<Detallecompras>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores fkProveedor = mercaderia.getFkProveedor();
            if (fkProveedor != null) {
                fkProveedor = em.getReference(fkProveedor.getClass(), fkProveedor.getIdProveedor());
                mercaderia.setFkProveedor(fkProveedor);
            }
            List<Detallecompras> attachedDetallecomprasList = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListDetallecomprasToAttach : mercaderia.getDetallecomprasList()) {
                detallecomprasListDetallecomprasToAttach = em.getReference(detallecomprasListDetallecomprasToAttach.getClass(), detallecomprasListDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasList.add(detallecomprasListDetallecomprasToAttach);
            }
            mercaderia.setDetallecomprasList(attachedDetallecomprasList);
            em.persist(mercaderia);
            if (fkProveedor != null) {
                fkProveedor.getMercaderiaList().add(mercaderia);
                fkProveedor = em.merge(fkProveedor);
            }
            for (Detallecompras detallecomprasListDetallecompras : mercaderia.getDetallecomprasList()) {
                Mercaderia oldMercaderiaOfDetallecomprasListDetallecompras = detallecomprasListDetallecompras.getMercaderia();
                detallecomprasListDetallecompras.setMercaderia(mercaderia);
                detallecomprasListDetallecompras = em.merge(detallecomprasListDetallecompras);
                if (oldMercaderiaOfDetallecomprasListDetallecompras != null) {
                    oldMercaderiaOfDetallecomprasListDetallecompras.getDetallecomprasList().remove(detallecomprasListDetallecompras);
                    oldMercaderiaOfDetallecomprasListDetallecompras = em.merge(oldMercaderiaOfDetallecomprasListDetallecompras);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mercaderia mercaderia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercaderia persistentMercaderia = em.find(Mercaderia.class, mercaderia.getIdMerca());
            Proveedores fkProveedorOld = persistentMercaderia.getFkProveedor();
            Proveedores fkProveedorNew = mercaderia.getFkProveedor();
            List<Detallecompras> detallecomprasListOld = persistentMercaderia.getDetallecomprasList();
            List<Detallecompras> detallecomprasListNew = mercaderia.getDetallecomprasList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompras detallecomprasListOldDetallecompras : detallecomprasListOld) {
                if (!detallecomprasListNew.contains(detallecomprasListOldDetallecompras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompras " + detallecomprasListOldDetallecompras + " since its mercaderia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkProveedorNew != null) {
                fkProveedorNew = em.getReference(fkProveedorNew.getClass(), fkProveedorNew.getIdProveedor());
                mercaderia.setFkProveedor(fkProveedorNew);
            }
            List<Detallecompras> attachedDetallecomprasListNew = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListNewDetallecomprasToAttach : detallecomprasListNew) {
                detallecomprasListNewDetallecomprasToAttach = em.getReference(detallecomprasListNewDetallecomprasToAttach.getClass(), detallecomprasListNewDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasListNew.add(detallecomprasListNewDetallecomprasToAttach);
            }
            detallecomprasListNew = attachedDetallecomprasListNew;
            mercaderia.setDetallecomprasList(detallecomprasListNew);
            mercaderia = em.merge(mercaderia);
            if (fkProveedorOld != null && !fkProveedorOld.equals(fkProveedorNew)) {
                fkProveedorOld.getMercaderiaList().remove(mercaderia);
                fkProveedorOld = em.merge(fkProveedorOld);
            }
            if (fkProveedorNew != null && !fkProveedorNew.equals(fkProveedorOld)) {
                fkProveedorNew.getMercaderiaList().add(mercaderia);
                fkProveedorNew = em.merge(fkProveedorNew);
            }
            for (Detallecompras detallecomprasListNewDetallecompras : detallecomprasListNew) {
                if (!detallecomprasListOld.contains(detallecomprasListNewDetallecompras)) {
                    Mercaderia oldMercaderiaOfDetallecomprasListNewDetallecompras = detallecomprasListNewDetallecompras.getMercaderia();
                    detallecomprasListNewDetallecompras.setMercaderia(mercaderia);
                    detallecomprasListNewDetallecompras = em.merge(detallecomprasListNewDetallecompras);
                    if (oldMercaderiaOfDetallecomprasListNewDetallecompras != null && !oldMercaderiaOfDetallecomprasListNewDetallecompras.equals(mercaderia)) {
                        oldMercaderiaOfDetallecomprasListNewDetallecompras.getDetallecomprasList().remove(detallecomprasListNewDetallecompras);
                        oldMercaderiaOfDetallecomprasListNewDetallecompras = em.merge(oldMercaderiaOfDetallecomprasListNewDetallecompras);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mercaderia.getIdMerca();
                if (findMercaderia(id) == null) {
                    throw new NonexistentEntityException("The mercaderia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercaderia mercaderia;
            try {
                mercaderia = em.getReference(Mercaderia.class, id);
                mercaderia.getIdMerca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mercaderia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallecompras> detallecomprasListOrphanCheck = mercaderia.getDetallecomprasList();
            for (Detallecompras detallecomprasListOrphanCheckDetallecompras : detallecomprasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mercaderia (" + mercaderia + ") cannot be destroyed since the Detallecompras " + detallecomprasListOrphanCheckDetallecompras + " in its detallecomprasList field has a non-nullable mercaderia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedores fkProveedor = mercaderia.getFkProveedor();
            if (fkProveedor != null) {
                fkProveedor.getMercaderiaList().remove(mercaderia);
                fkProveedor = em.merge(fkProveedor);
            }
            em.remove(mercaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mercaderia> findMercaderiaEntities() {
        return findMercaderiaEntities(true, -1, -1);
    }

    public List<Mercaderia> findMercaderiaEntities(int maxResults, int firstResult) {
        return findMercaderiaEntities(false, maxResults, firstResult);
    }

    private List<Mercaderia> findMercaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mercaderia.class));
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

    public Mercaderia findMercaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mercaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMercaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mercaderia> rt = cq.from(Mercaderia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
