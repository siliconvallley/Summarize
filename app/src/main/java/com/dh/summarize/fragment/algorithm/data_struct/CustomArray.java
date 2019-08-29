package com.dh.summarize.fragment.algorithm.data_struct;

import android.widget.Toast;
import com.dh.utils_library.utils.LogUtils;

public class CustomArray {
    private int[] array;
    // 元素个数
    private int size;
    private static final String TAG = "CustomArray";

    public CustomArray(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    public void insert(int index, int element) {
        // 判断下标是否合法
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("数组越界，超出已有元素范围");
        }
        // 如果实际元素个数达到数组容量的上限，则进行扩容
        if (size >= array.length) {
            LogUtils.d(TAG, "我扩容了");
            expansionArray();
        }
        // 数组在内存中占有连续的内存空间，数组插入一个元素，插入位置后面的元素需要依次像后移动一位
        /*for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }*/
        if (size - 1 - index >= 0) System.arraycopy(array, index, array, index + 1, size - 1 - index);
        array[index] = element;
        size++;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组越界，超出已有元素范围");
        }
        int delElement = array[index];
        // 需要将元素向前移动一位
        /*for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }*/
        // 下面这种方式其实是牺牲空间复杂度来提升时间复杂度
        if (size - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return delElement;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组越界，超出已有元素范围");
        }
        return array[index];
    }

    public void set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组越界，超出已有元素范围");
        }
        array[index] = element;
    }

    private void expansionArray() {
        // 进行两倍扩容
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void outArray() {
        if (array == null) {
            return;
        }
        for (int element : array) {
            LogUtils.d(TAG, "数组元素:" + element);
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getSize() {
        return size;
    }
}
