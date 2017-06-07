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
import negocio.entidades.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Estadosventa;

/**
 *
 * @author Matth
 */
public class EstadosventaJpaController implements Serializable {

    public EstadosventaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadosventa estadosventa) {
        if (estadosventa.getVentasList() == null) {
            estadosventa.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : estadosventa.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getIdVenta());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            estadosventa.setVentasList(attachedVentasList);
            em.persist(estadosventa);
            for (Ventas ventasListVentas : estadosventa.getVentasList()) {
                Estadosventa oldFkEstadoOfVentasListVentas = ventasListVentas.getFkEstado();
                ventasListVentas.setFkEstado(estadosventa);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldFkEstadoOfVentasListVentas != null) {
                    oldFkEstadoOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldFkEstadoOfVentasListVentas = em.merge(oldFkEstadoOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadosventa estadosventa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadosventa persistentEstadosventa = em.find(Estadosventa.class, estadosventa.getIdEstadoVenta());
            List<Ventas> ventasListOld = persistentEstadosventa.getVentasList();
            List<Ventas> ventasListNew = estadosventa.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its fkEstado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getIdVenta());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            estadosventa.setVentasList(ventasListNew);
            estadosventa = em.merge(estadosventa);
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Estadosventa oldFkEstadoOfVentasListNewVentas = ventasListNewVentas.getFkEstado();
                    ventasListNewVentas.setFkEstado(estadosventa);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldFkEstadoOfVentasListNewVentas != null && !oldFkEstadoOfVentasListNewVentas.equals(estadosventa)) {
                        oldFkEstadoOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldFkEstadoOfVentasListNewVentas = em.merge(oldFkEstadoOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadosventa.getIdEstadoVenta();
                if (findEstadosventa(id) == null) {
                    throw new NonexistentEntityException("The estadosventa with id " + id + " no longer exists.");
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
            Estadosventa estadosventa;
            try {
                estadosventa = em.getReference(Estadosventa.class, id);
                estadosventa.getIdEstadoVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadosventa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ventas> ventasListOrphanCheck = estadosventa.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadosventa (" + estadosventa + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable fkEstado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadosventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadosventa> findEstadosventaEntities() {
        return findEstadosventaEntities(true, -1, -1);
    }

    public List<Estadosventa> findEstadosventaEntities(int maxResults, int firstResult) {
        return findEstadosventaEntities(false, maxResults, firstResult);
    }

    private List<Estadosventa> findEstadosventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadosventa.class));
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

    public Estadosventa findEstadosventa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadosventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadosventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadosventa> rt = cq.from(Estadosventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
