import java.util.Set;

public class plikPdf {
    Set<String>formaty;
    int iloscStron;


    public plikPdf(Set<String>formaty,int iloscStron) {
        this.formaty=formaty;
        this.iloscStron = iloscStron;

    }

    public Set<String> getFormaty() {
        return formaty;
    }

    public void setFormaty(Set<String> formaty) {
        this.formaty = formaty;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }
}
