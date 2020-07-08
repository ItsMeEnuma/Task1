package task1.tests;

import org.junit.jupiter.api.Test;
import task1.Formula;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void test1() {
        String data = "1 + 1";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(2,f.execute());
    }

    @Test
    void test2() {
        String data = "1 - 1";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(0,f.execute());
    }

    @Test
    void test3() {
        String data = "2 * 2";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(4,f.execute());
    }

    @Test
    void test4() {
        String data = "4 / 2";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(2,f.execute());
    }

    @Test
    void test5() {
        String data = "5 + 5 / 5 - 5";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(1,f.execute());
    }

    @Test
    void test6() {
        String data = "5 + a";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(10,f.execute(5));
    }

    @Test
    void test7() {
        String data = "a + b";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(100,f.execute(70, 30));
    }

    @Test
    void test8() {
        String data = "a + b + a * b + 100 - 10";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(155,f.execute(5, 10));
    }

    @Test
    void test9() {
        String data = "qwe+9/.3!$+2";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(8,f.execute(3));
    }

    @Test
    void test10() {
        String data = "5 + 5 * a / b";
        Formula f = new Formula();
        f.prepare(data);
        assertEquals(7, f.execute(2, 5));
    }

}