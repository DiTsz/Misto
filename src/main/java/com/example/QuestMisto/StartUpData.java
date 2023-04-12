package com.example.QuestMisto;

import com.example.QuestMisto.Model.*;
import com.example.QuestMisto.Service.CityService;
import com.example.QuestMisto.Service.QuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class StartUpData implements CommandLineRunner {
    private final CityService cityService;
    private final QuestService questService;
    private static final Logger logger = LoggerFactory.getLogger(StartUpData.class);

    public StartUpData(CityService cityService, QuestService questService) {
        this.cityService = cityService;
        this.questService = questService;
    }

    @Override
    public void run(String... args) throws Exception {
        //exampleCities();
        //exampleQuests();
    }

    private void exampleCities() {
        City city1 = new City();
        City city2 = new City();
        city1.setId(UUID.randomUUID());
        city1.setCityName(CityName.KHARKIV);
        city1.setDescription("Simple description");
        city1.setPictureUrl("https://media.istockphoto.com/id/1342593154/ru/%D1%84%D0%BE%D1%82%D0%BE/" +
                "%D0%B2%D0%B8%D0%B4-%D0%BD%D0%B0-%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-%D1%85%D0%B0%D1%80%D1%" +
                "8C%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE-%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D1%81%D0%BA%D0%" +
                "BE%D0%B3%D0%BE-%D1%81%D0%BE%D0%B2%D0%B5%D1%82%D0%B0-%D1%81-%D0%BF%D0%BB%D0%BE%D1%89%D0%B0%D0%B4%D0%" +
                "B8-%D1%81%D0%B2%D0%BE%D0%B1%D0%BE%D0%B4%D1%8B.jpg?s=612x612&w=0&k=20&c=xaqf52NyDcDcb02Q06KAhE" +
                "tkejYpxatBVAL7zzJKwHk=");
        city2.setId(UUID.randomUUID());
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
        final City kharkiv = cityService.findByCityName(CityName.KHARKIV);
        final City kyiv = cityService.findByCityName(CityName.KYIV);
        quest1.setId(UUID.randomUUID());
        quest1.setName("Cymska quest");
        quest1.setDescription("Quest description");
        quest1.setQuestType(QuestType.GASTRO);
        quest1.setCity(kharkiv);
        quest1.setDifficulty(3);
        quest1.setRating(5);
        quest1.setTypeReward(TypeReward.PROMOCODE);
        quest1.setReward("qeqf34645yeg");
        quest1.setNumOfSteps(12);
        quest1.setDistance(1200f);
        quest1.setDuration("90-120");
        quest1.setPicture_url("https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg");

        quest2.setId(UUID.randomUUID());
        quest2.setName("Kyiv quest");
        quest2.setDescription("Quest description");
        quest2.setQuestType(QuestType.EDUCATION);
        quest2.setCity(kyiv);
        quest2.setDifficulty(2);
        quest2.setRating(4);
        quest2.setTypeReward(TypeReward.PROMOCODE);
        quest2.setReward("qeqf34645yeg");
        quest2.setNumOfSteps(12);
        quest2.setDistance(1200f);
        quest2.setDuration("90-120");
        quest2.setPicture_url("https://st2.depositphotos.com/1000128/7504/i/450/depositphotos_75041187" +
                "-stock-photo-evening-aerial-scenery-of-kyiv.jpg");
        questService.save(quest1);
        questService.save(quest2);
    }
}
