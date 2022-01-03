package lesson01;

public class Robot implements RunnerJumper {
    public int MAX_JUMP_DISTANCE;

    public int stamina;
    public int getStamina(){return stamina;};
    public void setStamina(int _stamina){
        this.stamina = _stamina;
    };
    public int getMaxRunDistance(){
        return stamina;
    }
    public int getMaxJumpDistance(){
        return MAX_JUMP_DISTANCE;
    }

    public Robot(int _stamina, int _MAX_JUMP_DISTANCE) {
        stamina = _stamina;
        MAX_JUMP_DISTANCE = _MAX_JUMP_DISTANCE;
    }
    //public void runJump(){};
}
