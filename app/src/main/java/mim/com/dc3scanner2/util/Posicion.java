package mim.com.dc3scanner2.util;

public class Posicion {
    private int xPos;
    private int yPos;

    public Posicion(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                '}';
    }
}
