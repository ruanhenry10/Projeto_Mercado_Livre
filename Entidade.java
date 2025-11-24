

public abstract class Entidade {
    protected int id;
    private static int contador = 1;

    public Entidade() {
        this.id = contador++;
    }

    public Entidade(int id) {
        this.id = id;
    }

    public int getId() { 
        return id; 
    }

    // Método abstrato — implementado nas classes filhas
    public abstract String getResumo();
}
