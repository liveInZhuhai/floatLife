package dataservice.entity;

/**
 * Created by HK on 2016/12/12.
 */
public class Place {
    private int id;
    private String placeName;
    private int random_people;
    private int random_value;
    private String things;
    private int limit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getRandomValue() { return random_value; }

    public void setRandomValue(int random_value) {
        this.random_value = random_value;
    }

    public int getRandomPeople() {
        return random_people;
    }

    public void setRandomPeople(int random_people) {
        this.random_people = random_people;
    }

    public String getThings() { return things; }

    public void setThings(String things) { this.things = things; }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) { this.limit = limit; }
}
