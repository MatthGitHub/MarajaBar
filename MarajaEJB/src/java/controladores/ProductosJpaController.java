/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Detalleventas;
import entidades.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Administrador
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) throws RollbackFailureException, Exception {
        if (productos.getDetalleventasList() == null) {
            productos.setDetalleventasList(new ArrayList<Detalleventas>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Detalleventas> attachedDetalleventasList = new ArrayList<Detalleventas>();
            for (Detalleventas detalleventasListDetalleventasToAttach : productos.getDetalleventasList()) {
                detalleventasListDetalleventasToAttach = em.getReference(detalleventasListDetalleventasToAttach.getClass(), detalleventasListDetalleventasToAttach.getDetalleventasPK());
                attachedDetalleventasList.add(detalleventasListDetalleventasToAttach);
            }
            productos.setDetalleventasList(attachedDetalleventasList);
            em.persist(productos);
            for (Detalleventas detalleventasListDetalleventas : productos.getDetalleventasList()) {
                Productos oldProductosOfDetalleventasListDetalleventas = detalleventasListDetalleventas.getProductos();
                detalleventasListDetalleventas.setProductos(productos);
                detalleventasListDetalleventas = em.merge(detalleventasListDetalleventas);
                if (oldProductosOfDetalleventasListDetalleventas != null) {
                    oldProductosOfDetalleventasListDetalleventas.getDetalleventasList().remove(detalleventasListDetalleventas);
                    oldProductosOfDetalleventasListDetalleventas = em.merge(oldProductosOfDetalleventasListDetalleventas);
                }
            }
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

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productos persistentProductos = em.find(Productos.class, productos.getIdProducto());
            List<Detalleventas> detalleventasListOld = persistentProductos.getDetalleventasList();
            List<Detalleventas> detalleventasListNew = productos.getDetalleventasList();
            List<String> illegalOrphanMessages = null;
            for (Detalleventas detalleventasListOldDetalleventas : detalleventasListOld) {
                if (!detalleventasListNew.contains(detalleventasListOldDetalleventas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventas " + detalleventasListOldDetalleventas + " since its productos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleventas> attachedDetalleventasListNew = new ArrayList<Detalleventas>();
            for (Detalleventas detalleventasListNewDetalleventasToAttach : detalleventasListNew) {
                detalleventasListNewDetalleventasToAttach = em.getReference(detalleventasListNewDetalleventasToAttach.getClass(), detalleventasListNewDetalleventasToAttach.getDetalleventasPK());
                attachedDetalleventasListNew.add(detalleventasListNewDetalleventasToAttach);
            }
            detalleventasListNew = attachedDetalleventasListNew;
            productos.setDetalleventasList(detalleventasListNew);
            productos = em.merge(productos);
            for (Detalleventas detalleventasListNewDetalleventas : detalleventasListNew) {
                if (!detalleventasListOld.contains(detalleventasListNewDetalleventas)) {
                    Productos oldProductosOfDetalleventasListNewDetalleventas = detalleventasListNewDetalleventas.getProductos();
                    detalleventasListNewDetalleventas.setProductos(productos);
                    detalleventasListNewDetalleventas = em.merge(detalleventasListNewDetalleventas);
                    if (oldProductosOfDetalleventasListNewDetalleventas != null && !oldProductosOfDetalleventasListNewDetalleventas.equals(productos)) {
                        oldProductosOfDetalleventasListNewDetalleventas.getDetalleventasList().remove(detalleventasListNewDetalleventas);
                        oldProductosOfDetalleventasListNewDetalleventas = em.merge(oldProductosOfDetalleventasListNewDetalleventas);
                    }
                }
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
                Integer id = productos.getIdProducto();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleventas> detalleventasListOrphanCheck = productos.getDetalleventasList();
            for (Detalleventas detalleventasListOrphanCheckDetalleventas : detalleventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detalleventas " + detalleventasListOrphanCheckDetalleventas + " in its detalleventasList field has a non-nullable productos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productos);
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

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
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

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
