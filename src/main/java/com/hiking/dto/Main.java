package com.hiking.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            line=line.trim();
            String[] input=line.split(" ");
            Stack stack=new Stack();
            stack.retrieveAlternates(input);
            //System.out.println(line);
        }
    }





    public static class Stack<E> {

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

        private String retrieveAlternates(String[] input) {
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

}
