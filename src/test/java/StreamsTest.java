import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static java.util.stream.Collectors.toMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class StreamsTest {
    List<Person> list = new ArrayList<>();

    @Before
    public void persons() throws Exception {
        list.add(new Person("Даша", 20));
        list.add(new Person("Костя", 22));
        list.add(new Person("Игорь", 25));
        list.add(new Person("Ира", 17));
    }


    @Test
    public void testFilter() throws Exception {
        Assert.assertEquals(list.stream().filter(p -> p.getAge() > 20).collect(toMap(Person::getName, Person::getAge)),
                Streams.of(list).filter(p -> p.getAge() > 20).toMap(Person::getName, Person::getAge));
    }

    @Test
    public void testTransform() throws Exception {
        Assert.assertEquals(list.stream().map(p -> new Person(p.getName(), p.getAge() + 30)).collect(toMap(Person::getName, Person::getAge)),
                Streams.of(list).transform(p -> new Person(p.getName(), p.getAge() + 30)).toMap(Person::getName, Person::getAge));
    }

    @Test
    public void testToMap() throws Exception {
        Assert.assertEquals(list.stream().collect(toMap(Person::getName, p -> p.getAge() + 30)),
                Streams.of(list).toMap(Person::getName, p -> p.getAge() + 30));

    }

}