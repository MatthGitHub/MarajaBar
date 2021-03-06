/*
 * *******************************************  * 
 * Desarrollado por Matias Benditti.
 * *******************************************  * 
 */
package negocio.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.entidades.Detallecompras;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Compras;

/**
 *
 * @author matth
 */
public class ComprasJpaController implements Serializable {

    public ComprasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compras compras) {
        if (compras.getDetallecomprasList() == null) {
            compras.setDetallecomprasList(new ArrayList<Detallecompras>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallecompras> attachedDetallecomprasList = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListDetallecomprasToAttach : compras.getDetallecomprasList()) {
                detallecomprasListDetallecomprasToAttach = em.getReference(detallecomprasListDetallecomprasToAttach.getClass(), detallecomprasListDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasList.add(detallecomprasListDetallecomprasToAttach);
            }
            compras.setDetallecomprasList(attachedDetallecomprasList);
            em.persist(compras);
            for (Detallecompras detallecomprasListDetallecompras : compras.getDetallecomprasList()) {
                Compras oldComprasOfDetallecomprasListDetallecompras = detallecomprasListDetallecompras.getCompras();
                detallecomprasListDetallecompras.setCompras(compras);
                detallecomprasListDetallecompras = em.merge(detallecomprasListDetallecompras);
                if (oldComprasOfDetallecomprasListDetallecompras != null) {
                    oldComprasOfDetallecomprasListDetallecompras.getDetallecomprasList().remove(detallecomprasListDetallecompras);
                    oldComprasOfDetallecomprasListDetallecompras = em.merge(oldComprasOfDetallecomprasListDetallecompras);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compras compras) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compras persistentCompras = em.find(Compras.class, compras.getIdCompras());
            List<Detallecompras> detallecomprasListOld = persistentCompras.getDetallecomprasList();
            List<Detallecompras> detallecomprasListNew = compras.getDetallecomprasList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompras detallecomprasListOldDetallecompras : detallecomprasListOld) {
                if (!detallecomprasListNew.contains(detallecomprasListOldDetallecompras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompras " + detallecomprasListOldDetallecompras + " since its compras field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detallecompras> attachedDetallecomprasListNew = new ArrayList<Detallecompras>();
            for (Detallecompras detallecomprasListNewDetallecomprasToAttach : detallecomprasListNew) {
                detallecomprasListNewDetallecomprasToAttach = em.getReference(detallecomprasListNewDetallecomprasToAttach.getClass(), detallecomprasListNewDetallecomprasToAttach.getDetallecomprasPK());
                attachedDetallecomprasListNew.add(detallecomprasListNewDetallecomprasToAttach);
            }
            detallecomprasListNew = attachedDetallecomprasListNew;
            compras.setDetallecomprasList(detallecomprasListNew);
            compras = em.merge(compras);
            for (Detallecompras detallecomprasListNewDetallecompras : detallecomprasListNew) {
                if (!detallecomprasListOld.contains(detallecomprasListNewDetallecompras)) {
                    Compras oldComprasOfDetallecomprasListNewDetallecompras = detallecomprasListNewDetallecompras.getCompras();
                    detallecomprasListNewDetallecompras.setCompras(compras);
                    detallecomprasListNewDetallecompras = em.merge(detallecomprasListNewDetallecompras);
                    if (oldComprasOfDetallecomprasListNewDetallecompras != null && !oldComprasOfDetallecomprasListNewDetallecompras.equals(compras)) {
                        oldComprasOfDetallecomprasListNewDetallecompras.getDetallecomprasList().remove(detallecomprasListNewDetallecompras);
                        oldComprasOfDetallecomprasListNewDetallecompras = em.merge(oldComprasOfDetallecomprasListNewDetallecompras);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compras.getIdCompras();
                if (findCompras(id) == null) {
                    throw new NonexistentEntityException("The compras with id " + id + " no longer exists.");
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
            Compras compras;
            try {
                compras = em.getReference(Compras.class, id);
                compras.getIdCompras();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compras with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallecompras> detallecomprasListOrphanCheck = compras.getDetallecomprasList();
            for (Detallecompras detallecomprasListOrphanCheckDetallecompras : detallecomprasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compras (" + compras + ") cannot be destroyed since the Detallecompras " + detallecomprasListOrphanCheckDetallecompras + " in its detallecomprasList field has a non-nullable compras field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(compras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compras> findComprasEntities() {
        return findComprasEntities(true, -1, -1);
    }

    public List<Compras> findComprasEntities(int maxResults, int firstResult) {
        return findComprasEntities(false, maxResults, firstResult);
    }

    private List<Compras> findComprasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compras.class));
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

    public Compras findCompras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compras.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compras> rt = cq.from(Compras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
