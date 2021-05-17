package com.hiking.dto;

/**
 * @developer -- ilkercolakoglu
 */
public class Stack<E> {

    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(E element) {
        Node temp = top;
        top = new Node(element);
        top.setNodeBelow(temp);
    }

    public E pop() {
        if (top == null) {
            // Do what you want to do here. You can throw an exception, or you
            // can just return null.
            return null;
        }
        E result = top.getValue();
        top = top.getNodeBelow();
        return result;
    }

    class Node {

        private E value;
        private Node below;

        public Node(E value) {
            this.value = value;
            this.below = null;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNodeBelow() {
            return below;
        }

        public void setNodeBelow(Node below) {
            this.below = below;
        }

    }

    private static String retrieveAlternates(String[] input) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (String s : input) {
            stack.push(Integer.parseInt(s));
        }

        for (int i = 0; i < input.length; i++) {
            if ((i & 1) == 0) {
                result.append(' ').append(stack.pop());
            } else {
                stack.pop();
            }
        }

        return result.substring(1);
    }

}
