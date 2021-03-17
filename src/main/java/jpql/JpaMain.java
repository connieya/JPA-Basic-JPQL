package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

          Member result =
                  em.createQuery("select m from Member m where m.username = :?1", Member.class)
            .setParameter(1,"member1")
            .getSingleResult();

            System.out.println("singleResult = "+result);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
