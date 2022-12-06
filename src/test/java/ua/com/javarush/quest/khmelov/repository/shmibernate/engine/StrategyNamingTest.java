package ua.com.javarush.quest.khmelov.repository.shmibernate.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

class StrategyNamingTest {

    @SuppressWarnings("unused")
    private static class TestEntityClass {
        private final String login = "login";
        private final Object companyId = "company_id";
        protected String HelloWorld = "hello_world";
        public String UPPER = "upper";
        String First = "first";
        String FirstAndT = "first_and_t";
        @Column(name = "test_id")
        Object annoField = "test_id";
    }

    public static Stream<Arguments> get() {
        TestEntityClass testEntityClass = new TestEntityClass();
        return Arrays.stream(TestEntityClass.class.getDeclaredFields()).map(
                f -> {
                    try {
                        return Arguments.of(f, f.get(testEntityClass));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @ParameterizedTest
    @MethodSource(value = "get")
    void getFieldName(Field in, String expected) {
        String actual = StrategyNaming.getFieldName(in);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getTableName() {
        String actual = StrategyNaming.getTableName(TestEntityClass.class);
        String expected = "t_test_entity_class";
        Assertions.assertEquals(expected, actual);

        @Table(name = "customName")
        class AnnoClass {
        }
        String actual2 = StrategyNaming.getTableName(AnnoClass.class);
        String expected2 = "customName";
        Assertions.assertEquals(expected2, actual2);
    }
}