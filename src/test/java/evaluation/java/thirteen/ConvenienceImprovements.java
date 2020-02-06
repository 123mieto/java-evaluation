package evaluation.java.thirteen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvenienceImprovements {

    @Test
    public void textBlockGivesTheSameResultAsOldStringBuilding() {
        String textBlock = """
                Some
                multiline
                String""";
        String oldMultilineString = "Some\nmultiline\nString";

        assertEquals(oldMultilineString, textBlock);
    }

    @Test
    public void formattedStrings() {
        String textBlockToFormat = """
               First value: %s
               Second value: %s""";

        assertEquals(String.format(textBlockToFormat, "'1'", "'2'"), textBlockToFormat.formatted("'1'", "'2'"));
    }

    @Test
    public void enhancedSwitch() {
        assertEquals(oldSwitch(TestEnum.FIRST), newSwitch(TestEnum.FIRST));
        assertEquals(oldSwitch(TestEnum.SECOND), newSwitch(TestEnum.SECOND));
        assertEquals(oldSwitch(TestEnum.THIRD), newSwitch(TestEnum.THIRD));
        assertEquals(oldSwitch(TestEnum.OTHER), newSwitch(TestEnum.OTHER));
    }

    private double newSwitch(TestEnum test) {
        return switch (test) {
            case FIRST -> 1;
            case SECOND -> 2;
            case THIRD -> 3;
            default -> 0;
        };
    }

    private double oldSwitch(TestEnum test) {
        switch(test) {
            case FIRST:
                return 1;
            case SECOND:
                return 2;
            case THIRD:
                return 3;
            default:
                return 0;
        }
    }

    enum TestEnum {
        FIRST,SECOND,THIRD, OTHER
    }
}
