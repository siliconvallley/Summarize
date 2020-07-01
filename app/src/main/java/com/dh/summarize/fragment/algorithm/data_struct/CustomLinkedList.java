package com.dh.summarize.fragment.algorithm.data_struct;

public class CustomLinkedList {
    // 头结点指针
    private Node head;
    // 尾节点指针
    private Node last;
    // 链表长度
    private int size;

    public void insert(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("链表越界，超出链表实际范围");
        }
        Node insertNode = new Node(element);
        if (size == 0) {
            // 链表长度为0，空链表
            head = insertNode;
            last = insertNode;
        } else if (index == 0) {
            // 插入头部
            insertNode.next = head;
            head = insertNode;
        } else if (index == size) {
            // 插入尾部
            last.next = insertNode;
            last = insertNode;
        } else {
            // 插入中间
            // 获取前一个节点
            Node preNode = get(index - 1);
            Node nextNode = preNode.next;
            preNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size++;
    }

    public Node remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("链表越界，超出链表实际范围");
        }
        Node removeNode = null;
        if (index == 0) {
            // 删除头部
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            // 删除尾节点
            Node preNode = get(index - 1);
            removeNode = preNode.next;
            preNode.next = null;
            last = preNode;
        } else {
            // 删除中间节点
            Node preNode = get(index - 1);
            Node nextNode = preNode.next.next;
            removeNode = preNode.next;
            preNode.next = nextNode;
        }
        size--;
        return removeNode;
    }

    /**
     * 查询
     *
     * @param index
     * @return
     */
    public Node get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("链表越界，超出链表实际范围");
        }
        Node tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    public String outPut() {
        StringBuilder builder = new StringBuilder();
        Node tempNode = head;
        while (tempNode != null) {
            builder.append(tempNode.data).append(" ");
            tempNode = tempNode.next;
        }
        return builder.toString();
    }

    public Node getNode() {
        return head;
    }

    public String outNode(Node node) {
        StringBuilder builder = new StringBuilder();
        Node tempNode = node;
        while (tempNode != null) {
            builder.append(tempNode.data).append(" ");
            tempNode = tempNode.next;
        }
        return builder.toString();
    }
}
