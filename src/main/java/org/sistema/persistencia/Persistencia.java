package org.sistema.persistencia;

import org.sistema.use_case.PersistenceUseCase;

import java.io.*;
import java.util.List;

public abstract class Persistencia<T> implements PersistenceUseCase<T> {
    //importa una lista
    @Override
    public boolean importarLista(List<T> lista) {
        File archivo = new File(getFilePath());
        boolean existe = archivo.exists();
        lista.clear();
        if (!existe) return false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            br.readLine();
            String linea;
            while((linea = br.readLine()) != null) {
                T objeto = parsearLinea(linea);
                if (objeto != null) lista.add(objeto);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    //exporta una lista
    @Override
    public boolean exportarLista(List<T> lista) {
        try {
            File archivo = new File(getFilePath());
            PrintWriter wr = new PrintWriter(new FileWriter(archivo));
            wr.println(getCabecera());
            for (T objeto : lista) wr.println(formatObjeto(objeto));
            wr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    protected abstract String getFilePath();
    //metodo necesario para importar
    protected abstract T parsearLinea(String linea);
    //metodos necesarios para exportar
    protected abstract String getCabecera();
    protected abstract String formatObjeto(T objeto);
}
