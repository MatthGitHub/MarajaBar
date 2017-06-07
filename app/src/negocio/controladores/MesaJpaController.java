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
import negocio.entidades.Sectores;
import negocio.entidades.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Mesa;

/**
 *
 * @author Matth
 */
public class MesaJpaController implements Serializable {

    public MesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mesa mesa) {
        if (mesa.getVentasList() == null) {
            mesa.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sectores fkSector = mesa.getFkSector();
            if (fkSector != null) {
                fkSector = em.getReference(fkSector.getClass(), fkSector.getIdSector());
                mesa.setFkSector(fkSector);
            }
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : mesa.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getIdVenta());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            mesa.setVentasList(attachedVentasList);
            em.persist(mesa);
            if (fkSector != null) {
                fkSector.getMesaList().add(mesa);
                fkSector = em.merge(fkSector);
            }
            for (Ventas ventasListVentas : mesa.getVentasList()) {
                Mesa oldFkMesaOfVentasListVentas = ventasListVentas.getFkMesa();
                ventasListVentas.setFkMesa(mesa);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldFkMesaOfVentasListVentas != null) {
                    oldFkMesaOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldFkMesaOfVentasListVentas = em.merge(oldFkMesaOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mesa mesa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mesa persistentMesa = em.find(Mesa.class, mesa.getIdMesa());
            Sectores fkSectorOld = persistentMesa.getFkSector();
            Sectores fkSectorNew = mesa.getFkSector();
            List<Ventas> ventasListOld = persistentMesa.getVentasList();
            List<Ventas> ventasListNew = mesa.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its fkMesa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkSectorNew != null) {
                fkSectorNew = em.getReference(fkSectorNew.getClass(), fkSectorNew.getIdSector());
                mesa.setFkSector(fkSectorNew);
            }
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getIdVenta());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            mesa.setVentasList(ventasListNew);
            mesa = em.merge(mesa);
            if (fkSectorOld != null && !fkSectorOld.equals(fkSectorNew)) {
                fkSectorOld.getMesaList().remove(mesa);
                fkSectorOld = em.merge(fkSectorOld);
            }
            if (fkSectorNew != null && !fkSectorNew.equals(fkSectorOld)) {
                fkSectorNew.getMesaList().add(mesa);
                fkSectorNew = em.merge(fkSectorNew);
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Mesa oldFkMesaOfVentasListNewVentas = ventasListNewVentas.getFkMesa();
                    ventasListNewVentas.setFkMesa(mesa);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldFkMesaOfVentasListNewVentas != null && !oldFkMesaOfVentasListNewVentas.equals(mesa)) {
                        oldFkMesaOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldFkMesaOfVentasListNewVentas = em.merge(oldFkMesaOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mesa.getIdMesa();
                if (findMesa(id) == null) {
                    throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.");
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
            Mesa mesa;
            try {
                mesa = em.getReference(Mesa.class, id);
                mesa.getIdMesa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mesa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ventas> ventasListOrphanCheck = mesa.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mesa (" + mesa + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable fkMesa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sectores fkSector = mesa.getFkSector();
            if (fkSector != null) {
                fkSector.getMesaList().remove(mesa);
                fkSector = em.merge(fkSector);
            }
            em.remove(mesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mesa> findMesaEntities() {
        return findMesaEntities(true, -1, -1);
    }

    public List<Mesa> findMesaEntities(int maxResults, int firstResult) {
        return findMesaEntities(false, maxResults, firstResult);
    }

    private List<Mesa> findMesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mesa.class));
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

    public Mesa findMesa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getMesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mesa> rt = cq.from(Mesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
