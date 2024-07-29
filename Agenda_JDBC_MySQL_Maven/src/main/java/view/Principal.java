/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package view;

import java.util.List;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;

/**
 *
 * @author lefoly
 */
public class Principal {

    public static void main(String[] args) {

        /*
        Contato c = new Contato("Contato MAIS NOVO", 
                               "contato@maisnovo.com", 
                             "555555555");

        try {
            ContatoDaoJdbc dao = DaoFactory.novoContatoDao();        
            
            //INCLUIR
            //dao.incluir(c);
        
            //EDITAR
            //c.setId(5);
            //dao.editar(c);
                             
            //EXCLUIR
            //c.setId(5);
            //dao.excluir(c);
        
        
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
        
        
        //LISTAR
        try {
            ContatoDaoJdbc dao1 = DaoFactory.novoContatoDao();
            List<Contato> lista;
            lista = dao1.listar();
            for (Contato contato : lista) {
                System.out.println(contato);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
*/
        
     try {
            ContatoDaoJdbc dao = DaoFactory.novoContatoDao();        
   
            Contato c = dao.pesquisarPorId(3);
            if (c != null) 
               System.out.println(c); 
             else
               System.out.println("Contato não encontrado."); 
            
         } catch (Exception e) {
            System.out.println(e);
        }        
        
    }
}
