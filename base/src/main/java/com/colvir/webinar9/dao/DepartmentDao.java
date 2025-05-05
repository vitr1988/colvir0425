package com.colvir.webinar9.dao;

import com.colvir.webinar9.model.Department;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentDao {

    private final EntityManager em;

    public Department getById(Long id) {
        return em.find(Department.class, id);
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class).getResultList();
    }

    @Transactional
    public Department save(Department department) {
//        em.getTransaction().begin();
        try {
            if (department.getId() == null) {
                em.persist(department);
            } else {
                department = em.merge(department);
            }
//            em.getTransaction().commit();
            return department;
        }
        catch (Exception e) {
//            em.getTransaction().rollback();
            return null;
        }
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from  Department d where d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void delete(Department department) {
        em.remove(getById(department.getId()));
    }
}
