import java.util.Objects;

public class Format {
    String opis;
    Integer rotacja;
    Integer x;
    Integer y;

    public Format(String opis, Integer rotacja, Integer x, Integer y) {
        this.opis = opis;
        this.rotacja = rotacja;
        this.x = x;
        this.y = y;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getRotacja() {
        return rotacja;
    }

    public void setRotacja(Integer rotacja) {
        this.rotacja = rotacja;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        Format bufor=(Format)obj;
        return opis.equals(bufor.opis);
    }

    @Override
    public int hashCode() {
       return Objects.hash(opis);
    }
}
