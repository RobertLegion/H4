package Homework4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.*;

public class GradeService {

    public String[][] calculateAverage(String[][] data) {

        return Arrays.stream(Optional.ofNullable(data).orElse(new String[][]{}))
                .collect(collectingAndThen(
                        groupingBy(kv -> kv[0], mapping(kv -> kv[1], averagingDouble(Double::parseDouble))),
                        map -> map.entrySet().stream().sorted(comparingByKey())
                                .map(student -> Stream.of(student.getKey(), new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.CANADA)).format(student.getValue()))
                                        .toArray(String[]::new)
                                )
                                .toArray(String[][]::new)
                        )
                );

    }

}