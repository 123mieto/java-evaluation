package evaluation.java.nine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvenienceImprovements {

    @Test
    public void newWayToCreateLists() {
        List<Character> listInNewWay = List.of('a', 'b', 'c', 'd');
        List<Character> listInOldWay = new ArrayList<>();
        listInOldWay.add('a');
        listInOldWay.add('b');
        listInOldWay.add('c');
        listInOldWay.add('d');

        for (int i = 0; i < listInNewWay.size(); i++) {
            assertEquals(listInOldWay.get(i), listInNewWay.get(i));
        }
    }

    @Test
    public void newWayToCreateMaps() {
        Map<String, Integer> mapInNewWay = Map.of("first", 1, "second", 2, "third", 3);
        Map<String, Integer> mapInOldWay = new HashMap<>();
        mapInOldWay.put("first", 1);
        mapInOldWay.put("second", 2);
        mapInOldWay.put("third", 3);

        mapInNewWay.keySet().forEach(key -> {
                    assertEquals(mapInOldWay.get(key), mapInNewWay.get(key));
                }
        );
    }

    @Test
    public void privateMethodsInInterfaces() {
        StringAppender appender = StringAppender::result;

        assertEquals("Original_string_some_part_appended", appender.callResult("Original_string"));
    }

    @Test
    public void tryWithResourcesExtractedToVariable() throws Throwable {
        FileReader fileReader = new FileReader(getClass().getResource("/test.txt").getFile());
        try (fileReader) {
            assertEquals('g', fileReader.read());
        }
        Assertions.assertThrows(IOException.class, fileReader::ready, "Stream is closed");
    }

    @Test
    public void dropAndTakeWhileStreamApiImprovements() {
        int[] takeWhileOutput = IntStream.of(1, 1, 1, 1, 9, 2, 2, 2, 3, 3, 3).takeWhile(i -> i < 5).toArray();
        int[] dropWhileOutput = IntStream.of(1, 1, 1, 1, 9, 2, 2, 2, 3, 3, 3).dropWhile(i -> i < 5).toArray();

        assertArrayEquals(new int[]{1, 1, 1, 1}, takeWhileOutput);
        assertArrayEquals(new int[]{9, 2, 2, 2, 3, 3, 3}, dropWhileOutput);
    }

    @Test
    public void streamOptional() {
        List<Integer> declaredList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Optional> integers = Stream.of(Optional.of(declaredList));

        assertArrayEquals(declaredList.toArray(), integers.flatMap(Optional::stream).toArray());
    }

    public interface StringAppender {
        static String result(String originalString) {
            return appendString(originalString);
        }

        private static String appendString(String originalString) {
            return originalString + "_some_part_appended";
        }

        String callResult(String originalString);
    }
}
