package org.zhupanovdm;

import org.junit.jupiter.api.Test;

class GeneratorsTest {

    @Test
    public void testBasic() {
        // TODO: to design tests
        Generators.combination("123", System.out::println);
        Generators.shuffle("123", System.out::println);
    }

}