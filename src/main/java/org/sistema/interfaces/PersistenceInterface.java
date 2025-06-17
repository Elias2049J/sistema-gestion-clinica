package org.sistema.interfaces;

import java.util.List;
// Define métodos para trabajar con listas y archivos
public interface PersistenceInterface<T> {
    boolean loadListFromFile(List<T> lista);
    boolean updateFileFromList(List<T> lista);
}
