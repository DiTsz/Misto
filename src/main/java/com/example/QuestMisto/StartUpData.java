package com.example.QuestMisto;

import com.example.QuestMisto.models.entities.*;
import com.example.QuestMisto.models.enums.*;
import com.example.QuestMisto.services.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class StartUpData implements CommandLineRunner {
    private final CityService cityService;
    private final QuestService questService;
    private final RatingService ratingService;
    private final UserService userService;
    private final UserAvatarService userAvatarService;
    private final CompletedQuestsService completedQuestsService;
    private final FeaturedQuestsService featuredQuestsService;
    private final QuestTaskService questTaskService;
    private final RangService rangService;

    public StartUpData(CityService cityService,
                       QuestService questService,
                       RatingService ratingService,
                       UserService userService,
                       UserAvatarService userAvatarService,
                       CompletedQuestsService completedQuestsService,
                       FeaturedQuestsService featuredQuestsService,
                       QuestTaskService questTaskService,
                       RangService rangService) {
        this.cityService = cityService;
        this.questService = questService;
        this.ratingService = ratingService;
        this.userService = userService;
        this.userAvatarService = userAvatarService;
        this.completedQuestsService = completedQuestsService;
        this.featuredQuestsService = featuredQuestsService;
        this.questTaskService = questTaskService;
        this.rangService = rangService;
    }

    @Override
    public void run(String... args) {
        exampleAvatars();
        exampleRangs();
        exampleUsers();
        exampleCities();
        exampleQuests();
        exampleRatings();
        exampleCompletedQuests();
        exampleFeaturedQuests();
        exampleQuestTasks();
        //test();
    }

    private void exampleAvatars() {
        UserAvatar defaultAvatar = new UserAvatar("Default avatar", "https://png.pngtree.com" +
                "/png-vector/20190223/ourmid/pngtree-vector-avatar-icon-png-image_695765.jpg", 0);
        UserAvatar firstAvatar = new UserAvatar("First avatar", "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg." +
                "freepik.com%2Ffree-icon%2Farcheologist_318-822403.jpg%3Fw%3D2000&tbnid=kNlNQlrXR4nBxM&vet=12ahUKEw" +
                "jI_Nir0e3-AhVowgIHHWATDkAQMygVegUIARD4AQ..i&imgrefurl=https%3A%2F%2Fwww.freepik.com%2Ffree-photo" +
                "s-vectors%2Favatar-hat%2F8&docid=cX-xIctxXoxUoM&w=512&h=512&itg=1&q=the%20explorer%20avatar&ved=2a" +
                "hUKEwjI_Nir0e3-AhVowgIHHWATDkAQMygVegUIARD4AQ", 5);
        userAvatarService.save(defaultAvatar);
        userAvatarService.save(firstAvatar);
    }

    public void exampleRangs() {
        Rang firstRang = new Rang("Newbie", 0);
        Rang explorerRang = new Rang("Explorer", 5);
        rangService.save(firstRang);
        rangService.save(explorerRang);

    }

    private void exampleUsers() {
        UserAvatar firstAvatar = userAvatarService.getByName("First avatar");
        Rang explorerRang = rangService.getByName("Explorer");
        User user = new User("user", "user", "userexpl@gmail.com", Role.USER);
        User admin = new User("admin", "admin", "adminexpl@gmail.com", Role.ADMIN);
        User user2 = new User("user2", "user2", "user2expl@gmail.com", Role.USER);
       /* admin.setNumOfXp(100);
        admin.setUserAvatar(firstAvatar);*/
        userService.save(user);
        userService.save(user2);
        userService.save(admin);
        user2.setUserAvatar(firstAvatar);
        user2.setRang(explorerRang);
        userService.save(user2);

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
        city1.setDescription("Kharkiv description");
        cityService.save(city1);
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
                12, 1200f, 90, 120, kharkiv);

        Quest quest2 = new Quest("Lviv quest",
                "Quest description",
                Difficulty.EASY,
                TypeReward.PROMOCODE,
                QuestType.ENTERTAINMENT,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, lviv);
        Quest quest3 = new Quest("Cymska quest2",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kharkiv);
        Quest quest4 = new Quest("Cymska quest3",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kharkiv);
        Quest quest5 = new Quest("Kyiv quest",
                "Quest description",
                Difficulty.HARD,
                TypeReward.PROMOCODE,
                QuestType.SCIENCE,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kyiv);
        Quest quest6 = new Quest("Cymska quest4",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kharkiv);
        Quest quest7 = new Quest("Cymska quest5",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kharkiv);
        Quest quest8 = new Quest("Cymska quest6",
                "Quest description",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://st4.depositphotos.com/4105125/22151/i/450/" +
                        "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg",
                12, 1200f, 90, 120, kharkiv);
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
        User user = userService.getByName("user");
        Quest quest1 = questService.getByName("Cymska quest1");
        Quest quest2 = questService.getByName("Cymska quest2");
        Quest quest3 = questService.getByName("Cymska quest3");
        Quest quest4 = questService.getByName("Cymska quest4");
        Quest quest5 = questService.getByName("Cymska quest5");
        Quest quest6 = questService.getByName("Cymska quest6");
        Quest quest7 = questService.getByName("Kyiv quest");
        Quest quest8 = questService.getByName("Lviv quest");

        Rating rating1 = new Rating(quest1, user, 25);
        Rating rating3 = new Rating(quest1, user, 30);
        Rating rating4 = new Rating(quest2, user, 90);
        Rating rating7 = new Rating(quest3, user, 60);
        Rating rating10 = new Rating(quest4, user, 100);
        Rating rating12 = new Rating(quest5, user, 8);
        Rating rating14 = new Rating(quest6, user, 75);
        Rating rating16 = new Rating(quest7, user, 88);
        Rating rating17 = new Rating(quest8, user, 61);

       /* Rating rating2 = new Rating(quest1, 30);
        Rating rating3 = new Rating(quest1, 36);
        Rating rating5 = new Rating(quest2, 97);
        Rating rating6 = new Rating(quest2, 95);
        Rating rating8 = new Rating(quest3, 78);
        Rating rating9 = new Rating(quest3, 66);
        Rating rating11 = new Rating(quest4, 99);
        Rating rating13 = new Rating(quest5, 10);
        Rating rating15 = new Rating(quest6, 81);*/


        ratingService.save(rating1);
        //ratingService.save(rating2);
        ratingService.save(rating3);
        ratingService.save(rating4);
        //ratingService.save(rating5);
        //ratingService.save(rating6);
        ratingService.save(rating7);
        // ratingService.save(rating8);
        //ratingService.save(rating9);
        ratingService.save(rating10);
        // ratingService.save(rating11);
        ratingService.save(rating12);
        //ratingService.save(rating13);
        ratingService.save(rating14);
        //ratingService.save(rating15);
        ratingService.save(rating16);
        ratingService.save(rating17);


    }

    public void exampleCompletedQuests() {
        User user = userService.getByName("user");
        User user2 = userService.getByName("user2");
        Quest quest1 = questService.getByName("Cymska quest1");
        Quest quest5 = questService.getByName("Cymska quest5");
        Quest quest6 = questService.getByName("Cymska quest6");

        CompletedQuests completedQuests = new CompletedQuests(user, quest1);
        CompletedQuests completedQuests2 = new CompletedQuests(user, quest5);
        CompletedQuests completedQuests3 = new CompletedQuests(user, quest6);
        CompletedQuests completedQuests5 = new CompletedQuests(user2, quest1);
        CompletedQuests completedQuests6 = new CompletedQuests(user2, quest5);
        CompletedQuests completedQuests7 = new CompletedQuests(user2, quest6);
        completedQuestsService.save(completedQuests);
        completedQuestsService.save(completedQuests2);
        completedQuestsService.save(completedQuests3);
        completedQuestsService.save(completedQuests5);
        completedQuestsService.save(completedQuests6);
        completedQuestsService.save(completedQuests7);


    }

    public void exampleFeaturedQuests() {
        User user = userService.getByName("user");
        Quest quest1 = questService.getByName("Cymska quest1");
        Quest quest5 = questService.getByName("Cymska quest5");
        Quest quest6 = questService.getByName("Cymska quest6");
        Quest quest8 = questService.getByName("Lviv quest");

        featuredQuestsService.addInFeatured(user, quest1);
        featuredQuestsService.addInFeatured(user, quest5);
        featuredQuestsService.addInFeatured(user, quest6);
        featuredQuestsService.addInFeatured(user, quest8);
        featuredQuestsService.removeFromFeatured(user, quest6);

    }

    public void exampleQuestTasks() {
        Quest quest1 = questService.getByName("Cymska quest1");
        QuestTask questTask1 = new QuestTask("What equals 1+1", List.of("2", "two", "Two"), quest1, QuestTaskType.MYSTERY, "You need to calculate expression 1+1");
        QuestTask questTask2 = new QuestTask("How many mainlands on Earth", List.of("5", "five", "Five"), quest1, QuestTaskType.MYSTERY, "You need to find how many mainlands on Earth");
        QuestTask questTask3 = new QuestTask("Who the first president of Ukraine", List.of("Leonid Kravchuk", "Kravchuk", "leonid kravchuk", "kravchuk"), quest1, QuestTaskType.MYSTERY, "A surname of this politician is Kravchuk");
        questTaskService.save(questTask1);
        questTaskService.save(questTask2);
        questTaskService.save(questTask3);

    }

    public void test() {
        //System.out.println(questService.getByCityNameSortedByRating(CityName.KHARKIV));
        //System.out.println(questService.getFirst5OrderByRatings());
        //System.out.println(userService.getAll());
        //System.out.println(questService.getAll());
//        User user = userService.getByName("user");
//        System.out.println(completedQuestsService.getAllByUser(user));
//        System.out.println(completedQuestsService.countCompletedQuestsByUser(user));

    }

}
