package com.dh.static_skin.entity;

import android.content.res.TypedArray;
import android.util.SparseIntArray;

/**
 * @author 86351
 * @date 2020/9/13
 * @description
 */
public class AttrsBean {
    private SparseIntArray resourceArray;
    private static final int DEFAULT_VALUE = -1;

    public AttrsBean() {
        resourceArray = new SparseIntArray();
    }

    /**
     * 存储控件
     * key 属性名    value  属性值
     *
     * @param typedArray 控件属性的类型集合，如：background / textColor
     * @param styleables 自定义属性，参考value/attrs.xml
     */
    public void saveViewResource(TypedArray typedArray, int[] styleables) {
        for (int i = 0; i < styleables.length; i++) {
            int key = styleables[i];
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            resourceArray.put(key, resourceId);
        }
    }

    /**
     * 获取控件某属性的resourceId
     * @param styleable 自定义控件属性
     * @return 自定义控件属性值
     */
    public int getViewResource(int styleable) {
        return resourceArray.get(styleable);
    }
}
