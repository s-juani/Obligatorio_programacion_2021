package entities;

import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;

import java.util.Date;

public class CastMember {

    private static MyHash<Integer,CauseOfDeath> causeOfDeathList = new MyClosedHashImpl<>(30,0.8);


    private final String imdbNameId;
    private final String name;
    private final String birthName;
    private final Integer height;
    private final String bio;
    private final Date birthDate;
    private final String birthCity;
    private final String birthState;
    private final String birthCountry;
    private final Date deathDate;
    private final String deathCity;
    private final String deathState;
    private final String deathCountry;
    private final CauseOfDeath[] reasonOfDeath;  // FIXME implementar un checkeo de si ya se registro dicha reasonOfDeath
    private final String spousesString;
    private final Integer spouses;
    private final Integer divorces;
    private final Integer spousesWithChildren;
    private final Integer children;

    public CastMember(String imdbNameId, String name, String birthName, Integer height, String bio, Date birthDate, String birthCity, String birthState, String birthCountry, Date deathDate, String deathCity, String deathState, String deathCountry, String[] reasonOfDeath, String spousesString, Integer spouses, Integer divorces, Integer spousesWithChildren, Integer children) {
        this.imdbNameId = imdbNameId;       //0
        this.name = name;                   //1
        this.birthName = birthName;         //2
        this.height = height;               //3
        this.bio = bio;                     //4
        this.birthDate = birthDate;         //6
        this.birthCity = birthCity;         //7
        this.birthState = birthState;       //7
        this.birthCountry = birthCountry;   //7
        this.deathDate = deathDate;         //9
        this.deathCity = deathCity;         //10
        this.deathState = deathState;       //10
        this.deathCountry = deathCountry;   //10
        this.reasonOfDeath = addReasonOfDeath(reasonOfDeath); //11
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
    public Integer getHeight() {
        return height;
    }
    public String getBio() {
        return bio;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getBirthCity() {
        return birthCity;
    }
    public String getBirthState() {
        return birthState;
    }
    public String getBirthCountry() {
        return birthCountry;
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
    public Integer getSpouses() {
        return spouses;
    }
    public Integer getDivorces() {
        return divorces;
    }
    public Integer getSpousesWithChildren() {
        return spousesWithChildren;
    }
    public Integer getChildren() {
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

    private CauseOfDeath[] addReasonOfDeath(String[] reasons){
        CauseOfDeath temp;
        CauseOfDeath[] causes = new CauseOfDeath[reasons.length];
        int i = 0;
        for (String reason : reasons) {
            temp = new CauseOfDeath(reason);
            CauseOfDeath exists = causeOfDeathList.get(temp.hashCode());
            if (exists == null){
                causeOfDeathList.put(temp.hashCode(),temp);
            } else {
                 temp = exists;
            }
            causes[i] = temp;
            i++;
        }



        /*String[] reasons = row[11].split(",");

                    CauseOfDeath[] causesOfDeath = new CauseOfDeath[reasons.length];
                    for (int i=0; i<causes.length; i++) {
                        CauseOfDeath causeOfDeath = new CauseOfDeath(causes[i]);
                        if (!causeOfDeathHash.contains(causeOfDeath.hashCode())){
                            causeOfDeathHash.put(causeOfDeath.hashCode(),causeOfDeath);
                        } else {
                            causeOfDeath = causeOfDeathHash.get(causeOfDeath.hashCode());
                        }
                        causesOfDeath[i] = causeOfDeath;
                    }*/
        return causes;
    }


    /*
     * row[0] = imdb_name_id            String
     * row[1] = name                    String
     * row[2] = birth_name              String
     * row[3] = height                  int
     * row[4] = bio                     String
     * row[5] = birth_details        -- IGNORAR ------
     * row[6] = date_of_birth           Date
     * row[7] = place_of_birth          String[birthState, birthCountry, birthCity]
     * row[8] = death_details        -- IGNORAR ------
     * row[9] = date_of_death           Date
     * row[10] = place_of_death         String[deathState, deathCountry, deathCity]
     * row[11] = reason_of_death        CauseOfDeath (name String)
     * row[12] = spouses_string         String
     * row[13] = spouses                int
     * row[14] = divorces               int
     * row[15] = spouses_with_children  int
     * row[16] = children               int
     */


}
