
    package Main.Model;

    public class Disciplina {
        private int id;
        private String nome;
        private int docenteResponsavelId;

        // Propriedades adicionais
        private String descricao;
        private String horario;
        private int capacidadeMaxima;

        public Disciplina(int id, String nome, int docenteResponsavelId) {
            this.id = id;
            this.nome = nome;
            this.docenteResponsavelId = docenteResponsavelId;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public int getDocenteResponsavelId() {
            return docenteResponsavelId;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getHorario() {
            return horario;
        }

        public int getCapacidadeMaxima() {
            return capacidadeMaxima;
        }
        
     // Notificar observadores (supondo que existe um mecanismo de Observer já configurado)
        public void notificarObservadores() {
            // Implementa a lógica para notificar observadores associados à disciplina
            // Exemplo genérico:
            System.out.println("Observadores da disciplina " + nome + " foram notificados.");
        }

        // Salvar memento do aluno (requer que a estrutura de caretaker e memento esteja integrada)
       
        // Método put para adicionar ou atualizar propriedades da disciplina
   
        // Método para exibir os detalhes da disciplina
        @Override
        public String toString() {
            return "Disciplina{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", docenteResponsavelId=" + docenteResponsavelId +
                    ", descricao='" + descricao + '\'' +
                    ", horario='" + horario + '\'' +
                    ", capacidadeMaxima=" + capacidadeMaxima +
                    '}';
        }
        
        public void put(String propriedade, Object valor) {
            if (propriedade == null || valor == null) {
                throw new IllegalArgumentException("Propriedade ou valor não podem ser nulos");
            }

            switch (propriedade.toLowerCase()) {
                case "descricao":
                    if (valor instanceof String) {
                        this.descricao = (String) valor;
                    } else {
                        throw new IllegalArgumentException("O valor para 'descricao' deve ser uma String");
                    }
                    break;

                case "horario":
                    if (valor instanceof String) {
                        this.horario = (String) valor;
                    } else {
                        throw new IllegalArgumentException("O valor para 'horario' deve ser uma String");
                    }
                    break;

                case "capacidademaxima":
                    if (valor instanceof Integer) {
                        this.capacidadeMaxima = (Integer) valor;
                    } else {
                        throw new IllegalArgumentException("O valor para 'capacidadeMaxima' deve ser um Integer");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Propriedade desconhecida: " + propriedade);
            }
        }
        
        
    }

