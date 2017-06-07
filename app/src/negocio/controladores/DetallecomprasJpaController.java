/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.controladores.exceptions.PreexistingEntityException;
import negocio.entidades.Compras;
import negocio.entidades.Detallecompras;
import negocio.entidades.DetallecomprasPK;
import negocio.entidades.Productos;

/**
 *
 * @author Matth
 */
public class DetallecomprasJpaController implements Serializable {

    public DetallecomprasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallecompras detallecompras) throws PreexistingEntityException, Exception {
        if (detallecompras.getDetallecomprasPK() == null) {
            detallecompras.setDetallecomprasPK(new DetallecomprasPK());
        }
        detallecompras.getDetallecomprasPK().setFkMerca(detallecompras.getProductos().getIdProducto());
        detallecompras.getDetallecomprasPK().setFkCompras(detallecompras.getCompras().getIdCompras());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compras compras = detallecompras.getCompras();
            if (compras != null) {
                compras = em.getReference(compras.getClass(), compras.getIdCompras());
                detallecompras.setCompras(compras);
            }
            Productos productos = detallecompras.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdProducto());
                detallecompras.setProductos(productos);
            }
            em.persist(detallecompras);
            if (compras != null) {
                compras.getDetallecomprasList().add(detallecompras);
                compras = em.merge(compras);
            }
            if (productos != null) {
                productos.getDetallecomprasList().add(detallecompras);
                productos = em.merge(productos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
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

    public void edit(Detallecompras detallecompras) throws NonexistentEntityException, Exception {
        detallecompras.getDetallecomprasPK().setFkMerca(detallecompras.getProductos().getIdProducto());
        detallecompras.getDetallecomprasPK().setFkCompras(detallecompras.getCompras().getIdCompras());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompras persistentDetallecompras = em.find(Detallecompras.class, detallecompras.getDetallecomprasPK());
            Compras comprasOld = persistentDetallecompras.getCompras();
            Compras comprasNew = detallecompras.getCompras();
            Productos productosOld = persistentDetallecompras.getProductos();
            Productos productosNew = detallecompras.getProductos();
            if (comprasNew != null) {
                comprasNew = em.getReference(comprasNew.getClass(), comprasNew.getIdCompras());
                detallecompras.setCompras(comprasNew);
            }
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdProducto());
                detallecompras.setProductos(productosNew);
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
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetallecomprasList().remove(detallecompras);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetallecomprasList().add(detallecompras);
                productosNew = em.merge(productosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
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

    public void destroy(DetallecomprasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            Productos productos = detallecompras.getProductos();
            if (productos != null) {
                productos.getDetallecomprasList().remove(detallecompras);
                productos = em.merge(productos);
            }
            em.remove(detallecompras);
            em.getTransaction().commit();
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
