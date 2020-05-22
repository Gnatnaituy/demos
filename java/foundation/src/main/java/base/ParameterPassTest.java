package base;

public class ParameterPassTest {

    public static void main(String[] args) {
        Foo f = new Foo("F");
        changeReference(f);
        modifyReference(f);

        Foo d = new Foo("D");
        Foo x = d;
        d.setAttr("Z");
        System.out.println(x.getAttr());
    }

    private static void changeReference(Foo a) {
        Foo b = new Foo("B");
        a = b;
    }

    private static void modifyReference(Foo c) {
        c.setAttr("C");
    }
}

class Foo {
    private String attr;

    Foo(String attr) {
        this.attr = attr;
    }

    public String getAttr() {
        return attr;
    }

    void setAttr(String attr) {
        this.attr = attr;
    }
}
