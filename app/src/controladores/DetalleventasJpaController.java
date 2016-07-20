/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Detalleventas;
import entidades.DetalleventasPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Productos;
import entidades.Ventas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrador
 */
public class DetalleventasJpaController implements Serializable {

    public DetalleventasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleventas detalleventas) throws PreexistingEntityException, Exception {
        if (detalleventas.getDetalleventasPK() == null) {
            detalleventas.setDetalleventasPK(new DetalleventasPK());
        }
        detalleventas.getDetalleventasPK().setFkVenta(detalleventas.getVentas().getIdVenta());
        detalleventas.getDetalleventasPK().setFkProducto(detalleventas.getProductos().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos = detalleventas.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdProducto());
                detalleventas.setProductos(productos);
            }
            Ventas ventas = detalleventas.getVentas();
            if (ventas != null) {
                ventas = em.getReference(ventas.getClass(), ventas.getIdVenta());
                detalleventas.setVentas(ventas);
            }
            em.persist(detalleventas);
            if (productos != null) {
                productos.getDetalleventasList().add(detalleventas);
                productos = em.merge(productos);
            }
            if (ventas != null) {
                ventas.getDetalleventasList().add(detalleventas);
                ventas = em.merge(ventas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleventas(detalleventas.getDetalleventasPK()) != null) {
                throw new PreexistingEntityException("Detalleventas " + detalleventas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleventas detalleventas) throws NonexistentEntityException, Exception {
        detalleventas.getDetalleventasPK().setFkVenta(detalleventas.getVentas().getIdVenta());
        detalleventas.getDetalleventasPK().setFkProducto(detalleventas.getProductos().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventas persistentDetalleventas = em.find(Detalleventas.class, detalleventas.getDetalleventasPK());
            Productos productosOld = persistentDetalleventas.getProductos();
            Productos productosNew = detalleventas.getProductos();
            Ventas ventasOld = persistentDetalleventas.getVentas();
            Ventas ventasNew = detalleventas.getVentas();
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdProducto());
                detalleventas.setProductos(productosNew);
            }
            if (ventasNew != null) {
                ventasNew = em.getReference(ventasNew.getClass(), ventasNew.getIdVenta());
                detalleventas.setVentas(ventasNew);
            }
            detalleventas = em.merge(detalleventas);
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetalleventasList().remove(detalleventas);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetalleventasList().add(detalleventas);
                productosNew = em.merge(productosNew);
            }
            if (ventasOld != null && !ventasOld.equals(ventasNew)) {
                ventasOld.getDetalleventasList().remove(detalleventas);
                ventasOld = em.merge(ventasOld);
            }
            if (ventasNew != null && !ventasNew.equals(ventasOld)) {
                ventasNew.getDetalleventasList().add(detalleventas);
                ventasNew = em.merge(ventasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleventasPK id = detalleventas.getDetalleventasPK();
                if (findDetalleventas(id) == null) {
                    throw new NonexistentEntityException("The detalleventas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleventasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventas detalleventas;
            try {
                detalleventas = em.getReference(Detalleventas.class, id);
                detalleventas.getDetalleventasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleventas with id " + id + " no longer exists.", enfe);
            }
            Productos productos = detalleventas.getProductos();
            if (productos != null) {
                productos.getDetalleventasList().remove(detalleventas);
                productos = em.merge(productos);
            }
            Ventas ventas = detalleventas.getVentas();
            if (ventas != null) {
                ventas.getDetalleventasList().remove(detalleventas);
                ventas = em.merge(ventas);
            }
            em.remove(detalleventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleventas> findDetalleventasEntities() {
        return findDetalleventasEntities(true, -1, -1);
    }

    public List<Detalleventas> findDetalleventasEntities(int maxResults, int firstResult) {
        return findDetalleventasEntities(false, maxResults, firstResult);
    }

    private List<Detalleventas> findDetalleventasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleventas.class));
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

    public Detalleventas findDetalleventas(DetalleventasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleventasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleventas> rt = cq.from(Detalleventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
