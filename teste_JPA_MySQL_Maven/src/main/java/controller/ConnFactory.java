/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lefoly
 */
public class ConnFactory {

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("AgendaPU");
        EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }

}
