package EntityLayer.Ortak;

/**
 * Created by isahin on 23.3.2017.
 */
public class SCity {



    @Override
    public String toString()
    {
        return  adi;
    }

    public Long id = null;
    public String adi = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }
}
