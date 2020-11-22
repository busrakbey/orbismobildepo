package DataLayer.Sistem;

import android.content.Context;

import DataLayer.Ortak.DataController;
import EntityLayer.Sistem.SCalisan;


/**
 * Created by Ömer YILDIRIM on 21.01.2016.
 */
public class SCalisan_Data extends DataController<SCalisan> {

    public SCalisan_Data(Context ctx) {
        super(ctx,new SCalisan());
    }

}
