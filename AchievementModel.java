public abstract class AchievementModel {

    char type;
    String name;
    String description;
    int value;
    
    public AchievementModel(char type, String name, String description, int value) {  //zmienic na protected
        this.type = type;
        this.name = name;
        this.description = description;
        this.value = value;
    }
}