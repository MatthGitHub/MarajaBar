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
import negocio.entidades.Estadosventa;
import negocio.entidades.Mesa;
import negocio.entidades.Usuarios;
import negocio.entidades.Ventas;

/**
 *
 * @author matth
 */
public class VentasJpaController implements Serializable {

    public VentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadosventa fkEstado = ventas.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getIdEstadoVenta());
                ventas.setFkEstado(fkEstado);
            }
            Mesa fkMesa = ventas.getFkMesa();
            if (fkMesa != null) {
                fkMesa = em.getReference(fkMesa.getClass(), fkMesa.getIdMesa());
                ventas.setFkMesa(fkMesa);
            }
            Usuarios fkUsuario = ventas.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getIdUsuario());
                ventas.setFkUsuario(fkUsuario);
            }
            em.persist(ventas);
            if (fkEstado != null) {
                fkEstado.getVentasList().add(ventas);
                fkEstado = em.merge(fkEstado);
            }
            if (fkMesa != null) {
                fkMesa.getVentasList().add(ventas);
                fkMesa = em.merge(fkMesa);
            }
            if (fkUsuario != null) {
                fkUsuario.getVentasList().add(ventas);
                fkUsuario = em.merge(fkUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas persistentVentas = em.find(Ventas.class, ventas.getIdVenta());
            Estadosventa fkEstadoOld = persistentVentas.getFkEstado();
            Estadosventa fkEstadoNew = ventas.getFkEstado();
            Mesa fkMesaOld = persistentVentas.getFkMesa();
            Mesa fkMesaNew = ventas.getFkMesa();
            Usuarios fkUsuarioOld = persistentVentas.getFkUsuario();
            Usuarios fkUsuarioNew = ventas.getFkUsuario();
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getIdEstadoVenta());
                ventas.setFkEstado(fkEstadoNew);
            }
            if (fkMesaNew != null) {
                fkMesaNew = em.getReference(fkMesaNew.getClass(), fkMesaNew.getIdMesa());
                ventas.setFkMesa(fkMesaNew);
            }
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getIdUsuario());
                ventas.setFkUsuario(fkUsuarioNew);
            }
            ventas = em.merge(ventas);
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getVentasList().remove(ventas);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getVentasList().add(ventas);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            if (fkMesaOld != null && !fkMesaOld.equals(fkMesaNew)) {
                fkMesaOld.getVentasList().remove(ventas);
                fkMesaOld = em.merge(fkMesaOld);
            }
            if (fkMesaNew != null && !fkMesaNew.equals(fkMesaOld)) {
                fkMesaNew.getVentasList().add(ventas);
                fkMesaNew = em.merge(fkMesaNew);
            }
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getVentasList().remove(ventas);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getVentasList().add(ventas);
                fkUsuarioNew = em.merge(fkUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ventas.getIdVenta();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas ventas;
            try {
                ventas = em.getReference(Ventas.class, id);
                ventas.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.", enfe);
            }
            Estadosventa fkEstado = ventas.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getVentasList().remove(ventas);
                fkEstado = em.merge(fkEstado);
            }
            Mesa fkMesa = ventas.getFkMesa();
            if (fkMesa != null) {
                fkMesa.getVentasList().remove(ventas);
                fkMesa = em.merge(fkMesa);
            }
            Usuarios fkUsuario = ventas.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getVentasList().remove(ventas);
                fkUsuario = em.merge(fkUsuario);
            }
            em.remove(ventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
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

    public Ventas findVentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
