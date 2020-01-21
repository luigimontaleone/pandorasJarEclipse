package model;

public class Filter {
    private String categoria;
    private String prezzo;
    private String valutazione;

    public Filter(String categoria, String prezzo, String valutazione) {
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.valutazione = valutazione;
    }

    public String getValutazione() {
        return valutazione;
    }

    public void setValutazione(String valutazione) {
        this.valutazione = valutazione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}
