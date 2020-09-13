package com.dh.static_skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.dh.static_skin.view.SkinButton;
import com.dh.static_skin.view.SkinEditText;
import com.dh.static_skin.view.SkinImageView;
import com.dh.static_skin.view.SkinTextView;

/**
 * @author 86351
 * @date 2020/9/13
 * @description 处理控件
 */
public class SkinAppCompatViewInflater {
    private Context mContext;
    /**
     * 控件名
     */
    private String name;
    /**
     * 控件属性集合
     */
    private AttributeSet attrs;

    public SkinAppCompatViewInflater(Context mContext) {
        this.mContext = mContext;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttrs(AttributeSet attrs) {
        this.attrs = attrs;
    }

    public View createView() {
        if (TextUtils.isEmpty(name) || attrs == null) {
            return null;
        }
        View view = null;
        switch (name) {
            case "TextView":
                view = new SkinTextView(mContext, attrs);
                break;
            case "ImageView":
                view = new SkinImageView(mContext, attrs);
                break;
            case "Button":
                view = new SkinButton(mContext, attrs);
                break;
            case "EditText":
                view = new SkinEditText(mContext, attrs);
                break;
            /*case "Spinner":
                view = createSpinner(mContext, attrs);
                break;
            case "ImageButton":
                view = createImageButton(mContext, attrs);
                break;
            case "CheckBox":
                view = createCheckBox(mContext, attrs);
                break;
            case "RadioButton":
                view = createRadioButton(mContext, attrs);
                break;
            case "CheckedTextView":
                view = createCheckedTextView(mContext, attrs);
                break;
            case "AutoCompleteTextView":
                view = createAutoCompleteTextView(mContext, attrs);
                break;
            case "MultiAutoCompleteTextView":
                view = createMultiAutoCompleteTextView(mContext, attrs);
                break;
            case "RatingBar":
                view = createRatingBar(mContext, attrs);
                break;
            case "SeekBar":
                view = createSeekBar(mContext, attrs);
                break;
            case "ToggleButton":
                view = createToggleButton(mContext, attrs);
                break;*/
        }
        return view;
    }
}
