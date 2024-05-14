interface AvaliacaoStrategy {
    double calcularNota();
}

class Prova implements AvaliacaoStrategy {
    private double nota;

    public Prova(double nota) {
        this.nota = nota;
    }

    public double calcularNota() {
        return nota;
    }
}

class Trabalho implements AvaliacaoStrategy {
    private double nota;

    public Trabalho(double nota) {
        this.nota = nota;
    }

    public double calcularNota() {
        return nota;
    }
}

interface AlunoBuilder {
    AlunoBuilder adicionarAvaliacao(AvaliacaoStrategy avaliacao);
    Aluno build();
}

class AlunoDirector implements AlunoBuilder {
    private Aluno aluno;

    public AlunoDirector(String nome) {
        this.aluno = new Aluno(nome);
    }

    public AlunoBuilder adicionarAvaliacao(AvaliacaoStrategy avaliacao) {
        aluno.adicionarAvaliacao(avaliacao);
        return this;
    }

    public Aluno build() {
        return aluno;
    }
}

class Aluno {
    private String nome;
    private List<AvaliacaoStrategy> avaliacoes;

    public Aluno(String nome) {
        this.nome = nome;
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(AvaliacaoStrategy avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public double calcularMedia() {
        double soma = 0;
        for (AvaliacaoStrategy avaliacao : avaliacoes) {
            soma += avaliacao.calcularNota();
        }
        return soma / avaliacoes.size();
    }
}

public class EscolaTest {
    public static void main(String[] args) {
        AlunoBuilder builder = new AlunoDirector("João");
        Aluno joao = builder
                        .adicionarAvaliacao(new Prova(8.5))
                        .adicionarAvaliacao(new Trabalho(7.0))
                        .build();

        double mediaJoao = joao.calcularMedia();
        System.out.println("Média de " + joao.getNome() + ": " + mediaJoao);
    }
}
