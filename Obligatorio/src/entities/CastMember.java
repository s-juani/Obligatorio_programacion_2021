package entities;

import java.util.Date;

public class CastMember {
    private final String imdbNameId;
    private final String name;
    private final String birthName;
    private final int height;
    private final String bio;
    private final Date birthDate;
    private final String birthState;
    private final String birthCountry;
    private final String birthCity;
    private final Date deathDate;
    private final String deathState;
    private final String deathCountry;
    private final String deathCity;
    private final CauseOfDeath[] reasonOfDeath;  // FIXME implementar un checkeo de si ya se registro dicha reasonOfDeath
    private final String spousesString;
    private final int spouses;
    private final int divorces;
    private final int spousesWithChildren;
    private final int children;

    public CastMember(String imdbNameId, String name, String birthName, int height,
                      String bio, Date birthDate, String birthState, String birthCountry,
                      String birthCity, Date deathDate, String deathState, String deathCountry,
                      String deathCity, CauseOfDeath[] reasonOfDeath, String spousesString,
                      int spouses, int divorces, int spousesWithChildren, int children) {
        this.imdbNameId = imdbNameId;       //0
        this.name = name;                   //1
        this.birthName = birthName;         //2
        this.height = height;               //3
        this.bio = bio;                     //4
        this.birthDate = birthDate;         //6
        this.birthState = birthState;       //7
        this.birthCountry = birthCountry;   //7
        this.birthCity = birthCity;         //7
        this.deathDate = deathDate;         //9
        this.deathState = deathState;       //10
        this.deathCountry = deathCountry;   //10
        this.deathCity = deathCity;         //10
        this.reasonOfDeath = reasonOfDeath; //11
        this.spousesString = spousesString; //12
        this.spouses = spouses;             //13
        this.divorces = divorces;           //14
        this.spousesWithChildren = spousesWithChildren; //15
        this.children = children;           //16
    }

    public String getImdbNameId() {
        return imdbNameId;
    }
    public String getName() {
        return name;
    }
    public String getBirthName() {
        return birthName;
    }
    public int getHeight() {
        return height;
    }
    public String getBio() {
        return bio;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getBirthState() {
        return birthState;
    }
    public String getBirthCountry() {
        return birthCountry;
    }
    public String getBirthCity() {
        return birthCity;
    }
    public Date getDeathDate() {
        return deathDate;
    }
    public String getDeathState() {
        return deathState;
    }
    public String getDeathCountry() {
        return deathCountry;
    }
    public String getDeathCity() {
        return deathCity;
    }
    public CauseOfDeath[] getReasonOfDeath() {
        return reasonOfDeath;
    }
    public String getSpousesString() {
        return spousesString;
    }
    public int getSpouses() {
        return spouses;
    }
    public int getDivorces() {
        return divorces;
    }
    public int getSpousesWithChildren() {
        return spousesWithChildren;
    }
    public int getChildren() {
        return children;
    }

    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        else if (!(o instanceof CastMember)){
            return false;
        }
        CastMember c = (CastMember) o;
        return c.getImdbNameId().equals(this.imdbNameId);
    }

    public int hashCode(){
        return Integer.parseInt(imdbNameId.substring(2));
    }


}
