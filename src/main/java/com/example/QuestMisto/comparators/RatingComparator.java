package com.example.QuestMisto.comparators;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.Rating;

import java.util.Comparator;

public class RatingComparator implements Comparator<Quest> {
    @Override
    public int compare(Quest o1, Quest o2) {
        float countO1 = o1.getRatings().size();
        float countO2 = o2.getRatings().size();
        int sumO1 = o1.getRatings().stream().mapToInt(Rating::getRating).sum();
        int sumO2 = o2.getRatings().stream().mapToInt(Rating::getRating).sum();
        float avgRatingO1 = sumO1 / countO1;
        float avgRatingO2 = sumO2 / countO2;
        return Float.compare(avgRatingO2, avgRatingO1);
    }
}
