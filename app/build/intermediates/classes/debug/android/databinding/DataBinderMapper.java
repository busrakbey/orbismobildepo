
package android.databinding;
import com.konumsal.orbisozetmobil.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 15;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.konumsal.orbisozetmobil.R.layout.item_product_card:
                    return com.konumsal.orbisozetmobil.databinding.ItemProductCardBinding.bind(view, bindingComponent);
                case com.konumsal.orbisozetmobil.R.layout.card_activity:
                    return com.konumsal.orbisozetmobil.databinding.CardActivityBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1705033816: {
                if(tag.equals("layout/item_product_card_0")) {
                    return com.konumsal.orbisozetmobil.R.layout.item_product_card;
                }
                break;
            }
            case 1137044138: {
                if(tag.equals("layout/card_activity_0")) {
                    return com.konumsal.orbisozetmobil.R.layout.card_activity;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}