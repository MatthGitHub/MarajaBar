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
import negocio.entidades.Tipoproducto;
import negocio.entidades.Detallecompras;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Detalleventas;
import negocio.entidades.Productos;

/**
 *
 * @author Matth
 */
public class ProductosJpaController implements Serializable {

    public ProductosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) {
        if (productos.getDetallecomprasList() == null) {
            productos.setDetallecomprasList(new ArrayList<Detallecompras>());
        }
        if (productos.getDetalleventasList() == null) {
            productos.setDetalleventasList(new ArrayList<Detalleventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproducto fkTipo = productos.getFkTipo();
            if (fkTipo != null) {
                fkTipo = em.getReference(fkTipo.getClass(), fkTipo.getIdTipoProducto());
                productos.setFkTipo(fkTipo);
            }
            List<Detallecompras> attachedDetallecomprasList = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListDetallecomprasToAttach : productos.getDetallecomprasList()) {
                detallecomprasListDetallecomprasToAttach = em.getReference(detallecomprasListDetallecomprasToAttach.getClass(), detallecomprasListDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasList.add(detallecomprasListDetallecomprasToAttach);
            }
            productos.setDetallecomprasList(attachedDetallecomprasList);
            List<Detalleventas> attachedDetalleventasList = new ArrayList<Detalleventas>();
            for (Detalleventas detalleventasListDetalleventasToAttach : productos.getDetalleventasList()) {
                detalleventasListDetalleventasToAttach = em.getReference(detalleventasListDetalleventasToAttach.getClass(), detalleventasListDetalleventasToAttach.getDetalleventasPK());
                attachedDetalleventasList.add(detalleventasListDetalleventasToAttach);
            }
            productos.setDetalleventasList(attachedDetalleventasList);
            em.persist(productos);
            if (fkTipo != null) {
                fkTipo.getProductosList().add(productos);
                fkTipo = em.merge(fkTipo);
            }
            for (Detallecompras detallecomprasListDetallecompras : productos.getDetallecomprasList()) {
                Productos oldProductosOfDetallecomprasListDetallecompras = detallecomprasListDetallecompras.getProductos();
                detallecomprasListDetallecompras.setProductos(productos);
                detallecomprasListDetallecompras = em.merge(detallecomprasListDetallecompras);
                if (oldProductosOfDetallecomprasListDetallecompras != null) {
                    oldProductosOfDetallecomprasListDetallecompras.getDetallecomprasList().remove(detallecomprasListDetallecompras);
                    oldProductosOfDetallecomprasListDetallecompras = em.merge(oldProductosOfDetallecomprasListDetallecompras);
                }
            }
            for (Detalleventas detalleventasListDetalleventas : productos.getDetalleventasList()) {
                Productos oldProductosOfDetalleventasListDetalleventas = detalleventasListDetalleventas.getProductos();
                detalleventasListDetalleventas.setProductos(productos);
                detalleventasListDetalleventas = em.merge(detalleventasListDetalleventas);
                if (oldProductosOfDetalleventasListDetalleventas != null) {
                    oldProductosOfDetalleventasListDetalleventas.getDetalleventasList().remove(detalleventasListDetalleventas);
                    oldProductosOfDetalleventasListDetalleventas = em.merge(oldProductosOfDetalleventasListDetalleventas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos persistentProductos = em.find(Productos.class, productos.getIdProducto());
            Tipoproducto fkTipoOld = persistentProductos.getFkTipo();
            Tipoproducto fkTipoNew = productos.getFkTipo();
            List<Detallecompras> detallecomprasListOld = persistentProductos.getDetallecomprasList();
            List<Detallecompras> detallecomprasListNew = productos.getDetallecomprasList();
            List<Detalleventas> detalleventasListOld = persistentProductos.getDetalleventasList();
            List<Detalleventas> detalleventasListNew = productos.getDetalleventasList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompras detallecomprasListOldDetallecompras : detallecomprasListOld) {
                if (!detallecomprasListNew.contains(detallecomprasListOldDetallecompras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompras " + detallecomprasListOldDetallecompras + " since its productos field is not nullable.");
                }
            }
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
            if (fkTipoNew != null) {
                fkTipoNew = em.getReference(fkTipoNew.getClass(), fkTipoNew.getIdTipoProducto());
                productos.setFkTipo(fkTipoNew);
            }
            List<Detallecompras> attachedDetallecomprasListNew = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListNewDetallecomprasToAttach : detallecomprasListNew) {
                detallecomprasListNewDetallecomprasToAttach = em.getReference(detallecomprasListNewDetallecomprasToAttach.getClass(), detallecomprasListNewDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasListNew.add(detallecomprasListNewDetallecomprasToAttach);
            }
            detallecomprasListNew = attachedDetallecomprasListNew;
            productos.setDetallecomprasList(detallecomprasListNew);
            List<Detalleventas> attachedDetalleventasListNew = new ArrayList<Detalleventas>();
            for (Detalleventas detalleventasListNewDetalleventasToAttach : detalleventasListNew) {
                detalleventasListNewDetalleventasToAttach = em.getReference(detalleventasListNewDetalleventasToAttach.getClass(), detalleventasListNewDetalleventasToAttach.getDetalleventasPK());
                attachedDetalleventasListNew.add(detalleventasListNewDetalleventasToAttach);
            }
            detalleventasListNew = attachedDetalleventasListNew;
            productos.setDetalleventasList(detalleventasListNew);
            productos = em.merge(productos);
            if (fkTipoOld != null && !fkTipoOld.equals(fkTipoNew)) {
                fkTipoOld.getProductosList().remove(productos);
                fkTipoOld = em.merge(fkTipoOld);
            }
            if (fkTipoNew != null && !fkTipoNew.equals(fkTipoOld)) {
                fkTipoNew.getProductosList().add(productos);
                fkTipoNew = em.merge(fkTipoNew);
            }
            for (Detallecompras detallecomprasListNewDetallecompras : detallecomprasListNew) {
                if (!detallecomprasListOld.contains(detallecomprasListNewDetallecompras)) {
                    Productos oldProductosOfDetallecomprasListNewDetallecompras = detallecomprasListNewDetallecompras.getProductos();
                    detallecomprasListNewDetallecompras.setProductos(productos);
                    detallecomprasListNewDetallecompras = em.merge(detallecomprasListNewDetallecompras);
                    if (oldProductosOfDetallecomprasListNewDetallecompras != null && !oldProductosOfDetallecomprasListNewDetallecompras.equals(productos)) {
                        oldProductosOfDetallecomprasListNewDetallecompras.getDetallecomprasList().remove(detallecomprasListNewDetallecompras);
                        oldProductosOfDetallecomprasListNewDetallecompras = em.merge(oldProductosOfDetallecomprasListNewDetallecompras);
                    }
                }
            }
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
            em.getTransaction().commit();
        } catch (Exception ex) {
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallecompras> detallecomprasListOrphanCheck = productos.getDetallecomprasList();
            for (Detallecompras detallecomprasListOrphanCheckDetallecompras : detallecomprasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productos (" + productos + ") cannot be destroyed since the Detallecompras " + detallecomprasListOrphanCheckDetallecompras + " in its detallecomprasList field has a non-nullable productos field.");
            }
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
            Tipoproducto fkTipo = productos.getFkTipo();
            if (fkTipo != null) {
                fkTipo.getProductosList().remove(productos);
                fkTipo = em.merge(fkTipo);
            }
            em.remove(productos);
            em.getTransaction().commit();
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
