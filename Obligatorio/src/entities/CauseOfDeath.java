package entities;

public class CauseOfDeath {
    private String name;

    public CauseOfDeath(String name) {
        this.name = name;
    }

    public boolean equals(String s){
        return (this.name.equals(s));
    }

}
