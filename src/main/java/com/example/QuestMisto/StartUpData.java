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
        test();
    }

    private void exampleCities() {
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();
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
        city3.setCityName(CityName.LVIV);
        city3.setDescription("Simple description");
        city3.setPictureUrl("https://st2.depositphotos.com/1000128/7504/i/450/depositphotos_75041187" +
                "-stock-photo-evening-aerial-scenery-of-kyiv.jpg");
        cityService.save(city1);
        cityService.save(city2);
        cityService.save(city3);

    }

    private void exampleQuests() {
        final City kharkiv = cityService.getByName(CityName.KHARKIV.name());
        final City kyiv = cityService.getByName(CityName.KYIV.name());
        final City lviv = cityService.getByName(CityName.LVIV.name());

        Quest quest1 = new Quest("Cymska quest1",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);

        Quest quest2 = new Quest("Lviv quest",
                "Quest description",
                Difficulty.EASY,
                TypeReward.PROMOCODE,
                QuestType.ENTERTAINMENT,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,lviv);
        Quest quest3 = new Quest("Cymska quest2",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);
        Quest quest4 = new Quest("Cymska quest3",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);
        Quest quest5 = new Quest("Kyiv quest",
                "Quest description",
                Difficulty.HARD,
                TypeReward.PROMOCODE,
                QuestType.SCIENCE,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kyiv);
        Quest quest6 = new Quest("Cymska quest4",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);
        Quest quest7 = new Quest("Cymska quest5",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);
        Quest quest8 = new Quest("Cymska quest6",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12,1200f,90,120,kharkiv);

        questService.save(quest1);
        questService.save(quest2);
        questService.save(quest3);
        questService.save(quest4);
        questService.save(quest5);
        questService.save(quest6);
        questService.save(quest7);
        questService.save(quest8);

    }

    public void exampleRatings() {
        Quest quest1 = questService.getByName("Cymska quest1");
        Quest quest2 = questService.getByName("Cymska quest2");
        Quest quest3 = questService.getByName("Cymska quest3");
        Quest quest4 = questService.getByName("Cymska quest4");
        Quest quest5 = questService.getByName("Cymska quest5");
        Quest quest6 = questService.getByName("Cymska quest6");
        Quest quest7 = questService.getByName("Kyiv quest");
        Quest quest8 = questService.getByName("Lviv quest");

        Rating rating1 = new Rating(quest1, 25);
        Rating rating2 = new Rating(quest1, 30);
        Rating rating3 = new Rating(quest1, 36);
        Rating rating4 = new Rating(quest2, 90);
        Rating rating5 = new Rating(quest2, 97);
        Rating rating6 = new Rating(quest2, 95);
        Rating rating7 = new Rating(quest3, 60);
        Rating rating8 = new Rating(quest3, 78);
        Rating rating9 = new Rating(quest3, 66);
        Rating rating10 = new Rating(quest4, 100);
        Rating rating11 = new Rating(quest4, 99);
        Rating rating12 = new Rating(quest5, 8);
        Rating rating13 = new Rating(quest5, 10);
        Rating rating14 = new Rating(quest6, 75);
        Rating rating15 = new Rating(quest6, 81);
        Rating rating16 = new Rating(quest7, 88);
        Rating rating17 = new Rating(quest8, 61);


        ratingService.save(rating1);
        ratingService.save(rating2);
        ratingService.save(rating3);
        ratingService.save(rating4);
        ratingService.save(rating5);
        ratingService.save(rating6);
        ratingService.save(rating7);
        ratingService.save(rating8);
        ratingService.save(rating9);
        ratingService.save(rating10);
        ratingService.save(rating11);
        ratingService.save(rating12);
        ratingService.save(rating13);
        ratingService.save(rating14);
        ratingService.save(rating15);
        ratingService.save(rating16);
        ratingService.save(rating17);


    }

    public void test() {
        //System.out.println(questService.getByCityNameSortedByRating(CityName.KHARKIV));
        System.out.println(questService.getFirst5OrderByRatings());
    }

}
