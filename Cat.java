package lesson01;

public class Cat implements RunnerJumper {

    public int max_jump_height;

    public int stamina;
    public int getStamina(){return stamina;};
    public void setStamina(int _stamina){
        this.stamina = _stamina;
    };

    public int getMaxRunDistance() {
        return stamina;
    }

    public int getMaxJumpDistance() {
        return max_jump_height;
    }

    public Cat(int _stamina, int _max_jump_height) {
        stamina = _stamina;
        max_jump_height = _max_jump_height;
    }

    //public void runJump() {}

    ;

}

