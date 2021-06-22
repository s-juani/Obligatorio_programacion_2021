package entities;

public class MovieRating {
    private Float weightedAverage;
    private Integer totalVotes;
    private Float meanVote;
    private Float medianVote;
    private Integer[] votesRating;

    private Rating allGendersRating;
    private Rating malesRating;
    private Rating femalesRating;
    private Rating top1000Rating;
    private Rating usRating;
    private Rating nonUsRating;


    public MovieRating() {}

    public Float getWeightedAverage() {
        return weightedAverage;
    }

    public Integer[] getVotesRating(){
        return this.votesRating;
    }

    public void setVotesRating(Integer[] votesRating){
        this.votesRating = votesRating;
    }
    public void setWeightedAverage(Float weightedAverage) {
        this.weightedAverage = weightedAverage;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Float getMeanVote() {
        return meanVote;
    }

    public void setMeanVote(Float meanVote) {
        this.meanVote = meanVote;
    }

    public Float getMedianVote() {
        return medianVote;
    }

    public void setMedianVote(Float medianVote) {
        this.medianVote = medianVote;
    }

    public Rating getAllGendersRating() {
        return allGendersRating;
    }

    public void setAllGendersRating(Rating allGendersRating) {
        this.allGendersRating = allGendersRating;
    }

    public Rating getMalesRating() {
        return malesRating;
    }

    public void setMalesRating(Rating malesRating) {
        this.malesRating = malesRating;
    }

    public Rating getFemalesRating() {
        return femalesRating;
    }

    public void setFemalesRating(Rating femalesRating) {
        this.femalesRating = femalesRating;
    }

    public Rating getTop1000Rating() {
        return top1000Rating;
    }

    public void setTop1000Rating(Rating top1000Rating) {
        this.top1000Rating = top1000Rating;
    }

    public Rating getUsRating() {
        return usRating;
    }

    public void setUsRating(Rating usRating) {
        this.usRating = usRating;
    }

    public Rating getNonUsRating() {
        return nonUsRating;
    }

    public void setNonUsRating(Rating nonUsRating) {
        this.nonUsRating = nonUsRating;
    }
}
/**
 *    imdb_title_id,
 *    weighted_average_vote,
 *    total_votes,
 *    mean_vote,
 *    median_vote,
 *    votes_10,
 *    votes_9,
 *    votes_8,
 *    votes_7,
 *    votes_6,
 *    votes_5,
 *    votes_4,
 *    votes_3,
 *    votes_2,
 *    votes_1,
 *    allgenders_0age_avg_vote,
 *    allgenders_0age_votes,
 *    allgenders_18age_avg_vote,
 *    allgenders_18age_votes,
 *    allgenders_30age_avg_vote,
 *    allgenders_30age_votes,
 *    allgenders_45age_avg_vote,
 *    allgenders_45age_votes,
 *    males_allages_avg_vote,
 *    males_allages_votes,
 *    males_0age_avg_vote,
 *    males_0age_votes,
 *    males_18age_avg_vote,
 *    males_18age_votes,
 *    males_30age_avg_vote,
 *    males_30age_votes,
 *    males_45age_avg_vote,
 *    males_45age_votes,
 *    females_allages_avg_vote,
 *    females_allages_votes,
 *    females_0age_avg_vote,
 *    females_0age_votes,
 *    females_18age_avg_vote,
 *    females_18age_votes,
 *    females_30age_avg_vote,
 *    females_30age_votes,
 *    females_45age_avg_vote,
 *    females_45age_votes,
 *    top1000_voters_rating,
 *    top1000_voters_votes,
 *    us_voters_rating,
 *    us_voters_votes,
 *    non_us_voters_rating,
 *    non_us_voters_votes
 */