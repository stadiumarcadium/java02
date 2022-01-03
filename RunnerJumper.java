package lesson01;

import javax.print.attribute.standard.JobImpressionsSupported;
import javax.swing.plaf.SpinnerUI;

interface RunnerJumper {

    public int getMaxRunDistance();

    public int getMaxJumpDistance();

    public int getStamina();

    public void setStamina(int _stamina);

    default boolean runJump(Obstacle obstacle) {
        String name = this.getClass().getSimpleName();
        if (obstacle instanceof Track) { // как избавиться от instanceof? можно изспользовать enum, но тогда классы дорожка и стена не нужны.
            if (this.getStamina() >= ((Track) obstacle).distance) {
                System.out.printf(name + " пробежал дистанцию %dм%n", ((Track) obstacle).distance);
                int remainStamina = this.getStamina() - ((Track) obstacle).distance;
                this.setStamina(remainStamina);
                return true;
            } else {
                System.out.printf(name + " не пробежал дистанцию %dм и выбыл из гонки%n", ((Track) obstacle).distance);
                return false;
            }
        }

        if (obstacle instanceof Wall) {
            if (this.getMaxJumpDistance() >= ((Wall) obstacle).height) {
                System.out.printf(name + " перепрыгнул стену %dм%n", ((Wall) obstacle).height);
                return true;
            } else {
                System.out.printf(name + " не перепрыгнул стену %dм и выбыл из гонки%n", ((Wall) obstacle).height);
                return false;
            }
        }
        return false;
    }
}