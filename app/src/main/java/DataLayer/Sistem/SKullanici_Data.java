package DataLayer.Sistem;

import android.content.Context;

import DataLayer.Ortak.DataController;
import EntityLayer.Sistem.SKullanici;


/**
 * Created by Ömer YILDIRIM on 21.01.2016.
 */
public class SKullanici_Data extends DataController<SKullanici> {

    public SKullanici_Data(Context ctx) {
        super(ctx,new SKullanici());
    }

}
