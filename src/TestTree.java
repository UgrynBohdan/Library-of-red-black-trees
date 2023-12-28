public class TestTree implements Comparable<TestTree> {
    public int x;
    public int y;

    public TestTree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TestTree{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(TestTree o) {
        return Integer.compare(this.x * this.y, o.x * o.y);
    }
}
