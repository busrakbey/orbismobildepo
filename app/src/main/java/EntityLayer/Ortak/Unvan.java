package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 13.10.2016.
 */

@Table(name="PER_UNVAN")
public class Unvan  extends BaseEntity{

    @Override
    public String toString()
    {
        return getUnvan();
    }

    @Column(name="id",nullable= false)
    private Long id=null;

    @Column(name="personelTipi",nullable= false)
    private String personelTipi=null;

    @Column(name="hizmetSinifi",nullable= false)
    private String hizmetSinifi=null;

    @Column(name="kodu",nullable= false)
    private String kodu=null;

    @Column(name="unvan",nullable= false)
    private String unvan=null;

    @Column(name="kisaUnvan",nullable= false)
    private String kisaUnvan=null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonelTipi() {
        return personelTipi;
    }

    public void setPersonelTipi(String personelTipi) {
        this.personelTipi = personelTipi;
    }

    public String getHizmetSinifi() {
        return hizmetSinifi;
    }

    public void setHizmetSinifi(String hizmetSinifi) {
        this.hizmetSinifi = hizmetSinifi;
    }

    public String getKodu() {
        return kodu;
    }

    public void setKodu(String kodu) {
        this.kodu = kodu;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getKisaUnvan() {
        return kisaUnvan;
    }

    public void setKisaUnvan(String kisaUnvan) {
        this.kisaUnvan = kisaUnvan;
    }
}
