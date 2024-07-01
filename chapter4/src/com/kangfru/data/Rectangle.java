package com.kangfru.data;

/**
 * 이 클래스는 코드 중복을 야기할 가능성이 높다
 * 어떤 클래스에서 right와 bottom의 크기를 변경시키는 코드가 있다고 치자,
 * 해당 코드를 제외하고도 다른 코드에서 크기를 변경시켜야 하면 동일한 코드들이 호출된다.
 * 두 번쨰는 변경에 취약하다.
 * right 와 bottom 대신 length 와 height로 표현을 바꾼다고 가정하면 모든 getter 와 setter를 변경해야하며
 * 이를 이용하는 모든 코드에 영향을 미친다.
 * 두 방법을 해결하는 것이 바로 캡슐화다. 맨 아래의 enlarge 메소드 처럼
 * rectangle 클래스가 스스로 자신의 크기를 증가시키도록 책임을 이동시킨 것이다.
 */
public class Rectangle {

    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
//
//    public int getLeft() {
//        return left;
//    }
//
//    public void setLeft(int left) {
//        this.left = left;
//    }
//
//    public int getTop() {
//        return top;
//    }
//
//    public void setTop(int top) {
//        this.top = top;
//    }
//
//    public int getRight() {
//        return right;
//    }
//
//    public void setRight(int right) {
//        this.right = right;
//    }
//
//    public int getBottom() {
//        return bottom;
//    }
//
//    public void setBottom(int bottom) {
//        this.bottom = bottom;
//    }

    public void enlarge(int multiple) {
        right *= multiple;
        bottom *= multiple;
    }
}
