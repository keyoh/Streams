import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;


public class Streams<T> {

    private List<T> list;

    public Streams(List<T> list) {
        this.list = list;
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<T>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> list = new ArrayList<>();
        for (T t : this.list) {
            if (predicate.test(t)) {
                list.add(t);
            }
        }
        return new Streams<>(list);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> function) {
        List<R> list = new ArrayList<>();
        for (T t : this.list) {
            list.add(function.apply(t));
        }
        return new Streams<>(list);
    }

    public <K,V> Map<K,V> toMap(Function<? super T, ? extends K> k, Function<? super T,? extends V> v) {
        Map<K,V> map = new HashMap<>();
        for (T t : this.list) {
            map.put(k.apply(t), v.apply(t));
        }
        return map;
    }
}
