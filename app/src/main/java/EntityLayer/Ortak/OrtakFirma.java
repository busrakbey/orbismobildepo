package EntityLayer.Ortak;

/**
 * Created by isahin on 24.3.2017.
 */
public class OrtakFirma {


    @Override
    public String toString()
    {
        return getAdi();
    }

    private Long	id	= null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    private String	adi	= null;

    public String getAdi() {
        return this.adi;
    }

    public void setAdi(final String adi) {
        this.adi = adi;
    }
}
