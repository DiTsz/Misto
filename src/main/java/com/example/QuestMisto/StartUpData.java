package com.example.QuestMisto;

import com.example.QuestMisto.models.Rating;
import com.example.QuestMisto.models.enums.Difficulty;
import com.example.QuestMisto.services.CityService;
import com.example.QuestMisto.services.QuestService;
import com.example.QuestMisto.models.City;
import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.models.enums.CityName;
import com.example.QuestMisto.models.enums.QuestType;
import com.example.QuestMisto.models.enums.TypeReward;
import com.example.QuestMisto.services.RatingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StartUpData implements CommandLineRunner {
    private final CityService cityService;
    private final QuestService questService;
    private final RatingService ratingService;

    public StartUpData(CityService cityService, QuestService questService, RatingService ratingService) {
        this.cityService = cityService;
        this.questService = questService;
        this.ratingService = ratingService;
    }

    @Override
    public void run(String... args) throws Exception {
        exampleCities();
        exampleQuests();
        exampleRatings();
        //test();
    }

    private void exampleCities() {
        City city1 = new City();
        City city2 = new City();
        city1.setCityName(CityName.KHARKIV);
        city1.setDescription("Simple description");
        city1.setPictureUrl("https://media.istockphoto.com/id/1342593154/ru/%D1%84%D0%BE%D1%82%D0%BE/" +
                "%D0%B2%D0%B8%D0%B4-%D0%BD%D0%B0-%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-%D1%85%D0%B0%D1%80%D1%" +
                "8C%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D1%81%D0%BA%D0%" +
                "BE%D0%B3%D0%BE-%D1%81%D0%BE%D0%B2%D0%B5%D1%82%D0%B0-%D1%81-%D0%BF%D0%BB%D0%BE%D1%89%D0%B0%D0%B4%D0%" +
                "B8-%D1%81%D0%B2%D0%BE%D0%B1%D0%BE%D0%B4%D1%8B.jpg?s=612x612&w=0&k=20&c=xaqf52NyDcDcb02Q06KAhE" +
                "tkejYpxatBVAL7zzJKwHk=");

        city2.setCityName(CityName.KYIV);
        city2.setDescription("Simple description");
        city2.setPictureUrl("https://st2.depositphotos.com/1000128/7504/i/450/depositphotos_75041187" +
                "-stock-photo-evening-aerial-scenery-of-kyiv.jpg");

        cityService.save(city1);
        cityService.save(city2);
    }

    private void exampleQuests() {
        Quest quest1 = new Quest();
        Quest quest2 = new Quest();
        Quest quest3 = new Quest();
        Quest quest4 = new Quest();
        final City kharkiv = cityService.getByName(CityName.KHARKIV.name());
        final City kyiv = cityService.getByName(CityName.KYIV.name());
        quest1.setName("Cymska quest1");
        quest1.setDescription("Quest description");
        quest1.setQuestType(QuestType.GASTRO);
        quest1.setCity(kharkiv);
        quest1.setDifficulty(Difficulty.MEDIUM);
        quest1.setTypeReward(TypeReward.PROMOCODE);
        quest1.setReward("qeqf34645yeg");
        quest1.setNumOfSteps(12);
        quest1.setDistance(1200f);
        quest1.setMinDuration(90);
        quest1.setMaxDuration(120);
        quest1.setPicture_url("https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg");

        quest3.setName("Cymska quest2");
        quest3.setDescription("Quest description");
        quest3.setQuestType(QuestType.GASTRO);
        quest3.setCity(kharkiv);
        quest3.setDifficulty(Difficulty.MEDIUM);
        quest3.setTypeReward(TypeReward.PROMOCODE);
        quest3.setReward("qeqf34645yeg");
        quest3.setNumOfSteps(12);
        quest3.setDistance(1200f);
        quest3.setMinDuration(90);
        quest3.setMaxDuration(120);
        quest3.setPicture_url("https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg");

        quest4.setName("Cymska quest3");
        quest4.setDescription("Quest description");
        quest4.setQuestType(QuestType.GASTRO);
        quest4.setCity(kharkiv);
        quest4.setDifficulty(Difficulty.MEDIUM);
        quest4.setTypeReward(TypeReward.PROMOCODE);
        quest4.setReward("qeqf34645yeg");
        quest4.setNumOfSteps(12);
        quest4.setDistance(1200f);
        quest4.setMinDuration(90);
        quest4.setMaxDuration(120);
        quest4.setPicture_url("https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg");

        quest2.setName("Kyiv quest");
        quest2.setDescription("Quest description");
        quest2.setQuestType(QuestType.EDUCATION);
        quest2.setCity(kyiv);
        quest2.setDifficulty(Difficulty.EASY);
        quest2.setTypeReward(TypeReward.PROMOCODE);
        quest2.setReward("qeqf34645yeg");
        quest2.setNumOfSteps(12);
        quest2.setDistance(1200f);
        quest2.setMinDuration(90);
        quest2.setMaxDuration(120);
        quest2.setPicture_url("https://st2.depositphotos.com/1000128/7504/i/450/depositphotos_75041187" +
                "-stock-photo-evening-aerial-scenery-of-kyiv.jpg");
        questService.save(quest1);
        questService.save(quest2);
        questService.save(quest3);
        questService.save(quest4);

    }

    public void exampleRatings() {
        Quest quest1 = questService.getByName("Cymska quest1");
        Quest quest2 = questService.getByName("Cymska quest2");
        Quest quest3 = questService.getByName("Cymska quest3");
        Rating rating1 = new Rating(quest1, 25);
        Rating rating2 = new Rating(quest1, 30);
        Rating rating3 = new Rating(quest1, 36);
        Rating rating4 = new Rating(quest2, 90);
        Rating rating5 = new Rating(quest2, 97);
        Rating rating6 = new Rating(quest2, 95);
        Rating rating7 = new Rating(quest3, 60);
        Rating rating8 = new Rating(quest3, 78);
        Rating rating9 = new Rating(quest3, 66);
        ratingService.save(rating1);
        ratingService.save(rating2);
        ratingService.save(rating3);
        ratingService.save(rating4);
        ratingService.save(rating5);
        ratingService.save(rating6);
        ratingService.save(rating7);
        ratingService.save(rating8);
        ratingService.save(rating9);
    }

    public void test() {
        System.out.println(questService.getByCityNameSortedByRating(CityName.KHARKIV));
    }

}
