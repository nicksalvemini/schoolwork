package toys;

/**
 * A concrete Action Figure that can be made
 * and played with
 *
 * @author Nick Salvemini
 */
public class ActionFigure extends Doll{

    /** The minimum energy level of any action figure */
    public final static int MIN_ENERGY_LEVEL = 1;

    /** The hair color of every action figure */
    public final static Color HAIR_COLOR = Color.ORANGE;

    /** The current energy level of the action figure */
    private int energyLevel;

    /**
     * Constructor for the action figure toy
     *
     * @param name Name of the action figure
     * @param age Age of the action figure
     * @param speak Catchphrase of the action figure
     */
    protected ActionFigure(String name, int age, String speak){
        super(name, HAIR_COLOR, age, speak);
        energyLevel = MIN_ENERGY_LEVEL;
    }

    /**
     * Gets the energy level of the action figure
     *
     * @return Returns the energy level
     */
    public int getEnergyLevel(){
        return energyLevel;
    }

    /**
     * Displays a play message for the Action Figure
     * and then increases its wear
     *
     * @param time Amount of minutes to play with the Action Figure
     */
    @Override
    protected void specialPlay(int time){
        System.out.println("\t" + getName() + " kung foo chops with " +
                (energyLevel * time) + " energy!");
        energyLevel++;
        super.specialPlay(time);
    }

    /**
     * Override of the toString()
     *
     * @return Returns a nicely formatted list
     *          of information about the Action Figure
     */
    @Override
    public String toString(){
        return super.toString() +
                ", ActionFigure{E:" + energyLevel + "}";
    }
}