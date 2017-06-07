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
import negocio.entidades.Roles;
import negocio.entidades.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.controladores.exceptions.IllegalOrphanException;
import negocio.controladores.exceptions.NonexistentEntityException;
import negocio.entidades.Usuarios;

/**
 *
 * @author Matth
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getVentasList() == null) {
            usuarios.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles fkRol = usuarios.getFkRol();
            if (fkRol != null) {
                fkRol = em.getReference(fkRol.getClass(), fkRol.getIdRol());
                usuarios.setFkRol(fkRol);
            }
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : usuarios.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getIdVenta());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            usuarios.setVentasList(attachedVentasList);
            em.persist(usuarios);
            if (fkRol != null) {
                fkRol.getUsuariosList().add(usuarios);
                fkRol = em.merge(fkRol);
            }
            for (Ventas ventasListVentas : usuarios.getVentasList()) {
                Usuarios oldFkUsuarioOfVentasListVentas = ventasListVentas.getFkUsuario();
                ventasListVentas.setFkUsuario(usuarios);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldFkUsuarioOfVentasListVentas != null) {
                    oldFkUsuarioOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldFkUsuarioOfVentasListVentas = em.merge(oldFkUsuarioOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Roles fkRolOld = persistentUsuarios.getFkRol();
            Roles fkRolNew = usuarios.getFkRol();
            List<Ventas> ventasListOld = persistentUsuarios.getVentasList();
            List<Ventas> ventasListNew = usuarios.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its fkUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkRolNew != null) {
                fkRolNew = em.getReference(fkRolNew.getClass(), fkRolNew.getIdRol());
                usuarios.setFkRol(fkRolNew);
            }
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getIdVenta());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            usuarios.setVentasList(ventasListNew);
            usuarios = em.merge(usuarios);
            if (fkRolOld != null && !fkRolOld.equals(fkRolNew)) {
                fkRolOld.getUsuariosList().remove(usuarios);
                fkRolOld = em.merge(fkRolOld);
            }
            if (fkRolNew != null && !fkRolNew.equals(fkRolOld)) {
                fkRolNew.getUsuariosList().add(usuarios);
                fkRolNew = em.merge(fkRolNew);
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Usuarios oldFkUsuarioOfVentasListNewVentas = ventasListNewVentas.getFkUsuario();
                    ventasListNewVentas.setFkUsuario(usuarios);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldFkUsuarioOfVentasListNewVentas != null && !oldFkUsuarioOfVentasListNewVentas.equals(usuarios)) {
                        oldFkUsuarioOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldFkUsuarioOfVentasListNewVentas = em.merge(oldFkUsuarioOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ventas> ventasListOrphanCheck = usuarios.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable fkUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Roles fkRol = usuarios.getFkRol();
            if (fkRol != null) {
                fkRol.getUsuariosList().remove(usuarios);
                fkRol = em.merge(fkRol);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
