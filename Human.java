package lesson01;

import java.util.function.ToIntBiFunction;

public class Human implements RunnerJumper {
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
    public Human(int _stamina, int _MAX_JUMP_DISTANCE) {
        stamina = _stamina;
        MAX_JUMP_DISTANCE = _MAX_JUMP_DISTANCE;
    }
  //  public void runJump(){};
}
