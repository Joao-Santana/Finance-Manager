package pt.upskill.projeto2.financemanager.categories;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class Category implements Serializable{

    private String name;
    private List<String> tags = new ArrayList<>();

    private static final long serialVersionUID = -9107819223195202547L;

    public Category(String name) {
        this.name = name;
    }

    /**
     * Função que lê o ficheiro categories e gera uma lista de {@link Category} (método fábrica)
     * Deve ser utilizada a desserialização de objetos para ler o ficheiro binário categories.
     *
     * @param file - Ficheiro onde estão apontadas as categorias possíveis iniciais, numa lista serializada (por defeito: /account_info/categories)
     * @return uma lista de categorias, geradas ao ler o ficheiro
     */
    public static List<Category> readCategories(File file) {
        List<Category> categories = null;
        try {
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(filein);
            categories = (List<Category>) in.readObject();
            in.close();
            filein.close();
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Couldn't read the file");
            e.printStackTrace();
        }
        return categories;
    }
    /**
     * Função que grava no ficheiro categories (por defeito: /account_info/categories) a lista de {@link Category} passada como segundo argumento
     * Deve ser utilizada a serialização dos objetos para gravar o ficheiro binário categories.
     *
     * @param file
     * @param categories
     */
    public static void writeCategories(File file, List<Category> categories)
    {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOutputStream);
            objectOut.writeObject(categories);
            objectOut.close();
        }
         catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean hasTag(String tag) {
        for (String label : tags) {
            if (label.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    public void addTag(String tag) {
        if(!hasTag(tag.trim()))
        {
            tags.add(tag.trim());
        }
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Categoria" + "\t" + "Nome: "+ name + "\t" + ", Descricao " + tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
