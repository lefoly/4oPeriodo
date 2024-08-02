/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package start;

import model.Aluno;
import model.Turma;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lefoly
 */
public class Principal {

    public static void main(String[] args) {
        Aluno a1 = new Aluno("Fulano");
        Aluno a2 = new Aluno("Beltrano");
        Aluno a3 = new Aluno("Ciclano");
        Aluno a4 = new Aluno("Maria");
        Aluno a5 = new Aluno("José");

        Turma t1 = new Turma("POO");
        List<Aluno> alunosT1 = new ArrayList();
        alunosT1.add(a1);
        alunosT1.add(a3);
        alunosT1.add(a4);
        t1.setAlunos(alunosT1);

        Turma t2 = new Turma("Banco de Dados");
        List<Aluno> alunosT2 = new ArrayList();
        alunosT2.add(a2);
        alunosT2.add(a5);
        t2.setAlunos(alunosT2);
    }
}
