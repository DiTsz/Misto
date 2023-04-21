package com.example.QuestMisto.comparators;

import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.models.Rating;

import java.util.Comparator;
import java.util.stream.Collectors;

public class RatingComparator implements Comparator<Quest> {
    @Override
    public int compare(Quest o1, Quest o2) {
        float countO1 = o1.getRatings().size();
        float countO2 = o2.getRatings().size();
        int sumO1 = o1.getRatings().stream().mapToInt(Rating::getRating).sum();
        int sumO2 = o2.getRatings().stream().mapToInt(Rating::getRating).sum();
        float avgRatingO1 = sumO1 / countO1;
        float avgRatingO2 = sumO2 / countO2;
        if (avgRatingO1 > avgRatingO2) {
            return -1;
        } else if (avgRatingO1 < avgRatingO2) {
            return 1;
        } else
            return 0;
    }
}
