package kg.neobis.diabetes.entity.enums;

import lombok.Getter;

@Getter
public enum Widgets {
    WATER(0, "Вода"),
    SLEEP(1, "Сон"),
    FOOD(2, "Еда"),
    MOOD(3, "Настроение"),
    CIGARETTE(4, "Сигареты"),
    ALCOHOL(5, "Алкоголь"),
    MEDICATION(6, "Медикаменты"),
    SUGAR(7, "Сахар"),
    PHYSICAL_ACTIVITY(8, "Физическая актиность"),
    INSULIN(9, "Инсулин");


    private long id;
    private String name;

    Widgets(long id, String name){
        this.id = id;
        this.name = name;
    }

    public static Widgets get(long id){
        for(Widgets widget : Widgets.values())
            if(widget.getId() == id)
                return widget;

            return  null;
    }

}
