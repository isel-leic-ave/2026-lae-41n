class RectangleJava {
    private final int height;
    private int width;

    public RectangleJava(int height, int width) {
       this.width = width;
       this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }
}