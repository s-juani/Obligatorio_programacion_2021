package entities;

public class CauseOfDeath {
    private String name;

    public CauseOfDeath(String name) {
        this.name = name;
    }

    /*
    public boolean equals(Object o){
        if (o == this){
            return true;
        } else if (!(o instanceof CauseOfDeath)){
            return false;
        }
        CauseOfDeath c = (CauseOfDeath) o;
        return c.getName().equals(this.name);
    }
    */

    public boolean equals(String str){
        return this.name.equalsIgnoreCase(str);
    }

    public String getName() {
        return name;
    }
}
