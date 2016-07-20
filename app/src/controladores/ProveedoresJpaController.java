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
import entidades.Mercaderia;
import entidades.Proveedores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrador
 */
public class ProveedoresJpaController implements Serializable {

    public ProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedores proveedores) {
        if (proveedores.getMercaderiaList() == null) {
            proveedores.setMercaderiaList(new ArrayList<Mercaderia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mercaderia> attachedMercaderiaList = new ArrayList<Mercaderia>();
            for (Mercaderia mercaderiaListMercaderiaToAttach : proveedores.getMercaderiaList()) {
                mercaderiaListMercaderiaToAttach = em.getReference(mercaderiaListMercaderiaToAttach.getClass(), mercaderiaListMercaderiaToAttach.getIdMerca());
                attachedMercaderiaList.add(mercaderiaListMercaderiaToAttach);
            }
            proveedores.setMercaderiaList(attachedMercaderiaList);
            em.persist(proveedores);
            for (Mercaderia mercaderiaListMercaderia : proveedores.getMercaderiaList()) {
                Proveedores oldFkProveedorOfMercaderiaListMercaderia = mercaderiaListMercaderia.getFkProveedor();
                mercaderiaListMercaderia.setFkProveedor(proveedores);
                mercaderiaListMercaderia = em.merge(mercaderiaListMercaderia);
                if (oldFkProveedorOfMercaderiaListMercaderia != null) {
                    oldFkProveedorOfMercaderiaListMercaderia.getMercaderiaList().remove(mercaderiaListMercaderia);
                    oldFkProveedorOfMercaderiaListMercaderia = em.merge(oldFkProveedorOfMercaderiaListMercaderia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getIdProveedor());
            List<Mercaderia> mercaderiaListOld = persistentProveedores.getMercaderiaList();
            List<Mercaderia> mercaderiaListNew = proveedores.getMercaderiaList();
            List<String> illegalOrphanMessages = null;
            for (Mercaderia mercaderiaListOldMercaderia : mercaderiaListOld) {
                if (!mercaderiaListNew.contains(mercaderiaListOldMercaderia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mercaderia " + mercaderiaListOldMercaderia + " since its fkProveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Mercaderia> attachedMercaderiaListNew = new ArrayList<Mercaderia>();
            for (Mercaderia mercaderiaListNewMercaderiaToAttach : mercaderiaListNew) {
                mercaderiaListNewMercaderiaToAttach = em.getReference(mercaderiaListNewMercaderiaToAttach.getClass(), mercaderiaListNewMercaderiaToAttach.getIdMerca());
                attachedMercaderiaListNew.add(mercaderiaListNewMercaderiaToAttach);
            }
            mercaderiaListNew = attachedMercaderiaListNew;
            proveedores.setMercaderiaList(mercaderiaListNew);
            proveedores = em.merge(proveedores);
            for (Mercaderia mercaderiaListNewMercaderia : mercaderiaListNew) {
                if (!mercaderiaListOld.contains(mercaderiaListNewMercaderia)) {
                    Proveedores oldFkProveedorOfMercaderiaListNewMercaderia = mercaderiaListNewMercaderia.getFkProveedor();
                    mercaderiaListNewMercaderia.setFkProveedor(proveedores);
                    mercaderiaListNewMercaderia = em.merge(mercaderiaListNewMercaderia);
                    if (oldFkProveedorOfMercaderiaListNewMercaderia != null && !oldFkProveedorOfMercaderiaListNewMercaderia.equals(proveedores)) {
                        oldFkProveedorOfMercaderiaListNewMercaderia.getMercaderiaList().remove(mercaderiaListNewMercaderia);
                        oldFkProveedorOfMercaderiaListNewMercaderia = em.merge(oldFkProveedorOfMercaderiaListNewMercaderia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedores.getIdProveedor();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
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
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mercaderia> mercaderiaListOrphanCheck = proveedores.getMercaderiaList();
            for (Mercaderia mercaderiaListOrphanCheckMercaderia : mercaderiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the Mercaderia " + mercaderiaListOrphanCheckMercaderia + " in its mercaderiaList field has a non-nullable fkProveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
