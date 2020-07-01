package com.dh.summarize.view.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.dh.summarize.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author 86351
 * @date 2019/11/14
 * @description SVG图使用
 */
public class ChinaMap extends View {
    private Paint mPaint;
    private Context mContext;
    private int[] colorArray = new int[]{
            0xFF239BD7,
            0xFF30A9E5,
            0xFF80CBF1,
            0xff9B30FF,
            0xffe52302,
            0xff20fed0,
            0xffF08080,
            0xffEE6AA7,
            0xff9AFF9A

    };
    private List<ProvinceItem> itemList;
    private ProvinceItem select;
    private RectF totalRect;
    private float scale = 1.0f;


    public ChinaMap(Context context) {
        this(context, null);
    }

    public ChinaMap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChinaMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        itemList = new ArrayList<>();
        new Thread(runnable).start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.china);
            // dom解析
            //取得DocumentBuilderFactory实例
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                // 从DocumentBuilderFactory对象中中获取DocumentBuilder实例
                builder = builderFactory.newDocumentBuilder();
                // 解析输入流获取Document
                Document document = builder.parse(inputStream);
                // 解析出根节点
                Element rootElement = document.getDocumentElement();
                // 解析出所有的path节点
                NodeList nodeList = rootElement.getElementsByTagName("path");
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;
                List<ProvinceItem> list = new ArrayList<>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    ProvinceItem provinceItem = new ProvinceItem(path);
                    provinceItem.setDrawColor(colorArray[i % colorArray.length]);
                    RectF rect = new RectF();
                    path.computeBounds(rect, true);
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                    list.add(provinceItem);
                }
                itemList = list;
                // 因为left，top最终获取的是最小的，right，bottom最终获取的是最大的。
                totalRect = new RectF(left, top, right, bottom);
                // 刷新界面
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    requestLayout();
                    postInvalidate();
                });

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取到当前控件宽高值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (totalRect != null) {
            double mapWidth = totalRect.width();
            // 根据宽度去计算缩放比
            scale = (float) (width / mapWidth);
        }

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (itemList == null || itemList.size() == 0) return;
        canvas.save();
        // 对画布进行缩放
        canvas.scale(scale, scale);
        for (ProvinceItem provinceItem : itemList) {
            if (provinceItem != select) {
                provinceItem.drawItem(canvas, mPaint, false);
            } else {
                select.drawItem(canvas, mPaint, true);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (itemList == null) return false;
        ProvinceItem selectItem = null;
        for (ProvinceItem provinceItem : itemList) {
            if (provinceItem.isTouch(event.getX() / scale, event.getY() / scale)) {
                selectItem = provinceItem;
            }
        }
        if (selectItem != null) {
            select = selectItem;
            postInvalidate();
        }

        return super.onTouchEvent(event);
    }
}
