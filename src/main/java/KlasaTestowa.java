import java.util.Objects;

public class KlasaTestowa {
    String opis;
    int id;

    public KlasaTestowa(String opis, int id) {
        this.opis = opis;
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        KlasaTestowa item=(KlasaTestowa) obj;
        if(obj==this){
            return true;
        }
        return opis.equals(item.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opis);
    }
}
