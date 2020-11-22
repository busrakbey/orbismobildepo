package ToolLayer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.konumsal.orbisozetmobil.R;


/**
 * Created by Test on 10.10.2015.
 */
public final class Utils {

    public static void HideKeyboard(Window w)
    {
        w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public static void setFormDisplay(final LinearLayout ly,final Context context)
    {
        ly.clearAnimation();
        boolean displayStatus = ly.getVisibility() == View.INVISIBLE ? false : true ;
        int lyv  = ly.getVisibility();
        int gv = View.INVISIBLE ;
        int   ngv= View.VISIBLE;
        if (View.INVISIBLE == ly.getVisibility())
        {
            displayStatus = true;
        }else
        {
            displayStatus =false;
        }
        if (displayStatus)
        {
            ly.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ly.getLayoutParams();
            lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            lp.width =LinearLayout.LayoutParams.MATCH_PARENT;
            ly.setLayoutParams(lp);
            ly.clearAnimation();
            Animation animationVisible = AnimationUtils.loadAnimation(context, R.anim.m_myanimation_anim);
            animationVisible.reset();
            ly.startAnimation(animationVisible);
            animationVisible.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }
        else
        {
            Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.m_slide_anim);
            ly.startAnimation(animation1);
            animation1.reset();
            ly.clearAnimation();
            ly.startAnimation(animation1);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ViewGroup.LayoutParams lp = ly.getLayoutParams();
                    lp.height = 0;
                    lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    ly.setLayoutParams(lp);
                    ly.setVisibility(View.INVISIBLE);


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }


    }
    public static void setFormDisplay_height600(final LinearLayout ly,final Context context)
    {
        ly.clearAnimation();
        boolean displayStatus = ly.getVisibility() == View.INVISIBLE ? false : true ;
        int lyv  = ly.getVisibility();
        int gv = View.INVISIBLE ;
        int   ngv= View.VISIBLE;
        if (View.INVISIBLE == ly.getVisibility())
        {
            displayStatus = true;
        }else
        {
            displayStatus =false;
        }
        if (displayStatus)
        {
            ly.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ly.getLayoutParams();
            lp.height = 1000;
            lp.width =LinearLayout.LayoutParams.MATCH_PARENT;
            ly.setLayoutParams(lp);
            ly.clearAnimation();
            Animation animationVisible = AnimationUtils.loadAnimation(context, R.anim.m_myanimation_anim);
            animationVisible.reset();
            ly.startAnimation(animationVisible);
            animationVisible.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }
        else
        {
            Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.m_slide_anim);
            ly.startAnimation(animation1);
            animation1.reset();
            ly.clearAnimation();
            ly.startAnimation(animation1);
            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ViewGroup.LayoutParams lp = ly.getLayoutParams();
                    lp.height = 0;
                    lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    ly.setLayoutParams(lp);
                    ly.setVisibility(View.INVISIBLE);


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }


    }

    public static void  setFormChange(final LinearLayout l_1,final LinearLayout l_2,final Context context)
    {
        l_1.setVisibility(View.INVISIBLE);
        l_2.clearAnimation();
        Animation animationVisible = AnimationUtils.loadAnimation(context, R.anim.m_myanimation_anim);
        animationVisible.reset();
        l_2.startAnimation(animationVisible);
        animationVisible.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                l_2.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
     private  static void setExChange(final LinearLayout l_2,final Context context)
     {
         l_2.setVisibility(View.VISIBLE);
         LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) l_2.getLayoutParams();
         lp2.height = LinearLayout.LayoutParams.WRAP_CONTENT;
         lp2.width =LinearLayout.LayoutParams.MATCH_PARENT;
         l_2.setLayoutParams(lp2);
         l_2.clearAnimation();
         Animation animationVisible = AnimationUtils.loadAnimation(context, R.anim.m_myanimation_anim);
         animationVisible.reset();
         l_2.startAnimation(animationVisible);

     }

    public static void setBtnClickAnim(final View v,final Context context)
    {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.m_my2anim);
        v.clearAnimation();
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                v.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void setIconClickAnimForClocWise(final View v,final Context context,final LinearLayout ln)
    {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.m_clockwise_anim);
        v.clearAnimation();
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setFormDisplay(ln, context);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public static void setIconClickAnimForClocWise600height(final View v,final Context context,final LinearLayout ln)
    {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.m_clockwise_anim);
        v.clearAnimation();
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setFormDisplay_height600(ln, context);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public static void setIconClickAnimForClocWiseForLinear_height600(final View v,final Context context,final LinearLayout ln)
    {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.m_clockwise_anim);
        v.clearAnimation();
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setFormDisplay_height600(ln, context);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public static void setListViewHeightBasedOnChildren(final  ListView listView) {

        try {
            ListAdapter listAdapter = listView.getAdapter();
            int numberOfItems = listAdapter.getCount();
            if (listAdapter == null) {
                return;
            }

            int totalHeight = 0;
            int itemHHEight=10;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.setLayoutParams(new ViewGroup.LayoutParams(0,0));
                int stm= listItem.getMeasuredState();
//                listItem.measure(0, 0);
                listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
                totalHeight += listItem.getMeasuredHeight();
                itemHHEight =listItem.getMeasuredHeight();
                int a = listItem.getHeight();
                int b = listItem.getMeasuredHeight();

            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            totalHeight=itemHHEight*10;
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            params.height = totalHeight + (listView.getDividerHeight() * (10));
            listView.setLayoutParams(params);
            listView.requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showAlert(listView.getContext(),"Hata\n"+e.getMessage(),false);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            MessageBox.showAlert(listView.getContext(),"Hata\n"+e.getMessage(),false);
        }
    }
//    public static void setListViewScrollable(final ListView list,final int listViewTouchAction) {
//
//        list.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                listViewTouchAction = event.getAction();
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    list.scrollBy(0, 1);
//                }
//                return false;
//            }
//        });
//
//        list.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (listViewTouchAction == MotionEvent.ACTION_MOVE) {
//                    list.scrollBy(0, -1);
//                }
//            }
//        });
//    }

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }
}
