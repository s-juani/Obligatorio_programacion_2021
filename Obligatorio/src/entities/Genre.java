package entities;

public class Genre {

    private final String name;

    public Genre(String name) {
        this.name = name;
    }

    public boolean equals(Object o){
        if (o == this){
            return true;
        } else if (o instanceof Genre g){
            return name.equals(g.getName());
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
