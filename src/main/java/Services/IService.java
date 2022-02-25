package Services;

import java.util.List;

public interface IService <T> {
    void ajouter(T t);
    List<T> afficher();
    void modifier(T t);
    void supprimer(T t);
}
