package ComponentLayer;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;


/**
 * Created by Ömer YILDIRIM on 9.2.2016.
 */
public class OrbisNumberPicker extends TextView {

	private static final String TAG = "OrbNumPicker";

	private static final float GESTURE_STEP_DP = 5.0f;

	private OnValueChangeListener mOnValueChangeListener;

	private int mGestureStepPx;

	private float mStartX;
	private float mIntermediateX;
	private float mIntermediateValue;
	private float mStrokeWidth;
	private float mTextWidth;
	private float mCornerRadius;

	private int mPrimaryValue;
	private int mMinValue;
	private int mMaxValue;

	private int mArrowColor;
	private int mBackgroundColor;
	private int mNumColor;

	private String mDialogTitle = "";

	private boolean mIsShowNumberPickerDialog = true;
	private boolean mIntermediate = false;

	private AlertDialog numberPickerDialog;
	private String labelText;
	public  TextView txtFrame=null;
	public Button btnFrame=null;
	public OrbisNumberPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public OrbisNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attributeSet) {
		initAttributes(context, attributeSet);
		float scale = getResources().getDisplayMetrics().density;
		mGestureStepPx = (int) (GESTURE_STEP_DP * scale + 0.5f);
		customize();
	}

	private void initAttributes(Context context, AttributeSet attributeSet) {
		TypedArray attrs = context.obtainStyledAttributes(attributeSet,
				R.styleable.OrbisNumberPicker, 0, 0);
		if (attrs != null) {
			try {
				labelText= attrs.getString(R.styleable.OrbisNumberPicker_snp_labeltext);
				if (labelText==null)
					labelText ="Deger";
				mPrimaryValue = attrs.getInteger(R.styleable.OrbisNumberPicker_snp_value, 0);
				mMinValue = attrs.getInteger(R.styleable.OrbisNumberPicker_snp_min, 0);
				mMaxValue = attrs.getInteger(R.styleable.OrbisNumberPicker_snp_max, 500);

				mArrowColor = attrs.getColor(R.styleable.OrbisNumberPicker_snp_arrowColor, context.getResources().getColor(R.color.text));
				mBackgroundColor = attrs.getColor(R.styleable.OrbisNumberPicker_snp_backgroundColor, context.getResources().getColor(R.color.bembeyaz));
				mNumColor = attrs.getColor(R.styleable.OrbisNumberPicker_snp_numberColor, context.getResources().getColor(R.color.siyah));

				mIntermediate = attrs.getBoolean(R.styleable.OrbisNumberPicker_snp_intermediate, false);
				setLines(3);
				setMaxLines(4);
			} finally {
				attrs.recycle();
			}
		}

		mCornerRadius = context.getResources().getDimension(R.dimen.radius);
		mStrokeWidth = context.getResources().getDimension(R.dimen.stroke_width);

		Paint textPaint = new Paint();
		textPaint.setTextSize(getTextSize());
		mTextWidth = textPaint.measureText(Integer.toString(mMaxValue));
	}

	private void customize() {
		Drawable left = getDrawableCompat(R.drawable.ic_arrow_left);
		Drawable right = getDrawableCompat(R.drawable.ic_arrow_right);

		setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
		setBackgroundCompat(createBackgroundStateList());
		setGravity(Gravity.CENTER);
		setSingleLine(true);
		setTextColor(mNumColor);
		setNormalBackground();
		//setHeight(100);
		// setBackgroundCompat(getDrawableCompat(R.drawable.mf_greenborder_backtrans_drw));
		mIntermediateValue = mPrimaryValue;
		changeValue(mPrimaryValue);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Drawable arrow = getCompoundDrawables()[0];
		setMinWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mTextWidth + arrow.getBounds().width() * 2, getContext().getResources().getDisplayMetrics()));
		setMinHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, arrow.getBounds().height() * 1.5f, getContext().getResources().getDisplayMetrics()));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			if (!isEnabled())
				return false;

			float currentX = event.getX();

			switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					if (btnFrame!=null)
					{
						btnFrame.setVisibility(ViewGroup.INVISIBLE);
					}
					mStartX = event.getX();
					mIntermediateX = mStartX;
					highlightBackground();


					return true;

				case MotionEvent.ACTION_MOVE:
					if (Math.abs(currentX - mStartX) > mGestureStepPx)
					{
						float distance = currentX - mIntermediateX;
						highlightArrows(distance);
						double swipedDistance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, Math.abs(distance), getContext().getResources().getDisplayMetrics());
						float threshold;
						if (swipedDistance < 25) {
							return true;
						} else if (swipedDistance < 50) {
							threshold = 1;
						} else if (swipedDistance < 150) {
							threshold = 2;
						} else if (swipedDistance < 300) {
							threshold = 3;
						} else if (swipedDistance < 450) {
							threshold = 4;
						} else {
							threshold = 5;
						}
						mIntermediateValue += distance > 0 ? threshold : -threshold;
						changeValue((int) mIntermediateValue);
						if (mIntermediate)
							notifyListener((int) mIntermediateValue);
					}

					mIntermediateX = currentX;
					break;

				case MotionEvent.ACTION_CANCEL:

				case MotionEvent.ACTION_UP:
					if (btnFrame!=null)
					{
						btnFrame.setVisibility(ViewGroup.VISIBLE);
					}

					setNormalBackground();

					if (Math.abs(currentX - mStartX) <= mGestureStepPx) {
						processClick();
					} else {
						notifyListener((int) mIntermediateValue);
					}
					return false;

				default:
					setNormalBackground();
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}catch (Throwable e) {
			e.printStackTrace();
		}
		return true;
	}

	private void notifyListener(int newValue)
	{
		if (mOnValueChangeListener != null
				&& mOnValueChangeListener.onValueChange(this, mPrimaryValue, newValue)) {

			mPrimaryValue = newValue;
		} else {

			changeValue(mPrimaryValue);
		}
		mIntermediateValue = mPrimaryValue;
	}

	private void changeValue(int value) {
		if (value < mMinValue || value > mMaxValue) {
			value = value < mMinValue ? mMinValue : mMaxValue;
			mIntermediateValue = value;
		}
		setText(labelText+" : "+String.valueOf(value));
		if (txtFrame!=null)
		{
			txtFrame.setText(labelText+":"+String.valueOf(value));
			//btnFrame.setVisibility(View.INVISIBLE);


		}

	}

	private void processClick() {
		if (mIsShowNumberPickerDialog)
			showNumberPickerDialog();
		//else
		//performClick();
	}

	public void showNumberPickerDialog() {
		if (numberPickerDialog != null && numberPickerDialog.isShowing())
			return;

		numberPickerDialog = getNumberPickerDialog();
		numberPickerDialog.show();
	}

	private AlertDialog getNumberPickerDialog() {

		final NumberPicker numberPicker = new NumberPicker(getContext());
		numberPicker.setLayoutParams(new NumberPicker.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		numberPicker.setMaxValue(mMaxValue);
		numberPicker.setMinValue(mMinValue > 0 ? mMinValue : 0);
		numberPicker.setValue(mPrimaryValue);
		//numberPicker.setWrapSelectorWheel(true);
		numberPicker.setMinimumWidth(400);
		numberPicker.setMinimumHeight(400);
		numberPicker.setTag(true);
		numberPicker.setBackgroundColor(getResources().getColor(R.color.transparent));

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		if (!mDialogTitle.equals(""))
			builder.setTitle(mDialogTitle);
		builder.setTitle("Seçiniz..");
		builder.setIcon(ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher));
		builder.setView(numberPicker);

		//builder.setView(numberPicker).setPositiveButton(android.R.string.ok,
		//		new DialogInterface.OnClickListener() {
		//			@Override
		//			public void onClick(DialogInterface dialog, int which) {
		//				int newValue = numberPicker.getValue();
		//				changeValue(newValue);
		//				notifyListener(newValue);
		//			}
		//		});
		numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if ((Boolean)picker.getTag())
				{
					numberPickerDialog.dismiss();
				}
				//
				changeValue(newVal);
				notifyListener(newVal);
				numberPicker.setTag(true);


			}
		});
		numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
			@Override
			public void onScrollStateChange(NumberPicker view, int scrollState) {
				view.setTag(false);
			}
		});

		numberPickerDialog =builder.create();
		return numberPickerDialog;
	}

	private void setNormalBackground() {
		setPressed(false);
		//customizeArrows(mArrowColor);
	}

	private StateListDrawable createBackgroundStateList() {
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{-android.R.attr.state_enabled}, createBackgroundDrawable(lightenColor(mBackgroundColor)));
		drawable.addState(new int[]{android.R.attr.state_pressed}, createBackgroundDrawable(darkenColor(mBackgroundColor)));
		drawable.addState(new int[]{}, createBackgroundDrawable(mBackgroundColor));
		return drawable;
	}

	private Drawable createBackgroundDrawable(int color) {
		ShapeDrawable backgroundDrawable = new ShapeDrawable(new RoundRectShape(new float[]{mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius}, null, null));
		final Paint paint = backgroundDrawable.getPaint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setColor(color);
		paint.setStrokeWidth(mStrokeWidth);
		Drawable[] layers = {backgroundDrawable};
		LayerDrawable drawable = new LayerDrawable(layers);
		int halfStrokeWidth = (int) (mStrokeWidth / 2f);
		drawable.setLayerInset(0, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth);
		return drawable;
	}

	private void customizeArrows(int color) {
		//setColorFilter(getCompoundDrawables()[0], color);
		//setColorFilter(getCompoundDrawables()[2], color);
	}

	private void highlightBackground() {
		setPressed(true);
	}

	private void highlightArrows(float distance) {
		setPressed(true);
		if (distance < 0) {
			// rg
			setColorFilter(getCompoundDrawables()[0], darkenColor(mArrowColor));
			setColorFilter(getCompoundDrawables()[2], mArrowColor);
		} else {
			// lf
			setColorFilter(getCompoundDrawables()[0], mArrowColor);
			setColorFilter(getCompoundDrawables()[2], darkenColor(mArrowColor));
		}
	}

	private void setColorFilter(Drawable drawable, int color) {
		if (drawable == null) return;

		drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}


	private int darkenColor(int color) {
		float factor = 0.9f;
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		return Color.argb(Color.alpha(color),
				Math.max((int) (r * factor), 0),
				Math.max((int) (g * factor), 0),
				Math.max((int) (b * factor), 0));
	}

	private int lightenColor(int color) {
		float factor = 0.3f;
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		return Color.argb(Color.alpha(color),
				(int) ((r * (1 - factor) / 255 + factor) * 255),
				(int) ((g * (1 - factor) / 255 + factor) * 255),
				(int) ((b * (1 - factor) / 255 + factor) * 255));

	}

	@Override
	public void setBackgroundColor(int color) {
		mBackgroundColor = color;
		setBackgroundCompat(createBackgroundStateList());
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		int arrowColor = mArrowColor;
		int numColor = mNumColor;
		if (!enabled) {
			arrowColor = lightenColor(mArrowColor);
			numColor = lightenColor(mNumColor);
		}
		customizeArrows(arrowColor);
		setTextColor(numColor);
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private Drawable getDrawableCompat(int resource) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			return getContext().getResources().getDrawable(resource, null);
		}
		return getContext().getResources().getDrawable(resource);
	}

	@SuppressWarnings("deprecation")
	private void setBackgroundCompat(Drawable drawable) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			setBackground(drawable);
		} else {
			setBackgroundDrawable(drawable);
		}
	}

	public void setValue(int value) {
		mPrimaryValue = value;
		mIntermediateValue = value;
	}

	public void setValue(int value, boolean isNotifyListener) {
		setValue(value);
		changeValue(value);
		if (isNotifyListener)
			notifyListener(value);
	}

	public void setArrowColor(int mArrowColor) {
		this.mArrowColor = mArrowColor;
		customizeArrows(mArrowColor);
	}

	public void setOnValueChangeListener(OnValueChangeListener valueChangeListener) {
		mOnValueChangeListener = valueChangeListener;
	}

	public int getValue() {
		return mPrimaryValue;
	}

	public void setNumberPickerDialogTitle(String title) {
		mDialogTitle = title;
	}

	public void setShowNumberPickerDialog(boolean isShowNumberPickerDialog) {
		mIsShowNumberPickerDialog = isShowNumberPickerDialog;
	}

	public int getMinValue() {
		return mMinValue;
	}

	public void setMinValue(int minValue) {
		mMinValue = minValue;
	}

	public int getMaxValue() {
		return mMaxValue;
	}

	public void setMaxValue(int maxValue) {
		mMaxValue = maxValue;
	}

	public boolean isIntermediate() {
		return mIntermediate;
	}

	public void setIntermediate(boolean intermediate) {
		mIntermediate = intermediate;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}


}