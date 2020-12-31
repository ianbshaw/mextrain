package gamengine;

/*Class representing a single domino*/
public class Domino implements DominoInterface {

    /*reference variables*/
    private int left, right;
    private boolean isDouble = false;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
        if (left == right)
            isDouble = true;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return this.right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public int getValue() {
        return (this.left + this.right);
    }

    /*CLI object*/
    @Override
    public String toString() {
        return "[ " + this.left +
                " | " + this.right + " ]";
    }
}
