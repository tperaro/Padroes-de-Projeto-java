package Main.Model;

import Main.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private int id;
    private String nome;
    private int docenteResponsavelId;
    private List<Observer> observers = new ArrayList<>();

    public Disciplina(int id, String nome, int docenteResponsavelId) {
        this.id = id;
        this.nome = nome;
        this.docenteResponsavelId = docenteResponsavelId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDocenteResponsavelId() {
        return docenteResponsavelId;
    }
    public void registrarObserver(Observer o) {
        observers.add(o);
    }

    public void removerObserver(Observer o) {
        observers.remove(o);
    }

    public void notificarObservadores() {
        for (Observer o : observers) {
            o.atualizar(this);
        }
    }
}

