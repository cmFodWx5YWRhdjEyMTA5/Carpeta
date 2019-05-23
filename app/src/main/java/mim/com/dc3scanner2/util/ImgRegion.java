package mim.com.dc3scanner2.util;

public class ImgRegion {
    private int x1, y1, x2, y2;
    private int row, collumn;

    public ImgRegion(int x1, int y1, int x2, int y2, int row, int collumn) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.row = row;
        this.collumn = collumn;
    }

    public ImgRegion findRegion(int row, int collumn) {
        if (this.row == row && this.collumn == collumn) {
            return this;
        } else {
            return null;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCollumn() {
        return collumn;
    }

    public void setCollumn(int collumn) {
        this.collumn = collumn;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
