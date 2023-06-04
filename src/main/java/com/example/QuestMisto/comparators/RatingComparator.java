package com.example.QuestMisto.comparators;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.Rating;

import java.util.Comparator;
import java.util.OptionalDouble;

public class RatingComparator implements Comparator<Quest> {
    @Override
    public int compare(Quest o1, Quest o2) {
       double avgRatingO1 = o1.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(Double.NaN);
        double avgRatingO2 = o2.getRatings().stream().mapToDouble(Rating::getRating).average().orElse(Double.NaN);

       /* float countO1 = o1.getRatings().size();
        float countO2 = o2.getRatings().size();
        int sumO1 = o1.getRatings().stream().mapToInt(Rating::getRating).sum();
        int sumO2 = o2.getRatings().stream().mapToInt(Rating::getRating).sum();
        float avgRatingO1 = sumO1 / countO1;
        float avgRatingO2 = sumO2 / countO2;*/
        return Double.compare(avgRatingO2, avgRatingO1);
    }
}
