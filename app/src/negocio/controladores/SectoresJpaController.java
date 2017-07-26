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
import negocio.entidades.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Sectores;

/**
 *
 * @author matth
 */
public class SectoresJpaController implements Serializable {

    public SectoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sectores sectores) {
        if (sectores.getMesaList() == null) {
            sectores.setMesaList(new ArrayList<Mesa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mesa> attachedMesaList = new ArrayList<Mesa>();
            for (Mesa mesaListMesaToAttach : sectores.getMesaList()) {
                mesaListMesaToAttach = em.getReference(mesaListMesaToAttach.getClass(), mesaListMesaToAttach.getIdMesa());
                attachedMesaList.add(mesaListMesaToAttach);
            }
            sectores.setMesaList(attachedMesaList);
            em.persist(sectores);
            for (Mesa mesaListMesa : sectores.getMesaList()) {
                Sectores oldFkSectorOfMesaListMesa = mesaListMesa.getFkSector();
                mesaListMesa.setFkSector(sectores);
                mesaListMesa = em.merge(mesaListMesa);
                if (oldFkSectorOfMesaListMesa != null) {
                    oldFkSectorOfMesaListMesa.getMesaList().remove(mesaListMesa);
                    oldFkSectorOfMesaListMesa = em.merge(oldFkSectorOfMesaListMesa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sectores sectores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sectores persistentSectores = em.find(Sectores.class, sectores.getIdSector());
            List<Mesa> mesaListOld = persistentSectores.getMesaList();
            List<Mesa> mesaListNew = sectores.getMesaList();
            List<String> illegalOrphanMessages = null;
            for (Mesa mesaListOldMesa : mesaListOld) {
                if (!mesaListNew.contains(mesaListOldMesa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mesa " + mesaListOldMesa + " since its fkSector field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Mesa> attachedMesaListNew = new ArrayList<Mesa>();
            for (Mesa mesaListNewMesaToAttach : mesaListNew) {
                mesaListNewMesaToAttach = em.getReference(mesaListNewMesaToAttach.getClass(), mesaListNewMesaToAttach.getIdMesa());
                attachedMesaListNew.add(mesaListNewMesaToAttach);
            }
            mesaListNew = attachedMesaListNew;
            sectores.setMesaList(mesaListNew);
            sectores = em.merge(sectores);
            for (Mesa mesaListNewMesa : mesaListNew) {
                if (!mesaListOld.contains(mesaListNewMesa)) {
                    Sectores oldFkSectorOfMesaListNewMesa = mesaListNewMesa.getFkSector();
                    mesaListNewMesa.setFkSector(sectores);
                    mesaListNewMesa = em.merge(mesaListNewMesa);
                    if (oldFkSectorOfMesaListNewMesa != null && !oldFkSectorOfMesaListNewMesa.equals(sectores)) {
                        oldFkSectorOfMesaListNewMesa.getMesaList().remove(mesaListNewMesa);
                        oldFkSectorOfMesaListNewMesa = em.merge(oldFkSectorOfMesaListNewMesa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sectores.getIdSector();
                if (findSectores(id) == null) {
                    throw new NonexistentEntityException("The sectores with id " + id + " no longer exists.");
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
            Sectores sectores;
            try {
                sectores = em.getReference(Sectores.class, id);
                sectores.getIdSector();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sectores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mesa> mesaListOrphanCheck = sectores.getMesaList();
            for (Mesa mesaListOrphanCheckMesa : mesaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sectores (" + sectores + ") cannot be destroyed since the Mesa " + mesaListOrphanCheckMesa + " in its mesaList field has a non-nullable fkSector field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sectores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sectores> findSectoresEntities() {
        return findSectoresEntities(true, -1, -1);
    }

    public List<Sectores> findSectoresEntities(int maxResults, int firstResult) {
        return findSectoresEntities(false, maxResults, firstResult);
    }

    private List<Sectores> findSectoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sectores.class));
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

    public Sectores findSectores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sectores.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sectores> rt = cq.from(Sectores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
