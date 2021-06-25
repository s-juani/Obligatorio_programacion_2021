package entities;

public class Genre {

    private final String name;

    public Genre(String name) {
        this.name = name;
    }

    public boolean equals(Object o){
        if (o == this){
            return true;
        } else if (!(o instanceof Genre)){
            return false;
        }
        Genre c = (Genre) o;
        return c.getName().equals(this.name);
    }

    public String getName() {
        return name;
    }
}
