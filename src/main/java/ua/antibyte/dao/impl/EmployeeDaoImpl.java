package ua.antibyte.dao.impl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.antibyte.dao.EmployeeDao;
import ua.antibyte.exception.DataProcessingException;
import ua.antibyte.model.Employee;

@Repository
@Log4j
public class EmployeeDaoImpl implements EmployeeDao {
    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addAll(List<Employee> employees) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession().getSession();
            transaction = session.beginTransaction();
            for (Employee employee : employees) {
                session.persist(employee);
            }
            transaction.commit();
            log.info(" " + employees.size() + " " + Employee.class.getSimpleName()
                    + "'s added to DB successfully");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save employees ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Employee findById(Long id) {
        try (Session session = sessionFactory.openSession().getSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find employee by id " + id, e);
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = sessionFactory.openSession().getSession()) {
            Query<Employee> getAllQuery = session.createQuery("from Employee e "
                    + "join fetch e.picture", Employee.class);
            return getAllQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all employees", e);
        }
    }
}
