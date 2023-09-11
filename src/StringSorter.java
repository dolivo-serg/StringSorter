import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * <p>
 * Implement in single class.
 */
public class StringSorter {
    /**
     * The StringSorter class defines the contract for sorting strings based on
     * alphabetical order while skipping words that start with a specific prefix.
     * <p>
     * Example:
     * Input: ["apple", "banana", "grape", "avocado", "cherry"], Exception prefix: "a"
     * Output: ["banana", "cherry", "grape", "avocado", "apple"]
     * <p>
     * Here, "banana", "cherry", and "grape" are sorted in alphabetical order
     * whereas "avocado" and "apple" (that start with 'a') are sorted in reverse alphabetical order
     * at the end of the list.
     */
    public List<String> sortStrings(List<String> unsortedStrings, String exceptionChar) {
        Map<Boolean, List<String>> map = unsortedStrings.stream()
                .collect(Collectors.partitioningBy(str -> !str.startsWith(exceptionChar)));

        map.get(true).sort(Comparator.naturalOrder());
        map.get(false).sort(Comparator.reverseOrder());

        return Stream.of(map.get(true).stream(), map.get(false).stream())
                .flatMap(Function.identity())
                .collect(Collectors.toList());
    }
}