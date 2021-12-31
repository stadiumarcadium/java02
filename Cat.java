package lesson01;

public class Cat implements RunnerJumper {

    public int MAX_RUN_DISTANCE;
    public int MAX_JUMP_DISTANCE;

    public int getMaxRunDistance() {
        return MAX_RUN_DISTANCE;
    }

    public int getMaxJumpDistance() {
        return MAX_JUMP_DISTANCE;
    }

    public Cat(int _MAX_RUN_DISTANCE, int _MAX_JUMP_DISTANCE) {
        MAX_RUN_DISTANCE = _MAX_RUN_DISTANCE;
        MAX_JUMP_DISTANCE = _MAX_JUMP_DISTANCE;
    }

    public void runJump() {
    }

    ;

}

