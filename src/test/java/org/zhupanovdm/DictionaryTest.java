package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DictionaryTest {

    @Test
    public void testBasic() {

        Dictionary<String, String> dictionary = new Dictionary<>();
        for (int i = 0; i < 100000; i++)
            dictionary.put("key-" + i, "value-" + i);

        for (int i = 0; i < 100000; i++)
            assertEquals("value-" + i, dictionary.get("key-" + i));

    }

}