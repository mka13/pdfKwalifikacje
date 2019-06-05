public class plikPdf {
    float y;
    float x;
    float x_landscape;
    float y_landscape;
    int iloscStron;
    boolean czyMaZZnakWodny;

    public plikPdf(float wysokosc, float szerokosc,float x_landscape,float y_landscape, int iloscStron, boolean czyMaZZnakWodny) {
        this.y = wysokosc;
        this.x = szerokosc;
        this.iloscStron = iloscStron;
        this.czyMaZZnakWodny = czyMaZZnakWodny;
        this.x_landscape=x_landscape;
        this.y_landscape=y_landscape;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX_landscape() {
        return x_landscape;
    }

    public void setX_landscape(float x_landscape) {
        this.x_landscape = x_landscape;
    }

    public float getY_landscape() {
        return y_landscape;
    }

    public void setY_landscape(float y_landscape) {
        this.y_landscape = y_landscape;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }

    public boolean isCzyMaZZnakWodny() {
        return czyMaZZnakWodny;
    }

    public void setCzyMaZZnakWodny(boolean czyMaZZnakWodny) {
        this.czyMaZZnakWodny = czyMaZZnakWodny;
    }
}
