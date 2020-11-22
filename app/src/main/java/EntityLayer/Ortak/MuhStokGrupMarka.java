package EntityLayer.Ortak;

/**
 * Created by isahin on 30.3.2017.
 */
public class MuhStokGrupMarka {

    @Override
    public String toString()
    {
        return  getMarkaAdi();
    }

    private Long	id	= null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


    private Long	stokGrupId	= null;

    public Long getStokGrupId() {
        return this.stokGrupId;
    }

    public void setStokGrupId(final Long stokGrupId) {
        this.stokGrupId = stokGrupId;
    }




    private String	markaKodu	= null;

    public String getMarkaKodu() {
        return this.markaKodu;
    }

    public void setMarkaKodu(final String markaKodu) {
        this.markaKodu = markaKodu;
    }


    private String	markaAdi	= null;

    public String getMarkaAdi() {
        return this.markaAdi;
    }

    public void setMarkaAdi(final String markaAdi) {
        this.markaAdi = markaAdi;
    }

    private String	tcoPrefix	= null;

    public String getTcoPrefix() {
        return this.tcoPrefix;
    }

    public void setTcoPrefix(final String tcoPrefix) {
        this.tcoPrefix = tcoPrefix;
    }



}
