package lesson01;


public class Course {
    public static void main(String[] args) {
        Obstacle[] obstacles = {new Track(500), new Wall(10), new Track(400), new Wall(5)};

        RunnerJumper[] runnerjumpers = {new Cat(500, 10), new Human(300, 5), new Robot(1000, 20)};

        for (RunnerJumper runnerjumper : runnerjumpers) {
            String name = runnerjumper.getClass().getSimpleName();
            for (Obstacle obstacle : obstacles) {
                boolean cantRun = !runnerjumper.runJump(obstacle);
                if (cantRun) {
                    break;
                }
            }
        }
  }
}




