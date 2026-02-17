package com.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {

    private String environment = "prod";

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @Nested
    class IsDietRecommendedTests {
        @ParameterizedTest(name = "weight={0}, height={1}")
//    @ValueSource(doubles = {89.0, 95.0, 110.0})
//    @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        @CsvFileSource(resources = "/diet-input-data.csv", numLinesToSkip = 1)
        public void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
            // Given
            double weight = coderWeight;
            double height = coderHeight;

            // When
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // Then
            assertTrue(recommended);
        }

        @Test
        public void should_ReturnFalse_When_DietNotRecommended() {
            // Given
            double weight = 89.0;
            double height = 1.92;

            // When
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // Then
            assertFalse(recommended);
        }

        @Test
//        @Disabled
        @DisabledOnOs(OS.LINUX)
        public void should_ThrowArithmeticException_When_HeightZero() {
            // Given
            double weight = 50.0;
            double height = 0;

            // When
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            // Then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    class FindCoderWithWorstBMITests {
        @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
        public void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));

            // When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight())
            );
        }

        @Test
        @DisplayName("should return null with worst BMI when coder list empty")
        public void should_ReturnNullWithWorstBMI_When_CoderListEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>();

            // When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertNull(coderWorstBMI);
        }
    }

    @Nested
    class GetBMIScoresTests {
        @Test
        public void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected = {18.52, 29.59, 19.53};

            // When
            double[] bmiScores = BMICalculator.getBMIScores(coders);

            // Then
            assertArrayEquals(expected, bmiScores);
        }

        @Test
        public void should_ReturnCoderWithWorstBMIIn500Ms_WhenCoderListHas10000Elements() {
            // Given
            Assumptions.assumeTrue(BMICalculatorTest.this.environment.equals("prod"));

            List<Coder> coders = new ArrayList<>();
            for (int i=0; i<1000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            // When
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertTimeout(Duration.ofMillis(500), executable);
        }
    }
}