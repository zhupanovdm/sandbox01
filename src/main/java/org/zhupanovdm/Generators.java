package org.zhupanovdm;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Generators {

    public static void combination(String sequence, Consumer<String> consumer) {
        class Composer implements BiConsumer<String, String> {
            @Override
            public void accept(String heap, String word) {
                consumer.accept(word);
                if (heap.isEmpty())
                    return;
                for (int i = 0; i < heap.length(); i++) {
                    accept(heap.substring(0, i) + heap.substring(i + 1), word + heap.charAt(i));
                }
            }
        }
        new Composer().accept(sequence, "");
    }

    public static void shuffle(String sequence, Consumer<String> consumer) {
        class Composer implements BiConsumer<String, String> {
            @Override
            public void accept(String heap, String word) {
                if (heap.isEmpty()) {
                    consumer.accept(word);
                    return;
                }
                for (int i = 0; i < heap.length(); i++) {
                    accept(heap.substring(0, i) + heap.substring(i + 1), word + heap.charAt(i));
                }
            }
        }
        new Composer().accept(sequence, "");
    }

}
