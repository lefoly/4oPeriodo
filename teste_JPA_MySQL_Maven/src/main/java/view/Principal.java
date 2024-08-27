/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package view;

import controller.ConnFactory;
import javax.persistence.EntityManager;
import model.Contato;

/**
 *
 * @author lefoly
 */
public class Principal {

    public static void main(String[] args) {

      Contato c = new Contato("Fulano de Tal", "fu@gmail.com", "1234-5678");   
        
      EntityManager em = ConnFactory.getEntityManager();

      try {         
            em.getTransaction().begin();

            //INCLUIR
            em.persist(c);
          
            em.getTransaction().commit();
      } finally {
            em.close();
      }
        
    }
}
