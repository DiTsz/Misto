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
    private final CompletedTasksService completedTasksService;

    public StartUpData(CityService cityService,
                       QuestService questService,
                       RatingService ratingService,
                       UserService userService,
                       UserAvatarService userAvatarService,
                       CompletedQuestsService completedQuestsService,
                       FeaturedQuestsService featuredQuestsService,
                       QuestTaskService questTaskService,
                       RangService rangService,
                       CompletedTasksService completedTasksService) {
        this.cityService = cityService;
        this.questService = questService;
        this.ratingService = ratingService;
        this.userService = userService;
        this.userAvatarService = userAvatarService;
        this.completedQuestsService = completedQuestsService;
        this.featuredQuestsService = featuredQuestsService;
        this.questTaskService = questTaskService;
        this.rangService = rangService;
        this.completedTasksService = completedTasksService;
    }

    @Override
    public void run(String... args) {
        /*exampleAvatars();
        exampleRangs();
        exampleUsers();
        exampleCities();
        exampleQuests();
        exampleRatings();
        exampleCompletedQuests();
        exampleFeaturedQuests();
        exampleQuestTasks();
        exampleCompletedTasks();
        test();*/
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
        User user = new User("user","Михайло","Васильківський", "useruser", "userexpl@gmail.com", Role.USER);
        User admin = new User("admin","Адмін","Хакерович", "adminadmin", "adminexpl@gmail.com", Role.ADMIN);
        User user2 = new User("user2", "user2user", "user2expl@gmail.com", Role.USER);
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
        //final City kyiv = cityService.getByName(CityName.KYIV.name());
        //final City lviv = cityService.getByName(CityName.LVIV.name());

        Quest quest1 = new Quest("Квест по Сумській. 1 рівень.",
                "Даний квест включає в себе невелику подорож по цікавим закладам по центральній вулиці Харкова - Сумській",
                Difficulty.EASY,
                TypeReward.PROMOCODE,
                QuestType.GASTRO,
                "qeqf34645yeg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/%D0%A1%D1%83%D0%BC%D1%" +
                        "81%D1%8C%D0%BA%D0%B0%2C17-22.%D0%A5%D0%B0%D1%80%D0%BA%D1%96%D0%B2.jpg/420px-%D0%A1%D1%83%" +
                        "D0%BC%D1%81%D1%8C%D0%BA%D0%B0%2C17-22.%D0%A5%D0%B0%D1%80%D0%BA%D1%96%D0%B2.jpg",
                4, 1200f, 90, 120, kharkiv);

        Quest quest2 = new Quest("Квест по Сумській. 2 рівень.",
                "Даний квест включає в себе більш складну подорож по цікавим закладам по центральній вулиці Харкова - Сумській",
                Difficulty.MEDIUM,
                TypeReward.PROMOCODE,
                QuestType.ENTERTAINMENT,
                "qeqf34645yeg",
                "https://kharkovgo.com/wp-content/uploads/2020/03/65661820_459603831470433_1931600398221574144_o-551x368.jpg",
                4, 1200f, 90, 120, kharkiv);
        /*Quest quest3 = new Quest("Cymska quest2",
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
                12, 1200f, 90, 120, kharkiv);*/
        questService.save(quest1);
        questService.save(quest2);
        /*questService.save(quest3);
        questService.save(quest4);
        questService.save(quest5);
        questService.save(quest6);
        questService.save(quest7);
        questService.save(quest8);*/

    }

    public void exampleRatings() {
        User user = userService.getByName("user");
        Quest quest1 = questService.getByName("Квест по Сумській. 1 рівень.");
        Quest quest2 = questService.getByName("Квест по Сумській. 2 рівень.");
        /*Quest quest3 = questService.getByName("Cymska quest3");
        Quest quest4 = questService.getByName("Cymska quest4");
        Quest quest5 = questService.getByName("Cymska quest5");
        Quest quest6 = questService.getByName("Cymska quest6");
        Quest quest7 = questService.getByName("Kyiv quest");
        Quest quest8 = questService.getByName("Lviv quest");*/

        Rating rating1 = new Rating(quest1, user, 25);
        Rating rating3 = new Rating(quest1, user, 30);
        Rating rating4 = new Rating(quest2, user, 90);
        /*Rating rating7 = new Rating(quest3, user, 60);
        Rating rating10 = new Rating(quest4, user, 100);
        Rating rating12 = new Rating(quest5, user, 8);
        Rating rating14 = new Rating(quest6, user, 75);
        Rating rating16 = new Rating(quest7, user, 88);
        Rating rating17 = new Rating(quest8, user, 61);*/

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
        //ratingService.save(rating7);
        // ratingService.save(rating8);
        //ratingService.save(rating9);
        //ratingService.save(rating10);
        // ratingService.save(rating11);
       // ratingService.save(rating12);
        //ratingService.save(rating13);
        //ratingService.save(rating14);
        //ratingService.save(rating15);
       // ratingService.save(rating16);
        //ratingService.save(rating17);


    }

    public void exampleCompletedQuests() {
        User user = userService.getByName("user");
        User user2 = userService.getByName("user2");
        Quest quest1 = questService.getByName("Квест по Сумській. 1 рівень.");
        Quest quest5 = questService.getByName("Квест по Сумській. 2 рівень.");
        //Quest quest6 = questService.getByName("Cymska quest6");

        // CompletedQuests completedQuests = new CompletedQuests(user, quest1);
        CompletedQuests completedQuests2 = new CompletedQuests(user, quest5);
        //CompletedQuests completedQuests3 = new CompletedQuests(user, quest6);
        CompletedQuests completedQuests5 = new CompletedQuests(user2, quest1);
        CompletedQuests completedQuests6 = new CompletedQuests(user2, quest5);
        //CompletedQuests completedQuests7 = new CompletedQuests(user2, quest6);
        // completedQuestsService.save(completedQuests);
        completedQuestsService.save(completedQuests2);
        //completedQuestsService.save(completedQuests3);
        completedQuestsService.save(completedQuests5);
        completedQuestsService.save(completedQuests6);
        //completedQuestsService.save(completedQuests7);


    }

    public void exampleFeaturedQuests() {
        User user = userService.getByName("user");
        Quest quest1 = questService.getByName("Квест по Сумській. 1 рівень.");
        Quest quest5 = questService.getByName("Квест по Сумській. 2 рівень.");
        //Quest quest6 = questService.getByName("Cymska quest6");
        //Quest quest8 = questService.getByName("Lviv quest");

        featuredQuestsService.addInFeatured(user, quest1);
        featuredQuestsService.addInFeatured(user, quest5);
        //featuredQuestsService.addInFeatured(user, quest6);
        //featuredQuestsService.addInFeatured(user, quest8);
        //featuredQuestsService.removeFromFeatured(user, quest6);

    }

    public void exampleQuestTasks() {
        Quest quest1 = questService.getByName("Квест по Сумській. 1 рівень.");
        Quest quest2 = questService.getByName("Квест по Сумській. 2 рівень.");
        QuestTask likesItHot = new QuestTask("Фільм, в якому кульмінаційною піснею є ця чудова композиція відомої співачки та акторки Мерлін Монро",
                List.of("Some like it hot", "Some likes it hot", "some like it hot", "some likes it hot",
                        "В джазі тільки дівчата", "в джазі тільки дівчата", "Хтось любить гарячіше",
                        "хтось любить гарячіше"), quest1, QuestTaskType.MYSTERY,
                "4 слова, назва англійська, це назва дуже відомого фільму", 1, "https://somelikeithot.com.ua/index.html");
        QuestTask sho = new QuestTask("Відгадкою цього питання буде третє слово у відомому вислові «Харківсього сленгу», самец е слово є назвою крутого закладу в центрі міста",
                List.of("sho", "Sho", "Шо", "шо"), quest1, QuestTaskType.MYSTERY,
                "Українська інтерпретація слова 'What'", 2, "https://shobar.com.ua/");
        QuestTask khmelnitskiy = new QuestTask("18 січня ЦЬОГО року в Переяславі відбулося зібрання представників запоріжського козачества з Богданом Хмельницьким",
                List.of("1654"),quest1,QuestTaskType.MYSTERY,"Підказка відсутня",3,"https://www.instagram.com/gorcafe1654/");
        QuestTask kitties = new QuestTask("У давнину цим тваринам поклонялись в Єгипті, вони вважались істотою " +
                "Божою, на них рівнялися. А цей напій, по упередженням міль'ярдів людей, володіє навичками " +
                "енергетика, то ж наступна відгадка – це місце, назва якого скаладається з двох слів, що описані вище",
                List.of("Киці та кава", "киці та кава", "Киці і кава", "киці і кава", "Коти і кава" ,"коти і кава"),
                quest1,QuestTaskType.MYSTERY,"Це про котиків, …і про каву",4,"https://www.instagram.com/cats_and_coffee_kh/");
        QuestTask shevchenko = new QuestTask("Стоїть він велично, з гордо піднятою головою, \n" +
                "Символ волі, поезії та національної слави ця фігура. \n" +
                "На проспекті Шевченка його знайдеш, \n" +
                "де Харків віддзвінює свою історію.\n",List.of("Тарас Шевченко", "пам’ятник Шевченку" ,
                "пам’ятник Тараса Шевченко" ,"пам’ятник Тарасу Шевченку", "Пам’ятник Тараса Шевченко" ),
                quest2,QuestTaskType.MYSTERY,"це пам’ятник в центрі міста",1,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fmykharkov.info%2Fcatalog%2Fpamyatnik-t-" +
                        "g-shevchenko.html&psig=AOvVaw2XYyx1z9Ce0iut5cseNTO8&ust=1684866029318000&source=images&cd=" +
                        "vfe&ved=0CBEQjRxqFwoTCKD01JzFif8CFQAAAAAdAAAAABAI");
        QuestTask cinema = new QuestTask("У місті Харкові є місце, де світ кіно оживає, \n" +
                "Слова з фільму стають реальністю, як казка, що розповідає. \n" +
                "Це місце, яке кожному знайоме, немов магічний світ, \n" +
                "Де можна втекти від реальності, навіть, не раз на рік.\n",
                List.of("Україна", "кінопалац Україна","Кінопалац Україна", "кінопалац «Україна»"),
                quest2,QuestTaskType.MYSTERY,"заклад в парку Шевченка",2,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fmykharkov.info%2Fcat" +
                        "alog%2Fpamyatnik-t-g-shevchenko.html&psig=AOvVaw2XYyx1z9Ce0iut5cseNTO8&ust=1" +
                        "684866029318000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCKD01JzFif8CFQAAAAAdAAAAABAI");
        QuestTask khnure = new QuestTask("У Харкові є місце, де наука розцвітає, \n" +
                "Дослідники й вчені свій розум розкривають. \n" +
                "Лабораторії секретні, знанням вони багаті, \n" +
                "Це місце, де інновації ніколи не затихають.\n" +
                "Тут знаходиться центр, де розробляють техніку, \n" +
                "Роботи і винаходи дивовижні й фантастичні. \n" +
                "Місце, де мрії стають реальністю й надією, \n" +
                "Це загадка Харкова, наукова магія безкінечна.\n",
                List.of("ХНУРЕ", "хнуре","Хнуре"),quest2,QuestTaskType.MYSTERY,"учбовий заклад на Науковій",3,
                "https://nure.ua/");
        QuestTask misto = new QuestTask("У місті цьому таємний закуток є, \n" +
                "Де нічні години свою магію несуть. \n" +
                "Тут вогні розбурхано танцюють у темряві, \n" +
                "Ритмом серця віддаються, немов музику приховують.\n" +
                "Коли дзвонить ніч, це місце оживає, \n" +
                "Люди забувають про сивий буденний день. \n" +
                "Музика вдихає життя в їхні душі без краю, \n" +
                "Тут веселі танцівники свої мрії таємні зустрічають без суджень.\n" +
                "Так загадка моя, вперед вас веде,  \n" +
                "Пошукайте місце, де ніч зупиняє час. \n" +
                "Де клубні вогні грають у ритмі людей,\n" +
                "Знаходьте відгадку, як початок пригод у нас.\n",
                List.of("МІСТО", "Місто", "клуб МІСТО", "Клуб МІСТО", "клуб місто" ,"місто", "клуб Місто"),
                quest2,QuestTaskType.MYSTERY,"клуб в центрі міста",4,
                "https://instagram.com/misto_complex?igshid=MmJiY2I4NDBkZg==");
        questTaskService.save(likesItHot);
        questTaskService.save(sho);
        questTaskService.save(khmelnitskiy);
        questTaskService.save(kitties);
        questTaskService.save(shevchenko);
        questTaskService.save(cinema);
        questTaskService.save(khnure);
        questTaskService.save(misto);


    }

    public void exampleCompletedTasks() {
        User user = userService.getByName("user");
        Quest quest1 = questService.getByName("Квест по Сумській. 1 рівень.");
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest1, 1);
        QuestTask questTask2 = questTaskService.getByQuestAndOrders(quest1, 2);
        QuestTask questTask3 = questTaskService.getByQuestAndOrders(quest1, 3);

        CompletedTasks completedTasks = new CompletedTasks(user, questTask);
        CompletedTasks completedTasks2 = new CompletedTasks(user, questTask2);
        CompletedTasks completedTasks3 = new CompletedTasks(user, questTask3);
        completedTasksService.save(completedTasks);
        completedTasksService.save(completedTasks2);
        completedTasksService.save(completedTasks3);


    }

    public void test() {
        //System.out.println(questService.getByCityNameSortedByRating(CityName.KHARKIV));
        //System.out.println(questService.getFirst5OrderByRatings());
        //System.out.println(userService.getAll());
        //System.out.println(questService.getAll());
//        User user = userService.getByName("user");
//        System.out.println(completedQuestsService.getAllByUser(user));
//        System.out.println(completedQuestsService.countCompletedQuestsByUser(user));
        // TEST QUEST COMPLETING
        /*
        Quest quest1 = questService.getByName("Cymska quest1");
        User user = userService.getByName("user");
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest1, 1);
        QuestTask questTask2 = questTaskService.getNextQuestTask(questTask,"2",user);
        QuestTask questTask3 = questTaskService.getNextQuestTask(questTask2,"Five",user);
        QuestTask questTask4 = questTaskService.getNextQuestTask(questTask3,"Kravchuk",user);
        System.out.println(questTask);
        System.out.println(questTask2);
        System.out.println(questTask3);
        System.out.println(questTask4);
        System.out.println(questTaskService.isAnswerRight(questTask2,"5"));*/
    }

}
