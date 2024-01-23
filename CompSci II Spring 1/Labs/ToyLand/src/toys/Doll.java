package toys;

/**
 * A concrete doll that can be made
 * and played with
 *
 * @author Nick Salvemini
 */
public class Doll extends Toy{

    /** The product code of the doll */
    private static int productCode = 200;

    /** The name of the doll */
    private final String name;

    /** The hair color of the doll */
    private final Color hairColor;

    /** The age of the doll */
    private final int age;

    /** The doll's catchphrase */
    private final String speak;

    /**
     * Constructor for the doll toy
     *
     * @param name Name of the doll
     * @param hairColor Hair color of the doll
     * @param age Age of the doll
     * @param speak Catchphrase of the doll
     */
    protected Doll(String name, Color hairColor, int age, String speak){
        super(productCode, name);
        productCode++;
        this.name = name;
        this.hairColor = hairColor;
        this.age = age;
        this.speak = speak;
    }

    /**
     * Gets the doll's hair color
     *
     * @return Returns the hair color
     */
    public Color getHairColor(){
        return hairColor;
    }

    /**
     * Gets the doll's age
     *
     * @return Returns the age
     */
    public int getAge(){
        return age;
    }

    /**
     * Returns the doll's catchphrase
     *
     * @return Returns the catchphrase
     */
    public String getSpeak(){
        return speak;
    }

    /**
     * Displays a play message for the doll
     * and then increases its wear
     *
     * @param time Amount of minutes to play with the doll
     */
    @Override
    protected void specialPlay(int time){
        System.out.println("\t" + name + " brushes their " +
                hairColor + " hair and says, " + '"' + speak + '"');
        increaseWear(age);
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the doll
     */
    @Override
    public String toString(){
        return super.toString() +
                ", Doll{HC:" +  hairColor +
                ", A:" + age +
                ", S:" + speak + "}";
    }
}