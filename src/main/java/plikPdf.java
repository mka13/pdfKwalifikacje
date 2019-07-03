import java.util.Set;

public class plikPdf {
    Set<Format>formaty;
    int iloscStron;


    public plikPdf(Set<Format>formaty,int iloscStron) {
        this.formaty=formaty;
        this.iloscStron = iloscStron;

    }

    public Set<Format> getFormaty() {
        return formaty;
    }

    public void setFormaty(Set<Format> formaty) {
        this.formaty = formaty;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }


    public Format znajdzFormat(String opis){

        for (Format x:formaty
             ) {
            if(x.getOpis().equals(opis)){
                return x;
            }
        }
        return null;
    }
}
