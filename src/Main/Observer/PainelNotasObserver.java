package Main.Observer;

import Main.Model.Disciplina;

public class PainelNotasObserver implements Observer {
    @Override
    public void atualizar(Object subject) {
        if (subject instanceof Disciplina) {
            Disciplina d = (Disciplina) subject;
            System.out.println("PainelNotasObserver: Notas da disciplina " + d.getNome() + " foram atualizadas!");
            // Aqui no futuro atualizaríamos o JTable na GUI, por exemplo
        }
    }
}

