package Services;

import java.util.List;

public interface IService <T> {
    void ajouterService_Name(T t);
    List<T> afficherService_Name();
    void modifierService_Name(T t);
    void supprimerService_Name(T t);
}
