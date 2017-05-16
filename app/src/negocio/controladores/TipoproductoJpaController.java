/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.entidades.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Tipoproducto;

/**
 *
 * @author matth
 */
public class TipoproductoJpaController implements Serializable {

    public TipoproductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoproducto tipoproducto) {
        if (tipoproducto.getProductosList() == null) {
            tipoproducto.setProductosList(new ArrayList<Productos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Productos> attachedProductosList = new ArrayList<Productos>();
            for (Productos productosListProductosToAttach : tipoproducto.getProductosList()) {
                productosListProductosToAttach = em.getReference(productosListProductosToAttach.getClass(), productosListProductosToAttach.getIdProducto());
                attachedProductosList.add(productosListProductosToAttach);
            }
            tipoproducto.setProductosList(attachedProductosList);
            em.persist(tipoproducto);
            for (Productos productosListProductos : tipoproducto.getProductosList()) {
                Tipoproducto oldFkTipoOfProductosListProductos = productosListProductos.getFkTipo();
                productosListProductos.setFkTipo(tipoproducto);
                productosListProductos = em.merge(productosListProductos);
                if (oldFkTipoOfProductosListProductos != null) {
                    oldFkTipoOfProductosListProductos.getProductosList().remove(productosListProductos);
                    oldFkTipoOfProductosListProductos = em.merge(oldFkTipoOfProductosListProductos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoproducto tipoproducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto persistentTipoproducto = em.find(Tipoproducto.class, tipoproducto.getIdTipoProducto());
            List<Productos> productosListOld = persistentTipoproducto.getProductosList();
            List<Productos> productosListNew = tipoproducto.getProductosList();
            List<String> illegalOrphanMessages = null;
            for (Productos productosListOldProductos : productosListOld) {
                if (!productosListNew.contains(productosListOldProductos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productos " + productosListOldProductos + " since its fkTipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Productos> attachedProductosListNew = new ArrayList<Productos>();
            for (Productos productosListNewProductosToAttach : productosListNew) {
                productosListNewProductosToAttach = em.getReference(productosListNewProductosToAttach.getClass(), productosListNewProductosToAttach.getIdProducto());
                attachedProductosListNew.add(productosListNewProductosToAttach);
            }
            productosListNew = attachedProductosListNew;
            tipoproducto.setProductosList(productosListNew);
            tipoproducto = em.merge(tipoproducto);
            for (Productos productosListNewProductos : productosListNew) {
                if (!productosListOld.contains(productosListNewProductos)) {
                    Tipoproducto oldFkTipoOfProductosListNewProductos = productosListNewProductos.getFkTipo();
                    productosListNewProductos.setFkTipo(tipoproducto);
                    productosListNewProductos = em.merge(productosListNewProductos);
                    if (oldFkTipoOfProductosListNewProductos != null && !oldFkTipoOfProductosListNewProductos.equals(tipoproducto)) {
                        oldFkTipoOfProductosListNewProductos.getProductosList().remove(productosListNewProductos);
                        oldFkTipoOfProductosListNewProductos = em.merge(oldFkTipoOfProductosListNewProductos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoproducto.getIdTipoProducto();
                if (findTipoproducto(id) == null) {
                    throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.");
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
            Tipoproducto tipoproducto;
            try {
                tipoproducto = em.getReference(Tipoproducto.class, id);
                tipoproducto.getIdTipoProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoproducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Productos> productosListOrphanCheck = tipoproducto.getProductosList();
            for (Productos productosListOrphanCheckProductos : productosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoproducto (" + tipoproducto + ") cannot be destroyed since the Productos " + productosListOrphanCheckProductos + " in its productosList field has a non-nullable fkTipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoproducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoproducto> findTipoproductoEntities() {
        return findTipoproductoEntities(true, -1, -1);
    }

    public List<Tipoproducto> findTipoproductoEntities(int maxResults, int firstResult) {
        return findTipoproductoEntities(false, maxResults, firstResult);
    }

    private List<Tipoproducto> findTipoproductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoproducto.class));
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

    public Tipoproducto findTipoproducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoproducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoproductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoproducto> rt = cq.from(Tipoproducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
