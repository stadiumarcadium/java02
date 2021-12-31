package lesson01;

public class Course {
    public static void main(String[] args) {
        Obstacle[] obstacle = new Obstacle[4];
        obstacle[0] = new Track(500);
        obstacle[1] = new Wall(10);
        obstacle[2] = new Track(400);
        obstacle[3] = new Wall(5);

        RunnerJumper[] runnerjumpers = new RunnerJumper[3];
        runnerjumpers[0] = new Cat(500, 10);
        runnerjumpers[1] = new Human(300, 5);
        runnerjumpers[2] = new Robot(1000, 20);
        for (RunnerJumper runnerjumper : runnerjumpers) {
            int stamina = runnerjumper.getMaxRunDistance();
            String name = runnerjumper.getClass().getSimpleName();
            for (Obstacle item : obstacle) {
                if (item instanceof Track) {
                    if (stamina >= ((Track) item).DISTANCE) {
                        System.out.printf(name+" пробежал дистанцию %dм%n", ((Track) item).DISTANCE);
                        stamina = stamina - ((Track) item).DISTANCE;
                    } else {
                        System.out.printf(name+" не пробежал дистанцию %dм и выбыл из гонки%n", ((Track) item).DISTANCE);
                        break;
                    }
                }
                if (item instanceof Wall) {
                    if (runnerjumper.getMaxJumpDistance() >= ((Wall) item).HEIGHT) {
                        System.out.printf(name+" перепрыгнул стену %dм%n", ((Wall) item).HEIGHT);
                    } else {
                        System.out.printf(name+" не перепрыгнул стену %dм и выбыл из гонки%n", ((Wall) item).HEIGHT);
                        break;
                    }
                }
            }
        }
    }
}


