

import java.util.List;

public interface IRepository<T extends Entidade> {
    void cadastrar(T obj);
    boolean remover(int id);
    List<T> listar();
    boolean alterar(T obj);
    T buscarPorId(int id);
}
