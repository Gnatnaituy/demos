package base;

import java.util.HashMap;

public class EnumDemo {

    public enum Direction {
        EAST(0), WEST(180), NORTH(90), SOUTH(270);

        private final int angle;

        Direction(final int angle) {
            this.angle = angle;
        }

        public int getAngle() {
            return angle;
        }

        protected String printDirection() {
            String message = "You are moving in " + this + " direction";
            System.out.println(message);
            return message;
        }

    }

    public static void main(String[] args) {
        Direction north = Direction.NORTH;
        System.out.println(north);

        // The ordinal() method returns the order of an enum instance.
        System.out.println(Direction.NORTH.ordinal());  // 2
        System.out.println(north.ordinal());            // 2

        // The enum values() method returns all the enum values in an enum array.
        Direction[] directions = Direction.values();
        for (Direction d : directions) {
            System.out.println(d);
        }

        // The enum valueOf() method helps to convert string to enum instance.
        // Direction top = Direction.valueOf("TOP"); // java.lang.IllegalArgumentException
        Direction east = Direction.valueOf("EAST");
        System.out.println(east);

        // Constructor and Methods in Enum
        System.out.println(north.getAngle());
        System.out.println(Direction.NORTH.getAngle());
        north.printDirection();
    }
}